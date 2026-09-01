//! Standalone JVMTI diagnostic collector for inspecting client updates.

#![allow(non_snake_case)]

pub mod dumper;

use core::ffi::c_void;
use crate::dumper::run_dump_worker;

#[cfg(windows)]
#[no_mangle]
pub unsafe extern "system" fn DllMain(
    instance: windows_sys::Win32::Foundation::HINSTANCE,
    reason: u32,
    _reserved: *mut c_void,
) -> windows_sys::Win32::Foundation::BOOL {
    use windows_sys::Win32::System::LibraryLoader::DisableThreadLibraryCalls;
    use windows_sys::Win32::System::SystemServices::DLL_PROCESS_ATTACH;

    if reason == DLL_PROCESS_ATTACH {
        DisableThreadLibraryCalls(instance);
        std::thread::spawn(move || {
            run_dump_worker();
        });
    }

    1
}
