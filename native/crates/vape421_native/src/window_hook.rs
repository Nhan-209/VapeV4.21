//! Window message hooks and subclassing for input routing to NativeBridge.om / wh.

use core::ffi::c_void;
use std::ptr;
use std::sync::atomic::{AtomicBool, AtomicPtr, Ordering};
use vape421_core::jni_sys::*;
use vape421_core::logger::{vape_log, vape_log_pending_exception};
use crate::jvmti_bridge::G_VM;

pub static G_BRIDGE_CLASS: AtomicPtr<c_void> = AtomicPtr::new(ptr::null_mut());
pub static G_BRIDGE_OM: AtomicPtr<_jmethodID> = AtomicPtr::new(ptr::null_mut());
pub static G_BRIDGE_WH: AtomicPtr<_jmethodID> = AtomicPtr::new(ptr::null_mut());

pub static G_WINDOWS_DISPLAY_REGISTERED: AtomicBool = AtomicBool::new(false);
pub static G_LWJGL3_WINDOW_REGISTERED: AtomicBool = AtomicBool::new(false);

#[cfg(windows)]
pub static G_LWJGL3_WINDOW: AtomicPtr<c_void> = AtomicPtr::new(ptr::null_mut());
#[cfg(windows)]
pub static G_LWJGL3_ORIGINAL_WNDPROC: AtomicPtr<c_void> = AtomicPtr::new(ptr::null_mut());

pub unsafe extern "system" fn windows_display_update(env: *mut JNIEnv, _owner: jclass) {
    if env.is_null() || ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
        return;
    }

    #[cfg(windows)]
    {
        use windows_sys::Win32::UI::WindowsAndMessaging::{
            DispatchMessageW, PeekMessageW, TranslateMessage, MSG, PM_REMOVE, WM_QUIT,
        };

        let bridge_class = G_BRIDGE_CLASS.load(Ordering::SeqCst) as jclass;
        let bridge_om = G_BRIDGE_OM.load(Ordering::SeqCst);

        let mut msg: MSG = std::mem::zeroed();
        while PeekMessageW(&mut msg, ptr::null_mut(), 0, 0, PM_REMOVE) != 0 {
            if msg.message == WM_QUIT {
                return;
            }

            if bridge_class.is_null() || bridge_om.is_null() {
                TranslateMessage(&msg);
                DispatchMessageW(&msg);
                continue;
            }

            let handled = ((*(*env)).CallStaticBooleanMethod)(
                env,
                bridge_class,
                bridge_om,
                msg.message as jint,
                msg.wParam as jlong,
                msg.lParam as jlong,
            );

            TranslateMessage(&msg);
            if ((*(*env)).ExceptionCheck)(env) == JNI_TRUE || handled == JNI_TRUE {
                return;
            }

            DispatchMessageW(&msg);
            if ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
                return;
            }
        }
    }
}

#[cfg(windows)]
unsafe fn get_callback_env(attached: &mut bool) -> *mut JNIEnv {
    *attached = false;
    let vm = G_VM.load(Ordering::SeqCst);
    if vm.is_null() {
        return ptr::null_mut();
    }

    let mut env_ptr: *mut c_void = ptr::null_mut();
    let res = ((*(*vm)).GetEnv)(vm, &mut env_ptr, JNI_VERSION_1_6);

    if res == JNI_EDETACHED {
        if ((*(*vm)).AttachCurrentThread)(vm, &mut env_ptr, ptr::null_mut()) == JNI_OK {
            *attached = true;
            env_ptr as *mut JNIEnv
        } else {
            ptr::null_mut()
        }
    } else if res == JNI_OK {
        env_ptr as *mut JNIEnv
    } else {
        ptr::null_mut()
    }
}

#[cfg(windows)]
pub unsafe extern "system" fn lwjgl3_window_proc(
    window: windows_sys::Win32::Foundation::HWND,
    message: u32,
    wparam: windows_sys::Win32::Foundation::WPARAM,
    lparam: windows_sys::Win32::Foundation::LPARAM,
) -> windows_sys::Win32::Foundation::LRESULT {
    use windows_sys::Win32::UI::WindowsAndMessaging::{CallWindowProcW, DefWindowProcW, WNDPROC};

    let original = G_LWJGL3_ORIGINAL_WNDPROC.load(Ordering::SeqCst);
    let mut attached = false;
    let env = get_callback_env(&mut attached);
    let mut handled = JNI_FALSE;

    let bridge_class = G_BRIDGE_CLASS.load(Ordering::SeqCst) as jclass;
    let bridge_om = G_BRIDGE_OM.load(Ordering::SeqCst);

    if !env.is_null() && !bridge_class.is_null() && !bridge_om.is_null()
        && ((*(*env)).ExceptionCheck)(env) == JNI_FALSE
    {
        handled = ((*(*env)).CallStaticBooleanMethod)(
            env,
            bridge_class,
            bridge_om,
            message as jint,
            wparam as jlong,
            lparam as jlong,
        );

        if ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
            vape_log_pending_exception(env, "LWJGL3 window input callback");
            handled = JNI_FALSE;
        }
    }

    if attached {
        let vm = G_VM.load(Ordering::SeqCst);
        if !vm.is_null() {
            ((*(*vm)).DetachCurrentThread)(vm);
        }
    }

    if handled == JNI_TRUE {
        return 0;
    }

    if original.is_null() {
        DefWindowProcW(window, message, wparam, lparam)
    } else {
        let orig_proc: WNDPROC = std::mem::transmute(original);
        CallWindowProcW(orig_proc, window, message, wparam, lparam)
    }
}

