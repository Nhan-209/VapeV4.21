package gg.vape.module.other;

import gg.vape.event.EventHandler;
import gg.vape.event.EventPriority;
import gg.vape.event.impl.EventPreTick;
import gg.vape.input.InputEventDispatcher;
import gg.vape.input.MovementInputLock;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.movement.MovementInputHelper;
import gg.vape.movement.PlayerMovementTaskManager;
import gg.vape.movement.TargetPositionMovementTask;
import gg.vape.rotation.AdaptiveRotationController;
import gg.vape.rotation.FixedRotationController;
import gg.vape.rotation.RotationAngles;
import gg.vape.rotation.RotationControlClaim;
import gg.vape.rotation.RotationManager;
import gg.vape.utils.MathUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.NumberValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Vec3;
import java.util.Random;

public class AntiAFK
extends Mod {
    private FixedRotationController t;
    private final TimerUtil S;
    private final NumberValue p;
    private RotationAngles D;
    private static final long s = -1750558580268514572L;
    private final NumberValue J;
    private final Random O;
    private long v = -1L;
    private final RotationControlClaim F;
    private Vec3 P;
    private boolean k;
    private final RandomValue H;
    private long U = -1L;
    private final BooleanValue V;
    private final BooleanValue Z;
    private final MovementInputLock A;
    private final TimerUtil I;
    private final BooleanValue K;
    private final NumberValue c;
    private TargetPositionMovementTask o;
    private final TimerUtil C;
    private final TimerUtil Y = new TimerUtil();

    private void captureReferencePose() {
        if (this.P == null) {
            this.P = Vec3.create(Minecraft.thePlayer().z(), Minecraft.thePlayer().N(), Minecraft.thePlayer().h());
        }
        if (this.D == null) {
            this.D = new RotationAngles(RotationManager.b.V(), RotationManager.b.x());
        }
    }

    private void resetActionState() {
        this.Y.reset();
        this.U = (long)this.H.B() * 1000L;
        this.v = (long)this.H.B() * 1000L;
        this.P = null;
        this.D = null;
        this.C.reset();
        this.S.reset();
        this.I.reset();
    }

    @EventHandler(A=EventPriority.LOW)
    public void onTick(EventPreTick eventPreTick) {
        boolean bl;
        EntityPlayerSP entityPlayerSP = eventPreTick.getThePlayer();
        if (Minecraft.thePlayer().isNull() || Minecraft.currentScreen().isInstance(MappedClasses.YS)) {
            return;
        }
        if (this.k || this.shouldPauseForInput() || this.F.e(this)) {
            this.resetActionState();
            this.clearActionState();
            this.removeMovementTarget();
            return;
        }
        if (!this.Y.hasTimeElapsed((long)this.H.B() * 1000L)) {
            return;
        }
        int n = (int)(1000.0f / ((Double)this.p.K()).floatValue());
        if (this.t != null && !this.t.v() && this.t.V$src$Z$lb4tvc()) {
            this.clearActionState();
        }
        boolean bl2 = this.F.U(this) || this.F.h(this, this.Z.L());
        boolean bl3 = bl = this.K.L() != false && bl2 && Minecraft.currentScreen().isNull();
        if (bl && this.S.hasTimeElapsed(this.v)) {
            this.captureReferencePose();
            this.v = this.O.nextInt(1000 + n);
            this.updateRandomRotation();
            this.S.reset();
        }
        if (this.o == null) {
            if (!this.A.s() && this.C.hasTimeElapsed(this.U)) {
                this.captureReferencePose();
                this.U = this.O.nextInt(1000 + n);
                this.A.K(this);
                this.queueMovementTarget(entityPlayerSP);
                this.C.reset();
                this.I.reset();
            }
        } else if (this.I.hasTimeElapsed(100 + this.O.nextInt(100))) {
            this.removeMovementTarget();
        }
    }

    @Override
    public void onEnable() {
        this.clearActionState();
        this.resetActionState();
    }

    private void removeMovementTarget() {
        if (this.o != null) {
            PlayerMovementTaskManager.G.Q(this.o);
            this.A.T(this);
            this.o = null;
        }
    }

    private void queueMovementTarget(EntityPlayerSP entityPlayerSP) {
        double d;
        double d2;
        double d3;
        double d4 = entityPlayerSP.z() + this.O.nextDouble() * 2.0 - 1.0;
        double d5 = entityPlayerSP.h() + this.O.nextDouble() * 2.0 - 1.0;
        if (this.V.L().booleanValue() && this.P != null && (d3 = Math.sqrt((d2 = this.P.getX() - entityPlayerSP.z()) * d2 + (d = this.P.getZ() - entityPlayerSP.h()) * d)) >= 0.75) {
            d4 = this.P.getX();
            d5 = this.P.getZ();
        }
        if (this.o == null) {
            this.o = new TargetPositionMovementTask(d4, d5);
        }
        this.o.g(true);
        PlayerMovementTaskManager.G.i(this.o);
    }

    public AntiAFK() {
        super("Anti-AFK", (int)s, Category.m);
        this.C = new TimerUtil();
        this.S = new TimerUtil();
        this.I = new TimerUtil();
        this.O = new Random();
        this.H = RandomValue.G(this, "Start delay", "##", "sec", 10.0, 30.0, 40.0, 200.0, 1.0, "How long to wait after moving to start");
        this.p = NumberValue.E(this, "Frequency", "#.#", "", 0.1, 0.2, 20.0, "How often you should move");
        this.K = BooleanValue.create(this, "Rotation", true, "Moves your camera around");
        this.Z = BooleanValue.create(this, "Silent aim", false, "Uses Silent Aim system");
        this.V = BooleanValue.create(this, "Keep close", false, "Keeps your position and rotation close to the original");
        this.c = NumberValue.create(this, "Max yaw change", "#", "\u00b0", 1.0, 10.0, 180.0, 1.0, "Max you will turn left and right");
        this.J = NumberValue.create(this, "Max pitch change", "#", "\u00b0", 1.0, 5.0, 90.0, 1.0, "Max you will tilt up and down");
        this.F = SharedModuleControlClaims.I;
        this.A = SharedModuleControlClaims.l;
        this.K.K(this.Z, this.c, this.J);
        this.addValue(this.H, this.p, this.V, this.K, this.Z, this.c, this.J);
    }

    private boolean shouldPauseForInput() {
        boolean bl = InputEventDispatcher.getInstance().getFocusState().isFocused();
        return bl && MovementInputHelper.k();
    }


    @Override
    public void onDisable() {
        this.clearActionState();
        this.removeMovementTarget();
        this.A.T(this);
        this.F.X(this);
    }

    @Override
    public void s(boolean bl, boolean bl2) {
        if (!bl && this.t instanceof AdaptiveRotationController) {
            this.k = !this.k;
        } else {
            this.k = false;
            super.s(bl, bl2);
        }
    }

    public void clearActionState() {
        if (this.t != null) {
            RotationManager.b.v(this.t);
            this.t.U(true);
            this.t.D(false);
            this.t.z(false);
            this.t.A(true);
            this.t.Y(6.0f);
        }
        if (RotationManager.b.w() == null || RotationManager.b.w() != this.t || this.t != null && !this.t.v() && this.t.V$src$Z$lb4tvc()) {
            this.t = null;
            this.F.X(this);
            if (this.k) {
                this.k = false;
                super.Y(false);
            }
        }
    }

    private void updateRandomRotation() {
        float f;
        float f2;
        float f3 = 0.0f;
        if (this.V.L().booleanValue() && this.D != null) {
            float f4 = MathUtil.wrapAngleTo180(RotationManager.b.V() - this.D.z());
            float f5 = MathUtil.wrapAngleTo180(RotationManager.b.x() - this.D.N());
            f3 = MathUtil.sqrt(f4 * f4 + f5 * f5);
        }
        double d = Math.sqrt((Double)this.c.K() * (Double)this.c.K() + (Double)this.J.K() * (Double)this.J.K());
        double d2 = this.O.nextDouble() * (Double)this.c.K();
        double d3 = this.O.nextDouble() * (Double)this.J.K();
        if (this.O.nextDouble() > 0.6) {
            d2 = -d2;
        }
        if (this.O.nextDouble() > 0.5) {
            d3 = -d3;
        }
        if ((double)f3 >= d) {
            f2 = this.D.z();
            f = this.D.N();
        } else {
            f2 = (float)((double)RotationManager.b.V() + d2) % 360.0f;
            f = (float)((double)RotationManager.b.x() + d3) % 90.0f;
        }
        if (this.t == null) {
            this.t = this.Z.L() != false ? new AdaptiveRotationController(f2, f) : new FixedRotationController(f2, f);
        }
        this.t.g(f2, f);
        this.t.Y((int)(this.O.nextDouble() * 10.0));
        RotationManager.b.S(this.t);
    }
}

