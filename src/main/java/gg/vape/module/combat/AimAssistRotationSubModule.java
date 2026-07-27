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
    private int randomOffsetX;
    private int swapTickCounter;
    private boolean farFromTarget;
    private float pitchBoost = 0.0f;
    private int randomOffsetY;
    private float yawAccumulator;
    private float pitchVelocity;
    private double prevTargetZ;
    private float pitchAccumulator;
    @Nullable
    private EntityLivingBase target = null;
    double targetY;
    private boolean prevOnLeft;
    private double lastAngleDiff;
    private boolean prevAbove;
    private boolean threadStarted;
    private float yawVelocity;
    private float yawVelocityBuffer;
    private int retargetCounter;
    private float yawBoost = 0.0f;
    private final Random random;
    private double driftTimer;
    double targetZ;
    private int driftY;
    private float pitchVelocityBuffer;
    private int sampleCounter;
    private int driftX;
    double targetX;
    private final Random sharedRandom = new Random();
    private double prevTargetX;

    private void computeTargetOffset() {
        Vec3d vec3d = RotationUtil.T(Minecraft.thePlayer(), this.target.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl(), 0.0, 0.0, 0.0);
        double d = this.target.z() - this.target.f();
        double d2 = this.target.h() - this.target.R();
        double d3 = vec3d.Y() - d;
        double d4 = vec3d.o() - d2;
        float f = Minecraft.getTimer().renderPartialTicks();
        double d5 = d3 + (vec3d.Y() - d3) * (double)f;
        double d6 = d4 + (vec3d.o() - d4) * (double)f;
        this.targetX = d5;
        this.targetZ = d6;
    }

    private void updateDrift() {
        this.driftTimer += 1.0;
        if (this.driftTimer >= (double)(250 + this.random.nextInt(50))) {
            this.driftTimer = MathUtil.randomExclusiveUpper(this.random, -100, -50);
            this.randomOffsetX = MathUtil.randomExclusiveUpper(this.random, -1, 2);
            this.randomOffsetY = MathUtil.randomExclusiveUpper(this.random, -1, 2);
        }
        int n = this.randomOffsetX;
        int n2 = this.randomOffsetY;
        if (this.random.nextInt(10) < 2) {
            // empty if block
        }
        if (this.random.nextInt(10) < 2) {
            // empty if block
        }
        if (this.random.nextInt(10) < 2) {
            n = 0;
        }
        if (this.random.nextInt(10) < 2) {
            n2 = 0;
        }
        if (this.driftTimer < 0.0) {
            n = 0;
            n2 = 0;
        }
        if (this.random.nextInt(20) == 1) {
            this.driftX += n;
            this.driftY += n2;
        }
        if (this.pitchAccumulator > 0.0f && this.driftX < 0 || this.pitchAccumulator < 0.0f && this.driftX > 0) {
            this.driftX = 0;
        }
    }

    @EventHandler
    public void onPreRenderTick(EventPreRenderTick eventPreRenderTick) {
        boolean bl;
        if (eventPreRenderTick.getWorld().isNull()) {
            return;
        }
        if (this.target == null) {
            return;
        }
        this.pitchAccumulator += (float)this.driftX;
        this.yawAccumulator += (float)this.driftY;
        int n = (int)this.pitchAccumulator;
        int n2 = (int)this.yawAccumulator;
        float f = this.pitchAccumulator - (float)n;
        float f2 = this.yawAccumulator - (float)n2;
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
        this.pitchAccumulator = f;
        this.yawAccumulator = f2;
        this.driftX = 0;
        this.driftY = 0;
    }

    public static void n(AimAssistRotationSubModule aimAssistRotationSubModule) {
        aimAssistRotationSubModule.tick();
    }

    void applyYaw(float f, float f2) {
        AimAssist aimAssist = (AimAssist)this.getParent();
        if (f != 0.0f) {
            f *= 5.0f;
            float f3 = ((Double)aimAssist.F$src$Lgg_vape_value_NumberValue_$cqv0bx().K()).floatValue();
            if (f2 <= 10.0f) {
                this.yawBoost = f3;
            }
            if (this.yawBoost > 0.0f) {
                f3 -= this.yawBoost / 3.0f;
                this.yawBoost -= f2 / 200.0f;
            }
            float f4 = 1.0f * f3 * f;
            this.yawAccumulator += f4;
        } else {
            this.yawAccumulator = 0.0f;
        }
    }

    void applyPitch(float f) {
        AimAssist aimAssist = (AimAssist)this.getParent();
        if (f != 0.0f) {
            f *= 5.0f;
            float f2 = ((Double)aimAssist.w$src$Lgg_vape_value_NumberValue_$cwexni().K()).floatValue();
            float f3 = RotationUtil.a(Minecraft.thePlayer(), this.target);
            if (f3 <= 10.0f) {
                this.pitchBoost = f2;
            }
            if (this.pitchBoost > 0.0f) {
                f2 -= this.pitchBoost / 3.0f;
                this.pitchBoost -= f3 / 200.0f;
            }
            float f4 = 1.0f * f2 * f;
            this.pitchAccumulator += f4;
        } else {
            this.pitchAccumulator = 0.0f;
        }
    }

    public AimAssistRotationSubModule(Mod mod, String string) {
        super(mod, string);
        this.random = new Random();
    }

    void V$src$V$1u3xaau() {
        this.pitchAccumulator = 0.0f;
        this.yawAccumulator = 0.0f;
        this.randomOffsetX = 0;
        this.randomOffsetY = 0;
        this.driftX = 0;
        this.driftY = 0;
    }

    private void tick() {
        AimAssist aimAssist = (AimAssist)this.getParent();
        if (Minecraft.theWorld().isNull() || Minecraft.thePlayer().isNull()) {
            return;
        }
        if (!aimAssist.K()) {
            this.V$src$V$1u3xaau();
            return;
        }
        if (this.target != null && this.target.isNull()) {
            this.target = null;
        }
        if (aimAssist.r$src$Lgg_vape_value_BooleanValue_$f5ztnc().L().booleanValue() && !gg.vape.config.ClientSettings.M()) {
            this.target = null;
            this.V$src$V$1u3xaau();
            return;
        }
        if (this.target != null && (RotationUtil.C(this.target) || (double)Minecraft.thePlayer().getDistanceToEntity(this.target) > (Double)aimAssist.W().K())) {
            this.V$src$V$1u3xaau();
            this.target = null;
        }
        if (aimAssist.r$src$Lgg_vape_value_BooleanValue_$f5ztnc().L().booleanValue() && gg.vape.config.ClientSettings.M() && this.target == null || !aimAssist.r$src$Lgg_vape_value_BooleanValue_$f5ztnc().L().booleanValue()) {
            EntityLivingBase entityLivingBase = aimAssist.M$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1qf3v8a();
            if (!aimAssist.r$src$Lgg_vape_value_BooleanValue_$f5ztnc().L().booleanValue()) {
                ++this.retargetCounter;
                if (this.retargetCounter > 700 || this.target == null || !aimAssist.o(this.target)) {
                    this.target = entityLivingBase;
                    this.retargetCounter = 0;
                }
            } else {
                this.target = entityLivingBase;
            }
        }
        if (Minecraft.theWorld().getObject() == null) {
            return;
        }
        if (this.target != null && Minecraft.currentScreen().getObject() == null && ClientSettings.fW.P) {
            this.swapVelocityBuffers(this.farFromTarget);
            this.applyRotation();
        } else {
            this.V$src$V$1u3xaau();
        }
    }

    @Override
    public void onDisable() {
        this.target = null;
        this.V$src$V$1u3xaau();
    }

    private void applyRotation() {
        float f;
        AimAssist aimAssist = (AimAssist)this.getParent();
        this.updateDrift();
        this.targetX = this.target.c();
        this.targetY = this.target.A();
        this.targetZ = this.target.Z();
        if (ForgeVersion.MC_1_7_10.L()) {
            this.targetY += (double)this.target.X();
        }
        if (aimAssist.F.K() == aimAssist.r) {
            this.computeTargetOffset();
        }
        double d = this.targetX - this.prevTargetX;
        double d2 = this.targetZ - this.prevTargetZ;
        this.prevTargetX = this.targetX;
        this.prevTargetZ = this.targetZ;
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        float f2 = RotationManager.s(entityPlayerSP);
        double d3 = 1.7;
        double d4 = RotationUtil.C(entityPlayerSP.c(), entityPlayerSP.Z(), f2, this.targetX + d * d3, this.targetZ + d2 * d3);
        boolean bl = RotationUtil.p(entityPlayerSP.c(), entityPlayerSP.Z(), f2, this.targetX + d * d3, this.targetZ + d2 * d3);
        if (bl) {
            float f3;
            int n = RotationUtil.H(entityPlayerSP, this.targetX, this.targetY, this.targetZ);
            boolean bl2 = n < 0;
            int n2 = Math.abs(n) - 10;
            float f4 = 1.0f;
            float f5 = 1.0f;
            f4 = (float)((double)f4 + MathUtil.randomRange(this.sharedRandom, 0.0, 2.0));
            f4 = (float)((double)f4 + d4 / 50.0);
            f5 = (float)((double)f5 + MathUtil.randomRange(this.sharedRandom, 0.0, 2.0));
            f5 += (float)Math.abs(n2) / 50.0f;
            if (Math.abs(d4 - this.lastAngleDiff) > 6.0) {
                f4 = (float)((double)f4 + d4 / 35.0);
            }
            double d5 = (9.0f - entityPlayerSP.getDistanceToEntity(this.target)) / 2.5f - 2.0f;
            d5 = Math.max(0.0, d5);
            f4 = (float)((double)f4 + d5);
            if (aimAssist.R().L().booleanValue() && entityPlayerSP.movementInput().T() < 0.0f) {
                f4 = (float)((double)f4 * 1.6);
            }
            if (entityPlayerSP.getDistanceToEntity(this.target) < 0.5f) {
                f4 /= 5.0f;
            }
            float f6 = -(f4 /= 90.0f);
            float f7 = f3 = bl2 ? f5 : -(f5 /= 90.0f);
            if (d4 < 5.0) {
                f6 = 0.0f;
                this.pitchVelocity *= 0.7f;
                if (entityPlayerSP.movementInput().T() > 0.0f) {
                    this.pitchVelocity *= 0.5f;
                }
            }
            if (bl != this.prevOnLeft) {
                this.pitchVelocity = -this.pitchVelocity;
                this.pitchVelocityBuffer = -this.pitchVelocityBuffer;
                this.pitchAccumulator = 0.0f;
            }
            if (bl2 != this.prevAbove) {
                this.yawVelocityBuffer = -this.yawVelocityBuffer;
                this.yawVelocity = -this.yawVelocity;
                this.yawAccumulator = 0.0f;
            }
            if (n2 < 5) {
                f3 = 0.0f;
                this.yawVelocityBuffer *= 0.7f;
            }
            this.pitchVelocityBuffer += f6;
            this.yawVelocity += f3;
            f6 = this.pitchVelocity;
            f3 = this.yawVelocityBuffer;
            if (Math.abs(f6) > 10.0f) {
                this.pitchVelocityBuffer = 0.0f;
                this.pitchVelocity = 0.0f;
                return;
            }
            float f8 = f6 * 0.15f;
            if (d4 <= 9.0) {
                f8 = (float)((double)f8 / (10.0 - d4));
            }
            boolean bl3 = this.farFromTarget = d4 > 5.0;
            if (Float.isNaN(f8)) {
                this.pitchVelocityBuffer = 0.0f;
                this.pitchVelocity = 0.0f;
                return;
            }
            this.applyPitch(f8);
            if (aimAssist.U().L().booleanValue()) {
                float f9 = (float)((double)f3 * 0.15);
                if (Float.isNaN(f9)) {
                    this.yawVelocity = 0.0f;
                    this.yawVelocityBuffer = 0.0f;
                    return;
                }
                this.applyYaw(f9, n);
            }
            this.prevAbove = bl2;
            this.prevOnLeft = bl;
            ++this.sampleCounter;
            if (this.sampleCounter > 10) {
                this.lastAngleDiff = d4;
                this.sampleCounter = 0;
            }
            return;
        }
        int n = RotationUtil.H(entityPlayerSP, this.targetX, this.targetY, this.targetZ);
        boolean bl4 = n < 0;
        int n3 = Math.abs(n) - 10;
        float f10 = 1.0f;
        float f11 = 1.0f;
        f10 = (float)((double)f10 + MathUtil.randomRange(this.sharedRandom, 0.0, 2.0));
        f10 = (float)((double)f10 + d4 / 50.0);
        f11 = (float)((double)f11 + MathUtil.randomRange(this.sharedRandom, 0.0, 2.0));
        f11 += (float)Math.abs(n3) / 50.0f;
        if (Math.abs(d4 - this.lastAngleDiff) > 6.0) {
            f10 = (float)((double)f10 + d4 / 35.0);
        }
        double d6 = (9.0f - entityPlayerSP.getDistanceToEntity(this.target)) / 2.5f - 2.0f;
        d6 = Math.max(0.0, d6);
        f10 = (float)((double)f10 + d6);
        if (aimAssist.R().L().booleanValue() && entityPlayerSP.movementInput().T() > 0.0f) {
            f10 = (float)((double)f10 * 1.6);
        }
        if (entityPlayerSP.getDistanceToEntity(this.target) < 0.5f) {
            f10 /= 5.0f;
        }
        float f12 = f10 /= 90.0f;
        float f13 = f = bl4 ? f11 : -(f11 /= 90.0f);
        if (d4 < 5.0) {
            f12 = 0.0f;
            this.pitchVelocity *= 0.7f;
            if (entityPlayerSP.movementInput().T() < 0.0f) {
                this.pitchVelocity *= 0.5f;
            }
        }
        if (bl != this.prevOnLeft) {
            this.pitchVelocity = -this.pitchVelocity;
            this.pitchVelocityBuffer = -this.pitchVelocityBuffer;
            this.pitchAccumulator = 0.0f;
        }
        if (bl4 != this.prevAbove) {
            this.yawVelocityBuffer = -this.yawVelocityBuffer;
            this.yawVelocity = -this.yawVelocity;
            this.yawAccumulator = 0.0f;
        }
        if (n3 < 5) {
            f = 0.0f;
            this.yawVelocityBuffer *= 0.7f;
        }
        this.pitchVelocityBuffer += f12;
        this.yawVelocity += f;
        f12 = this.pitchVelocity;
        f = this.yawVelocityBuffer;
        if (Math.abs(f12) > 10.0f) {
            this.pitchVelocityBuffer = 0.0f;
            this.pitchVelocity = 0.0f;
            return;
        }
        float f14 = f12 * 0.15f;
        if (d4 <= 9.0) {
            f14 = (float)((double)f14 / (10.0 - d4));
        }
        boolean bl5 = this.farFromTarget = d4 > 5.0;
        if (Float.isNaN(f14)) {
            this.pitchVelocityBuffer = 0.0f;
            this.pitchVelocity = 0.0f;
            return;
        }
        this.applyPitch(f14);
        if (aimAssist.U().L().booleanValue()) {
            float f15 = (float)((double)f * 0.15);
            if (Float.isNaN(f15)) {
                this.yawVelocity = 0.0f;
                this.yawVelocityBuffer = 0.0f;
                return;
            }
            this.applyYaw(f15, n);
        }
        this.prevAbove = bl4;
        this.prevOnLeft = bl;
        ++this.sampleCounter;
        if (this.sampleCounter > 10) {
            this.lastAngleDiff = d4;
            this.sampleCounter = 0;
        }
    }

    @Nullable
    public EntityLivingBase v() {
        return this.target;
    }

    void swapVelocityBuffers(boolean bl) {
        ++this.swapTickCounter;
        if (this.swapTickCounter > 10) {
            this.yawVelocityBuffer = this.yawVelocity;
            this.pitchVelocity = this.pitchVelocityBuffer;
            this.pitchVelocityBuffer = 0.0f;
            this.yawVelocity = 0.0f;
            this.swapTickCounter = 0;
        }
    }

    @Override
    public void onEnable() {
        if (!this.threadStarted) {
            this.threadStarted = true;
            new AimAssistRotationWorkerThread(this).start();
        }
    }

}