#[cfg(windows)]
unsafe fn is_lwjgl3_window(window: windows_sys::Win32::Foundation::HWND) -> bool {
    use windows_sys::Win32::System::Threading::GetCurrentProcessId;
    use windows_sys::Win32::UI::WindowsAndMessaging::{GetClassNameW, GetWindowThreadProcessId, IsWindow};

    if window.is_null() || IsWindow(window) == 0 {
        return false;
    }

    let mut pid: u32 = 0;
    GetWindowThreadProcessId(window, &mut pid);
    if pid != GetCurrentProcessId() {
        return false;
    }

    let mut class_name = [0u16; 64];
    let len = GetClassNameW(window, class_name.as_mut_ptr(), 64);
    if len <= 0 {
        return false;
    }

    let name = String::from_utf16_lossy(&class_name[..len as usize]);
    name == "GLFW30" || name == "LWJGL3"
}

#[cfg(windows)]
unsafe extern "system" fn find_lwjgl3_window_enum(
    window: windows_sys::Win32::Foundation::HWND,
    lparam: windows_sys::Win32::Foundation::LPARAM,
) -> windows_sys::Win32::Foundation::BOOL {
    let result_ptr = lparam as *mut windows_sys::Win32::Foundation::HWND;
    if !result_ptr.is_null() && is_lwjgl3_window(window) {
        *result_ptr = window;
        0
    } else {
        1
    }
}

pub unsafe fn register_lwjgl3_window(env: *mut JNIEnv) {
    #[cfg(windows)]
    {
        use windows_sys::Win32::Foundation::{GetLastError, SetLastError, ERROR_SUCCESS};
        use windows_sys::Win32::UI::WindowsAndMessaging::{
            EnumWindows, GetForegroundWindow, SetWindowLongPtrW, GWLP_WNDPROC,
        };

        if G_LWJGL3_WINDOW_REGISTERED.load(Ordering::SeqCst) {
            return;
        }

        let mut window = GetForegroundWindow();
        if !is_lwjgl3_window(window) {
            window = ptr::null_mut();
            EnumWindows(
                Some(find_lwjgl3_window_enum),
                &mut window as *mut _ as windows_sys::Win32::Foundation::LPARAM,
            );
        }

        if window.is_null() {
            vape_log("trs step 23: LWJGL3 window was not found");
            return;
        }

        SetLastError(ERROR_SUCCESS);
        let orig = SetWindowLongPtrW(
            window,
            GWLP_WNDPROC,
            lwjgl3_window_proc as usize as isize,
        );

        if orig == 0 && GetLastError() != ERROR_SUCCESS {
            vape_log(&format!("failed to subclass LWJGL3 window: {}", GetLastError()));
            return;
        }

        G_LWJGL3_WINDOW.store(window as *mut c_void, Ordering::SeqCst);
        G_LWJGL3_ORIGINAL_WNDPROC.store(orig as *mut c_void, Ordering::SeqCst);
        G_LWJGL3_WINDOW_REGISTERED.store(true, Ordering::SeqCst);

        let bridge_class = G_BRIDGE_CLASS.load(Ordering::SeqCst) as jclass;
        let bridge_wh = G_BRIDGE_WH.load(Ordering::SeqCst);
        if !env.is_null() && !bridge_class.is_null() && !bridge_wh.is_null() {
            ((*(*env)).CallStaticVoidMethod)(env, bridge_class, bridge_wh, window as usize as jlong);
            if ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
                vape_log_pending_exception(env, "initialize LWJGL3 window handle");
            }
        }

        vape_log("subclassed LWJGL3 window for input notifications");
    }
    #[cfg(not(windows))]
    {
        let _ = env;
    }
}

pub unsafe fn release_window_hook() {
    #[cfg(windows)]
    {
        use windows_sys::Win32::UI::WindowsAndMessaging::{IsWindow, SetWindowLongPtrW, GWLP_WNDPROC};
        let window = G_LWJGL3_WINDOW.load(Ordering::SeqCst);
        let orig = G_LWJGL3_ORIGINAL_WNDPROC.load(Ordering::SeqCst);

        if !window.is_null() && !orig.is_null() && IsWindow(window as _) != 0 {
            SetWindowLongPtrW(window as _, GWLP_WNDPROC, orig as usize as isize);
        }

        G_LWJGL3_WINDOW.store(ptr::null_mut(), Ordering::SeqCst);
        G_LWJGL3_ORIGINAL_WNDPROC.store(ptr::null_mut(), Ordering::SeqCst);
    }
    G_WINDOWS_DISPLAY_REGISTERED.store(false, Ordering::SeqCst);
    G_LWJGL3_WINDOW_REGISTERED.store(false, Ordering::SeqCst);
}
