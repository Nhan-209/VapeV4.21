//! Windows process discovery, window title enumeration, architecture validation,
//! and LoadLibraryW remote thread injection.

use std::path::Path;
use std::ptr;

#[derive(Debug, Clone)]
pub struct ProcessCandidate {
    pub process_id: u32,
    pub executable: String,
    pub title: String,
}

#[cfg(windows)]
unsafe fn is_java_process(name: &str) -> bool {
    name.eq_ignore_ascii_case("java.exe") || name.eq_ignore_ascii_case("javaw.exe")
}

#[cfg(windows)]
struct WindowSearchContext<'a> {
    candidates: &'a mut Vec<ProcessCandidate>,
}

#[cfg(windows)]
unsafe extern "system" fn capture_window_title(
    window: windows_sys::Win32::Foundation::HWND,
    lparam: windows_sys::Win32::Foundation::LPARAM,
) -> windows_sys::Win32::Foundation::BOOL {
    use windows_sys::Win32::UI::WindowsAndMessaging::{
        GetWindowTextLengthW, GetWindowTextW, GetWindowThreadProcessId, IsWindowVisible,
    };

    if IsWindowVisible(window) == 0 || GetWindowTextLengthW(window) == 0 {
        return 1;
    }

    let mut pid: u32 = 0;
    GetWindowThreadProcessId(window, &mut pid);
    if pid == 0 {
        return 1;
    }

    let mut title_buf = [0u16; 256];
    let len = GetWindowTextW(window, title_buf.as_mut_ptr(), 256);
    if len <= 0 {
        return 1;
    }

    let title = String::from_utf16_lossy(&title_buf[..len as usize]);
    let context = &mut *(lparam as *mut WindowSearchContext);

    for candidate in context.candidates.iter_mut() {
        if candidate.process_id == pid && candidate.title.is_empty() {
            candidate.title = title.clone();
            break;
        }
    }

    1
}

pub fn enumerate_candidates() -> Vec<ProcessCandidate> {
    #[cfg(windows)]
    {
        use windows_sys::Win32::Foundation::{CloseHandle, INVALID_HANDLE_VALUE};
        use windows_sys::Win32::System::Diagnostics::ToolHelp::{
            CreateToolhelp32Snapshot, Process32FirstW, Process32NextW, PROCESSENTRY32W,
            TH32CS_SNAPPROCESS,
        };
        use windows_sys::Win32::UI::WindowsAndMessaging::EnumWindows;

        let mut list = Vec::new();
        unsafe {
            let snapshot = CreateToolhelp32Snapshot(TH32CS_SNAPPROCESS, 0);
            if snapshot == INVALID_HANDLE_VALUE {
                return list;
            }

            let mut entry: PROCESSENTRY32W = std::mem::zeroed();
            entry.dwSize = std::mem::size_of::<PROCESSENTRY32W>() as u32;

            if Process32FirstW(snapshot, &mut entry) != 0 {
                loop {
                    let null_idx = entry
                        .szExeFile
                        .iter()
                        .position(|&c| c == 0)
                        .unwrap_or(entry.szExeFile.len());
                    let exe_name = String::from_utf16_lossy(&entry.szExeFile[..null_idx]);

                    if is_java_process(&exe_name) {
                        list.push(ProcessCandidate {
                            process_id: entry.th32ProcessID,
                            executable: exe_name,
                            title: String::new(),
                        });
                    }

                    if Process32NextW(snapshot, &mut entry) == 0 {
                        break;
                    }
                }
            }
            CloseHandle(snapshot);

            let mut context = WindowSearchContext {
                candidates: &mut list,
            };
            EnumWindows(
                Some(capture_window_title),
                &mut context as *mut _ as windows_sys::Win32::Foundation::LPARAM,
            );
        }

        // Only keep candidates with visible window titles
        list.retain(|c| !c.title.is_empty());
        list.sort_by_key(|c| c.process_id);
        list
    }
    #[cfg(not(windows))]
    {
        Vec::new()
    }
}

