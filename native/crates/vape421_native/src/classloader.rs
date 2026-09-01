//! Advanced JVM ClassLoader integration for Minecraft 1.7.10 - 1.21.11,
//! Fabric Knot, Forge ModLauncher, and vanilla System ClassLoaders.

use std::ffi::CString;
use std::ptr;
use std::sync::atomic::Ordering;
use vape421_core::jni_sys::*;
use vape421_core::logger::{vape_log, vape_log_pending_exception};
use crate::jvmti_bridge::G_JVMTI;

unsafe fn create_jar_url_objects(
    env: *mut JNIEnv,
    jar_path: &str,
) -> Option<(jclass, jclass, jobject, jobject)> {
    let url_loader_name = CString::new("java/net/URLClassLoader").unwrap();
    let url_name = CString::new("java/net/URL").unwrap();
    let file_name = CString::new("java/io/File").unwrap();
    let uri_name = CString::new("java/net/URI").unwrap();

    let url_loader_class = ((*(*env)).FindClass)(env, url_loader_name.as_ptr());
    let url_class = ((*(*env)).FindClass)(env, url_name.as_ptr());
    let file_class = ((*(*env)).FindClass)(env, file_name.as_ptr());
    let uri_class = ((*(*env)).FindClass)(env, uri_name.as_ptr());

    if url_loader_class.is_null() || url_class.is_null() || file_class.is_null() || uri_class.is_null() {
        vape_log_pending_exception(env, "resolve URLClassLoader classes");
        return None;
    }

    let file_init_sig = CString::new("(Ljava/lang/String;)V").unwrap();
    let to_uri_sig = CString::new("()Ljava/net/URI;").unwrap();
    let to_url_sig = CString::new("()Ljava/net/URL;").unwrap();

    let file_init = ((*(*env)).GetMethodID)(env, file_class, CString::new("<init>").unwrap().as_ptr(), file_init_sig.as_ptr());
    let to_uri = ((*(*env)).GetMethodID)(env, file_class, CString::new("toURI").unwrap().as_ptr(), to_uri_sig.as_ptr());
    let to_url = ((*(*env)).GetMethodID)(env, uri_class, CString::new("toURL").unwrap().as_ptr(), to_url_sig.as_ptr());

    if file_init.is_null() || to_uri.is_null() || to_url.is_null() {
        vape_log_pending_exception(env, "resolve product JAR URL methods");
        return None;
    }

    let c_path = CString::new(jar_path).unwrap();
    let path_str = ((*(*env)).NewStringUTF)(env, c_path.as_ptr());
    let file_obj = ((*(*env)).NewObject)(env, file_class, file_init, path_str);
    let uri_obj = if !file_obj.is_null() {
        ((*(*env)).CallObjectMethod)(env, file_obj, to_uri)
    } else {
        ptr::null_mut()
    };
    let url_obj = if !uri_obj.is_null() {
        ((*(*env)).CallObjectMethod)(env, uri_obj, to_url)
    } else {
        ptr::null_mut()
    };

    if url_obj.is_null() || ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
        vape_log_pending_exception(env, "create product JAR URL");
        return None;
    }

    Some((url_loader_class, url_class, file_obj, url_obj))
}

