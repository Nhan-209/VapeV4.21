package gg.vape.ui.click.component;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import java.awt.Color;
import org.jetbrains.annotations.Nullable;

public class GlyphIconComponent
extends InteractiveComponent {
    private double o1;
    private double o6;
    private double K;
    private ColorAnimation o0 = null;
    @Nullable
    private Color I;
    private float b = 2.0f;
    private float Q = 0.8f;
    private boolean v = false;
    private double oh;
    private String o8;
    private boolean oS = false;

    public boolean E() {
        return this.v;
    }

    public ColorAnimation z$src$Lgg_vape_ui_click_animation_ColorAnimation_$om4cxn() {
        return this.o0;
    }

    public String D$src$Ljava_lang_String_$gcdtym() {
        return this.o8;
    }

    public void A(double d) {
        this.K = d;
    }

    public void R(boolean bl) {
        this.oS = bl;
    }

    public boolean r$src$Z$1tc3woi() {
        return this.oS;
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public GlyphIconComponent(String string, double d, double d2, double d3, double d4, @Nullable Color color, @Nullable Color color2, @Nullable Color color3) {
        this.o8 = string;
        this.o(d3);
        this.Y(d4);
        this.o6 = d;
        this.oh = d2;
        this.o(color != null ? color : GlyphIconComponent.J.W);
        this.P(color2 != null ? color2 : GlyphIconComponent.J.f);
        this.I = color3;
    }

    public void p(ColorAnimation colorAnimation) {
        this.o0 = colorAnimation;
    }

    @Override
    public double x() {
        return 0.0;
    }

    public void W(String string) {
        this.o8 = string;
    }

    public void E(Color color, Color color2) {
        if (color2 == null) {
            this.o0 = null;
            return;
        }
        this.getClass();
        this.o0 = new ColorAnimation(0.15, color, color2);
    }

    @Override
    public double C() {
        return 0.0;
    }

    public void j(@Nullable Color color) {
        this.I = color;
    }

    public double J$src$D$1sq44f4() {
        return this.o1;
    }

    public void U(double d) {
        this.oh = d;
    }

    public double p() {
        return this.K;
    }

    public float B$src$F$1slprpy() {
        return this.b;
    }

    public void i(float f) {
        this.b = f;
    }

    @Override
    public void H() {
        double d = this.G$src$D$1b2f02a();
        double d2 = this.n();
        if (this.v) {
            d += this.A() / 2.0 - this.o6 / 2.0;
        }
        d += this.K;
        if (this.oS) {
            d2 += this.L() / 2.0 - this.oh / 2.0;
        }
        d2 += this.o1;
        if (this.o0 != null) {
            this.o0.u(this.w$src$Z$e457mb());
            if ((double)this.b == this.A() / 2.0 && (double)this.b == this.L() / 2.0) {
                GuiRenderPrimitives.V(this.G$src$D$1b2f02a(), this.n(), this.b * 2.0f, 1.0, this.o0.getInterpolatedColor());
            } else {
                GuiRenderPrimitives.e(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.o0.getInterpolatedColor(), false, this.b, 1.0f);
            }
        }
        Color color = this.e$src$Ljava_awt_Color_$1yl68fq();
        if (this.N() != null && this.w$src$Z$e457mb()) {
            color = this.N();
        }
        ImageRenderer.E(color, (int)d, (int)d2, this.o8, (float)this.o6, (float)this.oh, false);
        if (this.I != null) {
            GuiRenderPrimitives.P(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.w$src$Z$e457mb() ? this.I.brighter() : this.I, this.b, this.Q, 1.0f);
        }
    }

    public void Z(float f) {
        this.Q = f;
    }

    public void q(boolean bl) {
        this.v = bl;
    }

    public double v() {
        return this.oh;
    }

    @Nullable
    public Color I$src$Ljava_awt_Color_$18e48cp() {
        return this.I;
    }

    public double Y$src$D$1syd1bj() {
        return this.o6;
    }

    public void d(double d) {
        this.o6 = d;
    }

    public void w(double d) {
        this.o1 = d;
    }

    public float z() {
        return this.Q;
    }
}

