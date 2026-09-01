//! High-performance, type-safe Java reflection dispatch and primitive boxing/unboxing.

use std::ffi::CString;
use std::ptr;
use vape421_core::jni_sys::*;

#[derive(Debug, Copy, Clone, PartialEq, Eq)]
pub enum PrimitiveKind {
    Reference,
    Boolean,
    Byte,
    Char,
    Short,
    Int,
    Long,
    Float,
    Double,
    Void,
}

impl PrimitiveKind {
    pub fn wrapper_class_name(self) -> &'static str {
        match self {
            PrimitiveKind::Boolean => "java/lang/Boolean",
            PrimitiveKind::Byte => "java/lang/Byte",
            PrimitiveKind::Char => "java/lang/Character",
            PrimitiveKind::Short => "java/lang/Short",
            PrimitiveKind::Int => "java/lang/Integer",
            PrimitiveKind::Long => "java/lang/Long",
            PrimitiveKind::Float => "java/lang/Float",
            PrimitiveKind::Double => "java/lang/Double",
            _ => "",
        }
    }

    pub fn value_of_signature(self) -> &'static str {
        match self {
            PrimitiveKind::Boolean => "(Z)Ljava/lang/Boolean;",
            PrimitiveKind::Byte => "(B)Ljava/lang/Byte;",
            PrimitiveKind::Char => "(C)Ljava/lang/Character;",
            PrimitiveKind::Short => "(S)Ljava/lang/Short;",
            PrimitiveKind::Int => "(I)Ljava/lang/Integer;",
            PrimitiveKind::Long => "(J)Ljava/lang/Long;",
            PrimitiveKind::Float => "(F)Ljava/lang/Float;",
            PrimitiveKind::Double => "(D)Ljava/lang/Double;",
            _ => "",
        }
    }
}

pub unsafe fn throw_new(env: *mut JNIEnv, class_name: &str, message: &str) {
    if env.is_null() {
        return;
    }
    let c_class = CString::new(class_name).unwrap();
    let c_msg = CString::new(message).unwrap();
    let cls = ((*(*env)).FindClass)(env, c_class.as_ptr());
    if !cls.is_null() {
        ((*(*env)).ThrowNew)(env, cls, c_msg.as_ptr());
        ((*(*env)).DeleteLocalRef)(env, cls);
    }
}

pub unsafe fn get_primitive_kind(env: *mut JNIEnv, type_obj: jobject) -> PrimitiveKind {
    if env.is_null() || type_obj.is_null() {
        return PrimitiveKind::Reference;
    }

    let class_class_name = CString::new("java/lang/Class").unwrap();
    let get_name_name = CString::new("getName").unwrap();
    let get_name_sig = CString::new("()Ljava/lang/String;").unwrap();

    let class_class = ((*(*env)).FindClass)(env, class_class_name.as_ptr());
    if class_class.is_null() {
        return PrimitiveKind::Reference;
    }

    let get_name_method = ((*(*env)).GetMethodID)(
        env,
        class_class,
        get_name_name.as_ptr(),
        get_name_sig.as_ptr(),
    );
    if get_name_method.is_null() {
        return PrimitiveKind::Reference;
    }

    let name_obj = ((*(*env)).CallObjectMethod)(env, type_obj, get_name_method);
    if name_obj.is_null() || ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
        return PrimitiveKind::Reference;
    }

    let mut is_copy: jboolean = 0;
    let chars = ((*(*env)).GetStringUTFChars)(env, name_obj, &mut is_copy);
    if chars.is_null() {
        return PrimitiveKind::Reference;
    }

    let name_str = std::ffi::CStr::from_ptr(chars).to_bytes();
    let kind = match name_str {
        b"boolean" => PrimitiveKind::Boolean,
        b"byte" => PrimitiveKind::Byte,
        b"char" => PrimitiveKind::Char,
        b"short" => PrimitiveKind::Short,
        b"int" => PrimitiveKind::Int,
        b"long" => PrimitiveKind::Long,
        b"float" => PrimitiveKind::Float,
        b"double" => PrimitiveKind::Double,
        b"void" => PrimitiveKind::Void,
        _ => PrimitiveKind::Reference,
    };

    ((*(*env)).ReleaseStringUTFChars)(env, name_obj, chars);
    kind
}

