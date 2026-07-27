package gg.vape.utils.render;

import gg.vape.ui.font.SmoothFontGlyph;
import gg.vape.utils.render.GlCapabilityState;
import gg.vape.utils.render.GlImageTexture;
import gg.vape.utils.render.GlScissorRect;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.PrimitiveTopology;
import gg.vape.utils.render.RenderBatchBuilder;
import gg.vape.utils.render.RenderBatchManager;
import gg.vape.utils.render.RenderMatrix4f;
import gg.vape.utils.render.RenderMatrixStack;
import gg.vape.utils.render.TextureAtlas;
import gg.vape.utils.render.TextureAtlasRegion;
import gg.vape.utils.render.TextureAtlasRegistry;
import java.awt.Color;

public class BufferedGuiRenderPrimitives {
    public static GlCapabilityState b;
    public static GlImageTexture e;
    public static RenderMatrix4f l;
    public static RenderMatrix4f k;
    public static GlScissorRect u;
    public static int P;
    public static RenderMatrixStack X;
    private static boolean K;

    public static void m(double d, double d2, double d3, double d4, float f, Color color) {
        BufferedGuiRenderPrimitives.T((float)d, (float)d2, (float)d3, (float)d4, f, color);
    }

    public static void b(boolean bl) {
        K = bl;
    }

    public static void W(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, Color color) {
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder().q(f, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, color);
        RenderBatchManager.M().O(renderBatchBuilder);
    }

    public static boolean C() {
        boolean bl = BufferedGuiRenderPrimitives.x();
        return false;
    }

    public static void W(float f, float f2, SmoothFontGlyph smoothFontGlyph, int n, Color color, float f3) {
        P = n;
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder().Z(f, f2, smoothFontGlyph, n, color, f3);
        RenderBatchManager.M().O(renderBatchBuilder);
    }

    public static void Q(float f, float f2, float f3, float f4, float f5, float f6, float f7, Color color) {
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder().P(f, f2, f3, f4, f5, f6, f7, color);
        RenderBatchManager.M().O(renderBatchBuilder);
    }

    public static void s(float f, float f2, float f3, float f4, GlImageTexture glImageTexture, String string, float f5, Color color, Color color2, boolean bl) {
        float f6 = 0.0f;
        float f7 = 0.0f;
        float f8 = 1.0f;
        float f9 = 1.0f;
        float f10 = glImageTexture.l;
        float f11 = glImageTexture.N;
        TextureAtlas textureAtlas = TextureAtlasRegistry.w().m("vape_texture");
        if (textureAtlas.d() != null && glImageTexture.F == textureAtlas.d().F) {
            TextureAtlasRegion textureAtlasRegion = textureAtlas.B(string);
            if (textureAtlasRegion == null) {
                ImageRenderer.j("armor");
                return;
            }
            f6 = textureAtlasRegion.d;
            f8 = textureAtlasRegion.X;
            f7 = textureAtlasRegion.s;
            f9 = textureAtlasRegion.n;
            f10 = textureAtlasRegion.q;
            f11 = textureAtlasRegion.g;
        }
        BufferedGuiRenderPrimitives.c(f, f2, f3, f4, f10, f11, f6, f7, f8, f9, f5, color, color2, bl);
    }

    public static void X(float f, float f2, float f3, float f4, float f5, Color color) {
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder().Q(f, f2, f3, f4, f5, color);
        RenderBatchManager.M().O(renderBatchBuilder);
    }

    public static boolean x() {
        return K;
    }

    public static void u(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, Color color) {
        BufferedGuiRenderPrimitives.B((float)d, (float)d2, (float)d3, (float)d4, (float)d5, (float)d6, (float)d7, (float)d8, color);
    }

    public static void Y(float f, float f2, float f3, float f4, Color color, boolean bl, float f5, float f6, float f7, Color color2, int n) {
        if (f5 == 0.0f) {
            BufferedGuiRenderPrimitives.U(f, f2, f3, f4, color);
            return;
        }
        if (bl) {
            RenderBatchManager.M().O(new RenderBatchBuilder().r(f, f2 + 0.5f, f3, f4 - 1.5f, f7, f5, color2));
        }
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder().X(f, f2, f3, f4, color, f5, f6, n);
        RenderBatchManager.M().O(renderBatchBuilder);
    }

    public static void b(float f, float f2, SmoothFontGlyph smoothFontGlyph, GlImageTexture glImageTexture, Color color, float f3) {
        e = glImageTexture;
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder().H(f, f2, smoothFontGlyph, color, f3);
        RenderBatchManager.M().O(renderBatchBuilder);
    }

