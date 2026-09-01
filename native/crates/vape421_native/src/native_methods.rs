//! Implementations of all 9 authoritative JNI native methods, gat, cpy, and compatibility stubs.

use std::ffi::{CStr, CString};
use std::ptr;
use std::sync::atomic::Ordering;
use vape421_core::bootstrap::{
    vape_loader_access_token, vape_loader_bootstrap_failed, vape_loader_bootstrap_initialize,
    vape_loader_report_progress,
};
use vape421_core::jni_sys::*;
use vape421_core::logger::{vape_log, vape_log_pending_exception};
use crate::jvmti_bridge::{
    capture_for_retransform, clear_persisted_classes, clear_redefinition_active,
    log_jvmti_failure, set_redefinition_active, update_persisted_class, G_JVMTI,
    RETAIN_CLASS_TRANSFORMS,
};
use crate::reflection::{invoke_reflected_method, throw_new};
use crate::window_hook::{
    register_lwjgl3_window, release_window_hook, windows_display_update, G_BRIDGE_CLASS,
    G_BRIDGE_OM, G_BRIDGE_WH, G_WINDOWS_DISPLAY_REGISTERED,
};

#[cfg(windows)]
use crate::window_hook::G_LWJGL3_WINDOW;

pub unsafe extern "system" fn native_scb(
    env: *mut JNIEnv,
    _bridge: jclass,
    target: jclass,
    class_bytes: jbyteArray,
) -> jint {
    let jvmti = G_JVMTI.load(Ordering::SeqCst);
    if jvmti.is_null() || target.is_null() || class_bytes.is_null() {
        return JVMTI_ERROR_INVALID_ENVIRONMENT;
    }

    let length = ((*(*env)).GetArrayLength)(env, class_bytes);
    let mut is_copy: jboolean = 0;
    let bytes_ptr = ((*(*env)).GetByteArrayElements)(env, class_bytes, &mut is_copy);
    if bytes_ptr.is_null() {
        return JVMTI_ERROR_OUT_OF_MEMORY;
    }

    let raw_bytes = std::slice::from_raw_parts(bytes_ptr as *const u8, length as usize);
    let retain_transforms = RETAIN_CLASS_TRANSFORMS.load(Ordering::SeqCst);

    if retain_transforms {
        set_redefinition_active(target, raw_bytes);
    }

    let definition = jvmtiClassDefinition {
        klass: target,
        class_byte_count: length,
        class_bytes: bytes_ptr as *const u8,
    };

    let error = ((*(*jvmti)).RedefineClasses)(jvmti, 1, &definition);

    if retain_transforms {
        clear_redefinition_active();
        if error == JVMTI_ERROR_NONE {
            update_persisted_class(env, target, raw_bytes);
        }
    }

    ((*(*env)).ReleaseByteArrayElements)(env, class_bytes, bytes_ptr, JNI_ABORT);
    log_jvmti_failure("scb RedefineClasses", error, target);
    error
}

pub unsafe extern "system" fn native_smd(
    _env: *mut JNIEnv,
    _bridge: jclass,
    button_mask: jint,
    message: jint,
) {
    #[cfg(windows)]
    {
        use windows_sys::Win32::Foundation::{BOOL, HWND, POINT};
        use windows_sys::Win32::UI::WindowsAndMessaging::{
            GetClassNameW, GetForegroundWindow, IsWindow, PostMessageW,
            WM_LBUTTONDOWN, WM_MBUTTONDOWN, WM_RBUTTONDOWN,
        };

        extern "system" {
            fn GetCursorPos(lpPoint: *mut POINT) -> BOOL;
            fn ScreenToClient(hWnd: HWND, lpPoint: *mut POINT) -> BOOL;
        }

        let mut pt = POINT { x: 0, y: 0 };
        let mut window = G_LWJGL3_WINDOW.load(Ordering::SeqCst) as HWND;
        if window.is_null() || IsWindow(window) == 0 {
            window = GetForegroundWindow();
        }

        if window.is_null() {
            return;
        }

        let mut class_name = [0u16; 256];
        let len = GetClassNameW(window, class_name.as_mut_ptr(), 256);
        if len <= 0 {
            return;
        }

        let name = String::from_utf16_lossy(&class_name[..len as usize]);
        if name != "LWJGL" && name != "LWJGL3" && name != "GLFW30" {
            return;
        }

        GetCursorPos(&mut pt);
        ScreenToClient(window, &mut pt);

        let msg_u32 = message as u32;
        let wparam = if msg_u32 == WM_LBUTTONDOWN || msg_u32 == WM_RBUTTONDOWN || msg_u32 == WM_MBUTTONDOWN {
            button_mask as usize
        } else {
            0
        };

        let lparam = ((pt.y as u32) << 16) | (pt.x as u32 & 0xFFFF);
        PostMessageW(window, msg_u32, wparam, lparam as isize);
    }
}