pub unsafe fn unbox_value(
    env: *mut JNIEnv,
    kind: PrimitiveKind,
    value: jobject,
    out: &mut jvalue,
) -> bool {
    if kind == PrimitiveKind::Reference {
        out.l = value;
        return true;
    }

    if value.is_null() {
        throw_new(env, "java/lang/IllegalArgumentException", "invalid primitive argument");
        return false;
    }

    let (_class_name, method_name, sig) = match kind {
        PrimitiveKind::Boolean => ("java/lang/Boolean", "booleanValue", "()Z"),
        PrimitiveKind::Byte => ("java/lang/Byte", "byteValue", "()B"),
        PrimitiveKind::Char => ("java/lang/Character", "charValue", "()C"),
        PrimitiveKind::Short => ("java/lang/Short", "shortValue", "()S"),
        PrimitiveKind::Int => ("java/lang/Integer", "intValue", "()I"),
        PrimitiveKind::Long => ("java/lang/Long", "longValue", "()J"),
        PrimitiveKind::Float => ("java/lang/Float", "floatValue", "()F"),
        PrimitiveKind::Double => ("java/lang/Double", "doubleValue", "()D"),
        _ => {
            throw_new(env, "java/lang/IllegalArgumentException", "invalid primitive argument");
            return false;
        }
    };

    let c_method = CString::new(method_name).unwrap();
    let c_sig = CString::new(sig).unwrap();

    let val_class = ((*(*env)).GetObjectClass)(env, value);
    if val_class.is_null() {
        return false;
    }

    let method = ((*(*env)).GetMethodID)(env, val_class, c_method.as_ptr(), c_sig.as_ptr());
    if method.is_null() {
        return false;
    }

    match kind {
        PrimitiveKind::Boolean => {
            out.z = ((*(*env)).CallBooleanMethod)(env, value, method);
        }
        PrimitiveKind::Byte => {
            out.b = ((*(*env)).CallByteMethod)(env, value, method);
        }
        PrimitiveKind::Char => {
            out.c = ((*(*env)).CallCharMethod)(env, value, method);
        }
        PrimitiveKind::Short => {
            out.s = ((*(*env)).CallShortMethod)(env, value, method);
        }
        PrimitiveKind::Int => {
            out.i = ((*(*env)).CallIntMethod)(env, value, method);
        }
        PrimitiveKind::Long => {
            out.j = ((*(*env)).CallLongMethod)(env, value, method);
        }
        PrimitiveKind::Float => {
            out.f = ((*(*env)).CallFloatMethod)(env, value, method);
        }
        PrimitiveKind::Double => {
            out.d = ((*(*env)).CallDoubleMethod)(env, value, method);
        }
        _ => return false,
    }

    true
}

pub unsafe fn box_primitive_value(env: *mut JNIEnv, kind: PrimitiveKind, val: jvalue) -> jobject {
    if kind == PrimitiveKind::Void || kind == PrimitiveKind::Reference {
        return ptr::null_mut();
    }

    let class_name = kind.wrapper_class_name();
    let c_name = CString::new(class_name).unwrap();
    let wrapper_class = ((*(*env)).FindClass)(env, c_name.as_ptr());
    if wrapper_class.is_null() {
        return ptr::null_mut();
    }

    let sig = kind.value_of_signature();
    let c_sig = CString::new(sig).unwrap();
    let value_of = ((*(*env)).GetStaticMethodID)(
        env,
        wrapper_class,
        CString::new("valueOf").unwrap().as_ptr(),
        c_sig.as_ptr(),
    );
    if value_of.is_null() {
        return ptr::null_mut();
    }

    ((*(*env)).CallStaticObjectMethodA)(env, wrapper_class, value_of, &val as *const jvalue)
}

