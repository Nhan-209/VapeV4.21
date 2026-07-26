package gg.vape.utils.render;

import gg.vape.ui.font.SmoothFontGlyph;
import gg.vape.utils.datas.FloatPair;
import gg.vape.utils.render.BufferedGuiRenderPrimitives;
import gg.vape.utils.render.GlCapabilityState;
import gg.vape.utils.render.GlImageTexture;
import gg.vape.utils.render.GlScissorRect;
import gg.vape.utils.render.PrimitiveTopology;
import gg.vape.utils.render.RenderBatchManager;
import gg.vape.utils.render.RenderMatrix4f;
import gg.vape.utils.render.RenderVector3f;
import gg.vape.utils.render.RenderVector4f;
import gg.vape.utils.render.VertexCoordinateMode;
import java.awt.Color;
import java.util.function.Supplier;

public class RenderBatchBuilder {
    public int a = 0;
    private boolean c;
    private final RenderVector3f b;
    private GlImageTexture V;
    private final RenderVector4f w;
    public RenderMatrix4f C;
    private VertexCoordinateMode E;
    private int[] f;
    private float[] J;
    private float F = 1.0f;
    private Supplier<Void> o = null;
    private GlScissorRect G = null;
    private PrimitiveTopology H = null;
    private final FloatPair z = new FloatPair(0.0f, 0.0f);
    private int I;
    private Supplier<Void> x = null;
    public int v = 0;
    private GlCapabilityState g;

    private void n(float f, float f2, float f3, float f4, RenderVector4f renderVector4f, float f5, RenderVector4f renderVector4f2) {
        this.z(f, f2, f3, f4, renderVector4f, f5, 0.0f, renderVector4f2);
    }

    public RenderBatchBuilder p(float f, float f2, float f3, float f4, float f5, float f6, Color color) {
        if (f3 == 0.0f || f4 == 0.0f) {
            return null;
        }
        float f7 = 0.5f;
        if (f5 == 0.0f) {
            f7 = 0.0f;
        }
        float f8 = Math.max(0.0f, (f5 += f7 * 2.0f) - f6);
        if (f5 != 0.0f) {
            f = (float)((double)f - ((double)f6 - 0.5));
            f2 -= f6;
            f4 = (float)((double)f4 + (double)f6 * 1.5);
            f3 += f6 * 1.0f;
        }
        float f9 = f + f3;
        float f10 = f2 + f4;
        float f11 = this.V != null ? this.V.C : 0.0f;
        float f12 = this.V != null ? this.V.w : 0.0f;
        float f13 = this.V != null ? this.V.h : 1.0f;
        float f14 = this.V != null ? this.V.p : 1.0f;
        RenderVector4f renderVector4f = new RenderVector4f(f + f5, f2 + f5, f + f3 - f5, f2 + f4 - f5);
        RenderVector4f renderVector4f2 = new RenderVector4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        this.b(PrimitiveTopology.QUADS);
        this.x(7.0f, f, f2, 0.0f, f11, f12, f8, renderVector4f, f6, renderVector4f2);
        this.x(7.0f, f, f10, 0.0f, f11, f14, f8, renderVector4f, f6, renderVector4f2);
        this.x(7.0f, f9, f2, 0.0f, f13, f12, f8, renderVector4f, f6, renderVector4f2);
        this.x(7.0f, f9, f10, 0.0f, f13, f14, f8, renderVector4f, f6, renderVector4f2);
        this.B("quad");
        return this;
    }

    private void z(float f, float f2, float f3, float f4, RenderVector4f renderVector4f, float f5, float f6, RenderVector4f renderVector4f2) {
        RenderVector3f renderVector3f = new RenderVector3f(f2, f3, f4);
        RenderVector3f renderVector3f2 = new RenderVector3f(f6, 0.0f, 0.0f);
        this.q(f, renderVector3f, this.z, renderVector4f2, 0.0f, 0.0f, this.z, 0.0f, 0.0f, this.z, this.z, renderVector4f, renderVector3f2, f5, this.w);
    }

    public RenderBatchBuilder b(PrimitiveTopology primitiveTopology) {
        return this.Q(primitiveTopology, RenderBatchManager.M().s().Q());
    }

    public float[] y() {
        return this.J;
    }

    public RenderBatchBuilder F(float f, float f2, float f3, float f4, Color color) {
        float f5 = f3 += f4;
        float f6 = f3;
        float f7 = (f -= f4 / 2.0f) + f5;
        float f8 = (f2 -= f4 / 2.0f) + f6;
        float f9 = f3 / 2.0f;
        FloatPair floatPair = new FloatPair(f + f5 / 2.0f, f2 + f6 / 2.0f);
        RenderVector4f renderVector4f = new RenderVector4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        this.b(PrimitiveTopology.QUADS);
        this.k(2.0f, f, f2, 0.0f, f9, f4, floatPair, renderVector4f);
        this.k(2.0f, f, f8, 0.0f, f9, f4, floatPair, renderVector4f);
        this.k(2.0f, f7, f2, 0.0f, f9, f4, floatPair, renderVector4f);
        this.k(2.0f, f7, f8, 0.0f, f9, f4, floatPair, renderVector4f);
        this.B("quad");
        return this;
    }

