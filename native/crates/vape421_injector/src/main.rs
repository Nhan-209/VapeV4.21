//! Vape 4.21 Injector CLI and interactive process selector.

pub mod process;

use std::env;
use std::path::{Path, PathBuf};
use std::time::{Duration, Instant};
use crate::process::{enumerate_candidates, inject_library, ProcessCandidate};

fn default_dll_path() -> Option<PathBuf> {
    if let Ok(exe_path) = env::current_exe() {
        if let Some(parent) = exe_path.parent() {
            let dll = parent.join("Vape421Native.dll");
            if dll.is_file() {
                return Some(dll);
            }
        }
    }
    let current_dir_dll = Path::new("Vape421Native.dll");
    if current_dir_dll.is_file() {
        return Some(current_dir_dll.to_path_buf());
    }
    None
}

fn render_selector(
    candidates: &[ProcessCandidate],
    selected: usize,
    dll_path: &Path,
) {
    #[cfg(windows)]
    {
        use windows_sys::Win32::System::Console::{
            GetStdHandle, SetConsoleCursorPosition, COORD, STD_OUTPUT_HANDLE,
        };

        unsafe {
            let handle = GetStdHandle(STD_OUTPUT_HANDLE);
            let home = COORD { X: 0, Y: 0 };
            SetConsoleCursorPosition(handle, home);
        }
    }

    println!("Vape421 Injector (Rust edition)");
    println!("DLL: {}\n", dll_path.display());
    println!("Select a Java game window (Up/Down, Enter to inject, Esc to quit)\n");

    if candidates.is_empty() {
        println!("  No visible java.exe/javaw.exe windows. Waiting...");
    } else {
        for (i, candidate) in candidates.iter().enumerate() {
            let mark = if i == selected { '>' } else { ' ' };
            println!(
                "{} [{:5}] {:<9}  {}",
                mark, candidate.process_id, candidate.executable, candidate.title
            );
        }
    }
}

fn select_process(dll_path: &Path) -> Option<u32> {
    let mut candidates: Vec<ProcessCandidate> = Vec::new();
    let mut selected = 0;
    let mut next_refresh = Instant::now();

    #[cfg(windows)]
    {
        extern "C" {
            fn _kbhit() -> i32;
            fn _getwch() -> i32;
        }

        use windows_sys::Win32::System::Console::{
            GetConsoleCursorInfo, GetStdHandle, SetConsoleCursorInfo, CONSOLE_CURSOR_INFO,
            STD_OUTPUT_HANDLE,
        };

        let handle = unsafe { GetStdHandle(STD_OUTPUT_HANDLE) };
        let mut original_cursor: CONSOLE_CURSOR_INFO = unsafe { std::mem::zeroed() };
        let mut cursor_changed = false;

        unsafe {
            if GetConsoleCursorInfo(handle, &mut original_cursor) != 0 {
                let mut hidden = original_cursor;
                hidden.bVisible = 0;
                cursor_changed = SetConsoleCursorInfo(handle, &hidden) != 0;
            }
        }

        let result_pid = loop {
            let now = Instant::now();
            if now >= next_refresh {
                let previous_pid = candidates.get(selected).map(|c| c.process_id);
                candidates = enumerate_candidates();
                selected = 0;
                if let Some(prev) = previous_pid {
                    if let Some(pos) = candidates.iter().position(|c| c.process_id == prev) {
                        selected = pos;
                    }
                }
                render_selector(&candidates, selected, dll_path);
                next_refresh = now + Duration::from_millis(750);
            }

            let has_key = unsafe { _kbhit() != 0 };
            if has_key {
                let key = unsafe { _getwch() };
                if key == 0 || key == 0xE0 {
                    let arrow = unsafe { _getwch() };
                    if !candidates.is_empty() {
                        if arrow == 72 {
                            // Up arrow
                            selected = if selected == 0 {
                                candidates.len() - 1
                            } else {
                                selected - 1
                            };
                            render_selector(&candidates, selected, dll_path);
                        } else if arrow == 80 {
                            // Down arrow
                            selected = (selected + 1) % candidates.len();
                            render_selector(&candidates, selected, dll_path);
                        }
                    }
                } else if key == 13 && !candidates.is_empty() {
                    // Enter
                    break Some(candidates[selected].process_id);
                } else if key == 27 {
                    // Esc
                    break None;
                }
            }

            std::thread::sleep(Duration::from_millis(25));
        };

        if cursor_changed {
            unsafe { SetConsoleCursorInfo(handle, &original_cursor) };
        }

        println!();
        result_pid
    }
    #[cfg(not(windows))]
    {
        let _ = (dll_path, selected, next_refresh);
        eprintln!("Interactive selector is supported on Windows.");
        None
    }
}

fn print_usage(program: &str) {
    eprintln!(
        "Usage: {} [Vape421Native.dll]\n       {} <minecraft-pid> <Vape421Native.dll>\nWithout a PID, an automatically refreshing Java window selector is shown.\nThe injected DLL loads and starts the Java product automatically.",
        program, program
    );
}

fn main() {
    let args: Vec<String> = env::args().collect();
    let program = args.first().map(|s| s.as_str()).unwrap_or("Vape421Injector");

    let (pid, dll_path) = match args.len() {
        3 => {
            let parsed_pid: u32 = match args[1].parse() {
                Ok(p) => p,
                Err(_) => {
                    eprintln!("Invalid process id: {}", args[1]);
                    std::process::exit(2);
                }
            };
            let path = PathBuf::from(&args[2]);
            if !path.is_file() {
                eprintln!("DLL does not exist: {}", path.display());
                std::process::exit(2);
            }
            (Some(parsed_pid), path)
        }
        2 => {
            let path = PathBuf::from(&args[1]);
            if !path.is_file() {
                eprintln!("DLL does not exist: {}", path.display());
                std::process::exit(2);
            }
            (None, path)
        }
        1 => {
            let path = match default_dll_path() {
                Some(p) => p,
                None => {
                    eprintln!("Vape421Native.dll was not found beside the injector.");
                    print_usage(program);
                    std::process::exit(2);
                }
            };
            (None, path)
        }
        _ => {
            print_usage(program);
            std::process::exit(2);
        }
    };

    let target_pid = match pid {
        Some(p) => p,
        None => match select_process(&dll_path) {
            Some(p) => p,
            None => std::process::exit(1),
        },
    };

    let result = inject_library(target_pid, &dll_path);
    match result {
        1 => {
            println!(
                "Loaded {} into PID {}; Java bootstrap is running asynchronously.",
                dll_path.display(),
                target_pid
            );
            std::process::exit(0);
        }
        2 => {
            println!(
                "{} is already loaded in PID {}; no second bootstrap was requested.",
                dll_path.display(),
                target_pid
            );
            std::process::exit(0);
        }
        _ => {
            eprintln!("Injection into PID {} failed.", target_pid);
            std::process::exit(3);
        }
    }
}
