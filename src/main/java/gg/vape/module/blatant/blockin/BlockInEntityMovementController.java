package gg.vape.module.blatant.blockin;

import gg.vape.mapping.MappedClasses;
import gg.vape.module.blatant.blockin.AbstractBlockInMovementController;
import gg.vape.module.blatant.blockin.BlockPlacementGraph;
import gg.vape.module.utility.MLGBlockWrapper;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import gg.vape.utils.math.NumericMathUtil;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.AttributeInstance;
import gg.vape.wrapper.impl.AttributeModifier;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.Block;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.BlockRayTraceResult;
import gg.vape.wrapper.impl.BlockStateWorldBridge;
import gg.vape.wrapper.impl.Blocks;
import gg.vape.wrapper.impl.ChestType;
import gg.vape.wrapper.impl.Direction;
import gg.vape.wrapper.impl.DirectionAxis;
import gg.vape.wrapper.impl.DirectionVector;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.EntitySelectors;
import gg.vape.wrapper.impl.EnumFacing;
import gg.vape.wrapper.impl.ITooltipFlag;
import gg.vape.wrapper.impl.ModelPlayer;
import gg.vape.wrapper.impl.MonsterAttributesBridge;
import gg.vape.wrapper.impl.MoverType;
import gg.vape.wrapper.impl.PotionEffect;
import gg.vape.wrapper.impl.PotionRegistry;
import gg.vape.wrapper.impl.RayTraceContextFactory;
import gg.vape.wrapper.impl.RayTraceResult_type;
import gg.vape.wrapper.impl.Vec3;
import gg.vape.wrapper.impl.World;
import java.lang.invoke.MethodHandles;
import java.util.List;