    public static void c(float f, float f2, float f3, float f4, float f5, float f6, Color color, GlImageTexture glImageTexture) {
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder().o(glImageTexture).p(f, f2, f3, f4, f5, f6, color);
        RenderBatchManager.M().O(renderBatchBuilder);
    }

    public static void T(float f, float f2, float f3, float f4, float f5, float f6, Color color) {
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder().n(f, f2, f3, f4, f5, f6, color);
        RenderBatchManager.M().O(renderBatchBuilder);
    }

    public static void p(double d, double d2, double d3, double d4, double d5, double d6, Color color) {
        BufferedGuiRenderPrimitives.y((float)d, (float)d2, (float)d3, (float)d4, (float)d5, (float)d6, color);
    }

    public static void v(float f, float f2, SmoothFontGlyph smoothFontGlyph, int n, Color color, float f3) {
        P = n;
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder().t(f, f2, smoothFontGlyph, n, color, f3);
        RenderBatchManager.M().O(renderBatchBuilder);
    }

    public static void T(float f, float f2, float f3, float f4, float f5, Color color) {
        BufferedGuiRenderPrimitives.c(f, f2, 0.0f, f3, f4, 0.0f, f5, color);
    }

    static {
        BufferedGuiRenderPrimitives.b(true);
        X = new RenderMatrixStack();
        l = new RenderMatrix4f().b();
        k = new RenderMatrix4f().b();
        u = null;
        e = null;
        P = -1;
        b = new GlCapabilityState();
    }

    public static void c(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, Color color, Color color2, boolean bl) {
        Object object;
        if (Math.signum(f11) >= 0.0f) {
            OpenGlBackendHolder.d.G(f11, f11, f11);
        }
        if (bl) {
            object = new Color(0, 0, 0, 150);
            RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder().e(f + 0.5f, f2 + 0.5f, f3, f4, f5, f6, f7, f8, f9, f10, (Color)object);
            RenderBatchManager.M().O(renderBatchBuilder);
        }
        if (color2 == null) {
            object = new RenderBatchBuilder().e(f, f2, f3, f4, f5, f6, f7, f8, f9, f10, color);
            RenderBatchManager.M().O((RenderBatchBuilder)object);
        }
        if (Math.signum(f11) >= 0.0f) {
            OpenGlBackendHolder.d.G(1.0f / f11, 1.0f / f11, 1.0f / f11);
        }
    }

    public static void X(float f, float f2, float f3, float f4, Color color, GlImageTexture glImageTexture) {
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder().o(glImageTexture).k(f, f2, f3, f4, color);
        RenderBatchManager.M().O(renderBatchBuilder);
    }

    public static void T(float f, float f2, float f3, float f4, float[] fArray, float[] fArray2) {
        if (f3 <= 0.0f || f4 <= 0.0f) {
            return;
        }
        RenderBatchManager.M().O(new RenderBatchBuilder().d(f, f2, f3, f4, fArray, fArray2));
    }

    public static void c(float f, float f2, float f3, float f4, Color color, float f5, float f6, float f7) {
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder().p(f, f2, f3, f4, color, f5, f6, f7);
        RenderBatchManager.M().O(renderBatchBuilder);
    }

    public static void Y(float f, float f2, float f3, float f4, float f5, float f6, Color color, float f7, float f8, float f9, Color color2, Color color3, float f10, float f11, Color color4, boolean bl) {
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder().Y(f, f2, f3, f4, f5, f6, color, f7, f8, f9, color2, color3, f10, f11, color4, bl);
        RenderBatchManager.M().O(renderBatchBuilder);
    }

    public static void r(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, Color color) {
        BufferedGuiRenderPrimitives.Z((float)d, (float)d2, (float)d3, (float)d4, (float)d5, (float)d6, (float)d7, (float)d8, color);
    }

    public static void j(float f, float f2, float f3, float f4, Color color) {
        if (f3 <= 0.0f || f4 <= 0.0f) {
            return;
        }
        RenderBatchManager.M().O(new RenderBatchBuilder().v(f, f2, f3, f4, color));
    }

    public static void I(double d, double d2, double d3, double d4, float f, float f2, float f3, float f4, GlImageTexture glImageTexture) {
        if (d3 == 0.0 || d4 == 0.0) {
            return;
        }
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder().o(glImageTexture).A((float)d, (float)d2, (float)d3, (float)d4, f, f2, f3, f4);
        RenderBatchManager.M().O(renderBatchBuilder);
    }