#[cfg(windows)]
unsafe fn require_x64_target(process: windows_sys::Win32::Foundation::HANDLE) -> bool {
    use windows_sys::Win32::System::LibraryLoader::{GetModuleHandleW, GetProcAddress};
    use windows_sys::Win32::System::SystemInformation::{
        IMAGE_FILE_MACHINE_AMD64, IMAGE_FILE_MACHINE_UNKNOWN,
    };
    use windows_sys::Win32::System::Threading::IsWow64Process;

    #[allow(non_snake_case)]
    type IsWow64Process2Fn = unsafe extern "system" fn(
        hProcess: windows_sys::Win32::Foundation::HANDLE,
        pProcessMachine: *mut u16,
        pNativeMachine: *mut u16,
    ) -> windows_sys::Win32::Foundation::BOOL;

    let kernel_name: Vec<u16> = "kernel32.dll".encode_utf16().chain(std::iter::once(0)).collect();
    let kernel = GetModuleHandleW(kernel_name.as_ptr());
    if !kernel.is_null() {
        let sym = std::ffi::CString::new("IsWow64Process2").unwrap();
        if let Some(proc) = GetProcAddress(kernel, sym.as_ptr() as *const u8) {
            let is_wow64_2: IsWow64Process2Fn = std::mem::transmute(proc);
            let mut proc_mach: u16 = IMAGE_FILE_MACHINE_UNKNOWN;
            let mut native_mach: u16 = IMAGE_FILE_MACHINE_UNKNOWN;
            if is_wow64_2(process, &mut proc_mach, &mut native_mach) != 0 {
                return proc_mach == IMAGE_FILE_MACHINE_UNKNOWN
                    && native_mach == IMAGE_FILE_MACHINE_AMD64;
            }
            return false;
        }
    }

    let mut wow64: windows_sys::Win32::Foundation::BOOL = 0;
    if IsWow64Process(process, &mut wow64) != 0 {
        return wow64 == 0 && std::mem::size_of::<usize>() == 8;
    }

    false
}

#[cfg(windows)]
unsafe fn remote_module_by_path(pid: u32, dll_path: &Path) -> bool {
    use windows_sys::Win32::Foundation::CloseHandle;
    use windows_sys::Win32::System::Diagnostics::ToolHelp::{
        CreateToolhelp32Snapshot, Module32FirstW, Module32NextW, MODULEENTRY32W,
        TH32CS_SNAPMODULE, TH32CS_SNAPMODULE32,
    };

    let snapshot = CreateToolhelp32Snapshot(TH32CS_SNAPMODULE | TH32CS_SNAPMODULE32, pid);
    if snapshot == windows_sys::Win32::Foundation::INVALID_HANDLE_VALUE {
        return false;
    }

    let mut entry: MODULEENTRY32W = std::mem::zeroed();
    entry.dwSize = std::mem::size_of::<MODULEENTRY32W>() as u32;

    let mut found = false;
    if Module32FirstW(snapshot, &mut entry) != 0 {
        loop {
            let null_idx = entry
                .szExePath
                .iter()
                .position(|&c| c == 0)
                .unwrap_or(entry.szExePath.len());
            let mod_path = String::from_utf16_lossy(&entry.szExePath[..null_idx]);

            if mod_path.eq_ignore_ascii_case(&dll_path.to_string_lossy()) {
                found = true;
                break;
            }

            if Module32NextW(snapshot, &mut entry) == 0 {
                break;
            }
        }
    }

    CloseHandle(snapshot);
    found
}

#[cfg(windows)]
unsafe fn remote_module_base(pid: u32, module_name: &str) -> usize {
    use windows_sys::Win32::Foundation::CloseHandle;
    use windows_sys::Win32::System::Diagnostics::ToolHelp::{
        CreateToolhelp32Snapshot, Module32FirstW, Module32NextW, MODULEENTRY32W,
        TH32CS_SNAPMODULE, TH32CS_SNAPMODULE32,
    };

    let snapshot = CreateToolhelp32Snapshot(TH32CS_SNAPMODULE | TH32CS_SNAPMODULE32, pid);
    if snapshot == windows_sys::Win32::Foundation::INVALID_HANDLE_VALUE {
        return 0;
    }

    let mut entry: MODULEENTRY32W = std::mem::zeroed();
    entry.dwSize = std::mem::size_of::<MODULEENTRY32W>() as u32;

    let mut result = 0;
    if Module32FirstW(snapshot, &mut entry) != 0 {
        loop {
            let null_idx = entry
                .szModule
                .iter()
                .position(|&c| c == 0)
                .unwrap_or(entry.szModule.len());
            let mod_name = String::from_utf16_lossy(&entry.szModule[..null_idx]);

            if mod_name.eq_ignore_ascii_case(module_name) {
                result = entry.modBaseAddr as usize;
                break;
            }

            if Module32NextW(snapshot, &mut entry) == 0 {
                break;
            }
        }
    }

    CloseHandle(snapshot);
    result
}

