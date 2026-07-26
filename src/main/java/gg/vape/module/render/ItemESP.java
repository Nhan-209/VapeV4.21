package gg.vape.module.render;

import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventGuiOpen;
import gg.vape.event.impl.EventPostTick;
import gg.vape.event.impl.EventRender3D;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.render.hud.FreeLookHudModule;
import gg.vape.module.render.item.ItemESPGroup;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.render.BufferedGuiRenderPrimitives;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderMatrix4f;
import gg.vape.utils.render.RenderUtil;
import gg.vape.utils.render.RenderUtils;
import gg.vape.value.BooleanValue;
import gg.vape.value.LimitValue;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.ActiveRenderInfo;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityItem;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.FontRenderer;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.GuiScreen;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Matrix4f;
import gg.vape.wrapper.impl.MatrixStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Quaternion;
import gg.vape.wrapper.impl.RenderHelper;
import gg.vape.wrapper.impl.RenderItemFontBridge;
import gg.vape.wrapper.impl.RenderManager;
import gg.vape.wrapper.impl.ScorePlayerTeamTextComponent;
import gg.vape.wrapper.impl.SharedMonsterAttributes;
import gg.vape.wrapper.impl.Tessellator;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class ItemESP
extends Mod {
    private final Color p;
    private final LimitValue A;
    private final List<ItemESPGroup> I;
    private final BooleanValue Z;
    private final NumberValue v;
    private final BooleanValue s;
    private final BooleanValue D;
    private final BooleanValue k = BooleanValue.create(this, "Distance", false, "Shows the distance of the item.");

    @EventHandler
    public void onRender3D(EventRender3D eventRender3D) {
        if (Minecraft.theWorld().isNull()) {
            return;
        }
        double d = RenderManager.getInterpolatedRenderPosX();
        double d2 = RenderManager.getInterpolatedRenderPosY();
        double d3 = RenderManager.getInterpolatedRenderPosZ();
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().B(1.0);
        RenderHelper.e();
        GlStateManager.disableLighting();
        GlStateManager.depthMask(false);
        GlStateManager.disableDepth();
        boolean bl = OpenGlBackendHolder.d.L(3042);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.disableLighting();
        for (ItemESPGroup itemESPGroup : this.I) {
            if (itemESPGroup.e()) continue;
            double d4 = itemESPGroup.e + (itemESPGroup.U - itemESPGroup.e) * (double)eventRender3D.getTicks() - d;
            double d5 = itemESPGroup.G + (itemESPGroup.y - itemESPGroup.G) * (double)eventRender3D.getTicks() - d2;
            double d6 = itemESPGroup.n + (itemESPGroup.X - itemESPGroup.n) * (double)eventRender3D.getTicks() - d3;
            this.U(entityPlayerSP, itemESPGroup, d4, d5, d6, ForgeVersion.MC_1_16_5.d() ? eventRender3D.getMatrixStack() : null);
        }
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        GlStateManager.depthMask(true);
        GlStateManager.enableLighting();
        if (!bl) {
            GlStateManager.disableBlend();
        }
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        RenderHelper.s();
        Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().O(1.0);
    }

    private void c(EntityPlayerSP entityPlayerSP) {
        for (int i = 0; i < this.I.size(); ++i) {
            for (int j = i + 1; j < this.I.size(); ++j) {
                ItemESPGroup itemESPGroup = this.I.get(i);
                ItemESPGroup itemESPGroup2 = this.I.get(j);
                EntityItem entityItem = new EntityItem(itemESPGroup.g());
                EntityItem entityItem2 = new EntityItem(itemESPGroup2.g());
                double d = entityPlayerSP.getDistanceToEntity(entityItem);
                double d2 = entityPlayerSP.getDistanceToEntity(entityItem2);
                double d3 = (d + d2) / 2.0;
                double d4 = Math.max(1.5, d3 / 5.0);
                double d5 = itemESPGroup.U - itemESPGroup2.U;
                double d6 = itemESPGroup.y - itemESPGroup2.y;
                double d7 = itemESPGroup.X - itemESPGroup2.X;
                double d8 = Math.sqrt(d5 * d5 + d6 * d6 + d7 * d7);
                if (!(d8 <= d4)) continue;
                itemESPGroup.k(itemESPGroup2);
                this.I.remove(j);
                --j;
            }
        }
    }

    public ItemESP() {
        super("ItemESP", 48779, Category.k, "Renders tags on dropped items.");
        this.D = BooleanValue.create(this, "Group Items", false, "Groups items into easier to read tags.");
        this.Z = BooleanValue.create(this, "Auto Scale", true, "Automatically scales up tags\nthe further the distance.");
        this.s = BooleanValue.create(this, "Whitelist Only", false, "Only renders whitelisted items.");
        this.A = LimitValue.n(this, "itemesp-alloweditems", "Allowed Items", LimitValue.r, Collections.emptyList());
        this.v = NumberValue.create((Object)this, "Scale", "#.#", "", 0.1, 1.0, 1.5, 0.1);
        this.p = new Color(20, 20, 20, 64);
        this.I = new ArrayList<ItemESPGroup>();
        this.D.B(this::lambda$new$0);
        this.s.K(this.A);
        this.addValue(this.k, this.D, this.Z, this.v, this.s, this.A);
    }

    public static BooleanValue i(ItemESP itemESP) {
        return itemESP.D;
    }

    private void U(EntityPlayerSP entityPlayerSP, ItemESPGroup itemESPGroup, double d, double d2, double d3, MatrixStack matrixStack) {
        float f;
        float f2;
        Object object;
        int n;
        EntityItem entityItem = new EntityItem(itemESPGroup.g());
        List<ItemStack> list = itemESPGroup.p();
        int n2 = 0;
        String[] stringArray = new String[list.size()];
        FontRenderer fontRenderer = Minecraft.getFontRenderer();
        for (n = 0; n < list.size(); ++n) {
            object = list.get(n);
            String string = ((ItemStack)object).x();
            if (this.k.L().booleanValue() && n == 0) {
                string = ClientSettings.F + "a[" + ClientSettings.F + "f" + (int)entityPlayerSP.getDistanceToEntity(entityItem) + ClientSettings.F + "a]" + ClientSettings.F + "r " + string;
            }
            if (((ItemStack)object).P() > 1 && ((ItemStack)object).t() > 1) {
                string = string + ClientSettings.F + "r x" + ((ItemStack)object).t();
            }
            n2 = Math.max(fontRenderer.getStringWidth(string) / 2, n2);
            stringArray[n] = string;
        }
        n = -1;
        object = this.p;
        float f3 = (float)(0.03333335 * (Double)this.v.K());
        if (this.Z.L().booleanValue()) {
            f2 = entityPlayerSP.getDistanceToEntity(entityItem);
            f = f2 / 5.0f <= 2.0f ? 2.0f : f2 / 5.0f;
            f3 = (float)(0.01666666753590107 * ((double)f * (Double)this.v.K()));
        }
        RenderUtil.d();
        f2 = FreeLookHudModule.z() ? FreeLookHudModule.w$src$F$1kb9hl5() : Minecraft.D().getPlayerViewX();
        float f4 = f = FreeLookHudModule.z() ? FreeLookHudModule.c() : Minecraft.D().getPlayerViewY();
        if (ForgeVersion.MC_1_16_5.d()) {
            if (Minecraft.gameSettings().x() == 0) {
                OpenGlBackendHolder.d.I(d + 0.0, d2 + 0.25 + 0.5, d3);
                OpenGlBackendHolder.d.F(0.0f, 1.0f, 0.0f);
                OpenGlBackendHolder.d.X(-f2, 0.0f, 1.0f, 0.0f);
                OpenGlBackendHolder.d.X(-f, -1.0f, 0.0f, 0.0f);
            } else {
                ActiveRenderInfo activeRenderInfo = Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().l();
                double d4 = GuiRenderPrimitives.d() ? 0.0 : RenderManager.getInterpolatedRenderPosX() - activeRenderInfo.o().getX();
                double d5 = GuiRenderPrimitives.d() ? 0.0 : RenderManager.getInterpolatedRenderPosY() - activeRenderInfo.o().getY();
                double d6 = GuiRenderPrimitives.d() ? 0.0 : RenderManager.getInterpolatedRenderPosZ() - activeRenderInfo.o().getZ();
                OpenGlBackendHolder.d.I(d + d4, d2 + d5 + 0.25 + 0.5, d3 + d6);
                OpenGlBackendHolder.d.F(0.0f, 1.0f, 0.0f);
                OpenGlBackendHolder.d.X(-f2, 0.0f, 1.0f, 0.0f);
                OpenGlBackendHolder.d.X(f, 1.0f, 0.0f, 0.0f);
            }
        } else {
            OpenGlBackendHolder.d.I(d + 0.0, d2 + 0.25 + 0.5, d3);
            OpenGlBackendHolder.d.F(0.0f, 1.0f, 0.0f);
            if (Minecraft.gameSettings().x() == 2) {
                OpenGlBackendHolder.d.X(-f2, 0.0f, 1.0f, 0.0f);
                OpenGlBackendHolder.d.X(f, -1.0f, 0.0f, 0.0f);
            } else {
                OpenGlBackendHolder.d.X(-f2, 0.0f, 1.0f, 0.0f);
                OpenGlBackendHolder.d.X(f, 1.0f, 0.0f, 0.0f);
            }
        }
        OpenGlBackendHolder.d.H(-f3, -f3, f3);
        int n3 = fontRenderer.getFontHeight();
        int n4 = n2;
        int n5 = -(n3 * stringArray.length - 1);
        RenderUtils.M((double)(-n4) - 2.0, n5, (double)n4 + 2.0, 2.0, 0.0, (Color)object, (Color)object);
        GlStateManager.enableTexture2D();
        GlStateManager.disableDepth();
        OpenGlBackendHolder.d.E(1.0f, 1.0f, 1.0f);
        Object var25_23 = null;
        if (ForgeVersion.MC_1_16_5.d() && (matrixStack == null || matrixStack.isNull() || !matrixStack.isInstance(MappedClasses.DQ))) {
            matrixStack = MatrixStack.A();
        }
        int n6 = n5 + 2;
        for (int i = 0; i < stringArray.length; ++i) {
            RenderItemFontBridge renderItemFontBridge;
            Object object2;
            String string = stringArray[i];
            int n7 = fontRenderer.getStringWidth(string) / 2;
            int n8 = -(fontRenderer.FONT_HEIGHT(string) - 1);
            if (GuiRenderPrimitives.d()) {
                object2 = new RenderMatrix4f().b();
                ((RenderMatrix4f)object2).u(BufferedGuiRenderPrimitives.l);
                ((RenderMatrix4f)object2).u(BufferedGuiRenderPrimitives.X.c());
                if (ForgeVersion.MC_1_20_6.d()) {
                    fontRenderer.h(string, -n7, n6, n, false, (RenderMatrix4f)object2, SharedMonsterAttributes.V());
                } else {
                    int n9 = 0xF000F0;
                    renderItemFontBridge = Minecraft.H$src$Lgg_vape_wrapper_impl_VoxelShape_$1dlcquv().getBoundingBox();
                    ScorePlayerTeamTextComponent scorePlayerTeamTextComponent = ScorePlayerTeamTextComponent.B(string);
                    fontRenderer.Z(scorePlayerTeamTextComponent, -n7, n6, n, false, ((RenderMatrix4f)object2).u(), renderItemFontBridge, true, 0, n9);
                    renderItemFontBridge.q();
                }
            } else if (ForgeVersion.MC_1_16_5.d()) {
                matrixStack.H();
                object2 = Minecraft.D().getCameraOrientation();
                matrixStack.i((Quaternion)object2);
                matrixStack.i(Quaternion.K(180.0f, 0.0f, 180.0f, true));
                ScorePlayerTeamTextComponent scorePlayerTeamTextComponent = ScorePlayerTeamTextComponent.B(string);
                renderItemFontBridge = RenderItemFontBridge.V(Tessellator.getInstance().getWorldRenderer());
                int n10 = 0xF000F0;
                Matrix4f matrix4f = matrixStack.F().u();
                fontRenderer.Z(scorePlayerTeamTextComponent, -n7, n6, n, false, matrix4f, renderItemFontBridge, true, 0, n10);
                renderItemFontBridge.q();
                matrixStack.U();
            } else {
                fontRenderer.drawString(string, (double)(-n7), (double)n6, n);
            }
            n6 += n3;
        }
        RenderUtil.Y();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @EventHandler
    public void r(EventGuiOpen eventGuiOpen) {
        GuiScreen guiScreen = eventGuiOpen.getGuiScreen();
        if (guiScreen.isInstance(MappedClasses.u5) || guiScreen.isInstance(MappedClasses.D6) || guiScreen.isInstance(MappedClasses.F_)) {
            this.I.clear();
        }
    }

    @EventHandler
    public void C(EventPostTick eventPostTick) {
        if (Minecraft.thePlayer().isNull() || Minecraft.theWorld().isNull()) {
            this.I.clear();
            return;
        }
        List<Object> worldEntities = Minecraft.theWorld().z();
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (!this.D.L().booleanValue()) {
            this.I.clear();
            for (Object e : worldEntities) {
                Entity entity = new Entity(e);
                if (!entity.isInstance(MappedClasses.zW)) continue;
                EntityItem entityItem = new EntityItem(entity);
                if (this.s.L().booleanValue() && !this.A.A(entityItem.J$src$Lgg_vape_wrapper_impl_ItemStack_$5gv0ko())) continue;
                this.I.add(new ItemESPGroup(this, entityItem));
            }
            return;
        }
        Iterator<ItemESPGroup> groupIterator = this.I.iterator();
        while (groupIterator.hasNext()) {
            ItemESPGroup group = groupIterator.next();
            group.x(worldEntities, entityPlayerSP);
            if (!group.e()) continue;
            groupIterator.remove();
        }
        this.c(entityPlayerSP);
        HashSet<Object> groupedEntities = new HashSet<Object>();
        for (ItemESPGroup itemESPGroup : this.I) {
            groupedEntities.addAll(itemESPGroup.I());
        }
        for (Object entityObject : worldEntities) {
            Entity entity = new Entity(entityObject);
            if (!entity.isInstance(MappedClasses.zW)) continue;
            EntityItem entityItem = new EntityItem(entity);
            if (this.s.L().booleanValue() && !this.A.A(entityItem.J$src$Lgg_vape_wrapper_impl_ItemStack_$5gv0ko()) || groupedEntities.contains(entityItem.getObject())) continue;
            double d = entityPlayerSP.getDistanceToEntity(entityItem);
            double d2 = Math.max(1.5, d / 5.0);
            boolean bl = false;
            double d3 = entityItem.z();
            double d4 = entityItem.N();
            double d5 = entityItem.h();
            for (ItemESPGroup itemESPGroup2 : this.I) {
                double d6 = d3 - itemESPGroup2.U;
                double d7 = d4 - itemESPGroup2.y;
                double d8 = d5 - itemESPGroup2.X;
                double d9 = Math.sqrt(d6 * d6 + d7 * d7 + d8 * d8);
                if (!(d9 <= d2)) continue;
                itemESPGroup2.F(entityItem);
                groupedEntities.add(entityItem.getObject());
                bl = true;
                break;
            }
            if (bl) continue;
            ItemESPGroup itemESPGroup3 = new ItemESPGroup(this, entityItem);
            this.I.add(itemESPGroup3);
            groupedEntities.add(entityItem.getObject());
        }
    }

    private void lambda$new$0(BooleanValue booleanValue) {
        this.I.clear();
    }
}
