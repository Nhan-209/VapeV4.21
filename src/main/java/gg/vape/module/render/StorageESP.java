package gg.vape.module.render;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventRender3D;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.runtime.ObfuscatedRuntimeException;
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
    private final ColorValue C;
    private final BooleanValue S;
    private final ColorValue k;
    private final BooleanValue A;
    private final BooleanValue L;
    private final BooleanValue I;
    private final ColorValue K;
    private static final long D = -6638498230915768526L;
    private final BooleanValue O;
    private final ColorValue a;
    private final ColorValue o;
    private final BooleanValue Y;
    private final ColorValue J;
    private final BooleanValue Z;
    private final BooleanValue b;
    private final ColorValue c;
    private final RenderManager p;
    private final ColorValue U;
    private final BooleanValue j = BooleanValue.create(this, "Outline open", true, "Outlines open chests by contrasting color");

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
                if ((this.O.L().booleanValue() || this.I.L().booleanValue()) && MappedClasses.DZ.isInstance(e)) {
                    TileEntityOpenedChest openedChest = new TileEntityOpenedChest(e);
                    int n = openedChest.getNumPlayersUsing();
                    if (this.O.L().booleanValue() && n == 0) {
                        color = this.C.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
                        tileEntity = openedChest;
                    }
                    if (this.I.L().booleanValue() && n == 1) {
                        color = this.o.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
                        tileEntity = openedChest;
                    }
                } else if (this.L.L().booleanValue() && MappedClasses.u0.isInstance(e)) {
                    color = this.K.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
                    tileEntity = new TileEntityChest(e);
                } else if (this.A.L().booleanValue() && MappedClasses.Dx.isInstance(e)) {
                    color = this.k.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
                } else if (this.Y.L().booleanValue() && MappedClasses.li.isInstance(e)) {
                    color = this.c.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
                } else if (this.S.L().booleanValue() && MappedClasses.YI.equals(e.getClass())) {
                    color = this.U.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
                } else if (this.Z.L().booleanValue() && MappedClasses.lI.equals(e.getClass())) {
                    color = this.a.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
                } else if (ForgeVersion.MC_1_12_2.d() && this.b.L().booleanValue() && MappedClasses.Dh.isInstance(e)) {
                    color = this.J.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
                }
                if (color == null) continue;
                if (tileEntity == null) {
                    tileEntity = new TileEntity(e);
                }
                color = new Color(((Color)color).getRed(), ((Color)color).getGreen(), ((Color)color).getBlue(), ((Color)color).getAlpha());
                HSBData renderData;
                if (tileEntity instanceof TileEntityOpenedChest) {
                    TileEntityOpenedChest tileEntityOpenedChest = (TileEntityOpenedChest)tileEntity;
                    renderData = this.j.L().booleanValue() ? new HSBAData(tileEntity.getX(), tileEntity.getY(), tileEntity.getZ(), -1, color, tileEntityOpenedChest.Y()) : new HSBData(tileEntity.getX(), tileEntity.getY(), tileEntity.getZ(), -1, color);
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
            if ((this.O.L().booleanValue() || this.I.L().booleanValue()) && MappedClasses.DZ.isInstance(e)) {
                TileEntityOpenedChest openedChest = new TileEntityOpenedChest(e);
                int n = openedChest.getNumPlayersUsing();
                if (this.O.L().booleanValue() && n == 0) {
                    color = this.C.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
                    tileEntity = openedChest;
                }
                if (this.I.L().booleanValue() && n == 1) {
                    color = this.o.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
                    tileEntity = openedChest;
                }
            } else if (this.L.L().booleanValue() && MappedClasses.u0.isInstance(e)) {
                color = this.K.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
                tileEntity = new TileEntityChest(e);
            } else if (this.A.L().booleanValue() && MappedClasses.Dx.isInstance(e)) {
                color = this.k.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
            } else if (this.Y.L().booleanValue() && MappedClasses.li.isInstance(e)) {
                color = this.c.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
            } else if (this.S.L().booleanValue() && MappedClasses.YI.equals(e.getClass())) {
                color = this.U.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
            } else if (this.Z.L().booleanValue() && MappedClasses.lI.equals(e.getClass())) {
                color = this.a.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
            } else if (ForgeVersion.MC_1_12_2.d() && this.b.L().booleanValue() && MappedClasses.Dh.isInstance(e)) {
                color = this.J.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
            }
            if (color == null) continue;
            if (tileEntity == null) {
                tileEntity = new TileEntity(e);
            }
            color = new Color(((Color)color).getRed(), ((Color)color).getGreen(), ((Color)color).getBlue(), ((Color)color).getAlpha());
            HSBData renderData;
            if (tileEntity instanceof TileEntityOpenedChest) {
                TileEntityOpenedChest tileEntityOpenedChest = (TileEntityOpenedChest)tileEntity;
                renderData = this.j.L().booleanValue() ? new HSBAData(tileEntity.getX(), tileEntity.getY(), tileEntity.getZ(), -1, color, tileEntityOpenedChest.Y()) : new HSBData(tileEntity.getX(), tileEntity.getY(), tileEntity.getZ(), -1, color);
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
        super("StorageESP", (int)D, Category.k);
        this.O = BooleanValue.create(this, "Render Chests", true);
        this.I = BooleanValue.create(this, "Render Trapped Chests", true);
        this.L = BooleanValue.create(this, "Render Enderchests", false);
        this.A = BooleanValue.create(this, "Render Hopper", false);
        this.Y = BooleanValue.create(this, "Render Furnace", false);
        this.Z = BooleanValue.create(this, "Render Dispenser", false);
        this.S = BooleanValue.create(this, "Render Dropper", false);
        this.b = BooleanValue.create(this, "Render Shulker", false);
        this.C = ColorValue.L(this, "Chest Color", new Color(1, 255, 146, 100));
        this.o = ColorValue.L(this, "Chest Color", new Color(255, 0, 0, 100));
        this.K = ColorValue.L(this, "Ender Chest Color", new Color(126, 21, 156, 100));
        this.k = ColorValue.L(this, "Hopper Color", new Color(138, 138, 138, 255));
        this.c = ColorValue.L(this, "Furnace Color", new Color(90, 90, 90, 255));
        this.a = ColorValue.L(this, "Dispenser Color", new Color(1, 20, 200, 100));
        this.U = ColorValue.L(this, "Dropper Color", new Color(70, 200, 200, 100));
        this.J = ColorValue.L(this, "Shulker Color", new Color(255, 255, 255, 100));
        this.O.K(this.C);
        this.I.K(this.o);
        this.L.K(this.K);
        this.A.K(this.k);
        this.Y.K(this.c);
        this.Z.K(this.a);
        this.S.K(this.U);
        this.b.K(this.J);
        this.addValue(this.j, this.O, this.C, this.I, this.o, this.L, this.K, this.A, this.k, this.Y, this.c, this.Z, this.a, this.S, this.U);
        this.U(this.b, ForgeVersion.MC_1_12_2.n());
        this.U(this.J, ForgeVersion.MC_1_12_2.n());
        this.p = Minecraft.D();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}
