package gg.vape.utils.render;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.GlStateManager;
import org.lwjgl.opengl.GL11;

public class GlCapabilityState {
    private static String J;
    public boolean K;
    public boolean k;
    public boolean r;
    public boolean Z;

    public static String u() {
        return J;
    }

    public static void m(String string) {
        J = string;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof GlCapabilityState)) {
            return false;
        }
        GlCapabilityState eZ = (GlCapabilityState)object;
        boolean bl = this.Y() == eZ.Y();
        return bl;
    }

    public void a(boolean bl) {
        this.k = bl;
    }

    public GlCapabilityState L() {
        return new GlCapabilityState(this);
    }

    public boolean Z(int capability) {
        switch (capability) {
            case GL11.GL_DEPTH_TEST:
                this.K = false;
                return true;
            case GL11.GL_CULL_FACE:
                this.Z = false;
                return true;
            case GL11.GL_BLEND:
                this.r = false;
                return true;
            default:
                return false;
        }
    }

    public void K() {
        if (this.K) {
            GlStateManager.enableDepth();
        } else {
            GlStateManager.disableDepth();
        }
        if (this.r) {
            GlStateManager.enableBlend();
        } else {
            GlStateManager.disableBlend();
        }
        GL11.glDepthMask((boolean)this.k);
    }

    public long Y() {
        long state = 0L;
        state |= (this.Z ? 1L : 0L) << 0;
        state |= (this.K ? 1L : 0L) << 1;
        state |= (this.r ? 1L : 0L) << 2;
        state |= (this.k ? 1L : 0L) << 3;
        return state;
    }

    public GlCapabilityState(GlCapabilityState eZ) {
        this.Z = eZ.Z;
        this.K = eZ.K;
        this.r = eZ.r;
        this.k = eZ.k;
    }

    public boolean D(int capability) {
        switch (capability) {
            case GL11.GL_DEPTH_TEST:
                this.K = true;
                return true;
            case GL11.GL_CULL_FACE:
                this.Z = true;
                return true;
            case GL11.GL_BLEND:
                this.r = true;
                return true;
            default:
                return false;
        }
    }

    public GlCapabilityState() {
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    static {
        if (GlCapabilityState.u() != null) {
            GlCapabilityState.m("DrzNk");
        }
    }
}
