//! JVMTI bridge, capabilities configuration, ClassFileLoadHook event handling,
//! and Badlion 1.8.9 transformer persistence.

use core::ffi::c_void;
use std::ffi::{CStr, CString};
use std::ptr;
use std::sync::atomic::{AtomicBool, AtomicPtr, Ordering};
use std::sync::Mutex;
use vape421_core::class_parser::{class_data_matches_signature, contains_vape_callback};
use vape421_core::jni_sys::*;
use vape421_core::logger::vape_log;

pub static G_VM: AtomicPtr<JavaVM> = AtomicPtr::new(ptr::null_mut());
pub static G_JVMTI: AtomicPtr<jvmtiEnv> = AtomicPtr::new(ptr::null_mut());
pub static G_MODULE: AtomicPtr<c_void> = AtomicPtr::new(ptr::null_mut());

pub static RETAIN_CLASS_TRANSFORMS: AtomicBool = AtomicBool::new(false);
pub static REDEFINITION_ACTIVE: AtomicBool = AtomicBool::new(false);

struct CaptureState {
    target: jclass,
    bytes: Vec<u8>,
    name_matched: bool,
}

unsafe impl Send for CaptureState {}
unsafe impl Sync for CaptureState {}

static CAPTURE_STATE: Mutex<CaptureState> = Mutex::new(CaptureState {
    target: ptr::null_mut(),
    bytes: Vec::new(),
    name_matched: false,
});

struct PersistedClass {
    target: jclass,
    bytes: Vec<u8>,
}

unsafe impl Send for PersistedClass {}
unsafe impl Sync for PersistedClass {}

static PERSISTED_CLASSES: Mutex<Vec<PersistedClass>> = Mutex::new(Vec::new());

struct RedefinitionState {
    target: jclass,
    bytes: Vec<u8>,
}

unsafe impl Send for RedefinitionState {}
unsafe impl Sync for RedefinitionState {}

static REDEFINITION_STATE: Mutex<RedefinitionState> = Mutex::new(RedefinitionState {
    target: ptr::null_mut(),
    bytes: Vec::new(),
});

pub unsafe fn log_jvmti_failure(op: &str, error: jvmtiError, target: jclass) {
    let jvmti = G_JVMTI.load(Ordering::SeqCst);
    if jvmti.is_null() || error == JVMTI_ERROR_NONE {
        return;
    }

    let mut err_name_ptr: *mut i8 = ptr::null_mut();
    let mut resolved_err = "unknown".to_string();
    if ((*(*jvmti)).GetErrorName)(jvmti, error, &mut err_name_ptr) == JVMTI_ERROR_NONE
        && !err_name_ptr.is_null()
    {
        resolved_err = CStr::from_ptr(err_name_ptr).to_string_lossy().to_string();
        ((*(*jvmti)).Deallocate)(jvmti, err_name_ptr as *mut u8);
    }

    let mut sig_ptr: *mut i8 = ptr::null_mut();
    let mut resolved_sig = "<unknown>".to_string();
    if !target.is_null()
        && ((*(*jvmti)).GetClassSignature)(jvmti, target, &mut sig_ptr, ptr::null_mut())
            == JVMTI_ERROR_NONE
        && !sig_ptr.is_null()
    {
        resolved_sig = CStr::from_ptr(sig_ptr).to_string_lossy().to_string();
        ((*(*jvmti)).Deallocate)(jvmti, sig_ptr as *mut u8);
    }

    vape_log(&format!(
        "{} failed: jvmti={} ({}), target={}",
        op, error, resolved_err, resolved_sig
    ));
}

unsafe fn supply_hook_bytes(
    jvmti_env: *mut jvmtiEnv,
    bytes: &[u8],
    new_len: *mut jint,
    new_data: *mut *mut u8,
) -> bool {
    if jvmti_env.is_null() || bytes.is_empty() || new_len.is_null() || new_data.is_null() {
        return false;
    }

    let mut copy_ptr: *mut u8 = ptr::null_mut();
    let err = ((*(*jvmti_env)).Allocate)(jvmti_env, bytes.len() as jlong, &mut copy_ptr);
    if err != JVMTI_ERROR_NONE || copy_ptr.is_null() {
        return false;
    }

    ptr::copy_nonoverlapping(bytes.as_ptr(), copy_ptr, bytes.len());
    *new_len = bytes.len() as jint;
    *new_data = copy_ptr;
    true
}

