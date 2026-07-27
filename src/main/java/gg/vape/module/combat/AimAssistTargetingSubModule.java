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
    private float pitchVelocity;
    private float lastYawDiff;
    private double predictedZ;
    private float pendingPitch;
    private boolean aimPointInitialized;
    private float airFactor;
    private boolean predictionInitialized;
    private static final float SNAP_ENTER_THRESHOLD = 5.0f;
    private long lastFrameNanos;
    private double lastGroundEyeY;
    private float speedScaleY;
    private float lastPlayerPitch;
    private float pitchBias;
    private float pendingYaw;
    private float overshoot;
    private float driftPos;
    private float randomJitterAmount;
    private double leadY;
    private double leadX;
    private boolean yawSnapped;
    private static final float SNAP_MAX_ANGLE = 20.0f;
    private long driftNextNanos;
    private double leadZ;
    private float pitchFlickTicks;
    private static final float SNAP_EXIT_THRESHOLD = 7.0f;
    private float lastPitchSign;
    private float aimStrength;
    private double aimX;
    private float driftVelocity;
    private float yawAccel;
    private double predictedY;
    private float verticalVelocity;
    private float yawBias;
    private long noiseStartNanos;
    private float lastTargetPitch;
    private double lastEyeY;
    private float driftTarget;
    @Nullable
    private EntityLivingBase target;
    private float speedScaleX;
    private float lastYawSign;
    private float driftNoise;
    private float reactionBias;
    private double predictedX;
    private float lastPlayerYaw;
    private float yawFlickTicks;
    private int targetSwitchTicks;
    private float pitchAccel;
    private boolean pitchSnapped;
    private float lastTargetYaw;
    private float yawVelocity;
    private final Random random = new Random();
    private boolean workerStarted;
    private double aimY;
    private float lastPitchDiff;
    private boolean initialized;
    private double aimZ;

    public static void c(AimAssistTargetingSubModule aimAssistTargetingSubModule) {
        aimAssistTargetingSubModule.runTargetLoop();
    }

    private double[] resolveSnapPoint(EntityPlayerSP entityPlayerSP, EntityLivingBase entityLivingBase) {
        AimAssist aimAssist = (AimAssist)this.getParent();
        double d = entityPlayerSP.A() + (double)entityPlayerSP.X();
        double d2 = entityLivingBase.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().getMinY();
        double d3 = entityLivingBase.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().getMaxY();
        double d4 = d3 - d2;
        double d5 = d2 + d4 * 0.65;
        double d6 = Math.min(0.85, (double)this.airFactor);
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

    private void updateAim() {
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
        boolean bl4 = bl2 && this.yawSnapped;
        boolean bl5 = bl = bl3 && this.pitchSnapped;
        if (this.target == null || this.target.isNull()) {
            this.reset();
            return;
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            this.reset();
            return;
        }
        long l = System.nanoTime();
        if (!this.initialized || this.lastFrameNanos == 0L) {
            f18 = 0.016666668f;
        } else {
            f18 = (float)(l - this.lastFrameNanos) / 1.0E9f;
            f18 = Math.max(0.008333334f, Math.min(0.12f, f18));
        }
        this.lastFrameNanos = l;
        double[] dArray = this.resolveAimTarget(entityPlayerSP, this.target);
        double d3 = dArray[0];
        double d4 = dArray[1];
        double d5 = dArray[2];
        double d6 = this.target.t();
        double d7 = this.target.q();
        double d8 = this.target.T();
        double d9 = d6 - entityPlayerSP.t();
        double d10 = d7 - entityPlayerSP.q();
        double d11 = d8 - entityPlayerSP.T();
        double d12 = d6 + d9 * 0.08;
        double d13 = d7 + d10 * 0.1;
        double d14 = d8 + d11 * 0.08;
        if (!this.aimPointInitialized) {
            this.aimX = d3;
            this.aimY = d4;
            this.aimZ = d5;
            this.leadX = d12;
            this.leadY = d13;
            this.leadZ = d14;
            this.aimPointInitialized = true;
        }
        double d15 = Math.sqrt(d6 * d6 + d8 * d8);
        double d16 = Math.sqrt(d9 * d9 + d11 * d11);
        double d17 = entityPlayerSP.getDistanceToEntity(this.target);
        double d18 = aimAssist.F.K() == aimAssist.r ? 0.3 : 0.28;
        double d19 = d18 + Math.min(0.35, d16 * 0.5);
        d19 = Math.max(0.08, Math.min(0.75, d19));
        double d20 = d15 + d16 * 0.25;
        double d21 = 0.18 + Math.min(0.42, d20 * 0.72);
        d21 = Math.max(0.12, Math.min(0.68, d21));
        double d22 = Math.max(0.45, Math.min(2.4, (double)f18 * 60.0));
        double d23 = 1.0 - Math.pow(1.0 - d19, d22);
        double d24 = 1.0 - Math.pow(1.0 - d21, d22);
        this.aimX += (d3 - this.aimX) * d23;
        this.aimY += (d4 - this.aimY) * d23;
        this.aimZ += (d5 - this.aimZ) * d23;
        this.leadX += (d12 - this.leadX) * d24;
        this.leadY += (d13 - this.leadY) * d24;
        this.leadZ += (d14 - this.leadZ) * d24;
        double d25 = d3 - this.aimX;
        double d26 = d5 - this.aimZ;
        double d27 = Math.sqrt(d25 * d25 + d26 * d26);
        if (d27 > (d2 = 0.3 + d15 * 1.3 + d16 * 0.4)) {
            this.aimX = d3;
            this.aimY = d4;
            this.aimZ = d5;
            this.leadX = d12;
            this.leadY = d13;
            this.leadZ = d14;
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
        if (this.initialized && this.lastEyeY != 0.0) {
            f19 = (float)((d32 - this.lastEyeY) / (double)f18);
        }
        this.verticalVelocity += (f19 - this.verticalVelocity) * 0.65f;
        this.lastEyeY = d32;
        if (entityPlayerSP.b$src$Z$fqlxe4()) {
            this.lastGroundEyeY = d32;
            this.airFactor *= 0.35f;
        } else {
            if (this.lastGroundEyeY == 0.0) {
                this.lastGroundEyeY = d32;
            }
            d = Math.max(0.0, d32 - this.lastGroundEyeY);
            float f20 = (float)Math.min(0.7, Math.max(0.0, (double)this.verticalVelocity) * 0.34);
            float f21 = (float)Math.min(0.82, d * 0.58);
            this.airFactor = Math.max(this.airFactor * 0.92f, Math.max(f20, f21));
        }
        d = this.aimX;
        double d33 = this.aimZ;
        double d34 = this.aimY - d32;
        double d35 = Math.abs(d34);
        double d36 = Math.max(0.0, Math.min(1.0, d35 / 0.7));
        double d37 = Math.min(d28, 0.7 + d17 * 0.04);
        double d38 = 0.28 + 0.52 * d36;
        double d39 = this.leadY * d37 * d38;
        double d40 = 0.16 + d17 * 0.055;
        d39 = Math.max(-d40, Math.min(d40, d39));
        double d41 = this.aimY + d39;
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
        if (this.initialized) {
            f17 = Math.abs(MathUtil.wrapAngleTo180(f22 - this.lastTargetYaw));
            f16 = Math.abs(MathUtil.wrapAngleTo180(f23 - this.lastTargetPitch));
            f15 = aimAssist.F.K() == aimAssist.r ? 20.0f : 12.0f;
            f14 = Math.max(f17, f16);
            f13 = Math.max(0.0f, Math.min(1.0f, (f14 - f15) / 50.0f));
            this.overshoot = Math.max(this.overshoot, f13);
            if (f13 > 0.3f) {
                this.aimStrength *= 0.3f;
                this.aimX = d3;
                this.aimY = d4;
                this.aimZ = d5;
            }
            f12 = 1.0f + Math.min(2.0f, f17 / 60.0f);
            f11 = 1.0f + Math.min(2.0f, f16 / 60.0f);
            f10 = (120.0f + (float)(d16 * 700.0)) * f12;
            f9 = (95.0f + (float)(Math.abs(this.leadY) * 550.0)) * f11;
            f8 = Math.abs(MathUtil.wrapAngleTo180(f22 - RotationManager.s(entityPlayerSP)));
            f7 = AimAssistTargetingSubModule.smoothStep(10.0f, 35.0f, f8);
            f10 *= 1.0f + f7 * 1.5f;
            f10 = Math.max(90.0f, Math.min(1080.0f, f10));
            f9 = Math.max(70.0f, Math.min(500.0f, f9));
            f6 = MathUtil.wrapAngleTo180(f22 - this.lastTargetYaw);
            f5 = MathUtil.wrapAngleTo180(f23 - this.lastTargetPitch);
            if (!bl4) {
                f6 = Math.max(-f10 * f18, Math.min(f10 * f18, f6));
            }
            if (!bl) {
                f5 = Math.max(-f9 * f18, Math.min(f9 * f18, f5));
            }
            f22 = this.lastTargetYaw + f6;
            f23 = this.lastTargetPitch + f5;
        }
        f17 = (float)Math.pow(0.02, f18);
        this.overshoot *= f17;
        if (this.overshoot < 0.01f) {
            this.overshoot = 0.0f;
        }
        f16 = 1.0f + 3.0f * this.overshoot;
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
        this.yawSnapped = AimAssistTargetingSubModule.shouldSnap(bl2, this.yawSnapped, f11);
        this.pitchSnapped = AimAssistTargetingSubModule.shouldSnap(bl3, this.pitchSnapped, f10);
        bl4 = bl2 && this.yawSnapped;
        bl = bl3 && this.pitchSnapped;
        f8 = 1.0f - AimAssistTargetingSubModule.smoothStep(1.5f, 8.0f, f9);
        if (this.initialized) {
            f7 = -(f11 - Math.abs(this.lastYawDiff)) / f18;
            f6 = Math.max(-0.3f, Math.min(0.3f, f7 / 20.0f));
            f8 += f6;
            f8 = Math.max(0.0f, Math.min(1.0f, f8));
        }
        f7 = f8 > this.aimStrength ? Math.max(0.01f, Math.min(0.25f, f18 * 3.0f)) : Math.max(0.05f, Math.min(0.8f, f18 * 20.0f));
        this.aimStrength += (f8 - this.aimStrength) * f7;
        f6 = this.aimStrength;
        f5 = 0.0f;
        float f24 = 0.0f;
        float f25 = 0.0f;
        float f26 = 0.0f;
        if (this.initialized) {
            f4 = MathUtil.wrapAngleTo180(f22 - this.lastTargetYaw) / f18;
            f3 = MathUtil.wrapAngleTo180(f23 - this.lastTargetPitch) / f18;
            f25 = MathUtil.wrapAngleTo180(f15 - this.lastPlayerYaw) / f18;
            f26 = MathUtil.wrapAngleTo180(f14 - this.lastPlayerPitch) / f18;
            f2 = Math.max(0.05f, Math.min(0.45f, f18 * 12.0f));
            this.yawAccel += (f4 - this.yawAccel) * f2;
            this.pitchAccel += (f3 - this.pitchAccel) * f2;
            f5 = this.yawAccel;
            f24 = this.pitchAccel;
        }
        f4 = ((Double)aimAssist.w$src$Lgg_vape_value_NumberValue_$cwexni().K()).floatValue() * 0.75f;
        f3 = ((Double)aimAssist.F$src$Lgg_vape_value_NumberValue_$cqv0bx().K()).floatValue() * 0.75f;
        f2 = Math.max(0.0f, Math.min(1.0f, (f4 - 10.0f) / 90.0f));
        float f27 = Math.max(0.0f, Math.min(1.0f, (f3 - 10.0f) / 90.0f));
        if (Math.signum(f13) != Math.signum(this.lastYawDiff) && Math.abs(f13) > 0.1f && Math.abs(this.lastYawDiff) > 0.1f) {
            this.yawBias *= 0.3f;
        }
        if (Math.signum(f12) != Math.signum(this.lastPitchDiff) && Math.abs(f12) > 0.1f && Math.abs(this.lastPitchDiff) > 0.1f) {
            this.pitchBias *= 0.3f;
        }
        float f28 = f6 * f6;
        float f29 = 1.0f - f2;
        float f30 = 1.0f - f27;
        this.yawBias += f13 * f18 * f28 * f29;
        this.pitchBias += f12 * f18 * f28 * f30;
        float f31 = 1.0f - (1.0f - f6) * Math.max(0.0f, Math.min(0.5f, f18 * 5.0f));
        this.yawBias *= f31;
        this.pitchBias *= f31;
        float f32 = 15.0f * (1.0f - f2 * 0.9f);
        float f33 = 10.0f * (1.0f - f27 * 0.9f);
        this.yawBias = Math.max(-f32, Math.min(f32, this.yawBias));
        this.pitchBias = Math.max(-f33, Math.min(f33, this.pitchBias));
        float f34 = this.initialized ? (f13 - this.lastYawDiff) / f18 : 0.0f;
        float f35 = this.initialized ? (f12 - this.lastPitchDiff) / f18 : 0.0f;
        float f36 = 0.15f;
        this.yawVelocity = this.yawVelocity * (1.0f - f36) + f34 * f36;
        this.pitchVelocity = this.pitchVelocity * (1.0f - f36) + f35 * f36;
        float f37 = aimAssist.F.K() == aimAssist.r ? 1.5f : 0.5f;
        float f38 = Math.signum(f13);
        this.yawFlickTicks = f38 != this.lastYawSign && Math.abs(f13) > f37 ? Math.min(this.yawFlickTicks + 1.0f, 8.0f) : Math.max(0.0f, this.yawFlickTicks - f18 * 3.0f);
        this.lastYawSign = f38;
        float f39 = Math.signum(f12);
        this.pitchFlickTicks = f39 != this.lastPitchSign && Math.abs(f12) > f37 ? Math.min(this.pitchFlickTicks + 1.0f, 8.0f) : Math.max(0.0f, this.pitchFlickTicks - f18 * 3.0f);
        this.lastPitchSign = f39;
        float f40 = Math.max(0.0f, Math.min(1.0f, this.yawFlickTicks / 5.0f));
        float f41 = Math.max(0.0f, Math.min(1.0f, this.pitchFlickTicks / 5.0f));
        float f42 = Math.min(f4, 10.0f);
        float f43 = Math.min(f3, 10.0f);
        float f44 = (f42 - 1.0f) / 9.0f;
        float f45 = (f43 - 1.0f) / 9.0f;
        f44 *= f44;
        f45 *= f45;
        float f46 = 0.15f + 0.85f * Math.max(0.0f, Math.min(1.0f, f44));
        float f47 = 0.15f + 0.85f * Math.max(0.0f, Math.min(1.0f, f45));
        float f48 = AimAssistTargetingSubModule.lerp(f6, 8.0f, 2.5f + f42 * 0.15f) * f46;
        float f49 = AimAssistTargetingSubModule.lerp(f6, 7.0f, 2.2f + f43 * 0.13f) * f47;
        float f50 = AimAssistTargetingSubModule.lerp(f6, 0.15f, 0.8f + f42 * 0.04f) * f46;
        float f51 = AimAssistTargetingSubModule.lerp(f6, 0.12f, 0.65f + f43 * 0.035f) * f47;
        float f52 = AimAssistTargetingSubModule.lerp(f6, 0.08f, 0.25f) * f46;
        float f53 = AimAssistTargetingSubModule.lerp(f6, 0.06f, 0.2f) * f47;
        float f54 = (0.85f + f42 * 0.015f) * f46;
        float f55 = (0.82f + f43 * 0.013f) * f47;
        float f56 = AimAssistTargetingSubModule.lerp(f6, 0.1f, 0.3f);
        float f57 = AimAssistTargetingSubModule.lerp(f6, 0.08f, 0.25f);
        float f58 = 1.0f - 0.6f * f40;
        float f59 = 1.0f + 2.0f * f40;
        float f60 = 1.0f - 0.6f * f41;
        float f61 = 1.0f + 2.0f * f41;
        f49 *= f60;
        f53 *= f61;
        float f62 = (f48 *= f58) * f13 + f50 * this.yawBias + (f52 *= f59) * this.yawVelocity + f54 * f5 - f56 * f25;
        float f63 = 0.0f;
        if (aimAssist.U().L().booleanValue()) {
            f63 = f49 * f12 + f51 * this.pitchBias + f53 * this.pitchVelocity + f55 * f24 - f57 * f26;
        } else {
            this.pitchBias = 0.0f;
            this.pitchVelocity = 0.0f;
        }
        if (!bl) {
            f63 += this.computePitchDrift(f14, f18, l);
        }
        if (!aimAssist.U().L().booleanValue() || Math.abs(this.verticalVelocity) > 0.08f) {
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
        f = AimAssistTargetingSubModule.smoothStep(8.0f, 40.0f, f11);
        float f65 = 1.0f + f * 2.5f;
        float f66 = (22.0f + f42 * 15.0f) * f46 * f16 * f65;
        float f67 = (18.0f + f43 * 13.0f) * f47 * f16;
        float f68 = (Math.abs(f5) * 0.4f + 18.0f) * (0.35f + f46 * 0.65f) * f65;
        float f69 = (Math.abs(f24) * 0.38f + 14.0f) * (0.35f + f47 * 0.65f);
        float f70 = Math.min(400.0f * f16 * f65, Math.max(f66, f68));
        float f71 = Math.min(300.0f * f16, Math.max(f67, f69));
        f62 = Math.max(-f70, Math.min(f70, f62));
        f63 = Math.max(-f71, Math.min(f71, f63));
        float f72 = AimAssistTargetingSubModule.smoothStep(0.5f, 3.0f, (float)d17);
        float f73 = AimAssistTargetingSubModule.lerp(f, 0.15f, 0.65f);
        float f74 = AimAssistTargetingSubModule.lerp(f6, f73 + (1.0f - f73) * f72, 0.4f + 0.6f * f72);
        float f75 = AimAssistTargetingSubModule.lerp(f6, 0.2f + 0.8f * f72, 0.45f + 0.55f * f72);
        f62 *= f74;
        f63 *= f75;
        float f76 = (float)(l - this.noiseStartNanos) / 1.0E9f;
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
                this.pendingYaw = 0.0f;
            }
            if (bl) {
                this.pendingPitch = 0.0f;
            }
            if (!bl4 && !bl && (f2 > 0.0f || f93 > 0.0f)) {
                float f97 = f13 / f92;
                float f98 = f97 - this.pendingYaw;
                float f99 = f4 * 2.0f;
                f98 = Math.max(-f99, Math.min(f99, f98));
                float f100 = aimAssist.U().L() != false ? f12 / f92 : 0.0f;
                float f101 = f100 - this.pendingPitch;
                float f102 = f3 * 2.0f;
                f101 = Math.max(-f102, Math.min(f102, f101));
                this.pendingYaw += f94 * (1.0f - f2) + f98 * f2;
                this.pendingPitch += f95 * (1.0f - f93) + f101 * f93;
            } else if (!bl4 && !bl) {
                this.pendingYaw += f94;
                this.pendingPitch += f95;
            } else {
                float f103;
                float f104;
                float f105;
                if (!bl4) {
                    if (f2 > 0.0f) {
                        f105 = f13 / f92;
                        f104 = f105 - this.pendingYaw;
                        f103 = f4 * 2.0f;
                        f104 = Math.max(-f103, Math.min(f103, f104));
                        this.pendingYaw += f94 * (1.0f - f2) + f104 * f2;
                    } else {
                        this.pendingYaw += f94;
                    }
                }
                if (!bl) {
                    if (f93 > 0.0f) {
                        f105 = aimAssist.U().L() != false ? f12 / f92 : 0.0f;
                        f104 = f105 - this.pendingPitch;
                        f103 = f3 * 2.0f;
                        f104 = Math.max(-f103, Math.min(f103, f104));
                        this.pendingPitch += f95 * (1.0f - f93) + f104 * f93;
                    } else {
                        this.pendingPitch += f95;
                    }
                }
            }
        }
        if (!this.initialized) {
            this.initialized = true;
        }
        this.lastYawDiff = f13;
        this.lastPitchDiff = f12;
        this.lastTargetYaw = f22;
        this.lastTargetPitch = f23;
        this.lastPlayerYaw = f15;
        this.lastPlayerPitch = f14;
    }

    @Override
    public void onEnable() {
        this.reset();
        if (!this.workerStarted) {
            this.workerStarted = true;
            new AimAssistWorkerThread(this).start();
        }
    }

    private static float lerp(float f, float f2, float f3) {
        return f2 + f * (f3 - f2);
    }

    private void runTargetLoop() {
        AimAssist aimAssist = (AimAssist)this.getParent();
        if (Minecraft.theWorld().isNull() || Minecraft.thePlayer().isNull()) {
            return;
        }
        if (!aimAssist.K()) {
            this.reset();
            this.target = null;
            return;
        }
        if (this.target != null && this.target.isNull()) {
            this.target = null;
        }
        if (aimAssist.r$src$Lgg_vape_value_BooleanValue_$f5ztnc().L().booleanValue() && !gg.vape.config.ClientSettings.M()) {
            this.target = null;
            this.reset();
            return;
        }
        if (this.target != null && (RotationUtil.C(this.target) || (double)Minecraft.thePlayer().getDistanceToEntity(this.target) > (Double)aimAssist.W().K())) {
            this.reset();
            this.target = null;
        }
        if (aimAssist.r$src$Lgg_vape_value_BooleanValue_$f5ztnc().L().booleanValue() && gg.vape.config.ClientSettings.M() && this.target == null || !aimAssist.r$src$Lgg_vape_value_BooleanValue_$f5ztnc().L().booleanValue()) {
            EntityLivingBase entityLivingBase = aimAssist.M$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1qf3v8a();
            if (!aimAssist.r$src$Lgg_vape_value_BooleanValue_$f5ztnc().L().booleanValue()) {
                ++this.targetSwitchTicks;
                if (this.targetSwitchTicks > 700 || this.target == null) {
                    if (this.target == null || !this.target.equals(entityLivingBase)) {
                        this.reset();
                    }
                    this.target = entityLivingBase;
                    this.targetSwitchTicks = 0;
                }
            } else {
                if (this.target == null || !this.target.equals(entityLivingBase)) {
                    this.reset();
                }
                this.target = entityLivingBase;
            }
        }
        if (Minecraft.theWorld().getObject() == null) {
            return;
        }
        if (this.target != null && Minecraft.currentScreen().getObject() == null && ClientSettings.fW.P) {
            this.updateAim();
        } else {
            this.target = null;
            this.reset();
        }
    }

    private void reset() {
        this.pendingYaw = 0.0f;
        this.pendingPitch = 0.0f;
        this.yawSnapped = false;
        this.pitchSnapped = false;
        this.yawBias = 0.0f;
        this.pitchBias = 0.0f;
        this.yawVelocity = 0.0f;
        this.pitchVelocity = 0.0f;
        this.lastYawDiff = 0.0f;
        this.lastPitchDiff = 0.0f;
        this.lastTargetYaw = 0.0f;
        this.lastTargetPitch = 0.0f;
        this.yawAccel = 0.0f;
        this.pitchAccel = 0.0f;
        this.lastPlayerYaw = 0.0f;
        this.lastPlayerPitch = 0.0f;
        this.initialized = false;
        this.lastFrameNanos = 0L;
        this.aimPointInitialized = false;
        this.aimX = 0.0;
        this.aimY = 0.0;
        this.aimZ = 0.0;
        this.leadX = 0.0;
        this.leadY = 0.0;
        this.leadZ = 0.0;
        this.aimStrength = 0.0f;
        this.yawFlickTicks = 0.0f;
        this.pitchFlickTicks = 0.0f;
        this.lastYawSign = 0.0f;
        this.lastPitchSign = 0.0f;
        this.overshoot = 0.0f;
        this.lastEyeY = 0.0;
        this.lastGroundEyeY = 0.0;
        this.verticalVelocity = 0.0f;
        this.airFactor = 0.0f;
        this.randomJitterAmount = 0.15f + this.random.nextFloat() * 0.35f;
        this.reactionBias = 0.0f;
        this.speedScaleX = 1.0f;
        this.speedScaleY = 1.0f;
        this.predictionInitialized = false;
        this.predictedX = 0.0;
        this.predictedY = 0.0;
        this.predictedZ = 0.0;
        this.noiseStartNanos = System.nanoTime();
        this.driftPos = 0.0f;
        this.driftVelocity = 0.0f;
        this.driftTarget = 0.0f;
        this.driftNoise = 0.0f;
        this.driftNextNanos = 0L;
    }

    @EventHandler
    public void onPreRenderTick(EventPreRenderTick eventPreRenderTick) {
        boolean bl;
        if (eventPreRenderTick.getWorld().isNull() || this.target == null) {
            return;
        }
        AimAssist aimAssist = (AimAssist)this.getParent();
        boolean bl2 = ((Double)aimAssist.w$src$Lgg_vape_value_NumberValue_$cwexni().K()).floatValue() > 20.0f;
        boolean bl3 = aimAssist.U().L() != false && ((Double)aimAssist.F$src$Lgg_vape_value_NumberValue_$cqv0bx().K()).floatValue() > 20.0f;
        boolean bl4 = bl2 && this.yawSnapped;
        boolean bl5 = bl3 && this.pitchSnapped;
        int n = bl4 ? Math.round(this.pendingYaw) : (int)this.pendingYaw;
        int n2 = bl5 ? Math.round(this.pendingPitch) : (int)this.pendingPitch;
        float f = bl4 ? 0.0f : this.pendingYaw - (float)n;
        float f2 = bl5 ? 0.0f : this.pendingPitch - (float)n2;
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
            double[] dArray = this.resolveSnapPoint(entityPlayerSP, this.target);
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
        this.pendingYaw = f;
        this.pendingPitch = f2;
    }

    private double[] resolveAimTarget(EntityPlayerSP entityPlayerSP, EntityLivingBase entityLivingBase) {
        AimAssist aimAssist = (AimAssist)this.getParent();
        double d = entityPlayerSP.A() + (double)entityPlayerSP.X();
        double d2 = entityLivingBase.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().getMinY();
        double d3 = entityLivingBase.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().getMaxY();
        double d4 = d3 - d2;
        double d5 = d2 + d4 * 0.65;
        double d6 = 0.1;
        double d7 = Math.min(0.85, (double)this.airFactor);
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
            if (this.predictionInitialized) {
                double d16 = 0.35;
                d13 = this.predictedX + (d13 - this.predictedX) * d16;
                d14 = this.predictedY + (d14 - this.predictedY) * d16;
                d15 = this.predictedZ + (d15 - this.predictedZ) * d16;
            }
            this.predictedX = d13;
            this.predictedY = d14;
            this.predictedZ = d15;
            this.predictionInitialized = true;
            return new double[]{d13, d14, d15};
        }
        return new double[]{entityLivingBase.c(), d11, entityLivingBase.Z()};
    }

    private static float smoothStep(float f, float f2, float f3) {
        float f4 = Math.max(0.0f, Math.min(1.0f, (f3 - f) / (f2 - f)));
        return f4 * f4 * (3.0f - 2.0f * f4);
    }

    private static ObfuscatedRuntimeException passthroughException(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Nullable
    public EntityLivingBase S$src$Lgg_vape_wrapper_impl_EntityLivingBase_$15eeuu3() {
        return this.target;
    }

    public AimAssistTargetingSubModule(Mod mod, String string) {
        super(mod, string);
    }

    private float computePitchDrift(float f, float f2, long l) {
        float f3;
        float f4;
        float f5 = 0.65f + 0.35f * this.aimStrength;
        if (this.driftNextNanos == 0L || l >= this.driftNextNanos) {
            f4 = AimAssistTargetingSubModule.lerp(f5, 0.05f, 0.15f);
            f3 = f > 22.0f ? -0.18f : (f < -22.0f ? 0.18f : 0.0f);
            this.driftTarget = (this.random.nextFloat() * 2.0f - 1.0f) * f4 + f3;
            this.driftTarget = Math.max(-0.5f, Math.min(0.5f, this.driftTarget));
            long l2 = 300L + (long)this.random.nextInt(420);
            this.driftNextNanos = l + l2 * 1000000L;
        }
        f4 = this.driftTarget;
        f3 = AimAssistTargetingSubModule.lerp(f5, 2.0f, 5.0f);
        float f6 = (float)Math.pow(0.04f, f2);
        this.driftVelocity += (f4 - this.driftPos) * f3 * f2;
        this.driftVelocity *= f6;
        this.driftPos += this.driftVelocity * f2;
        float f7 = AimAssistTargetingSubModule.lerp(f5, 0.45f, 0.8f);
        if (this.driftPos > f7) {
            this.driftPos = f7;
            this.driftVelocity = Math.min(0.0f, this.driftVelocity);
        } else if (this.driftPos < -f7) {
            this.driftPos = -f7;
            this.driftVelocity = Math.max(0.0f, this.driftVelocity);
        }
        this.driftNoise += (this.random.nextFloat() * 2.0f - 1.0f - this.driftNoise) * Math.max(0.02f, Math.min(0.18f, f2 * 5.0f));
        float f8 = (float)(l - this.noiseStartNanos) / 1.0E9f;
        float f9 = (float)(Math.sin((double)f8 * 8.7 + 0.4) * (double)0.35f + Math.sin((double)f8 * 13.1 + 2.2) * (double)0.22f + Math.sin((double)f8 * 19.6 + 1.1) * (double)0.12f + (double)(this.driftNoise * 0.3f));
        float f10 = -this.driftPos * AimAssistTargetingSubModule.lerp(f5, 1.4f, 3.0f);
        float f11 = this.driftVelocity * 0.25f + f10 + f9 * AimAssistTargetingSubModule.lerp(f5, 0.35f, 0.9f);
        float f12 = 1.0f - 0.85f * AimAssistTargetingSubModule.smoothStep(72.0f, 88.0f, Math.abs(f));
        float f13 = AimAssistTargetingSubModule.lerp(f5, 0.25f, 0.55f) * f12;
        f11 = Math.max(-f13, Math.min(f13, f11));
        return f11;
    }

    private static boolean shouldSnap(boolean bl, boolean bl2, float f) {
        if (!bl) {
            return false;
        }
        return bl2 ? f <= 7.0f : f <= 5.0f;
    }

    @Override
    public void onDisable() {
        this.target = null;
        this.reset();
    }
}