public class BlockInEntityMovementController
extends AbstractBlockInMovementController {
    private boolean h;
    private static final long a;

    private void l$src$V$muqk5b() {
        this.W = this.E;
        this.b = this.A;
        this.d = BlockInEntityMovementController.M(this.g, this.M);
        this.Z = BlockInEntityMovementController.M(this.r, this.R);
    }

    static {
        long l = a = ZkmLongKeyState.a(6398869136590396384L, 6290548654227756087L, MethodHandles.lookup().lookupClass()).a(108831250848264L);
    }

    private void J() {
        if (this.D.s$src$Z$1iryxzy()) {
            this.R$src$V$mgfwpx();
        }
    }

    protected boolean e(Vec3 vec3) {
        float f = this.p.J() * ((float)Math.PI / 180);
        double d = NumericMathUtil.X(f);
        double d2 = NumericMathUtil.y(f);
        double d3 = (double)this.p.N$src$F$14ypudi() * d2 - (double)this.p.F() * d;
        double d4 = (double)this.p.F() * d2 + (double)this.p.N$src$F$14ypudi() * d;
        double d5 = NumericMathUtil.X(d3) + NumericMathUtil.X(d4);
        double d6 = NumericMathUtil.X(vec3.getX()) + NumericMathUtil.X(vec3.getZ());
        if (!(d5 < (double)1.0E-5f) && !(d6 < (double)1.0E-5f)) {
            double d7 = d3 * vec3.getX() + d4 * vec3.getZ();
            double d8 = Math.acos(d7 / Math.sqrt(d5 * d6));
            return d8 < 0.13962633907794952;
        }
        return false;
    }

    public boolean A() {
        return this.p.N$src$Z$1i7mk1l();
    }

    private void I() {
        this.p.k$src$V$5315b7(this.Z);
        this.p.M(this.d);
        this.p.b(this.E);
    }

    private void s() {
        this.p.h(this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi().addVector(0.0, -0.04f, 0.0));
    }

    public BlockInEntityMovementController(EntityPlayer entityPlayer, EntityPlayerSP entityPlayerSP, EntityPlayer entityPlayer2, World world) {
        super(entityPlayer, entityPlayerSP, entityPlayer2, world);
    }

    @Override
    public void N() {
        this.J();
    }

    private void x() {
        Vec3 vec3 = this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi();
        this.p.h(this.C(vec3));
        this.J(MoverType.X(), this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi());
    }

    private void L() {
        boolean bl;
        boolean bl2;
        long l = a ^ 0x4B2168C54DA6L;
        if (this.U > 0) {
            --this.U;
        }
        boolean bl3 = this.W;
        boolean bl4 = this.b;
        if (bl4) {
            boolean bl5;
            boolean bl6 = this.U();
            ModelPlayer modelPlayer = this.p.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86();
            this.l$src$V$muqk5b();
            if (this.V()) {
                this.p.R(false);
            }
            if (this.L$src$Z$md5597()) {
                float f = (float)this.p.o(MonsterAttributesBridge.D());
                this.Z *= f;
                this.d *= f;
            }
            if (!this.p.d()) {
                double d = this.p.z();
                double d2 = this.p.h();
                float f = this.p.f$src$F$fst3ac();
                this.D(d - (double)f * 0.35, d2 + (double)f * 0.35);
                this.D(d - (double)f * 0.35, d2 - (double)f * 0.35);
                this.D(d + (double)f * 0.35, d2 - (double)f * 0.35);
                this.D(d + (double)f * 0.35, d2 + (double)f * 0.35);
            }
            this.U = 0;
            boolean bl7 = this.O();
            boolean bl8 = this.p.f$src$Z$fst3rk() ? this.p.S$src$Lgg_vape_wrapper_impl_Entity_$dgzs12().b$src$Z$fqlxe4() : this.p.b$src$Z$fqlxe4();
            boolean bl9 = false;
            if (bl8 || this.p.N$src$Z$1i7mk1l()) {
                // empty if block
            }
            if ((!this.p.h$src$Z$ftwoya() || this.p.N$src$Z$1i7mk1l()) && bl7 && this.f) {
                this.p.R(true);
            }
            if (this.p.B$src$Z$f90iek()) {
                boolean bl10;
                bl5 = !this.i() || !this.E();
                boolean bl11 = bl10 = bl5 || this.p.r() && !this.p.m$src$Z$fwnnx3() || this.p.h$src$Z$ftwoya() && !this.p.N$src$Z$1i7mk1l();
                if (this.p.X$src$Z$1id4hz7()) {
                    if (!this.p.b$src$Z$fqlxe4() && !this.A && bl5 || !this.p.h$src$Z$ftwoya()) {
                        this.p.R(false);
                    }
                } else if (bl10) {
                    this.p.R(false);
                }
            }
            bl5 = false;
            if (this.p.h$src$Z$ftwoya() && this.A && this.p.y$src$Z$1iv9pk4()) {
                this.s();
            }
            this.C();
            return;
        }
        boolean bl12 = this.U();
        ModelPlayer modelPlayer = this.p.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86();
        this.l$src$V$muqk5b();
        if (this.V()) {
            this.p.R(false);
        }
        if (this.L$src$Z$md5597()) {
            float f = (float)this.p.o(MonsterAttributesBridge.D());
            this.Z *= f;
            this.d *= f;
        }
        if (!this.p.d()) {
            double d = this.p.z();
            double d3 = this.p.h();
            float f = this.p.f$src$F$fst3ac();
            this.D(d - (double)f * 0.35, d3 + (double)f * 0.35);
            this.D(d - (double)f * 0.35, d3 - (double)f * 0.35);
            this.D(d + (double)f * 0.35, d3 - (double)f * 0.35);
            this.D(d + (double)f * 0.35, d3 + (double)f * 0.35);
        }
        boolean bl13 = this.O();
        boolean bl14 = this.p.f$src$Z$fst3rk() ? this.p.S$src$Lgg_vape_wrapper_impl_Entity_$dgzs12().b$src$Z$fqlxe4() : this.p.b$src$Z$fqlxe4();
        boolean bl15 = bl2 = !bl12;
        if ((bl14 || this.p.N$src$Z$1i7mk1l()) && bl2 && bl13) {
            if (this.U <= 0 && !this.f) {
                this.U = 7;
            } else {
                this.p.R(true);
            }
        }
        if ((!this.p.h$src$Z$ftwoya() || this.p.N$src$Z$1i7mk1l()) && bl13 && this.f) {
            this.p.R(true);
        }
        if (this.p.B$src$Z$f90iek()) {
            boolean bl16;
            bl = !this.i() || !this.E();
            boolean bl17 = bl16 = bl || this.p.r() && !this.p.m$src$Z$fwnnx3() || this.p.h$src$Z$ftwoya() && !this.p.N$src$Z$1i7mk1l();
            if (this.p.X$src$Z$1id4hz7()) {
                if (!this.p.b$src$Z$fqlxe4() && !this.A && bl || !this.p.h$src$Z$ftwoya()) {
                    this.p.R(false);
                }
            } else if (bl16) {
                this.p.R(false);
            }
        }
        bl = false;
        if (this.p.h$src$Z$ftwoya() && this.A && this.p.y$src$Z$1iv9pk4()) {
            this.s();
        }
        this.C();
    }

    private void D(double d, double d2) {
        BlockPos blockPos = BlockPos.D(d, this.p.N(), d2);
        if (this.K(blockPos)) {
            double d3 = d - (double)blockPos.P();
            double d4 = d2 - (double)blockPos.d();
            Direction direction = null;
            double d5 = Double.MAX_VALUE;
            EnumFacing[] enumFacingArray = new EnumFacing[]{Direction.X(), Direction.g$src$Lgg_vape_wrapper_impl_EnumFacing_$1ii8mzu(), Direction.w(), Direction.M()};
            for (EnumFacing enumFacing : enumFacingArray) {
                double d6;
                Direction direction2 = new Direction(enumFacing.getObject());
                double d7 = direction2.n().W(d3, 0.0, d4);
                double d8 = d6 = direction2.Q$src$Lgg_vape_wrapper_impl_DirectionVector_$l2h44r().equals(DirectionVector.m$src$Lgg_vape_wrapper_impl_DirectionVector_$1h73psc()) ? 1.0 - d7 : d7;
                if (!(d6 < d5) || this.K(blockPos.offset(direction2))) continue;
                d5 = d6;
                direction = direction2;
            }
            if (direction != null) {
                Vec3 vec3 = this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi();
                if (direction.n().equals(DirectionAxis.j())) {
                    this.p.F(0.1 * (double)direction.S(), vec3.getY(), vec3.getZ());
                } else {
                    this.p.F(vec3.getX(), vec3.getY(), 0.1 * (double)direction.F());
                }
            }
        }
    }

    private boolean V() {
        return this.p.k$src$Z$15enw27() || this.h() || this.L$src$Z$md5597() || this.p.f$src$Z$fst3rk();
    }

    @Override
    public void X(BlockPlacementGraph blockPlacementGraph) {
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
        AttributeInstance attributeInstance = this.p.t(MonsterAttributesBridge.U());
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

    private void y() {
        this.p.h(this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi().addVector(0.0, 0.04f, 0.0));
    }

    private void R$src$V$mgfwpx() {
        this.Q();
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

    private boolean R$src$Z$mgfwtd() {
        return this.A() ? this.e() : (double)this.d >= 0.8;
    }

    public boolean j() {
        return this.p.I$src$Z$fcv2k3() && !this.p.h$src$Z$ftwoya();
    }

    private Vec3 O(Vec3 vec3, float f) {
        this.p.i(this.p.b(f), vec3);
        this.p.h(this.p.n(this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi()));
        this.J(MoverType.X(), this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi());
        Vec3 vec32 = this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi();
        if ((this.p.r() || this.p.e$src$Z$15bd4i1()) && (this.p.S$src$Z$151gttj() || this.p.y().w(Blocks.h()) && Block.r(this.p))) {
            vec32 = Vec3.create(vec32.getX(), 0.2, vec32.getZ());
        }
        return vec32;
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private void u() {
        this.p.U(null);
        this.p.p(this.p.E$src$Z$fanw6n());
        this.p.g(false);
        this.p.x$src$Z$g2peg2();
        this.p.n();
        this.p.V$src$V$1ic0wp1();
        if (this.p.Q$src$Z$fh9faz()) {
            this.p.U(this.p.M$src$F$ff28gb() * 0.5f);
        }
        this.p.A$src$V$f8gppr();
    }

    private float R() {
        return (float)this.p.o(MonsterAttributesBridge.U());
    }

    public boolean m() {
        return this.p.P() || this.p.I$src$Z$fcv2k3();
    }

    public boolean e() {
        return this.d > 1.0E-5f;
    }

    private void D(Vec3 vec3) {
        boolean bl = this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi().getY() <= 0.0;
        double d = this.p.N();
        double d2 = this.p.J$src$D$14winyc();
        if (this.p.h$src$Z$ftwoya()) {
            float f = this.p.B$src$Z$f90iek() ? 0.9f : this.p.m$src$F$15frgrp();
            float f2 = 0.02f;
            float f3 = (float)this.p.o(MonsterAttributesBridge.g());
            if (!this.p.b$src$Z$fqlxe4()) {
                f3 *= 0.5f;
            }
            if (f3 > 0.0f) {
                f += (0.54600006f - f) * f3;
                f2 += (this.R() - f2) * f3;
            }
            if (this.p.i(PotionRegistry.H)) {
                f = 0.96f;
            }
            this.p.i(f2, vec3);
            this.J(MoverType.X(), this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi());
            Vec3 vec32 = this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi();
            if (this.p.r() && this.p.S$src$Z$151gttj()) {
                vec32 = Vec3.create(vec32.getX(), 0.2, vec32.getZ());
            }
            vec32 = vec32.G(f, 0.8f, f);
            this.p.h(this.p.G(d2, bl, vec32));
        } else {
            this.p.i(0.02f, vec3);
            this.J(MoverType.X(), this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi());
            if (this.p.Z(MLGBlockWrapper.f()) <= this.F()) {
                this.p.h(this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi().G(0.5, 0.8f, 0.5));
                Vec3 vec33 = this.p.G(d2, bl, this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi());
                this.p.h(vec33);
            } else {
                this.p.h(this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi().r(0.5));
            }
            if (d2 != 0.0) {
                this.p.h(this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi().addVector(0.0, -d2 / 4.0, 0.0));
            }
        }
        Vec3 vec34 = this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi();
        if (this.p.r() && this.p.i$src$Z$avhpwd(vec34.getX(), vec34.getY() + (double)0.6f - this.p.N() + d, vec34.getZ())) {
            this.p.F(vec34.getX(), 0.3f, vec34.getZ());
        }
    }

    private boolean L$src$Z$md5597() {
        return this.p.P() || this.j();
    }

    private void Q() {
        long l = a ^ 0x62A362164E6BL;
        this.p.z(false);
        if (this.p.O$src$Z$fg5u49() || this.p.f$src$Z$fst3rk()) {
            this.p.U(false);
        }
        this.p.i$src$Z$1imh02c();
        this.o();
        int n = 29999999;
        double d = NumericMathUtil.W(this.p.z(), -2.9999999E7, 2.9999999E7);
        double d2 = NumericMathUtil.W(this.p.h(), -2.9999999E7, 2.9999999E7);
        if (d != this.p.z() || d2 != this.p.h()) {
            this.p.B(d, this.p.N(), d2);
        }
        this.p.d$src$V$1ijq103();
    }

    private Vec3 C(Vec3 vec3) {
        double d;
        double d2;
        Vec3 vec32 = this.p.E$src$Lgg_vape_wrapper_impl_Vec3_$2tp8us();
        float f = this.p.V() * ((float)Math.PI / 180);
        double d3 = vec32.Q();
        double d4 = vec3.Q();
        double d5 = this.p.J$src$D$14winyc();
        if ((vec3 = vec3.addVector(0.0, d5 * (-1.0 + (d2 = NumericMathUtil.X(Math.cos(f))) * 0.75), 0.0)).getY() < 0.0 && d3 > 0.0) {
            d = vec3.getY() * -0.1 * d2;
            vec3 = vec3.addVector(vec32.getX() * d / d3, d, vec32.getZ() * d / d3);
        }
        if (f < 0.0f && d3 > 0.0) {
            d = d4 * (double)(-NumericMathUtil.X(f)) * 0.04;
            vec3 = vec3.addVector(-vec32.getX() * d / d3, d * 3.2, -vec32.getZ() * d / d3);
        }
        if (d3 > 0.0) {
            vec3 = vec3.addVector((vec32.getX() / d3 * d4 - vec3.getX()) * 0.1, 0.0, (vec32.getZ() / d3 * d4 - vec3.getZ()) * 0.1);
        }
        return vec3.G(0.99f, 0.98f, 0.99f);
    }

    private boolean i() {
        return this.d > 1.0E-5f;
    }

    private boolean K(BlockPos blockPos) {
        AxisAlignedBB axisAlignedBB = this.p.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl();
        AxisAlignedBB axisAlignedBB2 = AxisAlignedBB.create(blockPos.P(), axisAlignedBB.getMinY(), blockPos.d(), (double)blockPos.P() + 1.0, axisAlignedBB.getMaxY(), (double)blockPos.d() + 1.0).v(1.0E-7);
        return this.P.M(this.p, axisAlignedBB2);
    }

    private static float M(boolean bl, boolean bl2) {
        if (bl == bl2) {
            return 0.0f;
        }
        return bl ? 1.0f : -1.0f;
    }

    private void G() {
        if (this.p.B$src$I$14s4bbr() > 0) {
            this.p.L(this.p.B$src$I$14s4bbr() - 1);
        }
        Vec3 vec3 = this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi();
        double d = vec3.getX();
        double d2 = vec3.getY();
        double d3 = vec3.getZ();
        if (Math.abs(vec3.getX()) < 0.003) {
            d = 0.0;
        }
        if (Math.abs(vec3.getY()) < 0.003) {
            d2 = 0.0;
        }
        if (Math.abs(vec3.getZ()) < 0.003) {
            d3 = 0.0;
        }
        this.p.F(d, d2, d3);
        if (this.p.p$src$Z$15hev10()) {
            this.p.b(false);
            this.p.k$src$V$5315b7(0.0f);
            this.p.M(0.0f);
        } else {
            this.I();
        }
        if (this.p.e$src$Z$15bd4i1() && this.p.y$src$Z$1iv9pk4()) {
            double d4 = this.p.Q$src$Z$fh9faz() ? this.p.Z(MLGBlockWrapper.f()) : this.p.Z(MLGBlockWrapper.t());
            boolean bl = this.p.h$src$Z$ftwoya() && d4 > 0.0;
            double d5 = this.F();
            if (!bl || this.p.b$src$Z$fqlxe4() && !(d4 > d5)) {
                if (!this.p.Q$src$Z$fh9faz() || this.p.b$src$Z$fqlxe4() && !(d4 > d5)) {
                    if ((this.p.b$src$Z$fqlxe4() || bl && d4 <= d5) && this.p.B$src$I$14s4bbr() == 0) {
                        this.p.t$src$V$15jm1b0();
                        this.p.L(10);
                    }
                } else {
                    this.y();
                }
            } else {
                this.y();
            }
        } else {
            this.p.L(0);
        }
        this.p.k$src$V$5315b7(this.p.N$src$F$14ypudi() * 0.98f);
        this.p.M(this.p.F() * 0.98f);
        AxisAlignedBB axisAlignedBB = this.p.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl();
        Vec3 vec32 = Vec3.create(this.p.N$src$F$14ypudi(), 0.0, this.p.F());
        this.B(vec32);
        if (!this.P.I()) {
            // empty if block
        }
        this.p.B$src$V$14s4bmy();
        this.p.R$src$V$150x14q();
        this.t();
    }

    private boolean U() {
        double d = 0.8;
        return this.p.N$src$Z$1i7mk1l() ? this.i() : (double)this.d >= 0.8;
    }

    private void C() {
        this.G();
        this.p.t((float)this.p.o(MonsterAttributesBridge.U()));
    }

    private void v(Vec3 vec3) {
        BlockPos blockPos = this.p.x$src$Lgg_vape_wrapper_impl_BlockPos_$1izbb73();
        float f = this.p.b$src$Z$fqlxe4() ? this.P.getBlockState(blockPos).getBlock().c() : 1.0f;
        float f2 = f * 0.91f;
        Vec3 vec32 = this.O(vec3, f);
        double d = vec32.getY();
        if (this.p.i(PotionRegistry.h)) {
            PotionEffect potionEffect = this.p.b(PotionRegistry.h);
            d += (0.05 * (double)(potionEffect.L() + 1) - vec32.getY()) * 0.2;
        } else {
            d -= this.p.J$src$D$14winyc();
        }
        if (this.p.V$src$Z$15347lm()) {
            this.p.F(vec32.getX(), d, vec32.getZ());
        } else {
            float f3 = 0.98f;
            this.p.F(vec32.getX() * (double)f2, d * (double)f3, vec32.getZ() * (double)f2);
        }
    }

    private boolean E() {
        return this.p.f$src$Z$fst3rk() || (float)this.p.Y$src$Lgg_vape_wrapper_impl_FoodStats_$fakh1z().getFoodLevel() > 6.0f || this.p.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().H();
    }

    @Override
    public void B() {
        this.U = this.D.L$src$I$1tmeeo5();
        AttributeInstance attributeInstance = this.O.t(MonsterAttributesBridge.U());
        AttributeInstance attributeInstance2 = this.p.t(MonsterAttributesBridge.U());
        attributeInstance2.J();
        for (Object e : attributeInstance.I()) {
            attributeInstance2.applyModifier(new AttributeModifier(e));
        }
    }

    public void J(MoverType moverType, Vec3 vec3) {
        block14: {
            Wrapper wrapper;
            boolean bl;
            EntityPlayer entityPlayer;
            boolean bl2;
            boolean bl3;
            Vec3 vec32;
            block18: {
                EntityPlayer entityPlayer2;
                block16: {
                    block17: {
                        EntityPlayer entityPlayer3;
                        block15: {
                            double d;
                            block13: {
                                if (!this.p.d()) break block13;
                                this.p.B(this.p.z() + vec3.getX(), this.p.N() + vec3.getY(), this.p.h() + vec3.getZ());
                                break block14;
                            }
                            if (moverType.equals(MoverType.E()) && (vec3 = this.p.y(vec3)).equals(Vec3.H())) {
                                return;
                            }
                            if (this.p.G$src$Lgg_vape_wrapper_impl_Vec3_$efeys6().j() > 1.0E-7) {
                                vec3 = vec3.N(this.p.G$src$Lgg_vape_wrapper_impl_Vec3_$efeys6());
                                this.p.Z(Vec3.H());
                                this.p.h(Vec3.H());
                            }
                            if ((d = (vec32 = this.p.K(vec3 = this.p.m(vec3, moverType))).j()) > 1.0E-7 || vec3.j() - d < 1.0E-7) {
                                BlockRayTraceResult blockRayTraceResult;
                                if (this.p.M$src$F$ff28gb() != 0.0f && d >= 1.0 && !(blockRayTraceResult = this.P.r(RayTraceContextFactory.v(this.p.I$src$Lgg_vape_wrapper_impl_Vec3_$q14opk(), this.p.I$src$Lgg_vape_wrapper_impl_Vec3_$q14opk().add(vec32), ChestType.e(), ITooltipFlag.n(), this.p))).getTypeOfHit().equals(RayTraceResult_type.miss())) {
                                    this.p.U(0.0f);
                                }
                                this.p.B(this.p.z() + vec32.getX(), this.p.N() + vec32.getY(), this.p.h() + vec32.getZ());
                            }
                            bl3 = !NumericMathUtil.F(vec3.getX(), vec32.getX());
                            bl2 = !NumericMathUtil.F(vec3.getZ(), vec32.getZ());
                            entityPlayer3 = this.p;
                            if (bl3) break block15;
                            entityPlayer2 = entityPlayer3;
                            if (!bl2) break block16;
                            entityPlayer = entityPlayer2;
                            break block17;
                        }
                        entityPlayer = entityPlayer3;
                    }
                    bl = true;
                    break block18;
                }
                entityPlayer = entityPlayer2;
                bl = false;
            }
            entityPlayer.T(bl);
            if (Math.abs(vec3.getY()) > 0.0) {
                this.p.t(vec3.getY() != vec32.getY());
                this.p.h(this.p.u$src$Z$g120nz() && vec3.getY() < 0.0);
                this.p.X(this.p.q$src$Z$fyuuaj(), this.p.r(), vec32);
            }
            if (this.p.r()) {
                this.p.K(this.e(vec32));
            } else {
                this.p.K(false);
            }
            if (this.p.r()) {
                double d;
                double d2;
                double d3;
                EntityPlayer entityPlayer4;
                double d4;
                EntityPlayer entityPlayer5;
                wrapper = this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi();
                EntityPlayer entityPlayer6 = this.p;
                if (bl3) {
                    entityPlayer5 = entityPlayer6;
                    d4 = 0.0;
                } else {
                    entityPlayer5 = entityPlayer6;
                    d4 = ((Vec3)wrapper).getX();
                }
                double d5 = ((Vec3)wrapper).getY();
                double d6 = d4;
                EntityPlayer entityPlayer7 = entityPlayer5;
                if (bl2) {
                    entityPlayer4 = entityPlayer7;
                    d3 = d6;
                    d2 = d5;
                    d = 0.0;
                } else {
                    entityPlayer4 = entityPlayer7;
                    d3 = d6;
                    d2 = d5;
                    d = ((Vec3)wrapper).getZ();
                }
                entityPlayer4.F(d3, d2, d);
            }
            wrapper = this.p.C$src$Lgg_vape_wrapper_impl_BlockPos_$y7f4vu();
            Block block = this.P.getBlockState((BlockPos)wrapper).getBlock();
            if (vec3.getY() != vec32.getY()) {
                block.r(this.P, this.p);
            }
            float f = this.p.i$src$F$1imgzl4();
            this.p.h(this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi().G(f, 1.0, f));
        }
    }

    public double F() {
        return (double)this.p.X() < 0.4 ? 0.0 : 0.4;
    }

    public void f(Vec3 vec3) {
        BlockStateWorldBridge blockStateWorldBridge = this.P.o(this.p.J$src$Lgg_vape_wrapper_impl_BlockPos_$kv8a0x());
        if ((this.p.h$src$Z$ftwoya() || this.p.Q$src$Z$fh9faz()) && this.p.y$src$Z$1iv9pk4() && !this.p.l(blockStateWorldBridge)) {
            this.D(vec3);
        } else if (this.p.k$src$Z$15enw27()) {
            this.x();
        } else {
            this.v(vec3);
        }
    }

    private void o() {
        float f;
        this.u();
        if (!this.p.M$src$Z$ff28xj()) {
            this.L();
        }
        if (this.p.k$src$Z$15enw27()) {
            // empty if block
        }
        if (this.p.w$src$Z$1iu64de()) {
            this.p.C(0.0f);
        }
        if ((f = this.p.e()) != this.p.K()) {
            this.p.j(f);
            this.p.O$src$V$fg5u0t();
        }
    }

    private boolean O() {
        return !this.p.B$src$Z$f90iek() && this.U() && this.E() && !this.h() && !this.p.f$src$Z$fst3rk() && !this.p.k$src$Z$15enw27() && (!this.L$src$Z$md5597() || this.p.N$src$Z$1i7mk1l());
    }

    private void t() {
        List list = this.P.i(this.p, this.p.u$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$kogbsu(), EntitySelectors.Z(this.D));
        if (!list.isEmpty()) {
            for (Object e : list) {
                if (MappedClasses.z5.isInstance(e) || e == this.p.getObject() || e == this.O.getObject()) continue;
                this.p.z(new Entity(e));
            }
        }
    }

    private boolean h() {
        return this.p.i(PotionRegistry.K);
    }

    private void B(Vec3 vec3) {
        if (this.p.f$src$Z$fst3rk()) {
            this.f(vec3);
        } else {
            double d;
            if (this.p.X$src$Z$1id4hz7()) {
                double d2;
                d = this.p.E$src$Lgg_vape_wrapper_impl_Vec3_$2tp8us().getY();
                double d3 = d2 = d < -0.2 ? 0.085 : 0.06;
                if (d <= 0.0 || this.p.e$src$Z$15bd4i1() || !this.P.o(BlockPos.D(this.p.z(), this.p.N() + 1.0 - 0.1, this.p.h())).x()) {
                    Vec3 vec32 = this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi();
                    this.p.h(vec32.addVector(0.0, (d - vec32.getY()) * d2, 0.0));
                }
            }
            if (this.p.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().isFlying()) {
                d = this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi().getY();
                this.f(vec3);
                this.p.h(this.p.C$src$Lgg_vape_wrapper_impl_Vec3_$1q93kwi().i(DirectionAxis.T(), d * 0.6));
            } else {
                this.f(vec3);
            }
        }
    }
}