unsafe fn install_fabric_payload_search(
    env: *mut JNIEnv,
    loader: &mut jobject,
    file_obj: jobject,
) -> i32 {
    let loader_name = CString::new("java/lang/ClassLoader").unwrap();
    let loader_class = ((*(*env)).FindClass)(env, loader_name.as_ptr());
    let load_class_sig = CString::new("(Ljava/lang/String;)Ljava/lang/Class;").unwrap();
    let load_class = if !loader_class.is_null() {
        ((*(*env)).GetMethodID)(env, loader_class, CString::new("loadClass").unwrap().as_ptr(), load_class_sig.as_ptr())
    } else {
        ptr::null_mut()
    };

    let launcher_base_name = CString::new("net.fabricmc.loader.impl.launch.FabricLauncherBase").unwrap();
    let launcher_base_str = ((*(*env)).NewStringUTF)(env, launcher_base_name.as_ptr());
    if load_class.is_null() || launcher_base_str.is_null() {
        vape_log_pending_exception(env, "resolve Fabric ClassLoader methods");
        return -1;
    }

    let launcher_base_class = ((*(*env)).CallObjectMethod)(env, *loader, load_class, launcher_base_str) as jclass;
    if launcher_base_class.is_null() || ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
        if ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
            ((*(*env)).ExceptionClear)(env);
        }
        return 0; // Fabric not present
    }

    let get_launcher_sig = CString::new("()Lnet/fabricmc/loader/impl/launch/FabricLauncher;").unwrap();
    let get_launcher = ((*(*env)).GetStaticMethodID)(
        env,
        launcher_base_class,
        CString::new("getLauncher").unwrap().as_ptr(),
        get_launcher_sig.as_ptr(),
    );
    let launcher = if !get_launcher.is_null() {
        ((*(*env)).CallStaticObjectMethod)(env, launcher_base_class, get_launcher)
    } else {
        ptr::null_mut()
    };

    if launcher.is_null() || ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
        vape_log_pending_exception(env, "FabricLauncherBase.getLauncher");
        return -1;
    }

    let launcher_class = ((*(*env)).GetObjectClass)(env, launcher);
    let get_target_sig = CString::new("()Ljava/lang/ClassLoader;").unwrap();
    let add_to_cp_sig = CString::new("(Ljava/nio/file/Path;[Ljava/lang/String;)V").unwrap();

    let get_target_loader = ((*(*env)).GetMethodID)(
        env,
        launcher_class,
        CString::new("getTargetClassLoader").unwrap().as_ptr(),
        get_target_sig.as_ptr(),
    );
    let add_to_class_path = ((*(*env)).GetMethodID)(
        env,
        launcher_class,
        CString::new("addToClassPath").unwrap().as_ptr(),
        add_to_cp_sig.as_ptr(),
    );

    if get_target_loader.is_null() || add_to_class_path.is_null() {
        vape_log_pending_exception(env, "resolve FabricLauncher methods");
        return -1;
    }

    let target_loader = ((*(*env)).CallObjectMethod)(env, launcher, get_target_loader);
    if target_loader.is_null() || ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
        vape_log_pending_exception(env, "FabricLauncher.getTargetClassLoader");
        return -1;
    }

    let file_class = ((*(*env)).GetObjectClass)(env, file_obj);
    let to_path_sig = CString::new("()Ljava/nio/file/Path;").unwrap();
    let to_path = ((*(*env)).GetMethodID)(env, file_class, CString::new("toPath").unwrap().as_ptr(), to_path_sig.as_ptr());
    let string_class = ((*(*env)).FindClass)(env, CString::new("java/lang/String").unwrap().as_ptr());

    let path_obj = if !to_path.is_null() {
        ((*(*env)).CallObjectMethod)(env, file_obj, to_path)
    } else {
        ptr::null_mut()
    };
    let allowed_prefixes = if !string_class.is_null() {
        ((*(*env)).NewObjectArray)(env, 0, string_class, ptr::null_mut())
    } else {
        ptr::null_mut()
    };

    if path_obj.is_null() || allowed_prefixes.is_null() || ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
        vape_log_pending_exception(env, "create Fabric payload path");
        return -1;
    }

    ((*(*env)).CallVoidMethod)(env, launcher, add_to_class_path, path_obj, allowed_prefixes);
    if ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
        vape_log_pending_exception(env, "FabricLauncher.addToClassPath");
        return -1;
    }

    let event_name = CString::new("gg.vape.event.impl.EventRenderWorldPassExecutorDrain").unwrap();
    let event_str = ((*(*env)).NewStringUTF)(env, event_name.as_ptr());
    let payload_event_class = ((*(*env)).CallObjectMethod)(env, target_loader, load_class, event_str) as jclass;

    let class_class = ((*(*env)).FindClass)(env, CString::new("java/lang/Class").unwrap().as_ptr());
    let get_class_loader_sig = CString::new("()Ljava/lang/ClassLoader;").unwrap();
    let get_class_loader = ((*(*env)).GetMethodID)(
        env,
        class_class,
        CString::new("getClassLoader").unwrap().as_ptr(),
        get_class_loader_sig.as_ptr(),
    );

    let payload_event_loader = if !payload_event_class.is_null() && !get_class_loader.is_null() {
        ((*(*env)).CallObjectMethod)(env, payload_event_class, get_class_loader)
    } else {
        ptr::null_mut()
    };

    if payload_event_loader.is_null()
        || ((*(*env)).ExceptionCheck)(env) == JNI_TRUE
        || ((*(*env)).IsSameObject)(env, payload_event_loader, target_loader) == JNI_FALSE
    {
        if ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
            vape_log_pending_exception(env, "resolve payload event from Fabric target ClassLoader");
        } else {
            vape_log("Fabric payload event used the wrong ClassLoader");
        }
        return -1;
    }

    let retained_target_loader = ((*(*env)).NewLocalRef)(env, target_loader);
    if retained_target_loader.is_null() {
        vape_log_pending_exception(env, "retain Fabric target ClassLoader");
        return -1;
    }

    ((*(*env)).DeleteLocalRef)(env, *loader);
    *loader = retained_target_loader;
    vape_log("appended product JAR to Fabric Knot ClassLoader");
    1
}

