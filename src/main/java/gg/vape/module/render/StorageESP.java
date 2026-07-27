package gg.vape.module.render;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventRender3D;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.utils.datas.HSBAData;
import gg.vape.utils.datas.HSBData;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderUtil;
import gg.vape.utils.render.RenderUtils;
import gg.vape.value.BooleanValue;
import gg.vape.value.ColorValue;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RenderManager;
import gg.vape.wrapper.impl.TileEntity;
import gg.vape.wrapper.impl.TileEntityChest;
import gg.vape.wrapper.impl.TileEntityOpenedChest;
import java.awt.Color;
import org.lwjgl.opengl.GL11;

public class StorageESP
extends Mod {
    private final ColorValue chestColor;
    private final BooleanValue renderDropper;
    private final ColorValue hopperColor;
    private final BooleanValue renderHopper;
    private final BooleanValue renderEnderchests;
    private final BooleanValue renderTrappedChests;
    private final ColorValue enderChestColor;
    private static final long MODULE_ID = -6638498230915768526L;
    private final BooleanValue renderChests;
    private final ColorValue dispenserColor;
    private final ColorValue trappedChestColor;
    private final BooleanValue renderFurnace;
    private final ColorValue shulkerColor;
    private final BooleanValue renderDispenser;
    private final BooleanValue renderShulker;
    private final ColorValue furnaceColor;
    private final RenderManager renderManager;
    private final ColorValue dropperColor;
    private final BooleanValue outlineOpen = BooleanValue.create(this, "Outline open", true, "Outlines open chests by contrasting color");

    @EventHandler
    public void onRender3D(EventRender3D eventRender3D) {
        RenderUtil.d();
        Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().B(1.0);
        RenderUtils.g();
        boolean bl = OpenGlBackendHolder.d.L(3042);
        if (bl) {
            GL11.glBlendFunc((int)770, (int)771);
            OpenGlBackendHolder.d.r(1.5f);
            OpenGlBackendHolder.d.u$src$V$hntn98(3553);
            OpenGlBackendHolder.d.l(2848);
            OpenGlBackendHolder.d.u$src$V$hntn98(2929);
            OpenGlBackendHolder.d.U(false);
            double d = RenderManager.getInterpolatedRenderPosX();
            double d2 = RenderManager.getInterpolatedRenderPosY();
            double d3 = RenderManager.getInterpolatedRenderPosZ();
            for (Object e : Minecraft.theWorld().R$src$Ljava_util_List_$1ycbpra()) {
                Color color = null;
                TileEntity tileEntity = null;
                if ((this.renderChests.L().booleanValue() || this.renderTrappedChests.L().booleanValue()) && MappedClasses.DZ.isInstance(e)) {
                    TileEntityOpenedChest openedChest = new TileEntityOpenedChest(e);
                    int n = openedChest.getNumPlayersUsing();
                    if (this.renderChests.L().booleanValue() && n == 0) {
                        color = this.chestColor.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
                        tileEntity = openedChest;
                    }
                    if (this.renderTrappedChests.L().booleanValue() && n == 1) {
                        color = this.trappedChestColor.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
                        tileEntity = openedChest;
                    }
                } else if (this.renderEnderchests.L().booleanValue() && MappedClasses.u0.isInstance(e)) {
                    color = this.enderChestColor.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
                    tileEntity = new TileEntityChest(e);
                } else if (this.renderHopper.L().booleanValue() && MappedClasses.Dx.isInstance(e)) {
                    color = this.hopperColor.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
                } else if (this.renderFurnace.L().booleanValue() && MappedClasses.li.isInstance(e)) {
                    color = this.furnaceColor.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
                } else if (this.renderDropper.L().booleanValue() && MappedClasses.YI.equals(e.getClass())) {
                    color = this.dropperColor.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
                } else if (this.renderDispenser.L().booleanValue() && MappedClasses.lI.equals(e.getClass())) {
                    color = this.dispenserColor.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
                } else if (ForgeVersion.MC_1_12_2.d() && this.renderShulker.L().booleanValue() && MappedClasses.Dh.isInstance(e)) {
                    color = this.shulkerColor.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
                }
                if (color == null) continue;
                if (tileEntity == null) {
                    tileEntity = new TileEntity(e);
                }
                color = new Color(((Color)color).getRed(), ((Color)color).getGreen(), ((Color)color).getBlue(), ((Color)color).getAlpha());
                HSBData renderData;
                if (tileEntity instanceof TileEntityOpenedChest) {
                    TileEntityOpenedChest tileEntityOpenedChest = (TileEntityOpenedChest)tileEntity;
                    renderData = this.outlineOpen.L().booleanValue() ? new HSBAData(tileEntity.getX(), tileEntity.getY(), tileEntity.getZ(), -1, color, tileEntityOpenedChest.Y()) : new HSBData(tileEntity.getX(), tileEntity.getY(), tileEntity.getZ(), -1, color);
                } else if (tileEntity instanceof TileEntityChest) {
                    TileEntity tileEntity2 = tileEntity;
                    renderData = new HSBAData(tileEntity.getX(), tileEntity.getY(), tileEntity.getZ(), -1, color, ((TileEntityChest)tileEntity2).D());
                } else {
                    renderData = new HSBData(tileEntity.getX(), tileEntity.getY(), tileEntity.getZ(), -1, color);
                }
                RenderUtil.L(d, d2, d3, renderData);
            }
            OpenGlBackendHolder.d.U(true);
            OpenGlBackendHolder.d.l(2929);
            OpenGlBackendHolder.d.l(3553);
            OpenGlBackendHolder.d.u$src$V$hntn98(2848);
            RenderUtils.f();
            Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().O(1.0);
            RenderUtil.Y();
            return;
        }
        OpenGlBackendHolder.d.l(3042);
        GL11.glBlendFunc((int)770, (int)771);
        OpenGlBackendHolder.d.r(1.5f);
        OpenGlBackendHolder.d.u$src$V$hntn98(3553);
        OpenGlBackendHolder.d.l(2848);
        OpenGlBackendHolder.d.u$src$V$hntn98(2929);
        OpenGlBackendHolder.d.U(false);
        double d = RenderManager.getInterpolatedRenderPosX();
        double d4 = RenderManager.getInterpolatedRenderPosY();
        double d5 = RenderManager.getInterpolatedRenderPosZ();
        for (Object e : Minecraft.theWorld().R$src$Ljava_util_List_$1ycbpra()) {
            Color color = null;
            TileEntity tileEntity = null;
            if ((this.renderChests.L().booleanValue() || this.renderTrappedChests.L().booleanValue()) && MappedClasses.DZ.isInstance(e)) {
                TileEntityOpenedChest openedChest = new TileEntityOpenedChest(e);
                int n = openedChest.getNumPlayersUsing();
                if (this.renderChests.L().booleanValue() && n == 0) {
                    color = this.chestColor.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
                    tileEntity = openedChest;
                }
                if (this.renderTrappedChests.L().booleanValue() && n == 1) {
                    color = this.trappedChestColor.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
                    tileEntity = openedChest;
                }
            } else if (this.renderEnderchests.L().booleanValue() && MappedClasses.u0.isInstance(e)) {
                color = this.enderChestColor.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
                tileEntity = new TileEntityChest(e);
            } else if (this.renderHopper.L().booleanValue() && MappedClasses.Dx.isInstance(e)) {
                color = this.hopperColor.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
            } else if (this.renderFurnace.L().booleanValue() && MappedClasses.li.isInstance(e)) {
                color = this.furnaceColor.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
            } else if (this.renderDropper.L().booleanValue() && MappedClasses.YI.equals(e.getClass())) {
                color = this.dropperColor.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
            } else if (this.renderDispenser.L().booleanValue() && MappedClasses.lI.equals(e.getClass())) {
                color = this.dispenserColor.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
            } else if (ForgeVersion.MC_1_12_2.d() && this.renderShulker.L().booleanValue() && MappedClasses.Dh.isInstance(e)) {
                color = this.shulkerColor.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
            }
            if (color == null) continue;
            if (tileEntity == null) {
                tileEntity = new TileEntity(e);
            }
            color = new Color(((Color)color).getRed(), ((Color)color).getGreen(), ((Color)color).getBlue(), ((Color)color).getAlpha());
            HSBData renderData;
            if (tileEntity instanceof TileEntityOpenedChest) {
                TileEntityOpenedChest tileEntityOpenedChest = (TileEntityOpenedChest)tileEntity;
                renderData = this.outlineOpen.L().booleanValue() ? new HSBAData(tileEntity.getX(), tileEntity.getY(), tileEntity.getZ(), -1, color, tileEntityOpenedChest.Y()) : new HSBData(tileEntity.getX(), tileEntity.getY(), tileEntity.getZ(), -1, color);
            } else if (tileEntity instanceof TileEntityChest) {
                TileEntity tileEntity3 = tileEntity;
                renderData = new HSBAData(tileEntity.getX(), tileEntity.getY(), tileEntity.getZ(), -1, color, ((TileEntityChest)tileEntity3).D());
            } else {
                renderData = new HSBData(tileEntity.getX(), tileEntity.getY(), tileEntity.getZ(), -1, color);
            }
            RenderUtil.L(d, d4, d5, renderData);
        }
        OpenGlBackendHolder.d.U(true);
        OpenGlBackendHolder.d.l(2929);
        OpenGlBackendHolder.d.l(3553);
        OpenGlBackendHolder.d.u$src$V$hntn98(2848);
        OpenGlBackendHolder.d.u$src$V$hntn98(3042);
        RenderUtils.f();
        Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().O(1.0);
        RenderUtil.Y();
    }

    public StorageESP() {
        super("StorageESP", (int)MODULE_ID, Category.k);
        this.renderChests = BooleanValue.create(this, "Render Chests", true);
        this.renderTrappedChests = BooleanValue.create(this, "Render Trapped Chests", true);
        this.renderEnderchests = BooleanValue.create(this, "Render Enderchests", false);
        this.renderHopper = BooleanValue.create(this, "Render Hopper", false);
        this.renderFurnace = BooleanValue.create(this, "Render Furnace", false);
        this.renderDispenser = BooleanValue.create(this, "Render Dispenser", false);
        this.renderDropper = BooleanValue.create(this, "Render Dropper", false);
        this.renderShulker = BooleanValue.create(this, "Render Shulker", false);
        this.chestColor = ColorValue.L(this, "Chest Color", new Color(1, 255, 146, 100));
        this.trappedChestColor = ColorValue.L(this, "Chest Color", new Color(255, 0, 0, 100));
        this.enderChestColor = ColorValue.L(this, "Ender Chest Color", new Color(126, 21, 156, 100));
        this.hopperColor = ColorValue.L(this, "Hopper Color", new Color(138, 138, 138, 255));
        this.furnaceColor = ColorValue.L(this, "Furnace Color", new Color(90, 90, 90, 255));
        this.dispenserColor = ColorValue.L(this, "Dispenser Color", new Color(1, 20, 200, 100));
        this.dropperColor = ColorValue.L(this, "Dropper Color", new Color(70, 200, 200, 100));
        this.shulkerColor = ColorValue.L(this, "Shulker Color", new Color(255, 255, 255, 100));
        this.renderChests.K(this.chestColor);
        this.renderTrappedChests.K(this.trappedChestColor);
        this.renderEnderchests.K(this.enderChestColor);
        this.renderHopper.K(this.hopperColor);
        this.renderFurnace.K(this.furnaceColor);
        this.renderDispenser.K(this.dispenserColor);
        this.renderDropper.K(this.dropperColor);
        this.renderShulker.K(this.shulkerColor);
        this.addValue(this.outlineOpen, this.renderChests, this.chestColor, this.renderTrappedChests, this.trappedChestColor, this.renderEnderchests, this.enderChestColor, this.renderHopper, this.hopperColor, this.renderFurnace, this.furnaceColor, this.renderDispenser, this.dispenserColor, this.renderDropper, this.dropperColor);
        this.U(this.renderShulker, ForgeVersion.MC_1_12_2.n());
        this.U(this.shulkerColor, ForgeVersion.MC_1_12_2.n());
        this.renderManager = Minecraft.D();
    }

}
