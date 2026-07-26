package gg.vape.module.combat;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPreRenderTick;
import gg.vape.module.Mod;
import gg.vape.module.SubModule;
import gg.vape.module.combat.AimAssist;
import gg.vape.module.combat.AimAssistWorkerThread;
import gg.vape.module.none.ClientSettings;
import gg.vape.rotation.PlayerMouseRotationApplier;
import gg.vape.rotation.RotationManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.MathUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.Vec3d;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;
import java.util.Random;
import org.jetbrains.annotations.Nullable;

public class AimAssistTargetingSubModule
extends SubModule<AimAssist> {
    private float Jw;
    private float JJ;
    private double C;
    private float V;
    private boolean Js;
    private float Z;
    private boolean JA;
    private static final float Jl = 5.0f;
    private long JR;
    private double O;
    private float JK;
    private float J9;
    private float J;
    private float v;
    private float o;
    private float b;
    private float D;
    private double J7;
    private double U;
    private boolean J6;
    private static final float Jc = 20.0f;
    private long t;
    private double JF;
    private float J4;
    private static final float K = 7.0f;
    private float Jj;
    private float s;
    private double J2;
    private float JT;
    private float F;
    private double JV;
    private float Jy;
    private float H;
    private long Jz;
    private float p;
    private double L;
    private float I;
    @Nullable
    private EntityLivingBase J_;
    private float c;
    private float r;
    private float J3;
    private float Jn;
    private double JS;
    private float S;
    private float Jk;
    private int JL;
    private float Jm;
    private boolean JD;
    private float JO;
    private float P;
    private final Random Jb = new Random();
    private boolean A;
    private double Ju;
    private float Jp;
    private boolean JP;
    private double J5;

    public static void c(AimAssistTargetingSubModule aimAssistTargetingSubModule) {
        aimAssistTargetingSubModule.Y();
    }

    private double[] w(EntityPlayerSP entityPlayerSP, EntityLivingBase entityLivingBase) {
        AimAssist aimAssist = (AimAssist)this.getParent();
        double d = entityPlayerSP.A() + (double)entityPlayerSP.X();
        double d2 = entityLivingBase.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().getMinY();
        double d3 = entityLivingBase.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().getMaxY();
        double d4 = d3 - d2;
        double d5 = d2 + d4 * 0.65;
        double d6 = Math.min(0.85, (double)this.Z);
        double d7 = 0.1 + d6;
        double d8 = Math.max(d2 + 0.01, Math.min(d3 - 0.01, d - d7));
        double d9 = Math.max(0.0, Math.min(1.0, d6 / 0.55));
        double d10 = d8 + (d5 - d8) * d9;
        d10 = Math.max(d2 + 0.01, Math.min(d3 - 0.01, d10));
        if (aimAssist.F.K() == aimAssist.r) {
            Vec3d vec3d = RotationUtil.T(entityPlayerSP, entityLivingBase.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl(), 0.0, 0.0, 0.0);
            double d11 = Math.max(d2 + 0.01, Math.min(d3 - 0.01, vec3d.t() - d7));
            d11 += (d5 - d11) * d9;
            d11 = Math.max(d2 + 0.01, Math.min(d3 - 0.01, d11));
            return new double[]{vec3d.Y(), d11, vec3d.o()};
        }
        return new double[]{entityLivingBase.c(), d10, entityLivingBase.Z()};
    }

    private void k$src$V$6adzo() {
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
        float f12;
        float f13;
        float f14;
        float f15;
        float f16;
        float f17;
        double d;
        double d2;
        float f18;
        boolean bl;
        AimAssist aimAssist = (AimAssist)this.getParent();
        boolean bl2 = ((Double)aimAssist.w$src$Lgg_vape_value_NumberValue_$cwexni().K()).floatValue() > 20.0f;
        boolean bl3 = aimAssist.U().L() != false && ((Double)aimAssist.F$src$Lgg_vape_value_NumberValue_$cqv0bx().K()).floatValue() > 20.0f;
        boolean bl4 = bl2 && this.J6;
        boolean bl5 = bl = bl3 && this.JD;
        if (this.J_ == null || this.J_.isNull()) {
            this.r$src$V$a4y57();
            return;
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            this.r$src$V$a4y57();
            return;
        }
        long l = System.nanoTime();
        if (!this.JP || this.JR == 0L) {
            f18 = 0.016666668f;
        } else {
            f18 = (float)(l - this.JR) / 1.0E9f;
            f18 = Math.max(0.008333334f, Math.min(0.12f, f18));
        }
        this.JR = l;
        double[] dArray = this.N(entityPlayerSP, this.J_);
        double d3 = dArray[0];
        double d4 = dArray[1];
        double d5 = dArray[2];
        double d6 = this.J_.t();
        double d7 = this.J_.q();
        double d8 = this.J_.T();
        double d9 = d6 - entityPlayerSP.t();
        double d10 = d7 - entityPlayerSP.q();
        double d11 = d8 - entityPlayerSP.T();
        double d12 = d6 + d9 * 0.08;
        double d13 = d7 + d10 * 0.1;
        double d14 = d8 + d11 * 0.08;
        if (!this.Js) {
            this.J2 = d3;
            this.Ju = d4;
            this.J5 = d5;
            this.U = d12;
            this.J7 = d13;
            this.JF = d14;
            this.Js = true;
        }
        double d15 = Math.sqrt(d6 * d6 + d8 * d8);
        double d16 = Math.sqrt(d9 * d9 + d11 * d11);
        double d17 = entityPlayerSP.getDistanceToEntity(this.J_);
        double d18 = aimAssist.F.K() == aimAssist.r ? 0.3 : 0.28;
        double d19 = d18 + Math.min(0.35, d16 * 0.5);
        d19 = Math.max(0.08, Math.min(0.75, d19));
        double d20 = d15 + d16 * 0.25;
        double d21 = 0.18 + Math.min(0.42, d20 * 0.72);
        d21 = Math.max(0.12, Math.min(0.68, d21));
        double d22 = Math.max(0.45, Math.min(2.4, (double)f18 * 60.0));
        double d23 = 1.0 - Math.pow(1.0 - d19, d22);
        double d24 = 1.0 - Math.pow(1.0 - d21, d22);
        this.J2 += (d3 - this.J2) * d23;
        this.Ju += (d4 - this.Ju) * d23;
        this.J5 += (d5 - this.J5) * d23;
        this.U += (d12 - this.U) * d24;
        this.J7 += (d13 - this.J7) * d24;
        this.JF += (d14 - this.JF) * d24;
        double d25 = d3 - this.J2;
        double d26 = d5 - this.J5;
        double d27 = Math.sqrt(d25 * d25 + d26 * d26);
        if (d27 > (d2 = 0.3 + d15 * 1.3 + d16 * 0.4)) {
            this.J2 = d3;
            this.Ju = d4;
            this.J5 = d5;
            this.U = d12;
            this.J7 = d13;
            this.JF = d14;
        }
        double d28 = 0.35 + d17 * 0.045 + d15 * 0.95 + Math.min(0.18, d16 * 0.12);
        d28 = Math.max(0.1, Math.min(1.05, d28));
        double d29 = Math.max(0.0, Math.min(1.0, (d17 - 0.8) / 2.5));
        d28 *= 0.3 + 0.7 * d29;
        d28 = Math.max(0.05, d28);
        double d30 = entityPlayerSP.c();
        double d31 = entityPlayerSP.Z();
        double d32 = entityPlayerSP.A() + (double)entityPlayerSP.X();
        float f19 = 0.0f;
        if (this.JP && this.L != 0.0) {
            f19 = (float)((d32 - this.L) / (double)f18);
        }
        this.Jy += (f19 - this.Jy) * 0.65f;
        this.L = d32;
        if (entityPlayerSP.b$src$Z$fqlxe4()) {
            this.O = d32;
            this.Z *= 0.35f;
        } else {
            if (this.O == 0.0) {
                this.O = d32;
            }
            d = Math.max(0.0, d32 - this.O);
            float f20 = (float)Math.min(0.7, Math.max(0.0, (double)this.Jy) * 0.34);
            float f21 = (float)Math.min(0.82, d * 0.58);
            this.Z = Math.max(this.Z * 0.92f, Math.max(f20, f21));
        }
        d = this.J2;
        double d33 = this.J5;
        double d34 = this.Ju - d32;
        double d35 = Math.abs(d34);
        double d36 = Math.max(0.0, Math.min(1.0, d35 / 0.7));
        double d37 = Math.min(d28, 0.7 + d17 * 0.04);
        double d38 = 0.28 + 0.52 * d36;
        double d39 = this.J7 * d37 * d38;
        double d40 = 0.16 + d17 * 0.055;
        d39 = Math.max(-d40, Math.min(d40, d39));
        double d41 = this.Ju + d39;
        if (bl4) {
            d = d3;
            d33 = d5;
        }
        if (bl) {
            d41 = d4;
        }
        double d42 = d - d30;
        double d43 = d33 - d31;
        double d44 = d41 - d32;
        double d45 = Math.sqrt(d42 * d42 + d43 * d43);
        float f22 = (float)(Math.toDegrees(Math.atan2(d43, d42)) - 90.0);
        float f23 = (float)(-Math.toDegrees(Math.atan2(d44, Math.max(d45, 1.0E-4))));
        if (this.JP) {
            f17 = Math.abs(MathUtil.wrapAngleTo180(f22 - this.JO));
            f16 = Math.abs(MathUtil.wrapAngleTo180(f23 - this.p));
            f15 = aimAssist.F.K() == aimAssist.r ? 20.0f : 12.0f;
            f14 = Math.max(f17, f16);
            f13 = Math.max(0.0f, Math.min(1.0f, (f14 - f15) / 50.0f));
            this.o = Math.max(this.o, f13);
            if (f13 > 0.3f) {
                this.s *= 0.3f;
                this.J2 = d3;
                this.Ju = d4;
                this.J5 = d5;
            }
            f12 = 1.0f + Math.min(2.0f, f17 / 60.0f);
            f11 = 1.0f + Math.min(2.0f, f16 / 60.0f);
            f10 = (120.0f + (float)(d16 * 700.0)) * f12;
            f9 = (95.0f + (float)(Math.abs(this.J7) * 550.0)) * f11;
            f8 = Math.abs(MathUtil.wrapAngleTo180(f22 - RotationManager.s(entityPlayerSP)));
            f7 = AimAssistTargetingSubModule.b(10.0f, 35.0f, f8);
            f10 *= 1.0f + f7 * 1.5f;
            f10 = Math.max(90.0f, Math.min(1080.0f, f10));
            f9 = Math.max(70.0f, Math.min(500.0f, f9));
            f6 = MathUtil.wrapAngleTo180(f22 - this.JO);
            f5 = MathUtil.wrapAngleTo180(f23 - this.p);
            if (!bl4) {
                f6 = Math.max(-f10 * f18, Math.min(f10 * f18, f6));
            }
            if (!bl) {
                f5 = Math.max(-f9 * f18, Math.min(f9 * f18, f5));
            }
            f22 = this.JO + f6;
            f23 = this.p + f5;
        }
        f17 = (float)Math.pow(0.02, f18);
        this.o *= f17;
        if (this.o < 0.01f) {
            this.o = 0.0f;
        }
        f16 = 1.0f + 3.0f * this.o;
        f15 = RotationManager.s(entityPlayerSP);
        f14 = RotationManager.g(entityPlayerSP);
        f13 = MathUtil.wrapAngleTo180(f22 - f15);
        f12 = MathUtil.wrapAngleTo180(f23 - f14);
        if (!aimAssist.U().L().booleanValue()) {
            f12 = 0.0f;
        }
        f11 = Math.abs(f13);
        f10 = Math.abs(f12);
        f9 = (float)Math.sqrt(f11 * f11 + f10 * f10);
        this.J6 = AimAssistTargetingSubModule.S(bl2, this.J6, f11);
        this.JD = AimAssistTargetingSubModule.S(bl3, this.JD, f10);
        bl4 = bl2 && this.J6;
        bl = bl3 && this.JD;
        f8 = 1.0f - AimAssistTargetingSubModule.b(1.5f, 8.0f, f9);
        if (this.JP) {
            f7 = -(f11 - Math.abs(this.JJ)) / f18;
            f6 = Math.max(-0.3f, Math.min(0.3f, f7 / 20.0f));
            f8 += f6;
            f8 = Math.max(0.0f, Math.min(1.0f, f8));
        }
        f7 = f8 > this.s ? Math.max(0.01f, Math.min(0.25f, f18 * 3.0f)) : Math.max(0.05f, Math.min(0.8f, f18 * 20.0f));
        this.s += (f8 - this.s) * f7;
        f6 = this.s;
        f5 = 0.0f;
        float f24 = 0.0f;
        float f25 = 0.0f;
        float f26 = 0.0f;
        if (this.JP) {
            f4 = MathUtil.wrapAngleTo180(f22 - this.JO) / f18;
            f3 = MathUtil.wrapAngleTo180(f23 - this.p) / f18;
            f25 = MathUtil.wrapAngleTo180(f15 - this.S) / f18;
            f26 = MathUtil.wrapAngleTo180(f14 - this.J9) / f18;
            f2 = Math.max(0.05f, Math.min(0.45f, f18 * 12.0f));
            this.F += (f4 - this.F) * f2;
            this.Jm += (f3 - this.Jm) * f2;
            f5 = this.F;
            f24 = this.Jm;
        }
        f4 = ((Double)aimAssist.w$src$Lgg_vape_value_NumberValue_$cwexni().K()).floatValue() * 0.75f;
        f3 = ((Double)aimAssist.F$src$Lgg_vape_value_NumberValue_$cqv0bx().K()).floatValue() * 0.75f;
        f2 = Math.max(0.0f, Math.min(1.0f, (f4 - 10.0f) / 90.0f));
        float f27 = Math.max(0.0f, Math.min(1.0f, (f3 - 10.0f) / 90.0f));
        if (Math.signum(f13) != Math.signum(this.JJ) && Math.abs(f13) > 0.1f && Math.abs(this.JJ) > 0.1f) {
            this.H *= 0.3f;
        }
        if (Math.signum(f12) != Math.signum(this.Jp) && Math.abs(f12) > 0.1f && Math.abs(this.Jp) > 0.1f) {
            this.J *= 0.3f;
        }
        float f28 = f6 * f6;
        float f29 = 1.0f - f2;
        float f30 = 1.0f - f27;
        this.H += f13 * f18 * f28 * f29;
        this.J += f12 * f18 * f28 * f30;
        float f31 = 1.0f - (1.0f - f6) * Math.max(0.0f, Math.min(0.5f, f18 * 5.0f));
        this.H *= f31;
        this.J *= f31;
        float f32 = 15.0f * (1.0f - f2 * 0.9f);
        float f33 = 10.0f * (1.0f - f27 * 0.9f);
        this.H = Math.max(-f32, Math.min(f32, this.H));
        this.J = Math.max(-f33, Math.min(f33, this.J));
        float f34 = this.JP ? (f13 - this.JJ) / f18 : 0.0f;
        float f35 = this.JP ? (f12 - this.Jp) / f18 : 0.0f;
        float f36 = 0.15f;
        this.P = this.P * (1.0f - f36) + f34 * f36;
        this.Jw = this.Jw * (1.0f - f36) + f35 * f36;
        float f37 = aimAssist.F.K() == aimAssist.r ? 1.5f : 0.5f;
        float f38 = Math.signum(f13);
        this.Jk = f38 != this.r && Math.abs(f13) > f37 ? Math.min(this.Jk + 1.0f, 8.0f) : Math.max(0.0f, this.Jk - f18 * 3.0f);
        this.r = f38;
        float f39 = Math.signum(f12);
        this.J4 = f39 != this.Jj && Math.abs(f12) > f37 ? Math.min(this.J4 + 1.0f, 8.0f) : Math.max(0.0f, this.J4 - f18 * 3.0f);
        this.Jj = f39;
        float f40 = Math.max(0.0f, Math.min(1.0f, this.Jk / 5.0f));
        float f41 = Math.max(0.0f, Math.min(1.0f, this.J4 / 5.0f));
        float f42 = Math.min(f4, 10.0f);
        float f43 = Math.min(f3, 10.0f);
        float f44 = (f42 - 1.0f) / 9.0f;
        float f45 = (f43 - 1.0f) / 9.0f;
        f44 *= f44;
        f45 *= f45;
        float f46 = 0.15f + 0.85f * Math.max(0.0f, Math.min(1.0f, f44));
        float f47 = 0.15f + 0.85f * Math.max(0.0f, Math.min(1.0f, f45));
        float f48 = AimAssistTargetingSubModule.Z(f6, 8.0f, 2.5f + f42 * 0.15f) * f46;
        float f49 = AimAssistTargetingSubModule.Z(f6, 7.0f, 2.2f + f43 * 0.13f) * f47;
        float f50 = AimAssistTargetingSubModule.Z(f6, 0.15f, 0.8f + f42 * 0.04f) * f46;
        float f51 = AimAssistTargetingSubModule.Z(f6, 0.12f, 0.65f + f43 * 0.035f) * f47;
        float f52 = AimAssistTargetingSubModule.Z(f6, 0.08f, 0.25f) * f46;
        float f53 = AimAssistTargetingSubModule.Z(f6, 0.06f, 0.2f) * f47;
        float f54 = (0.85f + f42 * 0.015f) * f46;
        float f55 = (0.82f + f43 * 0.013f) * f47;
        float f56 = AimAssistTargetingSubModule.Z(f6, 0.1f, 0.3f);
        float f57 = AimAssistTargetingSubModule.Z(f6, 0.08f, 0.25f);
        float f58 = 1.0f - 0.6f * f40;
        float f59 = 1.0f + 2.0f * f40;
        float f60 = 1.0f - 0.6f * f41;
        float f61 = 1.0f + 2.0f * f41;
        f49 *= f60;
        f53 *= f61;
        float f62 = (f48 *= f58) * f13 + f50 * this.H + (f52 *= f59) * this.P + f54 * f5 - f56 * f25;
        float f63 = 0.0f;
        if (aimAssist.U().L().booleanValue()) {
            f63 = f49 * f12 + f51 * this.J + f53 * this.Jw + f55 * f24 - f57 * f26;
        } else {
            this.J = 0.0f;
            this.Jw = 0.0f;
        }
        if (!bl) {
            f63 += this.T(f14, f18, l);
        }
        if (!aimAssist.U().L().booleanValue() || Math.abs(this.Jy) > 0.08f) {
            // empty if block
        }
        float f64 = 1.0f;
        if (aimAssist.R().L().booleanValue() && Math.abs(f = entityPlayerSP.movementInput().T()) > 0.01f) {
            boolean bl6;
            boolean bl7 = f13 > 0.0f;
            boolean bl8 = bl6 = bl7 && f < 0.0f || !bl7 && f > 0.0f;
            if (bl6) {
                f64 = 1.15f;
            }
        }
        f62 *= f64;
        f = AimAssistTargetingSubModule.b(8.0f, 40.0f, f11);
        float f65 = 1.0f + f * 2.5f;
        float f66 = (22.0f + f42 * 15.0f) * f46 * f16 * f65;
        float f67 = (18.0f + f43 * 13.0f) * f47 * f16;
        float f68 = (Math.abs(f5) * 0.4f + 18.0f) * (0.35f + f46 * 0.65f) * f65;
        float f69 = (Math.abs(f24) * 0.38f + 14.0f) * (0.35f + f47 * 0.65f);
        float f70 = Math.min(400.0f * f16 * f65, Math.max(f66, f68));
        float f71 = Math.min(300.0f * f16, Math.max(f67, f69));
        f62 = Math.max(-f70, Math.min(f70, f62));
        f63 = Math.max(-f71, Math.min(f71, f63));
        float f72 = AimAssistTargetingSubModule.b(0.5f, 3.0f, (float)d17);
        float f73 = AimAssistTargetingSubModule.Z(f, 0.15f, 0.65f);
        float f74 = AimAssistTargetingSubModule.Z(f6, f73 + (1.0f - f73) * f72, 0.4f + 0.6f * f72);
        float f75 = AimAssistTargetingSubModule.Z(f6, 0.2f + 0.8f * f72, 0.45f + 0.55f * f72);
        f62 *= f74;
        f63 *= f75;
        float f76 = (float)(l - this.Jz) / 1.0E9f;
        float f77 = (float)(Math.sin((double)f76 * 62.83) * 0.4 + Math.sin((double)f76 * 47.12) * 0.25 + Math.sin((double)f76 * 78.54) * 0.15);
        float f78 = (float)(Math.sin((double)f76 * 56.55 + 1.3) * 0.35 + Math.sin((double)f76 * 43.98 + 0.7) * 0.2 + Math.sin((double)f76 * 72.26 + 2.1) * 0.12);
        float f79 = (float)(Math.sin((double)f76 * 12.57) * 0.8 + Math.sin((double)f76 * 7.85) * 0.5);
        float f80 = (float)(Math.sin((double)f76 * 10.47 + 0.9) * 0.6 + Math.sin((double)f76 * 5.65 + 1.8) * 0.4);
        float f81 = (float)(Math.sin((double)f76 * 1.26) * 0.3);
        float f82 = (float)(Math.sin((double)f76 * 0.94 + 0.5) * 0.2);
        float f83 = 0.15f + 0.85f * f6;
        float f84 = 0.5f + 0.5f * (1.0f - f44);
        float f85 = (f77 + f79 + f81) * f83 * f84 * 1.5f;
        float f86 = (f78 + f80 + f82) * f83 * f84 * 1.0f;
        if (!bl4) {
            f62 += f85;
        }
        if (aimAssist.U().L().booleanValue() && !bl) {
            f63 += f86;
        }
        float f87 = f62 * f18;
        float f88 = f63 * f18;
        float f89 = Minecraft.gameSettings().y();
        float f90 = f89 * 0.6f + 0.2f;
        float f91 = f90 * f90 * f90 * 8.0f;
        float f92 = f91 * 0.15f;
        if (f92 > 1.0E-5f) {
            float f93;
            float f94 = f87 / f92;
            float f95 = f88 / f92;
            float f96 = f93 = aimAssist.U().L() != false ? f27 : 0.0f;
            if (bl4) {
                this.v = 0.0f;
            }
            if (bl) {
                this.V = 0.0f;
            }
            if (!bl4 && !bl && (f2 > 0.0f || f93 > 0.0f)) {
                float f97 = f13 / f92;
                float f98 = f97 - this.v;
                float f99 = f4 * 2.0f;
                f98 = Math.max(-f99, Math.min(f99, f98));
                float f100 = aimAssist.U().L() != false ? f12 / f92 : 0.0f;
                float f101 = f100 - this.V;
                float f102 = f3 * 2.0f;
                f101 = Math.max(-f102, Math.min(f102, f101));
                this.v += f94 * (1.0f - f2) + f98 * f2;
                this.V += f95 * (1.0f - f93) + f101 * f93;
            } else if (!bl4 && !bl) {
                this.v += f94;
                this.V += f95;
            } else {
                float f103;
                float f104;
                float f105;
                if (!bl4) {
                    if (f2 > 0.0f) {
                        f105 = f13 / f92;
                        f104 = f105 - this.v;
                        f103 = f4 * 2.0f;
                        f104 = Math.max(-f103, Math.min(f103, f104));
                        this.v += f94 * (1.0f - f2) + f104 * f2;
                    } else {
                        this.v += f94;
                    }
                }
                if (!bl) {
                    if (f93 > 0.0f) {
                        f105 = aimAssist.U().L() != false ? f12 / f92 : 0.0f;
                        f104 = f105 - this.V;
                        f103 = f3 * 2.0f;
                        f104 = Math.max(-f103, Math.min(f103, f104));
                        this.V += f95 * (1.0f - f93) + f104 * f93;
                    } else {
                        this.V += f95;
                    }
                }
            }
        }
        if (!this.JP) {
            this.JP = true;
        }
        this.JJ = f13;
        this.Jp = f12;
        this.JO = f22;
        this.p = f23;
        this.S = f15;
        this.J9 = f14;
    }

    @Override
    public void onEnable() {
        this.r$src$V$a4y57();
        if (!this.A) {
            this.A = true;
            new AimAssistWorkerThread(this).start();
        }
    }

    private static float Z(float f, float f2, float f3) {
        return f2 + f * (f3 - f2);
    }

    private void Y() {
        AimAssist aimAssist = (AimAssist)this.getParent();
        if (Minecraft.theWorld().isNull() || Minecraft.thePlayer().isNull()) {
            return;
        }
        if (!aimAssist.K()) {
            this.r$src$V$a4y57();
            this.J_ = null;
            return;
        }
        if (this.J_ != null && this.J_.isNull()) {
            this.J_ = null;
        }
        if (aimAssist.r$src$Lgg_vape_value_BooleanValue_$f5ztnc().L().booleanValue() && !gg.vape.config.ClientSettings.M()) {
            this.J_ = null;
            this.r$src$V$a4y57();
            return;
        }
        if (this.J_ != null && (RotationUtil.C(this.J_) || (double)Minecraft.thePlayer().getDistanceToEntity(this.J_) > (Double)aimAssist.W().K())) {
            this.r$src$V$a4y57();
            this.J_ = null;
        }
        if (aimAssist.r$src$Lgg_vape_value_BooleanValue_$f5ztnc().L().booleanValue() && gg.vape.config.ClientSettings.M() && this.J_ == null || !aimAssist.r$src$Lgg_vape_value_BooleanValue_$f5ztnc().L().booleanValue()) {
            EntityLivingBase entityLivingBase = aimAssist.M$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1qf3v8a();
            if (!aimAssist.r$src$Lgg_vape_value_BooleanValue_$f5ztnc().L().booleanValue()) {
                ++this.JL;
                if (this.JL > 700 || this.J_ == null) {
                    if (this.J_ == null || !this.J_.equals(entityLivingBase)) {
                        this.r$src$V$a4y57();
                    }
                    this.J_ = entityLivingBase;
                    this.JL = 0;
                }
            } else {
                if (this.J_ == null || !this.J_.equals(entityLivingBase)) {
                    this.r$src$V$a4y57();
                }
                this.J_ = entityLivingBase;
            }
        }
        if (Minecraft.theWorld().getObject() == null) {
            return;
        }
        if (this.J_ != null && Minecraft.currentScreen().getObject() == null && ClientSettings.fW.P) {
            this.k$src$V$6adzo();
        } else {
            this.J_ = null;
            this.r$src$V$a4y57();
        }
    }

    private void r$src$V$a4y57() {
        this.v = 0.0f;
        this.V = 0.0f;
        this.J6 = false;
        this.JD = false;
        this.H = 0.0f;
        this.J = 0.0f;
        this.P = 0.0f;
        this.Jw = 0.0f;
        this.JJ = 0.0f;
        this.Jp = 0.0f;
        this.JO = 0.0f;
        this.p = 0.0f;
        this.F = 0.0f;
        this.Jm = 0.0f;
        this.S = 0.0f;
        this.J9 = 0.0f;
        this.JP = false;
        this.JR = 0L;
        this.Js = false;
        this.J2 = 0.0;
        this.Ju = 0.0;
        this.J5 = 0.0;
        this.U = 0.0;
        this.J7 = 0.0;
        this.JF = 0.0;
        this.s = 0.0f;
        this.Jk = 0.0f;
        this.J4 = 0.0f;
        this.r = 0.0f;
        this.Jj = 0.0f;
        this.o = 0.0f;
        this.L = 0.0;
        this.O = 0.0;
        this.Jy = 0.0f;
        this.Z = 0.0f;
        this.D = 0.15f + this.Jb.nextFloat() * 0.35f;
        this.Jn = 0.0f;
        this.c = 1.0f;
        this.JK = 1.0f;
        this.JA = false;
        this.JS = 0.0;
        this.JV = 0.0;
        this.C = 0.0;
        this.Jz = System.nanoTime();
        this.b = 0.0f;
        this.JT = 0.0f;
        this.I = 0.0f;
        this.J3 = 0.0f;
        this.t = 0L;
    }

    @EventHandler
    public void y(EventPreRenderTick eventPreRenderTick) {
        boolean bl;
        if (eventPreRenderTick.getWorld().isNull() || this.J_ == null) {
            return;
        }
        AimAssist aimAssist = (AimAssist)this.getParent();
        boolean bl2 = ((Double)aimAssist.w$src$Lgg_vape_value_NumberValue_$cwexni().K()).floatValue() > 20.0f;
        boolean bl3 = aimAssist.U().L() != false && ((Double)aimAssist.F$src$Lgg_vape_value_NumberValue_$cqv0bx().K()).floatValue() > 20.0f;
        boolean bl4 = bl2 && this.J6;
        boolean bl5 = bl3 && this.JD;
        int n = bl4 ? Math.round(this.v) : (int)this.v;
        int n2 = bl5 ? Math.round(this.V) : (int)this.V;
        float f = bl4 ? 0.0f : this.v - (float)n;
        float f2 = bl5 ? 0.0f : this.V - (float)n2;
        boolean bl6 = Math.abs(n) > 0;
        boolean bl7 = bl = Math.abs(n2) > 0;
        if (!bl6) {
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
        float f8 = f6;
        float f9 = -f7;
        if ((bl4 || bl5) && Minecraft.thePlayer().isNotNull()) {
            EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
            double[] dArray = this.w(entityPlayerSP, this.J_);
            double d = dArray[0] - entityPlayerSP.c();
            double d2 = dArray[2] - entityPlayerSP.Z();
            double d3 = dArray[1] - (entityPlayerSP.A() + (double)entityPlayerSP.X());
            double d4 = Math.sqrt(d * d + d2 * d2);
            float f10 = (float)(Math.toDegrees(Math.atan2(d2, d)) - 90.0);
            float f11 = (float)(-Math.toDegrees(Math.atan2(d3, Math.max(d4, 1.0E-4))));
            if (bl4) {
                f8 = MathUtil.wrapAngleTo180(f10 - RotationManager.s(entityPlayerSP)) / 0.15f;
            }
            if (bl5) {
                f9 = -MathUtil.wrapAngleTo180(f11 - RotationManager.g(entityPlayerSP)) / 0.15f;
            }
        }
        PlayerMouseRotationApplier.j(f8, f9);
        this.v = f;
        this.V = f2;
    }

    private double[] N(EntityPlayerSP entityPlayerSP, EntityLivingBase entityLivingBase) {
        AimAssist aimAssist = (AimAssist)this.getParent();
        double d = entityPlayerSP.A() + (double)entityPlayerSP.X();
        double d2 = entityLivingBase.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().getMinY();
        double d3 = entityLivingBase.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().getMaxY();
        double d4 = d3 - d2;
        double d5 = d2 + d4 * 0.65;
        double d6 = 0.1;
        double d7 = Math.min(0.85, (double)this.Z);
        double d8 = d6 + d7;
        double d9 = Math.max(d2 + 0.01, Math.min(d3 - 0.01, d - d8));
        double d10 = Math.max(0.0, Math.min(1.0, d7 / 0.55));
        double d11 = d9 + (d5 - d9) * d10;
        d11 = Math.max(d2 + 0.01, Math.min(d3 - 0.01, d11));
        if (aimAssist.F.K() == aimAssist.r) {
            Vec3d vec3d = RotationUtil.T(entityPlayerSP, entityLivingBase.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl(), 0.0, 0.0, 0.0);
            double d12 = Math.max(d2 + 0.01, Math.min(d3 - 0.01, vec3d.t() - d8));
            d12 += (d5 - d12) * d10;
            d12 = Math.max(d2 + 0.01, Math.min(d3 - 0.01, d12));
            double d13 = vec3d.Y();
            double d14 = d12;
            double d15 = vec3d.o();
            if (this.JA) {
                double d16 = 0.35;
                d13 = this.JS + (d13 - this.JS) * d16;
                d14 = this.JV + (d14 - this.JV) * d16;
                d15 = this.C + (d15 - this.C) * d16;
            }
            this.JS = d13;
            this.JV = d14;
            this.C = d15;
            this.JA = true;
            return new double[]{d13, d14, d15};
        }
        return new double[]{entityLivingBase.c(), d11, entityLivingBase.Z()};
    }

    private static float b(float f, float f2, float f3) {
        float f4 = Math.max(0.0f, Math.min(1.0f, (f3 - f) / (f2 - f)));
        return f4 * f4 * (3.0f - 2.0f * f4);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Nullable
    public EntityLivingBase S$src$Lgg_vape_wrapper_impl_EntityLivingBase_$15eeuu3() {
        return this.J_;
    }

    public AimAssistTargetingSubModule(Mod mod, String string) {
        super(mod, string);
    }

    private float T(float f, float f2, long l) {
        float f3;
        float f4;
        float f5 = 0.65f + 0.35f * this.s;
        if (this.t == 0L || l >= this.t) {
            f4 = AimAssistTargetingSubModule.Z(f5, 0.05f, 0.15f);
            f3 = f > 22.0f ? -0.18f : (f < -22.0f ? 0.18f : 0.0f);
            this.I = (this.Jb.nextFloat() * 2.0f - 1.0f) * f4 + f3;
            this.I = Math.max(-0.5f, Math.min(0.5f, this.I));
            long l2 = 300L + (long)this.Jb.nextInt(420);
            this.t = l + l2 * 1000000L;
        }
        f4 = this.I;
        f3 = AimAssistTargetingSubModule.Z(f5, 2.0f, 5.0f);
        float f6 = (float)Math.pow(0.04f, f2);
        this.JT += (f4 - this.b) * f3 * f2;
        this.JT *= f6;
        this.b += this.JT * f2;
        float f7 = AimAssistTargetingSubModule.Z(f5, 0.45f, 0.8f);
        if (this.b > f7) {
            this.b = f7;
            this.JT = Math.min(0.0f, this.JT);
        } else if (this.b < -f7) {
            this.b = -f7;
            this.JT = Math.max(0.0f, this.JT);
        }
        this.J3 += (this.Jb.nextFloat() * 2.0f - 1.0f - this.J3) * Math.max(0.02f, Math.min(0.18f, f2 * 5.0f));
        float f8 = (float)(l - this.Jz) / 1.0E9f;
        float f9 = (float)(Math.sin((double)f8 * 8.7 + 0.4) * (double)0.35f + Math.sin((double)f8 * 13.1 + 2.2) * (double)0.22f + Math.sin((double)f8 * 19.6 + 1.1) * (double)0.12f + (double)(this.J3 * 0.3f));
        float f10 = -this.b * AimAssistTargetingSubModule.Z(f5, 1.4f, 3.0f);
        float f11 = this.JT * 0.25f + f10 + f9 * AimAssistTargetingSubModule.Z(f5, 0.35f, 0.9f);
        float f12 = 1.0f - 0.85f * AimAssistTargetingSubModule.b(72.0f, 88.0f, Math.abs(f));
        float f13 = AimAssistTargetingSubModule.Z(f5, 0.25f, 0.55f) * f12;
        f11 = Math.max(-f13, Math.min(f13, f11));
        return f11;
    }

    private static boolean S(boolean bl, boolean bl2, float f) {
        if (!bl) {
            return false;
        }
        return bl2 ? f <= 7.0f : f <= 5.0f;
    }

    @Override
    public void onDisable() {
        this.J_ = null;
        this.r$src$V$a4y57();
    }
}

