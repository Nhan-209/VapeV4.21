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
import gg.vape.runtime.ObfuscatedRuntimeException;
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
    private final KeyBinding P;
    private TimerUtil L;
    private double[] o;
    private static final long ib = -7821191894771171308L;
    private ArrayList<Integer> t;
    private boolean F = false;
    private boolean C = false;
    private boolean Z = false;
    private int G5;
    private final KeyBinding Gh;
    private boolean D = false;
    private final KeyBinding Gt;
    private int v;
    private int Gj;
    private int U = 0;
    private final NumberValue GB = NumberValue.E(this, "Activation blocks", "#", "", 1.0, 2.0, 4.0, "Manual blocks placed before bridging");
    private boolean V = false;
    boolean S;
    private int GG;
    private boolean I;
    TargetPositionMovementTask Gz = null;
    private boolean c = true;
    ArrayList<Integer> Gn;
    private ItemStack H;
    private TimerUtil r;
    private boolean A;
    private double[] GE;
    private boolean K = false;
    float[] GF;
    private boolean s = false;
    ScaffoldEdgeSneakHelper O;
    Scaffold b = (Scaffold)this.getParent();
    private boolean p = false;
    private double[] J;

    private boolean b(EntityPlayerSP entityPlayerSP) {
        double d = entityPlayerSP.z();
        double d2 = entityPlayerSP.h();
        if (this.U == 1) {
            d += 0.2;
            d2 += 0.2;
        } else if (this.U == 2) {
            d -= 0.2;
            d2 += 0.2;
        } else if (this.U == 3) {
            d -= 0.2;
            d2 -= 0.2;
        } else if (this.U == 4) {
            d += 0.2;
            d2 -= 0.2;
        } else if (this.U == 6) {
            d += 0.25;
            d2 = this.o[1];
        } else if (this.U == 8) {
            d -= 0.25;
            d2 = this.o[1];
        } else if (this.U == 7) {
            d = this.o[0];
            d2 += 0.25;
        } else if (this.U == 5) {
            d = this.o[0];
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

    private boolean m() {
        if (this.Gz != null) {
            if (!this.Gz.q$src$Z$naak2i() && this.GG < this.G5) {
                ++this.GG;
                if (this.Z) {
                    return true;
                }
            } else if (this.Gz.q$src$Z$naak2i()) {
                this.GG = 0;
                this.Z = false;
            } else {
                this.Gz.s(true);
                Scaffold.Access.A(this.b);
                this.Gz = null;
                this.GG = 0;
                this.Z = false;
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
        return !this.I;
    }

    @Override
    public void onEnable() {
        this.S = true;
    }

    private void k(float[] fArray, float f) {
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

    private boolean P(EntityPlayerSP entityPlayerSP, int n) {
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

    private boolean g(EntityPlayerSP entityPlayerSP) {
        int n = Scaffold.Access.l(this.b);
        if (n == -1) {
            return false;
        }
        if (this.H != null && !entityPlayerSP.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt().equals(this.H)) {
            int n2 = Scaffold.Access.s(this.b, entityPlayerSP, this.H);
            if (n2 != -1) {
                Scaffold.Access.s(this.b, n2);
            } else if (entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v() != n) {
                Scaffold.Access.s(this.b, n);
            }
        }
        return true;
    }

    private boolean c(EntityPlayerSP entityPlayerSP) {
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

    private void R(EntityPlayerSP entityPlayerSP) {
        boolean bl;
        if (Minecraft.currentScreen().getObject() == null) {
            KeyboardCodeUtil.v();
        }
        boolean bl2 = this.N(entityPlayerSP, this.U);
        boolean bl3 = bl = this.U < 5;
        if (!bl2) {
            this.L.reset();
        }
        this.C = bl2;
        if (bl2) {
            MovementInputHelper.w(this.Gt, false);
            MovementInputHelper.w(this.P, false);
            MovementInputHelper.w(this.Gh, false);
            if (!bl) {
                if (this.I) {
                    MovementInputHelper.w(this.Gh, false);
                } else {
                    MovementInputHelper.w(this.P, false);
                }
            }
        } else {
            MovementInputHelper.w(this.Gt, true);
            if (!bl) {
                if (this.I) {
                    MovementInputHelper.w(this.Gh, true);
                } else {
                    MovementInputHelper.w(this.P, true);
                }
            } else {
                MovementInputHelper.w(this.P, false);
                MovementInputHelper.w(this.Gh, false);
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

    private double[] e(EntityPlayerSP entityPlayerSP, int n) {
        double[] dArray = new double[]{MathUtil.floor(entityPlayerSP.z()), entityPlayerSP.q() > 0.0 ? Scaffold.Access.J(this.b, entityPlayerSP) + 1.0 : Scaffold.Access.J(this.b, entityPlayerSP), MathUtil.floor(entityPlayerSP.h())};
        if (Scaffold.Access.U(this.b, dArray) && Scaffold.Access.U(this.b, dArray = Scaffold.Access.X(this.b, dArray, -1, n)) && Scaffold.Access.U(this.b, dArray = Scaffold.Access.X(this.b, dArray, 1, this.Z(n, 2)))) {
            dArray = Scaffold.Access.X(this.b, dArray, -2, this.Z(n, 2));
        }
        return dArray;
    }

    private boolean v(EntityPlayerSP entityPlayerSP) {
        if (!SharedModuleControlClaims.I.U(this.b)) {
            this.S = true;
            MovementInputHelper.q();
            return true;
        }
        if (!this.g(entityPlayerSP)) {
            this.S = true;
            MovementInputHelper.i();
            return true;
        }
        if (!ClientSettings.B(this.Gt)) {
            if (!entityPlayerSP.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().isFlying() && !this.b(entityPlayerSP)) {
                return false;
            }
            MovementInputHelper.i();
            this.S = true;
            return true;
        }
        if (!FreeLookHudModule.z()) {
            this.t = Scaffold.Access.J(this.b, this.t);
            if (Scaffold.Access.M(this.b, this.t) >= 10) {
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

    private boolean S$src$Z$1ho1ph5() {
        if (this.b.A != null) {
            if (Scaffold.Access.P(this.b, RotationUtil.c(), this.b.A.L) > 4.0) {
                if (this.F) {
                    return true;
                }
            } else if (this.F) {
                Scaffold.Access.W(this.b);
                this.F = false;
            }
        }
        return false;
    }

    @EventHandler
    public void onRender2D(EventRender2D eventRender2D) {
        Scaffold.Access.V$src$V$dhg0vg(this.b);
    }

    private boolean w$src$Z$1i7uau5() {
        boolean bl = this.S$src$Z$1ho1ph5();
        boolean bl2 = this.m();
        return bl || bl2;
    }

    private boolean N(EntityPlayerSP entityPlayerSP, int n) {
        AxisAlignedBB axisAlignedBB;
        boolean bl = ClientSettings.B(Minecraft.gameSettings().O());
        if (n > 4) {
            double d = entityPlayerSP.z();
            double d2 = entityPlayerSP.h();
            this.V = bl;
            if (n == 6) {
                d += -0.15;
                d2 = this.o[1];
            } else if (n == 8) {
                d -= -0.15;
                d2 = this.o[1];
            } else if (n == 7) {
                d = this.o[0];
                d2 += -0.15;
            } else if (n == 5) {
                d = this.o[0];
                d2 -= -0.15;
            }
            d = MathUtil.floor(d);
            double d3 = Scaffold.Access.J(this.b, entityPlayerSP);
            d2 = MathUtil.floor(d2);
            return Scaffold.Access.i(this.b, d, d3, d2);
        }
        double d = -0.16;
        this.V = bl;
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

    private float V(float[] fArray, int n) {
        return (float)Math.min(2.0 + Scaffold.Access.P(this.b, RotationUtil.c(), fArray[0]) / (double)n, 12.0);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private boolean j(EntityPlayerSP entityPlayerSP) {
        double d = MathUtil.floor(entityPlayerSP.z());
        double d2 = MathUtil.floor(entityPlayerSP.h());
        double d3 = Scaffold.Access.J(this.b, entityPlayerSP);
        if (!Scaffold.Access.a(this.b, entityPlayerSP.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt())) {
            this.J = null;
            this.v = 0;
            return true;
        }
        int n = Scaffold.Access.u$src$I$dyhmyg(this.b);
        if (this.U != 0 && n != this.U) {
            this.J = null;
            this.v = 0;
        }
        this.U = n;
        double[] dArray = new double[]{d, d3, d2};
        double[] dArray2 = Scaffold.Access.X(this.b, dArray, 1, this.U);
        double[] dArray3 = Scaffold.Access.X(this.b, dArray, 2, this.U);
        if (this.J == null && entityPlayerSP.b$src$Z$fqlxe4()) {
            if (Scaffold.Access.U(this.b, dArray)) {
                this.J = dArray;
            } else if (Scaffold.Access.U(this.b, dArray2)) {
                this.J = dArray2;
            } else if (Scaffold.Access.U(this.b, dArray3)) {
                this.J = dArray3;
            }
        } else if (this.J != null) {
            if ((double)this.v >= (Double)this.GB.K()) {
                this.I = this.Z(entityPlayerSP, this.U);
                this.o = this.W(new double[]{this.J[0], this.J[2]}, this.U, this.I);
                this.H = entityPlayerSP.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt();
                if (!ClientSettings.B(Minecraft.gameSettings().O()) && !entityPlayerSP.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().isFlying()) {
                    this.o(this.o, true, false, 40);
                    this.GF = this.G(entityPlayerSP, this.I);
                    Scaffold.Access.S(this.b, this.GF, this.V(this.GF, 15));
                }
                this.v = 0;
                this.J = null;
                this.L.reset();
                return false;
            }
            if (!Scaffold.Access.U(this.b, this.J)) {
                ++this.v;
                double[] dArray4 = Scaffold.Access.X(this.b, this.J, 1, this.U);
                boolean bl = Scaffold.Access.U(this.b, dArray4);
                if (bl && (double)this.v < (Double)this.GB.K()) {
                    this.J = dArray4;
                } else if (!bl) {
                    this.J = null;
                    this.v = 0;
                }
            } else if (Scaffold.Access.X(this.b, this.J, dArray, this.U, (Double)this.GB.K(), this.v)) {
                this.J = null;
                this.v = 0;
            }
        }
        return true;
    }

    private boolean Q(EntityPlayerSP entityPlayerSP) {
        if (Minecraft.currentScreen().getObject() == null) {
            KeyboardCodeUtil.v();
        }
        if (this.Gz != null && this.Gz.q$src$Z$naak2i() && this.K) {
            this.C = true;
            this.Gz = null;
            this.o = this.GE;
            this.U = this.Gj;
            this.I = this.A;
            this.K = false;
            this.p = false;
            MovementInputHelper.i();
            this.L.reset();
            this.r.reset();
            if (this.U < 5) {
                float[] fArray = this.p(entityPlayerSP, this.W(this.o, this.U, this.I), this.U);
                this.k(fArray, this.V(fArray, 15));
            } else {
                float[] fArray = this.G(entityPlayerSP, this.I);
                this.k(fArray, this.V(fArray, 12));
            }
            this.F = true;
            return true;
        }

        boolean bl = ClientSettings.B(Minecraft.gameSettings().x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg());
        boolean bl7 = ClientSettings.B(Minecraft.gameSettings().g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3());
        if (!this.K) {
            boolean bl4;
            boolean bl3;
            if (this.c) {
                bl4 = bl && !this.D;
                bl3 = bl7 && !this.s;
            } else {
                bl4 = !bl && this.D;
                bl3 = !bl7 && this.s;
            }
            this.D = bl;
            this.s = bl7;

            if (this.c && this.r.hasTimeElapsed(0L)) {
                this.c = !bl4 && !bl3;
                if (bl4) {
                    this.Gj = this.Z(this.U, 1);
                } else if (bl3) {
                    this.Gj = this.Z(this.U, -1);
                }
            } else if (!this.c) {
                this.c = bl4 || bl3;
                if (bl4) {
                    this.Gj = this.Z(this.U, -1);
                } else if (bl3) {
                    this.Gj = this.Z(this.U, 1);
                }
            } else {
                if (bl) {
                    MovementInputHelper.w(this.P, false);
                } else if (bl7) {
                    MovementInputHelper.w(this.Gh, false);
                }
                return false;
            }
            this.K = bl4 || bl3;
        }

        if (this.K) {
            this.Gz = null;
            double[] dArray = new double[]{MathUtil.floor(entityPlayerSP.z()), Scaffold.Access.J(this.b, entityPlayerSP), MathUtil.floor(entityPlayerSP.h())};
            if (!(Scaffold.Access.U(this.b, dArray) || this.c(entityPlayerSP) || this.P(entityPlayerSP, this.U))) {
                this.A = this.Z(entityPlayerSP, this.Gj);
                this.GE = this.W(new double[]{dArray[0], dArray[2]}, this.Gj, this.A);
                Scaffold.Access.J$src$V$dauhr4(this.b);
                if (this.U > 4 && !this.I && this.Gj == this.Z(this.U, -1) || this.U > 4 && this.I && this.Gj == this.Z(this.U, 1)) {
                    this.Gz = new TargetPositionMovementTask(0.0, 0.0);
                    this.Gz.s(true);
                } else if (Math.abs(entityPlayerSP.z() - this.GE[0]) > 0.15 || Math.abs(entityPlayerSP.h() - this.GE[1]) > 0.15) {
                    this.o(this.GE, true, false, 40);
                } else {
                    this.Gz = new TargetPositionMovementTask(0.0, 0.0);
                    this.Gz.s(true);
                }
            } else if (!this.I) {
                if (bl && this.N(entityPlayerSP, this.U)) {
                    MovementInputHelper.w(this.P, false);
                } else if (bl7) {
                    MovementInputHelper.w(this.Gh, false);
                }
            } else if (bl7 && this.N(entityPlayerSP, this.U)) {
                MovementInputHelper.w(this.Gh, false);
            } else if (bl) {
                MovementInputHelper.w(this.P, false);
            }
        }
        return false;
    }

    private void G(double[] dArray, boolean bl, boolean bl2, boolean bl3, int n) {
        this.Gz = new TargetPositionMovementTask(dArray[0], dArray[1]);
        this.Z = bl;
        this.Gz.g(bl2);
        this.Gz.l(bl3);
        PlayerMovementTaskManager.G.i(this.Gz);
        this.GG = 0;
        this.G5 = n;
    }

    private boolean T(EntityPlayerSP entityPlayerSP) {
        if (this.L.hasTimeElapsed(800L)) {
            double[] dArray = this.e(entityPlayerSP, this.U);
            this.o = this.W(new double[]{dArray[0], dArray[2]}, this.U, this.I);
            this.GF = this.G(entityPlayerSP, this.I);
            Scaffold.Access.S(this.b, this.GF, this.V(this.GF, 15));
            this.o(this.o, true, false, 40);
            this.C = true;
            this.L.reset();
            return true;
        }
        return false;
    }

    private float[] G(EntityPlayerSP entityPlayerSP, boolean bl) {
        double d = entityPlayerSP.J();
        double d2 = entityPlayerSP.f();
        double d3 = entityPlayerSP.R();
        double d4 = d;
        double d5 = d;
        if (this.U == 6) {
            d4 = 135.0 + 20.0 * (this.o[1] - d3);
            d5 = 45.0 + 20.0 * (this.o[1] - d3);
        } else if (this.U == 8) {
            d4 = -45.0 - 20.0 * (this.o[1] - d3);
            d5 = -135.0 - 20.0 * (this.o[1] - d3);
        } else if (this.U == 7) {
            d4 = -135.0 - 20.0 * (this.o[0] - d2);
            d5 = 135.0 + 20.0 * (d2 - this.o[0]);
        } else if (this.U == 5) {
            d4 = 45.0 + 20.0 * (this.o[0] - d2);
            d5 = -45.0 - 20.0 * (d2 - this.o[0]);
        }
        d = bl ? d4 : d5;
        return new float[]{(float)d, this.L.hasTimeElapsed(300L) ? 80 : 78};
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
        if (!(this.S || this.F || this.Z)) {
            this.R(entityPlayerSP);
        }
        if (this.S) {
            if (this.Gz != null || this.GF != null) {
                Scaffold.Access.J$src$V$dauhr4(this.b);
                this.O.onEnable();
            }
            this.c();
            this.S = this.j(entityPlayerSP);
            SharedModuleControlClaims.l.T(this);
            SharedModuleControlClaims.x.Q();
            return;
        }
        SharedModuleControlClaims.l.K(this);
        SharedModuleControlClaims.x.i();
        if (this.v(entityPlayerSP)) {
            Scaffold.Access.W(this.b);
            this.O.onEnable();
            SharedModuleControlClaims.l.T(this);
            SharedModuleControlClaims.x.Q();
            return;
        }
        if (this.w$src$Z$1i7uau5()) {
            return;
        }
        if (this.Q(entityPlayerSP)) {
            return;
        }
        if (this.T(entityPlayerSP)) {
            return;
        }
        this.J(entityPlayerSP);
        float f = Math.abs(entityPlayerSP.J());
        this.R(entityPlayerSP);
    }

    private int Z(int n, int n2) {
        int n3 = this.Gn.indexOf(n) + n2 < 0 ? (this.Gn.indexOf(n) + n2) % this.Gn.size() + this.Gn.size() : (this.Gn.indexOf(n) + n2) % this.Gn.size();
        return this.Gn.get(n3);
    }

    public BlatantScaffoldMode(Mod mod, String string) {
        super(mod, string);
        this.O = new ScaffoldEdgeSneakHelper((Mod)this.getParent(), "legit");
        this.L = new TimerUtil();
        this.r = new TimerUtil();
        this.Gn = new ArrayList<Integer>(Arrays.asList(5, 4, 6, 1, 7, 2, 8, 3));
        this.t = new ArrayList();
        this.Gt = Minecraft.gameSettings().s();
        this.Gh = Minecraft.gameSettings().g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3();
        this.P = Minecraft.gameSettings().x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg();
        this.addValue(this.GB);
    }

    private void c() {
        this.S = true;
        this.o = null;
        this.Gz = null;
        this.GG = 0;
        this.GF = null;
        this.D = false;
        this.s = false;
        this.p = false;
        this.K = false;
        this.C = true;
        this.U = 0;
        this.Gj = 0;
        this.t = new ArrayList();
        this.G5 = (int)ib;
        this.H = null;
        this.A = false;
        this.c = true;
        SharedModuleControlClaims.l.T(this);
        SharedModuleControlClaims.x.Q();
    }

    private void o(double[] dArray, boolean bl, boolean bl2, int n) {
        this.Gz = new TargetPositionMovementTask(dArray[0], dArray[1]);
        this.Z = bl;
        this.Gz.g(bl2);
        PlayerMovementTaskManager.G.i(this.Gz);
        this.GG = 0;
        this.G5 = n;
    }

    private void J(EntityPlayerSP entityPlayerSP) {
        if (this.U == 0) {
            this.GF = new float[]{entityPlayerSP.J(), 90.0f};
        } else if (this.U < 5) {
            float[] fArray = this.GF;
            this.GF = this.p(entityPlayerSP, this.W(this.o, this.U, this.I), this.U);
            if (this.b.A == null || fArray == null || fArray[0] != this.GF[0] || fArray[1] != this.GF[1]) {
                Scaffold.Access.S(this.b, this.GF, this.V(this.GF, 15));
            }
        } else {
            float[] fArray = this.GF;
            this.GF = this.G(entityPlayerSP, this.I);
            if (this.b.A == null || fArray == null || fArray[0] != this.GF[0] || fArray[1] != this.GF[1]) {
                Scaffold.Access.S(this.b, this.GF, this.V(this.GF, 15));
            }
        }
    }

    private double[] W(double[] dArray, int n, boolean bl) {
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
        return new float[]{f2, this.L.getLastMS() > 500L ? 83.0f : 81.0f};
    }

    public boolean Y(EntityPlayerSP entityPlayerSP) {
        RayTraceResult rayTraceResult = RotationManager.b.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic();
        if (rayTraceResult.isNull() || !rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.block())) {
            return false;
        }
        int n = rayTraceResult.Z();
        double d = entityPlayerSP.q();
        if (d > 0.1 || d < -0.1 || this.K) {
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
        this.c();
    }
}
