//! Complete, pure Rust definitions of JNI and JVMTI types, interfaces, and constants.
//! Matches OpenJDK 8 - 21+ JNI / JVMTI C ABI.

#![allow(non_snake_case, non_camel_case_types, non_upper_case_globals, unused)]

use core::ffi::c_void;

pub type jboolean = u8;
pub type jbyte = i8;
pub type jchar = u16;
pub type jshort = i16;
pub type jint = i32;
pub type jlong = i64;
pub type jfloat = f32;
pub type jdouble = f64;
pub type jsize = jint;

pub const JNI_FALSE: jboolean = 0;
pub const JNI_TRUE: jboolean = 1;

pub const JNI_OK: jint = 0;
pub const JNI_ERR: jint = -1;
pub const JNI_EDETACHED: jint = -2;
pub const JNI_EVERSION: jint = -3;
pub const JNI_ENOMEM: jint = -4;
pub const JNI_EEXIST: jint = -5;
pub const JNI_EINVAL: jint = -6;

pub const JNI_VERSION_1_1: jint = 0x00010001;
pub const JNI_VERSION_1_2: jint = 0x00010002;
pub const JNI_VERSION_1_4: jint = 0x00010004;
pub const JNI_VERSION_1_6: jint = 0x00010006;
pub const JNI_VERSION_1_8: jint = 0x00010008;

pub const JVMTI_VERSION_1_0: jint = 0x30010000;
pub const JVMTI_VERSION_1_1: jint = 0x30010100;
pub const JVMTI_VERSION_1_2: jint = 0x30010200;

pub const JNI_COMMIT: jint = 1;
pub const JNI_ABORT: jint = 2;

pub type jobject = *mut c_void;
pub type jclass = jobject;
pub type jthrowable = jobject;
pub type jstring = jobject;
pub type jarray = jobject;
pub type jbooleanArray = jarray;
pub type jbyteArray = jarray;
pub type jcharArray = jarray;
pub type jshortArray = jarray;
pub type jintArray = jarray;
pub type jlongArray = jarray;
pub type jfloatArray = jarray;
pub type jdoubleArray = jarray;
pub type jobjectArray = jarray;
pub type jweak = jobject;
pub type jthread = jobject;
pub type jthreadGroup = jobject;

#[repr(C)]
pub struct _jfieldID {
    _unused: [u8; 0],
}
pub type jfieldID = *mut _jfieldID;

#[repr(C)]
pub struct _jmethodID {
    _unused: [u8; 0],
}
pub type jmethodID = *mut _jmethodID;

#[repr(C)]
#[derive(Copy, Clone)]
pub union jvalue {
    pub z: jboolean,
    pub b: jbyte,
    pub c: jchar,
    pub s: jshort,
    pub i: jint,
    pub j: jlong,
    pub f: jfloat,
    pub d: jdouble,
    pub l: jobject,
}

#[repr(C)]
pub struct JNINativeMethod {
    pub name: *const i8,
    pub signature: *const i8,
    pub fnPtr: *mut c_void,
}

pub type JNIEnv = *const JNINativeInterface_;
pub type JavaVM = *const JNIInvokeInterface_;

#[repr(C)]
pub struct JNINativeInterface_ {
    pub reserved0: *mut c_void,
    pub reserved1: *mut c_void,
    pub reserved2: *mut c_void,
    pub reserved3: *mut c_void,

    pub GetVersion: unsafe extern "system" fn(env: *mut JNIEnv) -> jint,

    pub DefineClass: unsafe extern "system" fn(
        env: *mut JNIEnv,
        name: *const i8,
        loader: jobject,
        buf: *const jbyte,
        len: jsize,
    ) -> jclass,
    pub FindClass: unsafe extern "system" fn(env: *mut JNIEnv, name: *const i8) -> jclass,

    pub FromReflectedMethod:
        unsafe extern "system" fn(env: *mut JNIEnv, method: jobject) -> jmethodID,
    pub FromReflectedField:
        unsafe extern "system" fn(env: *mut JNIEnv, field: jobject) -> jfieldID,
    pub ToReflectedMethod: unsafe extern "system" fn(
        env: *mut JNIEnv,
        cls: jclass,
        methodID: jmethodID,
        isStatic: jboolean,
    ) -> jobject,

    pub GetSuperclass: unsafe extern "system" fn(env: *mut JNIEnv, sub: jclass) -> jclass,
    pub IsAssignableFrom:
        unsafe extern "system" fn(env: *mut JNIEnv, sub: jclass, sup: jclass) -> jboolean,

    pub ToReflectedField: unsafe extern "system" fn(
        env: *mut JNIEnv,
        cls: jclass,
        fieldID: jfieldID,
        isStatic: jboolean,
    ) -> jobject,

    pub Throw: unsafe extern "system" fn(env: *mut JNIEnv, obj: jthrowable) -> jint,
    pub ThrowNew:
        unsafe extern "system" fn(env: *mut JNIEnv, clazz: jclass, msg: *const i8) -> jint,
    pub ExceptionOccurred: unsafe extern "system" fn(env: *mut JNIEnv) -> jthrowable,
    pub ExceptionDescribe: unsafe extern "system" fn(env: *mut JNIEnv),
    pub ExceptionClear: unsafe extern "system" fn(env: *mut JNIEnv),
    pub FatalError: unsafe extern "system" fn(env: *mut JNIEnv, msg: *const i8) -> !,

    pub PushLocalFrame: unsafe extern "system" fn(env: *mut JNIEnv, capacity: jint) -> jint,
    pub PopLocalFrame: unsafe extern "system" fn(env: *mut JNIEnv, result: jobject) -> jobject,

