package gg.vape.asm.helper;

import gg.vape.Vape;
import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.MappingMethod;
import gg.vape.runtime.NativeBridge;
import gg.vape.runtime.ObfuscatedRuntimeException;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class DescUtils {
    private static final Map<Class<?>, String> z;
    private static int s;

    public static String g(Class<?> clazz, boolean bl) {
        String string = DescUtils.h(clazz);
        if (string != null) {
            return string;
        }
        if (bl) {
            String string2 = MappedClasses.b(clazz).replace('.', '/');
            if (!clazz.isArray()) {
                string2 = "L" + string2 + ";";
            }
            return string2;
        }
        Class<?> clazz2 = clazz;
        return NativeBridge.gcs(clazz2);
    }

    public static int j() {
        return s == 0 ? 77 : 0;
    }

    public static String P(int n) {
        StackTraceElement[] stackTraceElementArray = Thread.currentThread().getStackTrace();
        return n >= 0 && n < stackTraceElementArray.length ? stackTraceElementArray[n].getMethodName() : "";
    }

    public static String U(Class<?> clazz) {
        return DescUtils.g(clazz, false);
    }

    public static int s() {
        return s;
    }

    public static String S(boolean bl, Class<?> clazz, Class<?> ... classArray) {
        StringBuilder stringBuilder = new StringBuilder("(");
        for (Class<?> clazz2 : classArray) {
            stringBuilder.append(DescUtils.g(clazz2, bl));
        }
        return stringBuilder.append(')').append(DescUtils.g(clazz, bl)).toString();
    }

    public static void k(int n) {
        s = n;
    }

    public static void traceStack() {
        Vape.debugLog("");
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            Vape.debugLog(stackTraceElement.toString());
        }
        Vape.debugLog("");
    }

    public static boolean w(MappingMethod mappingMethod, int n) {
        return NativeBridge.gtcf(Thread.currentThread(), mappingMethod.R(), n);
    }

    static {
        if (DescUtils.j() != 0) {
            DescUtils.k(39);
        }
        String[] stringArray = new String[]{"[S", "[I", "[J", "[C", "[F", "[B", "[D", "[Z"};
        z = new HashMap();
        z.put(Boolean.TYPE, "Z");
        z.put(Byte.TYPE, "B");
        z.put(Character.TYPE, "C");
        z.put(Short.TYPE, "S");
        z.put(Integer.TYPE, "I");
        z.put(Long.TYPE, "J");
        z.put(Float.TYPE, "F");
        z.put(Double.TYPE, "D");
        z.put(Void.TYPE, "V");
        z.put(boolean[].class, stringArray[7]);
        z.put(byte[].class, stringArray[5]);
        z.put(char[].class, stringArray[3]);
        z.put(short[].class, stringArray[0]);
        z.put(int[].class, stringArray[1]);
        z.put(long[].class, stringArray[2]);
        z.put(float[].class, stringArray[4]);
        z.put(double[].class, stringArray[6]);
    }

    public static String genMethodDesc(Class<?> clazz, Class<?> ... classArray) {
        return DescUtils.S(false, clazz, classArray);
    }

    public static Class<?> R(Class<?> clazz) {
        return clazz == null ? null : Array.newInstance(clazz, 1).getClass();
    }

    public static boolean J(String string, String string2) {
        if (string == null) {
            return false;
        }
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            if (!string.equals(stackTraceElement.getClassName()) || string2 != null && !string2.equals(stackTraceElement.getMethodName())) continue;
            return true;
        }
        return false;
    }

    public static Class<?> getArrayType(Class<?> clazz) {
        String string;
        Class<?> clazz2 = clazz;
        String string2 = string = "[" + NativeBridge.gcs(clazz2);
        return NativeBridge.gcj(string2);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static String h(Class<?> clazz) {
        return z.get(clazz);
    }
}

