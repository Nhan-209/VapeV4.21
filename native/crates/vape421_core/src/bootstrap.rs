//! Loader bootstrap, IPC memory mapping, and controller TCP communication.

use std::io::{Read, Write};
use std::net::{SocketAddr, TcpStream};
use std::sync::atomic::{AtomicI32, Ordering};
use std::sync::{Mutex, OnceLock};
use std::time::Duration;

pub const VAPE421_BOOTSTRAP_MAGIC: u32 = 0x54423456;
pub const VAPE421_BOOTSTRAP_VERSION: u16 = 2;
pub const VAPE421_BOOTSTRAP_MODE_ONLINE: u32 = 1;
pub const VAPE421_BOOTSTRAP_STATUS_CREATED: u32 = 1;
pub const VAPE421_BOOTSTRAP_STATUS_CONSUMED: u32 = 2;
pub const VAPE421_BOOTSTRAP_STATUS_FAILED: u32 = 3;

#[repr(C, packed)]
#[derive(Copy, Clone)]
pub struct Vape421BootstrapV2 {
    pub magic: u32,
    pub version: u16,
    pub structure_size: u16,
    pub target_pid: u32,
    pub mode: u32,
    pub controller_port: u16,
    pub reserved0: u16,
    pub service_http_base: [u8; 256],
    pub service_zeus_host: [u8; 128],
    pub service_zeus_port: u16,
    pub reserved: [u8; 14],
    pub status: u32,
}

// Compile-time assertion that size is exactly 424 bytes
const _: () = assert!(std::mem::size_of::<Vape421BootstrapV2>() == 424);

impl Default for Vape421BootstrapV2 {
    fn default() -> Self {
        Self {
            magic: VAPE421_BOOTSTRAP_MAGIC,
            version: VAPE421_BOOTSTRAP_VERSION,
            structure_size: std::mem::size_of::<Vape421BootstrapV2>() as u16,
            target_pid: 0,
            mode: VAPE421_BOOTSTRAP_MODE_ONLINE,
            controller_port: 0,
            reserved0: 0,
            service_http_base: [0; 256],
            service_zeus_host: [0; 128],
            service_zeus_port: 0,
            reserved: [0; 14],
            status: VAPE421_BOOTSTRAP_STATUS_CREATED,
        }
    }
}

pub const TOKEN_STATE_UNINITIALIZED: i32 = 0;
pub const TOKEN_STATE_STANDALONE: i32 = 1;
pub const TOKEN_STATE_ONLINE: i32 = 2;
pub const TOKEN_STATE_FAILED: i32 = 3;

static TOKEN_STATE: AtomicI32 = AtomicI32::new(TOKEN_STATE_UNINITIALIZED);
static ACCESS_TOKEN: Mutex<String> = Mutex::new(String::new());
static CONTROLLER_STREAM: Mutex<Option<TcpStream>> = Mutex::new(None);
static BOOTSTRAP_INIT_ONCE: OnceLock<()> = OnceLock::new();

fn object_name(kind: &str, pid: u32) -> String {
    format!("Local\\Vape421.{}.{}", kind, pid)
}

fn signal_ack(pid: u32) {
    #[cfg(windows)]
    {
        use windows_sys::Win32::Foundation::CloseHandle;
        use windows_sys::Win32::System::Threading::{OpenEventW, SetEvent, EVENT_MODIFY_STATE};

        let name = object_name("BootstrapAck", pid);
        let wide: Vec<u16> = name.encode_utf16().chain(std::iter::once(0)).collect();
        unsafe {
            let event = OpenEventW(EVENT_MODIFY_STATE, 0, wide.as_ptr());
            if !event.is_null() {
                SetEvent(event);
                CloseHandle(event);
            }
        }
    }
    #[cfg(not(windows))]
    {
        let _ = pid;
    }
}

fn send_line(stream: &mut TcpStream, line: &str) -> bool {
    let mut data = line.as_bytes().to_vec();
    data.push(b'\n');
    stream.write_all(&data).is_ok()
}