    pub NewGlobalRef: unsafe extern "system" fn(env: *mut JNIEnv, lobj: jobject) -> jobject,
    pub DeleteGlobalRef: unsafe extern "system" fn(env: *mut JNIEnv, gref: jobject),
    pub DeleteLocalRef: unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject),
    pub IsSameObject:
        unsafe extern "system" fn(env: *mut JNIEnv, obj1: jobject, obj2: jobject) -> jboolean,
    pub NewLocalRef: unsafe extern "system" fn(env: *mut JNIEnv, ref_: jobject) -> jobject,
    pub EnsureLocalCapacity: unsafe extern "system" fn(env: *mut JNIEnv, capacity: jint) -> jint,

    pub AllocObject: unsafe extern "system" fn(env: *mut JNIEnv, clazz: jclass) -> jobject,
    pub NewObject:
        unsafe extern "system" fn(env: *mut JNIEnv, clazz: jclass, methodID: jmethodID, ...) -> jobject,
    pub NewObjectV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        methodID: jmethodID,
        args: *mut c_void,
    ) -> jobject,
    pub NewObjectA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        methodID: jmethodID,
        args: *const jvalue,
    ) -> jobject,

    pub GetObjectClass: unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject) -> jclass,
    pub IsInstanceOf:
        unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject, clazz: jclass) -> jboolean,

    pub GetMethodID: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        name: *const i8,
        sig: *const i8,
    ) -> jmethodID,

    pub CallObjectMethod:
        unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject, methodID: jmethodID, ...) -> jobject,
    pub CallObjectMethodV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        methodID: jmethodID,
        args: *mut c_void,
    ) -> jobject,
    pub CallObjectMethodA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        methodID: jmethodID,
        args: *const jvalue,
    ) -> jobject,

    pub CallBooleanMethod:
        unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject, methodID: jmethodID, ...) -> jboolean,
    pub CallBooleanMethodV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        methodID: jmethodID,
        args: *mut c_void,
    ) -> jboolean,
    pub CallBooleanMethodA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        methodID: jmethodID,
        args: *const jvalue,
    ) -> jboolean,

    pub CallByteMethod:
        unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject, methodID: jmethodID, ...) -> jbyte,
    pub CallByteMethodV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        methodID: jmethodID,
        args: *mut c_void,
    ) -> jbyte,
    pub CallByteMethodA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        methodID: jmethodID,
        args: *const jvalue,
    ) -> jbyte,

    pub CallCharMethod:
        unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject, methodID: jmethodID, ...) -> jchar,
    pub CallCharMethodV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        methodID: jmethodID,
        args: *mut c_void,
    ) -> jchar,
    pub CallCharMethodA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        methodID: jmethodID,
        args: *const jvalue,
    ) -> jchar,

    pub CallShortMethod:
        unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject, methodID: jmethodID, ...) -> jshort,
    pub CallShortMethodV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        methodID: jmethodID,
        args: *mut c_void,
    ) -> jshort,
    pub CallShortMethodA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        methodID: jmethodID,
        args: *const jvalue,
    ) -> jshort,

    pub CallIntMethod:
        unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject, methodID: jmethodID, ...) -> jint,
    pub CallIntMethodV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        methodID: jmethodID,
        args: *mut c_void,
    ) -> jint,
    pub CallIntMethodA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        methodID: jmethodID,
        args: *const jvalue,
    ) -> jint,

    pub CallLongMethod:
        unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject, methodID: jmethodID, ...) -> jlong,
    pub CallLongMethodV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        methodID: jmethodID,
        args: *mut c_void,
    ) -> jlong,
    pub CallLongMethodA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        methodID: jmethodID,
        args: *const jvalue,
    ) -> jlong,

    pub CallFloatMethod:
        unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject, methodID: jmethodID, ...) -> jfloat,
    pub CallFloatMethodV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        methodID: jmethodID,
        args: *mut c_void,
    ) -> jfloat,
    pub CallFloatMethodA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        methodID: jmethodID,
        args: *const jvalue,
    ) -> jfloat,

    pub CallDoubleMethod:
        unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject, methodID: jmethodID, ...) -> jdouble,
    pub CallDoubleMethodV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        methodID: jmethodID,
        args: *mut c_void,
    ) -> jdouble,
    pub CallDoubleMethodA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        methodID: jmethodID,
        args: *const jvalue,
    ) -> jdouble,

    pub CallVoidMethod:
        unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject, methodID: jmethodID, ...),
    pub CallVoidMethodV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        methodID: jmethodID,
        args: *mut c_void,
    ),
    pub CallVoidMethodA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        methodID: jmethodID,
        args: *const jvalue,
    ),

    pub CallNonvirtualObjectMethod: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        clazz: jclass,
        methodID: jmethodID,
        ...
    ) -> jobject,
    pub CallNonvirtualObjectMethodV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        clazz: jclass,
        methodID: jmethodID,
        args: *mut c_void,
    ) -> jobject,
    pub CallNonvirtualObjectMethodA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        clazz: jclass,
        methodID: jmethodID,
        args: *const jvalue,
    ) -> jobject,

    pub CallNonvirtualBooleanMethod: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        clazz: jclass,
        methodID: jmethodID,
        ...
    ) -> jboolean,
    pub CallNonvirtualBooleanMethodV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        clazz: jclass,
        methodID: jmethodID,
        args: *mut c_void,
    ) -> jboolean,
    pub CallNonvirtualBooleanMethodA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        clazz: jclass,
        methodID: jmethodID,
        args: *const jvalue,
    ) -> jboolean,

    pub CallNonvirtualByteMethod: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        clazz: jclass,
        methodID: jmethodID,
        ...
    ) -> jbyte,
    pub CallNonvirtualByteMethodV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        clazz: jclass,
        methodID: jmethodID,
        args: *mut c_void,
    ) -> jbyte,
    pub CallNonvirtualByteMethodA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        clazz: jclass,
        methodID: jmethodID,
        args: *const jvalue,
    ) -> jbyte,

    pub CallNonvirtualCharMethod: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        clazz: jclass,
        methodID: jmethodID,
        ...
    ) -> jchar,
    pub CallNonvirtualCharMethodV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        clazz: jclass,
        methodID: jmethodID,
        args: *mut c_void,
    ) -> jchar,
    pub CallNonvirtualCharMethodA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        clazz: jclass,
        methodID: jmethodID,
        args: *const jvalue,
    ) -> jchar,

    pub CallNonvirtualShortMethod: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        clazz: jclass,
        methodID: jmethodID,
        ...
    ) -> jshort,
    pub CallNonvirtualShortMethodV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        clazz: jclass,
        methodID: jmethodID,
        args: *mut c_void,
    ) -> jshort,
    pub CallNonvirtualShortMethodA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        clazz: jclass,
        methodID: jmethodID,
        args: *const jvalue,
    ) -> jshort,

    pub CallNonvirtualIntMethod: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        clazz: jclass,
        methodID: jmethodID,
        ...
    ) -> jint,
    pub CallNonvirtualIntMethodV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        clazz: jclass,
        methodID: jmethodID,
        args: *mut c_void,
    ) -> jint,
    pub CallNonvirtualIntMethodA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        clazz: jclass,
        methodID: jmethodID,
        args: *const jvalue,
    ) -> jint,

    pub CallNonvirtualLongMethod: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        clazz: jclass,
        methodID: jmethodID,
        ...
    ) -> jlong,
    pub CallNonvirtualLongMethodV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        clazz: jclass,
        methodID: jmethodID,
        args: *mut c_void,
    ) -> jlong,
    pub CallNonvirtualLongMethodA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        clazz: jclass,
        methodID: jmethodID,
        args: *const jvalue,
    ) -> jlong,

    pub CallNonvirtualFloatMethod: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        clazz: jclass,
        methodID: jmethodID,
        ...
    ) -> jfloat,
    pub CallNonvirtualFloatMethodV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        clazz: jclass,
        methodID: jmethodID,
        args: *mut c_void,
    ) -> jfloat,
    pub CallNonvirtualFloatMethodA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        clazz: jclass,
        methodID: jmethodID,
        args: *const jvalue,
    ) -> jfloat,

    pub CallNonvirtualDoubleMethod: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        clazz: jclass,
        methodID: jmethodID,
        ...
    ) -> jdouble,
    pub CallNonvirtualDoubleMethodV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        clazz: jclass,
        methodID: jmethodID,
        args: *mut c_void,
    ) -> jdouble,
    pub CallNonvirtualDoubleMethodA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        clazz: jclass,
        methodID: jmethodID,
        args: *const jvalue,
    ) -> jdouble,

    pub CallNonvirtualVoidMethod: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        clazz: jclass,
        methodID: jmethodID,
        ...
    ),
    pub CallNonvirtualVoidMethodV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        clazz: jclass,
        methodID: jmethodID,
        args: *mut c_void,
    ),
    pub CallNonvirtualVoidMethodA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        clazz: jclass,
        methodID: jmethodID,
        args: *const jvalue,
    ),

    pub GetFieldID: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        name: *const i8,
        sig: *const i8,
    ) -> jfieldID,

    pub GetObjectField:
        unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject, fieldID: jfieldID) -> jobject,
    pub GetBooleanField:
        unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject, fieldID: jfieldID) -> jboolean,
    pub GetByteField:
        unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject, fieldID: jfieldID) -> jbyte,
    pub GetCharField:
        unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject, fieldID: jfieldID) -> jchar,
    pub GetShortField:
        unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject, fieldID: jfieldID) -> jshort,
    pub GetIntField:
        unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject, fieldID: jfieldID) -> jint,
    pub GetLongField:
        unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject, fieldID: jfieldID) -> jlong,
    pub GetFloatField:
        unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject, fieldID: jfieldID) -> jfloat,
    pub GetDoubleField:
        unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject, fieldID: jfieldID) -> jdouble,

    pub SetObjectField: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        fieldID: jfieldID,
        val: jobject,
    ),
    pub SetBooleanField: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        fieldID: jfieldID,
        val: jboolean,
    ),
    pub SetByteField:
        unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject, fieldID: jfieldID, val: jbyte),
    pub SetCharField:
        unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject, fieldID: jfieldID, val: jchar),
    pub SetShortField:
        unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject, fieldID: jfieldID, val: jshort),
    pub SetIntField:
        unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject, fieldID: jfieldID, val: jint),
    pub SetLongField:
        unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject, fieldID: jfieldID, val: jlong),
    pub SetFloatField:
        unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject, fieldID: jfieldID, val: jfloat),
    pub SetDoubleField: unsafe extern "system" fn(
        env: *mut JNIEnv,
        obj: jobject,
        fieldID: jfieldID,
        val: jdouble,
    ),

    pub GetStaticMethodID: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        name: *const i8,
        sig: *const i8,
    ) -> jmethodID,

    pub CallStaticObjectMethod:
        unsafe extern "system" fn(env: *mut JNIEnv, clazz: jclass, methodID: jmethodID, ...) -> jobject,
    pub CallStaticObjectMethodV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        methodID: jmethodID,
        args: *mut c_void,
    ) -> jobject,
    pub CallStaticObjectMethodA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        methodID: jmethodID,
        args: *const jvalue,
    ) -> jobject,

    pub CallStaticBooleanMethod: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        methodID: jmethodID,
        ...
    ) -> jboolean,
    pub CallStaticBooleanMethodV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        methodID: jmethodID,
        args: *mut c_void,
    ) -> jboolean,
    pub CallStaticBooleanMethodA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        methodID: jmethodID,
        args: *const jvalue,
    ) -> jboolean,

    pub CallStaticByteMethod:
        unsafe extern "system" fn(env: *mut JNIEnv, clazz: jclass, methodID: jmethodID, ...) -> jbyte,
    pub CallStaticByteMethodV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        methodID: jmethodID,
        args: *mut c_void,
    ) -> jbyte,
    pub CallStaticByteMethodA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        methodID: jmethodID,
        args: *const jvalue,
    ) -> jbyte,

    pub CallStaticCharMethod:
        unsafe extern "system" fn(env: *mut JNIEnv, clazz: jclass, methodID: jmethodID, ...) -> jchar,
    pub CallStaticCharMethodV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        methodID: jmethodID,
        args: *mut c_void,
    ) -> jchar,
    pub CallStaticCharMethodA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        methodID: jmethodID,
        args: *const jvalue,
    ) -> jchar,

    pub CallStaticShortMethod:
        unsafe extern "system" fn(env: *mut JNIEnv, clazz: jclass, methodID: jmethodID, ...) -> jshort,
    pub CallStaticShortMethodV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        methodID: jmethodID,
        args: *mut c_void,
    ) -> jshort,
    pub CallStaticShortMethodA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        methodID: jmethodID,
        args: *const jvalue,
    ) -> jshort,

    pub CallStaticIntMethod:
        unsafe extern "system" fn(env: *mut JNIEnv, clazz: jclass, methodID: jmethodID, ...) -> jint,
    pub CallStaticIntMethodV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        methodID: jmethodID,
        args: *mut c_void,
    ) -> jint,
    pub CallStaticIntMethodA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        methodID: jmethodID,
        args: *const jvalue,
    ) -> jint,

    pub CallStaticLongMethod:
        unsafe extern "system" fn(env: *mut JNIEnv, clazz: jclass, methodID: jmethodID, ...) -> jlong,
    pub CallStaticLongMethodV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        methodID: jmethodID,
        args: *mut c_void,
    ) -> jlong,
    pub CallStaticLongMethodA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        methodID: jmethodID,
        args: *const jvalue,
    ) -> jlong,

    pub CallStaticFloatMethod:
        unsafe extern "system" fn(env: *mut JNIEnv, clazz: jclass, methodID: jmethodID, ...) -> jfloat,
    pub CallStaticFloatMethodV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        methodID: jmethodID,
        args: *mut c_void,
    ) -> jfloat,
    pub CallStaticFloatMethodA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        methodID: jmethodID,
        args: *const jvalue,
    ) -> jfloat,

    pub CallStaticDoubleMethod: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        methodID: jmethodID,
        ...
    ) -> jdouble,
    pub CallStaticDoubleMethodV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        methodID: jmethodID,
        args: *mut c_void,
    ) -> jdouble,
    pub CallStaticDoubleMethodA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        methodID: jmethodID,
        args: *const jvalue,
    ) -> jdouble,

    pub CallStaticVoidMethod:
        unsafe extern "system" fn(env: *mut JNIEnv, cls: jclass, methodID: jmethodID, ...),
    pub CallStaticVoidMethodV: unsafe extern "system" fn(
        env: *mut JNIEnv,
        cls: jclass,
        methodID: jmethodID,
        args: *mut c_void,
    ),
    pub CallStaticVoidMethodA: unsafe extern "system" fn(
        env: *mut JNIEnv,
        cls: jclass,
        methodID: jmethodID,
        args: *const jvalue,
    ),

    pub GetStaticFieldID: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        name: *const i8,
        sig: *const i8,
    ) -> jfieldID,

    pub GetStaticObjectField:
        unsafe extern "system" fn(env: *mut JNIEnv, clazz: jclass, fieldID: jfieldID) -> jobject,
    pub GetStaticBooleanField:
        unsafe extern "system" fn(env: *mut JNIEnv, clazz: jclass, fieldID: jfieldID) -> jboolean,
    pub GetStaticByteField:
        unsafe extern "system" fn(env: *mut JNIEnv, clazz: jclass, fieldID: jfieldID) -> jbyte,
    pub GetStaticCharField:
        unsafe extern "system" fn(env: *mut JNIEnv, clazz: jclass, fieldID: jfieldID) -> jchar,
    pub GetStaticShortField:
        unsafe extern "system" fn(env: *mut JNIEnv, clazz: jclass, fieldID: jfieldID) -> jshort,
    pub GetStaticIntField:
        unsafe extern "system" fn(env: *mut JNIEnv, clazz: jclass, fieldID: jfieldID) -> jint,
    pub GetStaticLongField:
        unsafe extern "system" fn(env: *mut JNIEnv, clazz: jclass, fieldID: jfieldID) -> jlong,
    pub GetStaticFloatField:
        unsafe extern "system" fn(env: *mut JNIEnv, clazz: jclass, fieldID: jfieldID) -> jfloat,
    pub GetStaticDoubleField:
        unsafe extern "system" fn(env: *mut JNIEnv, clazz: jclass, fieldID: jfieldID) -> jdouble,

    pub SetStaticObjectField: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        fieldID: jfieldID,
        value: jobject,
    ),
    pub SetStaticBooleanField: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        fieldID: jfieldID,
        value: jboolean,
    ),
    pub SetStaticByteField: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        fieldID: jfieldID,
        value: jbyte,
    ),
    pub SetStaticCharField: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        fieldID: jfieldID,
        value: jchar,
    ),
    pub SetStaticShortField: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        fieldID: jfieldID,
        value: jshort,
    ),
    pub SetStaticIntField: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        fieldID: jfieldID,
        value: jint,
    ),
    pub SetStaticLongField: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        fieldID: jfieldID,
        value: jlong,
    ),
    pub SetStaticFloatField: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        fieldID: jfieldID,
        value: jfloat,
    ),
    pub SetStaticDoubleField: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        fieldID: jfieldID,
        value: jdouble,
    ),

    pub NewString:
        unsafe extern "system" fn(env: *mut JNIEnv, unicode: *const jchar, len: jsize) -> jstring,
    pub GetStringLength: unsafe extern "system" fn(env: *mut JNIEnv, str: jstring) -> jsize,
    pub GetStringChars:
        unsafe extern "system" fn(env: *mut JNIEnv, str: jstring, isCopy: *mut jboolean)
            -> *const jchar,
    pub ReleaseStringChars:
        unsafe extern "system" fn(env: *mut JNIEnv, str: jstring, chars: *const jchar),

    pub NewStringUTF: unsafe extern "system" fn(env: *mut JNIEnv, utf: *const i8) -> jstring,
    pub GetStringUTFLength: unsafe extern "system" fn(env: *mut JNIEnv, str: jstring) -> jsize,
    pub GetStringUTFChars:
        unsafe extern "system" fn(env: *mut JNIEnv, str: jstring, isCopy: *mut jboolean)
            -> *const i8,
    pub ReleaseStringUTFChars:
        unsafe extern "system" fn(env: *mut JNIEnv, str: jstring, utf: *const i8),

    pub GetArrayLength: unsafe extern "system" fn(env: *mut JNIEnv, array: jarray) -> jsize,

    pub NewObjectArray: unsafe extern "system" fn(
        env: *mut JNIEnv,
        len: jsize,
        clazz: jclass,
        init: jobject,
    ) -> jobjectArray,
    pub GetObjectArrayElement:
        unsafe extern "system" fn(env: *mut JNIEnv, array: jobjectArray, index: jsize) -> jobject,
    pub SetObjectArrayElement: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jobjectArray,
        index: jsize,
        val: jobject,
    ),

    pub NewBooleanArray:
        unsafe extern "system" fn(env: *mut JNIEnv, len: jsize) -> jbooleanArray,
    pub NewByteArray: unsafe extern "system" fn(env: *mut JNIEnv, len: jsize) -> jbyteArray,
    pub NewCharArray: unsafe extern "system" fn(env: *mut JNIEnv, len: jsize) -> jcharArray,
    pub NewShortArray: unsafe extern "system" fn(env: *mut JNIEnv, len: jsize) -> jshortArray,
    pub NewIntArray: unsafe extern "system" fn(env: *mut JNIEnv, len: jsize) -> jintArray,
    pub NewLongArray: unsafe extern "system" fn(env: *mut JNIEnv, len: jsize) -> jlongArray,
    pub NewFloatArray: unsafe extern "system" fn(env: *mut JNIEnv, len: jsize) -> jfloatArray,
    pub NewDoubleArray: unsafe extern "system" fn(env: *mut JNIEnv, len: jsize) -> jdoubleArray,

    pub GetBooleanArrayElements: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jbooleanArray,
        isCopy: *mut jboolean,
    ) -> *mut jboolean,
    pub GetByteArrayElements: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jbyteArray,
        isCopy: *mut jboolean,
    ) -> *mut jbyte,
    pub GetCharArrayElements: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jcharArray,
        isCopy: *mut jboolean,
    ) -> *mut jchar,
    pub GetShortArrayElements: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jshortArray,
        isCopy: *mut jboolean,
    ) -> *mut jshort,
    pub GetIntArrayElements: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jintArray,
        isCopy: *mut jboolean,
    ) -> *mut jint,
    pub GetLongArrayElements: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jlongArray,
        isCopy: *mut jboolean,
    ) -> *mut jlong,
    pub GetFloatArrayElements: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jfloatArray,
        isCopy: *mut jboolean,
    ) -> *mut jfloat,
    pub GetDoubleArrayElements: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jdoubleArray,
        isCopy: *mut jboolean,
    ) -> *mut jdouble,

    pub ReleaseBooleanArrayElements: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jbooleanArray,
        elems: *mut jboolean,
        mode: jint,
    ),
    pub ReleaseByteArrayElements: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jbyteArray,
        elems: *mut jbyte,
        mode: jint,
    ),
    pub ReleaseCharArrayElements: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jcharArray,
        elems: *mut jchar,
        mode: jint,
    ),
    pub ReleaseShortArrayElements: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jshortArray,
        elems: *mut jshort,
        mode: jint,
    ),
    pub ReleaseIntArrayElements: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jintArray,
        elems: *mut jint,
        mode: jint,
    ),
    pub ReleaseLongArrayElements: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jlongArray,
        elems: *mut jlong,
        mode: jint,
    ),
    pub ReleaseFloatArrayElements: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jfloatArray,
        elems: *mut jfloat,
        mode: jint,
    ),
    pub ReleaseDoubleArrayElements: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jdoubleArray,
        elems: *mut jdouble,
        mode: jint,
    ),

    pub GetBooleanArrayRegion: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jbooleanArray,
        start: jsize,
        len: jsize,
        buf: *mut jboolean,
    ),
    pub GetByteArrayRegion: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jbyteArray,
        start: jsize,
        len: jsize,
        buf: *mut jbyte,
    ),
    pub GetCharArrayRegion: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jcharArray,
        start: jsize,
        len: jsize,
        buf: *mut jchar,
    ),
    pub GetShortArrayRegion: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jshortArray,
        start: jsize,
        len: jsize,
        buf: *mut jshort,
    ),
    pub GetIntArrayRegion: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jintArray,
        start: jsize,
        len: jsize,
        buf: *mut jint,
    ),
    pub GetLongArrayRegion: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jlongArray,
        start: jsize,
        len: jsize,
        buf: *mut jlong,
    ),
    pub GetFloatArrayRegion: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jfloatArray,
        start: jsize,
        len: jsize,
        buf: *mut jfloat,
    ),
    pub GetDoubleArrayRegion: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jdoubleArray,
        start: jsize,
        len: jsize,
        buf: *mut jdouble,
    ),

    pub SetBooleanArrayRegion: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jbooleanArray,
        start: jsize,
        len: jsize,
        buf: *const jboolean,
    ),
    pub SetByteArrayRegion: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jbyteArray,
        start: jsize,
        len: jsize,
        buf: *const jbyte,
    ),
    pub SetCharArrayRegion: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jcharArray,
        start: jsize,
        len: jsize,
        buf: *const jchar,
    ),
    pub SetShortArrayRegion: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jshortArray,
        start: jsize,
        len: jsize,
        buf: *const jshort,
    ),
    pub SetIntArrayRegion: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jintArray,
        start: jsize,
        len: jsize,
        buf: *const jint,
    ),
    pub SetLongArrayRegion: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jlongArray,
        start: jsize,
        len: jsize,
        buf: *const jlong,
    ),
    pub SetFloatArrayRegion: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jfloatArray,
        start: jsize,
        len: jsize,
        buf: *const jfloat,
    ),
    pub SetDoubleArrayRegion: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jdoubleArray,
        start: jsize,
        len: jsize,
        buf: *const jdouble,
    ),

    pub RegisterNatives: unsafe extern "system" fn(
        env: *mut JNIEnv,
        clazz: jclass,
        methods: *const JNINativeMethod,
        nMethods: jint,
    ) -> jint,
    pub UnregisterNatives: unsafe extern "system" fn(env: *mut JNIEnv, clazz: jclass) -> jint,

    pub MonitorEnter: unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject) -> jint,
    pub MonitorExit: unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject) -> jint,

    pub GetJavaVM: unsafe extern "system" fn(env: *mut JNIEnv, vm: *mut *mut JavaVM) -> jint,

    pub GetStringRegion: unsafe extern "system" fn(
        env: *mut JNIEnv,
        str: jstring,
        start: jsize,
        len: jsize,
        buf: *mut jchar,
    ),
    pub GetStringUTFRegion: unsafe extern "system" fn(
        env: *mut JNIEnv,
        str: jstring,
        start: jsize,
        len: jsize,
        buf: *mut i8,
    ),

    pub GetPrimitiveArrayCritical: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jarray,
        isCopy: *mut jboolean,
    ) -> *mut c_void,
    pub ReleasePrimitiveArrayCritical: unsafe extern "system" fn(
        env: *mut JNIEnv,
        array: jarray,
        carray: *mut c_void,
        mode: jint,
    ),

    pub GetStringCritical: unsafe extern "system" fn(
        env: *mut JNIEnv,
        string: jstring,
        isCopy: *mut jboolean,
    ) -> *const jchar,
    pub ReleaseStringCritical:
        unsafe extern "system" fn(env: *mut JNIEnv, string: jstring, cstring: *const jchar),

    pub NewWeakGlobalRef: unsafe extern "system" fn(env: *mut JNIEnv, obj: jobject) -> jweak,
    pub DeleteWeakGlobalRef: unsafe extern "system" fn(env: *mut JNIEnv, ref_: jweak),

    pub ExceptionCheck: unsafe extern "system" fn(env: *mut JNIEnv) -> jboolean,

    pub NewDirectByteBuffer:
        unsafe extern "system" fn(env: *mut JNIEnv, address: *mut c_void, capacity: jlong) -> jobject,
    pub GetDirectBufferAddress:
        unsafe extern "system" fn(env: *mut JNIEnv, buf: jobject) -> *mut c_void,
    pub GetDirectBufferCapacity:
        unsafe extern "system" fn(env: *mut JNIEnv, buf: jobject) -> jlong,
}