pub unsafe extern "system" fn native_gks(
    _env: *mut JNIEnv,
    _bridge: jclass,
    virtual_key: jint,
) -> jshort {
    #[cfg(windows)]
    {
        use windows_sys::Win32::UI::Input::KeyboardAndMouse::GetAsyncKeyState;
        let state = GetAsyncKeyState(virtual_key) as u16;
        ((state >> 7) & 0x100) as jshort
    }
    #[cfg(not(windows))]
    {
        let _ = virtual_key;
        0
    }
}

pub unsafe extern "system" fn native_gkn(
    env: *mut JNIEnv,
    _bridge: jclass,
    key_data: jlong,
) -> jstring {
    #[cfg(windows)]
    {
        use windows_sys::Win32::UI::Input::KeyboardAndMouse::GetKeyNameTextA;
        let mut buffer = [0i8; 1024];
        GetKeyNameTextA(key_data as i32, buffer.as_mut_ptr(), 1024);
        ((*(*env)).NewStringUTF)(env, buffer.as_ptr())
    }
    #[cfg(not(windows))]
    {
        let _ = (env, key_data);
        ptr::null_mut()
    }
}

pub unsafe extern "system" fn native_mvk(
    _env: *mut JNIEnv,
    _bridge: jclass,
    code: jint,
    map_type: jint,
) -> jint {
    #[cfg(windows)]
    {
        use windows_sys::Win32::UI::Input::KeyboardAndMouse::MapVirtualKeyA;
        MapVirtualKeyA(code as u32, map_type as u32) as jint
    }
    #[cfg(not(windows))]
    {
        let _ = (code, map_type);
        0
    }
}

pub unsafe extern "system" fn native_cpy(
    env: *mut JNIEnv,
    _bridge: jclass,
    text: jstring,
) {
    if text.is_null() {
        return;
    }

    #[cfg(windows)]
    {
        use windows_sys::Win32::Foundation::{GetLastError, HGLOBAL};
        use windows_sys::Win32::System::DataExchange::{
            CloseClipboard, EmptyClipboard, OpenClipboard, SetClipboardData,
        };
        use windows_sys::Win32::System::Memory::{
            GlobalAlloc, GlobalLock, GlobalUnlock, GMEM_MOVEABLE,
        };

        const CF_TEXT: u32 = 1;

        extern "system" {
            fn GlobalFree(hMem: HGLOBAL) -> HGLOBAL;
        }

        let mut is_copy: jboolean = 0;
        let chars = ((*(*env)).GetStringUTFChars)(env, text, &mut is_copy);
        if chars.is_null() {
            return;
        }

        let slice = CStr::from_ptr(chars).to_bytes_with_nul();
        let byte_count = slice.len();

        let memory = GlobalAlloc(GMEM_MOVEABLE, byte_count);
        if memory.is_null() {
            vape_log(&format!("cpy GlobalAlloc failed: {}", GetLastError()));
            ((*(*env)).ReleaseStringUTFChars)(env, text, chars);
            return;
        }

        let destination = GlobalLock(memory);
        if destination.is_null() {
            vape_log(&format!("cpy GlobalLock failed: {}", GetLastError()));
            GlobalFree(memory);
            ((*(*env)).ReleaseStringUTFChars)(env, text, chars);
            return;
        }

        ptr::copy_nonoverlapping(slice.as_ptr(), destination as *mut u8, byte_count);
        GlobalUnlock(memory);

        if OpenClipboard(ptr::null_mut()) != 0 {
            if EmptyClipboard() != 0 {
                if SetClipboardData(CF_TEXT, memory as _).is_null() {
                    vape_log(&format!("cpy SetClipboardData failed: {}", GetLastError()));
                    GlobalFree(memory);
                }
            } else {
                vape_log(&format!("cpy EmptyClipboard failed: {}", GetLastError()));
                GlobalFree(memory);
            }
            CloseClipboard();
        } else {
            vape_log(&format!("cpy OpenClipboard failed: {}", GetLastError()));
            GlobalFree(memory);
        }

        ((*(*env)).ReleaseStringUTFChars)(env, text, chars);
    }
    #[cfg(not(windows))]
    {
        let _ = (env, text);
    }
}