    public RenderBatchBuilder i(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, Color color) {
        RenderVector4f renderVector4f = new RenderVector4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        this.b(PrimitiveTopology.TRIANGLES);
        this.V(9.0f, f4, f5, f6, renderVector4f);
        this.V(9.0f, f, f2, f3, renderVector4f);
        this.V(9.0f, f7, f8, f9, renderVector4f);
        this.B("triangle");
        return this;
    }

    public RenderBatchBuilder L(float f) {
        this.F = f;
        return this;
    }

    private static IllegalArgumentException a(IllegalArgumentException illegalArgumentException) {
        return illegalArgumentException;
    }

    public RenderBatchBuilder(VertexCoordinateMode vertexCoordinateMode, boolean bl) {
        this(4, vertexCoordinateMode, bl);
    }

    private void i(float f, float f2, float f3, float f4, float f5, float f6, float f7, FloatPair floatPair, RenderVector4f renderVector4f) {
        RenderVector3f renderVector3f = new RenderVector3f(f2, f3, f4);
        this.q(f, renderVector3f, this.z, renderVector4f, f5, f7, floatPair, 0.0f, 0.0f, this.z, this.z, this.w, new RenderVector3f(f6, 0.0f, 0.0f), 0.0f, this.w);
    }

    public float D() {
        return this.F;
    }

    public RenderBatchBuilder p(float f, float f2, float f3, float f4, Color color, float f5, float f6, float f7) {
        float f8 = 0.5f;
        f = (float)((double)f - ((double)f7 - 0.5));
        f4 = (float)((double)f4 + (double)f7 * 1.5);
        float f9 = f + f8;
        float f10 = f + (f3 += f7 * 1.0f) - f8;
        float f11 = (f2 -= f7) + f8;
        float f12 = f2 + f4 - f8;
        RenderVector3f renderVector3f = new RenderVector3f(f5, f7, f6);
        RenderVector4f renderVector4f = new RenderVector4f(f + f5 + f6, f2 + f5 + f6, f + f3 - (f5 + f6), f2 + f4 - (f5 + f6));
        RenderVector4f renderVector4f2 = new RenderVector4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        this.b(PrimitiveTopology.QUADS);
        this.j(6.0f, f9, f11, 0.0f, renderVector3f, renderVector4f, renderVector4f2);
        this.j(6.0f, f9, f12, 0.0f, renderVector3f, renderVector4f, renderVector4f2);
        this.j(6.0f, f10, f11, 0.0f, renderVector3f, renderVector4f, renderVector4f2);
        this.j(6.0f, f10, f12, 0.0f, renderVector3f, renderVector4f, renderVector4f2);
        this.B("quad");
        return this;
    }

    private void x(float f, float f2, float f3, float f4, float f5, float f6, float f7, RenderVector4f renderVector4f, float f8, RenderVector4f renderVector4f2) {
        RenderVector3f renderVector3f = new RenderVector3f(f2, f3, f4);
        FloatPair floatPair = new FloatPair(f5, f6);
        this.q(f, renderVector3f, floatPair, renderVector4f2, 0.0f, 0.0f, this.z, 0.0f, 0.0f, this.z, this.z, renderVector4f, new RenderVector3f(f7, 0.0f, 0.0f), f8, this.w);
    }

    private void d(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, FloatPair floatPair, RenderVector4f renderVector4f) {
        RenderVector3f renderVector3f = new RenderVector3f(f2, f3, f4);
        FloatPair floatPair2 = new FloatPair(f5, f6);
        this.q(f, renderVector3f, floatPair2, renderVector4f, 0.0f, f8, floatPair, 0.0f, 0.0f, this.z, this.z, this.w, new RenderVector3f(f7, 0.0f, 0.0f), 0.0f, this.w);
    }

    public RenderBatchBuilder H(float f, float f2, SmoothFontGlyph smoothFontGlyph, Color color, float f3) {
        float f4 = f + smoothFontGlyph.X * f3;
        float f5 = f + smoothFontGlyph.g * f3;
        float f6 = f2 + smoothFontGlyph.a * f3;
        float f7 = f2 + smoothFontGlyph.G * f3;
        this.o(BufferedGuiRenderPrimitives.e);
        RenderVector4f renderVector4f = new RenderVector4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        this.b(PrimitiveTopology.QUADS);
        this.w(11.0f, f4, f6, 0.0f, smoothFontGlyph.t, smoothFontGlyph.J, renderVector4f);
        this.w(11.0f, f4, f7, 0.0f, smoothFontGlyph.t, smoothFontGlyph.B, renderVector4f);
        this.w(11.0f, f5, f6, 0.0f, smoothFontGlyph.w, smoothFontGlyph.J, renderVector4f);
        this.w(11.0f, f5, f7, 0.0f, smoothFontGlyph.w, smoothFontGlyph.B, renderVector4f);
        this.B("quad");
        return this;
    }