unsafe fn capture_class_bytes_internal(
    env: *mut JNIEnv,
    target: jclass,
    bytes: &[u8],
) {
    if env.is_null() || target.is_null() || bytes.is_empty() {
        return;
    }

    let mut guard = CAPTURE_STATE.lock().unwrap();
    if guard.target.is_null() {
        return;
    }

    if ((*(*env)).IsSameObject)(env, target, guard.target) == JNI_FALSE {
        return;
    }

    let jvmti = G_JVMTI.load(Ordering::SeqCst);
    let mut sig_ptr: *mut i8 = ptr::null_mut();
    let mut name_matched = false;

    if !jvmti.is_null()
        && ((*(*jvmti)).GetClassSignature)(jvmti, target, &mut sig_ptr, ptr::null_mut())
            == JVMTI_ERROR_NONE
        && !sig_ptr.is_null()
    {
        let sig_str = CStr::from_ptr(sig_ptr).to_string_lossy();
        name_matched = class_data_matches_signature(bytes, &sig_str);
        ((*(*jvmti)).Deallocate)(jvmti, sig_ptr as *mut u8);
    }

    if !guard.bytes.is_empty() && guard.name_matched && !name_matched {
        return;
    }

    guard.bytes = bytes.to_vec();
    guard.name_matched = name_matched;
}

pub unsafe fn capture_for_retransform(
    env: *mut JNIEnv,
    target: jclass,
) -> Option<Vec<u8>> {
    let jvmti = G_JVMTI.load(Ordering::SeqCst);
    if jvmti.is_null() || target.is_null() {
        return None;
    }

    let global_target = ((*(*env)).NewGlobalRef)(env, target);
    if global_target.is_null() {
        return None;
    }

    {
        let mut guard = CAPTURE_STATE.lock().unwrap();
        guard.target = global_target;
        guard.bytes.clear();
        guard.name_matched = false;
    }

    let error = ((*(*jvmti)).RetransformClasses)(jvmti, 1, &target);
    let result = {
        let guard = CAPTURE_STATE.lock().unwrap();
        if error == JVMTI_ERROR_NONE && !guard.bytes.is_empty() {
            Some(guard.bytes.clone())
        } else {
            None
        }
    };

    if result.is_none() {
        log_jvmti_failure("gcb RetransformClasses", error, target);
    }

    {
        let mut guard = CAPTURE_STATE.lock().unwrap();
        guard.target = ptr::null_mut();
        guard.bytes.clear();
        guard.name_matched = false;
    }

    ((*(*env)).DeleteGlobalRef)(env, global_target);
    result
}

pub unsafe fn update_persisted_class(env: *mut JNIEnv, target: jclass, bytes: &[u8]) {
    let should_persist = contains_vape_callback(bytes);
    let mut list = PERSISTED_CLASSES.lock().unwrap();

    let existing_pos = list.iter().position(|p| {
        ((*(*env)).IsSameObject)(env, p.target, target) == JNI_TRUE
    });

    if !should_persist {
        if let Some(pos) = existing_pos {
            let removed = list.remove(pos);
            ((*(*env)).DeleteGlobalRef)(env, removed.target);
        }
        return;
    }

    if let Some(pos) = existing_pos {
        list[pos].bytes = bytes.to_vec();
    } else {
        let global_ref = ((*(*env)).NewGlobalRef)(env, target);
        if !global_ref.is_null() {
            list.push(PersistedClass {
                target: global_ref,
                bytes: bytes.to_vec(),
            });
        }
    }
}

pub unsafe fn clear_persisted_classes(env: *mut JNIEnv) {
    let mut list = PERSISTED_CLASSES.lock().unwrap();
    for item in list.drain(..) {
        if !env.is_null() && !item.target.is_null() {
            ((*(*env)).DeleteGlobalRef)(env, item.target);
        }
    }
}

pub unsafe fn set_redefinition_active(target: jclass, bytes: &[u8]) {
    let mut guard = REDEFINITION_STATE.lock().unwrap();
    guard.target = target;
    guard.bytes = bytes.to_vec();
    REDEFINITION_ACTIVE.store(true, Ordering::SeqCst);
}

pub unsafe fn clear_redefinition_active() {
    REDEFINITION_ACTIVE.store(false, Ordering::SeqCst);
    let mut guard = REDEFINITION_STATE.lock().unwrap();
    guard.target = ptr::null_mut();
    guard.bytes.clear();
}

pub unsafe extern "system" fn class_file_load_hook(
    jvmti_env: *mut jvmtiEnv,
    jni_env: *mut JNIEnv,
    class_being_redefined: jclass,
    _loader: jobject,
    _name: *const i8,
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

    if jni_env.is_null()
        || class_being_redefined.is_null()
        || class_data.is_null()
        || class_data_len <= 0
    {
        return;
    }

    let class_bytes = std::slice::from_raw_parts(class_data, class_data_len as usize);

    if REDEFINITION_ACTIVE.load(Ordering::SeqCst) {
        let guard = REDEFINITION_STATE.lock().unwrap();
        if !guard.target.is_null()
            && ((*(*jni_env)).IsSameObject)(jni_env, class_being_redefined, guard.target)
                == JNI_TRUE
        {
            capture_class_bytes_internal(jni_env, class_being_redefined, &guard.bytes);
            supply_hook_bytes(jvmti_env, &guard.bytes, new_class_data_len, new_class_data);
            return;
        }
    }

    {
        let list = PERSISTED_CLASSES.lock().unwrap();
        if let Some(entry) = list.iter().find(|p| {
            ((*(*jni_env)).IsSameObject)(jni_env, entry_target(p), class_being_redefined) == JNI_TRUE
        }) {
            capture_class_bytes_internal(jni_env, class_being_redefined, &entry.bytes);
            supply_hook_bytes(jvmti_env, &entry.bytes, new_class_data_len, new_class_data);
            return;
        }
    }

    capture_class_bytes_internal(jni_env, class_being_redefined, class_bytes);
}

