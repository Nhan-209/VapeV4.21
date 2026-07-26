package gg.vape.module.render;

import gg.vape.module.render.NameTagsFramebufferState;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.EnchantmentUtil;
import gg.vape.utils.ItemStackFingerprint;
import gg.vape.utils.render.BufferedRenderPrimitives;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ItemStackRenderUtils;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderBatchManager;
import gg.vape.utils.render.RenderUtils;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.FontRenderer;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.MatrixStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Quaternion;
import gg.vape.wrapper.impl.RenderItem;
import gg.vape.wrapper.impl.RenderItemFontBridge;
import gg.vape.wrapper.impl.RenderItemTextBridge;
import gg.vape.wrapper.impl.RenderManager;
import gg.vape.wrapper.impl.ScorePlayerTeamTextComponent;
import gg.vape.wrapper.impl.SharedMonsterAttributes;
import gg.vape.wrapper.impl.TagCompound;
import gg.vape.wrapper.impl.Tessellator;
import java.awt.Color;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

public class NameTagsNameState {
    private final double c;
    private final long V;
    private static final String d = "ench";
    private final double b;
    private final NameTagsFramebufferState P;

    public double n() {
        return this.b;
    }

    private static void t(FontRenderer fontRenderer, String string, int n, int n2, double d, double d2) {
        int n3 = ((int)d2 & 0xFF) << 24 | 0xFFFFFF;
        OpenGlBackendHolder.d.m();
        OpenGlBackendHolder.d.G(d, d, d);
        OpenGlBackendHolder.d.P(0.0f, 0.0f, 2000.0f);
        fontRenderer.drawStringWithShadow(string, (double)n, (double)n2, n3);
        OpenGlBackendHolder.d.F();
    }

    public NameTagsNameState(long l, NameTagsFramebufferState nameTagsFramebufferState, double d, double d2) {
        this.V = l;
        this.P = nameTagsFramebufferState;
        this.c = d;
        this.b = d2;
    }

    public void M(double d, double d2, int n, double[] dArray, @Nullable MatrixStack matrixStack, RenderManager renderManager, boolean bl) {
        int n2;
        if (this.P == null || !this.P.R()) {
            return;
        }
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.enableAlpha();
        GuiRenderPrimitives.S(d, d2, this.c, this.b + 2.0, this.P.m(), bl);
        if (n != 0) {
            int n3 = -1;
            if (ForgeVersion.MC_1_16_5.d()) {
                if (matrixStack == null || matrixStack.isNull()) {
                    matrixStack = MatrixStack.A();
                }
                matrixStack.H();
                Quaternion quaternion = renderManager.getCameraOrientation();
                matrixStack.i(quaternion);
                matrixStack.i(Quaternion.K(180.0f, 0.0f, 180.0f, true));
                n2 = 0xF000F0;
                if (ForgeVersion.MC_1_20_6.d()) {
                    Minecraft.getFontRenderer().B(n + "", (float)(d + 7.0), (float)(d2 + 9.0), n3, true, null, SharedMonsterAttributes.V(), null);
                } else {
                    ScorePlayerTeamTextComponent scorePlayerTeamTextComponent = ScorePlayerTeamTextComponent.B(n + "");
                    RenderItemFontBridge renderItemFontBridge = RenderItemFontBridge.V(Tessellator.getInstance().getWorldRenderer());
                    Minecraft.getFontRenderer().Z(scorePlayerTeamTextComponent, (float)(d + 7.0), (float)(d2 + 9.0), n3, true, matrixStack.F().u(), renderItemFontBridge, true, 0, n2);
                    renderItemFontBridge.q();
                }
                matrixStack.U();
            } else {
                Minecraft.getFontRenderer().drawStringWithShadow(n + "", d + 6.0, d2 + 8.0, n3);
            }
        }
        double d3 = 0.0;
        for (n2 = 0; n2 < dArray.length; ++n2) {
            double d4;
            double d5 = dArray[n2];
            if (d5 > 0.0 && d5 < 1.0 && (d4 = 1.0 - d5) < 1.0) {
                GuiRenderPrimitives.U = false;
                int n4 = (int)Math.round(255.0 - d5 * 255.0);
                if (GuiRenderPrimitives.d()) {
                    BufferedRenderPrimitives.A(d + 2.0 + d3, d2 + 13.0, 13.0, 2.0, Color.BLACK);
                    BufferedRenderPrimitives.A(d + 2.0 + d3, d2 + 13.0, 12.0, 1.0, new Color((255 - n4) / 4, 64, 0, 255));
                    BufferedRenderPrimitives.A(d + 2.0 + d3, d2 + 13.0, 13.0 * d4, 1.0, RenderUtils.S((float)d4));
                } else {
                    GuiRenderPrimitives.C(d + 2.0 + d3, d2 + 13.0, 13.0, 2.0, Color.BLACK);
                    GuiRenderPrimitives.C(d + 2.0 + d3, d2 + 13.0, 12.0, 1.0, new Color((255 - n4) / 4, 64, 0, 255));
                    GuiRenderPrimitives.C(d + 2.0 + d3, d2 + 13.0, 13.0 * d4, 1.0, RenderUtils.S((float)d4));
                }
                GuiRenderPrimitives.U = true;
            }
            d3 += 18.0;
        }
    }