    public RenderBatchBuilder X(float f, float f2, float f3, float f4, Color color, float f5, float f6, int n) {
        float f7 = 0.5f;
        if (f5 <= 0.0f) {
            f7 = 0.0f;
        }
        float f8 = Math.max(0.0f, (f5 += f7 * 2.0f) - f6);
        if (f5 > 0.0f) {
            f -= f6 - 0.5f;
            f2 -= f6;
            f4 += f6 * 1.0f;
            f3 += f6 * 1.0f;
        }
        float f9 = f + f7;
        float f10 = f + f3 - f7;
        float f11 = f2 + f7;
        float f12 = f2 + f4 - f7;
        boolean bl = (n & 1) != 0;
        boolean bl2 = (n & 2) != 0;
        boolean bl3 = (n & 4) != 0;
        boolean bl4 = (n & 8) != 0;
        RenderVector4f renderVector4f = new RenderVector4f(bl ? 1.0f : 0.0f, bl2 ? 1.0f : 0.0f, bl3 ? 1.0f : 0.0f, bl4 ? 1.0f : 0.0f);
        RenderVector4f renderVector4f2 = new RenderVector4f(f + f5, f2 + f5, f + f3 - f5, f2 + f4 - f5);
        RenderVector4f renderVector4f3 = new RenderVector4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        this.b(PrimitiveTopology.QUADS);
        this.o(5.0f, f9, f11, 0.0f, f8, renderVector4f2, f6, renderVector4f3, renderVector4f);
        this.o(5.0f, f9, f12, 0.0f, f8, renderVector4f2, f6, renderVector4f3, renderVector4f);
        this.o(5.0f, f10, f11, 0.0f, f8, renderVector4f2, f6, renderVector4f3, renderVector4f);
        this.o(5.0f, f10, f12, 0.0f, f8, renderVector4f2, f6, renderVector4f3, renderVector4f);
        this.B("quad");
        return this;
    }

    public RenderBatchBuilder e(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, Color color) {
        float f11;
        if (f3 == f4) {
            f11 = f5 / f6;
            f3 *= f11;
        }
        f11 = f + f3;
        float f12 = f2 + f4;
        RenderVector4f renderVector4f = new RenderVector4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        this.b(PrimitiveTopology.QUADS);
        this.w(10.0f, f, f2, 0.0f, f7, f8, renderVector4f);
        this.w(10.0f, f, f12, 0.0f, f7, f10, renderVector4f);
        this.w(10.0f, f11, f2, 0.0f, f9, f8, renderVector4f);
        this.w(10.0f, f11, f12, 0.0f, f9, f10, renderVector4f);
        this.B("quad");
        return this;
    }

    private void Q(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, RenderVector4f renderVector4f, FloatPair floatPair) {
        RenderVector3f renderVector3f = new RenderVector3f(f2, f3, f4);
        FloatPair floatPair2 = new FloatPair(f5, f6);
        this.q(f, renderVector3f, floatPair2, this.w, 0.0f, f7, this.z, 0.0f, 0.0f, floatPair, this.z, renderVector4f, new RenderVector3f(f9, f8, 0.0f), f10, this.w);
    }

    private void U(float f, float f2, float f3, float f4, float f5, float f6, float f7, FloatPair floatPair, float f8, float f9, RenderVector4f renderVector4f) {
        RenderVector3f renderVector3f = new RenderVector3f(f2, f3, f4);
        this.q(f, renderVector3f, this.z, renderVector4f, f5, f7, floatPair, f8, f9, this.z, this.z, this.w, new RenderVector3f(f6, 0.0f, 0.0f), 0.0f, this.w);
    }

    public int[] R() {
        return this.f;
    }

    public RenderBatchBuilder d(float f, float f2, float f3, float f4, float[] fArray, float[] fArray2) {
        float f5 = Math.max(f4 / 2.0f - 0.5f, 0.25f);
        float f6 = f2 + f4 / 2.0f;
        float f7 = f + f4 / 2.0f;
        float f8 = f + f3 - f4 / 2.0f;
        float f9 = 0.5f;
        float f10 = f - f9;
        float f11 = f2 - f9;
        float f12 = f + f3 + f9;
        float f13 = f2 + f4 + f9;
        RenderVector4f renderVector4f = new RenderVector4f(f7, f6, f8, f6);
        RenderVector4f renderVector4f2 = new RenderVector4f(fArray[0] * 255.0f, fArray[1] * 255.0f, fArray[2] * 255.0f, fArray[3] * 255.0f);
        RenderVector4f renderVector4f3 = new RenderVector4f(fArray2[0], fArray2[1], fArray2[2], fArray2[3]);
        this.b(PrimitiveTopology.QUADS);
        this.o(20.0f, f10, f11, 0.0f, f5, renderVector4f, 0.0f, renderVector4f2, renderVector4f3);
        this.o(20.0f, f10, f13, 0.0f, f5, renderVector4f, 0.0f, renderVector4f2, renderVector4f3);
        this.o(20.0f, f12, f11, 0.0f, f5, renderVector4f, 0.0f, renderVector4f2, renderVector4f3);
        this.o(20.0f, f12, f13, 0.0f, f5, renderVector4f, 0.0f, renderVector4f2, renderVector4f3);
        this.B("quad");
        return this;
    }

    public RenderBatchBuilder b(Supplier<Void> supplier) {
        this.o = supplier;
        this.b(PrimitiveTopology.QUADS);
        return this;
    }

    private void B(String string) {
        this.N(string, RenderBatchManager.M().A(this.c));
    }

