package gg.vape.runtime;

import gg.vape.Vape;
import gg.vape.reflect.Type;
import gg.vape.ui.click.GuiScreenNativeCallbackBridge;
import gg.vape.utils.Base64Util;
import java.lang.reflect.Method;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class NativeBridge {
    private static final String DEFAULT_CONFIG_JSON = "{"
            + "\"friends\":[],"
            + "\"profiles\":{},"
            + "\"otherdata\":[{\"frames\":["
            + "{\"title\":\"Combat\",\"x\":32,\"y\":32,\"visible\":true,\"pinned\":false},"
            + "{\"title\":\"Render\",\"x\":144,\"y\":32,\"visible\":true,\"pinned\":false},"
            + "{\"title\":\"Utility\",\"x\":256,\"y\":32,\"visible\":true,\"pinned\":false},"
            + "{\"title\":\"World\",\"x\":368,\"y\":32,\"visible\":true,\"pinned\":false},"
            + "{\"title\":\"Inventory\",\"x\":480,\"y\":32,\"visible\":true,\"pinned\":false},"
            + "{\"title\":\"Favorites\",\"x\":592,\"y\":32,\"visible\":true,\"pinned\":false},"
            + "{\"title\":\"Settings\",\"x\":32,\"y\":32,\"visible\":false,\"pinned\":false},"
            + "{\"title\":\"ModuleSearch\",\"x\":32,\"y\":32,\"visible\":false,\"pinned\":false}"
            + "]}]}";
    private static boolean forgeAbsent = true;
    static boolean alphaTestWasEnabled;

    public static int ss_3(String value) {
        return 0;
    }

    public static int sts() {
        return 1;
    }

    public static void smdp(int mode, int value) {
        NativeBridge.smd(mode, value);
    }

    //GetRenderHandler
    //Java Layer Unused
    public static Object grh() {
        return null;
    }

    //MakeFont
    //Java Layer Unused
    public static int mf(int fontId, int style, String text) {
        return 0;
    }

    //Controller Exit
    public static void exit(boolean forced) {
        System.out.println("exit " + forced);
    }

    //GetTexture
    //Java Layer Unused
    public static byte[] gt(String key) {
        return new byte[0];
    }

    //Disconnect
    //Disconnect dll with loader after finished loading
    public static void dc() {
    }

    //GetClassFields
    //Java Layer Unused
    public static String[] gcf(Class<?> targetClass) {
        if (targetClass == null) {
            return new String[0];
        }
        java.lang.reflect.Field[] fields = targetClass.getDeclaredFields();
        String[] names = new String[fields.length];
        for (int index = 0; index < fields.length; ++index) {
            names[index] = fields[index].getName();
        }
        return names;
    }

    public static int gts() {
        return 1;
    }

    public static boolean isForgeAbsent() {
        return forgeAbsent;
    }

    //Translate
    public static double[] trn(double worldX, double worldY, double worldZ) {
        FloatBuffer modelViewMatrix = BufferUtils.createFloatBuffer(16);
        FloatBuffer projectionMatrix = BufferUtils.createFloatBuffer(16);
        IntBuffer viewport = BufferUtils.createIntBuffer(16);
        GL11.glGetFloat(2982, modelViewMatrix);
        GL11.glGetFloat(2983, projectionMatrix);
        GL11.glGetInteger(2978, viewport);
        FloatBuffer screenPosition = BufferUtils.createFloatBuffer(3);
        GLU.gluProject((float)worldX, (float)worldY, (float)worldZ,
                modelViewMatrix, projectionMatrix, viewport, screenPosition);
        return new double[]{screenPosition.get(0), screenPosition.get(1), screenPosition.get(2)};
    }

    public static boolean gtcf(Object target, int index, int flags) {
        return false;
    }

    //GetKeyName
    public static native String gkn(long keyCode);

    //MessageBox
    public static void mb(int messageCode) {
        //can't understand why manthe would print error code instead of any meaningful text
    }

    //GetKeyState
    public static native short gks(int keyCode);

    //RenderState
    public static void rs(int phase, double width, double height) {
        GL11.glClear((int)256);
        GL11.glMatrixMode((int)5889);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0, width, height, 0.0, 1000.0, 3000.0);
        GL11.glMatrixMode((int)5888);
        GL11.glLoadIdentity();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-2000.0f);
        if (phase > 0) {
            if (alphaTestWasEnabled) {
                GL11.glEnable((int)3008);
            }
        } else {
            alphaTestWasEnabled = GL11.glIsEnabled((int)3008);
            if (alphaTestWasEnabled) {
                GL11.glDisable((int)3008);
            }
        }
    }

    //GetClass
    public static Class<?> gc(String internalName) {
        try {
            return Class.forName(internalName.replace("/", "."));
        }
        catch (Throwable throwable) {
            return null;
        }
    }

    //GetAccessToken
    public static native String gat();

    //GetClassObjects
    //Java Layer Unused
    //since I hate jvmti, so I'm not going to implement this
    public static Object[] gco(Class<?> targetClass) {
        return new Object[0];
    }

    //GetClassBytes
    public static native byte[] gcb(Class<?> targetClass);

    public static native void trs(int state);

    //CopyString
    //Java Layer Unused
    public static String cs(int stringId) {
        return "";
    }

    public static native byte[] gfb(String name);

    public static void scm(String sourceName, String mappedName) {
    }

    //GetClassJava
    public static Class<?> gcj(String descriptor) {
        try {
            return descriptor.startsWith("[")
                    ? Class.forName(descriptor.substring(2, descriptor.length() - 1).replace("/", "."))
                    : Class.forName(Type.getType(descriptor.substring(1, descriptor.length() - 1)).getClassName());
        }
        catch (Exception exception) {
            return null;
        }
    }

    //GetProfile
    public static String gp(String key) {
        if ("all".equals(key)) {
            return Base64Util.encodeUtf8Base64(DEFAULT_CONFIG_JSON);
        }
        return "";
    }

    public static void test() {
    }

    //GetStringHightV2
    //Java Layer Unused
    public static double gshv2(int fontId, String text) {
        return 0.0;
    }

    //GetClassSignature
    public static String gcs(Class<?> targetClass) {
        if (targetClass == null) {
            return "";
        }
        return Type.getDescriptor(targetClass);
    }

    //MapVirtualKey
    public static native int mvk(int virtualKey, int scanCode);

    //GetStringHeight
    //Java Layer Unused
    public static double gsh(int fontId, String text) {
        return 0.0;
    }

    public static void start() throws Throwable {
        try {
            Class.forName("net.minecraftforge.common.ForgeVersion");
            forgeAbsent = false;
        }
        catch (ClassNotFoundException classNotFoundException) {
            forgeAbsent = true;
        }
        Vape vape = new Vape();
        NativeBridge.invokeVoidInit(vape, "loadMappings");
        NativeBridge.sce("LOAD initAccountInfo");
        if (!vape.initAccountInfo()) {
            NativeBridge.sce("WARN initAccountInfo; continuing without account information");
        } else {
            NativeBridge.sce("OK initAccountInfo");
        }
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
            for (int frameIndex = 0; frameIndex < frames.length && frameIndex < 12; ++frameIndex) {
                NativeBridge.sce("    at " + frames[frameIndex].toString());
            }
        }
    }

    //GetStringWidthV2
    //Java Layer Unused
    public static double gswv2(int fontId, String text) {
        return 0.0;
    }

    //DrawString
    //Java Layer Unused
    public static int ds(int fontId, String text, double x, double y, int color) {
        return 0;
    }

    //GetStringWidth
    //Java Layer Unused
    public static double gsw(int fontId, String text) {
        return 0.0;
    }

    //SetUsername
    //Not available under current recovery project
    public static void su(String username) {
    }

    //ClipboardCopy
    public static native void cpy(String text);

    public static long smpm(boolean pressed, long windowHandle, int button,
                            long cursorPosition, long extraInfo) {
        return 0L;
    }

    //Reload
    //Java Layer Unused
    public static void rl() {
    }

    //GetClassMethods
    //Java Layer Unused
    public static String[] gcm(Class<?> targetClass) {
        if (targetClass == null) {
            return new String[0];
        }
        Method[] methods = targetClass.getDeclaredMethods();
        String[] names = new String[methods.length];
        for (int index = 0; index < methods.length; ++index) {
            names[index] = methods[index].getName();
        }
        return names;
    }

    //GetKey
    //Java Layer Unused
    public static int gk() {
        return 0;
    }

    //SendMouseDown
    public static native void smd(int mode, int value);

    public static void rsc() {
    }

    //UpdateDiscord
    public static void updc(String serverDescription, String clientDescription) {
    }

    public static void fs() {
    }

    //DrawStringV2
    //Java Layer Unused
    public static native int dsv2(int fontId, String text, double x, double y,
                                  int color, float scale);

    //GetMinorVersion
    public static int gmv() {
        return 15;
    }

    public static native int ss_2(String value);

    public static String sp(String key, String value) {
        return null;
    }

    public static void reload() {
    }

    public static void printLog(String message) {
        System.out.println(message);
    }

    //SetClassBytes
    public static native int scb(Class<?> targetClass, byte[] bytecode);

    //MakeFontV2
    //Java Layer Unused
    public static native int mfv2(int fontId, int style, String text);

    //SaveSettings
    @Deprecated
    public static native void ss(String value);

    public static boolean[] gls() {
        return new boolean[0];
    }

    //GetVanillaClas
    public static Class<?> gvc(String internalName) {
        try {
            return Class.forName(internalName.replace("/", "."));
        }
        catch (Throwable throwable) {
            return null;
        }
    }

    //SendClientError
    public static native void sce(String message);

    public static native Object inv(Method method, Object target, Object ... arguments);

    public static boolean om(int eventId, long firstArgument, long secondArgument) {
        return GuiScreenNativeCallbackBridge.onNotification(eventId, firstArgument, secondArgument);
    }
}