pub unsafe extern "system" fn native_gcb(
    env: *mut JNIEnv,
    _bridge: jclass,
    target: jclass,
) -> jbyteArray {
    if let Some(bytes) = capture_for_retransform(env, target) {
        let arr = ((*(*env)).NewByteArray)(env, bytes.len() as jsize);
        if !arr.is_null() {
            ((*(*env)).SetByteArrayRegion)(
                env,
                arr,
                0,
                bytes.len() as jsize,
                bytes.as_ptr() as *const jbyte,
            );
        }
        arr
    } else {
        ptr::null_mut()
    }
}

pub unsafe extern "system" fn native_gfb(
    env: *mut JNIEnv,
    bridge: jclass,
    resource_path: jstring,
) -> jbyteArray {
    if resource_path.is_null() {
        return ptr::null_mut();
    }

    let mut is_copy: jboolean = 0;
    let path_chars = ((*(*env)).GetStringUTFChars)(env, resource_path, &mut is_copy);
    if path_chars.is_null() {
        return ptr::null_mut();
    }

    let path_slice = CStr::from_ptr(path_chars).to_bytes();
    let normalized = if path_slice.starts_with(b"/") {
        &path_slice[1..]
    } else {
        path_slice
    };

    let c_norm = CString::new(normalized).unwrap();
    let normalized_path = ((*(*env)).NewStringUTF)(env, c_norm.as_ptr());
    ((*(*env)).ReleaseStringUTFChars)(env, resource_path, path_chars);

    if normalized_path.is_null() {
        return ptr::null_mut();
    }

    let class_name = CString::new("java/lang/Class").unwrap();
    let loader_name = CString::new("java/lang/ClassLoader").unwrap();
    let stream_name = CString::new("java/io/InputStream").unwrap();

    let class_class = ((*(*env)).FindClass)(env, class_name.as_ptr());
    let loader_class = ((*(*env)).FindClass)(env, loader_name.as_ptr());
    let stream_class = ((*(*env)).FindClass)(env, stream_name.as_ptr());

    if class_class.is_null() || loader_class.is_null() || stream_class.is_null() {
        return ptr::null_mut();
    }

    let get_loader_sig = CString::new("()Ljava/lang/ClassLoader;").unwrap();
    let get_stream_sig = CString::new("(Ljava/lang/String;)Ljava/io/InputStream;").unwrap();
    let read_sig = CString::new("([B)I").unwrap();
    let close_sig = CString::new("()V").unwrap();

    let get_loader = ((*(*env)).GetMethodID)(
        env,
        class_class,
        CString::new("getClassLoader").unwrap().as_ptr(),
        get_loader_sig.as_ptr(),
    );
    let get_stream = ((*(*env)).GetMethodID)(
        env,
        loader_class,
        CString::new("getResourceAsStream").unwrap().as_ptr(),
        get_stream_sig.as_ptr(),
    );
    let read_method = ((*(*env)).GetMethodID)(
        env,
        stream_class,
        CString::new("read").unwrap().as_ptr(),
        read_sig.as_ptr(),
    );
    let close_method = ((*(*env)).GetMethodID)(
        env,
        stream_class,
        CString::new("close").unwrap().as_ptr(),
        close_sig.as_ptr(),
    );

    if get_loader.is_null() || get_stream.is_null() || read_method.is_null() || close_method.is_null() {
        return ptr::null_mut();
    }

    let loader = ((*(*env)).CallObjectMethod)(env, bridge, get_loader);
    if loader.is_null() || ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
        return ptr::null_mut();
    }

    let stream = ((*(*env)).CallObjectMethod)(env, loader, get_stream, normalized_path);
    if stream.is_null() || ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
        return ptr::null_mut();
    }

    let buffer = ((*(*env)).NewByteArray)(env, 8192);
    if buffer.is_null() {
        ((*(*env)).CallVoidMethod)(env, stream, close_method);
        return ptr::null_mut();
    }

    let mut result_bytes = Vec::new();
    let mut temp = [0i8; 8192];

    loop {
        let count = ((*(*env)).CallIntMethod)(env, stream, read_method, buffer);
        if ((*(*env)).ExceptionCheck)(env) == JNI_TRUE || count <= 0 {
            break;
        }

        ((*(*env)).GetByteArrayRegion)(env, buffer, 0, count, temp.as_mut_ptr());
        if ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
            break;
        }

        result_bytes.extend_from_slice(&temp[..count as usize]);
    }

    ((*(*env)).CallVoidMethod)(env, stream, close_method);

    if ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
        return ptr::null_mut();
    }

    let result_array = ((*(*env)).NewByteArray)(env, result_bytes.len() as jsize);
    if !result_array.is_null() {
        ((*(*env)).SetByteArrayRegion)(
            env,
            result_array,
            0,
            result_bytes.len() as jsize,
            result_bytes.as_ptr() as *const jbyte,
        );
    }

    result_array
}

