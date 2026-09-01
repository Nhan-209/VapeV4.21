//! Lunar / OptiFine transformer order collector and bytecode dumper.

use core::ffi::c_void;
use std::ffi::{CStr, CString};
use std::fs::{create_dir_all, File, OpenOptions};
use std::io::Write;
use std::path::PathBuf;
use std::ptr;
use std::sync::atomic::{AtomicPtr, Ordering};
use std::sync::Mutex;
use std::thread::sleep;
use std::time::Duration;
use vape421_core::jni_sys::*;
use vape421_core::logger::current_timestamp;

static DUMP_VM: AtomicPtr<JavaVM> = AtomicPtr::new(ptr::null_mut());
static DUMP_JVMTI: AtomicPtr<jvmtiEnv> = AtomicPtr::new(ptr::null_mut());
static DUMP_MUTEX: Mutex<()> = Mutex::new(());

struct ActiveTarget {
    target: jclass,
    serial: u32,
    hook_serial: u32,
    dir: PathBuf,
    log_path: PathBuf,
}

unsafe impl Send for ActiveTarget {}
unsafe impl Sync for ActiveTarget {}

static ACTIVE_TARGET: Mutex<Option<ActiveTarget>> = Mutex::new(None);

pub fn dump_filter(signature: &str) -> bool {
    if !signature.starts_with('L') {
        return false;
    }

    if signature.starts_with("Lnet/minecraft/")
        || signature.starts_with("Lnet/optifine/")
        || signature.starts_with("Loptifine/")
        || signature.starts_with("Lshadersmod/")
        || signature.starts_with("Lcom/moonsworth/")
        || signature.starts_with("Lcom/lunarclient/")
    {
        return true;
    }

    let len = signature.len();
    !signature.contains('/') && len >= 3 && len <= 10
}

fn dump_log(log_path: &Option<PathBuf>, msg: &str) {
    let timestamp = current_timestamp();
    let line = format!("[{}] {}\r\n", timestamp, msg);

    #[cfg(windows)]
    {
        use windows_sys::Win32::System::Diagnostics::Debug::OutputDebugStringW;
        let wide: Vec<u16> = line.encode_utf16().chain(std::iter::once(0)).collect();
        unsafe { OutputDebugStringW(wide.as_ptr()) };
    }
    #[cfg(not(windows))]
    {
        eprint!("{}", line);
    }

    if let Some(path) = log_path {
        let _guard = DUMP_MUTEX.lock();
        if let Ok(mut file) = OpenOptions::new().create(true).append(true).open(path) {
            let _ = file.write_all(line.as_bytes());
        }
    }
}

fn signature_file_part(sig: &str) -> String {
    let mut out = String::new();
    for ch in sig.chars() {
        if ch == 'L' || ch == ';' {
            continue;
        }
        if ch == '/' || ch == '\\' || ch == ':' {
            out.push('_');
        } else {
            out.push(ch);
        }
    }
    out
}

unsafe extern "system" fn dump_class_file_load_hook(
    _jvmti_env: *mut jvmtiEnv,
    jni_env: *mut JNIEnv,
    class_being_redefined: jclass,
    _loader: jobject,
    name: *const i8,
    _protection_domain: jobject,
    class_data_len: jint,
    class_data: *const u8,
    new_class_data_len: *mut jint,
    new_class_data: *mut *mut u8,
) {
    if !new_class_data_len.is_null() {
        *new_class_data_len = 0;
    }
    if !new_class_data.is_null() {
        *new_class_data = ptr::null_mut();
    }

    if jni_env.is_null() || class_being_redefined.is_null() || class_data.is_null() || class_data_len <= 0 {
        return;
    }

    let jvmti = DUMP_JVMTI.load(Ordering::SeqCst);
    if jvmti.is_null() {
        return;
    }

    let mut guard = ACTIVE_TARGET.lock().unwrap();
    if let Some(ref mut active) = *guard {
        if ((*(*jni_env)).IsSameObject)(jni_env, class_being_redefined, active.target) == JNI_TRUE {
            let mut sig_ptr: *mut i8 = ptr::null_mut();
            if ((*(*jvmti)).GetClassSignature)(jvmti, class_being_redefined, &mut sig_ptr, ptr::null_mut())
                == JVMTI_ERROR_NONE
                && !sig_ptr.is_null()
            {
                let sig = CStr::from_ptr(sig_ptr).to_string_lossy().to_string();
                ((*(*jvmti)).Deallocate)(jvmti, sig_ptr as *mut u8);

                active.hook_serial += 1;
                let class_part = signature_file_part(&sig);
                let filename = format!(
                    "{:06}-hook-{:03}-{}.class",
                    active.serial, active.hook_serial, class_part
                );
                let out_file = active.dir.join(&filename);

                let slice = std::slice::from_raw_parts(class_data, class_data_len as usize);
                if let Ok(mut f) = File::create(&out_file) {
                    let _ = f.write_all(slice);
                }

                let cb_name = if name.is_null() {
                    "<null>".to_string()
                } else {
                    CStr::from_ptr(name).to_string_lossy().to_string()
                };

                dump_log(
                    &Some(active.log_path.clone()),
                    &format!(
                        "class={} hook={} callbackName={} bytes={} file={}",
                        sig,
                        active.hook_serial,
                        cb_name,
                        class_data_len,
                        out_file.display()
                    ),
                );
            }
        }
    }
}