#[repr(C)]
pub struct JNIInvokeInterface_ {
    pub reserved0: *mut c_void,
    pub reserved1: *mut c_void,
    pub reserved2: *mut c_void,

    pub DestroyJavaVM: unsafe extern "system" fn(vm: *mut JavaVM) -> jint,
    pub AttachCurrentThread:
        unsafe extern "system" fn(vm: *mut JavaVM, penv: *mut *mut c_void, args: *mut c_void)
            -> jint,
    pub DetachCurrentThread: unsafe extern "system" fn(vm: *mut JavaVM) -> jint,
    pub GetEnv:
        unsafe extern "system" fn(vm: *mut JavaVM, penv: *mut *mut c_void, version: jint) -> jint,
    pub AttachCurrentThreadAsDaemon:
        unsafe extern "system" fn(vm: *mut JavaVM, penv: *mut *mut c_void, args: *mut c_void)
            -> jint,
}

// ----------------------------------------------------------------------------
// JVMTI definitions
// ----------------------------------------------------------------------------

pub type jvmtiError = jint;
pub const JVMTI_ERROR_NONE: jvmtiError = 0;
pub const JVMTI_ERROR_INVALID_THREAD: jvmtiError = 10;
pub const JVMTI_ERROR_INVALID_FIELDID: jvmtiError = 25;
pub const JVMTI_ERROR_NO_MORE_FRAMES: jvmtiError = 31;
pub const JVMTI_ERROR_OPAQUE_FRAME: jvmtiError = 32;
pub const JVMTI_ERROR_NOT_CURRENT_FRAME: jvmtiError = 33;
pub const JVMTI_ERROR_TYPE_MISMATCH: jvmtiError = 34;
pub const JVMTI_ERROR_NOT_MONITOR_OWNER: jvmtiError = 51;
pub const JVMTI_ERROR_INTERRUPT: jvmtiError = 52;
pub const JVMTI_ERROR_NOT_AVAILABLE: jvmtiError = 98;
pub const JVMTI_ERROR_MUST_POSSESS_CAPABILITY: jvmtiError = 99;
pub const JVMTI_ERROR_NULL_POINTER: jvmtiError = 100;
pub const JVMTI_ERROR_ABSENT_INFORMATION: jvmtiError = 101;
pub const JVMTI_ERROR_INVALID_EVENT_TYPE: jvmtiError = 102;
pub const JVMTI_ERROR_ILLEGAL_ARGUMENT: jvmtiError = 103;
pub const JVMTI_ERROR_OUT_OF_MEMORY: jvmtiError = 110;
pub const JVMTI_ERROR_ACCESS_DENIED: jvmtiError = 111;
pub const JVMTI_ERROR_UNATTACHED_THREAD: jvmtiError = 112;
pub const JVMTI_ERROR_INVALID_ENVIRONMENT: jvmtiError = 113;
pub const JVMTI_ERROR_WRONG_PHASE: jvmtiError = 114;
pub const JVMTI_ERROR_INTERNAL: jvmtiError = 115;
pub const JVMTI_ERROR_UNMODIFIABLE_CLASS: jvmtiError = 116;