    public static void X(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, Color color, Color color2) {
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder().E(f, f2, 0.0f, f3, f4, 0.0f, f5, f6, 0.0f, f7, f8, 0.0f, color, color2);
        RenderBatchManager.M().O(renderBatchBuilder);
    }

    public static void y(float f, float f2, float f3, float f4, Color color, float f5, Color color2) {
        if (color2 != null) {
            float f6 = f + (f3 + f4) / 2.0f - f5 / 2.0f;
            RenderBatchManager.M().O(new RenderBatchBuilder().c(f6, f2, f5, f3, color2));
        }
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder().F(f, f2, f3, f4, color);
        RenderBatchManager.M().O(renderBatchBuilder);
    }

    public static void I(double d, double d2, double d3, double d4, double d5, double d6, int n, Color color) {
        BufferedGuiRenderPrimitives.Z((float)d, (float)d2, (float)d3, (float)d4, (float)d5, (float)d6, n, color);
    }

    public static void c(float f, float f2, float f3, float f4, float f5, float f6, float f7, Color color) {
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder(2).b(f, f2, f3, f4, f5, f6, f7, color);
        RenderBatchManager.M().O(renderBatchBuilder);
    }

    public static void t(double d, double d2, double d3, double d4, double d5, Color color, Color color2) {
        if (color.equals(color2)) {
            d5 = 0.0;
            BufferedGuiRenderPrimitives.V(d, d2, d3 - d5, d4 - d5, color);
            return;
        }
        BufferedGuiRenderPrimitives.V(d -= d5, d2, (d3 += d5) - d5, (d4 += d5) - d5, color);
    }

    public static void H(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, double d11, double d12, Color color) {
        BufferedGuiRenderPrimitives.W((float)d, (float)d2, (float)d3, (float)d4, (float)d5, (float)d6, (float)d7, (float)d8, (float)d9, (float)d10, (float)d11, (float)d12, color);
    }

    public static void U(float f, float f2, float f3, float f4, Color color) {
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder().d(f, f2, f3, f4, color);
        RenderBatchManager.M().O(renderBatchBuilder);
    }

    public static void d(float f, float f2, SmoothFontGlyph smoothFontGlyph, int n, Color color, float f3) {
        P = n;
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder().H(f, f2, smoothFontGlyph, n, color, f3);
        RenderBatchManager.M().O(renderBatchBuilder);
    }

    public static void Z(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, Color color) {
        BufferedGuiRenderPrimitives.W(f, f2, 0.0f, f3, f4, 0.0f, f5, f6, 0.0f, f7, f8, 0.0f, color);
    }

    public static void N(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, Color color, Color color2) {
        BufferedGuiRenderPrimitives.X((float)d, (float)d2, (float)d3, (float)d4, (float)d5, (float)d6, (float)d7, (float)d8, color, color2);
    }

    public static void V(double d, double d2, double d3, double d4, Color color) {
        BufferedGuiRenderPrimitives.U((float)d, (float)d2, (float)d3, (float)d4, color);
    }


    public static void B(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, Color color) {
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder().b(PrimitiveTopology.LINES_LOOP).q(f, f2, 0.0f, f3, f4, 0.0f, f5, f6, 0.0f, f7, f8, 0.0f, color);
        RenderBatchManager.M().O(renderBatchBuilder);
    }

    public static void O(float f, float f2, float f3, float f4, float f5, float f6, Color color) {
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder().r(f, f2, f3, f4, f5, f6, color);
        RenderBatchManager.M().O(renderBatchBuilder);
    }

    public static void Z(double d, double d2, double d3, double d4, double d5, double d6, float f, Color color) {
        BufferedGuiRenderPrimitives.c((float)d, (float)d2, (float)d3, (float)d4, (float)d5, (float)d6, f, color);
    }

    public static void y(float f, float f2, float f3, float f4, float f5, float f6, Color color) {
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder(3).i(f, f2, 0.0f, f3, f4, 0.0f, f5, f6, 0.0f, color);
        RenderBatchManager.M().O(renderBatchBuilder);
    }

    public static void Z(float f, float f2, float f3, float f4, float f5, float f6, int n, Color color) {
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder().a(f, f2, f3, f4, f5, f6, n, color);
        RenderBatchManager.M().O(renderBatchBuilder);
    }
}