pub unsafe extern "system" fn native_trs(
    env: *mut JNIEnv,
    _bridge: jclass,
    step: jint,
) {
    vape_loader_report_progress(step);
    if step != 23 || G_WINDOWS_DISPLAY_REGISTERED.load(Ordering::SeqCst) {
        return;
    }

    let jvmti = G_JVMTI.load(Ordering::SeqCst);
    if jvmti.is_null() {
        return;
    }

    let mut class_count: jint = 0;
    let mut classes_ptr: *mut jclass = ptr::null_mut();

    let err = ((*(*jvmti)).GetLoadedClasses)(jvmti, &mut class_count, &mut classes_ptr);
    if err != JVMTI_ERROR_NONE || classes_ptr.is_null() {
        vape_log(&format!("trs GetLoadedClasses failed: {}", err));
        return;
    }

    let classes = std::slice::from_raw_parts(classes_ptr, class_count as usize);
    let mut windows_display: jclass = ptr::null_mut();

    for &cls in classes {
        let mut sig_ptr: *mut i8 = ptr::null_mut();
        if ((*(*jvmti)).GetClassSignature)(jvmti, cls, &mut sig_ptr, ptr::null_mut())
            == JVMTI_ERROR_NONE
            && !sig_ptr.is_null()
        {
            let sig = CStr::from_ptr(sig_ptr).to_bytes();
            if sig == b"Lorg/lwjgl/opengl/WindowsDisplay;" {
                windows_display = cls;
            }
            ((*(*jvmti)).Deallocate)(jvmti, sig_ptr as *mut u8);
        }
        if !windows_display.is_null() {
            break;
        }
    }

    if !windows_display.is_null() {
        let method_name = CString::new("nUpdate").unwrap();
        let method_sig = CString::new("()V").unwrap();

        let update_method = JNINativeMethod {
            name: method_name.as_ptr(),
            signature: method_sig.as_ptr(),
            fnPtr: windows_display_update as *mut _,
        };

        if ((*(*env)).RegisterNatives)(env, windows_display, &update_method, 1) == JNI_OK {
            G_WINDOWS_DISPLAY_REGISTERED.store(true, Ordering::SeqCst);
            vape_log("registered org.lwjgl.opengl.WindowsDisplay.nUpdate");
        } else {
            vape_log_pending_exception(env, "RegisterNatives WindowsDisplay.nUpdate");
        }
    } else {
        vape_log("trs step 23: WindowsDisplay is not loaded; trying LWJGL3 window");
        register_lwjgl3_window(env);
    }

    ((*(*jvmti)).Deallocate)(jvmti, classes_ptr as *mut u8);
}

pub unsafe extern "system" fn native_inv(
    env: *mut JNIEnv,
    _bridge: jclass,
    reflected_method: jobject,
    receiver: jobject,
    arguments: jobjectArray,
) -> jobject {
    invoke_reflected_method(env, reflected_method, receiver, arguments)
}

pub unsafe extern "system" fn bridge_gat(env: *mut JNIEnv, _bridge: jclass) -> jstring {
    if !vape_loader_bootstrap_initialize() || vape_loader_bootstrap_failed() {
        throw_new(env, "java/lang/IllegalStateException", "Loader token bootstrap failed");
        return ptr::null_mut();
    }
    let token = vape_loader_access_token();
    let c_token = CString::new(token).unwrap_or_default();
    ((*(*env)).NewStringUTF)(env, c_token.as_ptr())
}

// ----------------------------------------------------------------------------
// Stubs
// ----------------------------------------------------------------------------

