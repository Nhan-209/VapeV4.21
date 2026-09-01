//! Logging utility for native bridge and diagnostics.
//! Writes timestamped messages to OutputDebugString and vape421-native.log.

use std::fs::OpenOptions;
use std::io::Write;
use std::path::{Path, PathBuf};
use std::sync::{Mutex, OnceLock};

static LOG_PATH: OnceLock<PathBuf> = OnceLock::new();
static LOG_MUTEX: Mutex<()> = Mutex::new(());

/// Set the directory path where `vape421-native.log` should be written.
pub fn set_log_directory(dir: &Path) {
    let file_path = dir.join("vape421-native.log");
    let _ = LOG_PATH.set(file_path);
}

/// Set the exact file path for the log file.
pub fn set_log_file_path(path: PathBuf) {
    let _ = LOG_PATH.set(path);
}

/// Get current timestamp formatted as `YYYY-MM-DD HH:MM:SS.mmm`.
pub fn current_timestamp() -> String {
    #[cfg(windows)]
    {
        use windows_sys::Win32::System::SystemInformation::GetLocalTime;
        use windows_sys::Win32::System::SystemInformation::SYSTEMTIME;
        let mut st = SYSTEMTIME {
            wYear: 0,
            wMonth: 0,
            wDayOfWeek: 0,
            wDay: 0,
            wHour: 0,
            wMinute: 0,
            wSecond: 0,
            wMilliseconds: 0,
        };
        unsafe { GetLocalTime(&mut st) };
        format!(
            "{:04}-{:02}-{:02} {:02}:{:02}:{:02}.{:03}",
            st.wYear, st.wMonth, st.wDay, st.wHour, st.wMinute, st.wSecond, st.wMilliseconds
        )
    }
    #[cfg(not(windows))]
    {
        use std::time::SystemTime;
        let now = SystemTime::now()
            .duration_since(SystemTime::UNIX_EPOCH)
            .unwrap_or_default();
        let secs = now.as_secs();
        let millis = now.subsec_millis();
        format!("{}.{:03}", secs, millis)
    }
}

/// Log a formatted message.
pub fn vape_log(msg: &str) {
    let timestamp = current_timestamp();
    let line = format!("[{}] {}\r\n", timestamp, msg);

    #[cfg(windows)]
    {
        use windows_sys::Win32::System::Diagnostics::Debug::OutputDebugStringW;
        let mut wide: Vec<u16> = line.encode_utf16().collect();
        wide.push(0);
        unsafe { OutputDebugStringW(wide.as_ptr()) };
    }
    #[cfg(not(windows))]
    {
        eprint!("{}", line);
    }

    if let Some(path) = LOG_PATH.get() {
        let _guard = LOG_MUTEX.lock();
        if let Ok(mut file) = OpenOptions::new().create(true).append(true).open(path) {
            let _ = file.write_all(line.as_bytes());
        }
    }
}

#[macro_export]
macro_rules! log_info {
    ($($arg:tt)*) => {
        $crate::logger::vape_log(&format!($($arg)*))
    };
}

/// Log and clear any pending JNI exception.
pub unsafe fn vape_log_pending_exception(
    env: *mut crate::jni_sys::JNIEnv,
    context: &str,
) {
    use crate::jni_sys::*;
    use std::ffi::CString;

    if env.is_null() || ((*(*env)).ExceptionCheck)(env) == JNI_FALSE {
        vape_log(&format!("{} failed without a Java exception", context));
        return;
    }

    let throwable = ((*(*env)).ExceptionOccurred)(env);
    ((*(*env)).ExceptionClear)(env);

    let throwable_class_name = CString::new("java/lang/Throwable").unwrap();
    let to_string_name = CString::new("toString").unwrap();
    let to_string_sig = CString::new("()Ljava/lang/String;").unwrap();

    let throwable_class = ((*(*env)).FindClass)(env, throwable_class_name.as_ptr());
    let to_string_method = if !throwable_class.is_null() {
        ((*(*env)).GetMethodID)(
            env,
            throwable_class,
            to_string_name.as_ptr(),
            to_string_sig.as_ptr(),
        )
    } else {
        std::ptr::null_mut()
    };

    let text_obj = if !to_string_method.is_null() && !throwable.is_null() {
        ((*(*env)).CallObjectMethod)(env, throwable, to_string_method)
    } else {
        std::ptr::null_mut()
    };

    if text_obj.is_null() || ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
        ((*(*env)).ExceptionClear)(env);
        vape_log(&format!("{} raised an unreadable Java exception", context));
        return;
    }

    let mut is_copy: jboolean = 0;
    let chars = ((*(*env)).GetStringUTFChars)(env, text_obj, &mut is_copy);
    if !chars.is_null() {
        let text_str = std::ffi::CStr::from_ptr(chars).to_string_lossy();
        vape_log(&format!("{}: {}", context, text_str));
        ((*(*env)).ReleaseStringUTFChars)(env, text_obj, chars);
    }
}