pub unsafe fn invoke_reflected_method(
    env: *mut JNIEnv,
    reflected_method: jobject,
    receiver: jobject,
    arguments: jobjectArray,
) -> jobject {
    if reflected_method.is_null() {
        throw_new(env, "java/lang/NullPointerException", "method is null");
        return ptr::null_mut();
    }

    let method_id = ((*(*env)).FromReflectedMethod)(env, reflected_method);
    if method_id.is_null() {
        throw_new(env, "java/lang/IllegalStateException", "FromReflectedMethod failed");
        return ptr::null_mut();
    }

    let method_class = ((*(*env)).GetObjectClass)(env, reflected_method);
    let c_decl_name = CString::new("getDeclaringClass").unwrap();
    let c_decl_sig = CString::new("()Ljava/lang/Class;").unwrap();
    let c_param_name = CString::new("getParameterTypes").unwrap();
    let c_param_sig = CString::new("()[Ljava/lang/Class;").unwrap();
    let c_ret_name = CString::new("getReturnType").unwrap();
    let c_ret_sig = CString::new("()Ljava/lang/Class;").unwrap();

    let get_declaring_class = ((*(*env)).GetMethodID)(
        env,
        method_class,
        c_decl_name.as_ptr(),
        c_decl_sig.as_ptr(),
    );
    let get_parameter_types = ((*(*env)).GetMethodID)(
        env,
        method_class,
        c_param_name.as_ptr(),
        c_param_sig.as_ptr(),
    );
    let get_return_type = ((*(*env)).GetMethodID)(
        env,
        method_class,
        c_ret_name.as_ptr(),
        c_ret_sig.as_ptr(),
    );

    let declaring_class = ((*(*env)).CallObjectMethod)(env, reflected_method, get_declaring_class) as jclass;
    if declaring_class.is_null() {
        throw_new(env, "java/lang/IllegalStateException", "declaring class is null");
        return ptr::null_mut();
    }

    if receiver.is_null() {
        throw_new(env, "java/lang/NullPointerException", "receiver is null");
        return ptr::null_mut();
    }

    let parameter_types = ((*(*env)).CallObjectMethod)(env, reflected_method, get_parameter_types) as jobjectArray;
    let return_type = ((*(*env)).CallObjectMethod)(env, reflected_method, get_return_type);

    if ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
        return ptr::null_mut();
    }

    let parameter_count = if parameter_types.is_null() {
        0
    } else {
        ((*(*env)).GetArrayLength)(env, parameter_types)
    };

    let argument_count = if arguments.is_null() {
        0
    } else {
        ((*(*env)).GetArrayLength)(env, arguments)
    };

    if parameter_count != argument_count {
        throw_new(
            env,
            "java/lang/IllegalArgumentException",
            "argument count does not match parameter count",
        );
        return ptr::null_mut();
    }

    let mut values = Vec::with_capacity(argument_count as usize);
    for index in 0..argument_count {
        let arg = ((*(*env)).GetObjectArrayElement)(env, arguments, index);
        let param_type = ((*(*env)).GetObjectArrayElement)(env, parameter_types, index);
        let kind = get_primitive_kind(env, param_type);

        let mut val = jvalue { l: ptr::null_mut() };
        if !unbox_value(env, kind, arg, &mut val) {
            return ptr::null_mut();
        }
        values.push(val);
    }

    let return_kind = get_primitive_kind(env, return_type);
    let mut returned = jvalue { l: ptr::null_mut() };
    let mut result_obj: jobject = ptr::null_mut();

    let values_ptr = if values.is_empty() {
        ptr::null()
    } else {
        values.as_ptr()
    };

    match return_kind {
        PrimitiveKind::Boolean => {
            returned.z = ((*(*env)).CallNonvirtualBooleanMethodA)(
                env,
                receiver,
                declaring_class,
                method_id,
                values_ptr,
            );
        }
        PrimitiveKind::Byte => {
            returned.b = ((*(*env)).CallNonvirtualByteMethodA)(
                env,
                receiver,
                declaring_class,
                method_id,
                values_ptr,
            );
        }
        PrimitiveKind::Char => {
            returned.c = ((*(*env)).CallNonvirtualCharMethodA)(
                env,
                receiver,
                declaring_class,
                method_id,
                values_ptr,
            );
        }
        PrimitiveKind::Short => {
            returned.s = ((*(*env)).CallNonvirtualShortMethodA)(
                env,
                receiver,
                declaring_class,
                method_id,
                values_ptr,
            );
        }
        PrimitiveKind::Int => {
            returned.i = ((*(*env)).CallNonvirtualIntMethodA)(
                env,
                receiver,
                declaring_class,
                method_id,
                values_ptr,
            );
        }
        PrimitiveKind::Long => {
            returned.j = ((*(*env)).CallNonvirtualLongMethodA)(
                env,
                receiver,
                declaring_class,
                method_id,
                values_ptr,
            );
        }
        PrimitiveKind::Float => {
            returned.f = ((*(*env)).CallNonvirtualFloatMethodA)(
                env,
                receiver,
                declaring_class,
                method_id,
                values_ptr,
            );
        }
        PrimitiveKind::Double => {
            returned.d = ((*(*env)).CallNonvirtualDoubleMethodA)(
                env,
                receiver,
                declaring_class,
                method_id,
                values_ptr,
            );
        }
        PrimitiveKind::Void => {
            ((*(*env)).CallNonvirtualVoidMethodA)(
                env,
                receiver,
                declaring_class,
                method_id,
                values_ptr,
            );
        }
        PrimitiveKind::Reference => {
            result_obj = ((*(*env)).CallNonvirtualObjectMethodA)(
                env,
                receiver,
                declaring_class,
                method_id,
                values_ptr,
            );
        }
    }

    if ((*(*env)).ExceptionCheck)(env) == JNI_TRUE
        || return_kind == PrimitiveKind::Void
        || return_kind == PrimitiveKind::Reference
    {
        return result_obj;
    }

    box_primitive_value(env, return_kind, returned)
}
