package gg.vape.module.blatant.scaffold;

import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPostTick;
import gg.vape.event.impl.EventPreEntityUpdate;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventRender2D;
import gg.vape.input.KeyBindingInputState;
import gg.vape.input.KeyboardCodeUtil;
import gg.vape.module.Mod;
import gg.vape.module.SubModule;
import gg.vape.module.blatant.Scaffold;
import gg.vape.module.blatant.scaffold.ScaffoldEdgeSneakHelper;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.movement.MovementInputHelper;
import gg.vape.movement.PlayerMovementTaskManager;
import gg.vape.movement.TargetPositionMovementTask;
import gg.vape.rotation.RotationManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.MathUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.GameSettings;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.RayTraceResult_type;
import java.math.BigDecimal;
import java.util.ArrayList;

public class TellyBridgeScaffoldMode
extends SubModule<Scaffold> {
    private final BooleanValue U;
    private ArrayList<double[]> C;
    KeyBinding p;
    private boolean vL = false;
    private double[] b = null;
    private int O = 0;
    private TimerUtil s;
    ScaffoldEdgeSneakHelper v1;
    boolean K;
    GameSettings v;
    float[] D;
    Scaffold Z;
    private TimerUtil ve;
    private final NumberValue P = NumberValue.E(this, "Activation Blocks", "#", "", 1.0, 2.0, 4.0, "Manual blocks placed before bridging");
    private ItemStack L;
    private boolean v9;
    TargetPositionMovementTask vs = null;
    private TimerUtil S;
    KeyBinding J;
    KeyBinding c;
    private int vA = 0;
    private int r = 0;
    private double[] V = null;
    private final NumberValue o = NumberValue.create((Object)this, "Y increase", "#", "", 0.0, 1.0, 3.0, 1.0);
    KeyBinding H;
    double[] I;
    private boolean A;
    private int t = 0;
    private ArrayList<Integer> F;

    private boolean A(EntityPlayerSP entityPlayerSP) {
        double d = MathUtil.floor(entityPlayerSP.z());
        double d2 = MathUtil.floor(entityPlayerSP.h());
        double d3 = Scaffold.Access.J(this.Z, entityPlayerSP);
        if (!Scaffold.Access.a(this.Z, entityPlayerSP.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt())) {
            this.V = null;
            this.vA = 0;
            return true;
        }
        int n = Scaffold.Access.u$src$I$dyhmyg(this.Z);
        if (this.t != 0 && n != this.t) {
            this.V = null;
            this.vA = 0;
        }
        this.t = n;
        double[] dArray = new double[]{d, d3, d2};
        double[] dArray2 = Scaffold.Access.X(this.Z, dArray, 1, this.t);
        double[] dArray3 = Scaffold.Access.X(this.Z, dArray, 2, this.t);
        if (this.V == null && entityPlayerSP.b$src$Z$fqlxe4()) {
            if (Scaffold.Access.U(this.Z, dArray)) {
                this.V = dArray;
            } else if (Scaffold.Access.U(this.Z, dArray2)) {
                this.V = dArray2;
            } else if (Scaffold.Access.U(this.Z, dArray3)) {
                this.V = dArray3;
            }
        } else if (this.V != null) {
            if ((double)this.vA >= (Double)this.P.K()) {
                this.C.add(this.V);
                this.vA = 0;
                double[] dArray4 = Scaffold.Access.e(this.Z, Scaffold.Access.X(this.Z, this.V, -1, this.t), 0.0, this.t);
                this.z(this.T(new double[]{dArray4[0], dArray4[2]}), false);
                this.L = entityPlayerSP.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt();
                this.V = null;
                this.A = true;
                return false;
            }
            if (!Scaffold.Access.U(this.Z, this.V)) {
                ++this.vA;
                double[] dArray5 = Scaffold.Access.X(this.Z, this.V, 1, this.t);
                boolean bl = Scaffold.Access.U(this.Z, dArray5);
                if (bl && (double)this.vA < (Double)this.P.K()) {
                    this.V = dArray5;
                } else if (!bl) {
                    this.V = null;
                    this.vA = 0;
                }
            } else if (Scaffold.Access.X(this.Z, this.V, dArray, this.t, (Double)this.P.K(), this.vA)) {
                this.V = null;
                this.vA = 0;
            }
        }
        return true;
    }

    @EventHandler
    public void j(EventPostTick eventPostTick) {
        if (this.K) {
            this.v1.g(eventPostTick);
        }
    }

    private void f(int n) {
        double[] dArray = this.C.get(this.C.size() - 1);
        this.C.clear();
        while (this.C.size() != n) {
            this.C.add(dArray);
        }
    }

    private double[] T() {
        double[] dArray;
        int n = this.C.size();
        if (n == 0) {
            return null;
        }
        if (this.O != 0 && n == 4) {
            dArray = Scaffold.Access.e(this.Z, this.C.get(n - 1), 0.2, this.t);
        } else {
            double d = 0.3;
            double d2 = 0.2;
            dArray = Scaffold.Access.S(this.Z, this.C.get(n - 1), d, d2, this.t);
        }
        return dArray;
    }

    private void m(EntityPlayerSP entityPlayerSP) {
        double[] dArray;
        int n = this.C.size();
        if (n == 0) {
            return;
        }
        if (entityPlayerSP.b$src$Z$fqlxe4() && (this.O != 0 || this.O == 0 && entityPlayerSP.B$src$Z$f90iek()) && this.d(entityPlayerSP, dArray = this.C.get(n - 1))) {
            MovementInputHelper.w(this.p, true);
            return;
        }
        if (this.p.isKeyDown()) {
            MovementInputHelper.w(this.p, false);
        }
        if (!this.J.isKeyDown()) {
            MovementInputHelper.w(this.J, true);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void t(EntityPlayerSP entityPlayerSP) {
        block12: {
            if (Minecraft.currentScreen().getObject() == null) {
                KeyboardCodeUtil.v();
            }
            if (!this.vL) {
                return;
            }
            if (!entityPlayerSP.b$src$Z$fqlxe4()) {
                this.v9 = true;
            }
            this.v();
            if (entityPlayerSP.b$src$Z$fqlxe4() && (this.A || this.v9)) {
                this.O = this.A || !this.y(entityPlayerSP) && this.k$src$Z$ppygix(entityPlayerSP) ? 0 : 1;
                this.A = false;
                this.I = null;
                this.b = null;
                if (this.r >= this.F$src$I$1um2x44() && this.O == 1) {
                    if ((Double)this.o.K() == 0.0 && !this.t$src$Z$186scao(entityPlayerSP)) {
                        this.z(this.S(false), false);
                        this.f(5);
                        this.v9 = false;
                        break block12;
                    } else {
                        this.O = 0;
                        if (this.b == null) {
                            this.b = Scaffold.Access.e(this.Z, Scaffold.Access.X(this.Z, this.C.get(this.C.size() - 1), 1, this.t), 0.0, this.t);
                            this.z(this.T(new double[]{this.b[0], this.b[2]}), false);
                        }
                        this.f(1);
                        this.k(entityPlayerSP);
                        return;
                    }
                }
                if (this.O == 0) {
                    this.D = this.e(entityPlayerSP, this.t);
                    Scaffold.Access.S(this.Z, this.D, Scaffold.Access.L(this.Z, this.D));
                    this.s.reset();
                    MovementInputHelper.w(this.J, true);
                    this.z(this.S(true), false);
                    this.f(1);
                    this.v9 = false;
                    this.r = 0;
                } else {
                    this.z(this.S(false), false);
                    this.f(4);
                    ++this.r;
                    this.v9 = false;
                }
            }
        }
        if (!entityPlayerSP.b$src$Z$fqlxe4()) {
            this.k(entityPlayerSP);
        }
        this.m(entityPlayerSP);
        if (this.H(10000)) {
            return;
        }
    }

    @Override
    public void onEnable() {
        this.K = true;
    }

    private boolean q(EntityPlayerSP entityPlayerSP) {
        double d = entityPlayerSP.z();
        double d2 = entityPlayerSP.h();
        if (this.t == 6) {
            d += 0.15;
        } else if (this.t == 8) {
            d -= 0.15;
        } else if (this.t == 7) {
            d2 += 0.15;
        } else if (this.t == 5) {
            d2 -= 0.15;
        } else {
            return true;
        }
        d = MathUtil.floor(d);
        double d3 = Scaffold.Access.J(this.Z, entityPlayerSP);
        d2 = MathUtil.floor(d2);
        return !Scaffold.Access.i(this.Z, d, d3, d2) && entityPlayerSP.b$src$Z$fqlxe4();
    }

    private boolean d(EntityPlayerSP entityPlayerSP, double[] dArray) {
        double d = dArray[0];
        double d2 = dArray[2];
        double d3 = 0.8;
        if (this.t == 6) {
            double d4 = entityPlayerSP.z() - (d += d3);
            return d4 >= -0.05;
        }
        if (this.t == 8) {
            double d5 = entityPlayerSP.z() - (d -= 1.0 - d3);
            return d5 <= 0.05;
        }
        if (this.t == 7) {
            double d6 = entityPlayerSP.h() - (d2 += d3);
            return d6 >= -0.05;
        }
        if (this.t == 5) {
            double d7 = entityPlayerSP.h() - (d2 -= 1.0 - d3);
            return d7 <= 0.05;
        }
        return false;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public TellyBridgeScaffoldMode(Mod mod, String string) {
        super(mod, string);
        this.U = BooleanValue.create(this, "Require right click", true, "On: Holding right click, and backwards move key, continues scaffold. Releasing either deactivates\nOff: Holding backwards move key continues scaffold, releasing deactivates\n");
        this.Z = (Scaffold)this.getParent();
        this.v1 = new ScaffoldEdgeSneakHelper((Mod)this.getParent(), "legit");
        this.S = new TimerUtil();
        this.ve = new TimerUtil();
        this.C = new ArrayList();
        this.s = new TimerUtil();
        this.v = Minecraft.gameSettings();
        this.c = this.v.b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
        this.H = this.v.s();
        this.J = this.v.r();
        this.p = this.v.O();
        this.F = new ArrayList();
        this.addValue(this.U, this.P, this.o);
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        if (Minecraft.thePlayer().isNull() || Minecraft.theWorld().isNull()) {
            return;
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (this.vL) {
            SharedModuleControlClaims.l.K(this);
            SharedModuleControlClaims.x.i();
        } else {
            SharedModuleControlClaims.l.T(this);
            SharedModuleControlClaims.x.Q();
        }
        if (!this.K && this.vL && this.v(entityPlayerSP)) {
            this.c.onTick(1);
        }
        if (this.K) {
            if (this.vL) {
                this.v1.onEnable();
                Scaffold.Access.J$src$V$dauhr4(this.Z);
            }
            this.H();
            this.K = this.A(entityPlayerSP);
            return;
        }
        if (this.M(entityPlayerSP)) {
            this.v1.onEnable();
            return;
        }
        if (!this.vL && this.vs != null && this.vs.q$src$Z$naak2i()) {
            this.vL = true;
        }
        this.t(entityPlayerSP);
    }

    private boolean M(EntityPlayerSP entityPlayerSP) {
        boolean bl;
        if (!SharedModuleControlClaims.I.U(this.Z)) {
            this.K = true;
            MovementInputHelper.q();
            return true;
        }
        if (!this.C(entityPlayerSP)) {
            this.K = true;
            MovementInputHelper.r();
            return true;
        }
        boolean bl2 = ClientSettings.B(this.H);
        boolean bl3 = bl = this.U.L() == false || KeyBindingInputState.q$src$Z$1enyqt3();
        if (!bl2 || !bl) {
            if (this.vL) {
                if (!entityPlayerSP.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().isFlying() && !this.q(entityPlayerSP)) {
                    return false;
                }
                MovementInputHelper.r();
            }
            this.K = true;
            return true;
        }
        this.F = Scaffold.Access.J(this.Z, this.F);
        if (this.vL && Scaffold.Access.M(this.Z, this.F) >= 10) {
            MovementInputHelper.r();
            this.K = true;
            this.ve.reset();
            return true;
        }
        return false;
    }

    @EventHandler
    public void onRender2D(EventRender2D eventRender2D) {
        Scaffold.Access.V$src$V$dhg0vg(this.Z);
    }

    private void k(EntityPlayerSP entityPlayerSP) {
        double[] dArray = this.T();
        if (this.I == null || MathUtil.floor(this.I[0]) != MathUtil.floor(dArray[0]) || MathUtil.floor(this.I[1]) != MathUtil.floor(dArray[1]) || MathUtil.floor(this.I[2]) != MathUtil.floor(dArray[2])) {
            this.I = dArray;
            double[] dArray2 = this.C.get(this.C.size() - 1);
            int n = this.t;
            float f = Scaffold.Access.W(this.Z, this.t);
            double[] dArray3 = this.I;
            Scaffold scaffold = this.Z;
            Scaffold.Access.t(scaffold, dArray3, f, n, dArray2);
        }
    }

    private int F$src$I$1um2x44() {
        double d = Math.random();
        int n = ((Double)this.o.K()).intValue();
        if (n != 0) {
            int n2 = n - 1;
            int n3 = n + 1;
            if (d < 0.15) {
                return n3;
            }
            if (d < 0.25) {
                return n2;
            }
            return n;
        }
        int n4 = 0;
        int n5 = 0;
        if (d < 0.15) {
            return n5;
        }
        if (d < 0.25) {
            return n4;
        }
        return n;
    }

    private float[] e(EntityPlayerSP entityPlayerSP, int n) {
        double d = RotationUtil.c();
        double d2 = 90.0;
        if (n == 6) {
            d = 230.0;
        } else if (n == 8) {
            d = 50.0;
        } else if (n == 7) {
            d = 320.0;
        } else if (n == 5) {
            d = 140.0;
        }
        return new float[]{(float)(d += Math.random() < 0.5 ? Math.random() * -4.0 : Math.random() * 4.0), (float)(d2 += Math.random() * -5.0)};
    }

    public boolean v(EntityPlayerSP entityPlayerSP) {
        RayTraceResult rayTraceResult = RotationManager.b.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic();
        if (rayTraceResult.isNull() || !rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.block()) || this.C.size() < 1) {
            return false;
        }
        double[] dArray = this.C.get(this.C.size() - 1);
        double[] dArray2 = new double[]{MathUtil.floor((double)rayTraceResult.g()), MathUtil.floor((double)rayTraceResult.T()), MathUtil.floor((double)rayTraceResult.a$src$I$8nuo9d())};
        boolean bl = dArray[0] == dArray2[0] && dArray[1] == dArray2[1] && dArray[2] == dArray2[2];
        int n = rayTraceResult.Z();
        boolean bl2 = this.O != 0 && this.C.size() == 4 ? n == 1 : n > 1;
        return bl && bl2;
    }

    private void v() {
        int n;
        for (n = this.C.size() - 1; n >= Math.max(0, this.C.size() - 3) && Scaffold.Access.U(this.Z, this.C.get(n)); --n) {
            this.I = null;
            this.C.remove(n);
        }
        n = this.C.size();
        if (n == 0) {
            return;
        }
        double[] dArray = this.j(this.C.get(n - 1), n);
        if (!Scaffold.Access.i(this.Z, dArray[0], dArray[1], dArray[2])) {
            if (n == 6) {
                this.C.clear();
            }
            this.C.add(new double[]{dArray[0], dArray[1], dArray[2]});
            Scaffold.Access.W(this.Z);
        }
    }

    @Override
    public void onDisable() {
        Scaffold.Access.J$src$V$dauhr4(this.Z);
        this.H();
    }

    @EventHandler
    public void P(EventPreEntityUpdate eventPreEntityUpdate) {
        if (this.K) {
            this.v1.X(eventPreEntityUpdate);
        }
    }

    private double[] S(boolean bl) {
        double[] dArray = bl ? Scaffold.Access.X(this.Z, this.C.get(this.C.size() - 1), this.A ? 4 : 3, this.t) : Scaffold.Access.X(this.Z, this.C.get(this.C.size() - 1), 2, this.t);
        return this.T(new double[]{dArray[0], dArray[2]});
    }

    private void H() {
        this.K = true;
        this.r = 0;
        this.vs = null;
        this.S.reset();
        this.D = null;
        this.vL = false;
        this.t = 0;
        this.C.clear();
        this.L = null;
    }

    private void z(double[] dArray, boolean bl) {
        this.vs = new TargetPositionMovementTask(dArray[0], dArray[1]);
        this.vs.g(bl);
        this.vs.v(true);
        PlayerMovementTaskManager.G.i(this.vs);
        this.S.reset();
    }

    private boolean t$src$Z$186scao(EntityPlayerSP entityPlayerSP) {
        if (this.t % 2 == 0) {
            return Math.abs(entityPlayerSP.t()) < 0.6;
        }
        return Math.abs(entityPlayerSP.T()) < 0.6;
    }

    private boolean H(int n) {
        return false;
    }

    private boolean y(EntityPlayerSP entityPlayerSP) {
        if (this.t % 2 == 0) {
            return Math.abs(entityPlayerSP.t()) >= 0.1;
        }
        return Math.abs(entityPlayerSP.T()) >= 0.1;
    }

    private boolean C(EntityPlayerSP entityPlayerSP) {
        int n = Scaffold.Access.l(this.Z);
        if (n == -1 || Scaffold.Access.n(this.Z, 1) < 5) {
            return false;
        }
        if (this.L != null && !entityPlayerSP.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt().equals(this.L)) {
            int n2 = Scaffold.Access.s(this.Z, entityPlayerSP, this.L);
            if (n2 != -1) {
                Scaffold.Access.s(this.Z, n2);
            } else if (entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v() != n) {
                Scaffold.Access.s(this.Z, n);
            }
        }
        return true;
    }

    private boolean k$src$Z$ppygix(EntityPlayerSP entityPlayerSP) {
        double d = this.C.get(this.C.size() - 1)[0];
        double d2 = this.C.get(this.C.size() - 1)[2];
        double d3 = 1.0;
        double d4 = 0.1;
        if (this.t == 6) {
            double d5 = entityPlayerSP.z() - (d += d3);
            return d5 < -d4 && Math.abs(entityPlayerSP.h() - (d2 += 0.6)) <= 0.15;
        }
        if (this.t == 8) {
            double d6 = entityPlayerSP.z() - (d -= 1.0 - d3);
            return d6 > d4 && Math.abs(entityPlayerSP.h() - (d2 += 0.4)) <= 0.15;
        }
        if (this.t == 7) {
            double d7 = entityPlayerSP.h() - (d2 += d3);
            return d7 < -d4 && Math.abs(entityPlayerSP.z() - (d += 0.4)) <= 0.15;
        }
        if (this.t == 5) {
            double d8 = entityPlayerSP.h() - (d2 -= 1.0 - d3);
            return d8 > d4 && Math.abs(entityPlayerSP.z() - (d += 0.6)) <= 0.15;
        }
        return false;
    }

    private double[] j(double[] dArray, int n) {
        int n2 = n;
        double d = dArray[0];
        double d2 = dArray[1];
        double d3 = dArray[2];
        if (this.O != 0 && n2 == 4) {
            d2 += 1.0;
        } else if (this.t == 6) {
            d += 1.0;
        } else if (this.t == 8) {
            d -= 1.0;
        } else if (this.t == 7) {
            d3 += 1.0;
        } else if (this.t == 5) {
            d3 -= 1.0;
        }
        return new double[]{d, d2, d3};
    }

    private double[] T(double[] dArray) {
        double d = dArray[0];
        double d2 = dArray[1];
        if (this.t == 6) {
            d = new BigDecimal(String.valueOf((double)MathUtil.floor(d) + 0.3)).doubleValue();
            d2 = new BigDecimal(String.valueOf((double)MathUtil.floor(d2) + 0.6)).doubleValue();
        } else if (this.t == 8) {
            d = new BigDecimal(String.valueOf((double)MathUtil.floor(d) + 0.7)).doubleValue();
            d2 = new BigDecimal(String.valueOf((double)MathUtil.floor(d2) + 0.4)).doubleValue();
        } else if (this.t == 7) {
            d = new BigDecimal(String.valueOf((double)MathUtil.floor(d) + 0.4)).doubleValue();
            d2 = new BigDecimal(String.valueOf((double)MathUtil.floor(d2) + 0.3)).doubleValue();
        } else if (this.t == 5) {
            d = new BigDecimal(String.valueOf((double)MathUtil.floor(d) + 0.6)).doubleValue();
            d2 = new BigDecimal(String.valueOf((double)MathUtil.floor(d2) + 0.7)).doubleValue();
        }
        return new double[]{d, d2};
    }
}
