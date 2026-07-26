package gg.vape.render;

import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.render.GlFramebuffer;
import gg.vape.utils.render.GlImageTexture;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderBatchBuilder;
import gg.vape.utils.render.RenderBatchManager;
import gg.vape.utils.render.RenderUtils;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityRenderer;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Framebuffer;
import gg.vape.wrapper.impl.GameSettings;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.Minecraft;
import java.awt.Color;
import java.lang.invoke.MethodHandles;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class OffscreenRenderContext {
    private GlFramebuffer t;
    private static final long a = ZkmLongKeyState.a(-8859360445857927563L, 5957914099028421359L, MethodHandles.lookup().lookupClass()).a(183697058671874L);
    private Framebuffer E;
    public float K;
    public double H;
    private int Q;
    private static int[] M;
    private int o;
    private static boolean m;
    public double p;
    private boolean c;
    public double L;
    public float i;
    private int U;
    private final TimerUtil I;
    private boolean C;
    private int D;

    public Framebuffer C() {
        return this.E;
    }

    public static void F(boolean bl) {
        m = bl;
    }

    public int d() {
        return this.o;
    }

    public OffscreenRenderContext L(boolean bl) {
        this.C = bl;
        return this;
    }

    static {
        long l = a ^ 0x3D96D3D7BDDCL;
        OffscreenRenderContext.U(null);
    }

    public void X(boolean bl, double d, double d2, double d3, double d4) {
        this.B(bl, d, d2, d3, d4, Color.WHITE);
    }

    public OffscreenRenderContext() {
        this(false);
    }

    public void d$src$V$1ng482b() {
        if (ForgeVersion.MC_1_21_4.d()) {
            this.t = new GlFramebuffer(this.o, this.U, true);
            this.E = null;
            return;
        }
        this.E = Framebuffer.create(this.o, this.U, true);
        this.t = null;
        this.F$src$V$1mzme9h();
    }

    public void k(int n) {
        this.U = n;
        this.c = false;
    }

    public int F() {
        return this.U;
    }

    protected void b(Entity entity) {
        this.S(entity);
        this.i = entity.J();
        this.K = entity.V();
    }

    public void i(int n) {
        this.o = n;
        this.c = false;
    }

    protected void S(Entity entity) {
        this.p = entity.M() - (entity.M() - entity.z()) * (double)Minecraft.getTimer().getElapsedPartialTicks();
        this.L = entity.W() - (entity.W() - entity.N()) * (double)Minecraft.getTimer().getElapsedPartialTicks();
        this.H = entity.m$src$D$fwnne5() - (entity.m$src$D$fwnne5() - entity.h()) * (double)Minecraft.getTimer().getElapsedPartialTicks();
    }

    public void X(int n) {
        this.Q = n;
    }

    public void F$src$V$1mzme9h() {
        if (ForgeVersion.MC_1_21_4.d()) {
            if (this.t != null) {
                this.t.x();
            }
            this.t = new GlFramebuffer(this.o, this.U, true);
            return;
        }
        this.E.createFramebuffer(this.o, this.U);
    }

    public void v(int n) {
        this.D = n;
    }

    public void b() {
        boolean bl;
        boolean bl2;
        boolean bl3;
        boolean bl4;
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        float f11;
        int n;
        int n2;
        int n3;
        double d;
        double d2;
        double d3;
        double d4;
        double d5;
        double d6;
        double d7;
        double d8;
        double d9;
        GameSettings gameSettings;
        EntityRenderer entityRenderer;
        Entity entity;
        long l = a ^ 0x3E1747E3C2D5L;
        entity = null;
        entityRenderer = null;
        gameSettings = null;
        d9 = 0.0;
        d8 = 0.0;
        d7 = 0.0;
        d6 = 0.0;
        d5 = 0.0;
        d4 = 0.0;
        d3 = 0.0;
        d2 = 0.0;
        d = 0.0;
        n3 = 0;
        n2 = 0;
        n = 0;
        f11 = 0.0f;
        f10 = 0.0f;
        f9 = 0.0f;
        f8 = 0.0f;
        f7 = 0.0f;
        f6 = 0.0f;
        f5 = 0.0f;
        f4 = 0.0f;
        f3 = 0.0f;
        f2 = 0.0f;
        f = 0.0f;
        bl4 = false;
        bl3 = false;
        bl2 = false;
        bl = false;
        try {
                        if (m) return;
                        if (!this.R()) {
                            return;
                        }
                        gameSettings = Minecraft.gameSettings();
                        if (gameSettings.d() > 0) return;
                        if (!gameSettings.Y$src$Z$1rxemad()) {
                            return;
                        }
                        if (gameSettings.M()) return;
                        if (gameSettings.M()) {
                            return;
                        }
                        if (gameSettings.isNull()) {
                            return;
                        }
                        if (Minecraft.F().isNull()) {
                            return;
                        }
                        entity = Minecraft.F();
                        entityRenderer = Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf();
                        n3 = Minecraft.J();
                        n2 = Minecraft.h();
                        f11 = entity.J();
                        f9 = entity.j();
                        f10 = entity.V();
                        f8 = entity.D();
                        f4 = ((EntityLivingBase)entity).s();
                        f3 = ((EntityLivingBase)entity).P$src$F$14ztfk8();
                        f2 = ((EntityLivingBase)entity).W$src$F$153nzpr();
                        f = ((EntityLivingBase)entity).S$src$F$151gtcb();
                        bl4 = gameSettings.U();
                        n = gameSettings.x();
                        bl3 = gameSettings.k();
                        d9 = entity.z();
                        d6 = entity.f();
                        d3 = entity.M();
                        d8 = entity.N();
                        d5 = entity.H();
                        d2 = entity.W();
                        d7 = entity.h();
                        d4 = entity.R();
                        d = entity.m$src$D$fwnne5();
                        f7 = gameSettings.g();
                        f6 = entityRenderer.b();
                        f5 = entityRenderer.s();
                        entity.H(this.p);
                        entity.n(this.p);
                        entity.C(this.p);
                        entity.u(this.L);
                        entity.w(this.L);
                        entity.L(this.L);
                        entity.l(this.H);
                        entity.A(this.H);
                        entity.s(this.H);
                        if (ForgeVersion.MC_1_21_4.v()) {
                            Minecraft.U(this.o);
                            Minecraft.X(this.U);
                        }
                        entity.H(this.i);
                        entity.D(this.i);
                        entity.C(this.K);
                        entity.l(this.K);
                        ((EntityLivingBase)entity).z(this.i);
                        ((EntityLivingBase)entity).o(this.i);
                        ((EntityLivingBase)entity).X(this.i);
                        ((EntityLivingBase)entity).Y(this.i);
                        gameSettings.I(0);
                        gameSettings.O(false);
                        gameSettings.F(true);
                        gameSettings.k(this.D);
                        entityRenderer.V(1.0f);
                        entityRenderer.r(1.0f);
                        bl2 = true;
                        if (!this.I.hasTimeElapsed(this.Q) && this.c) return;
                        if (ForgeVersion.MC_1_21_4.d()) {
                            int n4;
                            if (this.t == null) {
                                this.d$src$V$1ng482b();
                            }
                            if ((n4 = RenderBatchManager.M().E()) <= 0) {
                                n4 = GL11.glGetInteger((int)36006);
                            }
                            int n5 = GL11.glGetInteger((int)36010);
                            GL30.glBindFramebuffer((int)36160, (int)n4);
                            GL11.glClearColor((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                            GL11.glClear((int)16640);
                            this.z(true);
                            bl = true;
                            RenderBatchManager.M().a(this.t.w);
                            entityRenderer.D(Minecraft.getTimer().renderPartialTicks(), 0L);
                            RenderBatchManager.M().j();
                            int n6 = Minecraft.p().getDeltaX();
                            int n7 = Minecraft.p().e();
                            this.t.H();
                            GL30.glBindFramebuffer((int)36008, (int)n4);
                            GL30.glBindFramebuffer((int)36009, (int)this.t.w);
                            GL30.glBlitFramebuffer((int)0, (int)0, (int)n6, (int)n7, (int)0, (int)0, (int)this.o, (int)this.U, (int)16384, (int)9729);
                            GL30.glBindFramebuffer((int)36009, (int)this.t.w);
                            GL11.glColorMask((boolean)false, (boolean)false, (boolean)false, (boolean)true);
                            GL11.glClearColor((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                            GL11.glClear((int)16384);
                            GL11.glColorMask((boolean)true, (boolean)true, (boolean)true, (boolean)true);
                            GL30.glBindFramebuffer((int)36008, (int)n5);
                            GL30.glBindFramebuffer((int)36009, (int)n4);
                        } else {
                            this.z(true);
                            bl = true;
                            this.E.bindFramebufferTexture();
                            entityRenderer.D(Minecraft.getTimer().renderPartialTicks(), 0L);
                            FloatBuffer floatBuffer = BufferUtils.createFloatBuffer((int)16);
                            GL11.glGetFloat((int)3106, (FloatBuffer)floatBuffer);
                            GL11.glColorMask((boolean)false, (boolean)false, (boolean)false, (boolean)true);
                            GL11.glClearColor((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                            GL11.glClear((int)16384);
                            GL11.glColorMask((boolean)true, (boolean)true, (boolean)true, (boolean)true);
                            GL11.glClearColor((float)floatBuffer.get(0), (float)floatBuffer.get(1), (float)floatBuffer.get(2), (float)floatBuffer.get(3));
                        }
                        this.I.reset();
            this.c = true;
        }
        catch (Exception exception) {
            Object ignored = Minecraft.c;
            return;
        }
        finally {
            if (bl2 && entity != null && entityRenderer != null && gameSettings != null) {
                if (bl) {
                    this.z(false);
                }
                if (ForgeVersion.MC_1_21_4.v()) {
                    Minecraft.U(n3);
                    Minecraft.X(n2);
                }
                entity.H(f11);
                entity.D(f9);
                entity.C(f10);
                entity.l(f8);
                ((EntityLivingBase)entity).z(f4);
                ((EntityLivingBase)entity).o(f3);
                ((EntityLivingBase)entity).X(f2);
                ((EntityLivingBase)entity).Y(f);
                gameSettings.I(n);
                gameSettings.F(bl4);
                gameSettings.O(bl3);
                entity.H(d9);
                entity.n(d6);
                entity.C(d3);
                entity.u(d8);
                entity.w(d5);
                entity.L(d2);
                entity.l(d7);
                entity.A(d4);
                entity.s(d);
                gameSettings.k(f7);
                entityRenderer.V(f6);
                entityRenderer.r(f5);
                if (ForgeVersion.MC_1_21_4.d()) {
                    GL11.glViewport((int)0, (int)0, (int)n3, (int)n2);
                } else {
                    this.E.unbindFramebuffer();
                    Minecraft.getFrameBuffer().bindFramebuffer(true);
                }
            }
        }
    }

    private boolean R() {
        return true;
    }

    public static boolean W() {
        return m;
    }

    public OffscreenRenderContext(boolean bl, int n, int n2) {
        long l = a ^ 0x4E368F6E2AB8L;
        this.I = new TimerUtil();
        this.Q = 30;
        this.C = bl;
        this.o = n;
        this.U = n2;
        this.d$src$V$1ng482b();
    }

    public static int[] W$src$AI$1oy8lkc() {
        return M;
    }

    public boolean D() {
        return this.c;
    }

    protected void z(boolean bl) {
        if (ForgeVersion.MC_1_21_4.d()) {
            if (this.t == null) {
                this.d$src$V$1ng482b();
            }
            if (bl) {
                this.t.f(true);
            } else {
                this.t.o();
            }
        } else if (bl) {
            this.E.bindFramebuffer(true);
        } else {
            this.E.unbindFramebuffer();
        }
        m = bl;
    }

    public void B(boolean bl, double d, double d2, double d3, double d4, Color color) {
        if (ForgeVersion.MC_1_21_4.d()) {
            if (!bl || this.t == null || this.t.l <= 0) {
                return;
            }
            float f = (float)Math.min(d, d3);
            float f2 = (float)Math.min(d2, d4);
            float f3 = (float)Math.abs(d3 - d);
            float f4 = (float)Math.abs(d4 - d2);
            float f5 = this.C ? 1.0f : 0.0f;
            float f6 = this.C ? 0.0f : 1.0f;
            float f7 = 1.0f;
            float f8 = 0.0f;
            RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder().o(new GlImageTexture(this.t.l)).e(f, f2, f3, f4, f3, f4, f5, f7, f6, f8, color);
            RenderBatchManager.M().O(renderBatchBuilder);
            return;
        }
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
        GlStateManager.enableBlend();
        GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
        if (bl) {
            if (ForgeVersion.MC_1_21_4.d()) {
                if (this.t == null || this.t.l <= 0) {
                    return;
                }
                this.t.S();
            } else {
                this.E.bindFramebufferTexture();
            }
            if (this.C) {
                RenderUtils.J(d, d2, d3, d4);
            } else {
                RenderUtils.A(d, d2, d3, d4);
            }
            if (ForgeVersion.MC_1_21_4.d()) {
                this.t.M();
            } else {
                this.E.unbindFramebufferTexture();
            }
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
        if (!bl5) {
            GlStateManager.disableBlend();
        }
    }

    public OffscreenRenderContext(boolean bl) {
        this(bl, 720, 400);
    }

    public static void U(int[] nArray) {
        M = nArray;
    }

    private static Exception a(Exception exception) {
        return exception;
    }
}