pub unsafe extern "system" fn native_sce(
    env: *mut JNIEnv,
    _bridge: jclass,
    message: jstring,
) {
    if message.is_null() {
        vape_log("client error report: <null>");
        return;
    }
    let mut is_copy: jboolean = 0;
    let chars = ((*(*env)).GetStringUTFChars)(env, message, &mut is_copy);
    if !chars.is_null() {
        let msg = CStr::from_ptr(chars).to_string_lossy();
        vape_log(&format!("client error report: {}", msg));
        ((*(*env)).ReleaseStringUTFChars)(env, message, chars);
    }
}

pub unsafe extern "system" fn native_ss(
    _env: *mut JNIEnv,
    _bridge: jclass,
    _value: jstring,
) {}

pub unsafe extern "system" fn native_ss_2(
    _env: *mut JNIEnv,
    _bridge: jclass,
    _value: jstring,
) -> jint {
    0
}

pub unsafe extern "system" fn native_mfv2(
    _env: *mut JNIEnv,
    _bridge: jclass,
    _first: jint,
    _second: jint,
    _value: jstring,
) -> jint {
    0
}

pub unsafe extern "system" fn native_dsv2(
    _env: *mut JNIEnv,
    _bridge: jclass,
    _index: jint,
    _value: jstring,
    _low: jdouble,
    _high: jdouble,
    _mode: jint,
    _step: jfloat,
) -> jint {
    0
}

