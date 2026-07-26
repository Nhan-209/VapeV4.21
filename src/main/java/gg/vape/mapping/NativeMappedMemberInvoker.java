package gg.vape.mapping;

import gg.vape.reflect.Reflections;
import gg.vape.runtime.NativeBridge;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class NativeMappedMemberInvoker {
    public static void a(int var0, Class cls, String internalName, String name, String desc, String mappedDesc, boolean var6) throws Exception {
        Reflections.getMethod(var0, cls, name, desc, false, var6);
    }

    public static void b(int id, Class<?> cls, String name, String desc, boolean isStatic) throws Exception {
        Reflections.getMethod(id, cls, name, desc, true, isStatic);
    }

    public static void t(int id, Class cls, String internalName, String name, String desc, String mappedDesc, boolean isStatic) throws Exception {
        Reflections.getField(id, cls, name, desc, true, isStatic);
    }

    public static void u(int id, Class<?> cls, String name, String desc, boolean isStatic) throws Exception {
        Reflections.getField(id, cls, name, desc, false, isStatic);
    }

    public static void c(int id, Object instance, Object ... params) {
        Reflections.invokeMethod(id, instance, Void.TYPE, params);
    }

    public static boolean d(int id, Object instance, Object ... params) {
        return Reflections.invokeMethod(id, instance, Boolean.TYPE, params);
    }

    public static char e(int id, Object instance, Object ... params) {
        return Reflections.invokeMethod(id, instance, Character.TYPE, params).charValue();
    }

    public static short f(int id, Object instance, Object ... params) {
        return Reflections.invokeMethod(id, instance, Short.TYPE, params);
    }

    public static int g(int id, Object instance, Object ... params) {
        return Reflections.invokeMethod(id, instance, Integer.TYPE, params);
    }

    public static long h(int id, Object instance, Object ... params) {
        return Reflections.invokeMethod(id, instance, Long.TYPE, params);
    }

    public static float i(int id, Object instance, Object ... params) {
        return Reflections.invokeMethod(id, instance, Float.TYPE, params).floatValue();
    }

    public static double j(int id, Object instance, Object ... params) {
        return Reflections.invokeMethod(id, instance, Double.TYPE, params);
    }

    public static Object k(int id, Object instance, Object ... params) {
        return Reflections.invokeMethod(id, instance, Object.class, params);
    }

    public static byte ddd(int id, Object instance, Object ... params) {
        return Reflections.invokeMethod(id, instance, Byte.TYPE, params);
    }

    public static boolean[] l(int id, Object instance, Object ... params) {
        return Reflections.invokeMethod(id, instance, boolean[].class, params);
    }

    public static char[] m(int id, Object instance, Object ... params) {
        return Reflections.invokeMethod(id, instance, char[].class, params);
    }

    public static short[] n(int id, Object instance, Object ... params) {
        return Reflections.invokeMethod(id, instance, short[].class, params);
    }

    public static int[] o(int id, Object instance, Object ... params) {
        return Reflections.invokeMethod(id, instance, int[].class, params);
    }

    public static long[] p(int id, Object instance, Object ... params) {
        return Reflections.invokeMethod(id, instance, long[].class, params);
    }

    public static float[] q(int id, Object instance, Object ... params) {
        return Reflections.invokeMethod(id, instance, float[].class, params);
    }

    public static double[] r(int id, Object instance, Object ... params) {
        return Reflections.invokeMethod(id, instance, double[].class, params);
    }

    public static Object[] s(int id, Object instance, Object ... params) {
        return Reflections.invokeMethod(id, instance, Object[].class, params);
    }

    public static byte[] eee(int id, Object instance, Object ... params) {
        return Reflections.invokeMethod(id, instance, byte[].class, params);
    }

    public static void bbb(int id, Object instance, Object ... params) {
        Reflections.invokeMethod(id, instance, Void.TYPE, params);
    }

    public static Object ccc(int id, Class<?> cls, Object ... params) throws Exception {
        return Reflections.invokeConstructor(id, params);
    }

    public static boolean v(int id, Object instance) throws Exception {
        return Reflections.fieldMap.get(id).getBoolean(instance);
    }

    public static char w(int id, Object instance) throws Exception {
        return Reflections.fieldMap.get(id).getChar(instance);
    }

    public static short x(int id, Object instance) throws Exception {
        return Reflections.fieldMap.get(id).getShort(instance);
    }

    public static int y(int id, Object instance) {
        try {
            return Reflections.fieldMap.get(id).getInt(instance);
        }
        catch (IllegalAccessException exception) {
            return NativeMappedMemberInvoker.<RuntimeException, Integer>sneakyThrow(exception);
        }
    }

    public static long z(int id, Object instance) throws Exception {
        return Reflections.fieldMap.get(id).getLong(instance);
    }

    public static float aa(int id, Object instance) throws Exception {
        Field field = Reflections.fieldMap.get(id);
        return field.getType() == Float.TYPE ? field.getFloat(instance) : (float)field.getDouble(instance);
    }

    public static double bb(int id, Object instance) throws Exception {
        return Reflections.fieldMap.get(id).getDouble(instance);
    }

    public static byte fff(int id, Object instance) throws Exception {
        return Reflections.fieldMap.get(id).getByte(instance);
    }

    public static Object cc(int id, Object instance) {
        return Reflections.getField(id, instance);
    }

    public static boolean[] dd(int id, Object instance) throws Exception {
        return (boolean[])Reflections.getField(id, instance);
    }

    public static char[] ee(int id, Object instance) {
        return (char[])Reflections.getField(id, instance);
    }

    public static short[] ff(int id, Object instance) throws Exception {
        return (short[])Reflections.getField(id, instance);
    }

    public static int[] gg(int id, Object instance) throws Exception {
        return (int[])Reflections.getField(id, instance);
    }

    public static long[] hh(int id, Object instance) throws Exception {
        return (long[])Reflections.getField(id, instance);
    }

    public static float[] ii(int id, Object instance) throws Exception {
        return (float[])Reflections.getField(id, instance);
    }

    public static double[] jj(int id, Object instance) throws Exception {
        return (double[])Reflections.getField(id, instance);
    }

    public static Object[] kk(int id, Object instance) {
        return (Object[])Reflections.getField(id, instance);
    }

    public static byte[] hhh(int id, Object instance) {
        return (byte[])Reflections.getField(id, instance);
    }

    public static void ll(int id, Object instance, boolean value) throws Exception {
        Reflections.fieldMap.get(id).setBoolean(instance, value);
    }

    public static void mm(int id, Object instance, char value) throws Exception {
        Reflections.fieldMap.get(id).setChar(instance, value);
    }

    public static void nn(int id, Object instance, short value) throws Exception {
        Reflections.fieldMap.get(id).setShort(instance, value);
    }

    public static void oo(int id, Object instance, int value) throws Exception {
        Reflections.fieldMap.get(id).setInt(instance, value);
    }

    public static void pp(int id, Object instance, long value) throws Exception {
        Reflections.fieldMap.get(id).setLong(instance, value);
    }

    public static void qq(int id, Object instance, float value) throws Exception {
        Reflections.fieldMap.get(id).setFloat(instance, value);
    }

    public static void rr(int id, Object instance, double value) {
        try {
            Reflections.fieldMap.get(id).setDouble(instance, value);
        }
        catch (IllegalAccessException exception) {
            NativeMappedMemberInvoker.<RuntimeException, Void>sneakyThrow(exception);
        }
    }

    public static void ggg(int id, Object instance, byte value) throws Exception {
        Reflections.fieldMap.get(id).setByte(instance, value);
    }

    public static void ss(int id, Object instance, Object value) throws Exception {
        Reflections.setObjectField(id, instance, value);
    }

    public static void tt(int id, Object instance, boolean[] value) throws Exception {
        Reflections.setObjectField(id, instance, value);
    }

    public static void uu(int id, Object instance, char[] value) throws Exception {
        Reflections.setObjectField(id, instance, value);
    }

    public static void vv(int id, Object instance, short[] value) throws Exception {
        Reflections.setObjectField(id, instance, value);
    }

    public static void ww(int id, Object instance, int[] value) throws Exception {
        Reflections.setObjectField(id, instance, value);
    }

    public static void xx(int id, Object instance, long[] value) throws Exception {
        Reflections.setObjectField(id, instance, value);
    }

    public static void yy(int id, Object instance, float[] value) throws Exception {
        Reflections.setObjectField(id, instance, value);
    }

    public static void zz(int id, Object instance, double[] value) throws Exception {
        Reflections.setObjectField(id, instance, value);
    }

    public static void aaa(int id, Object instance, Object[] value) throws Exception {
        Reflections.setObjectField(id, instance, value);
    }

    public static void iii(int id, Object instance, byte[] value) throws Exception {
        Reflections.setObjectField(id, instance, value);
    }

    public static Object jjj(int n, Object object, Object ... objectArray) {
        Method method = Reflections.methodMap.get(n);
        return NativeBridge.inv(method, object, objectArray);
    }

    public static String gfn(int id) {
        return Reflections.fieldMap.get(id).getName();
    }

    public static String gmn(int id) {
        return Reflections.methodMap.get(id).getName();
    }

    @SuppressWarnings("unchecked")
    private static <E extends Throwable, T> T sneakyThrow(Throwable throwable) throws E {
        throw (E)throwable;
    }
}
