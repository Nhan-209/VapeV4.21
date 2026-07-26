package gg.vape.utils.render;

import gg.vape.ui.font.SmoothFontGlyph;
import gg.vape.utils.MutableColor;
import gg.vape.utils.render.BufferedGuiRenderPrimitives;
import gg.vape.utils.render.GlImageTexture;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderBatchBuilder;
import gg.vape.utils.render.RenderBatchManager;
import gg.vape.utils.render.RenderUtil;
import gg.vape.utils.render.VertexCoordinateMode;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RenderManager;
import java.awt.Color;
import org.lwjgl.opengl.GL11;

public class BufferedRenderPrimitives {
    public static void x(float f, float f2, float f3, float f4, float f5, Color color) {
        BufferedRenderPrimitives.X(f, f2, 0.0f, f3, f2, 0.0f, f5, color);
        BufferedRenderPrimitives.X(f3, f2, 0.0f, f3, f4, 0.0f, f5, color);
        BufferedRenderPrimitives.X(f3, f4, 0.0f, f, f4, 0.0f, f5, color);
        BufferedRenderPrimitives.X(f, f4, 0.0f, f, f2, 0.0f, f5, color);
    }

    public static void x(double d, double d2, double d3, double d4, float f, Color color) {
        BufferedRenderPrimitives.m((float)d, (float)d2, (float)d3, (float)d4, f, color);
    }

    public static void Q(double d, double d2, double d3, double d4, double d5, double d6, float f, Color color) {
        BufferedRenderPrimitives.X((float)d, (float)d2, (float)d3, (float)d4, (float)d5, (float)d6, f, color);
    }

    public static void q(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, Color color) {
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder(4, VertexCoordinateMode.DEFAULT, true).q(f, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, color);
        RenderBatchManager.M().c(renderBatchBuilder);
    }

    public static void v(float f, float f2, SmoothFontGlyph smoothFontGlyph, GlImageTexture glImageTexture, Color color, float f3) {
        BufferedGuiRenderPrimitives.e = glImageTexture;
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder(4, VertexCoordinateMode.DEFAULT, true).H(f, f2, smoothFontGlyph, color, f3);
        RenderBatchManager.M().c(renderBatchBuilder);
    }

    public static void A(double d, double d2, double d3, double d4, Color color) {
        BufferedRenderPrimitives.G((float)d, (float)d2, (float)d3, (float)d4, color);
    }

    public static void X(float f, float f2, float f3, float f4, float f5, float f6, float f7, Color color) {
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder(2, VertexCoordinateMode.DEFAULT, true).b(f, f2, f3, f4, f5, f6, f7, color);
        RenderBatchManager.M().c(renderBatchBuilder);
    }

    public static void z(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, double d11, double d12, Color color) {
        BufferedRenderPrimitives.q((float)d, (float)d2, (float)d3, (float)d4, (float)d5, (float)d6, (float)d7, (float)d8, (float)d9, (float)d10, (float)d11, (float)d12, color);
    }

    public static void v(float f, float f2, float f3, float f4, float f5, Color color, Color color2) {
        if (color.equals(color2)) {
            f5 = 0.0f;
            RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder(4, VertexCoordinateMode.DEFAULT, true).d(f, f2, f3 - f5, f4 - f5, color);
            RenderBatchManager.M().c(renderBatchBuilder);
            return;
        }
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder(4, VertexCoordinateMode.DEFAULT, true).d(f -= f5, f2, (f3 += f5) - f5, (f4 += f5) - f5, color);
        RenderBatchManager.M().c(renderBatchBuilder);
        float f6 = f + f3;
        float f7 = f2 + f4;
    }

    public static void G(float f, float f2, float f3, float f4, Color color) {
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder(4, VertexCoordinateMode.DEFAULT, true).d(f, f2, f3, f4, color);
        RenderBatchManager.M().c(renderBatchBuilder);
    }

    public static void n(float f, float f2, float f3, float f4, float f5, float f6, Color color) {
        BufferedRenderPrimitives.X(f, f2, f3, f4, f2, f3, 1.0f, color);
        BufferedRenderPrimitives.X(f4, f2, f3, f4, f2, f6, 1.0f, color);
        BufferedRenderPrimitives.X(f4, f2, f3, f4, f2, f6, 1.0f, color);
        BufferedRenderPrimitives.X(f, f2, f3, f, f2, f6, 1.0f, color);
        BufferedRenderPrimitives.X(f, f2, f6, f4, f2, f6, 1.0f, color);
        BufferedRenderPrimitives.X(f, f2, f3, f, f5, f3, 1.0f, color);
        BufferedRenderPrimitives.X(f4, f2, f6, f4, f5, f6, 1.0f, color);
        BufferedRenderPrimitives.X(f, f2, f6, f, f5, f6, 1.0f, color);
        BufferedRenderPrimitives.X(f4, f2, f3, f4, f5, f3, 1.0f, color);
        BufferedRenderPrimitives.X(f, f5, f3, f4, f5, f3, 1.0f, color);
        BufferedRenderPrimitives.X(f4, f5, f3, f4, f5, f6, 1.0f, color);
        BufferedRenderPrimitives.X(f, f5, f3, f, f5, f6, 1.0f, color);
        BufferedRenderPrimitives.X(f, f5, f6, f4, f5, f6, 1.0f, color);
    }

