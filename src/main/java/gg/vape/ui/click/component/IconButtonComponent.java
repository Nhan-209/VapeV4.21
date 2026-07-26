package gg.vape.ui.click.component;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import java.awt.Color;

@Deprecated
public class IconButtonComponent
extends InteractiveComponent {
    private String v;
    protected double K;
    private static String[] Q;
    private Color I;
    private Color cZ = null;
    private double b = -1.0;

    public IconButtonComponent(String string, double d, double d2, double d3, Color color, Color color2, Color color3) {
        this.v = string;
        this.K = d;
        this.o(d2);
        this.Y(d3);
        this.o(color != null ? color : IconButtonComponent.J.W);
        this.P(color2 != null ? color2 : IconButtonComponent.J.f);
        this.cZ = color3;
    }

    public IconButtonComponent(String string, double d, Color color) {
        this(string, d, 13.0, 13.0, null, null, color);
    }

    public static void f(String[] stringArray) {
        Q = stringArray;
    }

    public void G(Color color) {
        this.I = color;
    }

    public double e$src$D$yci9n1() {
        return this.K;
    }

    public void l(Color color) {
        this.cZ = color;
    }

    public static String[] c$src$ALjava_lang_String_$653i16() {
        return Q;
    }

    public IconButtonComponent(String string, double d, double d2, double d3) {
        this(string, d, d2, d3, null, null, null);
    }

    public void H(String string) {
        this.v = string;
    }

    @Override
    public double x() {
        return 0.0;
    }

    static {
        if (IconButtonComponent.c$src$ALjava_lang_String_$653i16() != null) {
            IconButtonComponent.f(new String[1]);
        }
    }

    private static ObfuscatedRuntimeException c(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void E(double d) {
        this.b = d;
    }

    public String T$src$Ljava_lang_String_$1x2cerw() {
        return this.v;
    }

    @Override
    public double C() {
        return 0.0;
    }

    @Override
    public void H() {
        double d;
        double d2;
        if (this.b != -1.0) {
            d2 = (double)((float)ImageRenderer.m(this.v)) / this.b;
            d = (double)((float)ImageRenderer.j(this.v)) / this.b;
        } else {
            d2 = d = (double)(8.0f * (float)this.K);
        }
        if (this.I != null) {
            GuiRenderPrimitives.F(this.v, this.G$src$D$1b2f02a() + this.A() / 2.0, this.n() + this.L() / 2.0, d2, d, this.I);
        } else {
            GuiRenderPrimitives.F(this.v, this.G$src$D$1b2f02a() + this.A() / 2.0, this.n() + this.L() / 2.0, d2, d, this.w$src$Z$e457mb() ? this.N() : this.e$src$Ljava_awt_Color_$1yl68fq());
        }
        if (this.cZ != null) {
            GuiRenderPrimitives.P(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.w$src$Z$e457mb() ? this.cZ.brighter() : this.cZ, 2.0f, 0.8f, 1.0f);
        }
    }

    public IconButtonComponent(String string, double d) {
        this(string, d, 13.0, 13.0, null, null, null);
    }

    public void A(double d) {
        this.K = d;
    }

    public IconButtonComponent(String string) {
        this(string, 1.0, 13.0, 13.0, null, null, null);
    }

    public Color D$src$Ljava_awt_Color_$os7bh8() {
        return this.I;
    }

    public IconButtonComponent(String string, double d, Color color, Color color2, double d2, double d3) {
        this(string, d, d2, d3, color, color2, null);
    }
}

