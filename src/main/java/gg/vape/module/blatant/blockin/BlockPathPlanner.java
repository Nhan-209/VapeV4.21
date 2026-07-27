package gg.vape.module.blatant.blockin;

import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.blatant.blockin.AbstractBlockInMovementController;
import gg.vape.module.blatant.blockin.BlockInCollisionMovementController;
import gg.vape.module.blatant.blockin.BlockInEntityMovementController;
import gg.vape.module.blatant.blockin.BlockInLegacyMovementController1122;
import gg.vape.module.blatant.blockin.BlockInLegacyMovementController189;
import gg.vape.module.blatant.blockin.BlockInTargetRotationState;
import gg.vape.module.blatant.blockin.BlockPlacementGraph;
import gg.vape.module.render.hud.FreeLookHudModule;
import gg.vape.movement.PlayerMovementTaskManager;
import gg.vape.rotation.AdaptiveRotationController;
import gg.vape.rotation.MouseRotationController;
import gg.vape.rotation.RotationManager;
import gg.vape.unmap.ModeSelection;
import gg.vape.utils.MathUtil;
import gg.vape.utils.PlayerSimulationUtil;
import gg.vape.utils.RayTraceUtil;
import gg.vape.utils.Vec3d;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GuiScreen;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.World;

public class BlockPathPlanner {
    private final World K;
    private final BlockPlacementGraph Y;
    private boolean S;
    private final GuiScreen H;
    private MouseRotationController I;
    private boolean J;
    private float D;
    private final RotationManager x = RotationManager.b;
    private double U;
    private final EntityPlayer k;
    private float s;
    private final AbstractBlockInMovementController c;
    private float g;
    private final EntityPlayer E;
    private final EntityPlayerSP j;

    public int g() {
        return this.c.K$src$I$15c3z46();
    }

    public boolean K$src$Z$17o55j8() {
        return this.c.y$src$Z$161eitf();
    }

    public void y(MouseRotationController mouseRotationController) {
        AdaptiveRotationController adaptiveRotationController;
        if (this.I == mouseRotationController) {
            return;
        }
        if (this.I instanceof AdaptiveRotationController && mouseRotationController instanceof AdaptiveRotationController) {
            adaptiveRotationController = (AdaptiveRotationController)this.I;
            adaptiveRotationController.b(true);
            adaptiveRotationController.u(true);
            AdaptiveRotationController adaptiveRotationController2 = (AdaptiveRotationController)mouseRotationController;
            adaptiveRotationController2.b(false);
            adaptiveRotationController2.T(adaptiveRotationController.J());
            adaptiveRotationController2.a(adaptiveRotationController.X());
        }
        if (this.I == null && mouseRotationController instanceof AdaptiveRotationController) {
            adaptiveRotationController = (AdaptiveRotationController)mouseRotationController;
            this.D = adaptiveRotationController.J();
            this.g = adaptiveRotationController.X();
        }
        this.I = mouseRotationController;
    }

    public boolean E$src$Z$17kudz2() {
        return this.I != null && this.I instanceof AdaptiveRotationController;
    }

    public void t() {
        if (this.Y != null) {
            this.Y.M = true;
            this.Y.D = false;
            this.Y.R = false;
            this.Y.Y = false;
        }
        this.G(true, false, false, false);
    }

    private static AbstractBlockInMovementController w(EntityPlayer entityPlayer, EntityPlayerSP entityPlayerSP, EntityPlayer entityPlayer2, World world) {
        if (ForgeVersion.MC_1_21_4.d()) {
            return new BlockInEntityMovementController(entityPlayer, entityPlayerSP, entityPlayer2, world);
        }
        if (ForgeVersion.MC_1_16_5.d()) {
            return new BlockInCollisionMovementController(entityPlayer, entityPlayerSP, entityPlayer2, world);
        }
        if (ForgeVersion.MC_1_12_2.d()) {
            return new BlockInLegacyMovementController1122(entityPlayer, entityPlayerSP, entityPlayer2, world);
        }
        return new BlockInLegacyMovementController189(entityPlayer, entityPlayerSP, entityPlayer2, world);
    }

    public void U(BlockPlacementGraph blockPlacementGraph) {
        this.c.X(blockPlacementGraph);
    }

    public void r(boolean bl) {
        this.c.q(bl);
    }

    private boolean lambda$getMouseOver$0(Entity entity) {
        return !entity.O$src$Z$fg5u49() && entity.n$src$Z$fx7gig() && !entity.equals(this.k) && !entity.equals(this.j);
    }

    public int D() {
        return this.c.C$src$I$157pmda();
    }

