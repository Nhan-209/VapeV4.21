//! Native bootstrap worker thread, JVM discovery, payload JAR extraction,
//! and automated NativeBridge initialization.

use core::ffi::c_void;
use std::ffi::{CStr, CString};
use std::fs::{create_dir_all, File};
use std::io::Write;
use std::path::PathBuf;
use std::ptr;
use std::sync::atomic::Ordering;
use std::thread::sleep;
use std::time::Duration;
use vape421_core::bootstrap::{
    vape_loader_bootstrap_initialize, vape_loader_report_completed, vape_loader_report_failure,
};
use vape421_core::jni_sys::*;
use vape421_core::logger::{vape_log, vape_log_pending_exception};
use crate::classloader::add_jar_to_loader;
use crate::jvmti_bridge::{vape_initialize_jvmti, G_JVMTI, G_MODULE};
use crate::native_methods::vape_register_native_bridge;

const VAPE421_PRODUCT_JAR_RESOURCE_ID: usize = 421;

pub fn get_temp_recovery_dir() -> PathBuf {
    std::env::temp_dir().join("Vape421Recovery")
}

pub unsafe fn materialize_embedded_product_jar() -> Option<PathBuf> {
    let pid = std::process::id();
    let temp_dir = get_temp_recovery_dir();
    let _ = create_dir_all(&temp_dir);
    let target_jar = temp_dir.join(format!("vape421-product-{}.jar", pid));

    #[cfg(windows)]
    {
        use windows_sys::Win32::System::LibraryLoader::{
            FindResourceW, GetModuleFileNameW, LoadResource, LockResource, SizeofResource,
        };

        let module = G_MODULE.load(Ordering::SeqCst) as windows_sys::Win32::Foundation::HMODULE;
        if !module.is_null() {
            let resource = FindResourceW(
                module,
                VAPE421_PRODUCT_JAR_RESOURCE_ID as *const u16,
                10 as *const u16, // RT_RCDATA
            );

            if !resource.is_null() {
                let size = SizeofResource(module, resource);
                let loaded = LoadResource(module, resource);
                let bytes = if loaded.is_null() {
                    ptr::null()
                } else {
                    LockResource(loaded) as *const u8
                };

                if !bytes.is_null() && size >= 4 {
                    let slice = std::slice::from_raw_parts(bytes, size as usize);
                    if slice.starts_with(b"PK") {
                        if let Ok(mut file) = File::create(&target_jar) {
                            if file.write_all(slice).is_ok() && file.flush().is_ok() {
                                vape_log(&format!(
                                    "materialized embedded product JAR: {} ({} bytes)",
                                    target_jar.display(),
                                    size
                                ));
                                return Some(target_jar);
                            }
                        }
                    }
                }
            }

            // Fallback: search for any .jar file in the same directory as the native DLL
            let mut buffer = [0u16; 1024];
            let len = GetModuleFileNameW(module, buffer.as_mut_ptr(), 1024);
            if len > 0 {
                let mod_path = String::from_utf16_lossy(&buffer[..len as usize]);
                if let Some(parent) = std::path::Path::new(&mod_path).parent() {
                    if let Ok(entries) = std::fs::read_dir(parent) {
                        for entry in entries.flatten() {
                            let path = entry.path();
                            if path.is_file() {
                                if let Some(ext) = path.extension() {
                                    if ext.eq_ignore_ascii_case("jar") {
                                        vape_log(&format!(
                                            "found product JAR beside DLL: {}",
                                            path.display()
                                        ));
                                        return Some(path);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if target_jar.exists() {
        return Some(target_jar);
    }

    if let Ok(entries) = std::fs::read_dir(&temp_dir) {
        for entry in entries.flatten() {
            let path = entry.path();
            if path.is_file() {
                if let Some(ext) = path.extension() {
                    if ext.eq_ignore_ascii_case("jar") {
                        vape_log(&format!("found product JAR in temp dir: {}", path.display()));
                        return Some(path);
                    }
                }
            }
        }
    }

    None
}

unsafe fn find_client_class_loader(env: *mut JNIEnv) -> jobject {
    let jvmti = G_JVMTI.load(Ordering::SeqCst);
    if jvmti.is_null() {
        return ptr::null_mut();
    }

    let mut thread_count: jint = 0;
    let mut threads_ptr: *mut jthread = ptr::null_mut();

    let err = ((*(*jvmti)).GetAllThreads)(jvmti, &mut thread_count, &mut threads_ptr);
    if err != JVMTI_ERROR_NONE || threads_ptr.is_null() || thread_count <= 0 {
        vape_log(&format!("GetAllThreads failed: {}", err));
        return ptr::null_mut();
    }

    let threads = std::slice::from_raw_parts(threads_ptr, thread_count as usize);
    let mut result_loader: jobject = ptr::null_mut();

    for &thread in threads {
        let mut info: jvmtiThreadInfo = std::mem::zeroed();
        if ((*(*jvmti)).GetThreadInfo)(jvmti, thread, &mut info) == JVMTI_ERROR_NONE {
            if !info.name.is_null() {
                let name_bytes = CStr::from_ptr(info.name).to_bytes();
                if (name_bytes == b"Client thread" || name_bytes == b"Render thread")
                    && !info.context_class_loader.is_null()
                {
                    result_loader = ((*(*env)).NewLocalRef)(env, info.context_class_loader);
                }
                ((*(*jvmti)).Deallocate)(jvmti, info.name as *mut u8);
            }

            if !info.thread_group.is_null() {
                ((*(*env)).DeleteLocalRef)(env, info.thread_group);
            }
            if !info.context_class_loader.is_null() {
                ((*(*env)).DeleteLocalRef)(env, info.context_class_loader);
            }
        }

        ((*(*env)).DeleteLocalRef)(env, thread);
        if !result_loader.is_null() {
            break;
        }
    }

    ((*(*jvmti)).Deallocate)(jvmti, threads_ptr as *mut u8);
    result_loader
}

unsafe fn set_current_context_class_loader(env: *mut JNIEnv, loader: jobject) -> bool {
    let thread_class = ((*(*env)).FindClass)(env, CString::new("java/lang/Thread").unwrap().as_ptr());
    if thread_class.is_null() {
        vape_log_pending_exception(env, "resolve java.lang.Thread");
        return false;
    }

    let cur_sig = CString::new("()Ljava/lang/Thread;").unwrap();
    let set_sig = CString::new("(Ljava/lang/ClassLoader;)V").unwrap();

    let cur_method = ((*(*env)).GetStaticMethodID)(
        env,
        thread_class,
        CString::new("currentThread").unwrap().as_ptr(),
        cur_sig.as_ptr(),
    );
    let set_method = ((*(*env)).GetMethodID)(
        env,
        thread_class,
        CString::new("setContextClassLoader").unwrap().as_ptr(),
        set_sig.as_ptr(),
    );

    if cur_method.is_null() || set_method.is_null() {
        vape_log_pending_exception(env, "resolve Thread context ClassLoader methods");
        return false;
    }

    let current_thread = ((*(*env)).CallStaticObjectMethod)(env, thread_class, cur_method);
    if current_thread.is_null() || ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
        vape_log_pending_exception(env, "Thread.currentThread");
        return false;
    }

    ((*(*env)).CallVoidMethod)(env, current_thread, set_method, loader);
    if ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
        vape_log_pending_exception(env, "Thread.setContextClassLoader");
        return false;
    }

    true
}

unsafe fn load_bridge_class(env: *mut JNIEnv, loader: jobject) -> jclass {
    let loader_class = ((*(*env)).FindClass)(env, CString::new("java/lang/ClassLoader").unwrap().as_ptr());
    let load_sig = CString::new("(Ljava/lang/String;)Ljava/lang/Class;").unwrap();
    let load_class = if !loader_class.is_null() {
        ((*(*env)).GetMethodID)(env, loader_class, CString::new("loadClass").unwrap().as_ptr(), load_sig.as_ptr())
    } else {
        ptr::null_mut()
    };

    let name = CString::new("gg.vape.runtime.NativeBridge").unwrap();
    let name_str = ((*(*env)).NewStringUTF)(env, name.as_ptr());

    if load_class.is_null() || name_str.is_null() {
        return ptr::null_mut();
    }

    let bridge = ((*(*env)).CallObjectMethod)(env, loader, load_class, name_str) as jclass;
    if bridge.is_null() || ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
        vape_log_pending_exception(env, "load gg.vape.runtime.NativeBridge");
        return ptr::null_mut();
    }

    bridge
}

unsafe fn pin_native_module() -> bool {
    #[cfg(windows)]
    {
        use windows_sys::Win32::Foundation::GetLastError;
        use windows_sys::Win32::System::LibraryLoader::{
            GetModuleHandleExW, GET_MODULE_HANDLE_EX_FLAG_FROM_ADDRESS,
            GET_MODULE_HANDLE_EX_FLAG_PIN,
        };

        let module = G_MODULE.load(Ordering::SeqCst);
        let mut pinned = ptr::null_mut();
        let ok = GetModuleHandleExW(
            GET_MODULE_HANDLE_EX_FLAG_FROM_ADDRESS | GET_MODULE_HANDLE_EX_FLAG_PIN,
            module as *const u16,
            &mut pinned,
        );

        if ok == 0 {
            vape_log(&format!("GetModuleHandleExW(PIN) failed: {}", GetLastError()));
            return false;
        }
    }
    true
}

unsafe fn call_bridge_start(env: *mut JNIEnv, bridge_class: jclass) -> bool {
    let start_sig = CString::new("()V").unwrap();
    let start = ((*(*env)).GetStaticMethodID)(
        env,
        bridge_class,
        CString::new("start").unwrap().as_ptr(),
        start_sig.as_ptr(),
    );

    if start.is_null() {
        vape_log_pending_exception(env, "resolve NativeBridge.start");
        return false;
    }

    ((*(*env)).CallStaticVoidMethod)(env, bridge_class, start);
    if ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
        vape_log_pending_exception(env, "NativeBridge.start");
        return false;
    }

    true
}

type GetCreatedJavaVMsFn = unsafe extern "system" fn(
    vmBuf: *mut *mut JavaVM,
    bufLen: jsize,
    nVMs: *mut jsize,
) -> jint;

pub unsafe fn run_bootstrap_worker() -> u32 {
    if !vape_loader_bootstrap_initialize() {
        vape_log("Loader token bootstrap is invalid");
        vape_loader_report_failure("Native bootstrap failed with code 6");
        return 6;
    }

    sleep(Duration::from_millis(150));

    let mut vm: *mut JavaVM = ptr::null_mut();
    let env: *mut JNIEnv;

    #[cfg(windows)]
    {
        use windows_sys::Win32::System::LibraryLoader::{GetModuleHandleW, GetProcAddress};

        let jvm_name: Vec<u16> = "jvm.dll".encode_utf16().chain(std::iter::once(0)).collect();
        let mut jvm_module = ptr::null_mut();

        for _ in 0..600 {
            jvm_module = GetModuleHandleW(jvm_name.as_ptr());
            if !jvm_module.is_null() {
                break;
            }
            sleep(Duration::from_millis(100));
        }

        if jvm_module.is_null() {
            vape_log("jvm.dll is not loaded");
            vape_loader_report_failure("Native bootstrap failed with code 2");
            return 2;
        }

        let sym = CString::new("JNI_GetCreatedJavaVMs").unwrap();
        let proc = GetProcAddress(jvm_module, sym.as_ptr() as *const u8);
        if proc.is_none() {
            vape_log("JNI_GetCreatedJavaVMs export is unavailable");
            vape_loader_report_failure("Native bootstrap failed with code 3");
            return 3;
        }

        let get_created_vms: GetCreatedJavaVMsFn = std::mem::transmute(proc.unwrap());
        let mut vm_count: jsize = 0;

        for _ in 0..600 {
            if get_created_vms(&mut vm, 1, &mut vm_count) == JNI_OK && !vm.is_null() && vm_count >= 1 {
                break;
            }
            vm = ptr::null_mut();
            vm_count = 0;
            sleep(Duration::from_millis(100));
        }
    }

    if vm.is_null() {
        vape_log("JNI_GetCreatedJavaVMs returned no VM");
        vape_loader_report_failure("Native bootstrap failed with code 4");
        return 4;
    }

    let mut env_ptr: *mut c_void = ptr::null_mut();
    let attach_res = ((*(*vm)).AttachCurrentThreadAsDaemon)(vm, &mut env_ptr, ptr::null_mut());
    if attach_res != JNI_OK || env_ptr.is_null() {
        vape_log("AttachCurrentThreadAsDaemon failed");
        vape_loader_report_failure("Native bootstrap failed with code 5");
        return 5;
    }

    env = env_ptr as *mut JNIEnv;

    if vape_initialize_jvmti(vm) != JNI_OK {
        ((*(*vm)).DetachCurrentThread)(vm);
        vape_loader_report_failure("Native bootstrap failed with code 1");
        return 1;
    }

    let jar_path = match materialize_embedded_product_jar() {
        Some(p) => p,
        None => {
            ((*(*vm)).DetachCurrentThread)(vm);
            vape_loader_report_failure("Native bootstrap failed with code 1");
            return 1;
        }
    };

    let mut loader: jobject = ptr::null_mut();
    for _ in 0..600 {
        loader = find_client_class_loader(env);
        if !loader.is_null() {
            break;
        }
        sleep(Duration::from_millis(100));
    }

    if loader.is_null() {
        vape_log("Minecraft client/render thread was not found within 60 seconds");
        ((*(*vm)).DetachCurrentThread)(vm);
        vape_loader_report_failure("Native bootstrap failed with code 1");
        return 1;
    }

    let jar_path_str = jar_path.to_string_lossy().to_string();
    if !add_jar_to_loader(env, &mut loader, &jar_path_str) {
        ((*(*vm)).DetachCurrentThread)(vm);
        vape_loader_report_failure("Native bootstrap failed with code 1");
        return 1;
    }

    if !set_current_context_class_loader(env, loader) {
        ((*(*vm)).DetachCurrentThread)(vm);
        vape_loader_report_failure("Native bootstrap failed with code 1");
        return 1;
    }

    let bridge_class = load_bridge_class(env, loader);
    if bridge_class.is_null() {
        ((*(*vm)).DetachCurrentThread)(vm);
        vape_loader_report_failure("Native bootstrap failed with code 1");
        return 1;
    }

    if vape_register_native_bridge(env, bridge_class) != JNI_OK {
        ((*(*vm)).DetachCurrentThread)(vm);
        vape_loader_report_failure("Native bootstrap failed with code 1");
        return 1;
    }

    let _ = pin_native_module();
    vape_log(&format!("NativeBridge linked from {}", jar_path_str));

    if !call_bridge_start(env, bridge_class) {
        ((*(*vm)).DetachCurrentThread)(vm);
        vape_loader_report_failure("Native bootstrap failed with code 1");
        return 1;
    }

    vape_loader_report_completed();
    vape_log("NativeBridge.start completed; injection is active");

    ((*(*vm)).DetachCurrentThread)(vm);
    0
}
