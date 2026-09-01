//! Vape 4.21 Native Test Bridge Library.
//! Reconstructed JNI/JVMTI bridge with automated bootstrap and multi-version client support.

#![allow(non_snake_case, non_camel_case_types)]

pub mod bootstrap_worker;
pub mod classloader;
pub mod jvmti_bridge;
pub mod native_methods;
pub mod reflection;
pub mod window_hook;

use core::ffi::c_void;
use std::ffi::CString;
use std::ptr;
use std::sync::atomic::{AtomicBool, Ordering};
use vape421_core::bootstrap::{vape_loader_bootstrap_clear, vape_loader_bootstrap_initialize};
use vape421_core::jni_sys::*;
use vape421_core::logger::{set_log_directory, vape_log, vape_log_pending_exception};

use crate::bootstrap_worker::run_bootstrap_worker;
use crate::jvmti_bridge::{vape_initialize_jvmti, G_MODULE};
use crate::native_methods::{vape_register_native_bridge, vape_release_native_bridge};

static LOADED_BY_JNI: AtomicBool = AtomicBool::new(false);

#[no_mangle]
pub unsafe extern "system" fn JNI_OnLoad(vm: *mut JavaVM, _reserved: *mut c_void) -> jint {
    LOADED_BY_JNI.store(true, Ordering::SeqCst);

    if !vape_loader_bootstrap_initialize() {
        return JNI_ERR;
    }

    let mut env_ptr: *mut c_void = ptr::null_mut();
    if ((*(*vm)).GetEnv)(vm, &mut env_ptr, JNI_VERSION_1_8) != JNI_OK || env_ptr.is_null() {
        return JNI_ERR;
    }

    let env = env_ptr as *mut JNIEnv;

    if vape_initialize_jvmti(vm) != JNI_OK {
        return JNI_ERR;
    }

    let bridge_name = CString::new("gg/vape/runtime/NativeBridge").unwrap();
    let bridge_class = ((*(*env)).FindClass)(env, bridge_name.as_ptr());

    if bridge_class.is_null() || vape_register_native_bridge(env, bridge_class) != JNI_OK {
        vape_log_pending_exception(env, "JNI_OnLoad NativeBridge registration");
        return JNI_ERR;
    }

    vape_log("JNI_OnLoad completed");
    JNI_VERSION_1_8
}

#[no_mangle]
pub unsafe extern "system" fn JNI_OnUnload(vm: *mut JavaVM, _reserved: *mut c_void) {
    let mut env_ptr: *mut c_void = ptr::null_mut();
    if ((*(*vm)).GetEnv)(vm, &mut env_ptr, JNI_VERSION_1_8) == JNI_OK && !env_ptr.is_null() {
        let env = env_ptr as *mut JNIEnv;
        vape_release_native_bridge(env);
    }
    vape_loader_bootstrap_clear();
}

#[cfg(windows)]
#[no_mangle]
pub unsafe extern "system" fn DllMain(
    instance: windows_sys::Win32::Foundation::HINSTANCE,
    reason: u32,
    _reserved: *mut c_void,
) -> windows_sys::Win32::Foundation::BOOL {
    use windows_sys::Win32::System::LibraryLoader::{DisableThreadLibraryCalls, GetModuleFileNameW};
    use windows_sys::Win32::System::SystemServices::{DLL_PROCESS_ATTACH, DLL_PROCESS_DETACH};

    if reason == DLL_PROCESS_ATTACH {
        G_MODULE.store(instance as *mut c_void, Ordering::SeqCst);
        DisableThreadLibraryCalls(instance);

        let mut buffer = [0u16; 1024];
        let len = GetModuleFileNameW(instance, buffer.as_mut_ptr(), 1024);
        if len > 0 {
            let path_str = String::from_utf16_lossy(&buffer[..len as usize]);
            let path = std::path::Path::new(&path_str);
            if let Some(parent) = path.parent() {
                set_log_directory(parent);
            }
        }

        std::thread::spawn(move || {
            run_bootstrap_worker();
        });
    } else if reason == DLL_PROCESS_DETACH {
        vape_loader_bootstrap_clear();
    }

    1
}
