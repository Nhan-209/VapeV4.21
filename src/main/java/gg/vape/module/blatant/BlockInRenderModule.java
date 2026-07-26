package gg.vape.module.blatant;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.EventPriority;
import gg.vape.event.impl.EventPostEntityUpdate;
import gg.vape.event.impl.EventPreEntityUpdate;
import gg.vape.event.impl.EventRender3D;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.blatant.blockin.BlockPathPlanner;
import gg.vape.module.blatant.blockin.BlockPlacementGraph;
import gg.vape.rotation.AdaptiveRotationController;
import gg.vape.rotation.MouseRotationController;
import gg.vape.rotation.RotationManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderUtil;
import gg.vape.utils.render.RenderUtils;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GameSettings;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RenderManager;
import gg.vape.wrapper.impl.World;
import java.awt.Color;
import org.lwjgl.opengl.GL11;

public class BlockInRenderModule
extends Mod {
    private BlockPlacementGraph J;
    private EntityPlayer j;
    private BlockPathPlanner c;
    private static final long t = 4400050581515796479L;
    private final Color r;
    private BlockPlacementGraph o;
    private final Color p = new Color(0, 0, 0, 150);

    @Override
    public void onEnable() {
        Vape.debugLog("\nSimulation test module enabled, ready to simulate player movement.\n");
    }

    @EventHandler(A=EventPriority.HIGHEST)
    public void f(EventPreEntityUpdate eventPreEntityUpdate) {
        GameSettings gameSettings = eventPreEntityUpdate.getGameSettings();
        EntityPlayerSP entityPlayerSP = eventPreEntityUpdate.getThePlayer();
        World world = entityPlayerSP.getWorld();
        if (entityPlayerSP.isNull() || world.isNull()) {
            return;
        }
        this.o = new BlockPlacementGraph(entityPlayerSP);
        if (this.c == null) {
            this.c = new BlockPathPlanner(entityPlayerSP, entityPlayerSP, world, this.o);
            this.c.U(this.o);
            this.j = this.c.T();
            MouseRotationController mouseRotationController = this.N(entityPlayerSP);
            if (mouseRotationController != null) {
                this.c.y(mouseRotationController);
            }
        }
        this.j.H(entityPlayerSP.J());
        this.j.l(entityPlayerSP.D());
        this.j.C(entityPlayerSP.V());
        this.j.D(entityPlayerSP.j());
        this.j.z(entityPlayerSP.s());
        this.j.o(entityPlayerSP.P$src$F$14ztfk8());
        boolean bl = gameSettings.Y().u();
        boolean bl2 = gameSettings.s().u();
        boolean bl3 = gameSettings.x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg().u();
        boolean bl4 = gameSettings.g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3().u();
        boolean bl5 = gameSettings.O().u();
        boolean bl6 = gameSettings.d$src$Lgg_vape_wrapper_impl_KeyBinding_$adn2z0().u();
        boolean bl7 = gameSettings.r().u();
        this.c.e(bl, bl2, bl3, bl4, bl5, bl6);
        this.c.r(bl7);
        this.J = new BlockPlacementGraph(this.c);
        this.c.I(false);
    }

    @EventHandler(A=EventPriority.HIGHEST)
    public void U(EventPostEntityUpdate eventPostEntityUpdate) {
        EntityPlayerSP entityPlayerSP = eventPostEntityUpdate.getThePlayer();
        if (entityPlayerSP.isNull() || this.c == null) {
            return;
        }
        BlockPlacementGraph blockPlacementGraph = new BlockPlacementGraph(entityPlayerSP);
        BlockPlacementGraph blockPlacementGraph2 = new BlockPlacementGraph(this.c);
        this.c.d();
        boolean bl = false;
        if (!this.o.Q(this.J)) {
            bl = true;
            Vape.debugLog("PRE UPDATE SNAPSHOT IS OFF");
        }
        if (!blockPlacementGraph.Q(blockPlacementGraph2)) {
            bl = true;
            Vape.debugLog("POST UPDATE SNAPSHOT IS OFF");
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @EventHandler
    public void onRender3D(EventRender3D eventRender3D) {
        EntityPlayerSP entityPlayerSP = eventRender3D.getThePlayer();
        if (entityPlayerSP.isNull() || this.c == null) {
            return;
        }
        this.K(eventRender3D);
    }

    public BlockInRenderModule() {
        super("Simulation", (int)t, Category.Y);
        this.r = new Color(255, 255, 255, 150);
    }

    public MouseRotationController N(EntityPlayer entityPlayer) {
        MouseRotationController mouseRotationController = RotationManager.b.w();
        if (mouseRotationController == null) {
            return null;
        }
        if (mouseRotationController instanceof AdaptiveRotationController) {
            AdaptiveRotationController adaptiveRotationController = (AdaptiveRotationController)mouseRotationController;
            AdaptiveRotationController adaptiveRotationController2 = new AdaptiveRotationController(entityPlayer);
            adaptiveRotationController2.I(adaptiveRotationController);
            return adaptiveRotationController2;
        }
        return null;
    }

    @Override
    public void onDisable() {
        if (this.c != null) {
            this.c = null;
            this.j = null;
            Vape.debugLog("\n\nSimulation test module disabled, simulator reset.\n");
        }
    }

    private void K(EventRender3D eventRender3D) {
        double d;
        double d2;
        double d3;
        double d4 = this.j.z();
        double d5 = this.j.N();
        double d6 = this.j.h();
        double d7 = this.j.f();
        double d8 = this.j.H();
        double d9 = this.j.R();
        float f = Minecraft.getTimer().renderPartialTicks();
        float f2 = this.j.j() + (this.j.J() - this.j.j()) * f;
        double d10 = d7 + (d4 - d7) * (double)f;
        double d11 = d8 + (d5 - d8) * (double)f;
        double d12 = d9 + (d6 - d9) * (double)f;
        RenderUtil.d();
        Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().B(1.0);
        RenderUtils.g();
        OpenGlBackendHolder.d.l(3042);
        GL11.glBlendFunc((int)770, (int)771);
        OpenGlBackendHolder.d.r(1.5f);
        OpenGlBackendHolder.d.u$src$V$hntn98(3553);
        OpenGlBackendHolder.d.l(2848);
        OpenGlBackendHolder.d.u$src$V$hntn98(2929);
        GL11.glDepthMask((boolean)false);
        if (ForgeVersion.MC_1_20_6.d()) {
            d3 = RenderManager.getInterpolatedRenderPosX();
            d2 = RenderManager.getInterpolatedRenderPosY();
            d = RenderManager.getInterpolatedRenderPosZ();
        } else {
            RenderManager renderManager = Minecraft.D();
            d3 = renderManager.getRenderPosX();
            d2 = renderManager.getRenderPosY();
            d = renderManager.getRenderPosZ();
        }
        double d13 = this.j.u$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$kogbsu().getMaxX() - this.j.u$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$kogbsu().getMinX() + (double)this.j.b();
        double d14 = d13 / 2.0;
        RenderUtil.u(d10 - d14, d11, d12 - d14, d13, this.j.Y(), d13, 1.5, this.p, this.r, d3, d2, d);
        GL11.glDepthMask((boolean)true);
        OpenGlBackendHolder.d.l(2929);
        OpenGlBackendHolder.d.l(3553);
        OpenGlBackendHolder.d.u$src$V$hntn98(2848);
        OpenGlBackendHolder.d.u$src$V$hntn98(3042);
        RenderUtils.f();
        Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().O(1.0);
        RenderUtil.Y();
    }
}

