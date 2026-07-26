package gg.vape.module.blatant.blockin;

import gg.vape.mapping.MappedClasses;
import gg.vape.module.blatant.blockin.AbstractBlockInMovementController;
import gg.vape.module.blatant.blockin.BlockPlacementGraph;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import gg.vape.utils.MathUtil;
import gg.vape.utils.PlayerSimulationUtil;
import gg.vape.wrapper.impl.AttributeInstance;
import gg.vape.wrapper.impl.AttributeModifier;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.EnchantmentHelper;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.EntitySelectors;
import gg.vape.wrapper.impl.MonsterAttributesBridge;
import gg.vape.wrapper.impl.MoverType;
import gg.vape.wrapper.impl.PotionRegistry;
import gg.vape.wrapper.impl.World;
import java.lang.invoke.MethodHandles;
import java.util.List;

public class BlockInLegacyMovementController1122
extends AbstractBlockInMovementController {
    private static final long a = ZkmLongKeyState.a(-255201125873829439L, 7162464027634338605L, MethodHandles.lookup().lookupClass()).a(201192532245173L);
    private static final long c;

    static {
        long l = a ^ 0x5578E3F4C9EAL;
        c = -428257519865954297L;
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void B() {
        this.U = this.D.L$src$I$1tmeeo5();
        this.B = this.D.z$src$I$1uboxyr();
        AttributeInstance attributeInstance = this.O.h(MonsterAttributesBridge.B());
        AttributeInstance attributeInstance2 = this.p.h(MonsterAttributesBridge.B());
        attributeInstance2.J();
        for (Object e : attributeInstance.I()) {
            attributeInstance2.applyModifier(new AttributeModifier(e));
        }
        this.p.M(this.O.F());
        this.p.k$src$V$5315b7(this.O.N$src$F$14ypudi());
    }

    private void l$src$V$6ly98l() {
        List list = this.P.i(this.p, this.p.u$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$kogbsu(), EntitySelectors.x(this.D));
        if (!list.isEmpty()) {
            for (Object e : list) {
                if (MappedClasses.z5.isInstance(e) || e == this.p.getObject() || e == this.O.getObject()) continue;
                this.p.z(new Entity(e));
            }
        }
    }

    private void x$src$V$6sjscx() {
        this.p.k$src$V$5315b7(this.Z);
        this.p.M(this.d);
        this.p.b(this.W);
    }

    @Override
    public void b(boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5, boolean bl6) {
        this.g = bl;
        this.M = bl2;
        this.r = bl3;
        this.R = bl4;
        this.E = bl5;
        this.A = bl6;
    }

    private void x(float f, float f2, float f3) {
        this.p.z(false);
        MoverType moverType = MoverType.X();
        if (!this.p.h$src$Z$ftwoya() || this.p.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().isFlying()) {
            if (!this.p.Q$src$Z$fh9faz() || this.p.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().isFlying()) {
                boolean bl;
                float f4 = 0.91f;
                if (this.p.b$src$Z$fqlxe4()) {
                    f4 = this.P.getBlockState(BlockPos.create(MathUtil.floor(this.p.z()), MathUtil.floor(this.p.u$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$kogbsu().getMinY()) - 1, MathUtil.floor(this.p.h()))).getBlock().c() * 0.91f;
                }
                float f5 = 0.16277136f / (f4 * f4 * f4);
                float f6 = this.p.b$src$Z$fqlxe4() ? this.p.C$src$F$1i1kt1e() * f5 : this.p.y$src$F$15mczw1();
                this.p.x(f, f2, f3, f6);
                f4 = 0.91f;
                if (this.p.b$src$Z$fqlxe4()) {
                    f4 = this.P.getBlockState(BlockPos.create(MathUtil.floor(this.p.z()), MathUtil.floor(this.p.u$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$kogbsu().getMinY()) - 1, MathUtil.floor(this.p.h()))).getBlock().c() * 0.91f;
                }
                if (this.p.S$src$Z$151gttj()) {
                    boolean bl2;
                    float f7 = 0.15f;
                    this.p.r(MathUtil.clamp(this.p.t(), (double)(-f7), (double)f7));
                    this.p.i(MathUtil.clamp(this.p.T(), (double)(-f7), (double)f7));
                    this.p.U(0.0f);
                    if (this.p.q() < -0.15) {
                        this.p.k(-0.15);
                    }
                    if ((bl2 = this.p.P()) && this.p.q() < 0.0) {
                        this.p.k(0.0);
                    }
                }
                this.p.r(moverType, this.p.t(), this.p.q(), this.p.T());
                if (this.p.r() && this.p.S$src$Z$151gttj()) {
                    this.p.k(0.2);
                }
                boolean bl3 = bl = this.P.j$src$Z$11aji0a(BlockPos.create((int)this.p.z(), 0, (int)this.p.h())) && this.P.j(BlockPos.create((int)this.p.z(), 0, (int)this.p.h())).F();
                if (!this.P.I() || bl) {
                    if (!this.p.v$src$Z$g1lt9c()) {
                        this.p.k(this.p.q() - 0.08);
                    }
                } else if (this.p.N() > 0.0) {
                    this.p.k(-0.1);
                } else {
                    this.p.k(0.0);
                }
                this.p.k(this.p.q() * (double)0.98f);
                this.p.r(this.p.t() * (double)f4);
                this.p.i(this.p.T() * (double)f4);
            } else {
                double d = this.p.N();
                this.p.x(f, f2, f3, 0.02f);
                this.p.b(this.p.t(), this.p.q(), this.p.T());
                this.p.r(this.p.t() * 0.5);
                this.p.k(this.p.q() * 0.5);
                this.p.i(this.p.T() * 0.5);
                if (!this.p.v$src$Z$g1lt9c()) {
                    this.p.k(this.p.q() - 0.02);
                }
                if (this.p.r() && this.p.i$src$Z$avhpwd(this.p.t(), this.p.q() + (double)0.6f - this.p.N() + d, this.p.T())) {
                    this.p.k((double)0.3f);
                }
            }
        } else {
            double d = this.p.N();
            float f8 = 0.8f;
            float f9 = 0.02f;
            float f10 = EnchantmentHelper.y(this.p);
            if (f10 > 3.0f) {
                f10 = 3.0f;
            }
            if (!this.p.b$src$Z$fqlxe4()) {
                f10 *= 0.5f;
            }
            if (f10 > 0.0f) {
                f8 += (0.54600006f - f8) * f10 / 3.0f;
                f9 += (this.p.C$src$F$1i1kt1e() * 1.0f - f9) * f10 / 3.0f;
            }
            this.p.x(f, f2, f3, f9);
            this.p.r(moverType, this.p.t(), this.p.q(), this.p.T());
            this.p.r(this.p.t() * (double)f8);
            this.p.k(this.p.q() * (double)0.8f);
            this.p.i(this.p.T() * (double)f8);
            if (!this.p.v$src$Z$g1lt9c()) {
                this.p.k(this.p.q() - 0.02);
            }
            if (this.p.r() && this.p.i$src$Z$avhpwd(this.p.t(), this.p.q() + (double)0.6f - this.p.N() + d, this.p.T())) {
                this.p.k((double)0.3f);
            }
        }
    }

    @Override
    public void N() {
        long l = a ^ 0x6B3F53A0F32EL;
        this.p.l$src$V$fw3v8a();
        ++this.B;
        if (this.U > 0) {
            --this.U;
        }
        boolean bl = this.W;
        boolean bl2 = this.b;
        float f = 0.8f;
        boolean bl3 = this.d >= 0.8f;
        this.F();
        boolean bl4 = false;
        boolean bl5 = false;
        float f2 = this.p.f$src$F$fst3ac();
        double d = this.p.z();
        double d2 = this.p.h();
        double d3 = this.p.u$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$kogbsu().getMinY();
        PlayerSimulationUtil.p(this.p, d - (double)f2 * 0.35, d3 + 0.5, d2 + (double)f2 * 0.35);
        PlayerSimulationUtil.p(this.p, d - (double)f2 * 0.35, d3 + 0.5, d2 - (double)f2 * 0.35);
        PlayerSimulationUtil.p(this.p, d + (double)f2 * 0.35, d3 + 0.5, d2 - (double)f2 * 0.35);
        PlayerSimulationUtil.p(this.p, d + (double)f2 * 0.35, d3 + 0.5, d2 + (double)f2 * 0.35);
        if ((float)this.p.Y$src$Lgg_vape_wrapper_impl_FoodStats_$fakh1z().getFoodLevel() > 6.0f || this.p.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().H()) {
            if (this.p.b$src$Z$fqlxe4() && !bl2 && !bl3 && this.d >= f && !this.p.B$src$Z$f90iek() && !this.p.i(PotionRegistry.K)) {
                if (this.U <= 0 && !this.f) {
                    this.U = (int)c;
                } else {
                    this.p.R(true);
                    this.B = 0;
                }
            }
            if (!this.p.B$src$Z$f90iek() && this.d >= f && !this.p.i(PotionRegistry.K) && this.f) {
                this.p.R(true);
                this.B = 0;
            }
            if (this.p.B$src$Z$f90iek() && (this.d < f || this.p.r())) {
                this.p.R(false);
                this.B = 0;
            }
            if (this.p.B$src$I$14s4bbr() > 0) {
                this.p.L(this.p.B$src$I$14s4bbr() - 1);
            }
            if (Math.abs(this.p.t()) < 0.003) {
                this.p.r(0.0);
            }
            if (Math.abs(this.p.q()) < 0.003) {
                this.p.k(0.0);
            }
            if (Math.abs(this.p.T()) < 0.003) {
                this.p.i(0.0);
            }
            this.x$src$V$6sjscx();
            if (this.p.e$src$Z$15bd4i1()) {
                if (this.p.h$src$Z$ftwoya()) {
                    this.p.k(this.p.q() + (double)0.04f);
                } else if (this.p.Q$src$Z$fh9faz()) {
                    this.p.k(this.p.q() + (double)0.04f);
                } else if (this.p.b$src$Z$fqlxe4() && this.p.B$src$I$14s4bbr() == 0) {
                    this.p.t$src$V$15jm1b0();
                    if (this.D.i(PotionRegistry.Z)) {
                        double d4 = (float)(this.D.b(PotionRegistry.Z).L() + 1) * 0.1f;
                        this.p.k(this.p.q() + d4);
                    }
                    this.p.L(10);
                }
            } else {
                this.p.L(0);
            }
            this.p.k$src$V$5315b7(this.p.N$src$F$14ypudi() * 0.98f);
            this.p.M(this.p.F() * 0.98f);
            this.x(this.p.N$src$F$14ypudi(), 0.0f, this.p.F());
            this.l$src$V$6ly98l();
            AttributeInstance attributeInstance = this.p.h(MonsterAttributesBridge.B());
            if (!this.P.I()) {
                attributeInstance.I(this.p.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().l());
            }
            float f3 = 0.02f;
            this.p.t(0.02f);
            if (this.p.B$src$Z$f90iek()) {
                this.p.t((float)((double)this.p.y$src$F$15mczw1() + 0.005999999865889549));
            }
            this.p.I((float)attributeInstance.W());
            return;
        }
        if (!this.p.b$src$Z$fqlxe4() || bl2 || bl3 || !(this.d >= f) || !this.p.B$src$Z$f90iek()) {
            // empty if block
        }
        if (this.p.B$src$Z$f90iek() || this.d >= f) {
            // empty if block
        }
        if (this.p.B$src$Z$f90iek()) {
            if (this.d < f || !this.p.r()) {
                // empty if block
            }
            this.p.R(false);
            this.B = 0;
        }
        if (this.p.B$src$I$14s4bbr() > 0) {
            this.p.L(this.p.B$src$I$14s4bbr() - 1);
        }
        if (Math.abs(this.p.t()) < 0.003) {
            this.p.r(0.0);
        }
        if (Math.abs(this.p.q()) < 0.003) {
            this.p.k(0.0);
        }
        if (Math.abs(this.p.T()) < 0.003) {
            this.p.i(0.0);
        }
        this.x$src$V$6sjscx();
        if (this.p.e$src$Z$15bd4i1()) {
            if (this.p.h$src$Z$ftwoya()) {
                this.p.k(this.p.q() + (double)0.04f);
            } else if (this.p.Q$src$Z$fh9faz()) {
                this.p.k(this.p.q() + (double)0.04f);
            } else if (this.p.b$src$Z$fqlxe4() && this.p.B$src$I$14s4bbr() == 0) {
                this.p.t$src$V$15jm1b0();
                if (this.D.i(PotionRegistry.Z)) {
                    double d5 = (float)(this.D.b(PotionRegistry.Z).L() + 1) * 0.1f;
                    this.p.k(this.p.q() + d5);
                }
                this.p.L(10);
            }
        } else {
            this.p.L(0);
        }
        this.p.k$src$V$5315b7(this.p.N$src$F$14ypudi() * 0.98f);
        this.p.M(this.p.F() * 0.98f);
        this.x(this.p.N$src$F$14ypudi(), 0.0f, this.p.F());
        this.l$src$V$6ly98l();
        AttributeInstance attributeInstance = this.p.h(MonsterAttributesBridge.B());
        if (!this.P.I()) {
            attributeInstance.I(this.p.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().l());
        }
        float f4 = 0.02f;
        this.p.t(0.02f);
        if (this.p.B$src$Z$f90iek()) {
            this.p.t((float)((double)this.p.y$src$F$15mczw1() + 0.005999999865889549));
        }
        this.p.I((float)attributeInstance.W());
    }

    @Override
    public void X(BlockPlacementGraph blockPlacementGraph) {
        this.B = blockPlacementGraph.J;
        this.U = blockPlacementGraph.S;
        this.d = blockPlacementGraph.p;
        this.Z = blockPlacementGraph.s;
        this.W = blockPlacementGraph.l;
        this.b = blockPlacementGraph.c;
        this.p.H(blockPlacementGraph.k);
        this.p.u(blockPlacementGraph.v);
        this.p.l(blockPlacementGraph.P);
        this.p.n(blockPlacementGraph.Z);
        this.p.w(blockPlacementGraph.j);
        this.p.A(blockPlacementGraph.x);
        this.p.r(blockPlacementGraph.I);
        this.p.k(blockPlacementGraph.H);
        this.p.i(blockPlacementGraph.t);
        this.p.H(blockPlacementGraph.Q);
        this.p.C(blockPlacementGraph.n);
        this.p.D(blockPlacementGraph.g);
        this.p.l(blockPlacementGraph.L);
        this.p.U(blockPlacementGraph.U);
        this.p.F(blockPlacementGraph.V);
        this.p.R(blockPlacementGraph.K);
        this.p.L(blockPlacementGraph.G);
        this.p.t(blockPlacementGraph.E);
        this.p.I(blockPlacementGraph.B);
        AttributeInstance attributeInstance = this.p.h(MonsterAttributesBridge.B());
        attributeInstance.J();
        for (Object e : blockPlacementGraph.f) {
            attributeInstance.applyModifier(new AttributeModifier(e));
        }
        this.g = blockPlacementGraph.M;
        this.M = blockPlacementGraph.D;
        this.r = blockPlacementGraph.R;
        this.R = blockPlacementGraph.Y;
        this.A = blockPlacementGraph.y;
        this.E = blockPlacementGraph.A;
        this.f = blockPlacementGraph.N;
    }

    public BlockInLegacyMovementController1122(EntityPlayer entityPlayer, EntityPlayerSP entityPlayerSP, EntityPlayer entityPlayer2, World world) {
        super(entityPlayer, entityPlayerSP, entityPlayer2, world);
    }

    public boolean E() {
        return !this.P.I();
    }

    public void F() {
        this.d = 0.0f;
        this.Z = 0.0f;
        if (this.g) {
            this.d += 1.0f;
        }
        if (this.M) {
            this.d -= 1.0f;
        }
        if (this.r) {
            this.Z += 1.0f;
        }
        if (this.R) {
            this.Z -= 1.0f;
        }
        this.W = this.E;
        this.b = this.A;
        if (this.b) {
            this.Z = (float)((double)this.Z * 0.3);
            this.d = (float)((double)this.d * 0.3);
        }
    }
}