pub unsafe fn vape_register_native_bridge(env: *mut JNIEnv, bridge_class: jclass) -> jint {
    if env.is_null() || bridge_class.is_null() {
        return JNI_ERR;
    }

    let m_scb = CString::new("scb").unwrap();
    let s_scb = CString::new("(Ljava/lang/Class;[B)I").unwrap();
    let m_smd = CString::new("smd").unwrap();
    let s_smd = CString::new("(II)V").unwrap();
    let m_gks = CString::new("gks").unwrap();
    let s_gks = CString::new("(I)S").unwrap();
    let m_gkn = CString::new("gkn").unwrap();
    let s_gkn = CString::new("(J)Ljava/lang/String;").unwrap();
    let m_mvk = CString::new("mvk").unwrap();
    let s_mvk = CString::new("(II)I").unwrap();
    let m_cpy = CString::new("cpy").unwrap();
    let s_cpy = CString::new("(Ljava/lang/String;)V").unwrap();
    let m_gcb = CString::new("gcb").unwrap();
    let s_gcb = CString::new("(Ljava/lang/Class;)[B").unwrap();
    let m_gfb = CString::new("gfb").unwrap();
    let s_gfb = CString::new("(Ljava/lang/String;)[B").unwrap();
    let m_trs = CString::new("trs").unwrap();
    let s_trs = CString::new("(I)V").unwrap();
    let m_inv = CString::new("inv").unwrap();
    let s_inv = CString::new("(Ljava/lang/reflect/Method;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;").unwrap();
    let m_gat = CString::new("gat").unwrap();
    let s_gat = CString::new("()Ljava/lang/String;").unwrap();
    let m_dsv2 = CString::new("dsv2").unwrap();
    let s_dsv2 = CString::new("(ILjava/lang/String;DDIF)I").unwrap();
    let m_ss_2 = CString::new("ss_2").unwrap();
    let s_ss_2 = CString::new("(Ljava/lang/String;)I").unwrap();
    let m_mfv2 = CString::new("mfv2").unwrap();
    let s_mfv2 = CString::new("(IILjava/lang/String;)I").unwrap();
    let m_ss = CString::new("ss").unwrap();
    let s_ss = CString::new("(Ljava/lang/String;)V").unwrap();
    let m_sce = CString::new("sce").unwrap();
    let s_sce = CString::new("(Ljava/lang/String;)V").unwrap();

    let methods = [
        JNINativeMethod { name: m_scb.as_ptr(), signature: s_scb.as_ptr(), fnPtr: native_scb as *mut _ },
        JNINativeMethod { name: m_smd.as_ptr(), signature: s_smd.as_ptr(), fnPtr: native_smd as *mut _ },
        JNINativeMethod { name: m_gks.as_ptr(), signature: s_gks.as_ptr(), fnPtr: native_gks as *mut _ },
        JNINativeMethod { name: m_gkn.as_ptr(), signature: s_gkn.as_ptr(), fnPtr: native_gkn as *mut _ },
        JNINativeMethod { name: m_mvk.as_ptr(), signature: s_mvk.as_ptr(), fnPtr: native_mvk as *mut _ },
        JNINativeMethod { name: m_cpy.as_ptr(), signature: s_cpy.as_ptr(), fnPtr: native_cpy as *mut _ },
        JNINativeMethod { name: m_gcb.as_ptr(), signature: s_gcb.as_ptr(), fnPtr: native_gcb as *mut _ },
        JNINativeMethod { name: m_gfb.as_ptr(), signature: s_gfb.as_ptr(), fnPtr: native_gfb as *mut _ },
        JNINativeMethod { name: m_trs.as_ptr(), signature: s_trs.as_ptr(), fnPtr: native_trs as *mut _ },
        JNINativeMethod { name: m_inv.as_ptr(), signature: s_inv.as_ptr(), fnPtr: native_inv as *mut _ },
        JNINativeMethod { name: m_gat.as_ptr(), signature: s_gat.as_ptr(), fnPtr: bridge_gat as *mut _ },
        JNINativeMethod { name: m_dsv2.as_ptr(), signature: s_dsv2.as_ptr(), fnPtr: native_dsv2 as *mut _ },
        JNINativeMethod { name: m_ss_2.as_ptr(), signature: s_ss_2.as_ptr(), fnPtr: native_ss_2 as *mut _ },
        JNINativeMethod { name: m_mfv2.as_ptr(), signature: s_mfv2.as_ptr(), fnPtr: native_mfv2 as *mut _ },
        JNINativeMethod { name: m_ss.as_ptr(), signature: s_ss.as_ptr(), fnPtr: native_ss as *mut _ },
        JNINativeMethod { name: m_sce.as_ptr(), signature: s_sce.as_ptr(), fnPtr: native_sce as *mut _ },
    ];

    let reg_res = ((*(*env)).RegisterNatives)(env, bridge_class, methods.as_ptr(), methods.len() as jint);
    if reg_res != JNI_OK {
        vape_log_pending_exception(env, "RegisterNatives NativeBridge");
        return reg_res;
    }

    let prev_class = G_BRIDGE_CLASS.swap(ptr::null_mut(), Ordering::SeqCst);
    if !prev_class.is_null() {
        ((*(*env)).DeleteGlobalRef)(env, prev_class as jobject);
    }

    let global_class = ((*(*env)).NewGlobalRef)(env, bridge_class);
    G_BRIDGE_CLASS.store(global_class as *mut c_void, Ordering::SeqCst);

    let om_name = CString::new("om").unwrap();
    let om_sig = CString::new("(IJJ)Z").unwrap();
    let wh_name = CString::new("wh").unwrap();
    let wh_sig = CString::new("(J)V").unwrap();

    let om_method = ((*(*env)).GetStaticMethodID)(env, bridge_class, om_name.as_ptr(), om_sig.as_ptr());
    let wh_method = ((*(*env)).GetStaticMethodID)(env, bridge_class, wh_name.as_ptr(), wh_sig.as_ptr());

    G_BRIDGE_OM.store(om_method, Ordering::SeqCst);
    G_BRIDGE_WH.store(wh_method, Ordering::SeqCst);

    if global_class.is_null() || om_method.is_null() || wh_method.is_null() {
        vape_log_pending_exception(env, "resolve NativeBridge input callbacks");
        return JNI_ERR;
    }

    vape_log("registered NativeBridge methods (9 sample + gat + cpy + 5 stub)");
    JNI_OK
}

pub unsafe fn vape_release_native_bridge(env: *mut JNIEnv) {
    let jvmti = G_JVMTI.load(Ordering::SeqCst);
    if !jvmti.is_null() {
        let _ = ((*(*jvmti)).SetEventNotificationMode)(
            jvmti,
            JVMTI_DISABLE,
            JVMTI_EVENT_CLASS_FILE_LOAD_HOOK,
            ptr::null_mut(),
        );
    }

    clear_persisted_classes(env);
    RETAIN_CLASS_TRANSFORMS.store(false, Ordering::SeqCst);
    release_window_hook();

    let global_class = G_BRIDGE_CLASS.swap(ptr::null_mut(), Ordering::SeqCst);
    if !env.is_null() && !global_class.is_null() {
        ((*(*env)).DeleteGlobalRef)(env, global_class as jobject);
    }

    G_BRIDGE_OM.store(ptr::null_mut(), Ordering::SeqCst);
    G_BRIDGE_WH.store(ptr::null_mut(), Ordering::SeqCst);
}