    public RenderBatchBuilder H(float f, float f2, SmoothFontGlyph smoothFontGlyph, int n, Color color, float f3) {
        float f4 = f + smoothFontGlyph.X * f3;
        float f5 = f + smoothFontGlyph.g * f3;
        float f6 = f2 + smoothFontGlyph.a * f3;
        float f7 = f2 + smoothFontGlyph.G * f3;
        this.o(new GlImageTexture(n));
        RenderVector4f renderVector4f = new RenderVector4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        this.b(PrimitiveTopology.QUADS);
        this.w(17.0f, f4, f6, 0.0f, smoothFontGlyph.t, smoothFontGlyph.J, renderVector4f);
        this.w(17.0f, f4, f7, 0.0f, smoothFontGlyph.t, smoothFontGlyph.B, renderVector4f);
        this.w(17.0f, f5, f6, 0.0f, smoothFontGlyph.w, smoothFontGlyph.J, renderVector4f);
        this.w(17.0f, f5, f7, 0.0f, smoothFontGlyph.w, smoothFontGlyph.B, renderVector4f);
        this.B("quad");
        return this;
    }

    public RenderBatchBuilder Q(float f, float f2, float f3, float f4, float f5, Color color) {
        float f6 = f3 += f5;
        float f7 = f3;
        float f8 = (f -= f5 / 2.0f) + f6;
        float f9 = (f2 -= f5 / 2.0f) + f7;
        float f10 = f3 / 2.0f;
        float f11 = f10 - f4;
        FloatPair floatPair = new FloatPair(f + f6 / 2.0f, f2 + f7 / 2.0f);
        RenderVector4f renderVector4f = new RenderVector4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        this.b(PrimitiveTopology.QUADS);
        this.i(3.0f, f, f2, 0.0f, f11, f10, f5, floatPair, renderVector4f);
        this.i(3.0f, f, f9, 0.0f, f11, f10, f5, floatPair, renderVector4f);
        this.i(3.0f, f8, f2, 0.0f, f11, f10, f5, floatPair, renderVector4f);
        this.i(3.0f, f8, f9, 0.0f, f11, f10, f5, floatPair, renderVector4f);
        this.B("quad");
        return this;
    }

    public RenderBatchBuilder() {
        this(4);
    }

    public RenderBatchBuilder k(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, Color color, Color color2) {
        RenderVector4f renderVector4f = new RenderVector4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        RenderVector4f renderVector4f2 = new RenderVector4f(color2.getRed(), color2.getGreen(), color2.getBlue(), color2.getAlpha());
        this.b(PrimitiveTopology.QUADS);
        this.V(9.0f, f4, f5, f6, renderVector4f);
        this.V(9.0f, f, f2, f3, renderVector4f);
        this.V(9.0f, f7, f8, f9, renderVector4f2);
        this.V(9.0f, f10, f11, f12, renderVector4f2);
        this.B("quad");
        return this;
    }

    public RenderBatchBuilder n(float f, float f2, float f3, float f4, float f5, float f6, Color color) {
        float f7 = f5 + f6;
        float f8 = Math.min(f, f3) - f7;
        float f9 = Math.max(f, f3) + f7;
        float f10 = Math.min(f2, f4) - f7;
        float f11 = Math.max(f2, f4) + f7;
        FloatPair floatPair = new FloatPair(f, f2);
        FloatPair floatPair2 = new FloatPair(f3, f4);
        RenderVector3f renderVector3f = new RenderVector3f(f5, f6, 0.0f);
        RenderVector4f renderVector4f = new RenderVector4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        this.b(PrimitiveTopology.QUADS);
        this.q(18.0f, new RenderVector3f(f8, f10, 0.0f), this.z, renderVector4f, 0.0f, 0.0f, floatPair, 0.0f, 0.0f, this.z, floatPair2, this.w, renderVector3f, 0.0f, this.w);
        this.q(18.0f, new RenderVector3f(f8, f11, 0.0f), this.z, renderVector4f, 0.0f, 0.0f, floatPair, 0.0f, 0.0f, this.z, floatPair2, this.w, renderVector3f, 0.0f, this.w);
        this.q(18.0f, new RenderVector3f(f9, f10, 0.0f), this.z, renderVector4f, 0.0f, 0.0f, floatPair, 0.0f, 0.0f, this.z, floatPair2, this.w, renderVector3f, 0.0f, this.w);
        this.q(18.0f, new RenderVector3f(f9, f11, 0.0f), this.z, renderVector4f, 0.0f, 0.0f, floatPair, 0.0f, 0.0f, this.z, floatPair2, this.w, renderVector3f, 0.0f, this.w);
        this.B("quad");
        return this;
    }

    public VertexCoordinateMode m() {
        return this.E;
    }

    public void o(int n, float f) {
        this.J[n] = f;
    }

    public PrimitiveTopology q() {
        return this.H;
    }

    private void o(float f, float f2, float f3, float f4, float f5, RenderVector4f renderVector4f, float f6, RenderVector4f renderVector4f2, RenderVector4f renderVector4f3) {
        RenderVector3f renderVector3f = new RenderVector3f(f2, f3, f4);
        this.q(f, renderVector3f, this.z, renderVector4f2, 0.0f, 0.0f, this.z, 0.0f, 0.0f, this.z, this.z, renderVector4f, new RenderVector3f(f5, 0.0f, 0.0f), f6, renderVector4f3);
    }