fn receive_line(stream: &mut TcpStream) -> Option<String> {
    let mut buffer = Vec::new();
    let mut byte = [0u8; 1];
    while stream.read_exact(&mut byte).is_ok() {
        if byte[0] == b'\n' {
            if buffer.ends_with(b"\r") {
                buffer.pop();
            }
            return String::from_utf8(buffer).ok();
        }
        buffer.push(byte[0]);
        if buffer.len() > 1024 {
            return None;
        }
    }
    None
}

fn connect_controller(port: u16) -> Option<TcpStream> {
    let addr = SocketAddr::from(([127, 0, 0, 1], port));
    let stream = TcpStream::connect_timeout(&addr, Duration::from_millis(500)).ok()?;
    let _ = stream.set_read_timeout(Some(Duration::from_millis(500)));
    let _ = stream.set_write_timeout(Some(Duration::from_millis(500)));
    Some(stream)
}

fn request_access_token(stream: &mut TcpStream) -> Option<String> {
    if !send_line(stream, "617") || !send_line(stream, "200") {
        return None;
    }
    let token = receive_line(stream)?;
    if token.is_empty() {
        None
    } else {
        Some(token)
    }
}

fn extract_null_terminated_str(bytes: &[u8]) -> Option<&str> {
    let null_pos = bytes.iter().position(|&b| b == 0)?;
    if null_pos == 0 {
        return None;
    }
    std::str::from_utf8(&bytes[..null_pos]).ok()
}

/// Initialize loader bootstrap.
pub fn vape_loader_bootstrap_initialize() -> bool {
    BOOTSTRAP_INIT_ONCE.get_or_init(|| {
        let pid = std::process::id();
        let mut final_state = TOKEN_STATE_FAILED;
        let mut access_token = "0".to_string();

        #[cfg(windows)]
        {
            use windows_sys::Win32::Foundation::{CloseHandle, GetLastError, ERROR_FILE_NOT_FOUND};
            use windows_sys::Win32::System::Environment::SetEnvironmentVariableA;
            use windows_sys::Win32::System::Memory::{
                MapViewOfFile, OpenFileMappingW, UnmapViewOfFile, FILE_MAP_READ, FILE_MAP_WRITE,
            };

            let name = object_name("Bootstrap", pid);
            let wide: Vec<u16> = name.encode_utf16().chain(std::iter::once(0)).collect();

            unsafe {
                let mapping = OpenFileMappingW(FILE_MAP_READ | FILE_MAP_WRITE, 0, wide.as_ptr());
                if mapping.is_null() {
                    if GetLastError() == ERROR_FILE_NOT_FOUND {
                        final_state = TOKEN_STATE_STANDALONE;
                        access_token = "0".to_string();
                    }
                } else {
                    let ptr = MapViewOfFile(
                        mapping,
                        FILE_MAP_READ | FILE_MAP_WRITE,
                        0,
                        0,
                        std::mem::size_of::<Vape421BootstrapV2>(),
                    );
                    if !ptr.is_null() {
                        let block = &mut *(ptr as *mut Vape421BootstrapV2);
                        let is_valid = block.magic == VAPE421_BOOTSTRAP_MAGIC
                            && block.version == VAPE421_BOOTSTRAP_VERSION
                            && block.structure_size as usize == std::mem::size_of::<Vape421BootstrapV2>()
                            && block.target_pid == pid
                            && block.mode == VAPE421_BOOTSTRAP_MODE_ONLINE
                            && block.status == VAPE421_BOOTSTRAP_STATUS_CREATED
                            && block.controller_port != 0
                            && block.service_zeus_port != 0;

                        if is_valid {
                            let http_base = extract_null_terminated_str(&block.service_http_base);
                            let zeus_host = extract_null_terminated_str(&block.service_zeus_host);

                            if let (Some(http_base), Some(zeus_host)) = (http_base, zeus_host) {
                                let zeus_address = format!("{}:{}", zeus_host, block.service_zeus_port);
                                let c_http = std::ffi::CString::new(http_base).unwrap_or_default();
                                let c_zeus = std::ffi::CString::new(zeus_address).unwrap_or_default();
                                let c_var_http = std::ffi::CString::new("VAPE_ONLINE_BASE_URL").unwrap();
                                let c_var_zeus = std::ffi::CString::new("VAPE_ZEUS_ADDRESS").unwrap();

                                SetEnvironmentVariableA(c_var_http.as_ptr() as _, c_http.as_ptr() as _);
                                SetEnvironmentVariableA(c_var_zeus.as_ptr() as _, c_zeus.as_ptr() as _);

                                if let Some(mut stream) = connect_controller(block.controller_port) {
                                    if let Some(token) = request_access_token(&mut stream) {
                                        access_token = token;
                                        final_state = TOKEN_STATE_ONLINE;
                                        block.status = VAPE421_BOOTSTRAP_STATUS_CONSUMED;
                                        *CONTROLLER_STREAM.lock().unwrap() = Some(stream);
                                    } else {
                                        block.status = VAPE421_BOOTSTRAP_STATUS_FAILED;
                                    }
                                } else {
                                    block.status = VAPE421_BOOTSTRAP_STATUS_FAILED;
                                }
                            } else {
                                block.status = VAPE421_BOOTSTRAP_STATUS_FAILED;
                            }
                        } else {
                            block.status = VAPE421_BOOTSTRAP_STATUS_FAILED;
                        }

                        signal_ack(pid);
                        UnmapViewOfFile(ptr);
                    } else {
                        signal_ack(pid);
                    }
                    CloseHandle(mapping);
                }
            }
        }

        #[cfg(not(windows))]
        {
            // On non-Windows platforms (e.g. tests), default to standalone mode
            final_state = TOKEN_STATE_STANDALONE;
            access_token = "0".to_string();
        }

        *ACCESS_TOKEN.lock().unwrap() = access_token;
        TOKEN_STATE.store(final_state, Ordering::SeqCst);
    });

    TOKEN_STATE.load(Ordering::SeqCst) != TOKEN_STATE_FAILED
}