type GetCreatedJavaVMsFn = unsafe extern "system" fn(
    vmBuf: *mut *mut JavaVM,
    bufLen: jsize,
    nVMs: *mut jsize,
) -> jint;

unsafe fn get_vm_and_jvmti(log_path: &Option<PathBuf>) -> bool {
    let mut vm: *mut JavaVM = ptr::null_mut();

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
            dump_log(log_path, "jvm.dll was not found");
            return false;
        }

        let sym = CString::new("JNI_GetCreatedJavaVMs").unwrap();
        let proc = GetProcAddress(jvm_module, sym.as_ptr() as *const u8);
        if proc.is_none() {
            dump_log(log_path, "JNI_GetCreatedJavaVMs is unavailable");
            return false;
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
        dump_log(log_path, "JNI_GetCreatedJavaVMs returned no VM");
        return false;
    }

    DUMP_VM.store(vm, Ordering::SeqCst);

    let mut env_ptr: *mut c_void = ptr::null_mut();
    let attach_res = ((*(*vm)).AttachCurrentThreadAsDaemon)(vm, &mut env_ptr, ptr::null_mut());
    if attach_res != JNI_OK || env_ptr.is_null() {
        dump_log(log_path, "AttachCurrentThreadAsDaemon failed");
        return false;
    }

    let mut jvmti_ptr: *mut c_void = ptr::null_mut();
    if ((*(*vm)).GetEnv)(vm, &mut jvmti_ptr, JVMTI_VERSION_1_2) != JNI_OK || jvmti_ptr.is_null() {
        dump_log(log_path, "JVMTI 1.2 is unavailable");
        return false;
    }

    let jvmti = jvmti_ptr as *mut jvmtiEnv;
    DUMP_JVMTI.store(jvmti, Ordering::SeqCst);

    let mut potential = jvmtiCapabilities::new();
    let mut requested = jvmtiCapabilities::new();

    let cap_err = ((*(*jvmti)).GetPotentialCapabilities)(jvmti, &mut potential);
    if cap_err != JVMTI_ERROR_NONE {
        dump_log(log_path, &format!("GetPotentialCapabilities failed: {}", cap_err));
        return false;
    }

    requested.set_can_retransform_classes(potential.can_retransform_classes());
    requested.set_can_retransform_any_class(potential.can_retransform_any_class());
    requested.set_can_get_source_file_name(potential.can_get_source_file_name());

    let add_err = ((*(*jvmti)).AddCapabilities)(jvmti, &requested);
    if add_err != JVMTI_ERROR_NONE {
        dump_log(log_path, &format!("AddCapabilities failed: {}", add_err));
        return false;
    }

    let mut callbacks: jvmtiEventCallbacks = std::mem::zeroed();
    callbacks.ClassFileLoadHook = Some(dump_class_file_load_hook);

    let set_cb_err = ((*(*jvmti)).SetEventCallbacks)(
        jvmti,
        &callbacks,
        std::mem::size_of::<jvmtiEventCallbacks>() as jint,
    );
    if set_cb_err != JVMTI_ERROR_NONE {
        dump_log(log_path, &format!("SetEventCallbacks failed: {}", set_cb_err));
        return false;
    }

    let notif_err = ((*(*jvmti)).SetEventNotificationMode)(
        jvmti,
        JVMTI_ENABLE,
        JVMTI_EVENT_CLASS_FILE_LOAD_HOOK,
        ptr::null_mut(),
    );
    if notif_err != JVMTI_ERROR_NONE {
        dump_log(log_path, &format!("ClassFileLoadHook enable failed: {}", notif_err));
        return false;
    }

    true
}