    private void q(float f, RenderVector3f renderVector3f, FloatPair floatPair, RenderVector4f renderVector4f, float f2, float f3, FloatPair floatPair2, float f4, float f5, FloatPair floatPair3, FloatPair floatPair4, RenderVector4f renderVector4f2, RenderVector3f renderVector3f2, float f6, RenderVector4f renderVector4f3) {
        float f7 = 0.003921569f;
        float[] fArray = new float[]{f, renderVector3f.t, renderVector3f.n, renderVector3f.x, floatPair.l, floatPair.S, renderVector4f.N * f7, renderVector4f.w * f7, renderVector4f.Y * f7, renderVector4f.J * f7, f2, f3, floatPair2.l, floatPair2.S, f4, f5, floatPair3.l, floatPair3.S, floatPair4.l, floatPair4.S, renderVector4f2.N, renderVector4f2.w, renderVector4f2.Y, renderVector4f2.J, renderVector3f2.t, renderVector3f2.n, renderVector3f2.x, f6, renderVector4f3.N, renderVector4f3.w, renderVector4f3.Y, renderVector4f3.J};
        int n = this.v * RenderBatchManager.M().s().Q();
        System.arraycopy(fArray, 0, this.J, n, fArray.length);
        ++this.v;
    }

    public Supplier<Void> d() {
        return this.x;
    }

    public RenderBatchBuilder V(Supplier<Void> supplier) {
        this.x = supplier;
        return this;
    }

    private void j(float f, float f2, float f3, float f4, RenderVector3f renderVector3f, RenderVector4f renderVector4f, RenderVector4f renderVector4f2) {
        RenderVector3f renderVector3f2 = new RenderVector3f(f2, f3, f4);
        this.q(f, renderVector3f2, this.z, renderVector4f2, 0.0f, 0.0f, this.z, 0.0f, 0.0f, this.z, this.z, renderVector4f, renderVector3f, 0.0f, this.w);
    }

    public RenderBatchBuilder d(float f, float f2, float f3, float f4, Color color) {
        float f5 = f + f3;
        float f6 = f2 + f4;
        RenderVector4f renderVector4f = new RenderVector4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        this.b(PrimitiveTopology.QUADS);
        this.V(9.0f, f, f2, 0.0f, renderVector4f);
        this.V(9.0f, f, f6, 0.0f, renderVector4f);
        this.V(9.0f, f5, f2, 0.0f, renderVector4f);
        this.V(9.0f, f5, f6, 0.0f, renderVector4f);
        this.B("quad");
        return this;
    }

    public RenderBatchBuilder b(float f, float f2, float f3, float f4, float f5, float f6, float f7, Color color) {
        RenderVector4f renderVector4f = new RenderVector4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        this.b(PrimitiveTopology.LINES);
        this.L(f7);
        this.V(9.0f, f, f2, f3, renderVector4f);
        this.V(9.0f, f4, f5, f6, renderVector4f);
        this.B("line");
        return this;
    }

    public RenderBatchBuilder v(float f, float f2, float f3, float f4, Color color) {
        float f5 = Math.max(f4 / 2.0f - 0.5f, 0.25f);
        float f6 = f2 + f4 / 2.0f;
        float f7 = f + f4 / 2.0f;
        float f8 = f + f3 - f4 / 2.0f;
        float f9 = 0.5f;
        float f10 = f - f9;
        float f11 = f2 - f9;
        float f12 = f + f3 + f9;
        float f13 = f2 + f4 + f9;
        RenderVector4f renderVector4f = new RenderVector4f(f7, f6, f8, f6);
        RenderVector3f renderVector3f = new RenderVector3f(f5, 0.0f, 0.0f);
        RenderVector4f renderVector4f2 = new RenderVector4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        this.b(PrimitiveTopology.QUADS);
        this.j(19.0f, f10, f11, 0.0f, renderVector3f, renderVector4f, renderVector4f2);
        this.j(19.0f, f10, f13, 0.0f, renderVector3f, renderVector4f, renderVector4f2);
        this.j(19.0f, f12, f11, 0.0f, renderVector3f, renderVector4f, renderVector4f2);
        this.j(19.0f, f12, f13, 0.0f, renderVector3f, renderVector4f, renderVector4f2);
        this.B("quad");
        return this;
    }

    public RenderBatchBuilder P(float f, float f2, float f3, float f4, float f5, float f6, float f7, Color color) {
        float f8 = (f3 += f5 * 4.0f) / 2.0f;
        FloatPair floatPair = new FloatPair((f -= f5 * 2.0f) + f8, (f2 -= f5 * 2.0f) + f8);
        f8 = f8 - (f4 /= 2.0f) - 1.0f;
        float f9 = f8 - f4;
        float f10 = f + f3;
        float f11 = f2 + f3;
        RenderVector4f renderVector4f = new RenderVector4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        if (f7 != -360.0f) {
            f7 %= 360.0f;
        }
        float f12 = f6 % 360.0f + f7 * 0.5f;
        this.b(PrimitiveTopology.QUADS);
        this.U(0.0f, f, f2, 0.0f, f9, f8, f5, floatPair, f12, f7, renderVector4f);
        this.U(0.0f, f, f11, 0.0f, f9, f8, f5, floatPair, f12, f7, renderVector4f);
        this.U(0.0f, f10, f2, 0.0f, f9, f8, f5, floatPair, f12, f7, renderVector4f);
        this.U(0.0f, f10, f11, 0.0f, f9, f8, f5, floatPair, f12, f7, renderVector4f);
        this.B("quad");
        return this;
    }

