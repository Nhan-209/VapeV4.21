package gg.vape.utils.render;

import gg.vape.Vape;
import gg.vape.utils.render.GpuVendor;
import org.lwjgl.opengl.GL11;

public class OpenGlDeviceInfo {
    public static String h;
    public static GpuVendor T;
    private static int Q;
    public static String m;
    public static String f;

    public static int e() {
        int n = OpenGlDeviceInfo.i();
        return 112;
    }

    public static int i() {
        return Q;
    }

    public static void n() {
        try {
            String string = GL11.glGetString((int)7937);
            String string2 = GL11.glGetString((int)7936);
            String string3 = GL11.glGetString((int)7938);
            h = string2 != null ? string2 : "Unknown Vendor";
            m = string != null ? string : "Unknown GPU";
            f = string3 != null ? string3 : "Unknown Version";
            T = OpenGlDeviceInfo.e(string2);
        }
        catch (Exception exception) {
            Vape.debugLog("Error getting OpenGL: " + exception.getMessage());
        }
    }

    public static void L(int n) {
        Q = n;
    }

    static {
        OpenGlDeviceInfo.L(0);
        T = GpuVendor.UNKNOWN;
        h = null;
        f = null;
        m = null;
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    private static GpuVendor e(String string) {
        if (string == null) {
            return GpuVendor.UNKNOWN;
        }
        switch (string) {
            case "NVIDIA Corporation": {
                return GpuVendor.NVIDIA;
            }
            case "Intel": 
            case "Intel Open Source Technology Center": {
                return GpuVendor.INTEL;
            }
            case "AMD": 
            case "ATI Technologies Inc.": {
                return GpuVendor.AMD;
            }
        }
        return GpuVendor.UNKNOWN;
    }

    public static void Z(StringBuilder stringBuilder) {
        OpenGlDeviceInfo.n();
        stringBuilder.append("GPU Vendor: ").append(T.name()).append(" (").append(h).append(")\n");
        stringBuilder.append("GPU Renderer: ").append(m).append('\n');
        stringBuilder.append("OpenGL Version: ").append(f).append('\n');
    }

    private static void l() {
        try {
            Vape.debugLog("MAX_TEXTURE_SIZE - " + GL11.glGetInteger((int)3379));
            Vape.debugLog("MAX_TEXTURE_UNITS - " + GL11.glGetInteger((int)34930));
            Vape.debugLog("MAX_VERTEX_ATTRIBS - " + GL11.glGetInteger((int)34921));
            Vape.debugLog("MAX_COLOR_ATTACHMENTS - " + GL11.glGetInteger((int)36063));
            Vape.debugLog("MAX_VIEWPORT_WIDTH - " + GL11.glGetInteger((int)3386));
            Vape.debugLog("MAX_VERTEX_UNIFORM_COMPONENTS - " + GL11.glGetInteger((int)35658));
            Vape.debugLog("MAX_FRAGMENT_UNIFORM_COMPONENTS - " + GL11.glGetInteger((int)35657));
        }
        catch (Exception exception) {
            Vape.debugLog("Failed to collect GPU capabilities: " + exception.getMessage());
        }
    }

    public static void P() {
        Vape.debugLog("===== Graphics Information =====");
        Vape.debugLog("Vendor: " + T.name() + " (" + h + ")");
        Vape.debugLog("Device Name: " + m);
        Vape.debugLog("Driver Version: " + f);
        Vape.debugLog("---GPU Capabilities---");
        OpenGlDeviceInfo.l();
        Vape.debugLog("================================");
    }
}

