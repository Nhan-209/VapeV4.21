package gg.vape.mapping.runtime;

import gg.vape.Vape;
import gg.vape.mapping.runtime.MemberLookupSignature;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public class MemberNameRemapTable {
    private final HashMap<Class<?>, Map<String, MemberLookupSignature>> fieldMappings = new HashMap();
    private static String[] controlFlowState;
    private final HashMap<Class<?>, Map<String, MemberLookupSignature>> methodMappings = new HashMap();
    private static final String initializerMethodName;

    public static String[] C() {
        return controlFlowState;
    }

    public void G(Class<?> clazz, String string, String string2, Class<?> clazz2) {
        this.L(clazz, string, string2, null, clazz2);
    }

    public void f(Class<?> clazz, String string, String string2, Class<?> clazz2, Class<?> ... classArray) {
        this.z(clazz, string, string2, null, clazz2, classArray);
    }

    public void L(Class<?> clazz, String string, String string2, Boolean bl, Class<?> clazz2) {
        this.fieldMappings.compute(clazz, (arg_0, arg_1) -> MemberNameRemapTable.lambda$setFieldMapping$0(string, string2, bl, clazz2, arg_0, arg_1));
    }

    public void f(Class<?> clazz, String string, String string2, boolean bl) {
        this.L(clazz, string, string2, bl, null);
    }

    public boolean v() {
        return !Vape.INSTANCE.isVanillaMinecraftPresent();
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    public void z(Class<?> clazz, String string, String string2, Boolean bl, Class<?> clazz2, Class<?> ... classArray) {
        this.methodMappings.compute(clazz, (arg_0, arg_1) -> MemberNameRemapTable.lambda$setMethodMapping$1(string, string2, bl, clazz2, classArray, arg_0, arg_1));
    }

    @Nullable
    public MemberLookupSignature U(Class<?> clazz, String string) {
        Map<String, MemberLookupSignature> map = this.fieldMappings.get(clazz);
        if (map == null) {
            return null;
        }
        return map.getOrDefault(string, null);
    }

    public void B(Class<?> clazz, String string, String string2) {
        this.L(clazz, string, string2, null, null);
    }

    public void T() {
        ArrayList<Class<?>> arrayList = new ArrayList<Class<?>>();
        for (Class<?> clazz = this.getClass(); clazz != null && MemberNameRemapTable.class.isAssignableFrom(clazz) && clazz != MemberNameRemapTable.class; clazz = clazz.getSuperclass()) {
            arrayList.add(clazz);
        }
        Collections.reverse(arrayList);
        for (Class<?> clazz : arrayList) {
            Method[] methodArray;
            for (Method method : methodArray = clazz.getDeclaredMethods()) {
                if (method.getParameterCount() != 0 || initializerMethodName.equals(method.getName())) continue;
                method.setAccessible(true);
                try {
                    method.invoke(this, new Object[0]);
                }
                catch (Exception exception) {
                    Vape.logThrowable(exception);
                }
            }
        }
    }

    @Nullable
    public MemberLookupSignature B(Class<?> clazz, String string) {
        Map<String, MemberLookupSignature> map = this.methodMappings.get(clazz);
        if (map == null) {
            return null;
        }
        return map.getOrDefault(string, null);
    }

    public static void w(String[] stringArray) {
        controlFlowState = stringArray;
    }

    private static Map lambda$setMethodMapping$1(String string, String string2, Boolean bl, Class clazz, Class[] classArray, Class clazz2, Map hashMap) {
        if (hashMap == null) {
            hashMap = new HashMap<String, MemberLookupSignature>();
        }
        hashMap.put(string, new MemberLookupSignature(string2, bl, clazz, classArray));
        return hashMap;
    }

    public void t(Class<?> clazz, String string, String string2) {
        this.f(clazz, string, string2, null, new Class[0]);
    }

    static {
        MemberNameRemapTable.w(null);
        initializerMethodName = "initialize";
    }

    public void b(Class<?> clazz, String string, String string2, boolean bl) {
        this.z(clazz, string, string2, bl, null, new Class[0]);
    }

    private static Map lambda$setFieldMapping$0(String string, String string2, Boolean bl, Class clazz, Class clazz2, Map hashMap) {
        if (hashMap == null) {
            hashMap = new HashMap<String, MemberLookupSignature>();
        }
        hashMap.put(string, new MemberLookupSignature(string2, bl, clazz, new Class[0]));
        return hashMap;
    }
}