#[inline]
fn entry_target(p: &PersistedClass) -> jclass {
    p.target
}

pub unsafe fn detect_badlion_189_runtime() -> bool {
    let jvmti = G_JVMTI.load(Ordering::SeqCst);
    if jvmti.is_null() {
        return false;
    }

    let mut class_count: jint = 0;
    let mut classes_ptr: *mut jclass = ptr::null_mut();

    let err = ((*(*jvmti)).GetLoadedClasses)(jvmti, &mut class_count, &mut classes_ptr);
    if err != JVMTI_ERROR_NONE || classes_ptr.is_null() || class_count <= 0 {
        return false;
    }

    let classes = std::slice::from_raw_parts(classes_ptr, class_count as usize);
    let mut has_minecraft_189 = false;
    let mut has_badlion = false;

    for &cls in classes {
        let mut sig_ptr: *mut i8 = ptr::null_mut();
        if ((*(*jvmti)).GetClassSignature)(jvmti, cls, &mut sig_ptr, ptr::null_mut())
            == JVMTI_ERROR_NONE
            && !sig_ptr.is_null()
        {
            let sig = CStr::from_ptr(sig_ptr).to_bytes();
            if sig == b"Lave;" {
                has_minecraft_189 = true;
            } else if sig.starts_with(b"Lnet/badlion/") {
                has_badlion = true;
            }
            ((*(*jvmti)).Deallocate)(jvmti, sig_ptr as *mut u8);
        }

        if has_minecraft_189 && has_badlion {
            break;
        }
    }

    ((*(*jvmti)).Deallocate)(jvmti, classes_ptr as *mut u8);
    has_minecraft_189 && has_badlion
}

pub unsafe fn vape_initialize_jvmti(vm: *mut JavaVM) -> jint {
    if vm.is_null() {
        return JNI_ERR;
    }

    G_VM.store(vm, Ordering::SeqCst);
    let mut jvmti_ptr: *mut c_void = ptr::null_mut();

    let get_env_res = ((*(*vm)).GetEnv)(vm, &mut jvmti_ptr, JVMTI_VERSION_1_2);
    if get_env_res != JNI_OK || jvmti_ptr.is_null() {
        vape_log(&format!("JVMTI 1.2 is unavailable: {}", get_env_res));
        G_JVMTI.store(ptr::null_mut(), Ordering::SeqCst);
        return JNI_ERR;
    }

    let jvmti = jvmti_ptr as *mut jvmtiEnv;
    G_JVMTI.store(jvmti, Ordering::SeqCst);

    let mut potential = jvmtiCapabilities::new();
    let mut requested = jvmtiCapabilities::new();

    let cap_err = ((*(*jvmti)).GetPotentialCapabilities)(jvmti, &mut potential);
    if cap_err != JVMTI_ERROR_NONE {
        vape_log(&format!("GetPotentialCapabilities failed: {}", cap_err));
        return JNI_ERR;
    }

    requested.set_can_redefine_classes(potential.can_redefine_classes());
    requested.set_can_redefine_any_class(potential.can_redefine_any_class());
    requested.set_can_retransform_classes(potential.can_retransform_classes());
    requested.set_can_retransform_any_class(potential.can_retransform_any_class());

    let add_cap_err = ((*(*jvmti)).AddCapabilities)(jvmti, &requested);
    if add_cap_err != JVMTI_ERROR_NONE {
        vape_log(&format!("AddCapabilities failed: {}", add_cap_err));
        return JNI_ERR;
    }

    let mut callbacks: jvmtiEventCallbacks = std::mem::zeroed();
    callbacks.ClassFileLoadHook = Some(class_file_load_hook);

    let set_cb_err = ((*(*jvmti)).SetEventCallbacks)(
        jvmti,
        &callbacks,
        std::mem::size_of::<jvmtiEventCallbacks>() as jint,
    );
    if set_cb_err != JVMTI_ERROR_NONE {
        vape_log(&format!("SetEventCallbacks failed: {}", set_cb_err));
        return JNI_ERR;
    }

    let event_err = ((*(*jvmti)).SetEventNotificationMode)(
        jvmti,
        JVMTI_ENABLE,
        JVMTI_EVENT_CLASS_FILE_LOAD_HOOK,
        ptr::null_mut(),
    );
    if event_err != JVMTI_ERROR_NONE {
        vape_log(&format!("Enable ClassFileLoadHook failed: {}", event_err));
        return JNI_ERR;
    }

    let retain_badlion = detect_badlion_189_runtime();
    RETAIN_CLASS_TRANSFORMS.store(retain_badlion, Ordering::SeqCst);

    JNI_OK
}