    private boolean lambda$getMouseOver$1(Entity entity) {
        return !entity.O$src$Z$fg5u49() && entity.n$src$Z$fx7gig() && !entity.equals(this.k) && !entity.equals(this.j);
    }

    public float U() {
        return this.c.x$src$F$160upqu();
    }

    public void l() {
        this.c.b(this.Y.M, this.Y.D, this.Y.R, this.Y.Y, this.Y.l, this.Y.c);
    }

    public BlockPathPlanner(EntityPlayer entityPlayer, EntityPlayerSP entityPlayerSP, World world, BlockPlacementGraph blockPlacementGraph) {
        this.k = entityPlayer;
        this.j = entityPlayerSP;
        this.K = world;
        this.E = PlayerSimulationUtil.I(entityPlayer);
        this.Y = blockPlacementGraph;
        this.U = this.x.D();
        this.H = Minecraft.currentScreen();
        this.c = BlockPathPlanner.w(this.E, entityPlayerSP, entityPlayer, world);
        this.q();
        if (entityPlayer.isInstance(MappedClasses.z5) && RotationManager.b.u()) {
            AdaptiveRotationController adaptiveRotationController = (AdaptiveRotationController)RotationManager.b.w();
            this.D = adaptiveRotationController.J();
            this.g = adaptiveRotationController.X();
        }
    }

    public BlockInTargetRotationState E() {
        BlockInTargetRotationState blockInTargetRotationState = new BlockInTargetRotationState(this.E);
        blockInTargetRotationState.m(new BlockPlacementGraph(this));
        return blockInTargetRotationState;
    }

    public void K() {
        this.c.G(false);
    }

    public void q() {
        this.c.B();
    }

    public void h() {
        this.c.b(false, false, false, false, false, false);
    }

    public float k() {
        return this.c.q$src$F$15x05lb();
    }

    public BlockPathPlanner(EntityPlayer entityPlayer, EntityPlayerSP entityPlayerSP, World world, BlockPlacementGraph blockPlacementGraph, BlockPathPlanner blockPathPlanner) {
        this.k = entityPlayer;
        this.j = entityPlayerSP;
        this.K = world;
        this.E = blockPathPlanner.T();
        PlayerSimulationUtil.s(this.E, entityPlayer);
        this.Y = blockPlacementGraph;
        this.U = this.x.D();
        this.H = Minecraft.currentScreen();
        this.c = BlockPathPlanner.w(this.E, entityPlayerSP, entityPlayer, world);
        this.q();
        if (entityPlayer.isInstance(MappedClasses.z5) && RotationManager.b.u()) {
            AdaptiveRotationController adaptiveRotationController = (AdaptiveRotationController)RotationManager.b.w();
            this.D = adaptiveRotationController.J();
            this.g = adaptiveRotationController.X();
        }
    }

    public Vec3d f() {
        return new Vec3d(this.E.z(), this.E.N(), this.E.h());
    }

    public boolean i() {
        return this.c.l$src$Z$15u973q();
    }

    public boolean Y() {
        return this.c.K$src$Z$15c3zit();
    }

    public void O() {
        boolean bl;
        boolean bl2 = this.E$src$Z$17kudz2();
        if (bl2) {
            boolean bl3;
            ModeSelection modeSelection = (ModeSelection)Vape.INSTANCE.getClientSettings().o.K();
            if (modeSelection.equals(ClientSettings.O)) {
                return;
            }
            boolean bl4 = bl3 = modeSelection.equals(ClientSettings.Y) || modeSelection.equals(ClientSettings.u);
            if (bl3) {
                boolean bl5 = this.D$src$Z$17kaldp();
                AdaptiveRotationController adaptiveRotationController = (AdaptiveRotationController)this.I;
                this.s = FreeLookHudModule.z() ? FreeLookHudModule.L$src$F$1jnmc2m() : this.E.J();
                float f = adaptiveRotationController.v$src$F$1mgxytb();
                float f2 = this.x.G(f, this.c.c(), this.c.s$src$Z$15y3r99(), this.c.N$src$Z$15drdaw(), this.c.z$src$Z$161ybes());
                float f3 = modeSelection.equals(ClientSettings.u) ? f2 + 180.0f : this.D;
                this.E.H(f3);
                this.E.z(f3);
                this.J = true;
                if (bl5) {
                    float f4 = MathUtil.wrapAngleTo180(MathUtil.wrapAngleTo180(f3) - f2);
                    float f5 = f4 * ((float)Math.PI / 180);
                    float f6 = (float)Math.cos(f5);
                    float f7 = (float)(-Math.sin(f5));
                    double d = PlayerMovementTaskManager.G.e() != null ? 0.075 : (double)0.4f;
                    boolean bl6 = (double)f6 >= d;
                    boolean bl7 = (double)f7 >= d;
                    boolean bl8 = (double)f7 <= -d;
                    boolean bl9 = (double)f6 <= -d;
                    this.G(bl6, bl9, bl8, bl7);
                    this.S = true;
                }
                if (!bl5 && this.S) {
                    this.l();
                    this.S = false;
                }
            }
            return;
        }
        ModeSelection modeSelection = (ModeSelection)Vape.INSTANCE.getClientSettings().o.K();
        if (modeSelection.equals(ClientSettings.O)) {
            return;
        }
        boolean bl10 = bl = modeSelection.equals(ClientSettings.Y) || modeSelection.equals(ClientSettings.u);
        if (bl) {
            boolean bl11 = this.D$src$Z$17kaldp();
            if (this.S) {
                this.l();
                this.S = false;
            }
        }
    }

