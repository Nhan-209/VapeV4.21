package gg.vape.utils.render;

import gg.vape.Vape;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.render.hud.FreeLookHudModule;
import gg.vape.runtime.NativeBridge;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import gg.vape.unmap.GLUtils;
import gg.vape.utils.datas.HSBAData;
import gg.vape.utils.datas.HSBData;
import gg.vape.utils.datas.SearchResultData;
import gg.vape.utils.render.BufferedGuiRenderPrimitives;
import gg.vape.utils.render.BufferedRenderPrimitives;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderMatrix4f;
import gg.vape.utils.render.RenderUtils;
import gg.vape.utils.render.RenderVector4f;
import gg.vape.wrapper.impl.ActiveRenderInfo;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.FontRenderer;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.Matrix4f;
import gg.vape.wrapper.impl.MatrixStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Quaternion;
import gg.vape.wrapper.impl.RenderItemFontBridge;
import gg.vape.wrapper.impl.RenderManager;
import gg.vape.wrapper.impl.ScorePlayerTeamTextComponent;
import gg.vape.wrapper.impl.SharedMonsterAttributes;
import gg.vape.wrapper.impl.Tessellator;
import java.awt.Color;
import java.lang.invoke.MethodHandles;
import org.lwjgl.opengl.GL11;

public class RenderUtil {
    private static final GLUtils x;
    private static final GLUtils K;
    private static final long a;
    private static final GLUtils y;
    private static final GLUtils p;

    public static void m(SearchResultData searchResultData, double d, double d2, double d3) {
        RenderUtil.u(searchResultData.F, searchResultData.O, searchResultData.D, 1.0, 1.0, 1.0, 0.1, searchResultData.O(), null, d, d2, d3);
    }

    static {
        long l = a = ZkmLongKeyState.a(6292540316681502535L, -7493871292502627190L, MethodHandles.lookup().lookupClass()).a(197878076146852L);
        p = Vape.INSTANCE.getGlUtils();
        K = new GLUtils();
        K.initializeVertexBuffer(48, 7);
        x = new GLUtils();
        x.initializeVertexBuffer(24, 1);
        y = new GLUtils();
        y.initializeVertexBuffer(36, 4);
    }

    public static void L(double d, double d2, double d3, HSBData hSBData) {
        float f = 0.0f;
        if (hSBData instanceof HSBAData) {
            HSBAData hSBAData = (HSBAData)hSBData;
            f = hSBAData.n();
        }
        RenderUtil.q(d, d2, d3, hSBData.F, hSBData.O, hSBData.D, new Color(255 - hSBData.U.getRed(), 255 - hSBData.U.getGreen(), 255 - hSBData.U.getBlue(), hSBData.U.getAlpha()), hSBData.U, f);
    }

    public static void D(HSBData hSBData, double d, double d2, double d3) {
        RenderUtil.u(hSBData.F, hSBData.O, hSBData.D, 1.0, 1.0, 1.0, 0.1, hSBData.U, null, d, d2, d3);
    }

    public static void d() {
        OpenGlBackendHolder.d.m();
        if (ForgeVersion.MC_1_16_5.d()) {
            RenderUtil.p();
        }
    }

