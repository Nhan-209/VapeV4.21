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
        (self.bitfield0 & (1 << 2)) != 0
    }

    #[inline]
    pub fn set_can_redefine_classes(&mut self, val: bool) {
        if val {
            self.bitfield0 |= 1 << 2;
        } else {
            self.bitfield0 &= !(1 << 2);
        }
    }

    #[inline]
    pub fn can_redefine_any_class(&self) -> bool {
        (self.bitfield0 & (1 << 3)) != 0
    }

    #[inline]
    pub fn set_can_redefine_any_class(&mut self, val: bool) {
        if val {
            self.bitfield0 |= 1 << 3;
        } else {
            self.bitfield0 &= !(1 << 3);
        }
    }

    #[inline]
    pub fn can_retransform_classes(&self) -> bool {
        (self.bitfield0 & (1 << 16)) != 0
    }

    #[inline]
    pub fn set_can_retransform_classes(&mut self, val: bool) {
        if val {
            self.bitfield0 |= 1 << 16;
        } else {
            self.bitfield0 &= !(1 << 16);
        }
    }

    #[inline]
    pub fn can_retransform_any_class(&self) -> bool {
        (self.bitfield0 & (1 << 17)) != 0
    }

    #[inline]
    pub fn set_can_retransform_any_class(&mut self, val: bool) {
        if val {
            self.bitfield0 |= 1 << 17;
        } else {
            self.bitfield0 &= !(1 << 17);
        }
    }

    #[inline]
    pub fn can_get_source_file_name(&self) -> bool {
        (self.bitfield0 & (1 << 7)) != 0
    }

    #[inline]
    pub fn set_can_get_source_file_name(&mut self, val: bool) {
        if val {
            self.bitfield0 |= 1 << 7;
        } else {
            self.bitfield0 &= !(1 << 7);
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
    pub reserved1: *mut c_void,

    pub SetEventNotificationMode: unsafe extern "system" fn(
        env: *mut jvmtiEnv,
        mode: jvmtiEventMode,
        event_type: jvmtiEvent,
        event_thread: jthread,
        ...
    ) -> jvmtiError,

    pub reserved3: *mut c_void,
    pub GetAllThreads: unsafe extern "system" fn(
        env: *mut jvmtiEnv,
        threads_count_ptr: *mut jint,
        threads_ptr: *mut *mut jthread,
    ) -> jvmtiError,
    pub SuspendThread: *mut c_void,
    pub ResumeThread: *mut c_void,
    pub StopThread: *mut c_void,
    pub InterruptThread: *mut c_void,
    pub GetThreadInfo: unsafe extern "system" fn(
        env: *mut jvmtiEnv,
        thread: jthread,
        info_ptr: *mut jvmtiThreadInfo,
    ) -> jvmtiError,

    pub reserved9: [*mut c_void; 22],

    pub GetLoadedClasses: unsafe extern "system" fn(
        env: *mut jvmtiEnv,
        class_count_ptr: *mut jint,
        classes_ptr: *mut *mut jclass,
    ) -> jvmtiError,
    pub GetClassLoaderClasses: *mut c_void,
    pub GetClassSignature: unsafe extern "system" fn(
        env: *mut jvmtiEnv,
        klass: jclass,
        signature_ptr: *mut *mut i8,
        generic_ptr: *mut *mut i8,
    ) -> jvmtiError,
    pub GetClassStatus: unsafe extern "system" fn(
        env: *mut jvmtiEnv,
        klass: jclass,
        status_ptr: *mut jint,
    ) -> jvmtiError,

    pub reserved35: [*mut c_void; 10],

    pub RedefineClasses: unsafe extern "system" fn(
        env: *mut jvmtiEnv,
        class_count: jint,
        class_definitions: *const jvmtiClassDefinition,
    ) -> jvmtiError,

    pub reserved46: [*mut c_void; 41],

    pub Allocate: unsafe extern "system" fn(
        env: *mut jvmtiEnv,
        size: jlong,
        mem_ptr: *mut *mut u8,
    ) -> jvmtiError,
    pub Deallocate:
        unsafe extern "system" fn(env: *mut jvmtiEnv, mem: *mut u8) -> jvmtiError,

    pub reserved89: [*mut c_void; 2],

    pub GetPotentialCapabilities: unsafe extern "system" fn(
        env: *mut jvmtiEnv,
        capabilities_ptr: *mut jvmtiCapabilities,
    ) -> jvmtiError,
    pub AddCapabilities: unsafe extern "system" fn(
        env: *mut jvmtiEnv,
        capabilities_ptr: *const jvmtiCapabilities,
    ) -> jvmtiError,
    pub RelinquishCapabilities: *mut c_void,
    pub GetCapabilities: *mut c_void,

    pub reserved95: [*mut c_void; 7],

    pub SetEventCallbacks: unsafe extern "system" fn(
        env: *mut jvmtiEnv,
        callbacks: *const jvmtiEventCallbacks,
        size_of_callbacks: jint,
    ) -> jvmtiError,

    pub reserved103: [*mut c_void; 6],

    pub GetErrorName: unsafe extern "system" fn(
        env: *mut jvmtiEnv,
        error: jvmtiError,
        name_ptr: *mut *mut i8,
    ) -> jvmtiError,

    pub reserved110: [*mut c_void; 4],

    pub AddToSystemClassLoaderSearch: unsafe extern "system" fn(
        env: *mut jvmtiEnv,
        segment: *const i8,
    ) -> jvmtiError,
    pub AddToBootstrapClassLoaderSearch: *mut c_void,

    pub reserved116: [*mut c_void; 3],

    pub RetransformClasses: unsafe extern "system" fn(
        env: *mut jvmtiEnv,
        class_count: jint,
        classes: *const jclass,
    ) -> jvmtiError,
}