    public void G(boolean bl, boolean bl2, boolean bl3, boolean bl4) {
        this.c.E(bl);
        this.c.Z(bl2);
        this.c.T(bl3);
        this.c.r(bl4);
    }

    public void e(boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5, boolean bl6) {
        this.c.b(bl, bl2, bl3, bl4, bl5, bl6);
    }

    public boolean d$src$Z$181w0d9() {
        return this.c.s$src$Z$15y3r99();
    }

    public MouseRotationController H() {
        return this.I;
    }

    public boolean g$src$Z$183je5c() {
        return this.c.w();
    }

    public EntityPlayer T() {
        return this.E;
    }

    public boolean o() {
        return this.c.N$src$Z$15drdaw();
    }

    public void d() {
        if (this.I != null) {
            double d = Math.round(50.0f * this.x.X());
            this.U += d;
            int n = (int)Math.round(this.U);
            for (int i = 0; i < n; ++i) {
                try {
                    this.I.J(this.j, this.H);
                    this.I.o(this.H);
                    continue;
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
            this.U -= (double)n;
            if (this.E$src$Z$17kudz2()) {
                AdaptiveRotationController adaptiveRotationController = (AdaptiveRotationController)this.I;
                this.D = adaptiveRotationController.J();
                this.g = adaptiveRotationController.X();
            }
        }
    }

    public boolean C() {
        return this.c.c();
    }


    public void B() {
        this.I(true);
    }

    public RayTraceResult C(double d, float f, boolean bl) {
        RayTraceResult rayTraceResult;
        EntityPlayer entityPlayer = this.E;
        if (this.E$src$Z$17kudz2()) {
            float f2 = entityPlayer.J();
            float f3 = entityPlayer.s();
            float f4 = entityPlayer.V();
            entityPlayer.H(this.D);
            entityPlayer.z(this.D);
            entityPlayer.C(this.g);
            rayTraceResult = RayTraceUtil.U(this.E, d, f, bl, ForgeVersion.MC_1_16_5.v() ? null : this::lambda$getMouseOver$0);
            entityPlayer.H(f2);
            entityPlayer.z(f3);
            entityPlayer.C(f4);
        } else {
            entityPlayer.z(entityPlayer.J());
            rayTraceResult = RayTraceUtil.U(this.E, d, f, bl, ForgeVersion.MC_1_16_5.v() ? null : this::lambda$getMouseOver$1);
        }
        return rayTraceResult;
    }

    public void I(boolean bl) {
        if (bl) {
            this.d();
        }
        this.E.D(this.E.J());
        this.E.l(this.E.V());
        this.E.n(this.E.z());
        this.E.w(this.E.N());
        this.E.A(this.E.h());
        if (this.E$src$Z$17kudz2()) {
            AdaptiveRotationController adaptiveRotationController = (AdaptiveRotationController)this.I;
            this.D = adaptiveRotationController.J();
            this.g = MathUtil.clamp(adaptiveRotationController.X(), -90.0f, 90.0f);
            this.O();
        }
        this.c.N();
        if (this.J) {
            this.E.H(this.s);
            this.E.z(this.s);
            this.J = false;
        }
        if (this.S) {
            this.l();
            this.S = false;
        }
    }

    public boolean D$src$Z$17kaldp() {
        return this.c.c() || this.c.z$src$Z$161ybes() || this.c.N$src$Z$15drdaw() || this.c.s$src$Z$15y3r99();
    }

    public boolean s() {
        return this.c.z$src$Z$161ybes();
    }

    public boolean R() {
        return this.c.C$src$Z$157pmrx();
    }
}