    public static void q(double d, double d2, double d3, double d4, double d5, double d6, Color color, Color color2, float f) {
        if (GuiRenderPrimitives.d()) {
            double d7 = d4 + 1.0 - d;
            double d8 = d4 - d;
            double d9 = d5 + 1.0 - d2;
            double d10 = d5 - d2;
            double d11 = d6 + 1.0 - d3;
            double d12 = d6 - d3;
            BufferedRenderPrimitives.z(d8, d10, d12, d8, d9, d12, d7, d10, d12, d7, d9, d12, color2);
            BufferedRenderPrimitives.z(d7, d10, d11, d7, d9, d11, d8, d10, d11, d8, d9, d11, color2);
            BufferedRenderPrimitives.z(d7, d9, d12, d7, d10, d12, d8, d9, d12, d8, d10, d12, color2);
            BufferedRenderPrimitives.z(d8, d9, d11, d8, d10, d11, d7, d9, d11, d7, d10, d11, color2);
            BufferedRenderPrimitives.z(d8, d9, d12, d7, d9, d12, d7, d9, d11, d8, d9, d11, color2);
            BufferedRenderPrimitives.z(d8, d9, d12, d8, d9, d11, d7, d9, d11, d7, d9, d12, color2);
            BufferedRenderPrimitives.z(d8, d10, d12, d7, d10, d12, d7, d10, d11, d8, d10, d11, color2);
            BufferedRenderPrimitives.z(d8, d10, d12, d8, d10, d11, d7, d10, d11, d7, d10, d12, color2);
            BufferedRenderPrimitives.z(d8, d10, d12, d8, d9, d12, d8, d10, d11, d8, d9, d11, color2);
            BufferedRenderPrimitives.z(d7, d10, d11, d7, d9, d11, d7, d10, d12, d7, d9, d12, color2);
            BufferedRenderPrimitives.z(d8, d9, d11, d8, d10, d11, d8, d9, d12, d8, d10, d12, color2);
            BufferedRenderPrimitives.z(d7, d9, d12, d7, d10, d12, d7, d9, d11, d7, d10, d11, color2);
            if ((double)f > 0.1) {
                BufferedRenderPrimitives.Q(d8, d10, d12, d8, d9, d12, 3.0f, color);
                BufferedRenderPrimitives.Q(d7, d10, d12, d7, d9, d12, 3.0f, color);
                BufferedRenderPrimitives.Q(d8, d10, d11, d8, d9, d11, 3.0f, color);
                BufferedRenderPrimitives.Q(d7, d10, d11, d7, d9, d11, 3.0f, color);
                BufferedRenderPrimitives.Q(d7, d10, d12, d7, d10, d11, 3.0f, color);
                BufferedRenderPrimitives.Q(d7, d9, d12, d7, d9, d11, 3.0f, color);
                BufferedRenderPrimitives.Q(d8, d10, d12, d8, d10, d11, 3.0f, color);
                BufferedRenderPrimitives.Q(d8, d9, d12, d8, d9, d11, 3.0f, color);
                BufferedRenderPrimitives.Q(d8, d10, d12, d7, d10, d12, 3.0f, color);
                BufferedRenderPrimitives.Q(d8, d9, d12, d7, d9, d12, 3.0f, color);
                BufferedRenderPrimitives.Q(d8, d10, d11, d7, d10, d11, 3.0f, color);
                BufferedRenderPrimitives.Q(d8, d9, d11, d7, d9, d11, 3.0f, color);
            }
            return;
        }
        GL11.glColor4d((double)((float)color2.getRed() / 255.0f), (double)((float)color2.getGreen() / 255.0f), (double)((float)color2.getBlue() / 255.0f), (double)((float)color2.getAlpha() / 255.0f));
        K.reset();
        double d13 = d4 + 1.0 - d;
        double d14 = d4 - d;
        double d15 = d5 + 1.0 - d2;
        double d16 = d5 - d2;
        double d17 = d6 + 1.0 - d3;
        double d18 = d6 - d3;
        K.addVertex(d14, d16, d18);
        K.addVertex(d14, d15, d18);
        K.addVertex(d13, d16, d18);
        K.addVertex(d13, d15, d18);
        K.addVertex(d13, d16, d17);
        K.addVertex(d13, d15, d17);
        K.addVertex(d14, d16, d17);
        K.addVertex(d14, d15, d17);
        K.addVertex(d13, d15, d18);
        K.addVertex(d13, d16, d18);
        K.addVertex(d14, d15, d18);
        K.addVertex(d14, d16, d18);
        K.addVertex(d14, d15, d17);
        K.addVertex(d14, d16, d17);
        K.addVertex(d13, d15, d17);
        K.addVertex(d13, d16, d17);
        K.addVertex(d14, d15, d18);
        K.addVertex(d13, d15, d18);
        K.addVertex(d13, d15, d17);
        K.addVertex(d14, d15, d17);
        K.addVertex(d14, d15, d18);
        K.addVertex(d14, d15, d17);
        K.addVertex(d13, d15, d17);
        K.addVertex(d13, d15, d18);
        K.addVertex(d14, d16, d18);
        K.addVertex(d13, d16, d18);
        K.addVertex(d13, d16, d17);
        K.addVertex(d14, d16, d17);
        K.addVertex(d14, d16, d18);
        K.addVertex(d14, d16, d17);
        K.addVertex(d13, d16, d17);
        K.addVertex(d13, d16, d18);
        K.addVertex(d14, d16, d18);
        K.addVertex(d14, d15, d18);
        K.addVertex(d14, d16, d17);
        K.addVertex(d14, d15, d17);
        K.addVertex(d13, d16, d17);
        K.addVertex(d13, d15, d17);
        K.addVertex(d13, d16, d18);
        K.addVertex(d13, d15, d18);
        K.addVertex(d14, d15, d17);
        K.addVertex(d14, d16, d17);
        K.addVertex(d14, d15, d18);
        K.addVertex(d14, d16, d18);
        K.addVertex(d13, d15, d18);
        K.addVertex(d13, d16, d18);
        K.addVertex(d13, d15, d17);
        K.addVertex(d13, d16, d17);
        K.uploadAndDraw();
        if (f > 0.0f) {
            GL11.glColor4d((double)((double)color.getRed() / 255.0), (double)((double)color.getGreen() / 255.0), (double)((double)color.getBlue() / 255.0), (double)0.7);
            GL11.glLineWidth((float)3.0f);
            p.reset();
            p.addVertex(d14, d16, d18);
            p.addVertex(d14, d15, d18);
            p.addVertex(d13, d16, d18);
            p.addVertex(d13, d15, d18);
            p.addVertex(d14, d16, d17);
            p.addVertex(d14, d15, d17);
            p.addVertex(d13, d16, d17);
            p.addVertex(d13, d15, d17);
            p.addVertex(d13, d16, d18);
            p.addVertex(d13, d16, d17);
            p.addVertex(d13, d15, d18);
            p.addVertex(d13, d15, d17);
            p.addVertex(d14, d16, d18);
            p.addVertex(d14, d16, d17);
            p.addVertex(d14, d15, d18);
            p.addVertex(d14, d15, d17);
            p.addVertex(d14, d16, d18);
            p.addVertex(d13, d16, d18);
            p.addVertex(d14, d15, d18);
            p.addVertex(d13, d15, d18);
            p.addVertex(d14, d16, d17);
            p.addVertex(d13, d16, d17);
            p.addVertex(d14, d15, d17);
            p.addVertex(d13, d15, d17);
            p.uploadAndDraw();
        }
    }