    public RenderBatchBuilder(int n) {
        this(n, VertexCoordinateMode.DEFAULT, false);
    }

    public GlImageTexture C() {
        return this.V;
    }

    public GlScissorRect c() {
        return this.G;
    }

    public RenderBatchBuilder k(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, Color color) {
        float f11;
        if (f3 == f4) {
            f11 = f5 / f6;
            f3 *= f11;
        }
        f11 = f + f3;
        float f12 = f2 + f4;
        RenderVector4f renderVector4f = new RenderVector4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        this.b(PrimitiveTopology.QUADS);
        this.w(14.0f, f, f2, 0.0f, f7, f8, renderVector4f);
        this.w(14.0f, f, f12, 0.0f, f7, f10, renderVector4f);
        this.w(14.0f, f11, f2, 0.0f, f9, f8, renderVector4f);
        this.w(14.0f, f11, f12, 0.0f, f9, f10, renderVector4f);
        this.B("quad");
        return this;
    }

    public RenderBatchBuilder t(float f, float f2, SmoothFontGlyph smoothFontGlyph, int n, Color color, float f3) {
        float f4 = f + smoothFontGlyph.X * f3;
        float f5 = f + smoothFontGlyph.g * f3;
        float f6 = f2 + smoothFontGlyph.a * f3;
        float f7 = f2 + smoothFontGlyph.G * f3;
        this.o(new GlImageTexture(n));
        RenderVector4f renderVector4f = new RenderVector4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        this.b(PrimitiveTopology.QUADS);
        this.w(15.0f, f4, f6, 0.0f, smoothFontGlyph.t, smoothFontGlyph.J, renderVector4f);
        this.w(15.0f, f4, f7, 0.0f, smoothFontGlyph.t, smoothFontGlyph.B, renderVector4f);
        this.w(15.0f, f5, f6, 0.0f, smoothFontGlyph.w, smoothFontGlyph.J, renderVector4f);
        this.w(15.0f, f5, f7, 0.0f, smoothFontGlyph.w, smoothFontGlyph.B, renderVector4f);
        this.B("quad");
        return this;
    }

    public RenderBatchBuilder q(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, Color color) {
        return this.k(f, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, color, color);
    }

    public RenderBatchBuilder Y(float f, float f2, float f3, float f4, float f5, float f6, Color color, float f7, float f8, float f9, Color color2, Color color3, float f10, float f11, Color color4, boolean bl) {
        if (f3 == 0.0f || f4 == 0.0f) {
            return this;
        }
        float f12 = f5;
        float f13 = f - f12;
        float f14 = f2 - f12;
        float f15 = f + f3 + f12;
        float f16 = f2 + f4 + f12;
        float f17 = 0.003921569f;
        RenderVector4f renderVector4f = new RenderVector4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        RenderVector4f renderVector4f2 = new RenderVector4f((float)color2.getRed() * f17, (float)color2.getGreen() * f17, (float)color2.getBlue() * f17, (float)color2.getAlpha() * f17);
        FloatPair floatPair = new FloatPair((float)color3.getRed() * f17, (float)color3.getGreen() * f17);
        FloatPair floatPair2 = new FloatPair((float)color3.getBlue() * f17, (float)color3.getAlpha() * f17);
        FloatPair floatPair3 = new FloatPair((float)color4.getBlue() * f17, (float)color4.getAlpha() * f17);
        float f18 = (float)color4.getRed() * f17;
        float f19 = (float)color4.getGreen() * f17;
        RenderVector4f renderVector4f3 = new RenderVector4f(f, f2, f3, f4);
        RenderVector3f renderVector3f = new RenderVector3f(f6, f18, f19);
        FloatPair floatPair4 = new FloatPair(f7, f8);
        float f20 = bl ? 1.0f : 0.0f;
        this.b(PrimitiveTopology.QUADS);
        this.q(13.0f, new RenderVector3f(f13, f14, 0.0f), floatPair3, renderVector4f, f9, f11, floatPair4, f10, f20, floatPair, floatPair2, renderVector4f3, renderVector3f, f12, renderVector4f2);
        this.q(13.0f, new RenderVector3f(f13, f16, 0.0f), floatPair3, renderVector4f, f9, f11, floatPair4, f10, f20, floatPair, floatPair2, renderVector4f3, renderVector3f, f12, renderVector4f2);
        this.q(13.0f, new RenderVector3f(f15, f14, 0.0f), floatPair3, renderVector4f, f9, f11, floatPair4, f10, f20, floatPair, floatPair2, renderVector4f3, renderVector3f, f12, renderVector4f2);
        this.q(13.0f, new RenderVector3f(f15, f16, 0.0f), floatPair3, renderVector4f, f9, f11, floatPair4, f10, f20, floatPair, floatPair2, renderVector4f3, renderVector3f, f12, renderVector4f2);
        this.B("quad");
        return this;
    }

