package gg.vape.ui.click.component.value;

import func.skidline.RectData;
import gg.vape.input.MouseInput;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.MouseButton;
import gg.vape.ui.click.MousePosition;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.animation.DoubleAnimation;
import gg.vape.ui.click.component.value.SliderComponentBase;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.RenderUtils;
import gg.vape.value.ColorValue;
import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ColorPaletteSliderComponent
extends SliderComponentBase {
    private double hQ;
    private boolean hY;
    private Color R;
    private boolean hN;
    private double hD = 0.0;
    private double hu = 0.0;
    private int hr;
    private double O = 1.0;
    private ColorAnimation hH;
    private boolean K;
    private ColorValue hE;
    private boolean a;
    protected RectData v;
    private double hi;
    private double hK = 0.75;
    protected int h3;
    private Color[] h2;
    private DoubleAnimation hA;
    protected DoubleAnimation ho = new DoubleAnimation(0.15, 7.0, 8.0);

    public ColorValue Z$src$Lgg_vape_value_ColorValue_$1er4i1l() {
        return this.hE;
    }

    public DoubleAnimation I$src$Lgg_vape_ui_click_animation_DoubleAnimation_$f0r39g() {
        return this.hA;
    }

    public void v() {
        double d = this.A() - 10.0 - (double)this.h2.length * 0.5;
        double d2 = d / (double)this.h2.length;
        double d3 = this.X$src$D$1ej56qc() - 1.0;
        double d4 = (double)this.h3 / d3;
        d = this.A() - 10.0 - (double)(this.hN ? 2 : 0);
        double d5 = 5.0 + d * d4;
        double d6 = this.hA.getInterpolatedValue();
        this.hA = new DoubleAnimation(0.15, d6, (d5 += (d4 - 0.5) * -1.0 * d2) + (this.hN ? 0.5 : 0.0));
        this.hA.c();
        this.hH = new ColorAnimation(0.15, this.h2[this.hr], this.h2[this.h3]);
        this.hH.c();
    }

    @Override
    public void F() {
        if (!this.hY) {
            this.ho.J();
        }
        this.hY = true;
    }

    public double J$src$D$1ebg2fa() {
        return this.hu;
    }

    public double X$src$D$1ej56qc() {
        return this.hQ;
    }

    @Override
    public void H() {
        double d;
        this.V$src$V$1ei1lz4();
        SmoothFontRenderer smoothFontRenderer = this.O(this.hK);
        double d2 = smoothFontRenderer.d(this.W$src$Ljava_lang_String_$24bvf0());
        this.hD = smoothFontRenderer.N(this.W$src$Ljava_lang_String_$24bvf0());
        double d3 = this.n() + 12.5 + d2;
        double d4 = this.A() - 10.0 - (double)this.h2.length * 0.5;
        double d5 = 5.0;
        double d6 = d4 / (double)this.h2.length;
        double d7 = this.X$src$D$1ej56qc() - 1.0;
        smoothFontRenderer.d(this.W$src$Ljava_lang_String_$24bvf0(), this.G$src$D$1b2f02a() + 5.0, this.n() + 5.0, this.R);
        if (this.hN) {
            d6 = (d4 - 2.0) / (double)this.h2.length;
            for (d = 1.0; d < (double)(this.h2.length + 1); d += 1.0) {
                if (d - 1.0 == (double)this.H(this.hA.getInterpolatedValue())) {
                    GuiRenderPrimitives.C(this.G$src$D$1b2f02a() + d5 - 1.0, d3, d6 + 1.0, 1.0, this.d());
                    d5 += d6 + 2.5;
                    continue;
                }
                GuiRenderPrimitives.C(this.G$src$D$1b2f02a() + d5, d3, d6, 1.0, this.d(d));
                d5 += d6 + 0.5;
            }
        } else {
            for (d = 1.0; d < (double)(this.h2.length + 1); d += 1.0) {
                GuiRenderPrimitives.C(this.G$src$D$1b2f02a() + d5, d3, d6, 1.0, this.d(d));
                if (d == 1.0) {
                    GuiRenderPrimitives.V(this.G$src$D$1b2f02a() + d5 - 0.5, d3 + 0.25, 0.5, 1.0, this.d(d));
                } else if (d == (double)this.h2.length) {
                    GuiRenderPrimitives.V(this.G$src$D$1b2f02a() + d5 + d6, d3 + 0.25, 0.5, 1.0, this.d(d));
                }
                d5 += d6 + 0.5;
            }
        }
        d = 0.5;
        if (!this.D()) {
            d = this.hE != null ? (double)this.h3 / d7 : 0.0;
        }
        d4 = this.A() - 10.0 + (double)(this.hN ? 2 : 0);
        double d8 = this.G$src$D$1b2f02a() + 5.0 + d4 * d;
        this.v = this.L(d8 += (d - 0.5) * -1.0 * d6, d3 + 0.5, this.ho.getEndValue() / 2.0);
        if (!this.a) {
            this.a = true;
            double d9 = d8 - this.G$src$D$1b2f02a();
            this.hA = new DoubleAnimation(0.0, d9, d9);
            this.hH = new ColorAnimation(0.0, this.h2[this.h3], this.h2[this.h3]);
        }
        this.C$src$V$1okgo1v();
    }

    @Override
    public void u() {
        if (this.hY && !this.w$src$Z$e457mb()) {
            this.ho.J();
            this.hY = false;
        }
        this.l$src$V$1eu5312();
    }

    public void Y(boolean bl) {
        this.hN = bl;
    }

    public void k() {
        this.hE.Y(false);
        int n = Math.round(this.h2.length / 2);
        this.hE.Z(this.h2[n]);
        this.h3 = n;
        this.v();
        this.s(false);
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        RectData rectData = new RectData(this.G$src$D$1b2f02a(), this.v.W(), this.A(), this.v.R());
        if (rectData.J(guiMouseEvent.getX(), guiMouseEvent.getY())) {
            this.s(false);
            this.hE.Y(false);
            this.o = RenderUtils.h();
            this.I = true;
        }
    }

    @Override
    public double x() {
        return 110.0;
    }

    protected void C$src$V$1okgo1v() {
        if (this.D()) {
            GuiRenderPrimitives.F("newcustomtheme", this.G$src$D$1b2f02a() + (double)this.hA.getInterpolatedValue().floatValue() - 3.5, this.v.W() + this.v.R() / 2.0, this.v.e() - 2.0, this.v.R() - 2.0, Color.WHITE);
            GuiRenderPrimitives.F("toggledot", this.G$src$D$1b2f02a() + (double)this.hA.getInterpolatedValue().floatValue(), this.v.W() + this.v.R() / 2.0, this.v.e() - 4.0, this.v.R() - 4.0, ColorPaletteSliderComponent.J.i);
            return;
        }
        GuiRenderPrimitives.F("newtheme", this.G$src$D$1b2f02a() + (double)this.hA.getInterpolatedValue().floatValue() - 3.5, this.v.W() + this.v.R() / 2.0, this.v.e() - 2.0, this.v.R() - 2.0, this.hH.getInterpolatedColor());
    }

    public boolean D() {
        if (this.K) {
            return true;
        }
        for (Color color : this.h2) {
            if (!this.hE.H(color)) continue;
            return false;
        }
        this.h3 = Math.round(this.h2.length / 2);
        this.v();
        this.hr = this.h3;
        return true;
    }

    public ColorPaletteSliderComponent(String string, ColorValue colorValue, Color[] colorArray) {
        super(string);
        this.v = new RectData(0.0, 0.0, 0.0, 0.0);
        this.hA = new DoubleAnimation(0.0, 0.0, 0.0);
        this.hH = new ColorAnimation(0.0, Color.BLACK, Color.BLACK);
        this.R = ColorPaletteSliderComponent.J.Z;
        this.hE = colorValue;
        this.C(colorValue);
        this.h2 = colorArray;
        this.hQ = colorArray.length;
        this.hi = (this.hQ - this.hu) / 100.0;
        this.l$src$V$1eu5312();
    }

    @Override
    public double C() {
        return 20.0;
    }

    public void l$src$V$1eu5312() {
        if (!this.D()) {
            for (int i = 0; i < this.h2.length; ++i) {
                if (!this.hE.H(this.h2[i])) continue;
                this.h3 = i;
                if (this.h3 != this.hr) {
                    this.v();
                }
                this.hr = this.h3;
            }
        }
    }

    public Color d(double d) {
        double d2 = (this.X$src$D$1ej56qc() - this.J$src$D$1ebg2fa()) / (double)this.h2.length;
        double d3 = this.J$src$D$1ebg2fa();
        Color color = Color.BLACK;
        boolean bl = false;
        for (int i = 0; i < this.h2.length; ++i) {
            double d4 = d3 + (double)i * d2;
            int n = i + 1;
            double d5 = d3 + (double)n * d2;
            if (!(d > d4) || !(d <= d5)) continue;
            color = this.h2[i];
            bl = true;
            break;
        }
        if (!bl) {
            color = d <= d3 ? this.h2[0] : this.h2[this.h2.length - 1];
        }
        return color;
    }

    public void Y(int n) {
        Color color = this.h2[n];
        this.hE.Z(color);
    }

    @Override
    public void Z(boolean bl) {
        super.Z(bl);
        this.K = false;
        this.a = false;
    }

    public ColorAnimation l$src$Lgg_vape_ui_click_animation_ColorAnimation_$lcq3bn() {
        return this.hH;
    }

    public double F$src$D$1e98w1u() {
        return this.hD;
    }

    public void R(double d) {
        this.hQ = d;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void f(double d) {
        this.O = d;
    }

    @Override
    public void I() {
    }

    public int y$src$I$1f1aefk() {
        return this.h3;
    }

    private void V$src$V$1ei1lz4() {
        if (this.I) {
            if (!MouseInput.I(MouseButton.LEFT_CLICK.ordinal())) {
                this.I = false;
                return;
            }
            double d = this.A() - 10.0;
            MousePosition mousePosition = RenderUtils.h();
            double d2 = (double)mousePosition.O - this.G$src$D$1b2f02a() - 5.0 - this.v.e() / 2.0;
            double d3 = this.G$src$D$1b2f02a() + 5.0 + this.v.e() / 2.0;
            double d4 = this.G$src$D$1b2f02a() + this.A() - 5.0 - this.v.e() / 2.0;
            double d5 = this.h(this.hu, this.hQ, d3, d4, this.O, d2);
            d5 = new BigDecimal("" + d5).setScale(1, RoundingMode.HALF_UP).doubleValue();
            if (this.hE != null) {
                this.hE.Z(this.d(d5 + 0.0));
                this.l$src$V$1eu5312();
            }
        }
    }

    public RectData A$src$Lfunc_skidline_RectData_$x2qn2p() {
        return this.v;
    }

    public void s(boolean bl) {
        this.K = bl;
    }

    public int H(double d) {
        double d2 = this.A() - 10.0 - (double)this.h2.length * 0.5;
        double d3 = 5.0;
        double d4 = d2 / (double)this.h2.length;
        for (int i = 1; i < this.h2.length + 1; ++i) {
            if (d >= d3 && d <= d3 + d4 + 0.5) {
                return i - 1;
            }
            d3 += d4 + 0.5;
        }
        return 0;
    }

    public Color[] j$src$ALjava_awt_Color_$sw9v39() {
        return this.h2;
    }

    public double S$src$D$1ege7rj() {
        return this.O;
    }

    public void W(double d) {
        this.hu = d;
    }
}