pub type jvmtiEventMode = jint;
pub const JVMTI_ENABLE: jvmtiEventMode = 1;
pub const JVMTI_DISABLE: jvmtiEventMode = 0;

pub type jvmtiEvent = jint;
pub const JVMTI_EVENT_CLASS_FILE_LOAD_HOOK: jvmtiEvent = 54;

#[repr(C)]
pub struct jvmtiCapabilities {
    pub bitfield0: u32,
    pub bitfield1: u32,
    pub bitfield2: u32,
    pub bitfield3: u32,
}

impl jvmtiCapabilities {
    pub const fn new() -> Self {
        Self {
            bitfield0: 0,
            bitfield1: 0,
            bitfield2: 0,
            bitfield3: 0,
        }
    }

    #[inline]
    pub fn can_redefine_classes(&self) -> bool {
        (self.bitfield0 & (1 << 9)) != 0
    }

    #[inline]
    pub fn set_can_redefine_classes(&mut self, val: bool) {
        if val {
            self.bitfield0 |= 1 << 9;
        } else {
            self.bitfield0 &= !(1 << 9);
        }
    }

    #[inline]
    pub fn can_redefine_any_class(&self) -> bool {
        (self.bitfield0 & (1 << 21)) != 0
    }

    #[inline]
    pub fn set_can_redefine_any_class(&mut self, val: bool) {
        if val {
            self.bitfield0 |= 1 << 21;
        } else {
            self.bitfield0 &= !(1 << 21);
        }
    }

