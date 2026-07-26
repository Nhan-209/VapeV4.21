package gg.vape.ui.click.component;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.IconGlyphComponent;
import gg.vape.ui.click.component.IconShape;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;
import java.util.function.Supplier;
import org.jetbrains.annotations.Nullable;

public class ShapeIconComponent
extends GuiComponent {
    @Nullable
    private IconGlyphComponent a;
    private double G;
    private TruncatedTextComponent O;
    private Supplier<Integer> i;
    private Integer o;
    private Color hX;
    private static final String cb;
    private static int R;
    private String K;
    private IconShape hV;
    private float hz;
    private double b;
    private Color v;
    private double Q;
    private double I;

    public double I$src$D$gy5f0q() {
        return this.I;
    }

    public Color w$src$Ljava_awt_Color_$738qkw() {
        return this.v;
    }

    static {
        ShapeIconComponent.G(13);
        cb = "9+";
    }

    public ShapeIconComponent j(IconShape iconShape) {
        this.hV = iconShape;
        return this;
    }

    @Override
    public double x() {
        return this.b;
    }

    public IconShape o$src$Lgg_vape_ui_click_component_IconShape_$20egvb() {
        return this.hV;
    }

    public ShapeIconComponent y(String string) {
        this.K = string == null ? "" : string;
        this.o = null;
        this.i = null;
        this.O.O(this.K.toUpperCase());
        return this;
    }

    public float O$src$F$h1g6mm() {
        return this.hz;
    }

    public static void G(int n) {
        R = n;
    }

    public Supplier<Integer> E() {
        return this.i;
    }

    public static int I$src$I$gy5f51() {
        return R;
    }

    public ShapeIconComponent W(Color color) {
        this.hX = color;
        return this;
    }

    public double T$src$D$h475jp() {
        return this.G;
    }

    public Integer n$src$Ljava_lang_Integer_$lq0x92() {
        return this.o;
    }

    public ShapeIconComponent H(double d) {
        this.I = d;
        return this;
    }

    public void d$src$V$hczvgz() {
        this.o = null;
        this.i = null;
        this.K = null;
        this.O.O("");
    }

    public ShapeIconComponent(IconShape iconShape, String string, double d, double d2, double d3, float f, Color color, Color color2, double d4) {
        this.hV = iconShape;
        this.hX = color;
        this.v = color2;
        this.Q = d4;
        this.d(false);
        this.Y(d);
        this.I = d;
        this.b = d2;
        this.G = d3;
        this.hz = f;
        this.O = new TruncatedTextComponent(string == null ? "" : string, "", 0.0, d4, color2, true);
        this.O.K(false);
        this.O.D(32767.0);
        this.H(this.O);
        this.a = null;
    }

    public ShapeIconComponent x(Supplier<Integer> supplier) {
        this.i = supplier;
        this.o = null;
        this.K = null;
        return this;
    }

    public ShapeIconComponent H(Integer n) {
        this.o = n;
        this.i = null;
        this.K = null;
        return this;
    }

    @Nullable
    private String getName() {
        Integer n = null;
        if (this.i != null) {
            n = this.i.get();
        } else if (this.o != null) {
            n = this.o;
        }
        if (n != null) {
            if (n <= 0) {
                return "";
            }
            if (n > 9) {
                return cb;
            }
            return String.valueOf(n);
        }
        if (this.K != null) {
            return this.K;
        }
        return null;
    }

    public ShapeIconComponent g(float f) {
        this.hz = f;
        return this;
    }

    public double O$src$D$h1g6kw() {
        if (this.hV == IconShape.CIRCLE) {
            return this.I;
        }
        String string = this.getName();
        double d = string == null || string.isEmpty() ? Math.ceil(this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(this.Q).N(this.O.S$src$Ljava_lang_String_$1bp7ddx())) : Math.ceil(this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(this.Q).N(string));
        return Math.max(this.b, d + this.G * 2.0);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Nullable
    public IconGlyphComponent M() {
        return this.a;
    }

    public ShapeIconComponent O(Color color) {
        this.v = color;
        return this;
    }

    public ShapeIconComponent U$src$Lgg_vape_ui_click_component_ShapeIconComponent_$1h1w58a(double d) {
        this.G = d;
        return this;
    }

    public TruncatedTextComponent d$src$Lgg_vape_ui_click_component_TruncatedTextCompone$1ti9i2w() {
        return this.O;
    }

    public static int X$src$I$h6ec1g() {
        int n = ShapeIconComponent.I$src$I$gy5f51();
        return 0;
    }

    public ShapeIconComponent d(double d) {
        this.b = d;
        return this;
    }

    public void W(@Nullable IconGlyphComponent iconGlyphComponent) {
        if (this.a != null) {
            this.a.Z(false);
        }
        this.a = iconGlyphComponent;
        if (this.a != null) {
            this.H(this.a);
        }
    }

    public void O(String string) {
        this.K = string == null ? "" : string;
        this.o = null;
        this.i = null;
        this.O.O(this.K == null ? "" : this.K.toUpperCase());
    }

    public ShapeIconComponent x(double d) {
        this.Q = d;
        return this;
    }

    @Override
    public void H() {
        double d = this.G$src$D$1b2f02a();
        double d2 = this.n();
        double d3 = this.A();
        double d4 = this.L();
        String string = this.getName();
        if (string != null) {
            this.O.O(string.toUpperCase());
        }
        double d5 = Math.min(d3, d4);
        double d6 = d + (d3 - d5) / 2.0;
        double d7 = d2 + (d4 - d5) / 2.0;
        if (this.hV == IconShape.CIRCLE) {
            GuiRenderPrimitives.B(d, d2, d3, d4, this.hX, (float)(this.I / 2.0));
            if (this.a != null) {
                this.a.K(d6);
                this.a.S(d7);
                this.a.o(d5);
                this.a.Y(d5);
                this.a.c();
            } else {
                this.O.K(d);
                this.O.S(d2);
                this.O.o(d3);
                this.O.Y(d4);
                this.O.D(d3);
                this.O.R(this.v != null ? this.v : Color.WHITE);
                this.O.N(true);
            }
        } else {
            GuiRenderPrimitives.B(d, d2, d3, d4, this.hX, this.hz);
            if (this.a != null) {
                this.a.K(d6);
                this.a.S(d7);
                this.a.o(d5);
                this.a.Y(d5);
                this.a.c();
            } else {
                this.O.K(d);
                this.O.S(d2);
                this.O.o(d3);
                this.O.Y(d4);
                this.O.D(Math.max(0.0, d3 - this.G * 2.0));
                if (this.v != null) {
                    this.O.R(this.v);
                }
                this.O.N(true);
            }
        }
    }

    public ShapeIconComponent(IconShape iconShape, IconGlyphComponent iconGlyphComponent, double d, Color color) {
        this.hV = iconShape;
        this.hX = color;
        this.v = null;
        this.Q = 0.0;
        this.d(false);
        this.Y(d);
        this.I = d;
        this.b = d;
        this.G = 0.0;
        this.hz = (float)(d / 2.0);
        this.O = new TruncatedTextComponent("", "", 0.0, 0.5, Color.WHITE, true);
        this.O.K(false);
        this.O.D(32767.0);
        this.a = iconGlyphComponent;
        if (this.a != null) {
            this.H(this.a);
        }
    }

    public double s() {
        return this.Q;
    }

    @Override
    public Color d() {
        return this.hX;
    }

    public String u$src$Ljava_lang_String_$9kgbbm() {
        return this.K;
    }
}

