package gg.vape.rotation;

import gg.vape.event.impl.EventPostRenderTick;
import gg.vape.event.impl.EventPreEntityRendererMouseUpdate;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.render.hud.FreeLookHudModule;
import gg.vape.rotation.FixedRotationController;
import gg.vape.rotation.RotationAngles;
import gg.vape.rotation.RotationManager;
import gg.vape.rotation.WorldPointRotationTarget;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.MathUtil;
import gg.vape.utils.MutableColor;
import gg.vape.utils.RotationVectorMath;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GuiScreen;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Vec3;
import java.util.Random;

public class AdaptiveRotationController
extends FixedRotationController
implements WorldPointRotationTarget {
    private boolean T;
    private long R;
    private boolean Yw;
    private double z;
    private RotationAngles r;
    private float u;
    private float q;
    private float M;
    private MutableColor i;
    private boolean C;
    private MutableColor d;
    private float E;
    private final Random Yq;
    private Float X;
    private Vec3 F;
    private float P;
    private float g;
    private float h;
    private boolean O;
    private float K;
    private EntityLivingBase j;
    private static final RotationAngles H;
    private float D;

    public float v$src$F$1mgxytb() {
        EntityLivingBase entityLivingBase;
        EntityLivingBase entityLivingBase2 = entityLivingBase = this.j != null ? this.j : Minecraft.F();
        return this.X != null ? this.X.floatValue() : (FreeLookHudModule.z() ? FreeLookHudModule.L$src$F$1jnmc2m() : entityLivingBase.J());
    }

    public float P() {
        return this.u;
    }

    @Override
    public boolean m() {
        float f;
        float f2;
        float f3;
        float f4;
        if (this.I == -999.0f) {
            return true;
        }
        float f5 = this.d();
        if (f5 == -90.0f) {
            f5 = -89.99f;
        }
        float f6 = RotationManager.b.E();
        int n = (int)this.B;
        int n2 = (int)(-this.y);
        float f7 = f6 * 0.6f + 0.2f;
        float f8 = f7 * f7 * f7 * 8.0f;
        float f9 = (float)n * f8;
        float f10 = (float)n2 * f8;
        float f11 = (float)((double)this.k() + (double)f9 * 0.15);
        float f12 = (float)((double)f5 - (double)f10 * 0.15);
        double d = MathUtil.wrapAngleTo180((this.L - f11) % 360.0f);
        double d2 = MathUtil.wrapAngleTo180((this.I - f12) % 360.0f);
        double d3 = Math.abs(d);
        double d4 = Math.abs(d2);
        double d5 = (double)this.O() * 0.25;
        double d6 = d4 / d3;
        boolean bl = this.Y();
        boolean bl2 = this.S();
        boolean bl3 = this.K();
        boolean bl4 = this.w$src$Z$15qe9bc();
        boolean bl5 = this.e();
        if (bl && d6 < 1.0) {
            d5 *= d6;
        }
        if (Math.round(d4 / (double)(f4 = (float)(0.0 + (double)(f3 = (f2 = (f = RotationManager.b.E()) * 0.6f + 0.2f) * f2 * f2 * 8.0f) * 0.15))) > (long)Math.max(Math.round(this.W / f4), 0)) {
            if (bl2) {
                d5 *= (135.0 + d4) / 90.0;
            } else if (bl4) {
                d5 += d4 * 0.05;
            } else if (bl5) {
                double d7 = d4 / 75.0;
                double d8 = 0.4;
                double d9 = 1.0;
                double d10 = -0.7;
                double d11 = d9 + 1.0;
                d5 *= Math.max(1.0, d8 + d11 * Math.pow(d7 - d10, 3.0) + d9 * Math.pow(d7 - d10, 2.0));
            }
            this.y = bl3 ? (d2 > 0.0 ? (float)((double)this.y + Math.min(d5, d2 / (double)f4)) : (float)((double)this.y - Math.min(d5, Math.abs(d2 / (double)f4)))) : (d2 > 0.0 ? (float)((double)this.y + d5) : (float)((double)this.y - d5));
            return false;
        }
        return true;
    }

    public void T(MutableColor mutableColor) {
        this.d = mutableColor;
    }

    public void x(double d) {
        this.z = d;
    }

    @Override
    public void B(EventPreEntityRendererMouseUpdate eventPreEntityRendererMouseUpdate) {
    }

    public AdaptiveRotationController(EntityPlayer entityPlayer) {
        super(entityPlayer.J(), entityPlayer.V());
        this.T = false;
        this.Yw = true;
        this.X = null;
        this.i = new MutableColor(0xFFFFFF);
        this.d = new MutableColor(16756275);
        this.u = 0.0f;
        this.Yq = new Random();
        this.E = 0.0f;
        this.q = 0.0f;
        this.K = 0.0f;
        this.D = 0.0f;
        this.M = 0.0f;
        this.h = 0.0f;
        this.R = 0L;
        this.j = entityPlayer;
        this.P = this.L;
        this.g = this.I;
        this.z = 3.0;
    }

    public boolean O$src$Z$1lvi05g() {
        return this.C;
    }

    private float y$src$F$1milcle() {
        float f = (float)(Math.sin(this.E) * (double)this.M);
        float f2 = (float)(Math.sin(this.E * 2.7f + 1.3f) * (double)this.M * (double)0.3f);
        return f + f2;
    }

    public double s() {
        return this.z;
    }

    public void c() {
        if (this.C) {
            this.b(H);
        } else {
            RotationAngles rotationAngles = null;
            if (this.F != null) {
                rotationAngles = this.j(this.F);
            } else if (this.r != null) {
                rotationAngles = this.r;
            }
            if (rotationAngles != null) {
                if (this.u != 0.0f) {
                    this.g(rotationAngles.z() + this.u, rotationAngles.N());
                } else {
                    this.b(rotationAngles);
                }
            }
        }
    }

    public AdaptiveRotationController() {
        super(RotationManager.b.V(), RotationManager.b.x());
        this.T = false;
        this.Yw = true;
        this.X = null;
        this.i = new MutableColor(0xFFFFFF);
        this.d = new MutableColor(16756275);
        this.u = 0.0f;
        this.Yq = new Random();
        this.E = 0.0f;
        this.q = 0.0f;
        this.K = 0.0f;
        this.D = 0.0f;
        this.M = 0.0f;
        this.h = 0.0f;
        this.R = 0L;
        this.P = this.L;
        this.g = this.I;
        this.z = 3.0;
    }

    public RotationAngles j(Vec3 vec3) {
        EntityLivingBase entityLivingBase = this.j != null ? this.j : Minecraft.F();
        double d = ForgeVersion.MC_1_7_10.Y() ? (double)entityLivingBase.X() : 0.0;
        Vec3 vec32 = Vec3.create(vec3.getX(), vec3.getY(), vec3.getZ());
        Vec3 vec33 = Vec3.create(entityLivingBase.c(), entityLivingBase.A() + d, entityLivingBase.Z());
        return RotationVectorMath.H(vec33, vec32, this.k(), false);
    }

    private void P$src$V$1lw1snd() {
        long l = System.currentTimeMillis();
        if (l - this.R > (long)(200 + this.Yq.nextInt(300))) {
            this.R = l;
            this.K = 0.05f + this.Yq.nextFloat() * 0.15f;
            this.D = 0.04f + this.Yq.nextFloat() * 0.12f;
            this.M = 0.15f + this.Yq.nextFloat() * 0.25f;
            this.h = 0.1f + this.Yq.nextFloat() * 0.2f;
        }
        this.E += this.K;
        this.q += this.D;
        if ((double)this.E > Math.PI * 2) {
            this.E -= (float)Math.PI * 2;
        }
        if ((double)this.q > Math.PI * 2) {
            this.q -= (float)Math.PI * 2;
        }
    }

    public void d(boolean bl) {
        this.Yw = bl;
    }

    public void t(boolean bl) {
        this.T = bl;
    }

    public void I(AdaptiveRotationController adaptiveRotationController) {
        this.P = adaptiveRotationController.P;
        this.g = adaptiveRotationController.g;
        this.L = adaptiveRotationController.L;
        this.I = adaptiveRotationController.I;
        this.z = adaptiveRotationController.z;
        this.B = adaptiveRotationController.B;
        this.y = adaptiveRotationController.y;
        this.o = adaptiveRotationController.o;
        this.n = adaptiveRotationController.n;
        this.j(adaptiveRotationController.T());
        this.k(adaptiveRotationController.K());
        this.U(adaptiveRotationController.Y());
        this.A(adaptiveRotationController.S());
        this.s(adaptiveRotationController.w$src$Z$15qe9bc());
        this.z(adaptiveRotationController.e());
        this.w(adaptiveRotationController.v());
        this.u(adaptiveRotationController.V$src$Z$lb4tvc());
        this.b = adaptiveRotationController.b;
        this.W = adaptiveRotationController.W;
        this.C = adaptiveRotationController.C;
        this.X = adaptiveRotationController.X;
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public AdaptiveRotationController(float f, float f2) {
        super(f, f2);
        this.T = false;
        this.Yw = true;
        this.X = null;
        this.i = new MutableColor(0xFFFFFF);
        this.d = new MutableColor(16756275);
        this.u = 0.0f;
        this.Yq = new Random();
        this.E = 0.0f;
        this.q = 0.0f;
        this.K = 0.0f;
        this.D = 0.0f;
        this.M = 0.0f;
        this.h = 0.0f;
        this.R = 0L;
        this.P = Minecraft.F().J();
        this.g = Minecraft.F().V();
        this.z = 3.0;
    }

    public MutableColor y() {
        return this.i;
    }

    @Override
    public void w(float f, float f2) {
        this.P = (float)((double)this.P + (double)f * 0.15);
        this.g = (float)((double)this.g - (double)f2 * 0.15);
        this.g = MathUtil.clamp(this.g, -90.0f, 90.0f);
    }

    public void s(EntityLivingBase entityLivingBase) {
        this.j = entityLivingBase;
    }

    public void x(float f) {
        this.u = f;
    }

    static {
        H = new RotationAngles(0.0f, 0.0f);
    }

    @Override
    public void z(double d, double d2, double d3) {
        this.J(Vec3.create(d, d2, d3));
    }

    @Override
    public boolean v() {
        return !this.C;
    }

    @Override
    public float d() {
        return this.g;
    }

    public void z(MutableColor mutableColor) {
        this.i = mutableColor;
    }

    public MutableColor C() {
        return this.d;
    }

    @Override
    public void J(EntityPlayerSP entityPlayerSP, GuiScreen guiScreen) {
        boolean bl;
        if (entityPlayerSP.isNull() || this.T && guiScreen.isNotNull()) {
            return;
        }
        this.c();
        boolean bl2 = this.A();
        boolean bl3 = this.m();
        boolean bl4 = bl = bl2 && bl3 && Math.abs(this.B) < 1.0f && Math.abs(this.y) < 1.0f;
        if (!this.C && bl && !this.v()) {
            this.b(true);
        } else {
            this.u(bl);
        }
    }

    private float i() {
        float f = (float)(Math.sin(this.q) * (double)this.h);
        float f2 = (float)(Math.sin(this.q * 3.1f + 0.7f) * (double)this.h * 0.25);
        return f + f2;
    }

    public AdaptiveRotationController(Vec3 vec3) {
        this(0.0f, 0.0f);
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        double d = ForgeVersion.MC_1_7_10.Y() ? (double)entityPlayerSP.X() : 0.0;
        Vec3 vec32 = Vec3.create(vec3.getX(), vec3.getY(), vec3.getZ());
        Vec3 vec33 = Vec3.create(entityPlayerSP.c(), entityPlayerSP.A() + d, entityPlayerSP.Z());
        RotationAngles rotationAngles = RotationVectorMath.H(vec33, vec32, this.k(), this.c$src$Z$1m6hw0o());
        this.L = rotationAngles.z();
        this.I = rotationAngles.N();
    }

    @Override
    public void J(Vec3 vec3) {
        this.r = null;
        this.F = vec3;
    }

    @Override
    public boolean A() {
        float f;
        float f2;
        float f3;
        float f4;
        if (this.L == -999.0f) {
            return true;
        }
        float f5 = RotationManager.b.E();
        int n = (int)this.B;
        int n2 = (int)(-this.y);
        float f6 = f5 * 0.6f + 0.2f;
        float f7 = f6 * f6 * f6 * 8.0f;
        float f8 = (float)n * f7;
        float f9 = (float)n2 * f7;
        float f10 = (float)((double)this.k() + (double)f8 * 0.15);
        float f11 = (float)((double)this.d() - (double)f9 * 0.15);
        double d = MathUtil.wrapAngleTo180((double)((this.L - f10) % 360.0f));
        double d2 = MathUtil.wrapAngleTo180((double)((this.I - f11) % 360.0f));
        double d3 = Math.abs(d);
        double d4 = Math.abs(d2);
        double d5 = (double)this.O() * 0.25;
        double d6 = d3 / d4;
        boolean bl = this.Y();
        boolean bl2 = this.S();
        boolean bl3 = this.K();
        boolean bl4 = this.w$src$Z$15qe9bc();
        boolean bl5 = this.e();
        if (bl && d6 < 1.0) {
            d5 *= d6;
        }
        if (Math.round(d3 / (double)(f4 = (float)(0.0 + (double)(f3 = (f2 = (f = RotationManager.b.E()) * 0.6f + 0.2f) * f2 * f2 * 8.0f) * 0.15))) > (long)Math.max(Math.round(this.W / f4), 0)) {
            if (bl2) {
                d5 *= (225.0 + d3) / 180.0;
            } else if (bl4) {
                d5 += d3 * 0.05;
            } else if (bl5) {
                double d7 = d3 / 100.0;
                double d8 = 0.4;
                double d9 = 1.0;
                double d10 = -0.7;
                double d11 = d9 + 1.0;
                d5 *= Math.min(Math.max(1.0, d8 + d11 * Math.pow(d7 - d10, 3.0) + d9 * Math.pow(d7 - d10, 2.0)), 4.0);
            }
            this.B = bl3 ? (d > 0.0 ? (float)((double)this.B + Math.min(d5, d / (double)f4)) : (float)((double)this.B - Math.min(d5, Math.abs(d / (double)f4)))) : (d > 0.0 ? (float)((double)this.B + d5) : (float)((double)this.B - d5));
            return false;
        }
        return true;
    }

    public float X() {
        EntityLivingBase entityLivingBase = this.j != null ? this.j : Minecraft.F();
        float f = this.C ? entityLivingBase.V() + this.g : this.g;
        return MathUtil.clamp(f, -90.0f, 90.0f);
    }

    public void L(boolean bl) {
        this.O = bl;
    }

    public boolean r() {
        return this.T;
    }

    public void C(Float f) {
        this.X = f;
    }

    @Override
    public void R(EventPostRenderTick eventPostRenderTick) {
        if (this.C && this.j == null) {
            float f;
            float f2 = eventPostRenderTick.getThePlayer().J() - this.Q;
            float f3 = eventPostRenderTick.getThePlayer().V() - this.c;
            float f4 = Math.abs(this.P - f2);
            if (f4 < Math.abs(this.P)) {
                this.P -= f2;
            }
            if ((f = Math.abs(this.g - f3)) < Math.abs(this.g)) {
                this.g -= f3;
            }
        }
    }

    @Override
    public Vec3 w() {
        return this.F;
    }

    public float J() {
        EntityLivingBase entityLivingBase;
        EntityLivingBase entityLivingBase2 = entityLivingBase = this.j != null ? this.j : Minecraft.F();
        if (this.C) {
            return entityLivingBase.J() + this.P;
        }
        return this.P;
    }

    @Override
    public float k() {
        return this.P;
    }

    public boolean M() {
        return this.O;
    }

    @Override
    public void g(float f, float f2) {
        this.F = null;
        super.g(f, f2);
    }

    public void a(float f) {
        this.g = f;
    }

    public boolean c$src$Z$1m6hw0o() {
        return this.Yw;
    }

    public void T(float f) {
        this.P = f;
    }

    public void b(boolean bl) {
        if (this.C != bl) {
            EntityLivingBase entityLivingBase;
            EntityLivingBase entityLivingBase2 = entityLivingBase = this.j != null ? this.j : Minecraft.F();
            if (bl) {
                this.C(null);
                float f = entityLivingBase.J() - this.P;
                float f2 = entityLivingBase.V() - this.g;
                float f3 = 0.0f;
                while (f + f3 > 180.0f) {
                    f3 -= 360.0f;
                }
                while (f + f3 < -180.0f) {
                    f3 += 360.0f;
                }
                if (f3 != 0.0f) {
                    entityLivingBase.H(entityLivingBase.J() + f3);
                    entityLivingBase.z(entityLivingBase.s() + f3);
                    entityLivingBase.D(entityLivingBase.j() + f3);
                    if (entityLivingBase.isInstance(MappedClasses.z5)) {
                        EntityPlayerSP entityPlayerSP = new EntityPlayerSP(entityLivingBase);
                        entityPlayerSP.F(entityPlayerSP.q$src$F$1u6qsjx() + f3);
                    }
                }
                this.P = MathUtil.wrapAngleTo180(-f);
                this.g = MathUtil.clamp(-f2, -90.0f, 90.0f);
            } else {
                this.P += entityLivingBase.J();
                this.g += entityLivingBase.V();
                this.g = MathUtil.clamp(this.g, -90.0f, 90.0f);
            }
            this.u(false);
        }
        this.C = bl;
    }
}
