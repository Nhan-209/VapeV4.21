//! Integration tests for loader bootstrap, standalone sentinel mode, socket handoff,
//! and rejection of invalid/corrupted bootstrap descriptors.

use std::env;
use std::io::{BufRead, BufReader, Write};
use std::net::TcpListener;
use std::sync::atomic::{AtomicBool, Ordering};
use std::sync::Arc;
use std::thread;
use std::time::Duration;
use vape421_core::bootstrap::{
    vape_loader_access_token, vape_loader_bootstrap_clear, vape_loader_bootstrap_initialize,
    vape_loader_report_completed, vape_loader_report_progress, Vape421BootstrapV2,
    VAPE421_BOOTSTRAP_MAGIC, VAPE421_BOOTSTRAP_MODE_ONLINE, VAPE421_BOOTSTRAP_STATUS_CONSUMED,
    VAPE421_BOOTSTRAP_STATUS_CREATED, VAPE421_BOOTSTRAP_STATUS_FAILED, VAPE421_BOOTSTRAP_VERSION,
};

fn run_standalone_test() -> Result<(), String> {
    vape_loader_bootstrap_clear();
    if !vape_loader_bootstrap_initialize() || vape_loader_access_token() != "0" {
        return Err("standalone bootstrap did not return sentinel token".into());
    }
    vape_loader_bootstrap_clear();
    Ok(())
}