/// Retrieve the access token string.
pub fn vape_loader_access_token() -> String {
    ACCESS_TOKEN.lock().unwrap().clone()
}

/// Check if bootstrap failed.
pub fn vape_loader_bootstrap_failed() -> bool {
    TOKEN_STATE.load(Ordering::SeqCst) == TOKEN_STATE_FAILED
}

/// Report progress to the controller (command 604).
pub fn vape_loader_report_progress(step: i32) {
    let mut guard = CONTROLLER_STREAM.lock().unwrap();
    if let Some(ref mut stream) = *guard {
        let step_str = step.to_string();
        if !send_line(stream, "604") || !send_line(stream, &step_str) || !send_line(stream, "200") {
            *guard = None;
        }
    }
}

/// Report completion to the controller (command 606).
pub fn vape_loader_report_completed() {
    let mut guard = CONTROLLER_STREAM.lock().unwrap();
    if let Some(ref mut stream) = *guard {
        let _ = send_line(stream, "606");
        let _ = send_line(stream, "200");
        *guard = None;
    }
}

/// Report failure to the controller (command 618).
pub fn vape_loader_report_failure(message: &str) {
    let mut guard = CONTROLLER_STREAM.lock().unwrap();
    if let Some(ref mut stream) = *guard {
        let len_str = message.len().to_string();
        let _ = send_line(stream, "618");
        let _ = send_line(stream, &len_str);
        if !message.is_empty() {
            let _ = stream.write_all(message.as_bytes());
        }
        *guard = None;
    }
}

/// Clear bootstrap state.
pub fn vape_loader_bootstrap_clear() {
    let mut guard = CONTROLLER_STREAM.lock().unwrap();
    *guard = None;
    *ACCESS_TOKEN.lock().unwrap() = "0".to_string();
    TOKEN_STATE.store(TOKEN_STATE_UNINITIALIZED, Ordering::SeqCst);
}
