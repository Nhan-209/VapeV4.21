package gg.vape.module.combat;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPreRenderTick;
import gg.vape.module.Mod;
import gg.vape.module.SubModule;
import gg.vape.module.combat.AimAssist;
import gg.vape.module.combat.aimassist.AimAssistRotationWorkerThread;
import gg.vape.module.none.ClientSettings;
import gg.vape.rotation.PlayerMouseRotationApplier;
import gg.vape.rotation.RotationManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.MathUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.Vec3d;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;
import java.util.Random;
import org.jetbrains.annotations.Nullable;

public class AimAssistRotationSubModule
extends SubModule<AimAssist> {
    private int vg;
    private int p;
    private boolean vM;
    private float A = 0.0f;
    private int F;
    private float V;
    private float vb;
    private double I;
    private float b;
    @Nullable
    private EntityLivingBase o = null;
    double vX;
    private boolean L;
    private double D;
    private boolean vo;
    private boolean v;
    private float s;
    private float vF;
    private int P;
    private float U = 0.0f;
    private final Random r;
    private double K;
    double C;
    private int O;
    private float H;
    private int J;
    private int Z;
    double t;
    private final Random S = new Random();
    private double c;

    private void K() {
        Vec3d vec3d = RotationUtil.T(Minecraft.thePlayer(), this.o.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl(), 0.0, 0.0, 0.0);
        double d = this.o.z() - this.o.f();
        double d2 = this.o.h() - this.o.R();
        double d3 = vec3d.Y() - d;
        double d4 = vec3d.o() - d2;
        float f = Minecraft.getTimer().renderPartialTicks();
        double d5 = d3 + (vec3d.Y() - d3) * (double)f;
        double d6 = d4 + (vec3d.o() - d4) * (double)f;
        this.t = d5;
        this.C = d6;
    }

    private void A() {
        this.K += 1.0;
        if (this.K >= (double)(250 + this.r.nextInt(50))) {
            this.K = MathUtil.randomExclusiveUpper(this.r, -100, -50);
            this.vg = MathUtil.randomExclusiveUpper(this.r, -1, 2);
            this.F = MathUtil.randomExclusiveUpper(this.r, -1, 2);
        }
        int n = this.vg;
        int n2 = this.F;
        if (this.r.nextInt(10) < 2) {
            // empty if block
        }
        if (this.r.nextInt(10) < 2) {
            // empty if block
        }
        if (this.r.nextInt(10) < 2) {
            n = 0;
        }
        if (this.r.nextInt(10) < 2) {
            n2 = 0;
        }
        if (this.K < 0.0) {
            n = 0;
            n2 = 0;
        }
        if (this.r.nextInt(20) == 1) {
            this.Z += n;
            this.O += n2;
        }
        if (this.b > 0.0f && this.Z < 0 || this.b < 0.0f && this.Z > 0) {
            this.Z = 0;
        }
    }

    @EventHandler
    public void P(EventPreRenderTick eventPreRenderTick) {
        boolean bl;
        if (eventPreRenderTick.getWorld().isNull()) {
            return;
        }
        if (this.o == null) {
            return;
        }
        this.b += (float)this.Z;
        this.V += (float)this.O;
        int n = (int)this.b;
        int n2 = (int)this.V;
        float f = this.b - (float)n;
        float f2 = this.V - (float)n2;
        boolean bl2 = Math.abs(n) > 0;
        boolean bl3 = bl = Math.abs(n2) > 0;
        if (!bl2) {
            n = 0;
        }
        if (!bl) {
            n2 = 0;
        }
        float f3 = Minecraft.gameSettings().y();
        float f4 = f3 * 0.6f + 0.2f;
        float f5 = f4 * f4 * f4 * 8.0f;
        float f6 = (float)n * f5;
        float f7 = (float)n2 * f5;
        int n3 = -1;
        PlayerMouseRotationApplier.j(f6, f7 * (float)n3);
        this.b = f;
        this.V = f2;
        this.Z = 0;
        this.O = 0;
    }

    public static void n(AimAssistRotationSubModule aimAssistRotationSubModule) {
        aimAssistRotationSubModule.x();
    }

    void g(float f, float f2) {
        AimAssist aimAssist = (AimAssist)this.getParent();
        if (f != 0.0f) {
            f *= 5.0f;
            float f3 = ((Double)aimAssist.F$src$Lgg_vape_value_NumberValue_$cqv0bx().K()).floatValue();
            if (f2 <= 10.0f) {
                this.U = f3;
            }
            if (this.U > 0.0f) {
                f3 -= this.U / 3.0f;
                this.U -= f2 / 200.0f;
            }
            float f4 = 1.0f * f3 * f;
            this.V += f4;
        } else {
            this.V = 0.0f;
        }
    }

    void R(float f) {
        AimAssist aimAssist = (AimAssist)this.getParent();
        if (f != 0.0f) {
            f *= 5.0f;
            float f2 = ((Double)aimAssist.w$src$Lgg_vape_value_NumberValue_$cwexni().K()).floatValue();
            float f3 = RotationUtil.a(Minecraft.thePlayer(), this.o);
            if (f3 <= 10.0f) {
                this.A = f2;
            }
            if (this.A > 0.0f) {
                f2 -= this.A / 3.0f;
                this.A -= f3 / 200.0f;
            }
            float f4 = 1.0f * f2 * f;
            this.b += f4;
        } else {
            this.b = 0.0f;
        }
    }

    public AimAssistRotationSubModule(Mod mod, String string) {
        super(mod, string);
        this.r = new Random();
    }

    void V$src$V$1u3xaau() {
        this.b = 0.0f;
        this.V = 0.0f;
        this.vg = 0;
        this.F = 0;
        this.Z = 0;
        this.O = 0;
    }

    private void x() {
        AimAssist aimAssist = (AimAssist)this.getParent();
        if (Minecraft.theWorld().isNull() || Minecraft.thePlayer().isNull()) {
            return;
        }
        if (!aimAssist.K()) {
            this.V$src$V$1u3xaau();
            return;
        }
        if (this.o != null && this.o.isNull()) {
            this.o = null;
        }
        if (aimAssist.r$src$Lgg_vape_value_BooleanValue_$f5ztnc().L().booleanValue() && !gg.vape.config.ClientSettings.M()) {
            this.o = null;
            this.V$src$V$1u3xaau();
            return;
        }
        if (this.o != null && (RotationUtil.C(this.o) || (double)Minecraft.thePlayer().getDistanceToEntity(this.o) > (Double)aimAssist.W().K())) {
            this.V$src$V$1u3xaau();
            this.o = null;
        }
        if (aimAssist.r$src$Lgg_vape_value_BooleanValue_$f5ztnc().L().booleanValue() && gg.vape.config.ClientSettings.M() && this.o == null || !aimAssist.r$src$Lgg_vape_value_BooleanValue_$f5ztnc().L().booleanValue()) {
            EntityLivingBase entityLivingBase = aimAssist.M$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1qf3v8a();
            if (!aimAssist.r$src$Lgg_vape_value_BooleanValue_$f5ztnc().L().booleanValue()) {
                ++this.P;
                if (this.P > 700 || this.o == null || !aimAssist.o(this.o)) {
                    this.o = entityLivingBase;
                    this.P = 0;
                }
            } else {
                this.o = entityLivingBase;
            }
        }
        if (Minecraft.theWorld().getObject() == null) {
            return;
        }
        if (this.o != null && Minecraft.currentScreen().getObject() == null && ClientSettings.fW.P) {
            this.F(this.vM);
            this.r$src$V$1ujbiwy();
        } else {
            this.V$src$V$1u3xaau();
        }
    }

    @Override
    public void onDisable() {
        this.o = null;
        this.V$src$V$1u3xaau();
    }

    private void r$src$V$1ujbiwy() {
        float f;
        AimAssist aimAssist = (AimAssist)this.getParent();
        this.A();
        this.t = this.o.c();
        this.vX = this.o.A();
        this.C = this.o.Z();
        if (ForgeVersion.MC_1_7_10.L()) {
            this.vX += (double)this.o.X();
        }
        if (aimAssist.F.K() == aimAssist.r) {
            this.K();
        }
        double d = this.t - this.c;
        double d2 = this.C - this.I;
        this.c = this.t;
        this.I = this.C;
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        float f2 = RotationManager.s(entityPlayerSP);
        double d3 = 1.7;
        double d4 = RotationUtil.C(entityPlayerSP.c(), entityPlayerSP.Z(), f2, this.t + d * d3, this.C + d2 * d3);
        boolean bl = RotationUtil.p(entityPlayerSP.c(), entityPlayerSP.Z(), f2, this.t + d * d3, this.C + d2 * d3);
        if (bl) {
            float f3;
            int n = RotationUtil.H(entityPlayerSP, this.t, this.vX, this.C);
            boolean bl2 = n < 0;
            int n2 = Math.abs(n) - 10;
            float f4 = 1.0f;
            float f5 = 1.0f;
            f4 = (float)((double)f4 + MathUtil.randomRange(this.S, 0.0, 2.0));
            f4 = (float)((double)f4 + d4 / 50.0);
            f5 = (float)((double)f5 + MathUtil.randomRange(this.S, 0.0, 2.0));
            f5 += (float)Math.abs(n2) / 50.0f;
            if (Math.abs(d4 - this.D) > 6.0) {
                f4 = (float)((double)f4 + d4 / 35.0);
            }
            double d5 = (9.0f - entityPlayerSP.getDistanceToEntity(this.o)) / 2.5f - 2.0f;
            d5 = Math.max(0.0, d5);
            f4 = (float)((double)f4 + d5);
            if (aimAssist.R().L().booleanValue() && entityPlayerSP.movementInput().T() < 0.0f) {
                f4 = (float)((double)f4 * 1.6);
            }
            if (entityPlayerSP.getDistanceToEntity(this.o) < 0.5f) {
                f4 /= 5.0f;
            }
            float f6 = -(f4 /= 90.0f);
            float f7 = f3 = bl2 ? f5 : -(f5 /= 90.0f);
            if (d4 < 5.0) {
                f6 = 0.0f;
                this.vb *= 0.7f;
                if (entityPlayerSP.movementInput().T() > 0.0f) {
                    this.vb *= 0.5f;
                }
            }
            if (bl != this.L) {
                this.vb = -this.vb;
                this.H = -this.H;
                this.b = 0.0f;
            }
            if (bl2 != this.vo) {
                this.vF = -this.vF;
                this.s = -this.s;
                this.V = 0.0f;
            }
            if (n2 < 5) {
                f3 = 0.0f;
                this.vF *= 0.7f;
            }
            this.H += f6;
            this.s += f3;
            f6 = this.vb;
            f3 = this.vF;
            if (Math.abs(f6) > 10.0f) {
                this.H = 0.0f;
                this.vb = 0.0f;
                return;
            }
            float f8 = f6 * 0.15f;
            if (d4 <= 9.0) {
                f8 = (float)((double)f8 / (10.0 - d4));
            }
            boolean bl3 = this.vM = d4 > 5.0;
            if (Float.isNaN(f8)) {
                this.H = 0.0f;
                this.vb = 0.0f;
                return;
            }
            this.R(f8);
            if (aimAssist.U().L().booleanValue()) {
                float f9 = (float)((double)f3 * 0.15);
                if (Float.isNaN(f9)) {
                    this.s = 0.0f;
                    this.vF = 0.0f;
                    return;
                }
                this.g(f9, n);
            }
            this.vo = bl2;
            this.L = bl;
            ++this.J;
            if (this.J > 10) {
                this.D = d4;
                this.J = 0;
            }
            return;
        }
        int n = RotationUtil.H(entityPlayerSP, this.t, this.vX, this.C);
        boolean bl4 = n < 0;
        int n3 = Math.abs(n) - 10;
        float f10 = 1.0f;
        float f11 = 1.0f;
        f10 = (float)((double)f10 + MathUtil.randomRange(this.S, 0.0, 2.0));
        f10 = (float)((double)f10 + d4 / 50.0);
        f11 = (float)((double)f11 + MathUtil.randomRange(this.S, 0.0, 2.0));
        f11 += (float)Math.abs(n3) / 50.0f;
        if (Math.abs(d4 - this.D) > 6.0) {
            f10 = (float)((double)f10 + d4 / 35.0);
        }
        double d6 = (9.0f - entityPlayerSP.getDistanceToEntity(this.o)) / 2.5f - 2.0f;
        d6 = Math.max(0.0, d6);
        f10 = (float)((double)f10 + d6);
        if (aimAssist.R().L().booleanValue() && entityPlayerSP.movementInput().T() > 0.0f) {
            f10 = (float)((double)f10 * 1.6);
        }
        if (entityPlayerSP.getDistanceToEntity(this.o) < 0.5f) {
            f10 /= 5.0f;
        }
        float f12 = f10 /= 90.0f;
        float f13 = f = bl4 ? f11 : -(f11 /= 90.0f);
        if (d4 < 5.0) {
            f12 = 0.0f;
            this.vb *= 0.7f;
            if (entityPlayerSP.movementInput().T() < 0.0f) {
                this.vb *= 0.5f;
            }
        }
        if (bl != this.L) {
            this.vb = -this.vb;
            this.H = -this.H;
            this.b = 0.0f;
        }
        if (bl4 != this.vo) {
            this.vF = -this.vF;
            this.s = -this.s;
            this.V = 0.0f;
        }
        if (n3 < 5) {
            f = 0.0f;
            this.vF *= 0.7f;
        }
        this.H += f12;
        this.s += f;
        f12 = this.vb;
        f = this.vF;
        if (Math.abs(f12) > 10.0f) {
            this.H = 0.0f;
            this.vb = 0.0f;
            return;
        }
        float f14 = f12 * 0.15f;
        if (d4 <= 9.0) {
            f14 = (float)((double)f14 / (10.0 - d4));
        }
        boolean bl5 = this.vM = d4 > 5.0;
        if (Float.isNaN(f14)) {
            this.H = 0.0f;
            this.vb = 0.0f;
            return;
        }
        this.R(f14);
        if (aimAssist.U().L().booleanValue()) {
            float f15 = (float)((double)f * 0.15);
            if (Float.isNaN(f15)) {
                this.s = 0.0f;
                this.vF = 0.0f;
                return;
            }
            this.g(f15, n);
        }
        this.vo = bl4;
        this.L = bl;
        ++this.J;
        if (this.J > 10) {
            this.D = d4;
            this.J = 0;
        }
    }

    @Nullable
    public EntityLivingBase v() {
        return this.o;
    }

    void F(boolean bl) {
        ++this.p;
        if (this.p > 10) {
            this.vF = this.s;
            this.vb = this.H;
            this.H = 0.0f;
            this.s = 0.0f;
            this.p = 0;
        }
    }

    @Override
    public void onEnable() {
        if (!this.v) {
            this.v = true;
            new AimAssistRotationWorkerThread(this).start();
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

