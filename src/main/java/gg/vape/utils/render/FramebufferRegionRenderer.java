package gg.vape.utils.render;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderUtils;
import gg.vape.wrapper.impl.Framebuffer;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.Minecraft;
import org.lwjgl.opengl.GL11;

public class FramebufferRegionRenderer {
    public double I;
    private Framebuffer M;
    public float p;
    private static boolean z;
    private int b;
    private int S;
    private boolean V;
    public double g;
    private final TimerUtil H;
    public float s;
    private int A;
    private boolean o;
    public double L;
    private static final long c;
    private int t;

    public void N$src$V$5gaqtr() {
        if (!Minecraft.gameSettings().Y$src$Z$1rxemad()) {
            return;
        }
        this.u().bindFramebufferTexture();
        this.o(true);
    }

    public int U() {
        return this.A;
    }

    public Framebuffer u() {
        return this.M;
    }

    public void L(int n) {
        this.t = n;
        this.V = false;
    }

    public int N() {
        return this.t;
    }

    public boolean A() {
        return this.V;
    }

    public void S() {
        this.M = Framebuffer.create(this.A, this.t, true);
        this.R();
    }

    public FramebufferRegionRenderer(int n, int n2) {
        this.H = new TimerUtil();
        this.b = (int)c;
        this.o = this.o;
        this.A = n;
        this.t = n2;
        this.S();
    }

    public void i(boolean bl, double d, double d2, double d3, double d4) {
        if (!Minecraft.gameSettings().Y$src$Z$1rxemad()) {
            return;
        }
        OpenGlBackendHolder.d.l(2903);
        boolean bl2 = GL11.glIsEnabled((int)3553);
        boolean bl3 = GL11.glIsEnabled((int)2896);
        boolean bl4 = GL11.glIsEnabled((int)3008);
        boolean bl5 = GL11.glIsEnabled((int)3042);
        if (!bl2) {
            GlStateManager.enableTexture2D();
        }
        if (bl3) {
            GlStateManager.disableLighting();
        }
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
        if (bl) {
            this.M.bindFramebufferTexture();
            if (this.o) {
                RenderUtils.J(d, d2, d3, d4);
            } else {
                RenderUtils.A(d, d2, d3, d4);
            }
            this.M.unbindFramebufferTexture();
        }
        if (!bl2) {
            GlStateManager.disableTexture2D();
        }
        if (bl3) {
            GlStateManager.enableLighting();
        }
        if (bl4) {
            GlStateManager.enableAlpha();
        }
        if (bl5) {
            GlStateManager.enableBlend();
        }
    }

    static {
        c = 3773695550600249374L;
    }

    public void x() {
        if (!Minecraft.gameSettings().Y$src$Z$1rxemad()) {
            return;
        }
        this.o(false);
        this.u().unbindFramebuffer();
        Minecraft.getFrameBuffer().bindFramebuffer(true);
    }

    protected void o(boolean bl) {
        if (bl) {
            this.M.bindFramebuffer(true);
        } else {
            this.M.unbindFramebuffer();
        }
        z = bl;
    }

    public void R() {
        this.M.createFramebuffer(this.A, this.t);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void R(int n) {
        this.A = n;
        this.V = false;
    }
}