    public RenderBatchBuilder(int n, VertexCoordinateMode vertexCoordinateMode, boolean bl) {
        this.b = new RenderVector3f(0.0f, 0.0f, 0.0f);
        this.w = new RenderVector4f(0.0f, 0.0f, 0.0f, 0.0f);
        this.E = vertexCoordinateMode;
        this.C = BufferedGuiRenderPrimitives.X.c().m();
        if (BufferedGuiRenderPrimitives.u != null) {
            this.G = new GlScissorRect(BufferedGuiRenderPrimitives.u.v, BufferedGuiRenderPrimitives.u.F, BufferedGuiRenderPrimitives.u.I, BufferedGuiRenderPrimitives.u.f);
        }
        this.c = bl;
        this.I = n;
        this.g = BufferedGuiRenderPrimitives.b.L();
    }

    public Supplier<Void> h() {
        return this.o;
    }

    public void N(String string, int n) {
        this.a += this.H.verticesCount * n;
        switch (string) {
            case "quad": {
                this.f[0] = this.a;
                this.f[1] = this.a + 1;
                this.f[2] = this.a + 2;
                this.f[3] = this.a + 1;
                this.f[4] = this.a + 3;
                this.f[5] = this.a + 2;
                break;
            }
            case "line": {
                this.f[0] = this.a;
                this.f[1] = this.a + 1;
                break;
            }
            case "triangle": {
                this.f[0] = this.a;
                this.f[1] = this.a + 2;
                this.f[2] = this.a + 1;
                break;
            }
            default: {
                throw new IllegalArgumentException("Unknown mode: " + string);
            }
        }
    }

    public RenderBatchBuilder Q(PrimitiveTopology primitiveTopology, int n) {
        if (this.H != null) {
            return this;
        }
        this.H = primitiveTopology;
        this.J = new float[n * this.I];
        this.f = new int[primitiveTopology.indicesCount];
        return this;
    }

    public GlCapabilityState A() {
        return this.g;
    }

    public RenderBatchBuilder o(GlImageTexture glImageTexture) {
        this.V = glImageTexture;
        return this;
    }

    public RenderBatchBuilder c(float f, float f2, float f3, float f4, Color color) {
        return this.X(f, f2, f3, f4, color, 1.5f, 1.0f, 0);
    }

    public RenderBatchBuilder k(float f, float f2, float f3, float f4, Color color) {
        f -= f4 / 2.0f;
        f2 -= f4 / 2.0f;
        float f5 = f3 += f4;
        float f6 = f3;
        if (color == null) {
            color = Color.WHITE;
        }
        float f7 = f + f5;
        float f8 = f2 + f6;
        float f9 = f3 / 2.0f;
        FloatPair floatPair = new FloatPair(f + f5 / 2.0f, f2 + f6 / 2.0f);
        RenderVector4f renderVector4f = new RenderVector4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        this.b(PrimitiveTopology.QUADS);
        float f10 = this.V != null ? this.V.C : 0.0f;
        float f11 = this.V != null ? this.V.w : 0.0f;
        float f12 = this.V != null ? this.V.h : 1.0f;
        float f13 = this.V != null ? this.V.p : 1.0f;
        this.d(4.0f, f, f2, 0.0f, f10, f11, f9, f4, floatPair, renderVector4f);
        this.d(4.0f, f, f8, 0.0f, f10, f13, f9, f4, floatPair, renderVector4f);
        this.d(4.0f, f7, f2, 0.0f, f12, f11, f9, f4, floatPair, renderVector4f);
        this.d(4.0f, f7, f8, 0.0f, f12, f13, f9, f4, floatPair, renderVector4f);
        this.B("quad");
        return this;
    }

    private void N(float f, float f2, float f3, float f4, RenderVector4f renderVector4f, float f5, float f6, RenderVector4f renderVector4f2, RenderVector4f renderVector4f3) {
        RenderVector3f renderVector3f = new RenderVector3f(f2, f3, f4);
        RenderVector3f renderVector3f2 = new RenderVector3f(f5, 0.0f, 0.0f);
        this.q(f, renderVector3f, this.z, renderVector4f3, 0.0f, f6, this.z, 0.0f, 0.0f, this.z, this.z, renderVector4f, renderVector3f2, 0.0f, renderVector4f2);
    }

    public RenderBatchBuilder a(float f, float f2, float f3, float f4, float f5, float f6, int n, Color color) {
        float f7 = 0.5f;
        if (f5 <= 0.0f) {
            f7 = 0.0f;
        }
        float f8 = f6;
        float f9 = Math.max(0.0f, (f5 += f7 * 2.0f) - f8);
        if (f5 > 0.0f) {
            f = (float)((double)f - ((double)f8 - 0.5));
            f2 -= f8;
            f4 = (float)((double)f4 + (double)f8 * 1.5);
            f3 += f8 * 1.0f;
        }
        float f10 = f + f7;
        float f11 = f + f3 - f7;
        float f12 = f2 + f7;
        float f13 = f2 + f4 - f7;
        boolean bl = (n & 1) != 0;
        boolean bl2 = (n & 2) != 0;
        boolean bl3 = (n & 4) != 0;
        boolean bl4 = (n & 8) != 0;
        RenderVector4f renderVector4f = new RenderVector4f(bl ? 1.0f : 0.0f, bl2 ? 1.0f : 0.0f, bl3 ? 1.0f : 0.0f, bl4 ? 1.0f : 0.0f);
        RenderVector4f renderVector4f2 = new RenderVector4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        RenderVector4f renderVector4f3 = new RenderVector4f(f + f5, f2 + f5, f + f3 - f5, f2 + f4 - f5);
        this.N(12.0f, f10, f12, 0.0f, renderVector4f3, f9, f8, renderVector4f, renderVector4f2);
        this.N(12.0f, f10, f13, 0.0f, renderVector4f3, f9, f8, renderVector4f, renderVector4f2);
        this.N(12.0f, f11, f12, 0.0f, renderVector4f3, f9, f8, renderVector4f, renderVector4f2);
        this.N(12.0f, f11, f13, 0.0f, renderVector4f3, f9, f8, renderVector4f, renderVector4f2);
        return this;
    }

