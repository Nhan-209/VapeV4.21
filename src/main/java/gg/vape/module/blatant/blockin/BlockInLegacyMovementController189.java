package gg.vape.module.blatant.blockin;

import gg.vape.module.blatant.blockin.AbstractBlockInMovementController;
import gg.vape.module.blatant.blockin.BlockPlacementGraph;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import gg.vape.utils.MathUtil;
import gg.vape.utils.PlayerSimulationUtil;
import gg.vape.wrapper.impl.AttributeInstance;
import gg.vape.wrapper.impl.AttributeModifier;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.MonsterAttributesBridge;
import gg.vape.wrapper.impl.PotionRegistry;
import gg.vape.wrapper.impl.World;
import java.lang.invoke.MethodHandles;

public class BlockInLegacyMovementController189
extends AbstractBlockInMovementController {
    private static final long a;

    static {
        long l = a = ZkmLongKeyState.a(4958802174440982186L, -5041486704205248601L, MethodHandles.lookup().lookupClass()).a(260995586159542L);
    }

    @Override
    public void B() {
        this.U = this.D.L$src$I$1tmeeo5();
        this.B = this.D.z$src$I$1uboxyr();
        this.d = this.D.movementInput().D();
        this.Z = this.D.movementInput().T();
        AttributeInstance attributeInstance = this.O.h(MonsterAttributesBridge.B());
        AttributeInstance attributeInstance2 = this.p.h(MonsterAttributesBridge.B());
        attributeInstance2.J();
        for (Object e : attributeInstance.I()) {
            attributeInstance2.applyModifier(new AttributeModifier(e));
        }
    }

    @Override
    public void N() {
        boolean bl;
        boolean bl2;
        long l = a ^ 0x27CFD2A9F411L;
        this.p.l$src$V$fw3v8a();
        if (this.B > 0) {
            --this.B;
            if (this.B == 0) {
                this.p.R(false);
            }
        }
        if (this.U > 0) {
            --this.U;
        }
        boolean bl3 = this.W;
        boolean bl4 = this.b;
        float f = 0.8f;
        boolean bl5 = this.d >= f;
        this.q();
        boolean bl6 = bl2 = ForgeVersion.MC_1_8_9.v() && this.p.l$src$Z$1io4duf();
        if (bl2 && !this.p.f$src$Z$fst3rk()) {
            this.Z *= 0.2f;
            this.d *= 0.2f;
            this.U = 0;
        }
        AxisAlignedBB axisAlignedBB = this.p.u$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$kogbsu();
        PlayerSimulationUtil.p(this.p, this.p.z() - (double)this.p.f$src$F$fst3ac() * 0.35, axisAlignedBB.getMinY() + 0.5, this.p.h() + (double)this.p.f$src$F$fst3ac() * 0.35);
        PlayerSimulationUtil.p(this.p, this.p.z() - (double)this.p.f$src$F$fst3ac() * 0.35, axisAlignedBB.getMinY() + 0.5, this.p.h() - (double)this.p.f$src$F$fst3ac() * 0.35);
        PlayerSimulationUtil.p(this.p, this.p.z() + (double)this.p.f$src$F$fst3ac() * 0.35, axisAlignedBB.getMinY() + 0.5, this.p.h() - (double)this.p.f$src$F$fst3ac() * 0.35);
        PlayerSimulationUtil.p(this.p, this.p.z() + (double)this.p.f$src$F$fst3ac() * 0.35, axisAlignedBB.getMinY() + 0.5, this.p.h() + (double)this.p.f$src$F$fst3ac() * 0.35);
        boolean bl7 = bl = (float)this.p.Y$src$Lgg_vape_wrapper_impl_FoodStats_$fakh1z().getFoodLevel() > 6.0f || this.p.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().H();
        if (this.p.b$src$Z$fqlxe4() && !bl4 && !bl5 && this.d >= f && !this.p.B$src$Z$f90iek() && bl && !bl2 && !this.p.i(PotionRegistry.K)) {
            if (this.U <= 0 && !this.f) {
                this.U = 7;
            } else {
                this.p.R(true);
                this.B = 600;
            }
        }
        if (!this.p.B$src$Z$f90iek() && this.d >= f && bl && !bl2 && !this.p.i(PotionRegistry.K) && this.f) {
            this.p.R(true);
            this.B = 600;
        }
        if (this.p.B$src$Z$f90iek() && (this.d < f || this.p.r() || !bl)) {
            this.p.R(false);
            this.B = 0;
        }
        if (this.p.B$src$I$14s4bbr() > 0) {
            this.p.L(this.p.B$src$I$14s4bbr() - 1);
        }
        if (Math.abs(this.p.t()) < 0.005) {
            this.p.r(0.0);
        }
        if (Math.abs(this.p.q()) < 0.005) {
            this.p.k(0.0);
        }
        if (Math.abs(this.p.T()) < 0.005) {
            this.p.i(0.0);
        }
        this.p.k$src$V$5315b7(this.Z);
        this.p.M(this.d);
        this.p.b(this.W);
        if (this.p.e$src$Z$15bd4i1()) {
            if (this.p.h$src$Z$ftwoya()) {
                this.p.k(this.p.q() + (double)0.04f);
            } else if (this.p.Q$src$Z$fh9faz()) {
                this.p.k(this.p.q() + (double)0.04f);
            } else if (this.p.b$src$Z$fqlxe4() && this.p.B$src$I$14s4bbr() == 0) {
                this.p.k((double)0.42f);
                if (this.D.i(PotionRegistry.Z)) {
                    double d = (float)(this.D.b(PotionRegistry.Z).L() + 1) * 0.1f;
                    this.p.k(this.p.q() + d);
                }
                if (this.p.B$src$Z$f90iek()) {
                    float f2 = this.p.J() * ((float)Math.PI / 180);
                    this.p.r(this.p.t() - (double)(MathUtil.sin(f2) * 0.2f));
                    this.p.i(this.p.T() + (double)(MathUtil.cos(f2) * 0.2f));
                }
                this.p.L(10);
            }
        } else {
            this.p.L(0);
        }
        this.p.k$src$V$5315b7(this.p.N$src$F$14ypudi() * 0.98f);
        this.p.M(this.p.F() * 0.98f);
        PlayerSimulationUtil.g(this.p, this.p.N$src$F$14ypudi(), this.p.F());
        float f3 = 0.02f;
        AttributeInstance attributeInstance = this.p.h(MonsterAttributesBridge.B());
        if (!this.P.I()) {
            attributeInstance.I(this.p.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().l());
        }
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
        this.p.J(blockPlacementGraph.C);
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

    public BlockInLegacyMovementController189(EntityPlayer entityPlayer, EntityPlayerSP entityPlayerSP, EntityPlayer entityPlayer2, World world) {
        super(entityPlayer, entityPlayerSP, entityPlayer2, world);
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

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void q() {
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