    private static void W(ItemStack itemStack, float f, float f2) {
        if (GuiRenderPrimitives.d()) {
            if (ForgeVersion.MC_1_21_10.d()) {
                GuiRenderPrimitives.g(itemStack, 1.0, f, -2.0, true);
                return;
            }
            boolean bl = GL11.glIsEnabled((int)2929);
            boolean bl2 = GL11.glIsEnabled((int)3042);
            if (!bl) {
                OpenGlBackendHolder.d.l(2929);
            }
            if (!bl2) {
                OpenGlBackendHolder.d.l(3042);
            }
            MatrixStack matrixStack = MatrixStack.A();
            matrixStack.H();
            float f3 = (float)Minecraft.p().k(Minecraft.gameSettings().T(), false) / 2.0f;
            float f4 = 1.0f / f3;
            matrixStack.S(f4, f4, f4);
            matrixStack.S((float)Minecraft.J() / f2 / 2.0f, (float)Minecraft.h() / 36.0f, 0.0f);
            if (ForgeVersion.MC_1_20_6.d()) {
                ItemStackRenderUtils.O(RenderItemTextBridge.t(matrixStack), itemStack, (int)f, 0);
            } else {
                RenderItem renderItem = Minecraft.v();
                renderItem.a(itemStack, (int)f, -2, matrixStack);
            }
            if (!bl) {
                OpenGlBackendHolder.d.u$src$V$hntn98(2929);
            }
            if (!bl2) {
                OpenGlBackendHolder.d.u$src$V$hntn98(3042);
            }
            return;
        }
        GuiRenderPrimitives.g(itemStack, 1.0, f, -2.0, true);
    }

    public static NameTagsNameState T(EntityPlayer entityPlayer) {
        long l = ItemStackFingerprint.T(entityPlayer);
        ItemStack[] itemStackArray = ItemStackFingerprint.T$src$ALgg_vape_wrapper_impl_ItemStack_$f6ukg1(entityPlayer);
        int n = 0;
        for (ItemStack itemStackArray2 : itemStackArray) {
            if (itemStackArray2 == null) continue;
            n += 18;
        }
        GuiRenderPrimitives.Y();
        NameTagsFramebufferState nameTagsFramebufferState = new NameTagsFramebufferState(0, -2, n -= 2, 18);
        if (GuiRenderPrimitives.d()) {
            RenderBatchManager.M().G(0.0f);
        }
        nameTagsFramebufferState.N();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        FontRenderer fontRenderer = Minecraft.getFontRenderer();
        int n2 = 0;
        for (ItemStack itemStack : itemStackArray) {
            TagCompound tagCompound;
            if (itemStack == null) continue;
            ItemStack itemStack2 = itemStack.k();
            if (!ForgeVersion.MC_1_20_6.d() && (tagCompound = new TagCompound(itemStack2.l())).isNotNull()) {
                tagCompound.getTagMap().remove(d);
            }
            NameTagsNameState.W(itemStack2, n2, n);
            int tagCompound2 = 0;
            double d = 0.7;
            double d2 = 1.0 / d;
            for (String string : EnchantmentUtil.E(itemStack)) {
                NameTagsNameState.t(fontRenderer, string, (int)((double)n2 * d2), (int)((double)tagCompound2 * d2) - 2, d, 1.0);
                tagCompound2 += 6;
            }
            n2 += 18;
        }
        nameTagsFramebufferState.V();
        GuiRenderPrimitives.D();
        return new NameTagsNameState(l, nameTagsFramebufferState, n, 16.0);
    }

    public double t() {
        return this.c;
    }

    public NameTagsFramebufferState d() {
        return this.P;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