    #[inline]
    pub fn can_retransform_classes(&self) -> bool {
        (self.bitfield1 & (1 << 5)) != 0
    }

    #[inline]
    pub fn set_can_retransform_classes(&mut self, val: bool) {
        if val {
            self.bitfield1 |= 1 << 5;
        } else {
            self.bitfield1 &= !(1 << 5);
        }
    }

    #[inline]
    pub fn can_retransform_any_class(&self) -> bool {
        (self.bitfield1 & (1 << 6)) != 0
    }

    #[inline]
    pub fn set_can_retransform_any_class(&mut self, val: bool) {
        if val {
            self.bitfield1 |= 1 << 6;
        } else {
            self.bitfield1 &= !(1 << 6);
        }
    }

    #[inline]
    pub fn can_get_source_file_name(&self) -> bool {
        (self.bitfield0 & (1 << 11)) != 0
    }

    #[inline]
    pub fn set_can_get_source_file_name(&mut self, val: bool) {
        if val {
            self.bitfield0 |= 1 << 11;
        } else {
            self.bitfield0 &= !(1 << 11);
        }
    }
}

pub type jvmtiEventClassFileLoadHook = Option<
    unsafe extern "system" fn(
        jvmti_env: *mut jvmtiEnv,
        jni_env: *mut JNIEnv,
        class_being_redefined: jclass,
        loader: jobject,
        name: *const i8,
        protection_domain: jobject,
        class_data_len: jint,
        class_data: *const u8,
        new_class_data_len: *mut jint,
        new_class_data: *mut *mut u8,
    ),