fn serve_controller(listener: TcpListener, succeeded: Arc<AtomicBool>) {
    if let Ok((mut stream, _)) = listener.accept() {
        let mut reader = BufReader::new(stream.try_clone().unwrap());
        let mut line = String::new();

        // Expect: 617 \n 200 \n
        if reader.read_line(&mut line).is_ok() && line.trim() == "617" {
            line.clear();
            if reader.read_line(&mut line).is_ok() && line.trim() == "200" {
                let token = "persistent-test-token\n";
                if stream.write_all(token.as_bytes()).is_ok() {
                    line.clear();
                    // Expect progress: 604 \n 23 \n 200 \n
                    if reader.read_line(&mut line).is_ok() && line.trim() == "604" {
                        line.clear();
                        if reader.read_line(&mut line).is_ok() && line.trim() == "23" {
                            line.clear();
                            if reader.read_line(&mut line).is_ok() && line.trim() == "200" {
                                line.clear();
                                // Expect completed: 606 \n 200 \n
                                if reader.read_line(&mut line).is_ok() && line.trim() == "606" {
                                    line.clear();
                                    if reader.read_line(&mut line).is_ok() && line.trim() == "200" {
                                        succeeded.store(true, Ordering::SeqCst);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fn run_socket_handoff_test(invalid: bool) -> Result<(), String> {
    let pid = std::process::id();
    let succeeded = Arc::new(AtomicBool::new(false));

    #[cfg(windows)]
    {
        use windows_sys::Win32::Foundation::{CloseHandle, INVALID_HANDLE_VALUE, WAIT_OBJECT_0};
        use windows_sys::Win32::System::Memory::{
            CreateFileMappingW, MapViewOfFile, UnmapViewOfFile, FILE_MAP_ALL_ACCESS, PAGE_READWRITE,
        };
        use windows_sys::Win32::System::Threading::{CreateEventW, WaitForSingleObject};

        let listener = if !invalid {
            let l = TcpListener::bind("127.0.0.1:0").map_err(|e| e.to_string())?;
            let succ_clone = succeeded.clone();
            let l_clone = l.try_clone().unwrap();
            thread::spawn(move || {
                serve_controller(l_clone, succ_clone);
            });
            Some(l)
        } else {
            None
        };

        let port = listener.as_ref().map(|l| l.local_addr().unwrap().port()).unwrap_or(1);

        let map_name: Vec<u16> = format!("Local\\Vape421.Bootstrap.{}", pid)
            .encode_utf16()
            .chain(std::iter::once(0))
            .collect();
        let ack_name: Vec<u16> = format!("Local\\Vape421.BootstrapAck.{}", pid)
            .encode_utf16()
            .chain(std::iter::once(0))
            .collect();

        unsafe {
            let mapping = CreateFileMappingW(
                INVALID_HANDLE_VALUE,
                std::ptr::null(),
                PAGE_READWRITE,
                0,
                std::mem::size_of::<Vape421BootstrapV2>() as u32,
                map_name.as_ptr(),
            );

            let ack = CreateEventW(std::ptr::null(), 1, 0, ack_name.as_ptr());
            let view = MapViewOfFile(
                mapping,
                FILE_MAP_ALL_ACCESS,
                0,
                0,
                std::mem::size_of::<Vape421BootstrapV2>(),
            );

            if mapping.is_null() || ack.is_null() || view.Value.is_null() {
                return Err("failed to create bootstrap test objects".into());
            }

            let mut temp_block = Vape421BootstrapV2::default();
            temp_block.magic = if invalid { 0 } else { VAPE421_BOOTSTRAP_MAGIC };
            temp_block.version = VAPE421_BOOTSTRAP_VERSION;
            temp_block.structure_size = std::mem::size_of::<Vape421BootstrapV2>() as u16;
            temp_block.target_pid = pid;
            temp_block.mode = VAPE421_BOOTSTRAP_MODE_ONLINE;
            temp_block.controller_port = port;

            let http_base = b"http://127.0.0.1:8080\0";
            let zeus_host = b"127.0.0.1\0";
            temp_block.service_http_base[..http_base.len()].copy_from_slice(http_base);
            temp_block.service_zeus_host[..zeus_host.len()].copy_from_slice(zeus_host);
            temp_block.service_zeus_port = 8091;
            temp_block.status = VAPE421_BOOTSTRAP_STATUS_CREATED;

            let block = &mut *(view.Value as *mut Vape421BootstrapV2);
            *block = temp_block;

            vape_loader_bootstrap_clear();

            if invalid {
                let init_ok = vape_loader_bootstrap_initialize();
                let wait = WaitForSingleObject(ack, 1000);
                let final_status = block.status;
                if init_ok || wait != WAIT_OBJECT_0 || final_status != VAPE421_BOOTSTRAP_STATUS_FAILED {
                    UnmapViewOfFile(view);
                    CloseHandle(ack);
                    CloseHandle(mapping);
                    return Err("invalid bootstrap was not rejected".into());
                }
            } else {
                let init_ok = vape_loader_bootstrap_initialize();
                let wait = WaitForSingleObject(ack, 1000);
                let final_status = block.status;
                if !init_ok
                    || wait != WAIT_OBJECT_0
                    || final_status != VAPE421_BOOTSTRAP_STATUS_CONSUMED
                    || vape_loader_access_token() != "persistent-test-token"
                {
                    UnmapViewOfFile(view);
                    CloseHandle(ack);
                    CloseHandle(mapping);
                    return Err("socket token bootstrap failed".into());
                }

                vape_loader_report_progress(23);
                vape_loader_report_completed();

                thread::sleep(Duration::from_millis(200));
                if !succeeded.load(Ordering::SeqCst) {
                    UnmapViewOfFile(view);
                    CloseHandle(ack);
                    CloseHandle(mapping);
                    return Err("progress protocol sequence failed".into());
                }
            }

            vape_loader_bootstrap_clear();
            UnmapViewOfFile(view);
            CloseHandle(ack);
            CloseHandle(mapping);
        }
    }

    #[cfg(not(windows))]
    {
        let _ = (pid, succeeded, invalid);
        // On non-Windows platforms, tests pass standalone check
        if !invalid {
            run_standalone_test()?;
        }
    }

    Ok(())
}

fn main() {
    let args: Vec<String> = env::args().collect();
    let mode = args.get(1).map(|s| s.as_str()).unwrap_or("handoff");

    let result = match mode {
        "standalone" => run_standalone_test(),
        "invalid" => run_socket_handoff_test(true),
        _ => run_socket_handoff_test(false),
    };

    match result {
        Ok(()) => {
            println!("Test '{}' PASSED", mode);
            std::process::exit(0);
        }
        Err(e) => {
            eprintln!("Test '{}' FAILED: {}", mode, e);
            std::process::exit(1);
        }
    }
}