    public static void e(double d, double d2, double d3, double d4, double d5, double d6, Color color) {
        BufferedRenderPrimitives.n((float)d, (float)d2, (float)d3, (float)d4, (float)d5, (float)d6, color);
    }

    public static void X(double d, double d2, double d3, float f, float f2, float f3, Color color) {
        double d4 = RenderManager.getInterpolatedRenderPosX();
        double d5 = RenderManager.getInterpolatedRenderPosY();
        double d6 = RenderManager.getInterpolatedRenderPosZ();
        float f4 = (float)(d - d4);
        float f5 = (float)(d2 - d5);
        float f6 = (float)(d3 - d6);
        RenderUtil.d();
        Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().B(1.0);
        OpenGlBackendHolder.d.l(3042);
        GL11.glBlendFunc((int)770, (int)771);
        OpenGlBackendHolder.d.U(false);
        OpenGlBackendHolder.d.u$src$V$hntn98(2884);
        float f7 = f5;
        float f8 = f5 + f3;
        MutableColor mutableColor = new MutableColor(color).withAlpha(0);
        Color color2 = color;
        float f9 = 0.0f;
        float f10 = 0.0f;
        boolean bl = false;
        int n = 0;
        while ((float)n <= f) {
            float f11 = (float)(Math.PI * 2 * (double)n / (double)f);
            float f12 = (float)((double)f2 * Math.cos(f11) + (double)f4);
            float f13 = (float)((double)f2 * Math.sin(f11) + (double)f6);
            if (!bl) {
                bl = true;
                f9 = f12;
                f10 = f13;
            } else {
                BufferedRenderPrimitives.H(f12, f8, f13, f9, f8, f10, f9, f7, f10, f12, f7, f13, mutableColor, color2);
                f9 = f12;
                f10 = f13;
            }
            ++n;
        }
        OpenGlBackendHolder.d.U(true);
        OpenGlBackendHolder.d.u$src$V$hntn98(3042);
        Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().O(1.0);
        RenderUtil.Y();
    }

    public static void m(float f, float f2, float f3, float f4, float f5, Color color) {
        BufferedRenderPrimitives.X(f, f2, 0.0f, f3, f4, 0.0f, f5, color);
    }

    public static void W(double d, double d2, double d3, double d4, double d5, double d6, Color color) {
        BufferedRenderPrimitives.Z((float)d, (float)d2, (float)d3, (float)d4, (float)d5, (float)d6, color);
    }

    public static void K(double d, double d2, double d3, double d4, float f, Color color, Color color2) {
        BufferedRenderPrimitives.v((float)d, (float)d2, (float)d3, (float)d4, f, color, color2);
    }

    public static void Z(float f, float f2, float f3, float f4, float f5, float f6, Color color) {
        BufferedRenderPrimitives.q(f, f2, f3, f, f5, f3, f4, f5, f3, f4, f2, f3, color);
        BufferedRenderPrimitives.q(f4, f2, f6, f4, f5, f6, f, f5, f6, f, f2, f6, color);
        BufferedRenderPrimitives.q(f4, f2, f3, f4, f5, f3, f4, f5, f6, f4, f2, f6, color);
        BufferedRenderPrimitives.q(f, f2, f6, f, f5, f6, f, f5, f3, f, f2, f3, color);
        BufferedRenderPrimitives.q(f, f5, f3, f, f5, f6, f4, f5, f6, f4, f5, f3, color);
        BufferedRenderPrimitives.q(f, f2, f3, f4, f2, f3, f4, f2, f6, f, f2, f6, color);
    }

    public static void P(float f, float f2, float f3, float f4, float f5, float f6, Color color, float f7, float f8, float f9, Color color2, Color color3, float f10, float f11, Color color4, boolean bl) {
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder(4, VertexCoordinateMode.DEFAULT, true).Y(f, f2, f3, f4, f5, f6, color, f7, f8, f9, color2, color3, f10, f11, color4, bl);
        RenderBatchManager.M().c(renderBatchBuilder);
    }

    public static void H(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, Color color, Color color2) {
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder(4, VertexCoordinateMode.DEFAULT, true).k(f, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, color, color2);
        RenderBatchManager.M().c(renderBatchBuilder);
    }

    public static void c(double d, double d2, double d3, double d4, float f, Color color) {
        BufferedRenderPrimitives.x((float)d, (float)d2, (float)d3, (float)d4, f, color);
    }
}