    public static void x(double d, double d2, double d3, double d4, double d5, double d6, Color color, double d7, double d8, double d9) {
        double d10 = d + d4 - d7;
        double d11 = d - d7;
        double d12 = d2 + d5 - d8;
        double d13 = d2 - d8;
        double d14 = d3 + d6 - d9;
        double d15 = d3 - d9;
        y.addVertex(d11, d13, d15);
        y.addVertex(d10, d13, d15);
        y.addVertex(d11, d12, d15);
        y.addVertex(d10, d13, d15);
        y.addVertex(d10, d12, d15);
        y.addVertex(d11, d12, d15);
        y.addVertex(d11, d13, d14);
        y.addVertex(d11, d12, d14);
        y.addVertex(d10, d13, d14);
        y.addVertex(d10, d13, d14);
        y.addVertex(d11, d12, d14);
        y.addVertex(d10, d12, d14);
        y.addVertex(d11, d13, d15);
        y.addVertex(d11, d12, d14);
        y.addVertex(d11, d13, d14);
        y.addVertex(d11, d13, d15);
        y.addVertex(d11, d12, d15);
        y.addVertex(d11, d12, d14);
        y.addVertex(d10, d13, d15);
        y.addVertex(d10, d13, d14);
        y.addVertex(d10, d12, d14);
        y.addVertex(d10, d13, d15);
        y.addVertex(d10, d12, d14);
        y.addVertex(d10, d12, d15);
        y.addVertex(d11, d12, d15);
        y.addVertex(d10, d12, d14);
        y.addVertex(d11, d12, d14);
        y.addVertex(d11, d12, d15);
        y.addVertex(d10, d12, d15);
        y.addVertex(d10, d12, d14);
        y.addVertex(d11, d13, d15);
        y.addVertex(d11, d13, d14);
        y.addVertex(d10, d13, d14);
        y.addVertex(d11, d13, d15);
        y.addVertex(d10, d13, d14);
        y.addVertex(d10, d13, d15);
        if (color != null) {
            GL11.glColor4d((double)((float)color.getRed() / 255.0f), (double)((float)color.getGreen() / 255.0f), (double)((float)color.getBlue() / 255.0f), (double)((float)color.getAlpha() / 255.0f));
            y.uploadAndDraw();
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static Color N(Color color) {
        long l = a ^ 0x615F387CD666L;
        int n = color.getRGB();
        n = 0xFFFFFF - n;
        return new Color(n);
    }

    public static void U(String string, double d, double d2, double d3, double d4, double d5, int n, Color color, double d6, MatrixStack matrixStack) {
        float f;
        float f2;
        long l = a ^ 0x1F04EE1C5E66L;
        GuiRenderPrimitives.U = true;
        FontRenderer fontRenderer = Minecraft.getFontRenderer();
        int n2 = n;
        float f3 = d5 / 5.0 <= 2.0 ? 2.0f : (float)(d5 / 5.0);
        float f4 = f2 = (float)(0.01666666753590107 * ((double)f3 * d4));
        RenderUtil.d();
        float f5 = FreeLookHudModule.z() ? FreeLookHudModule.w$src$F$1kb9hl5() : Minecraft.D().getPlayerViewX();
        float f6 = f = FreeLookHudModule.z() ? FreeLookHudModule.c() : Minecraft.D().getPlayerViewY();
        if (ForgeVersion.MC_1_16_5.d()) {
            if (Minecraft.gameSettings().x() == 0) {
                OpenGlBackendHolder.d.I(d + 0.0, d2 + 0.25 + 0.5, d3);
                OpenGlBackendHolder.d.F(0.0f, 1.0f, 0.0f);
                OpenGlBackendHolder.d.X(-f5, 0.0f, 1.0f, 0.0f);
                OpenGlBackendHolder.d.X(-f, -1.0f, 0.0f, 0.0f);
            } else {
                ActiveRenderInfo activeRenderInfo = Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().l();
                double d7 = GuiRenderPrimitives.d() ? 0.0 : RenderManager.getInterpolatedRenderPosX() - activeRenderInfo.o().getX();
                double d8 = GuiRenderPrimitives.d() ? 0.0 : RenderManager.getInterpolatedRenderPosY() - activeRenderInfo.o().getY();
                double d9 = GuiRenderPrimitives.d() ? 0.0 : RenderManager.getInterpolatedRenderPosZ() - activeRenderInfo.o().getZ();
                OpenGlBackendHolder.d.I(d + d7, d2 + d8 + 0.25 + 0.5, d3 + d9);
                OpenGlBackendHolder.d.F(0.0f, 1.0f, 0.0f);
                OpenGlBackendHolder.d.X(-f5, 0.0f, 1.0f, 0.0f);
                OpenGlBackendHolder.d.X(f, 1.0f, 0.0f, 0.0f);
            }
        } else {
            OpenGlBackendHolder.d.I(d + 0.0, d2 + 0.25 + 0.5, d3);
            OpenGlBackendHolder.d.F(0.0f, 1.0f, 0.0f);
            if (Minecraft.gameSettings().x() == 2) {
                OpenGlBackendHolder.d.X(-f5, 0.0f, 1.0f, 0.0f);
                OpenGlBackendHolder.d.X(f, -1.0f, 0.0f, 0.0f);
            } else {
                OpenGlBackendHolder.d.X(-f5, 0.0f, 1.0f, 0.0f);
                OpenGlBackendHolder.d.X(f, 1.0f, 0.0f, 0.0f);
            }
        }
        OpenGlBackendHolder.d.H(-f4, -f4, f4);
        int n3 = fontRenderer.getStringWidth(string) / 2;
        int n4 = -(fontRenderer.FONT_HEIGHT(string) - 1);
        int n5 = fontRenderer.getFontHeight();
        int n6 = n3;
        int n7 = -(n5 - 1);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(false);
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.Y();
        GlStateManager.disableTexture2D();
        RenderUtils.M((double)(-n6) - 2.0, n7, (double)n6 + 2.0, 2.0, 0.0, color, color);
        GlStateManager.enableTexture2D();
        GlStateManager.disableDepth();
        OpenGlBackendHolder.d.E(1.0f, 1.0f, 1.0f);
        if (ForgeVersion.MC_1_16_5.d() && (matrixStack == null || matrixStack.isNull() || !matrixStack.isInstance(MappedClasses.DQ))) {
            matrixStack = MatrixStack.A();
        }
        boolean bl = false;
        if (GuiRenderPrimitives.d()) {
            RenderMatrix4f renderMatrix4f = new RenderMatrix4f().b();
            renderMatrix4f.u(BufferedGuiRenderPrimitives.l);
            renderMatrix4f.u(BufferedGuiRenderPrimitives.X.c());
            if (ForgeVersion.MC_1_20_6.d()) {
                fontRenderer.h(string, -n3, -n5 + 3, n2, false, renderMatrix4f, SharedMonsterAttributes.V());
            } else {
                int n8 = 0xF000F0;
                RenderItemFontBridge renderItemFontBridge = Minecraft.H$src$Lgg_vape_wrapper_impl_VoxelShape_$1dlcquv().getBoundingBox();
                ScorePlayerTeamTextComponent scorePlayerTeamTextComponent = ScorePlayerTeamTextComponent.B(string);
                fontRenderer.Z(scorePlayerTeamTextComponent, -n3, -n5 + 3, n2, false, renderMatrix4f.u(), renderItemFontBridge, true, 0, n8);
                renderItemFontBridge.q();
            }
        } else if (ForgeVersion.MC_1_16_5.d()) {
            matrixStack.H();
            bl = true;
            Quaternion quaternion = Minecraft.D().getCameraOrientation();
            matrixStack.i(quaternion);
            matrixStack.i(Quaternion.K(180.0f, 0.0f, 180.0f, true));
            ScorePlayerTeamTextComponent scorePlayerTeamTextComponent = ScorePlayerTeamTextComponent.B(string);
            RenderItemFontBridge renderItemFontBridge = RenderItemFontBridge.V(Tessellator.getInstance().getWorldRenderer());
            int n9 = 0xF000F0;
            Matrix4f matrix4f = matrixStack.F().u();
            fontRenderer.Z(scorePlayerTeamTextComponent, -n3, -n5 + 3, n2, false, matrix4f, renderItemFontBridge, true, 0, n9);
            renderItemFontBridge.q();
        } else {
            fontRenderer.drawString(string, (double)(-n3), (double)(-n5 + 3), n2);
        }
        if (bl) {
            matrixStack.U();
        }
        GlStateManager.enableDepth();
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        RenderUtil.Y();
        GuiRenderPrimitives.U = false;
    }

    public static void u(double d, double d2, double d3, double d4, double d5, double d6, double d7, Color color, Color color2, double d8, double d9, double d10) {
        double d11;
        double d12;
        double d13;
        double d14;
        double d15;
        double d16;
        if (GuiRenderPrimitives.d()) {
            double d17 = d + d4 - d8;
            double d18 = d - d8;
            double d19 = d2 + d5 - d9;
            double d20 = d2 - d9;
            double d21 = d3 + d6 - d10;
            double d22 = d3 - d10;
            if (color2 != null) {
                BufferedRenderPrimitives.W(d18, d20, d22, d17, d19, d21, color2);
            }
            if (color != null) {
                BufferedRenderPrimitives.e(d18, d20, d22, d17, d19, d21, color);
            }
            return;
        }
        if (color2 != null) {
            GL11.glColor4d((double)((float)color2.getRed() / 255.0f), (double)((float)color2.getGreen() / 255.0f), (double)((float)color2.getBlue() / 255.0f), (double)((float)color2.getAlpha() / 255.0f));
            K.reset();
            d16 = d + d4 - d8;
            d15 = d - d8;
            d14 = d2 + d5 - d9;
            d13 = d2 - d9;
            d12 = d3 + d6 - d10;
            d11 = d3 - d10;
            K.addVertex(d15, d13, d11);
            K.addVertex(d15, d14, d11);
            K.addVertex(d16, d13, d11);
            K.addVertex(d16, d14, d11);
            K.addVertex(d16, d13, d12);
            K.addVertex(d16, d14, d12);
            K.addVertex(d15, d13, d12);
            K.addVertex(d15, d14, d12);
            K.addVertex(d16, d14, d11);
            K.addVertex(d16, d13, d11);
            K.addVertex(d15, d14, d11);
            K.addVertex(d15, d13, d11);
            K.addVertex(d15, d14, d12);
            K.addVertex(d15, d13, d12);
            K.addVertex(d16, d14, d12);
            K.addVertex(d16, d13, d12);
            K.addVertex(d15, d14, d11);
            K.addVertex(d16, d14, d11);
            K.addVertex(d16, d14, d12);
            K.addVertex(d15, d14, d12);
            K.addVertex(d15, d14, d11);
            K.addVertex(d15, d14, d12);
            K.addVertex(d16, d14, d12);
            K.addVertex(d16, d14, d11);
            K.addVertex(d15, d13, d11);
            K.addVertex(d16, d13, d11);
            K.addVertex(d16, d13, d12);
            K.addVertex(d15, d13, d12);
            K.addVertex(d15, d13, d11);
            K.addVertex(d15, d13, d12);
            K.addVertex(d16, d13, d12);
            K.addVertex(d16, d13, d11);
            K.addVertex(d15, d13, d11);
            K.addVertex(d15, d14, d11);
            K.addVertex(d15, d13, d12);
            K.addVertex(d15, d14, d12);
            K.addVertex(d16, d13, d12);
            K.addVertex(d16, d14, d12);
            K.addVertex(d16, d13, d11);
            K.addVertex(d16, d14, d11);
            K.addVertex(d15, d14, d12);
            K.addVertex(d15, d13, d12);
            K.addVertex(d15, d14, d11);
            K.addVertex(d15, d13, d11);
            K.addVertex(d16, d14, d11);
            K.addVertex(d16, d13, d11);
            K.addVertex(d16, d14, d12);
            K.addVertex(d16, d13, d12);
            K.uploadAndDraw();
        }
        if (color != null) {
            GL11.glColor4d((double)((float)color.getRed() / 255.0f), (double)((float)color.getGreen() / 255.0f), (double)((float)color.getBlue() / 255.0f), (double)((float)color.getAlpha() / 255.0f));
            x.reset();
            d16 = d + d4 - d8;
            d15 = d - d8;
            d14 = d2 + d5 - d9;
            d13 = d2 - d9;
            d12 = d3 + d6 - d10;
            d11 = d3 - d10;
            x.addVertex(d15, d13, d11);
            x.addVertex(d16, d13, d11);
            x.addVertex(d16, d13, d11);
            x.addVertex(d16, d13, d12);
            x.addVertex(d16, d13, d12);
            x.addVertex(d15, d13, d12);
            x.addVertex(d15, d13, d12);
            x.addVertex(d15, d13, d11);
            x.addVertex(d15, d14, d11);
            x.addVertex(d16, d14, d11);
            x.addVertex(d16, d14, d11);
            x.addVertex(d16, d14, d12);
            x.addVertex(d16, d14, d12);
            x.addVertex(d15, d14, d12);
            x.addVertex(d15, d14, d12);
            x.addVertex(d15, d14, d11);
            x.addVertex(d15, d13, d11);
            x.addVertex(d15, d14, d11);
            x.addVertex(d16, d13, d11);
            x.addVertex(d16, d14, d11);
            x.addVertex(d16, d13, d12);
            x.addVertex(d16, d14, d12);
            x.addVertex(d15, d13, d12);
            x.addVertex(d15, d14, d12);
            x.uploadAndDraw();
        }
    }

    public static void p() {
        RenderUtil.f(Minecraft.D());
    }

    public static void Y() {
        OpenGlBackendHolder.d.F();
    }

    public static void f(RenderManager renderManager) {
        if (ForgeVersion.MC_1_16_5.d()) {
            OpenGlBackendHolder.d.X(renderManager.getPlayerViewY(), 1.0f, 0.0f, 0.0f);
            OpenGlBackendHolder.d.X(renderManager.getPlayerViewX() + 180.0f, 0.0f, 1.0f, 0.0f);
            if (GuiRenderPrimitives.d() && Minecraft.gameSettings().x() != 0) {
                ActiveRenderInfo activeRenderInfo = Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().l();
                double d = RenderManager.getInterpolatedRenderPosX() - activeRenderInfo.o().getX();
                double d2 = RenderManager.getInterpolatedRenderPosY() - activeRenderInfo.o().getY();
                double d3 = RenderManager.getInterpolatedRenderPosZ() - activeRenderInfo.o().getZ();
                float f = FreeLookHudModule.z() ? FreeLookHudModule.w$src$F$1kb9hl5() - Minecraft.thePlayer().J() : 0.0f;
                OpenGlBackendHolder.d.I(d, d2, d3);
                OpenGlBackendHolder.d.X(-f, 0.0f, 1.0f, 0.0f);
            }
        }
    }

    public static void w(double d, double d2, double d3, double d4, double d5, double d6, Color color) {
        if (GuiRenderPrimitives.d()) {
            double d7 = d4 + 1.0 - d;
            double d8 = d4 - d;
            double d9 = d5 + 1.0 - d2;
            double d10 = d5 - d2;
            double d11 = d6 + 1.0 - d3;
            double d12 = d6 - d3;
            BufferedRenderPrimitives.z(d8, d10, d12, d8, d9, d12, d7, d10, d12, d7, d9, d12, color);
            BufferedRenderPrimitives.z(d7, d10, d11, d7, d9, d11, d8, d10, d11, d8, d9, d11, color);
            BufferedRenderPrimitives.z(d7, d9, d12, d7, d10, d12, d8, d9, d12, d8, d10, d12, color);
            BufferedRenderPrimitives.z(d8, d9, d11, d8, d10, d11, d7, d9, d11, d7, d10, d11, color);
            BufferedRenderPrimitives.z(d8, d9, d12, d7, d9, d12, d7, d9, d11, d8, d9, d11, color);
            BufferedRenderPrimitives.z(d8, d9, d12, d8, d9, d11, d7, d9, d11, d7, d9, d12, color);
            BufferedRenderPrimitives.z(d8, d10, d12, d7, d10, d12, d7, d10, d11, d8, d10, d11, color);
            BufferedRenderPrimitives.z(d8, d10, d12, d8, d10, d11, d7, d10, d11, d7, d10, d12, color);
            BufferedRenderPrimitives.z(d8, d10, d12, d8, d9, d12, d8, d10, d11, d8, d9, d11, color);
            BufferedRenderPrimitives.z(d7, d10, d11, d7, d9, d11, d7, d10, d12, d7, d9, d12, color);
            BufferedRenderPrimitives.z(d8, d9, d11, d8, d10, d11, d8, d9, d12, d8, d10, d12, color);
            BufferedRenderPrimitives.z(d7, d9, d12, d7, d10, d12, d7, d9, d11, d7, d10, d11, color);
            return;
        }
        GL11.glColor4d((double)((float)color.getRed() / 255.0f), (double)((float)color.getGreen() / 255.0f), (double)((float)color.getBlue() / 255.0f), (double)((float)color.getAlpha() / 255.0f));
        K.reset();
        double d13 = d4 + 1.0 - d;
        double d14 = d4 - d;
        double d15 = d5 + 1.0 - d2;
        double d16 = d5 - d2;
        double d17 = d6 + 1.0 - d3;
        double d18 = d6 - d3;
        K.addVertex(d14, d16, d18);
        K.addVertex(d14, d15, d18);
        K.addVertex(d13, d16, d18);
        K.addVertex(d13, d15, d18);
        K.addVertex(d13, d16, d17);
        K.addVertex(d13, d15, d17);
        K.addVertex(d14, d16, d17);
        K.addVertex(d14, d15, d17);
        K.addVertex(d13, d15, d18);
        K.addVertex(d13, d16, d18);
        K.addVertex(d14, d15, d18);
        K.addVertex(d14, d16, d18);
        K.addVertex(d14, d15, d17);
        K.addVertex(d14, d16, d17);
        K.addVertex(d13, d15, d17);
        K.addVertex(d13, d16, d17);
        K.addVertex(d14, d15, d18);
        K.addVertex(d13, d15, d18);
        K.addVertex(d13, d15, d17);
        K.addVertex(d14, d15, d17);
        K.addVertex(d14, d15, d18);
        K.addVertex(d14, d15, d17);
        K.addVertex(d13, d15, d17);
        K.addVertex(d13, d15, d18);
        K.addVertex(d14, d16, d18);
        K.addVertex(d13, d16, d18);
        K.addVertex(d13, d16, d17);
        K.addVertex(d14, d16, d17);
        K.addVertex(d14, d16, d18);
        K.addVertex(d14, d16, d17);
        K.addVertex(d13, d16, d17);
        K.addVertex(d13, d16, d18);
        K.addVertex(d14, d16, d18);
        K.addVertex(d14, d15, d18);
        K.addVertex(d14, d16, d17);
        K.addVertex(d14, d15, d17);
        K.addVertex(d13, d16, d17);
        K.addVertex(d13, d15, d17);
        K.addVertex(d13, d16, d18);
        K.addVertex(d13, d15, d18);
        K.addVertex(d14, d15, d17);
        K.addVertex(d14, d16, d17);
        K.addVertex(d14, d15, d18);
        K.addVertex(d14, d16, d18);
        K.addVertex(d13, d15, d18);
        K.addVertex(d13, d16, d18);
        K.addVertex(d13, d15, d17);
        K.addVertex(d13, d16, d17);
        K.uploadAndDraw();
    }

    public static void k(Entity entity, double d, Color color, Color color2, float f) {
        double d2 = RenderManager.getInterpolatedRenderPosX();
        double d3 = RenderManager.getInterpolatedRenderPosY();
        double d4 = RenderManager.getInterpolatedRenderPosZ();
        double d5 = entity.M() + (entity.z() - entity.M()) * (double)f;
        double d6 = entity.W() + (entity.N() - entity.W()) * (double)f;
        double d7 = entity.m$src$D$fwnne5() + (entity.h() - entity.m$src$D$fwnne5()) * (double)f;
        RenderUtil.d();
        Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().B(1.0);
        boolean bl = OpenGlBackendHolder.d.L(3042);
        boolean bl2 = OpenGlBackendHolder.d.L(2929);
        boolean bl3 = OpenGlBackendHolder.d.L(3553);
        boolean bl4 = GL11.glGetBoolean((int)2930);
        if (!bl) {
            OpenGlBackendHolder.d.l(3042);
        }
        if (bl2) {
            OpenGlBackendHolder.d.u$src$V$hntn98(2929);
        }
        if (bl3) {
            OpenGlBackendHolder.d.u$src$V$hntn98(3553);
        }
        if (bl4) {
            OpenGlBackendHolder.d.U(false);
        }
        GL11.glBlendFunc((int)770, (int)771);
        OpenGlBackendHolder.d.r(1.5f);
        OpenGlBackendHolder.d.l(2848);
        double d8 = 0.15;
        RenderUtil.u(d5 - (double)(entity.f$src$F$fst3ac() / 2.0f) - d8, d6, d7 - (double)(entity.f$src$F$fst3ac() / 2.0f) - d8, (double)entity.f$src$F$fst3ac() + d8 * 2.0, entity.Y(), (double)entity.f$src$F$fst3ac() + d8 * 2.0, d, color, color2, d2, d3, d4);
        if (bl4) {
            OpenGlBackendHolder.d.U(true);
        }
        if (bl2) {
            OpenGlBackendHolder.d.l(2929);
        }
        if (bl3) {
            OpenGlBackendHolder.d.l(3553);
        }
        if (bl) {
            OpenGlBackendHolder.d.u$src$V$hntn98(3042);
        }
        OpenGlBackendHolder.d.u$src$V$hntn98(2848);
        Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().O(1.0);
        RenderUtil.Y();
    }

    public static void c(SearchResultData searchResultData, double d, double d2, double d3) {
        RenderUtil.x(searchResultData.F, searchResultData.O, searchResultData.D, 1.0, 1.0, 1.0, searchResultData.O(), d, d2, d3);
    }

    public static double[] W(double d, double d2, double d3) {
        if (GuiRenderPrimitives.d()) {
            double[] dArray = new double[3];
            RenderVector4f renderVector4f = new RenderVector4f(d, d2, d3, 1.0);
            RenderVector4f renderVector4f2 = new RenderVector4f(0.0f, 0.0f, 0.0f, 0.0f);
            RenderMatrix4f renderMatrix4f = BufferedGuiRenderPrimitives.X.c();
            RenderMatrix4f renderMatrix4f2 = BufferedGuiRenderPrimitives.l;
            RenderMatrix4f renderMatrix4f3 = BufferedGuiRenderPrimitives.k;
            renderMatrix4f.Z(renderVector4f, renderVector4f2);
            renderMatrix4f2.Z(renderVector4f2, renderVector4f);
            renderMatrix4f3.Z(renderVector4f, renderVector4f2);
            if ((double)renderVector4f2.J == 0.0) {
                return dArray;
            }
            renderVector4f2.J = 1.0f / renderVector4f2.J * 0.5f;
            renderVector4f2.N = renderVector4f2.N * renderVector4f2.J + 0.5f;
            renderVector4f2.w = renderVector4f2.w * renderVector4f2.J + 0.5f;
            renderVector4f2.Y = renderVector4f2.Y * renderVector4f2.J + 0.5f;
            dArray[0] = renderVector4f2.N * (float)Minecraft.J();
            dArray[1] = renderVector4f2.w * (float)Minecraft.h();
            dArray[2] = renderVector4f2.Y;
            return dArray;
        }
        return NativeBridge.trn(d, d2, d3);
    }
}