unsafe fn install_modular_payload_loader(
    env: *mut JNIEnv,
    loader: &mut jobject,
    url_loader_class: jclass,
    url_class: jclass,
    url_obj: jobject,
) -> i32 {
    let runtime_loader_class = ((*(*env)).GetObjectClass)(env, *loader);
    if runtime_loader_class.is_null() {
        vape_log_pending_exception(env, "resolve runtime ClassLoader type");
        return -1;
    }

    let routes_field_name = CString::new("packageToParentLoader").unwrap();
    let routes_field_sig = CString::new("Ljava/util/Map;").unwrap();
    let package_routes_field = ((*(*env)).GetFieldID)(
        env,
        runtime_loader_class,
        routes_field_name.as_ptr(),
        routes_field_sig.as_ptr(),
    );

    if package_routes_field.is_null() {
        if ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
            ((*(*env)).ExceptionClear)(env);
        }
        let marker_sig = CString::new("(Ljava/lang/ClassLoader;)V").unwrap();
        let modular_marker = ((*(*env)).GetMethodID)(
            env,
            runtime_loader_class,
            CString::new("setFallbackClassLoader").unwrap().as_ptr(),
            marker_sig.as_ptr(),
        );
        if !modular_marker.is_null() {
            vape_log("ModLauncher package routing field is unavailable");
            return -1;
        }
        if ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
            ((*(*env)).ExceptionClear)(env);
        }
        return 0; // Not a modular loader
    }

    let loader_class = ((*(*env)).FindClass)(env, CString::new("java/lang/ClassLoader").unwrap().as_ptr());
    let url_loader_init_sig = CString::new("([Ljava/net/URL;Ljava/lang/ClassLoader;)V").unwrap();
    let load_class_sig = CString::new("(Ljava/lang/String;)Ljava/lang/Class;").unwrap();

    let url_loader_init = ((*(*env)).GetMethodID)(
        env,
        url_loader_class,
        CString::new("<init>").unwrap().as_ptr(),
        url_loader_init_sig.as_ptr(),
    );
    let load_class = ((*(*env)).GetMethodID)(
        env,
        loader_class,
        CString::new("loadClass").unwrap().as_ptr(),
        load_class_sig.as_ptr(),
    );

    let urls = ((*(*env)).NewObjectArray)(env, 1, url_class, ptr::null_mut());
    if loader_class.is_null() || url_loader_init.is_null() || load_class.is_null() || urls.is_null() {
        vape_log_pending_exception(env, "resolve modular payload ClassLoader methods");
        return -1;
    }
    ((*(*env)).SetObjectArrayElement)(env, urls, 0, url_obj);

    let bootstrap_loader = ((*(*env)).NewObject)(env, url_loader_class, url_loader_init, urls, *loader);
    let payload_loader_name = CString::new("gg.vape.runtime.ForgePayloadClassLoader").unwrap();
    let payload_name_str = ((*(*env)).NewStringUTF)(env, payload_loader_name.as_ptr());

    let payload_loader_class = if !bootstrap_loader.is_null() && !payload_name_str.is_null() {
        ((*(*env)).CallObjectMethod)(env, bootstrap_loader, load_class, payload_name_str) as jclass
    } else {
        ptr::null_mut()
    };

    if payload_loader_class.is_null() || ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
        vape_log_pending_exception(env, "load ForgePayloadClassLoader bootstrap class");
        return -1;
    }

    let payload_init_sig = CString::new("([Ljava/net/URL;Ljava/lang/ClassLoader;)V").unwrap();
    let build_routes_sig = CString::new("(Ljava/util/Map;)Ljava/util/Map;").unwrap();

    let payload_loader_init = ((*(*env)).GetMethodID)(
        env,
        payload_loader_class,
        CString::new("<init>").unwrap().as_ptr(),
        payload_init_sig.as_ptr(),
    );
    let build_package_routes = ((*(*env)).GetMethodID)(
        env,
        payload_loader_class,
        CString::new("buildPackageRoutingMap").unwrap().as_ptr(),
        build_routes_sig.as_ptr(),
    );

    let payload_loader = if !payload_loader_init.is_null() {
        ((*(*env)).NewObject)(env, payload_loader_class, payload_loader_init, urls, *loader)
    } else {
        ptr::null_mut()
    };

    if build_package_routes.is_null() || payload_loader.is_null() || ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
        vape_log_pending_exception(env, "create modular payload ClassLoader");
        return -1;
    }

    let previous_package_routes = ((*(*env)).GetObjectField)(env, *loader, package_routes_field);
    let package_routes = ((*(*env)).CallObjectMethod)(
        env,
        payload_loader,
        build_package_routes,
        previous_package_routes,
    );

    if package_routes.is_null() || ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
        vape_log_pending_exception(env, "build ModLauncher payload package routes");
        return -1;
    }

    ((*(*env)).SetObjectField)(env, *loader, package_routes_field, package_routes);
    if ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
        vape_log_pending_exception(env, "install ModLauncher payload package routes");
        return -1;
    }

    let target_event_name = CString::new("gg.vape.event.impl.EventRenderWorldPassExecutorDrain").unwrap();
    let target_event_str = ((*(*env)).NewStringUTF)(env, target_event_name.as_ptr());
    let target_event_class = ((*(*env)).CallObjectMethod)(env, *loader, load_class, target_event_str) as jclass;

    if target_event_class.is_null() || ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
        vape_log_pending_exception(env, "resolve payload event from ModuleClassLoader");
        ((*(*env)).SetObjectField)(env, *loader, package_routes_field, previous_package_routes);
        return -1;
    }

    let payload_event_class = ((*(*env)).CallObjectMethod)(env, payload_loader, load_class, target_event_str) as jclass;
    if payload_event_class.is_null()
        || ((*(*env)).ExceptionCheck)(env) == JNI_TRUE
        || ((*(*env)).IsSameObject)(env, target_event_class, payload_event_class) == JNI_FALSE
    {
        vape_log_pending_exception(env, "verify modular payload visibility");
        ((*(*env)).SetObjectField)(env, *loader, package_routes_field, previous_package_routes);
        return -1;
    }

    ((*(*env)).DeleteLocalRef)(env, *loader);
    *loader = payload_loader;
    vape_log("installed payload package routes on ModLauncher ModuleClassLoader");
    1
}

