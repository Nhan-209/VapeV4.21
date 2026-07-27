package gg.vape.module.render.hud;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventRender3D;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.render.hud.HudModule;
import gg.vape.module.render.hud.HudModuleGroup;
import gg.vape.rotation.RotationManager;
import gg.vape.utils.MathUtil;
import gg.vape.utils.render.BufferedRenderPrimitives;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderUtil;
import gg.vape.utils.render.RenderUtils;
import gg.vape.value.ColorValue;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.Block;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.BlockReaderBridge;
import gg.vape.wrapper.impl.BlockState;
import gg.vape.wrapper.impl.EntityFishHook;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.RayTraceResult_type;
import gg.vape.wrapper.impl.RenderManager;
import gg.vape.wrapper.impl.WorldClient;
import java.awt.Color;
import org.lwjgl.opengl.GL11;

public class BlockOverlayHudModule
extends HudModule {
    private ColorValue overlayColor = ColorValue.L(this, "Overlay Color", new Color(255, 0, 0, 95));
    private ColorValue outlineColor = ColorValue.L(this, "Outline Color", new Color(255, 0, 0, 200));


    private void drawOutline(AxisAlignedBB axisAlignedBB, Color color) {
        if (GuiRenderPrimitives.d()) {
            BufferedRenderPrimitives.Q(axisAlignedBB.getMinX(), axisAlignedBB.getMinY(), axisAlignedBB.getMinZ(), axisAlignedBB.getMinX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMinZ(), 2.0f, color);
            BufferedRenderPrimitives.Q(axisAlignedBB.getMaxX(), axisAlignedBB.getMinY(), axisAlignedBB.getMinZ(), axisAlignedBB.getMaxX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMinZ(), 2.0f, color);
            BufferedRenderPrimitives.Q(axisAlignedBB.getMinX(), axisAlignedBB.getMinY(), axisAlignedBB.getMaxZ(), axisAlignedBB.getMinX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMaxZ(), 2.0f, color);
            BufferedRenderPrimitives.Q(axisAlignedBB.getMaxX(), axisAlignedBB.getMinY(), axisAlignedBB.getMaxZ(), axisAlignedBB.getMaxX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMaxZ(), 2.0f, color);
            BufferedRenderPrimitives.Q(axisAlignedBB.getMaxX(), axisAlignedBB.getMinY(), axisAlignedBB.getMinZ(), axisAlignedBB.getMaxX(), axisAlignedBB.getMinY(), axisAlignedBB.getMaxZ(), 2.0f, color);
            BufferedRenderPrimitives.Q(axisAlignedBB.getMaxX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMinZ(), axisAlignedBB.getMaxX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMaxZ(), 2.0f, color);
            BufferedRenderPrimitives.Q(axisAlignedBB.getMinX(), axisAlignedBB.getMinY(), axisAlignedBB.getMinZ(), axisAlignedBB.getMinX(), axisAlignedBB.getMinY(), axisAlignedBB.getMaxZ(), 2.0f, color);
            BufferedRenderPrimitives.Q(axisAlignedBB.getMinX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMinZ(), axisAlignedBB.getMinX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMaxZ(), 2.0f, color);
            BufferedRenderPrimitives.Q(axisAlignedBB.getMinX(), axisAlignedBB.getMinY(), axisAlignedBB.getMinZ(), axisAlignedBB.getMaxX(), axisAlignedBB.getMinY(), axisAlignedBB.getMinZ(), 2.0f, color);
            BufferedRenderPrimitives.Q(axisAlignedBB.getMinX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMinZ(), axisAlignedBB.getMaxX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMinZ(), 2.0f, color);
            BufferedRenderPrimitives.Q(axisAlignedBB.getMinX(), axisAlignedBB.getMinY(), axisAlignedBB.getMaxZ(), axisAlignedBB.getMaxX(), axisAlignedBB.getMinY(), axisAlignedBB.getMaxZ(), 2.0f, color);
            BufferedRenderPrimitives.Q(axisAlignedBB.getMinX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMaxZ(), axisAlignedBB.getMaxX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMaxZ(), 2.0f, color);
        } else {
            OpenGlBackendHolder.d.q((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);
            GL11.glBegin((int)1);
            GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMinZ());
            GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMinZ());
            GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMinZ());
            GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMaxZ());
            GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMaxZ());
            GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMaxZ());
            GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMaxZ());
            GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMinZ());
            GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMinZ());
            GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMinZ());
            GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMinZ());
            GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMinZ());
            GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMaxZ());
            GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMaxZ());
            GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMaxZ());
            GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMaxZ());
            GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMinZ());
            GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMinZ());
            GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMinZ());
            GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMaxZ());
            GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMaxZ());
            GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMaxZ());
            GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMaxZ());
            GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMinZ());
            GL11.glEnd();
        }
    }

    private void drawFilledBox(AxisAlignedBB axisAlignedBB, Color color) {
        if (GuiRenderPrimitives.d()) {
            BufferedRenderPrimitives.z(axisAlignedBB.getMinX(), axisAlignedBB.getMinY(), axisAlignedBB.getMinZ(), axisAlignedBB.getMaxX(), axisAlignedBB.getMinY(), axisAlignedBB.getMinZ(), axisAlignedBB.getMaxX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMinZ(), axisAlignedBB.getMinX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMinZ(), color);
            BufferedRenderPrimitives.z(axisAlignedBB.getMaxX(), axisAlignedBB.getMinY(), axisAlignedBB.getMaxZ(), axisAlignedBB.getMinX(), axisAlignedBB.getMinY(), axisAlignedBB.getMaxZ(), axisAlignedBB.getMinX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMaxZ(), axisAlignedBB.getMaxX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMaxZ(), color);
            BufferedRenderPrimitives.z(axisAlignedBB.getMinX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMinZ(), axisAlignedBB.getMinX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMaxZ(), axisAlignedBB.getMaxX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMaxZ(), axisAlignedBB.getMaxX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMinZ(), color);
            BufferedRenderPrimitives.z(axisAlignedBB.getMinX(), axisAlignedBB.getMinY(), axisAlignedBB.getMinZ(), axisAlignedBB.getMaxX(), axisAlignedBB.getMinY(), axisAlignedBB.getMinZ(), axisAlignedBB.getMaxX(), axisAlignedBB.getMinY(), axisAlignedBB.getMaxZ(), axisAlignedBB.getMinX(), axisAlignedBB.getMinY(), axisAlignedBB.getMaxZ(), color);
            BufferedRenderPrimitives.z(axisAlignedBB.getMinX(), axisAlignedBB.getMinY(), axisAlignedBB.getMaxZ(), axisAlignedBB.getMinX(), axisAlignedBB.getMinY(), axisAlignedBB.getMinZ(), axisAlignedBB.getMinX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMinZ(), axisAlignedBB.getMinX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMaxZ(), color);
            BufferedRenderPrimitives.z(axisAlignedBB.getMaxX(), axisAlignedBB.getMinY(), axisAlignedBB.getMinZ(), axisAlignedBB.getMaxX(), axisAlignedBB.getMinY(), axisAlignedBB.getMaxZ(), axisAlignedBB.getMaxX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMaxZ(), axisAlignedBB.getMaxX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMinZ(), color);
        } else {
            OpenGlBackendHolder.d.q((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);
            GL11.glBegin((int)7);
            GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMinZ());
            GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMinZ());
            GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMaxZ());
            GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMaxZ());
            GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMinZ());
            GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMaxZ());
            GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMaxZ());
            GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMinZ());
            GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMinZ());
            GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMinZ());
            GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMinZ());
            GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMinZ());
            GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMinZ());
            GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMinZ());
            GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMaxZ());
            GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMaxZ());
            GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMaxZ());
            GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMaxZ());
            GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMaxZ());
            GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMaxZ());
            GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMinZ());
            GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMaxZ());
            GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMaxZ());
            GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMinZ());
            GL11.glEnd();
        }
    }

    @EventHandler
    public void onRender3D(EventRender3D eventRender3D) {
        double d;
        double d2;
        double d3;
        AxisAlignedBB axisAlignedBB;
        RayTraceResult rayTraceResult = RotationManager.b.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic();
        if (rayTraceResult.isNull()) {
            return;
        }
        if (!rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.block())) {
            return;
        }
        Block block = rayTraceResult.Z$src$Lgg_vape_wrapper_impl_Block_$6x2c9a();
        if (block == null || block.isNull()) {
            return;
        }
        RenderUtil.d();
        OpenGlBackendHolder.d.l(3042);
        OpenGlBackendHolder.d.l(2929);
        GL11.glBlendFunc((int)770, (int)771);
        OpenGlBackendHolder.d.l(2848);
        OpenGlBackendHolder.d.r(2.0f);
        OpenGlBackendHolder.d.u$src$V$hntn98(3553);
        GlStateManager.depthMask(false);
        WorldClient worldClient = Minecraft.theWorld();
        if (ForgeVersion.MC_1_7_10.B()) {
            axisAlignedBB = rayTraceResult.Z$src$Lgg_vape_wrapper_impl_Block_$6x2c9a().M(Minecraft.theWorld(), rayTraceResult.g(), rayTraceResult.T(), rayTraceResult.a$src$I$8nuo9d());
        } else {
            BlockReaderBridge blockReaderBridge;
            EntityFishHook entityFishHook;
            BlockState blockState;
            BlockPos blockPos = BlockPos.D(MathUtil.floor((double)rayTraceResult.g()), MathUtil.floor((double)rayTraceResult.T()), MathUtil.floor((double)rayTraceResult.a$src$I$8nuo9d()));
            axisAlignedBB = ForgeVersion.MC_1_16_5.d() ? ((blockState = worldClient.getBlockState(blockPos)).isInstance(MappedClasses.Fj) ? (!(entityFishHook = (blockReaderBridge = new BlockReaderBridge(blockState)).Z(Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().l().x$src$Lgg_vape_wrapper_impl_BlockReader_$120g8sh(), blockPos)).o() ? entityFishHook.n() : null) : rayTraceResult.Z$src$Lgg_vape_wrapper_impl_Block_$6x2c9a().Q(worldClient, blockPos)) : (ForgeVersion.MC_1_12_2.L() ? rayTraceResult.Z$src$Lgg_vape_wrapper_impl_Block_$6x2c9a().Y(Minecraft.theWorld().getBlockState(blockPos), worldClient, blockPos) : rayTraceResult.Z$src$Lgg_vape_wrapper_impl_Block_$6x2c9a().Q(worldClient, blockPos));
        }
        if (axisAlignedBB == null) {
            return;
        }
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
        if (ForgeVersion.MC_1_16_5.d()) {
            OpenGlBackendHolder.d.I(rayTraceResult.g(), rayTraceResult.T(), rayTraceResult.a$src$I$8nuo9d());
        }
        axisAlignedBB = axisAlignedBB.expand(0.002f, 0.002f, 0.002f).A(-d3, -d2, -d);
        RenderUtils.g();
        this.drawFilledBox(axisAlignedBB, this.overlayColor.q$src$Lgg_vape_utils_MutableColor_$1dowyd3());
        OpenGlBackendHolder.d.r(2.0f);
        this.drawOutline(axisAlignedBB, this.outlineColor.q$src$Lgg_vape_utils_MutableColor_$1dowyd3());
        GlStateManager.depthMask(true);
        RenderUtils.f();
        OpenGlBackendHolder.d.l(3553);
        if (ForgeVersion.MC_1_21_6.d()) {
            OpenGlBackendHolder.d.u$src$V$hntn98(3042);
        }
        OpenGlBackendHolder.d.u$src$V$hntn98(2848);
        RenderUtil.Y();
    }

    public BlockOverlayHudModule() {
        super("Block Overlay", HudModuleGroup.T, "block_overlay");
        this.setSuffix("Highlights the block you're hovering by the specified color");
        this.addValue(this.overlayColor, this.outlineColor);
    }
}