/// Inject a DLL into the target process by PID.
/// Returns:
/// - 0: injection failed
/// - 1: injection succeeded
/// - 2: DLL already loaded in target process
pub fn inject_library(pid: u32, dll_path: &Path) -> u32 {
    #[cfg(windows)]
    unsafe {
        use windows_sys::Win32::Foundation::{CloseHandle, GetLastError, WAIT_OBJECT_0};
        use windows_sys::Win32::System::LibraryLoader::{GetModuleHandleW, GetProcAddress};
        use windows_sys::Win32::System::Memory::{
            VirtualAllocEx, VirtualFreeEx, MEM_COMMIT, MEM_RELEASE, MEM_RESERVE, PAGE_READWRITE,
        };
        use windows_sys::Win32::System::Threading::{
            CreateRemoteThread, OpenProcess, WaitForSingleObject, PROCESS_CREATE_THREAD,
            PROCESS_QUERY_INFORMATION, PROCESS_VM_OPERATION, PROCESS_VM_READ, PROCESS_VM_WRITE,
        };
        use windows_sys::Win32::System::Diagnostics::Debug::WriteProcessMemory;

        if remote_module_by_path(pid, dll_path) {
            return 2;
        }

        let process = OpenProcess(
            PROCESS_CREATE_THREAD
                | PROCESS_QUERY_INFORMATION
                | PROCESS_VM_OPERATION
                | PROCESS_VM_WRITE
                | PROCESS_VM_READ,
            0,
            pid,
        );

        if process.is_null() {
            eprintln!("OpenProcess failed ({})", GetLastError());
            return 0;
        }

        if !require_x64_target(process) {
            eprintln!("Target process is not x64; injection refused.");
            CloseHandle(process);
            return 0;
        }

        let abs_path = match dll_path.canonicalize() {
            Ok(p) => p,
            Err(_) => dll_path.to_path_buf(),
        };

        let wide_path: Vec<u16> = abs_path
            .to_string_lossy()
            .trim_start_matches(r"\\?\")
            .encode_utf16()
            .chain(std::iter::once(0))
            .collect();
        let path_bytes = wide_path.len() * 2;

        let remote_path = VirtualAllocEx(
            process,
            ptr::null(),
            path_bytes,
            MEM_COMMIT | MEM_RESERVE,
            PAGE_READWRITE,
        );

        if remote_path.is_null() {
            eprintln!("VirtualAllocEx failed ({})", GetLastError());
            CloseHandle(process);
            return 0;
        }

        let mut written: usize = 0;
        let ok_write = WriteProcessMemory(
            process,
            remote_path,
            wide_path.as_ptr() as *const _,
            path_bytes,
            &mut written,
        );

        if ok_write == 0 || written != path_bytes {
            eprintln!("WriteProcessMemory failed ({})", GetLastError());
            VirtualFreeEx(process, remote_path, 0, MEM_RELEASE);
            CloseHandle(process);
            return 0;
        }

        let kernel_name: Vec<u16> = "kernel32.dll".encode_utf16().chain(std::iter::once(0)).collect();
        let local_kernel = GetModuleHandleW(kernel_name.as_ptr());
        let sym = std::ffi::CString::new("LoadLibraryW").unwrap();
        let local_load_lib = GetProcAddress(local_kernel, sym.as_ptr() as *const u8);
        let remote_kernel = remote_module_base(pid, "kernel32.dll");

        if local_kernel.is_null() || local_load_lib.is_none() || remote_kernel == 0 {
            eprintln!("Could not resolve remote kernel32!LoadLibraryW.");
            VirtualFreeEx(process, remote_path, 0, MEM_RELEASE);
            CloseHandle(process);
            return 0;
        }

        let offset = local_load_lib.unwrap() as usize - local_kernel as usize;
        let remote_load_lib = (remote_kernel + offset) as *const ();

        let thread = CreateRemoteThread(
            process,
            ptr::null(),
            0,
            std::mem::transmute(remote_load_lib),
            remote_path,
            0,
            ptr::null_mut(),
        );

        if thread.is_null() {
            eprintln!("CreateRemoteThread failed ({})", GetLastError());
            VirtualFreeEx(process, remote_path, 0, MEM_RELEASE);
            CloseHandle(process);
            return 0;
        }

        let wait_res = WaitForSingleObject(thread, 30000);
        CloseHandle(thread);
        VirtualFreeEx(process, remote_path, 0, MEM_RELEASE);
        CloseHandle(process);

        if wait_res != WAIT_OBJECT_0 {
            eprintln!("Remote LoadLibraryW did not finish within 30 seconds.");
            return 0;
        }

        for _ in 0..100 {
            if remote_module_by_path(pid, &abs_path) {
                return 1;
            }
            std::thread::sleep(std::time::Duration::from_millis(50));
        }

        eprintln!(
            "LoadLibraryW returned, but the DLL is not mapped. Inspect vape421-native.log for bootstrap failure."
        );
        0
    }
    #[cfg(not(windows))]
    {
        let _ = (pid, dll_path);
        0
    }
}