unsafe fn install_system_payload_search(
    env: *mut JNIEnv,
    loader: jobject,
    jar_path: &str,
) -> i32 {
    let jvmti = G_JVMTI.load(Ordering::SeqCst);
    if jvmti.is_null() {
        vape_log("JVMTI is unavailable for system ClassLoader search");
        return -1;
    }

    let loader_class = ((*(*env)).FindClass)(env, CString::new("java/lang/ClassLoader").unwrap().as_ptr());
    let get_sys_sig = CString::new("()Ljava/lang/ClassLoader;").unwrap();
    let load_sig = CString::new("(Ljava/lang/String;)Ljava/lang/Class;").unwrap();

    let get_system_loader = ((*(*env)).GetStaticMethodID)(
        env,
        loader_class,
        CString::new("getSystemClassLoader").unwrap().as_ptr(),
        get_sys_sig.as_ptr(),
    );
    let load_class = ((*(*env)).GetMethodID)(
        env,
        loader_class,
        CString::new("loadClass").unwrap().as_ptr(),
        load_sig.as_ptr(),
    );

    if get_system_loader.is_null() || load_class.is_null() {
        vape_log_pending_exception(env, "resolve system ClassLoader methods");
        return -1;
    }

    let system_loader = ((*(*env)).CallStaticObjectMethod)(env, loader_class, get_system_loader);
    if system_loader.is_null() || ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
        vape_log_pending_exception(env, "ClassLoader.getSystemClassLoader");
        return -1;
    }

    if ((*(*env)).IsSameObject)(env, loader, system_loader) == JNI_FALSE {
        return 0; // Not system classloader
    }

    let c_path = CString::new(jar_path).unwrap();
    let error = ((*(*jvmti)).AddToSystemClassLoaderSearch)(jvmti, c_path.as_ptr());
    if error != JVMTI_ERROR_NONE {
        vape_log(&format!("AddToSystemClassLoaderSearch failed: {}", error));
        return -1;
    }

    let event_name = CString::new("gg.vape.event.impl.EventRenderWorldPassExecutorDrain").unwrap();
    let event_str = ((*(*env)).NewStringUTF)(env, event_name.as_ptr());
    let event_class = ((*(*env)).CallObjectMethod)(env, loader, load_class, event_str) as jclass;

    if event_class.is_null() || ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
        vape_log_pending_exception(env, "resolve payload event from system ClassLoader");
        return -1;
    }

    vape_log("appended product JAR to system ClassLoader search");
    1
}