    private void V(float f, float f2, float f3, float f4, RenderVector4f renderVector4f) {
        this.n(f, f2, f3, f4, this.w, 0.0f, renderVector4f);
    }

    public RenderBatchBuilder G(float f, float f2, float f3, float f4, Color color, float f5) {
        return this.X(f, f2, f3, f4, color, f5, 1.0f, 0);
    }

    public RenderBatchBuilder r(float f, float f2, float f3, float f4, float f5, float f6, Color color) {
        float f7 = f - f5;
        float f8 = f + f3 + f5;
        float f9 = f2 - f5;
        float f10 = f2 + f4 + f5;
        RenderVector4f renderVector4f = new RenderVector4f(f, f2, f3, f4);
        RenderVector4f renderVector4f2 = new RenderVector4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        this.b(PrimitiveTopology.QUADS);
        this.z(8.0f, f7, f9, 0.0f, renderVector4f, f5, f6, renderVector4f2);
        this.z(8.0f, f7, f10, 0.0f, renderVector4f, f5, f6, renderVector4f2);
        this.z(8.0f, f8, f9, 0.0f, renderVector4f, f5, f6, renderVector4f2);
        this.z(8.0f, f8, f10, 0.0f, renderVector4f, f5, f6, renderVector4f2);
        this.B("quad");
        return this;
    }

    private void k(float f, float f2, float f3, float f4, float f5, float f6, FloatPair floatPair, RenderVector4f renderVector4f) {
        this.d(f, f2, f3, f4, 0.0f, 0.0f, f5, f6, floatPair, renderVector4f);
    }

    private void w(float f, float f2, float f3, float f4, float f5, float f6, RenderVector4f renderVector4f) {
        this.x(f, f2, f3, f4, f5, f6, 0.0f, this.w, 0.0f, renderVector4f);
    }

    public RenderBatchBuilder A(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        float f9 = 0.0f;
        float f10 = 1.0f;
        float f11 = 1.0f;
        float f12 = 0.0f;
        float f13 = f + f3;
        float f14 = f2 + f4;
        RenderVector4f renderVector4f = new RenderVector4f(f + f6, f2 + f6, f + f3 - f6, f2 + f4 - f6);
        FloatPair floatPair = new FloatPair(f3 * 2.0f, f4 * 2.0f);
        this.b(PrimitiveTopology.QUADS);
        this.Q(1.0f, f, f2, 0.0f, f9, f10, f7, f5, f6, f8, renderVector4f, floatPair);
        this.Q(1.0f, f, f14, 0.0f, f9, f12, f7, f5, f6, f8, renderVector4f, floatPair);
        this.Q(1.0f, f13, f2, 0.0f, f11, f10, f7, f5, f6, f8, renderVector4f, floatPair);
        this.Q(1.0f, f13, f14, 0.0f, f11, f12, f7, f5, f6, f8, renderVector4f, floatPair);
        this.B("quad");
        return this;
    }

    public RenderBatchBuilder Z(float f, float f2, SmoothFontGlyph smoothFontGlyph, int n, Color color, float f3) {
        float f4 = f + smoothFontGlyph.X * f3;
        float f5 = f + smoothFontGlyph.g * f3;
        float f6 = f2 + smoothFontGlyph.a * f3;
        float f7 = f2 + smoothFontGlyph.G * f3;
        this.o(new GlImageTexture(n));
        RenderVector4f renderVector4f = new RenderVector4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        this.b(PrimitiveTopology.QUADS);
        this.w(16.0f, f4, f6, 0.0f, smoothFontGlyph.t, smoothFontGlyph.J, renderVector4f);
        this.w(16.0f, f4, f7, 0.0f, smoothFontGlyph.t, smoothFontGlyph.B, renderVector4f);
        this.w(16.0f, f5, f6, 0.0f, smoothFontGlyph.w, smoothFontGlyph.J, renderVector4f);
        this.w(16.0f, f5, f7, 0.0f, smoothFontGlyph.w, smoothFontGlyph.B, renderVector4f);
        this.B("quad");
        return this;
    }

    public RenderBatchBuilder E(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, Color color, Color color2) {
        RenderVector4f renderVector4f = new RenderVector4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        RenderVector4f renderVector4f2 = new RenderVector4f(color2.getRed(), color2.getGreen(), color2.getBlue(), color2.getAlpha());
        this.b(PrimitiveTopology.QUADS);
        this.V(9.0f, f4, f5, f6, renderVector4f);
        this.V(9.0f, f, f2, f3, renderVector4f);
        this.V(9.0f, f7, f8, f9, renderVector4f2);
        this.V(9.0f, f10, f11, f12, renderVector4f2);
        this.B("quad");
        return this;
    }
}

