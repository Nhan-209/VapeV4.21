package gg.vape.module.blatant.blockin;

import gg.vape.mapping.MappedClasses;
import gg.vape.module.blatant.blockin.AbstractBlockInMovementController;
import gg.vape.module.blatant.blockin.BlockPlacementGraph;
import gg.vape.module.utility.MLGBlockWrapper;
import gg.vape.utils.MathUtil;
import gg.vape.wrapper.impl.AttributeInstance;
import gg.vape.wrapper.impl.AttributeModifier;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.BlockReaderBridge;
import gg.vape.wrapper.impl.BlockStateWorldBridge;
import gg.vape.wrapper.impl.Direction;
import gg.vape.wrapper.impl.DirectionAxis;
import gg.vape.wrapper.impl.DirectionVector;
import gg.vape.wrapper.impl.EnchantmentHelper;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.EntitySelectors;
import gg.vape.wrapper.impl.EnumFacing;
import gg.vape.wrapper.impl.MonsterAttributesBridge;
import gg.vape.wrapper.impl.MoverType;
import gg.vape.wrapper.impl.PotionRegistry;
import gg.vape.wrapper.impl.Vec3;
import gg.vape.wrapper.impl.World;
import java.util.List;

public class BlockInCollisionMovementController
extends AbstractBlockInMovementController {

    private void W() {
        this.p.h(this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi().addVector(0.0, -0.04f, 0.0));
    }

    @Override
    public void N() {
        this.S();
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

    private boolean z(BlockPos blockPos) {
        AxisAlignedBB axisAlignedBB = this.p.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl();
        AxisAlignedBB axisAlignedBB2 = AxisAlignedBB.create(blockPos.P(), axisAlignedBB.getMinY(), blockPos.d(), (double)blockPos.P() + 1.0, axisAlignedBB.getMaxY(), (double)blockPos.d() + 1.0).y(1.0E-7);
        return !this.P.z(this.p, axisAlignedBB2, this::lambda$shouldBlockPushPlayer$0);
    }

    private void O(Vec3 vec3) {
        double d;
        this.p.z(false);
        double d2 = this.p.z();
        double d3 = this.p.N();
        double d4 = this.p.h();
        if (this.p.X$src$Z$1id4hz7() && !this.p.f$src$Z$fst3rk()) {
            double d5;
            d = this.p.E$src$Lgg_vape_wrapper_impl_Vec3_$2tp8us().getY();
            double d6 = d5 = d < -0.2 ? 0.085 : 0.06;
            if (d <= 0.0 || this.p.e$src$Z$15bd4i1() || !this.P.getBlockState(BlockPos.D(this.p.z(), this.p.N() + 1.0 - 0.1, this.p.h())).j().x()) {
                Vec3 vec32 = this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi();
                this.p.h(vec32.addVector(0.0, (d - vec32.getY()) * d5, 0.0));
            }
        }
        if (this.p.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().isFlying() && !this.p.f$src$Z$fst3rk()) {
            d = this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi().getY();
            float f = this.p.y$src$F$15mczw1();
            this.p.t(this.p.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().m$src$F$1kykyr0() * (float)(this.p.B$src$Z$f90iek() ? 2 : 1));
            this.J(vec3);
            Vec3 vec33 = this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi();
            this.p.F(vec33.getX(), d * 0.6, vec33.getZ());
            this.p.t(f);
            this.p.U(0.0f);
            this.p.k(7, false);
        } else {
            this.J(vec3);
        }
    }

    public boolean u() {
        return this.p.P() || this.p.I$src$Z$fcv2k3();
    }


    public Vec3 M(Vec3 vec3, float f) {
        this.p.i(this.p.b(f), vec3);
        this.p.h(this.p.n(this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi()));
        this.p.B(MoverType.X(), this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi());
        Vec3 vec32 = this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi();
        if ((this.p.r() || this.p.e$src$Z$15bd4i1()) && this.p.S$src$Z$151gttj()) {
            vec32 = Vec3.create(vec32.getX(), 0.2, vec32.getZ());
        }
        return vec32;
    }

    public boolean r() {
        return true;
    }

    private void L() {
        List list = this.P.i(this.p, this.p.u$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$kogbsu(), EntitySelectors.Z(this.D));
        if (!list.isEmpty()) {
            for (Object e : list) {
                if (MappedClasses.z5.isInstance(e) || e == this.p.getObject() || e == this.O.getObject()) continue;
                this.p.z(new Entity(e));
            }
        }
    }

    private void Q() {
        this.p.i$src$Z$1imh02c();
        this.K();
        int n = 29999999;
        double d = MathUtil.clamp(this.p.z(), -2.9999999E7, 2.9999999E7);
        double d2 = MathUtil.clamp(this.p.h(), -2.9999999E7, 2.9999999E7);
        if (d != this.p.z() || d2 != this.p.h()) {
            this.p.B(d, this.p.N(), d2);
        }
        this.p.d$src$V$1ijq103();
    }

    public boolean O() {
        return this.d > 1.0E-5f;
    }

    private void S(double d, double d2) {
        BlockPos blockPos = BlockPos.D(d, this.p.N(), d2);
        if (this.z(blockPos)) {
            double d3 = d - (double)blockPos.P();
            double d4 = d2 - (double)blockPos.d();
            Direction direction = null;
            double d5 = Double.MAX_VALUE;
            EnumFacing[] enumFacingArray = new EnumFacing[]{EnumFacing.X(), EnumFacing.g$src$Lgg_vape_wrapper_impl_EnumFacing_$1ii8mzu(), EnumFacing.w(), EnumFacing.M()};
            for (EnumFacing enumFacing : enumFacingArray) {
                double d6;
                Direction direction2 = new Direction(enumFacing.getObject());
                double d7 = direction2.n().W(d3, 0.0, d4);
                double d8 = d6 = direction2.Q$src$Lgg_vape_wrapper_impl_DirectionVector_$l2h44r().equals(DirectionVector.m$src$Lgg_vape_wrapper_impl_DirectionVector_$1h73psc()) ? 1.0 - d7 : d7;
                if (!(d6 < d5) || this.z(blockPos.offset(direction2))) continue;
                d5 = d6;
                direction = direction2;
            }
            if (direction != null) {
                Vec3 vec3 = this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi();
                if (direction.n().equals(DirectionAxis.j())) {
                    this.p.F(0.1 * (double)direction.g(), vec3.getY(), vec3.getZ());
                } else {
                    this.p.F(vec3.getX(), vec3.getY(), 0.1 * (double)direction.o());
                }
            }
        }
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

    public void I() {
        this.T();
        this.p.t(0.02f);
        if (this.p.B$src$Z$f90iek()) {
            this.p.t((float)((double)this.p.y$src$F$15mczw1() + 0.005999999865889549));
        }
        this.p.I((float)this.p.z(MonsterAttributesBridge.B()));
    }


    public double R() {
        return (double)this.p.X() < 0.4 ? 0.0 : 0.4;
    }

    public void f() {
        boolean bl;
        boolean bl2;
        ++this.B;
        if (this.U > 0) {
            --this.U;
        }
        boolean bl3 = this.b;
        boolean bl4 = this.W;
        boolean bl5 = this.b;
        boolean bl6 = this.L$src$Z$jiewmo();
        this.a(this.u());
        boolean bl7 = false;
        double d = this.p.z();
        double d2 = this.p.h();
        double d3 = this.p.f$src$F$fst3ac();
        this.S(d - d3 * 0.35, d2 + d3 * 0.35);
        this.S(d - d3 * 0.35, d2 - d3 * 0.35);
        this.S(d + d3 * 0.35, d2 - d3 * 0.35);
        this.S(d + d3 * 0.35, d2 + d3 * 0.35);
        if (bl5) {
            this.U = 0;
        }
        boolean bl8 = bl2 = (float)this.p.Y$src$Lgg_vape_wrapper_impl_FoodStats_$fakh1z().getFoodLevel() > 6.0f || this.p.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().H();
        if ((this.p.b$src$Z$fqlxe4() || this.Z()) && !bl5 && !bl6 && this.L$src$Z$jiewmo() && !this.p.B$src$Z$f90iek() && bl2 && !this.p.i(PotionRegistry.K)) {
            if (this.U <= 0 && !this.f) {
                this.U = 7;
            } else {
                this.p.R(true);
                this.B = 0;
            }
        }
        if (!this.p.B$src$Z$f90iek() && (!this.p.h$src$Z$ftwoya() || this.Z()) && this.L$src$Z$jiewmo() && bl2 && !this.p.i(PotionRegistry.K) && this.f) {
            this.p.R(true);
            this.B = 0;
        }
        if (this.p.B$src$Z$f90iek()) {
            boolean bl9;
            bl = !this.O() || !bl2;
            boolean bl10 = bl9 = bl || this.p.r() || this.p.h$src$Z$ftwoya() && !this.Z();
            if (this.p.X$src$Z$1id4hz7()) {
                if (!this.p.b$src$Z$fqlxe4() && !this.b && bl || !this.p.h$src$Z$ftwoya()) {
                    this.p.R(false);
                    this.B = 0;
                }
            } else if (bl9) {
                this.p.R(false);
                this.B = 0;
            }
        }
        bl = false;
        if (this.p.h$src$Z$ftwoya() && this.b && !this.p.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().isFlying()) {
            this.W();
        }
        this.I();
    }

    private boolean L$src$Z$jiewmo() {
        return this.Z() ? this.O() : (double)this.d >= 0.8;
    }

    public void a(boolean bl) {
        float f = this.g == this.M ? 0.0f : (this.d = this.g ? 1.0f : -1.0f);
        this.Z = this.r == this.R ? 0.0f : (this.r ? 1.0f : -1.0f);
        this.W = this.E;
        this.b = this.A;
        if (bl) {
            this.Z = (float)((double)this.Z * 0.3);
            this.d = (float)((double)this.d * 0.3);
        }
    }

    public void S() {
        this.Q();
    }

    private void h() {
        this.l();
    }

    private void z() {
        this.p.k$src$V$5315b7(this.Z);
        this.p.M(this.d);
        this.p.b(this.W);
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
        Vec3 vec3 = Vec3.create(blockPlacementGraph.I, blockPlacementGraph.H, blockPlacementGraph.t);
        this.p.h(vec3);
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

    public void T() {
        if (this.p.B$src$I$14s4bbr() > 0) {
            this.p.L(this.p.B$src$I$14s4bbr() - 1);
        }
        if (!this.r()) {
            this.p.h(this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi().r(0.98));
        }
        Vec3 vec3 = this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi();
        double d = vec3.getX();
        double d2 = vec3.getY();
        double d3 = vec3.getZ();
        if (Math.abs(d) < 0.003) {
            d = 0.0;
        }
        if (Math.abs(d2) < 0.003) {
            d2 = 0.0;
        }
        if (Math.abs(d3) < 0.003) {
            d3 = 0.0;
        }
        this.p.F(d, d2, d3);
        this.z();
        if (this.p.e$src$Z$15bd4i1() && !this.p.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().isFlying()) {
            double d4 = this.p.Q$src$Z$fh9faz() ? this.p.Z(MLGBlockWrapper.f()) : this.p.Z(MLGBlockWrapper.t());
            boolean bl = this.p.h$src$Z$ftwoya() && d4 > 0.0;
            double d5 = this.R();
            if (!bl || this.p.b$src$Z$fqlxe4() && !(d4 > d5)) {
                if (!this.p.Q$src$Z$fh9faz() || this.p.b$src$Z$fqlxe4() && !(d4 > d5)) {
                    if ((this.p.b$src$Z$fqlxe4() || bl && d4 <= d5) && this.p.B$src$I$14s4bbr() == 0) {
                        this.p.t$src$V$15jm1b0();
                        this.p.L(10);
                    }
                } else {
                    this.p();
                }
            } else {
                this.p();
            }
        } else {
            this.p.L(0);
        }
        this.p.k$src$V$5315b7(this.p.N$src$F$14ypudi() * 0.98f);
        this.p.M(this.p.F() * 0.98f);
        Vec3 vec32 = Vec3.create(this.p.N$src$F$14ypudi(), 0.0, this.p.F());
        this.O(vec32);
        this.L();
    }

    private void l() {
        this.p.l(this.p.V());
        this.p.D(this.p.J());
        this.p.x$src$Z$g2peg2();
        this.p.n();
        this.p.V$src$V$1ic0wp1();
    }

    public void J(Vec3 vec3) {
        if (this.r() || this.p.H$src$Z$fcb9yq()) {
            boolean bl;
            double d = 0.08;
            boolean bl2 = bl = this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi().getY() <= 0.0;
            if (bl && this.p.i(PotionRegistry.k)) {
                d = 0.01;
                this.p.U(0.0f);
            }
            BlockStateWorldBridge blockStateWorldBridge = this.P.o(this.p.J$src$Lgg_vape_wrapper_impl_BlockPos_$kv8a0x());
            if (this.p.h$src$Z$ftwoya() && this.p.y$src$Z$1iv9pk4() && !this.p.l(blockStateWorldBridge)) {
                double d2 = this.p.N();
                float f = this.p.B$src$Z$f90iek() ? 0.9f : this.p.m$src$F$15frgrp();
                float f2 = 0.02f;
                float f3 = EnchantmentHelper.y(this.p);
                if (f3 > 3.0f) {
                    f3 = 3.0f;
                }
                if (!this.p.b$src$Z$fqlxe4()) {
                    f3 *= 0.5f;
                }
                if (f3 > 0.0f) {
                    f += (0.54600006f - f) * f3 / 3.0f;
                    f2 += (this.p.C$src$F$1i1kt1e() - f2) * f3 / 3.0f;
                }
                if (this.p.i(PotionRegistry.H)) {
                    f = 0.96f;
                }
                this.p.i(f2, vec3);
                this.p.B(MoverType.X(), this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi());
                Vec3 vec32 = this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi();
                if (this.p.r() && this.p.S$src$Z$151gttj()) {
                    vec32 = Vec3.create(vec32.getX(), 0.2, vec32.getZ());
                }
                this.p.h(vec32.G(f, 0.8f, f));
                Vec3 vec33 = this.p.G(d, bl, this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi());
                this.p.h(vec33);
                if (this.p.r() && this.p.i$src$Z$avhpwd(vec33.getX(), vec33.getY() + (double)0.6f - this.p.N() + d2, vec33.getZ())) {
                    this.p.F(vec33.getX(), 0.3f, vec33.getZ());
                }
            } else if (this.p.Q$src$Z$fh9faz() && this.p.y$src$Z$1iv9pk4() && !this.p.l(blockStateWorldBridge)) {
                Vec3 vec34;
                double d3 = this.p.N();
                this.p.i(0.02f, vec3);
                this.p.B(MoverType.X(), this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi());
                if (this.p.Z(MLGBlockWrapper.f()) <= this.R()) {
                    this.p.h(this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi().G(0.5, 0.8f, 0.5));
                    vec34 = this.p.G(d, bl, this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi());
                    this.p.h(vec34);
                } else {
                    this.p.h(this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi().r(0.5));
                }
                if (!this.p.v$src$Z$g1lt9c()) {
                    this.p.h(this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi().addVector(0.0, -d / 4.0, 0.0));
                }
                vec34 = this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi();
                if (this.p.r() && this.p.i$src$Z$avhpwd(vec34.getX(), vec34.getY() + (double)0.6f - this.p.N() + d3, vec34.getZ())) {
                    this.p.F(vec34.getX(), 0.3f, vec34.getZ());
                }
            } else if (!this.p.k$src$Z$15enw27()) {
                BlockPos blockPos = this.p.x$src$Lgg_vape_wrapper_impl_BlockPos_$1izbb73();
                float f = this.P.getBlockState(blockPos).getBlock().c();
                float f4 = this.p.b$src$Z$fqlxe4() ? f * 0.91f : 0.91f;
                Vec3 vec35 = this.M(vec3, f);
                double d4 = vec35.getY();
                if (this.p.i(PotionRegistry.h)) {
                    d4 += (0.05 * (double)(this.p.b(PotionRegistry.h).L() + 1) - vec35.getY()) * 0.2;
                    this.p.U(0.0f);
                } else if (!this.P.j$src$Z$11aji0a(blockPos)) {
                    d4 = this.p.N() > 0.0 ? -0.1 : 0.0;
                } else if (!this.p.v$src$Z$g1lt9c()) {
                    d4 -= d;
                }
                this.p.F(vec35.getX() * (double)f4, d4 * (double)0.98f, vec35.getZ() * (double)f4);
            }
        }
    }

    private boolean lambda$shouldBlockPushPlayer$0(Object object, Object object2) {
        BlockReaderBridge blockReaderBridge = new BlockReaderBridge(object);
        return blockReaderBridge.e(this.P.getObject(), object2);
    }

    protected void p() {
        this.p.h(this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi().addVector(0.0, 0.04f, 0.0));
    }

    public BlockInCollisionMovementController(EntityPlayer entityPlayer, EntityPlayerSP entityPlayerSP, EntityPlayer entityPlayer2, World world) {
        super(entityPlayer, entityPlayerSP, entityPlayer2, world);
    }

    private void K() {
        this.h();
        this.f();
    }

    public boolean Z() {
        return this.p.N$src$Z$1i7mk1l();
    }
}