unsafe fn dump_loaded_classes(env: *mut JNIEnv, dump_dir: &PathBuf, log_path: &PathBuf) {
    let jvmti = DUMP_JVMTI.load(Ordering::SeqCst);
    if jvmti.is_null() {
        return;
    }

    let mut class_count: jint = 0;
    let mut classes_ptr: *mut jclass = ptr::null_mut();

    let err = ((*(*jvmti)).GetLoadedClasses)(jvmti, &mut class_count, &mut classes_ptr);
    if err != JVMTI_ERROR_NONE || classes_ptr.is_null() {
        dump_log(&Some(log_path.clone()), &format!("GetLoadedClasses failed: {}", err));
        return;
    }

    dump_log(&Some(log_path.clone()), &format!("loaded class count={}", class_count));
    let classes = std::slice::from_raw_parts(classes_ptr, class_count as usize);
    let mut selected_count = 0;

    for &target in classes {
        let mut sig_ptr: *mut i8 = ptr::null_mut();
        if ((*(*jvmti)).GetClassSignature)(jvmti, target, &mut sig_ptr, ptr::null_mut())
            != JVMTI_ERROR_NONE
            || sig_ptr.is_null()
        {
            continue;
        }

        let sig = CStr::from_ptr(sig_ptr).to_string_lossy().to_string();
        ((*(*jvmti)).Deallocate)(jvmti, sig_ptr as *mut u8);

        if !dump_filter(&sig) {
            continue;
        }

        let mut status: jint = 0;
        if ((*(*jvmti)).GetClassStatus)(jvmti, target, &mut status) != JVMTI_ERROR_NONE {
            continue;
        }

        selected_count += 1;
        let global_target = ((*(*env)).NewGlobalRef)(env, target);
        if global_target.is_null() {
            continue;
        }

        {
            let mut guard = ACTIVE_TARGET.lock().unwrap();
            *guard = Some(ActiveTarget {
                target: global_target,
                serial: selected_count,
                hook_serial: 0,
                dir: dump_dir.clone(),
                log_path: log_path.clone(),
            });
        }

        dump_log(
            &Some(log_path.clone()),
            &format!("retransform begin class={} status=0x{:x}", sig, status),
        );

        let retrans_err = ((*(*jvmti)).RetransformClasses)(jvmti, 1, &target);
        if retrans_err != JVMTI_ERROR_NONE {
            dump_log(
                &Some(log_path.clone()),
                &format!("retransform failed class={} jvmti={}", sig, retrans_err),
            );
        }

        let hooks = {
            let guard = ACTIVE_TARGET.lock().unwrap();
            guard.as_ref().map(|a| a.hook_serial).unwrap_or(0)
        };

        dump_log(
            &Some(log_path.clone()),
            &format!("retransform end class={} hooks={}", sig, hooks),
        );

        ((*(*env)).DeleteGlobalRef)(env, global_target);
        {
            let mut guard = ACTIVE_TARGET.lock().unwrap();
            *guard = None;
        }
    }

    ((*(*jvmti)).Deallocate)(jvmti, classes_ptr as *mut u8);
    dump_log(&Some(log_path.clone()), &format!("selected classes={}", selected_count));
}

pub unsafe fn run_dump_worker() {
    let pid = std::process::id();
    let temp_dir = std::env::temp_dir().join("Vape421Recovery");
    let dump_dir = temp_dir.join(format!("lunar-{}-dump", pid));
    let _ = create_dir_all(&dump_dir);
    let log_path = dump_dir.join("dump.log");

    dump_log(&Some(log_path.clone()), &format!("Lunar/OptiFine JVMTI dump started for PID {}", pid));

    if !get_vm_and_jvmti(&Some(log_path.clone())) {
        return;
    }

    let vm = DUMP_VM.load(Ordering::SeqCst);
    let mut env_ptr: *mut c_void = ptr::null_mut();

    if ((*(*vm)).GetEnv)(vm, &mut env_ptr, JNI_VERSION_1_8) != JNI_OK || env_ptr.is_null() {
        dump_log(&Some(log_path.clone()), "unable to resolve attached JNIEnv");
        return;
    }

    let env = env_ptr as *mut JNIEnv;
    dump_loaded_classes(env, &dump_dir, &log_path);
    dump_log(&Some(log_path.clone()), &format!("dump complete; files are in {}", dump_dir.display()));

    ((*(*vm)).DetachCurrentThread)(vm);
}