>;

#[repr(C)]
pub struct jvmtiEventCallbacks {
    pub VMInit: *mut c_void,
    pub VMDeath: *mut c_void,
    pub ThreadStart: *mut c_void,
    pub ThreadEnd: *mut c_void,
    pub ClassFileLoadHook: jvmtiEventClassFileLoadHook,
    pub ClassLoad: *mut c_void,
    pub ClassPrepare: *mut c_void,
    pub EntryEmpty: [*mut c_void; 33],
}

#[repr(C)]
pub struct jvmtiClassDefinition {
    pub klass: jclass,
    pub class_byte_count: jint,
    pub class_bytes: *const u8,
}

#[repr(C)]
pub struct jvmtiThreadInfo {
    pub name: *mut i8,
    pub priority: jint,
    pub is_daemon: jboolean,
    pub thread_group: jthreadGroup,
    pub context_class_loader: jobject,
}

pub type jvmtiEnv = *const jvmtiInterface_1_;

#[repr(C)]
pub struct jvmtiInterface_1_ {
    // Slot 1: reserved
    pub reserved1: *mut c_void,
    // Slot 2: SetEventNotificationMode
    pub SetEventNotificationMode: unsafe extern "system" fn(
        env: *mut jvmtiEnv,
        mode: jvmtiEventMode,
        event_type: jvmtiEvent,
        event_thread: jthread,
        ...
    ) -> jvmtiError,
    // Slot 3: GetAllModules
    pub GetAllModules: *mut c_void,
    // Slot 4: GetAllThreads
    pub GetAllThreads: unsafe extern "system" fn(
        env: *mut jvmtiEnv,
        threads_count_ptr: *mut jint,
        threads_ptr: *mut *mut jthread,
    ) -> jvmtiError,
    // Slot 5: SuspendThread
    pub SuspendThread: *mut c_void,
    // Slot 6: ResumeThread
    pub ResumeThread: *mut c_void,
    // Slot 7: StopThread
    pub StopThread: *mut c_void,
    // Slot 8: InterruptThread
    pub InterruptThread: *mut c_void,
    // Slot 9: GetThreadInfo
    pub GetThreadInfo: unsafe extern "system" fn(
        env: *mut jvmtiEnv,
        thread: jthread,
        info_ptr: *mut jvmtiThreadInfo,
    ) -> jvmtiError,
    // Slot 10: GetOwnedMonitorInfo
    pub GetOwnedMonitorInfo: *mut c_void,
    // Slot 11: GetCurrentContendedMonitor
    pub GetCurrentContendedMonitor: *mut c_void,
    // Slot 12: RunAgentThread
    pub RunAgentThread: *mut c_void,
    // Slot 13: GetTopThreadGroups
    pub GetTopThreadGroups: *mut c_void,
    // Slot 14: GetThreadGroupInfo
    pub GetThreadGroupInfo: *mut c_void,
    // Slot 15: GetThreadGroupChildren
    pub GetThreadGroupChildren: *mut c_void,
    // Slot 16: GetFrameCount
    pub GetFrameCount: *mut c_void,
    // Slot 17: GetThreadState
    pub GetThreadState: *mut c_void,
    // Slot 18: GetCurrentThread
    pub GetCurrentThread: *mut c_void,
    // Slot 19: GetFrameLocation
    pub GetFrameLocation: *mut c_void,
    // Slot 20: NotifyFramePop
    pub NotifyFramePop: *mut c_void,
    // Slot 21: GetLocalObject
    pub GetLocalObject: *mut c_void,
    // Slot 22: GetLocalInt
    pub GetLocalInt: *mut c_void,
    // Slot 23: GetLocalLong
    pub GetLocalLong: *mut c_void,
    // Slot 24: GetLocalFloat
    pub GetLocalFloat: *mut c_void,
    // Slot 25: GetLocalDouble
    pub GetLocalDouble: *mut c_void,
    // Slot 26: SetLocalObject
    pub SetLocalObject: *mut c_void,
    // Slot 27: SetLocalInt
    pub SetLocalInt: *mut c_void,
    // Slot 28: SetLocalLong
    pub SetLocalLong: *mut c_void,
    // Slot 29: SetLocalFloat
    pub SetLocalFloat: *mut c_void,
    // Slot 30: SetLocalDouble
    pub SetLocalDouble: *mut c_void,
    // Slot 31: CreateRawMonitor
    pub CreateRawMonitor: *mut c_void,
    // Slot 32: DestroyRawMonitor
    pub DestroyRawMonitor: *mut c_void,
    // Slot 33: RawMonitorEnter
    pub RawMonitorEnter: *mut c_void,
    // Slot 34: RawMonitorExit
    pub RawMonitorExit: *mut c_void,
    // Slot 35: RawMonitorWait
    pub RawMonitorWait: *mut c_void,
    // Slot 36: RawMonitorNotify
    pub RawMonitorNotify: *mut c_void,
    // Slot 37: RawMonitorNotifyAll
    pub RawMonitorNotifyAll: *mut c_void,
    // Slot 38: SetBreakpoint
    pub SetBreakpoint: *mut c_void,
    // Slot 39: ClearBreakpoint
    pub ClearBreakpoint: *mut c_void,
    // Slot 40: GetNamedModule
    pub GetNamedModule: *mut c_void,
    // Slot 41: SetFieldAccessWatch
    pub SetFieldAccessWatch: *mut c_void,
    // Slot 42: ClearFieldAccessWatch
    pub ClearFieldAccessWatch: *mut c_void,
    // Slot 43: SetFieldModificationWatch
    pub SetFieldModificationWatch: *mut c_void,
    // Slot 44: ClearFieldModificationWatch
    pub ClearFieldModificationWatch: *mut c_void,
    // Slot 45: IsModifiableClass
    pub IsModifiableClass: *mut c_void,
    // Slot 46: Allocate
    pub Allocate: unsafe extern "system" fn(
        env: *mut jvmtiEnv,
        size: jlong,
        mem_ptr: *mut *mut u8,
    ) -> jvmtiError,
    // Slot 47: Deallocate
    pub Deallocate: unsafe extern "system" fn(
        env: *mut jvmtiEnv,
        mem: *mut u8,
    ) -> jvmtiError,
    // Slot 48: GetClassSignature
    pub GetClassSignature: unsafe extern "system" fn(
        env: *mut jvmtiEnv,
        klass: jclass,
        signature_ptr: *mut *mut i8,
        generic_ptr: *mut *mut i8,
    ) -> jvmtiError,
    // Slot 49: GetClassStatus
    pub GetClassStatus: unsafe extern "system" fn(
        env: *mut jvmtiEnv,
        klass: jclass,
        status_ptr: *mut jint,
    ) -> jvmtiError,
    // Slot 50: GetSourceFileName
    pub GetSourceFileName: *mut c_void,
    // Slot 51: GetClassModifiers
    pub GetClassModifiers: *mut c_void,
    // Slot 52: GetClassMethods
    pub GetClassMethods: *mut c_void,
    // Slot 53: GetClassFields
    pub GetClassFields: *mut c_void,
    // Slot 54: GetImplementedInterfaces
    pub GetImplementedInterfaces: *mut c_void,
    // Slot 55: IsInterface
    pub IsInterface: *mut c_void,
    // Slot 56: IsArrayClass
    pub IsArrayClass: *mut c_void,
    // Slot 57: GetClassLoader
    pub GetClassLoader: *mut c_void,
    // Slot 58: GetObjectHashCode
    pub GetObjectHashCode: *mut c_void,
    // Slot 59: GetObjectMonitorUsage
    pub GetObjectMonitorUsage: *mut c_void,
    // Slot 60: GetFieldName
    pub GetFieldName: *mut c_void,
    // Slot 61: GetFieldDeclaringClass
    pub GetFieldDeclaringClass: *mut c_void,
    // Slot 62: GetFieldModifiers
    pub GetFieldModifiers: *mut c_void,
    // Slot 63: IsFieldSynthetic
    pub IsFieldSynthetic: *mut c_void,
    // Slot 64: GetMethodName
    pub GetMethodName: *mut c_void,
    // Slot 65: GetMethodDeclaringClass
    pub GetMethodDeclaringClass: *mut c_void,
    // Slot 66: GetMethodModifiers
    pub GetMethodModifiers: *mut c_void,
    // Slot 67: ClearAllFramePops
    pub ClearAllFramePops: *mut c_void,
    // Slot 68: GetMaxLocals
    pub GetMaxLocals: *mut c_void,
    // Slot 69: GetArgumentsSize
    pub GetArgumentsSize: *mut c_void,
    // Slot 70: GetLineNumberTable
    pub GetLineNumberTable: *mut c_void,
    // Slot 71: GetMethodLocation
    pub GetMethodLocation: *mut c_void,
    // Slot 72: GetLocalVariableTable
    pub GetLocalVariableTable: *mut c_void,
    // Slot 73: SetNativeMethodPrefix
    pub SetNativeMethodPrefix: *mut c_void,
    // Slot 74: SetNativeMethodPrefixes
    pub SetNativeMethodPrefixes: *mut c_void,
    // Slot 75: GetBytecodes
    pub GetBytecodes: *mut c_void,
    // Slot 76: IsMethodNative
    pub IsMethodNative: *mut c_void,
    // Slot 77: IsMethodSynthetic
    pub IsMethodSynthetic: *mut c_void,
    // Slot 78: GetLoadedClasses
    pub GetLoadedClasses: unsafe extern "system" fn(
        env: *mut jvmtiEnv,
        class_count_ptr: *mut jint,
        classes_ptr: *mut *mut jclass,
    ) -> jvmtiError,
    // Slot 79: GetClassLoaderClasses
    pub GetClassLoaderClasses: *mut c_void,
    // Slot 80: PopFrame
    pub PopFrame: *mut c_void,
    // Slot 81: ForceEarlyReturnObject
    pub ForceEarlyReturnObject: *mut c_void,
    // Slot 82: ForceEarlyReturnInt
    pub ForceEarlyReturnInt: *mut c_void,
    // Slot 83: ForceEarlyReturnLong
    pub ForceEarlyReturnLong: *mut c_void,
    // Slot 84: ForceEarlyReturnFloat
    pub ForceEarlyReturnFloat: *mut c_void,
    // Slot 85: ForceEarlyReturnDouble
    pub ForceEarlyReturnDouble: *mut c_void,
    // Slot 86: ForceEarlyReturnVoid
    pub ForceEarlyReturnVoid: *mut c_void,
    // Slot 87: RedefineClasses
    pub RedefineClasses: unsafe extern "system" fn(
        env: *mut jvmtiEnv,
        class_count: jint,
        class_definitions: *const jvmtiClassDefinition,
    ) -> jvmtiError,
    // Slot 88: GetVersionNumber
    pub GetVersionNumber: *mut c_void,
    // Slot 89: GetCapabilities
    pub GetCapabilities: *mut c_void,
    // Slot 90: GetSourceDebugExtension
    pub GetSourceDebugExtension: *mut c_void,
    // Slot 91: IsMethodObsolete
    pub IsMethodObsolete: *mut c_void,
    // Slot 92: SuspendThreadList
    pub SuspendThreadList: *mut c_void,
    // Slot 93: ResumeThreadList
    pub ResumeThreadList: *mut c_void,
    // Slot 94: AddModuleReads
    pub AddModuleReads: *mut c_void,
    // Slot 95: AddModuleExports
    pub AddModuleExports: *mut c_void,
    // Slot 96: AddModuleOpens
    pub AddModuleOpens: *mut c_void,
    // Slot 97: AddModuleUses
    pub AddModuleUses: *mut c_void,
    // Slot 98: AddModuleProvides
    pub AddModuleProvides: *mut c_void,
    // Slot 99: IsModifiableModule
    pub IsModifiableModule: *mut c_void,
    // Slot 100: GetAllStackTraces
    pub GetAllStackTraces: *mut c_void,
    // Slot 101: GetThreadListStackTraces
    pub GetThreadListStackTraces: *mut c_void,
    // Slot 102: GetThreadLocalStorage
    pub GetThreadLocalStorage: *mut c_void,
    // Slot 103: SetThreadLocalStorage
    pub SetThreadLocalStorage: *mut c_void,
    // Slot 104: GetStackTrace
    pub GetStackTrace: *mut c_void,
    // Slot 105: reserved
    pub reserved105: *mut c_void,
    // Slot 106: GetTag
    pub GetTag: *mut c_void,
    // Slot 107: SetTag
    pub SetTag: *mut c_void,
    // Slot 108: ForceGarbageCollection
    pub ForceGarbageCollection: *mut c_void,
    // Slot 109: IterateOverObjectsReachableFromObject
    pub IterateOverObjectsReachableFromObject: *mut c_void,
    // Slot 110: IterateOverReachableObjects
    pub IterateOverReachableObjects: *mut c_void,
    // Slot 111: IterateOverHeap
    pub IterateOverHeap: *mut c_void,
    // Slot 112: IterateOverInstancesOfClass
    pub IterateOverInstancesOfClass: *mut c_void,
    // Slot 113: reserved
    pub reserved113: *mut c_void,
    // Slot 114: GetObjectsWithTags
    pub GetObjectsWithTags: *mut c_void,
    // Slot 115: FollowReferences
    pub FollowReferences: *mut c_void,
    // Slot 116: GetObjectMonitors
    pub GetObjectMonitors: *mut c_void,
    // Slot 117: reserved
    pub reserved117: *mut c_void,
    // Slot 118: GetRawMonitorUse
    pub GetRawMonitorUse: *mut c_void,
    // Slot 119: GetRawMonitors
    pub GetRawMonitors: *mut c_void,
    // Slot 120: SetJNIFunctionTable
    pub SetJNIFunctionTable: *mut c_void,
    // Slot 121: GetJNIFunctionTable
    pub GetJNIFunctionTable: *mut c_void,
    // Slot 122: SetEventCallbacks
    pub SetEventCallbacks: unsafe extern "system" fn(
        env: *mut jvmtiEnv,
        callbacks: *const jvmtiEventCallbacks,
        size_of_callbacks: jint,
    ) -> jvmtiError,
    // Slot 123: GenerateEvents
    pub GenerateEvents: *mut c_void,
    // Slot 124: GetExtensionFunctions
    pub GetExtensionFunctions: *mut c_void,
    // Slot 125: GetExtensionEvents
    pub GetExtensionEvents: *mut c_void,
    // Slot 126: SetExtensionEventCallback
    pub SetExtensionEventCallback: *mut c_void,
    // Slot 127: DisposeEnvironment
    pub DisposeEnvironment: *mut c_void,
    // Slot 128: GetErrorName
    pub GetErrorName: unsafe extern "system" fn(
        env: *mut jvmtiEnv,
        error: jvmtiError,
        name_ptr: *mut *mut i8,
    ) -> jvmtiError,
    // Slot 129: GetJLocationFormat
    pub GetJLocationFormat: *mut c_void,
    // Slot 130: GetSystemProperties
    pub GetSystemProperties: *mut c_void,
    // Slot 131: GetSystemProperty
    pub GetSystemProperty: *mut c_void,
    // Slot 132: SetSystemProperty
    pub SetSystemProperty: *mut c_void,
    // Slot 133: GetPhase
    pub GetPhase: *mut c_void,
    // Slot 134: GetCurrentThreadCpuTimerInfo
    pub GetCurrentThreadCpuTimerInfo: *mut c_void,
    // Slot 135: GetCurrentThreadCpuTime
    pub GetCurrentThreadCpuTime: *mut c_void,
    // Slot 136: GetThreadCpuTimerInfo
    pub GetThreadCpuTimerInfo: *mut c_void,
    // Slot 137: GetThreadCpuTime
    pub GetThreadCpuTime: *mut c_void,
    // Slot 138: GetTimerInfo
    pub GetTimerInfo: *mut c_void,
    // Slot 139: GetTime
    pub GetTime: *mut c_void,
    // Slot 140: GetPotentialCapabilities
    pub GetPotentialCapabilities: unsafe extern "system" fn(
        env: *mut jvmtiEnv,
        capabilities_ptr: *mut jvmtiCapabilities,
    ) -> jvmtiError,
    // Slot 141: EstimateCostOfCapabilities
    pub EstimateCostOfCapabilities: *mut c_void,
    // Slot 142: AddCapabilities
    pub AddCapabilities: unsafe extern "system" fn(
        env: *mut jvmtiEnv,
        capabilities_ptr: *const jvmtiCapabilities,
    ) -> jvmtiError,
    // Slot 143: RelinquishCapabilities
    pub RelinquishCapabilities: *mut c_void,
    // Slot 144: GetAvailableProcessors
    pub GetAvailableProcessors: *mut c_void,
    // Slot 145: GetClassVersionNumbers
    pub GetClassVersionNumbers: *mut c_void,
    // Slot 146: GetConstantPool
    pub GetConstantPool: *mut c_void,
    // Slot 147: GetEnvironmentLocalStorage
    pub GetEnvironmentLocalStorage: *mut c_void,
    // Slot 148: SetEnvironmentLocalStorage
    pub SetEnvironmentLocalStorage: *mut c_void,
    // Slot 149: AddToBootstrapClassLoaderSearch
    pub AddToBootstrapClassLoaderSearch: *mut c_void,
    // Slot 150: SetVerboseFlag
    pub SetVerboseFlag: *mut c_void,
    // Slot 151: AddToSystemClassLoaderSearch
    pub AddToSystemClassLoaderSearch: unsafe extern "system" fn(
        env: *mut jvmtiEnv,
        segment: *const i8,
    ) -> jvmtiError,
    // Slot 152: RetransformClasses
    pub RetransformClasses: unsafe extern "system" fn(
        env: *mut jvmtiEnv,
        class_count: jint,
        classes: *const jclass,
    ) -> jvmtiError,
    // Slot 153: GetOwnedMonitorStackDepthInfo
    pub GetOwnedMonitorStackDepthInfo: *mut c_void,
    // Slot 154: GetObjectSize
    pub GetObjectSize: *mut c_void,
    // Slot 155: GetLocalInstance
    pub GetLocalInstance: *mut c_void,
    // Slot 156: SetHeapSamplingInterval
    pub SetHeapSamplingInterval: *mut c_void,
}
