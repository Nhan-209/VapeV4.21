package gg.vape.utils.render;

import gg.vape.Vape;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GlStateManagerTexGenState;

public class GlTextureUnitState {
    private static boolean k;
    private static final int i;
    private static final String b;
    private static boolean v;
    private static final int[] Z;

    static {
        b = "SamplerFix: GL33 sampler support enabled via hook";
        long l = -700399992203902968L;
        i = (int)l;
        v = false;
        k = false;
        Z = new int[8];
    }

    public static void V() {
        GlTextureUnitState.L(0);
    }

    @Deprecated
    public static void C(int n) {
        if (!GlTextureUnitState.m()) {
            return;
        }
        for (int i = 0; i < n; ++i) {
            GlTextureUnitState.Q(i);
        }
    }

    public static void b(int n) {
        if (!GlTextureUnitState.m()) {
            return;
        }
        int n2 = Math.min(n, 8);
        for (int i = 0; i < n2; ++i) {
            GlTextureUnitState.a(i);
        }
    }

    public static void L(int n) {
        if (!GlTextureUnitState.m() || n >= 8) {
            return;
        }
        GlStateManagerTexGenState.I(n, Z[n]);
    }

    @Deprecated
    public static void U() {
        GlTextureUnitState.Q(0);
    }

    public static void M(int n) {
        if (!GlTextureUnitState.m() || n >= 8) {
            return;
        }
        GlTextureUnitState.Z[n] = GlStateManagerTexGenState.J(n);
    }

    public static void H() {
        GlTextureUnitState.a(0);
    }

    public static void I(int n) {
        if (!GlTextureUnitState.m()) {
            return;
        }
        int n2 = Math.min(n, 8);
        for (int i = 0; i < n2; ++i) {
            GlTextureUnitState.L(i);
        }
    }

    public static void a(int n) {
        if (!GlTextureUnitState.m()) {
            return;
        }
        GlTextureUnitState.M(n);
        GlTextureUnitState.Q(n);
    }

    public static void Q(int n) {
        if (!GlTextureUnitState.m()) {
            return;
        }
        GlStateManagerTexGenState.I(n, 0);
    }

    public static boolean m() {
        if (!v) {
            GlTextureUnitState.X();
        }
        return k;
    }

    public static void X() {
        if (v) {
            return;
        }
        v = true;
        if (!GuiRenderPrimitives.d()) {
            k = false;
            return;
        }
        if (ForgeVersion.MC_1_21_11.v()) {
            k = false;
            return;
        }
        k = GlStateManagerTexGenState.p();
        if (k) {
            Vape.debugLog(b);
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