pub unsafe fn add_jar_to_loader(
    env: *mut JNIEnv,
    loader: &mut jobject,
    jar_path: &str,
) -> bool {
    let (url_loader_class, url_class, file_obj, url_obj) = match create_jar_url_objects(env, jar_path) {
        Some(res) => res,
        None => return false,
    };

    let fabric_res = install_fabric_payload_search(env, loader, file_obj);
    if fabric_res != 0 {
        return fabric_res > 0;
    }

    if ((*(*env)).IsInstanceOf)(env, *loader, url_loader_class) == JNI_TRUE {
        let add_url_sig = CString::new("(Ljava/net/URL;)V").unwrap();
        let add_url = ((*(*env)).GetMethodID)(
            env,
            url_loader_class,
            CString::new("addURL").unwrap().as_ptr(),
            add_url_sig.as_ptr(),
        );
        if add_url.is_null() {
            vape_log_pending_exception(env, "resolve URLClassLoader.addURL");
            return false;
        }
        ((*(*env)).CallVoidMethod)(env, *loader, add_url, url_obj);
        if ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
            vape_log_pending_exception(env, "URLClassLoader.addURL");
            return false;
        }
        return true;
    }

    let runtime_loader_class = ((*(*env)).GetObjectClass)(env, *loader);
    let delegated_name = CString::new("delegatedClassLoader").unwrap();
    let delegated_sig = CString::new("Lcpw/mods/modlauncher/TransformingClassLoader$DelegatedClassLoader;").unwrap();
    let delegated_field = if !runtime_loader_class.is_null() {
        ((*(*env)).GetFieldID)(env, runtime_loader_class, delegated_name.as_ptr(), delegated_sig.as_ptr())
    } else {
        ptr::null_mut()
    };

    if !delegated_field.is_null() {
        let delegated_loader = ((*(*env)).GetObjectField)(env, *loader, delegated_field);
        if !delegated_loader.is_null()
            && ((*(*env)).IsInstanceOf)(env, delegated_loader, url_loader_class) == JNI_TRUE
        {
            let add_url_sig = CString::new("(Ljava/net/URL;)V").unwrap();
            let add_url = ((*(*env)).GetMethodID)(
                env,
                url_loader_class,
                CString::new("addURL").unwrap().as_ptr(),
                add_url_sig.as_ptr(),
            );
            if !add_url.is_null() {
                ((*(*env)).CallVoidMethod)(env, delegated_loader, add_url, url_obj);
                if ((*(*env)).ExceptionCheck)(env) == JNI_FALSE {
                    vape_log("appended product JAR to ModLauncher delegated ClassLoader");
                    return true;
                }
            }
        }
    } else if ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
        ((*(*env)).ExceptionClear)(env);
    }

    let modular_res = install_modular_payload_loader(env, loader, url_loader_class, url_class, url_obj);
    if modular_res != 0 {
        return modular_res > 0;
    }

    let system_res = install_system_payload_search(env, *loader, jar_path);
    if system_res != 0 {
        return system_res > 0;
    }

    let url_loader_init_sig = CString::new("([Ljava/net/URL;Ljava/lang/ClassLoader;)V").unwrap();
    let url_loader_init = ((*(*env)).GetMethodID)(
        env,
        url_loader_class,
        CString::new("<init>").unwrap().as_ptr(),
        url_loader_init_sig.as_ptr(),
    );
    let urls = ((*(*env)).NewObjectArray)(env, 1, url_class, ptr::null_mut());

    if url_loader_init.is_null() || urls.is_null() {
        vape_log_pending_exception(env, "resolve child URLClassLoader constructor");
        return false;
    }

    ((*(*env)).SetObjectArrayElement)(env, urls, 0, url_obj);
    let child_loader = ((*(*env)).NewObject)(env, url_loader_class, url_loader_init, urls, *loader);

    if child_loader.is_null() || ((*(*env)).ExceptionCheck)(env) == JNI_TRUE {
        vape_log_pending_exception(env, "create child product URLClassLoader");
        return false;
    }

    ((*(*env)).DeleteLocalRef)(env, *loader);
    *loader = child_loader;
    vape_log("created child URLClassLoader for non-URL Minecraft ClassLoader");
    true
}
