package gg.vape.module.blatant.scaffold;

import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPostTick;
import gg.vape.event.impl.EventPreEntityUpdate;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventRender2D;
import gg.vape.input.KeyboardCodeUtil;
import gg.vape.module.Mod;
import gg.vape.module.SubModule;
import gg.vape.module.blatant.Scaffold;
import gg.vape.module.blatant.scaffold.ScaffoldEdgeSneakHelper;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.module.render.hud.FreeLookHudModule;
import gg.vape.movement.MovementInputHelper;
import gg.vape.movement.PlayerMovementTaskManager;
import gg.vape.movement.TargetPositionMovementTask;
import gg.vape.rotation.FixedRotationController;
import gg.vape.rotation.RotationManager;
import gg.vape.utils.MathUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.RayTraceResult_type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class BlatantScaffoldMode
extends SubModule<Scaffold> {
    private final KeyBinding leftKey;
    private TimerUtil moveTimer;
    private double[] targetPos;
    private static final long DEFAULT_TICK_BUDGET = -7821191894771171308L;
    private ArrayList<Integer> slotHistory;
    private boolean rotationPending = false;
    private boolean atEdge = false;
    private boolean taskSneak = false;
    private int taskTickLimit;
    private final KeyBinding rightKey;
    private boolean prevLeftDown = false;
    private final KeyBinding sneakKey;
    private int blocksPlaced;
    private int pendingDirection;
    private int direction = 0;
    private final NumberValue activationBlocks = NumberValue.E(this, "Activation blocks", "#", "", 1.0, 2.0, 4.0, "Manual blocks placed before bridging");
    private boolean jumpDown = false;
    boolean S;
    private int taskTicks;
    private boolean reversed;
    TargetPositionMovementTask Gz = null;
    private boolean keyIdle = true;
    ArrayList<Integer> Gn;
    private ItemStack heldBlock;
    private TimerUtil switchTimer;
    private boolean pendingReversed;
    private double[] pendingPos;
    private boolean switching = false;
    float[] GF;
    private boolean prevRightDown = false;
    ScaffoldEdgeSneakHelper O;
    Scaffold b = (Scaffold)this.getParent();
    private boolean pendingReset = false;
    private double[] placePos;

    private boolean isNextBlockEmpty(EntityPlayerSP entityPlayerSP) {
        double d = entityPlayerSP.z();
        double d2 = entityPlayerSP.h();
        if (this.direction == 1) {
            d += 0.2;
            d2 += 0.2;
        } else if (this.direction == 2) {
            d -= 0.2;
            d2 += 0.2;
        } else if (this.direction == 3) {
            d -= 0.2;
            d2 -= 0.2;
        } else if (this.direction == 4) {
            d += 0.2;
            d2 -= 0.2;
        } else if (this.direction == 6) {
            d += 0.25;
            d2 = this.targetPos[1];
        } else if (this.direction == 8) {
            d -= 0.25;
            d2 = this.targetPos[1];
        } else if (this.direction == 7) {
            d = this.targetPos[0];
            d2 += 0.25;
        } else if (this.direction == 5) {
            d = this.targetPos[0];
            d2 -= 0.25;
        } else {
            return true;
        }
        d = MathUtil.floor(d);
        double d3 = Scaffold.Access.J(this.b, entityPlayerSP);
        d2 = MathUtil.floor(d2);
        return !Scaffold.Access.i(this.b, d, d3, d2);
    }

    @EventHandler
    public void M(EventPostTick eventPostTick) {
        if (Minecraft.thePlayer().isNull()) {
            return;
        }
        if (this.S) {
            this.O.g(eventPostTick);
        }
    }

    private boolean advanceTask() {
        if (this.Gz != null) {
            if (!this.Gz.q$src$Z$naak2i() && this.taskTicks < this.taskTickLimit) {
                ++this.taskTicks;
                if (this.taskSneak) {
                    return true;
                }
            } else if (this.Gz.q$src$Z$naak2i()) {
                this.taskTicks = 0;
                this.taskSneak = false;
            } else {
                this.Gz.s(true);
                Scaffold.Access.A(this.b);
                this.Gz = null;
                this.taskTicks = 0;
                this.taskSneak = false;
                return true;
            }
        }
        return false;
    }

    public boolean Z(EntityPlayerSP entityPlayerSP, int n) {
        if (n > 4) {
            double[] dArray;
            double d = RotationUtil.c();
            return Scaffold.Access.P(this.b, d, (dArray = this.T(n))[0]) <= Scaffold.Access.P(this.b, d, dArray[1]);
        }
        return !this.reversed;
    }

    @Override
    public void onEnable() {
        this.S = true;
    }

    private void applyRotation(float[] fArray, float f) {
        if (this.b.A == null) {
            this.b.A = new FixedRotationController(fArray[0], fArray[1]);
            this.b.A.Y(Math.min(Math.max(2.0f, f), 12.0f));
            this.b.A.k(true);
            this.b.A.t(0.0f);
            this.b.A.s(true);
            this.b.A.U(true);
            this.b.A.w(true);
            RotationManager.b.S(this.b.A);
        } else {
            this.b.A.Y(Math.min(Math.max(2.0f, f), 12.0f));
            this.b.A.g(fArray[0], fArray[1]);
        }
    }

    private boolean isPastCorner(EntityPlayerSP entityPlayerSP, int n) {
        double d = entityPlayerSP.z();
        double d2 = entityPlayerSP.h();
        double d3 = MathUtil.floor(d);
        double d4 = MathUtil.floor(d2);
        if (n == 1) {
            return d - d3 + (d2 - d4) > 1.0;
        }
        if (n == 2) {
            return d3 - d + (d2 - d4) > 1.0;
        }
        if (n == 3) {
            return d3 - d + (d4 - d2) > 1.0;
        }
        if (n == 4) {
            return d - d3 + (d4 - d2) > 1.0;
        }
        if (n == 6) {
            return d - d3 > 0.5;
        }
        if (n == 8) {
            return d3 - d > 0.5;
        }
        if (n == 7) {
            return d2 - d4 > 0.5;
        }
        if (n == 5) {
            return d4 - d2 > 0.5;
        }
        return false;
    }

    private boolean ensureBlockSelected(EntityPlayerSP entityPlayerSP) {
        int n = Scaffold.Access.l(this.b);
        if (n == -1) {
            return false;
        }
        if (this.heldBlock != null && !entityPlayerSP.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt().equals(this.heldBlock)) {
            int n2 = Scaffold.Access.s(this.b, entityPlayerSP, this.heldBlock);
            if (n2 != -1) {
                Scaffold.Access.s(this.b, n2);
            } else if (entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v() != n) {
                Scaffold.Access.s(this.b, n);
            }
        }
        return true;
    }

    private boolean isFrontClear(EntityPlayerSP entityPlayerSP) {
        AxisAlignedBB axisAlignedBB;
        double d = -0.2;
        if (ForgeVersion.MC_1_8_9.d()) {
            axisAlignedBB = entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl();
        } else {
            AxisAlignedBB axisAlignedBB2 = entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl();
            axisAlignedBB = axisAlignedBB2.copy();
        }
        double d2 = entityPlayerSP.t();
        double d3 = ForgeVersion.MC_1_20_6.d() ? 1.0 : -1.0;
        double d4 = entityPlayerSP.T();
        AxisAlignedBB axisAlignedBB3 = axisAlignedBB.expand(d, 0.0, d).k(d2, d3, d4);
        int n = Minecraft.theWorld().i(entityPlayerSP, axisAlignedBB3).size();
        return n == 0;
    }

    private void updateSneakInput(EntityPlayerSP entityPlayerSP) {
        boolean bl;
        if (Minecraft.currentScreen().getObject() == null) {
            KeyboardCodeUtil.v();
        }
        boolean bl2 = this.isAtEdge(entityPlayerSP, this.direction);
        boolean bl3 = bl = this.direction < 5;
        if (!bl2) {
            this.moveTimer.reset();
        }
        this.atEdge = bl2;
        if (bl2) {
            MovementInputHelper.w(this.sneakKey, false);
            MovementInputHelper.w(this.leftKey, false);
            MovementInputHelper.w(this.rightKey, false);
            if (!bl) {
                if (this.reversed) {
                    MovementInputHelper.w(this.rightKey, false);
                } else {
                    MovementInputHelper.w(this.leftKey, false);
                }
            }
        } else {
            MovementInputHelper.w(this.sneakKey, true);
            if (!bl) {
                if (this.reversed) {
                    MovementInputHelper.w(this.rightKey, true);
                } else {
                    MovementInputHelper.w(this.leftKey, true);
                }
            } else {
                MovementInputHelper.w(this.leftKey, false);
                MovementInputHelper.w(this.rightKey, false);
            }
        }
    }

    public double[] T(int n) {
        if (n == 6) {
            return new double[]{135.0, 45.0};
        }
        if (n == 8) {
            return new double[]{315.0, 225.0};
        }
        if (n == 7) {
            return new double[]{225.0, 135.0};
        }
        if (n == 5) {
            return new double[]{45.0, 315.0};
        }
        return new double[0];
    }

    private double[] computeAnchorTarget(EntityPlayerSP entityPlayerSP, int n) {
        double[] dArray = new double[]{MathUtil.floor(entityPlayerSP.z()), entityPlayerSP.q() > 0.0 ? Scaffold.Access.J(this.b, entityPlayerSP) + 1.0 : Scaffold.Access.J(this.b, entityPlayerSP), MathUtil.floor(entityPlayerSP.h())};
        if (Scaffold.Access.U(this.b, dArray) && Scaffold.Access.U(this.b, dArray = Scaffold.Access.X(this.b, dArray, -1, n)) && Scaffold.Access.U(this.b, dArray = Scaffold.Access.X(this.b, dArray, 1, this.rotateDirection(n, 2)))) {
            dArray = Scaffold.Access.X(this.b, dArray, -2, this.rotateDirection(n, 2));
        }
        return dArray;
    }

    private boolean shouldReleaseControl(EntityPlayerSP entityPlayerSP) {
        if (!SharedModuleControlClaims.I.U(this.b)) {
            this.S = true;
            MovementInputHelper.q();
            return true;
        }
        if (!this.ensureBlockSelected(entityPlayerSP)) {
            this.S = true;
            MovementInputHelper.i();
            return true;
        }
        if (!ClientSettings.B(this.sneakKey)) {
            if (!entityPlayerSP.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().isFlying() && !this.isNextBlockEmpty(entityPlayerSP)) {
                return false;
            }
            MovementInputHelper.i();
            this.S = true;
            return true;
        }
        if (!FreeLookHudModule.z()) {
            this.slotHistory = Scaffold.Access.J(this.b, this.slotHistory);
            if (Scaffold.Access.M(this.b, this.slotHistory) >= 10) {
                MovementInputHelper.i();
                this.S = true;
                return true;
            }
        }
        return false;
    }

    @EventHandler
    public void Y(EventPreEntityUpdate eventPreEntityUpdate) {
        if (this.S) {
            this.O.X(eventPreEntityUpdate);
        }
    }

    private boolean isRotationOffTarget() {
        if (this.b.A != null) {
            if (Scaffold.Access.P(this.b, RotationUtil.c(), this.b.A.L) > 4.0) {
                if (this.rotationPending) {
                    return true;
                }
            } else if (this.rotationPending) {
                Scaffold.Access.W(this.b);
                this.rotationPending = false;
            }
        }
        return false;
    }

    @EventHandler
    public void onRender2D(EventRender2D eventRender2D) {
        Scaffold.Access.V$src$V$dhg0vg(this.b);
    }

    private boolean handleRotationAndTask() {
        boolean bl = this.isRotationOffTarget();
        boolean bl2 = this.advanceTask();
        return bl || bl2;
    }

    private boolean isAtEdge(EntityPlayerSP entityPlayerSP, int n) {
        AxisAlignedBB axisAlignedBB;
        boolean bl = ClientSettings.B(Minecraft.gameSettings().O());
        if (n > 4) {
            double d = entityPlayerSP.z();
            double d2 = entityPlayerSP.h();
            this.jumpDown = bl;
            if (n == 6) {
                d += -0.15;
                d2 = this.targetPos[1];
            } else if (n == 8) {
                d -= -0.15;
                d2 = this.targetPos[1];
            } else if (n == 7) {
                d = this.targetPos[0];
                d2 += -0.15;
            } else if (n == 5) {
                d = this.targetPos[0];
                d2 -= -0.15;
            }
            d = MathUtil.floor(d);
            double d3 = Scaffold.Access.J(this.b, entityPlayerSP);
            d2 = MathUtil.floor(d2);
            return Scaffold.Access.i(this.b, d, d3, d2);
        }
        double d = -0.16;
        this.jumpDown = bl;
        if (ForgeVersion.MC_1_8_9.d()) {
            axisAlignedBB = entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl();
        } else {
            AxisAlignedBB axisAlignedBB2 = entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl();
            axisAlignedBB = axisAlignedBB2.copy();
        }
        double d4 = entityPlayerSP.t();
        double d5 = ForgeVersion.MC_1_20_6.d() ? 1.0 : -1.0;
        double d6 = entityPlayerSP.T();
        AxisAlignedBB axisAlignedBB3 = axisAlignedBB.expand(d, 0.0, d).k(d4, d5, d6);
        int n2 = Minecraft.theWorld().i(entityPlayerSP, axisAlignedBB3).size();
        return n2 == 0;
    }

    private float computeRotationSpeed(float[] fArray, int n) {
        return (float)Math.min(2.0 + Scaffold.Access.P(this.b, RotationUtil.c(), fArray[0]) / (double)n, 12.0);
    }


    private boolean updatePlacement(EntityPlayerSP entityPlayerSP) {
        double d = MathUtil.floor(entityPlayerSP.z());
        double d2 = MathUtil.floor(entityPlayerSP.h());
        double d3 = Scaffold.Access.J(this.b, entityPlayerSP);
        if (!Scaffold.Access.a(this.b, entityPlayerSP.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt())) {
            this.placePos = null;
            this.blocksPlaced = 0;
            return true;
        }
        int n = Scaffold.Access.u$src$I$dyhmyg(this.b);
        if (this.direction != 0 && n != this.direction) {
            this.placePos = null;
            this.blocksPlaced = 0;
        }
        this.direction = n;
        double[] dArray = new double[]{d, d3, d2};
        double[] dArray2 = Scaffold.Access.X(this.b, dArray, 1, this.direction);
        double[] dArray3 = Scaffold.Access.X(this.b, dArray, 2, this.direction);
        if (this.placePos == null && entityPlayerSP.b$src$Z$fqlxe4()) {
            if (Scaffold.Access.U(this.b, dArray)) {
                this.placePos = dArray;
            } else if (Scaffold.Access.U(this.b, dArray2)) {
                this.placePos = dArray2;
            } else if (Scaffold.Access.U(this.b, dArray3)) {
                this.placePos = dArray3;
            }
        } else if (this.placePos != null) {
            if ((double)this.blocksPlaced >= (Double)this.activationBlocks.K()) {
                this.reversed = this.Z(entityPlayerSP, this.direction);
                this.targetPos = this.computePlacementPoint(new double[]{this.placePos[0], this.placePos[2]}, this.direction, this.reversed);
                this.heldBlock = entityPlayerSP.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt();
                if (!ClientSettings.B(Minecraft.gameSettings().O()) && !entityPlayerSP.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().isFlying()) {
                    this.startMoveTaskNoTurn(this.targetPos, true, false, 40);
                    this.GF = this.computeDiagonalRotation(entityPlayerSP, this.reversed);
                    Scaffold.Access.S(this.b, this.GF, this.computeRotationSpeed(this.GF, 15));
                }
                this.blocksPlaced = 0;
                this.placePos = null;
                this.moveTimer.reset();
                return false;
            }
            if (!Scaffold.Access.U(this.b, this.placePos)) {
                ++this.blocksPlaced;
                double[] dArray4 = Scaffold.Access.X(this.b, this.placePos, 1, this.direction);
                boolean bl = Scaffold.Access.U(this.b, dArray4);
                if (bl && (double)this.blocksPlaced < (Double)this.activationBlocks.K()) {
                    this.placePos = dArray4;
                } else if (!bl) {
                    this.placePos = null;
                    this.blocksPlaced = 0;
                }
            } else if (Scaffold.Access.X(this.b, this.placePos, dArray, this.direction, (Double)this.activationBlocks.K(), this.blocksPlaced)) {
                this.placePos = null;
                this.blocksPlaced = 0;
            }
        }
        return true;
    }

    private boolean handleDirectionSwitch(EntityPlayerSP entityPlayerSP) {
        if (Minecraft.currentScreen().getObject() == null) {
            KeyboardCodeUtil.v();
        }
        if (this.Gz != null && this.Gz.q$src$Z$naak2i() && this.switching) {
            this.atEdge = true;
            this.Gz = null;
            this.targetPos = this.pendingPos;
            this.direction = this.pendingDirection;
            this.reversed = this.pendingReversed;
            this.switching = false;
            this.pendingReset = false;
            MovementInputHelper.i();
            this.moveTimer.reset();
            this.switchTimer.reset();
            if (this.direction < 5) {
                float[] fArray = this.p(entityPlayerSP, this.computePlacementPoint(this.targetPos, this.direction, this.reversed), this.direction);
                this.applyRotation(fArray, this.computeRotationSpeed(fArray, 15));
            } else {
                float[] fArray = this.computeDiagonalRotation(entityPlayerSP, this.reversed);
                this.applyRotation(fArray, this.computeRotationSpeed(fArray, 12));
            }
            this.rotationPending = true;
            return true;
        }

        boolean bl = ClientSettings.B(Minecraft.gameSettings().x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg());
        boolean bl7 = ClientSettings.B(Minecraft.gameSettings().g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3());
        if (!this.switching) {
            boolean bl4;
            boolean bl3;
            if (this.keyIdle) {
                bl4 = bl && !this.prevLeftDown;
                bl3 = bl7 && !this.prevRightDown;
            } else {
                bl4 = !bl && this.prevLeftDown;
                bl3 = !bl7 && this.prevRightDown;
            }
            this.prevLeftDown = bl;
            this.prevRightDown = bl7;

            if (this.keyIdle && this.switchTimer.hasTimeElapsed(0L)) {
                this.keyIdle = !bl4 && !bl3;
                if (bl4) {
                    this.pendingDirection = this.rotateDirection(this.direction, 1);
                } else if (bl3) {
                    this.pendingDirection = this.rotateDirection(this.direction, -1);
                }
            } else if (!this.keyIdle) {
                this.keyIdle = bl4 || bl3;
                if (bl4) {
                    this.pendingDirection = this.rotateDirection(this.direction, -1);
                } else if (bl3) {
                    this.pendingDirection = this.rotateDirection(this.direction, 1);
                }
            } else {
                if (bl) {
                    MovementInputHelper.w(this.leftKey, false);
                } else if (bl7) {
                    MovementInputHelper.w(this.rightKey, false);
                }
                return false;
            }
            this.switching = bl4 || bl3;
        }

        if (this.switching) {
            this.Gz = null;
            double[] dArray = new double[]{MathUtil.floor(entityPlayerSP.z()), Scaffold.Access.J(this.b, entityPlayerSP), MathUtil.floor(entityPlayerSP.h())};
            if (!(Scaffold.Access.U(this.b, dArray) || this.isFrontClear(entityPlayerSP) || this.isPastCorner(entityPlayerSP, this.direction))) {
                this.pendingReversed = this.Z(entityPlayerSP, this.pendingDirection);
                this.pendingPos = this.computePlacementPoint(new double[]{dArray[0], dArray[2]}, this.pendingDirection, this.pendingReversed);
                Scaffold.Access.J$src$V$dauhr4(this.b);
                if (this.direction > 4 && !this.reversed && this.pendingDirection == this.rotateDirection(this.direction, -1) || this.direction > 4 && this.reversed && this.pendingDirection == this.rotateDirection(this.direction, 1)) {
                    this.Gz = new TargetPositionMovementTask(0.0, 0.0);
                    this.Gz.s(true);
                } else if (Math.abs(entityPlayerSP.z() - this.pendingPos[0]) > 0.15 || Math.abs(entityPlayerSP.h() - this.pendingPos[1]) > 0.15) {
                    this.startMoveTaskNoTurn(this.pendingPos, true, false, 40);
                } else {
                    this.Gz = new TargetPositionMovementTask(0.0, 0.0);
                    this.Gz.s(true);
                }
            } else if (!this.reversed) {
                if (bl && this.isAtEdge(entityPlayerSP, this.direction)) {
                    MovementInputHelper.w(this.leftKey, false);
                } else if (bl7) {
                    MovementInputHelper.w(this.rightKey, false);
                }
            } else if (bl7 && this.isAtEdge(entityPlayerSP, this.direction)) {
                MovementInputHelper.w(this.rightKey, false);
            } else if (bl) {
                MovementInputHelper.w(this.leftKey, false);
            }
        }
        return false;
    }

    private void startMoveTask(double[] dArray, boolean bl, boolean bl2, boolean bl3, int n) {
        this.Gz = new TargetPositionMovementTask(dArray[0], dArray[1]);
        this.taskSneak = bl;
        this.Gz.g(bl2);
        this.Gz.l(bl3);
        PlayerMovementTaskManager.G.i(this.Gz);
        this.taskTicks = 0;
        this.taskTickLimit = n;
    }

    private boolean recoverIfStuck(EntityPlayerSP entityPlayerSP) {
        if (this.moveTimer.hasTimeElapsed(800L)) {
            double[] dArray = this.computeAnchorTarget(entityPlayerSP, this.direction);
            this.targetPos = this.computePlacementPoint(new double[]{dArray[0], dArray[2]}, this.direction, this.reversed);
            this.GF = this.computeDiagonalRotation(entityPlayerSP, this.reversed);
            Scaffold.Access.S(this.b, this.GF, this.computeRotationSpeed(this.GF, 15));
            this.startMoveTaskNoTurn(this.targetPos, true, false, 40);
            this.atEdge = true;
            this.moveTimer.reset();
            return true;
        }
        return false;
    }

    private float[] computeDiagonalRotation(EntityPlayerSP entityPlayerSP, boolean bl) {
        double d = entityPlayerSP.J();
        double d2 = entityPlayerSP.f();
        double d3 = entityPlayerSP.R();
        double d4 = d;
        double d5 = d;
        if (this.direction == 6) {
            d4 = 135.0 + 20.0 * (this.targetPos[1] - d3);
            d5 = 45.0 + 20.0 * (this.targetPos[1] - d3);
        } else if (this.direction == 8) {
            d4 = -45.0 - 20.0 * (this.targetPos[1] - d3);
            d5 = -135.0 - 20.0 * (this.targetPos[1] - d3);
        } else if (this.direction == 7) {
            d4 = -135.0 - 20.0 * (this.targetPos[0] - d2);
            d5 = 135.0 + 20.0 * (d2 - this.targetPos[0]);
        } else if (this.direction == 5) {
            d4 = 45.0 + 20.0 * (this.targetPos[0] - d2);
            d5 = -45.0 - 20.0 * (d2 - this.targetPos[0]);
        }
        d = bl ? d4 : d5;
        return new float[]{(float)d, this.moveTimer.hasTimeElapsed(300L) ? 80 : 78};
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        if (Minecraft.thePlayer().isNull() || Minecraft.theWorld().isNull()) {
            return;
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (!this.S && this.Y(entityPlayerSP)) {
            Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362().onTick(1);
        }
        if (!(this.S || this.rotationPending || this.taskSneak)) {
            this.updateSneakInput(entityPlayerSP);
        }
        if (this.S) {
            if (this.Gz != null || this.GF != null) {
                Scaffold.Access.J$src$V$dauhr4(this.b);
                this.O.onEnable();
            }
            this.resetState();
            this.S = this.updatePlacement(entityPlayerSP);
            SharedModuleControlClaims.l.T(this);
            SharedModuleControlClaims.x.Q();
            return;
        }
        SharedModuleControlClaims.l.K(this);
        SharedModuleControlClaims.x.i();
        if (this.shouldReleaseControl(entityPlayerSP)) {
            Scaffold.Access.W(this.b);
            this.O.onEnable();
            SharedModuleControlClaims.l.T(this);
            SharedModuleControlClaims.x.Q();
            return;
        }
        if (this.handleRotationAndTask()) {
            return;
        }
        if (this.handleDirectionSwitch(entityPlayerSP)) {
            return;
        }
        if (this.recoverIfStuck(entityPlayerSP)) {
            return;
        }
        this.updateRotation(entityPlayerSP);
        float f = Math.abs(entityPlayerSP.J());
        this.updateSneakInput(entityPlayerSP);
    }

    private int rotateDirection(int n, int n2) {
        int n3 = this.Gn.indexOf(n) + n2 < 0 ? (this.Gn.indexOf(n) + n2) % this.Gn.size() + this.Gn.size() : (this.Gn.indexOf(n) + n2) % this.Gn.size();
        return this.Gn.get(n3);
    }

    public BlatantScaffoldMode(Mod mod, String string) {
        super(mod, string);
        this.O = new ScaffoldEdgeSneakHelper((Mod)this.getParent(), "legit");
        this.moveTimer = new TimerUtil();
        this.switchTimer = new TimerUtil();
        this.Gn = new ArrayList<Integer>(Arrays.asList(5, 4, 6, 1, 7, 2, 8, 3));
        this.slotHistory = new ArrayList();
        this.sneakKey = Minecraft.gameSettings().s();
        this.rightKey = Minecraft.gameSettings().g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3();
        this.leftKey = Minecraft.gameSettings().x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg();
        this.addValue(this.activationBlocks);
    }

    private void resetState() {
        this.S = true;
        this.targetPos = null;
        this.Gz = null;
        this.taskTicks = 0;
        this.GF = null;
        this.prevLeftDown = false;
        this.prevRightDown = false;
        this.pendingReset = false;
        this.switching = false;
        this.atEdge = true;
        this.direction = 0;
        this.pendingDirection = 0;
        this.slotHistory = new ArrayList();
        this.taskTickLimit = (int)DEFAULT_TICK_BUDGET;
        this.heldBlock = null;
        this.pendingReversed = false;
        this.keyIdle = true;
        SharedModuleControlClaims.l.T(this);
        SharedModuleControlClaims.x.Q();
    }

    private void startMoveTaskNoTurn(double[] dArray, boolean bl, boolean bl2, int n) {
        this.Gz = new TargetPositionMovementTask(dArray[0], dArray[1]);
        this.taskSneak = bl;
        this.Gz.g(bl2);
        PlayerMovementTaskManager.G.i(this.Gz);
        this.taskTicks = 0;
        this.taskTickLimit = n;
    }

    private void updateRotation(EntityPlayerSP entityPlayerSP) {
        if (this.direction == 0) {
            this.GF = new float[]{entityPlayerSP.J(), 90.0f};
        } else if (this.direction < 5) {
            float[] fArray = this.GF;
            this.GF = this.p(entityPlayerSP, this.computePlacementPoint(this.targetPos, this.direction, this.reversed), this.direction);
            if (this.b.A == null || fArray == null || fArray[0] != this.GF[0] || fArray[1] != this.GF[1]) {
                Scaffold.Access.S(this.b, this.GF, this.computeRotationSpeed(this.GF, 15));
            }
        } else {
            float[] fArray = this.GF;
            this.GF = this.computeDiagonalRotation(entityPlayerSP, this.reversed);
            if (this.b.A == null || fArray == null || fArray[0] != this.GF[0] || fArray[1] != this.GF[1]) {
                Scaffold.Access.S(this.b, this.GF, this.computeRotationSpeed(this.GF, 15));
            }
        }
    }

    private double[] computePlacementPoint(double[] dArray, int n, boolean bl) {
        double d = dArray[0];
        double d2 = dArray[1];
        if (n == 1) {
            if (bl) {
                d = new BigDecimal(String.valueOf((double)MathUtil.floor(d) + 0.65)).doubleValue();
                d2 = new BigDecimal(String.valueOf((double)MathUtil.floor(d2) + 0.35)).doubleValue();
            } else {
                d = new BigDecimal(String.valueOf((double)MathUtil.floor(d) + 0.35)).doubleValue();
                d2 = new BigDecimal(String.valueOf((double)MathUtil.floor(d2) + 0.65)).doubleValue();
            }
        } else if (n == 2) {
            if (bl) {
                d = new BigDecimal(String.valueOf((double)MathUtil.floor(d) + 0.65)).doubleValue();
                d2 = new BigDecimal(String.valueOf((double)MathUtil.floor(d2) + 0.65)).doubleValue();
            } else {
                d = new BigDecimal(String.valueOf((double)MathUtil.floor(d) + 0.35)).doubleValue();
                d2 = new BigDecimal(String.valueOf((double)MathUtil.floor(d2) + 0.35)).doubleValue();
            }
        } else if (n == 3) {
            if (bl) {
                d = new BigDecimal(String.valueOf((double)MathUtil.floor(d) + 0.35)).doubleValue();
                d2 = new BigDecimal(String.valueOf((double)MathUtil.floor(d2) + 0.65)).doubleValue();
            } else {
                d = new BigDecimal(String.valueOf((double)MathUtil.floor(d) + 0.65)).doubleValue();
                d2 = new BigDecimal(String.valueOf((double)MathUtil.floor(d2) + 0.35)).doubleValue();
            }
        } else if (n == 4) {
            if (bl) {
                d = new BigDecimal(String.valueOf((double)MathUtil.floor(d) + 0.35)).doubleValue();
                d2 = new BigDecimal(String.valueOf((double)MathUtil.floor(d2) + 0.35)).doubleValue();
            } else {
                d = new BigDecimal(String.valueOf((double)MathUtil.floor(d) + 0.65)).doubleValue();
                d2 = new BigDecimal(String.valueOf((double)MathUtil.floor(d2) + 0.65)).doubleValue();
            }
        } else if (n == 6) {
            if (bl) {
                d = new BigDecimal(String.valueOf((double)MathUtil.floor(d) + 0.8)).doubleValue();
                d2 = new BigDecimal(String.valueOf((double)MathUtil.floor(d2) + 0.8)).doubleValue();
            } else {
                d = new BigDecimal(String.valueOf((double)MathUtil.floor(d) + 0.8)).doubleValue();
                d2 = new BigDecimal(String.valueOf((double)MathUtil.floor(d2) + 0.2)).doubleValue();
            }
        } else if (n == 8) {
            if (bl) {
                d = new BigDecimal(String.valueOf((double)MathUtil.floor(d) + 0.2)).doubleValue();
                d2 = new BigDecimal(String.valueOf((double)MathUtil.floor(d2) + 0.2)).doubleValue();
            } else {
                d = new BigDecimal(String.valueOf((double)MathUtil.floor(d) + 0.2)).doubleValue();
                d2 = new BigDecimal(String.valueOf((double)MathUtil.floor(d2) + 0.8)).doubleValue();
            }
        } else if (n == 7) {
            if (bl) {
                d = new BigDecimal(String.valueOf((double)MathUtil.floor(d) + 0.2)).doubleValue();
                d2 = new BigDecimal(String.valueOf((double)MathUtil.floor(d2) + 0.8)).doubleValue();
            } else {
                d = new BigDecimal(String.valueOf((double)MathUtil.floor(d) + 0.8)).doubleValue();
                d2 = new BigDecimal(String.valueOf((double)MathUtil.floor(d2) + 0.8)).doubleValue();
            }
        } else if (n == 5) {
            if (bl) {
                d = new BigDecimal(String.valueOf((double)MathUtil.floor(d) + 0.8)).doubleValue();
                d2 = new BigDecimal(String.valueOf((double)MathUtil.floor(d2) + 0.2)).doubleValue();
            } else {
                d = new BigDecimal(String.valueOf((double)MathUtil.floor(d) + 0.2)).doubleValue();
                d2 = new BigDecimal(String.valueOf((double)MathUtil.floor(d2) + 0.2)).doubleValue();
            }
        }
        return new double[]{d, d2};
    }

    public float[] p(EntityPlayerSP entityPlayerSP, double[] dArray, int n) {
        float f = 0.1f * this.U(dArray, new double[]{entityPlayerSP.z(), entityPlayerSP.h()}, n);
        float f2 = n == 1 ? 135.0f - f : (n == 2 ? -135.0f - f : (n == 3 ? -45.0f - f : 45.0f - f));
        return new float[]{f2, this.moveTimer.getLastMS() > 500L ? 83.0f : 81.0f};
    }

    public boolean Y(EntityPlayerSP entityPlayerSP) {
        RayTraceResult rayTraceResult = RotationManager.b.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic();
        if (rayTraceResult.isNull() || !rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.block())) {
            return false;
        }
        int n = rayTraceResult.Z();
        double d = entityPlayerSP.q();
        if (d > 0.1 || d < -0.1 || this.switching) {
            return n != 0;
        }
        return n > 1;
    }

    public float U(double[] dArray, double[] dArray2, int n) {
        double[] dArray3 = Scaffold.Access.X(this.b, new double[]{dArray[0], 0.0, dArray[1]}, (int)(RotationUtil.y(dArray[0], 0.0, dArray[1], dArray2[0], 0.0, dArray2[1]) + (double)Scaffold.Access.n(this.b, 1)), n);
        float f = (float)((dArray3[0] - dArray[0]) * (dArray2[1] - dArray[1]) - (dArray3[2] - dArray[1]) * (dArray2[0] - dArray[0]));
        return f;
    }

    @Override
    public void onDisable() {
        Scaffold.Access.J$src$V$dauhr4(this.b);
        this.resetState();
    }
}
