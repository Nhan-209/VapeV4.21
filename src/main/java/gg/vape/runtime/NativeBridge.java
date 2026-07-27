package gg.vape.runtime;

import gg.vape.Vape;
import gg.vape.reflect.Type;
import gg.vape.ui.click.GuiScreenNativeCallbackBridge;
import java.lang.reflect.Method;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class NativeBridge {
    private static boolean v = true;
    static boolean flag;

    public static int ss_3(String string) {
        return 0;
    }

    public static int sts() {
        return 1;
    }

    public static void smdp(int n, int n2) {
        NativeBridge.smd(n, n2);
    }

    public static Object grh() {
        return null;
    }

    public static int mf(int n, int n2, String string) {
        return 0;
    }

    public static void exit(boolean bl) {
        System.out.println("exit " + bl);
    }

    public static byte[] gt(String string) {
        return new byte[0];
    }

    public static void dc() {
    }

    public static String[] gcf(Class clazz) {
        return null;
    }

    public static int gts() {
        return 1;
    }

    public static boolean iv() {
        return v;
    }

    public static double[] trn(double d, double d2, double d3) {
        FloatBuffer floatBuffer = BufferUtils.createFloatBuffer((int)16);
        FloatBuffer floatBuffer2 = BufferUtils.createFloatBuffer((int)16);
        IntBuffer intBuffer = BufferUtils.createIntBuffer((int)16);
        GL11.glGetFloat((int)2982, (FloatBuffer)floatBuffer);
        GL11.glGetFloat((int)2983, (FloatBuffer)floatBuffer2);
        GL11.glGetInteger((int)2978, (IntBuffer)intBuffer);
        FloatBuffer floatBuffer3 = BufferUtils.createFloatBuffer((int)3);
        GLU.gluProject((float)((float)d), (float)((float)d2), (float)((float)d3), (FloatBuffer)floatBuffer, (FloatBuffer)floatBuffer2, (IntBuffer)intBuffer, (FloatBuffer)floatBuffer3);
        double[] dArray = new double[]{floatBuffer3.get(0), floatBuffer3.get(1), floatBuffer3.get(2)};
        return dArray;
    }

    public static boolean gtcf(Object object, int n, int n2) {
        return false;
    }

    public static native String gkn(long var0);

    public static void mb(int n) {
    }

    public static native short gks(int var0);

    public static void rs(int n, double d, double d2) {
        GL11.glClear((int)256);
        GL11.glMatrixMode((int)5889);
        GL11.glLoadIdentity();
        GL11.glOrtho((double)0.0, (double)d, (double)d2, (double)0.0, (double)1000.0, (double)3000.0);
        GL11.glMatrixMode((int)5888);
        GL11.glLoadIdentity();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-2000.0f);
        if (n > 0) {
            if (flag) {
                GL11.glEnable((int)3008);
            }
        } else {
            flag = GL11.glIsEnabled((int)3008);
            if (flag) {
                GL11.glDisable((int)3008);
            }
        }
    }

    public static Class gc(String string) {
        try {
            return Class.forName(string.replace("/", "."));
        }
        catch (Throwable throwable) {
            return null;
        }
    }

    public static String gat() {
        return "";
    }

    public static Object[] gco(Class clazz) {
        return new Object[0];
    }

    public static native byte[] gcb(Class var0);

    public static native void trs(int var0);

    public static String cs(int n) {
        return "";
    }

    public static native byte[] gfb(String var0);

    public static void scm(String string, String string2) {
    }

    public static Class gcj(String string) {
        try {
            return string.startsWith("[") ? Class.forName(string.substring(2, string.length() - 1).replace("/", ".")) : Class.forName(Type.getType(string.substring(1, string.length() - 1)).getClassName());
        }
        catch (Exception exception) {
            return null;
        }
    }

    public static String gp(String string) {
        return "";
    }

    public static void test() {
    }

    public static double gshv2(int n, String string) {
        return 0.0;
    }

    public static String gcs(Class clazz) {
        if (clazz == null) {
            return "";
        }
        return "L" + clazz.getName().replaceAll("\\.", "/") + ";";
    }

    public static native int mvk(int var0, int var1);

    public static double gsh(int n, String string) {
        return 0.0;
    }

    public static void start() throws Throwable {
        try {
            Class.forName("net.minecraftforge.common.ForgeVersion");
            v = false;
        }
        catch (ClassNotFoundException classNotFoundException) {
            v = true;
        }
        Vape vape = new Vape();
        NativeBridge.invokeVoidInit(vape, "loadMappings");
        NativeBridge.invokeVoidInit(vape, "initializeManagers");
    }

    private static void invokeVoidInit(Vape vape, String name) {
        for (Method method : Vape.class.getDeclaredMethods()) {
            if (!method.getName().equals(name) || !method.getReturnType().equals(Void.TYPE)) continue;
            NativeBridge.sce("LOAD " + name);
            method.setAccessible(true);
            try {
                method.invoke(vape, new Object[0]);
                NativeBridge.sce("OK " + name);
            }
            catch (java.lang.reflect.InvocationTargetException wrapper) {
                NativeBridge.logThrowable(name, wrapper.getCause() != null ? wrapper.getCause() : wrapper);
            }
            catch (Throwable other) {
                NativeBridge.logThrowable(name, other);
            }
            return;
        }
        NativeBridge.sce("MISSING void " + name + "()");
    }

    private static void logThrowable(String context, Throwable error) {
        int depth = 0;
        for (Throwable current = error; current != null && depth < 8; current = current.getCause(), ++depth) {
            NativeBridge.sce("EXC " + context + " -> " + current.getClass().getName() + ": " + current.getMessage());
            StackTraceElement[] frames = current.getStackTrace();
            for (int i = 0; i < frames.length && i < 12; ++i) {
                NativeBridge.sce("    at " + frames[i].toString());
            }
        }
    }

    public static double gswv2(int n, String string) {
        return 0.0;
    }

    public static int ds(int n, String string, double d, double d2, int n2) {
        return 0;
    }

    public static double gsw(int n, String string) {
        return 0.0;
    }

    public static void su(String string) {
    }

    public static void cpy(String string) {
    }

    public static long smpm(boolean bl, long l, int n, long l2, long l3) {
        return 0L;
    }

    public static void rl() {
    }

    public static String[] gcm(Class clazz) {
        return new String[0];
    }

    public static int gk() {
        return 0;
    }

    public static native void smd(int var0, int var1);

    public static void rsc() {
    }

    public static void updc(String string, String string2) {
    }

    public static void fs() {
    }

    public static native int dsv2(int var0, String var1, double var2, double var4, int var6, float var7);

    public static int gmv() {
        return 15;
    }

    public static native int ss_2(String var0);

    public static String sp(String string, String string2) {
        return null;
    }

    public static void reload() {
    }

    public static void p(String string) {
        System.out.println(string);
    }

    public static native int scb(Class var0, byte[] var1);

    public static native int mfv2(int var0, int var1, String var2);

    @Deprecated
    public static native void ss(String var0);

    public static boolean[] gls() {
        return new boolean[0];
    }

    public static Class gvc(String string) {
        try {
            return Class.forName(string.replace("/", "."));
        }
        catch (Throwable throwable) {
            return null;
        }
    }

    public static native void sce(String var0);

    public static native Object inv(Method var0, Object var1, Object ... var2);

    public static boolean om(int n, long l, long l2) {
        return GuiScreenNativeCallbackBridge.onNotification(n, l, l2);
    }
}
