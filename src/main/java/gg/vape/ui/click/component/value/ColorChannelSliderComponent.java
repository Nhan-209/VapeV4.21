package gg.vape.ui.click.component.value;

import func.skidline.RectData;
import gg.vape.Vape;
import gg.vape.input.MouseInput;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.MouseButton;
import gg.vape.ui.click.animation.DoubleAnimation;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.gui.TextLabel;
import gg.vape.ui.click.component.value.ColorChannelType;
import gg.vape.ui.click.component.value.ColorPaletteRefreshClickListener;
import gg.vape.ui.click.component.value.ColorPaletteSliderComponent;
import gg.vape.ui.click.component.value.ColorValueEditorComponent;
import gg.vape.ui.click.component.value.SliderComponentBase;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.MutableColor;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.RenderUtils;
import gg.vape.value.ColorValue;
import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ColorChannelSliderComponent
extends SliderComponentBase {
    private DoubleAnimation ZT = new DoubleAnimation(0.0, 0.0, 0.0);
    private double Zf = 255.0;
    private TextLabel Zw;
    private Color ZI;
    private double Z8 = 0.0;
    private ColorValue v;
    private final float Zv;
    private RectData Z3;
    private long a;
    private boolean ZU;
    protected DoubleAnimation Zz = new DoubleAnimation(0.15, 7.0, 8.0);
    private double K;
    private double Zl;
    private double ZR = 0.75;
    private GuiComponent Zb;
    private Double O;
    private ColorChannelType Zh;
    private static final String cb = "RESET";
    private double Zm = -1.0;
    private double R;

    public void L(double d) {
        this.R = d;
    }

    @Override
    public double x() {
        return 110.0;
    }

    private void lambda$new$0(ColorValue colorValue) {
        this.Z$src$V$30xmgs();
    }

    private float[] y(double d, double d2) {
        float f = (float)(d / d2);
        switch (this.Zh) {
            case BLOCK_CHILD: 
            case RAINBOW: {
                return new float[]{f, 1.0f, 1.0f, 1.0f};
            }
            case SATURATION: {
                return new float[]{this.v.q(), f, this.v.n(), 1.0f};
            }
            case VIBRANCE: {
                return new float[]{this.v.q(), this.v.r(), f, 1.0f};
            }
            case OPACITY: {
                return new float[]{this.v.q(), this.v.r(), this.v.n(), f};
            }
        }
        return new float[]{0.0f, 0.0f, 0.0f, 1.0f};
    }

    public void E(double d) {
        this.K = d;
    }

    @Override
    public void u() {
        if (this.ZU && !this.w$src$Z$e457mb()) {
            this.Zz.J();
            this.ZU = false;
        }
        if (this.v.g()) {
            this.v.O();
        }
    }

    private void j$src$V$39qbyk() {
        if (this.I) {
            if (!MouseInput.I(MouseButton.LEFT_CLICK.ordinal())) {
                this.I = false;
                return;
            }
            double d = this.A() - 10.0 - this.Z3.e();
            double d2 = (double)this.o.O - this.G$src$D$1b2f02a() + this.P$src$D$34o7qt() - 5.0 - this.Z3.e() / 2.0;
            double d3 = this.G$src$D$1b2f02a() + 5.0 + this.Z3.e() / 2.0;
            double d4 = this.G$src$D$1b2f02a() + this.A() - 5.0 - this.Z3.e() / 2.0;
            double d5 = this.h(this.R, this.Zf, d3, d4, this.K, d2);
            d5 = new BigDecimal("" + d5).setScale(3, RoundingMode.HALF_UP).doubleValue();
            if (this.v != null) {
                switch (this.Zh) {
                    case BLOCK_CHILD: 
                    case RAINBOW: {
                        if (((Double)this.v.C$src$Lgg_vape_value_NumberValue_$z6u28w().K()).equals(this.O)) {
                            return;
                        }
                        this.v.C$src$Lgg_vape_value_NumberValue_$z6u28w().A(d5);
                        break;
                    }
                    case SATURATION: {
                        if (((Double)this.v.G().K()).equals(this.O)) {
                            return;
                        }
                        this.v.G().A(d5);
                        break;
                    }
                    case VIBRANCE: {
                        if (((Double)this.v.C$src$Lgg_vape_value_NumberValue_$z6u28w().K()).equals(this.O)) {
                            return;
                        }
                        this.v.y().A(d5);
                        break;
                    }
                    case OPACITY: {
                        if (((Double)this.v.x$src$Lgg_vape_value_NumberValue_$1mjtff9().K()).equals(this.O)) {
                            return;
                        }
                        this.v.x$src$Lgg_vape_value_NumberValue_$1mjtff9().A(d5);
                    }
                }
            }
        }
    }

    public void s(double d) {
        this.Zf = d;
    }

    public double c$src$D$35vrdj() {
        return this.Z8;
    }

    public double b$src$D$35bys6() {
        return this.K;
    }

    public Color k(double d, double d2) {
        switch (this.Zh) {
            case BLOCK_CHILD: 
            case RAINBOW: {
                return new Color(Color.HSBtoRGB((float)(d / d2), 1.0f, 1.0f));
            }
            case SATURATION: {
                return new Color(Color.HSBtoRGB(this.v.q(), (float)(d / d2), this.v.n()));
            }
            case VIBRANCE: {
                return new Color(Color.HSBtoRGB(this.v.q(), this.v.r(), (float)(d / d2)));
            }
            case OPACITY: {
                MutableColor mutableColor = new MutableColor(Color.HSBtoRGB(this.v.q(), this.v.r(), this.v.n()));
                mutableColor.withAlpha((int)(255.0 * (d / d2)));
                return mutableColor;
            }
        }
        return new Color(0, 0, 0);
    }

    @Override
    public void H() {
        Object object;
        double d;
        double d2;
        double d3;
        double d4;
        this.j$src$V$39qbyk();
        if (!this.d().equals(Color.WHITE)) {
            this.onDisable();
        }
        double d5 = 0.0;
        switch (this.Zh) {
            case BLOCK_CHILD: 
            case RAINBOW: {
                d5 = (Double)this.v.C$src$Lgg_vape_value_NumberValue_$z6u28w().K() / this.Zf;
                break;
            }
            case SATURATION: {
                d5 = (Double)this.v.G().K() / this.Zf;
                break;
            }
            case VIBRANCE: {
                d5 = (Double)this.v.y().K() / this.Zf;
                break;
            }
            case OPACITY: {
                d5 = (Double)this.v.x$src$Lgg_vape_value_NumberValue_$1mjtff9().K() / this.Zf;
            }
        }
        if ((d5 *= (double)this.Zv) != this.O) {
            this.Z$src$V$30xmgs();
        }
        SmoothFontRenderer smoothFontRenderer = this.O(this.ZR);
        String string = this.Zh.equals((Object)ColorChannelType.RAINBOW) ? this.W$src$Ljava_lang_String_$24bvf0() : this.Zh.T();
        double d6 = smoothFontRenderer.d(string);
        this.Z8 = smoothFontRenderer.N(this.W$src$Ljava_lang_String_$24bvf0());
        double d7 = this.n() + 12.5 + d6;
        double d8 = this.A() - 10.0;
        double d9 = this.G$src$D$1b2f02a() + this.ZT.getInterpolatedValue();
        if (this.Zh.equals((Object)ColorChannelType.RAINBOW)) {
            smoothFontRenderer.d(this.W$src$Ljava_lang_String_$24bvf0(), this.G$src$D$1b2f02a() + 5.0, this.n() + 5.0, this.ZI);
        } else {
            smoothFontRenderer.d(this.Zh.T(), this.G$src$D$1b2f02a() + 5.0, this.n() + 5.0, this.ZI);
        }
        if (this.Zw != null) {
            d4 = smoothFontRenderer.N(this.Zw.L$src$Ljava_lang_String_$1ncdwqb());
            this.Zw.K(this.G$src$D$1b2f02a() + this.A() - 5.0 - d4 - 2.0);
            this.Zw.S(this.n() + 2.0);
            this.Zw.Y(10.0);
            this.Zw.o(d4);
            this.Zw.y(0.8);
        }
        this.Z3 = this.L(d9, d7 + 0.5, this.Zz.getEndValue() / 2.0);
        d4 = 5.0;
        double d10 = this.A() - d4 * 2.0;
        double d11 = this.G$src$D$1b2f02a() + d4;
        double d12 = d9 - d11 - (d3 = this.Zz.getEndValue() / 2.0 + 0.5);
        if (d12 >= 2.0) {
            float[] fArray = this.y(0.0, d10);
            float[] fArray2 = this.y(d12, d10);
            GuiRenderPrimitives.F(d11, d7, d12, 2.0, fArray, fArray2);
        }
        if ((d2 = d10 - (d = d9 - d11 + d3)) >= 2.0) {
            float[] gradientStart = this.y(d, d10);
            float[] fArray = this.y(d10, d10);
            GuiRenderPrimitives.F(d11 + d, d7, d2, 2.0, gradientStart, fArray);
        }
        object = this.d().equals(Color.WHITE) ? this.Zb.d() : this.d();
        double d13 = this.Zz.getInterpolatedValue();
        GuiRenderPrimitives.Y((float)(this.Z3.o() + this.Z3.e() / 2.0 - d13 / 2.0), (float)(this.Z3.W() + this.Z3.R() / 2.0 - d13 / 2.0), (float)d13, (float)(0.8 / Vape.INSTANCE.getClientSettings().s()), ColorChannelSliderComponent.J.A, 0.0, null);
    }

    public ColorChannelSliderComponent(ColorChannelType colorChannelType, ColorValue colorValue) {
        this(colorChannelType, colorValue, colorValue.getName(), 1.0);
        this.C(colorValue);
        colorValue.B(this::lambda$new$0);
        this.Z$src$V$30xmgs();
    }

    @Override
    public void F() {
        if (!this.ZU) {
            this.Zz.J();
        }
        this.ZU = true;
    }

    public ColorChannelSliderComponent(ColorChannelType colorChannelType, ColorValue colorValue, String string, double d) {
        super(string);
        this.Z3 = new RectData(0.0, 0.0, 0.0, 0.0);
        this.ZI = ColorChannelSliderComponent.J.Z;
        this.O = -1.0;
        this.v = colorValue;
        this.Zh = colorChannelType;
        this.K = d;
        this.Zl = (this.Zf - this.R) / 100.0;
        if (colorChannelType.equals((Object)ColorChannelType.RAINBOW)) {
            // empty if block
        }
        this.Zv = (float)(this.Zf / colorValue.Q$src$D$1rauh53());
    }

    public double g$src$D$382xqz() {
        return this.Zf;
    }

    @Override
    public double C() {
        return 25.0;
    }

    private void Z$src$V$30xmgs() {
        double d = this.A() - 10.0;
        double d2 = 0.0;
        switch (this.Zh) {
            case BLOCK_CHILD: 
            case RAINBOW: {
                d2 = (Double)this.v.C$src$Lgg_vape_value_NumberValue_$z6u28w().K() / this.Zf;
                break;
            }
            case SATURATION: {
                d2 = (Double)this.v.G().K() / this.Zf;
                break;
            }
            case VIBRANCE: {
                d2 = (Double)this.v.y().K() / this.Zf;
                break;
            }
            case OPACITY: {
                d2 = (Double)this.v.x$src$Lgg_vape_value_NumberValue_$1mjtff9().K() / this.Zf;
            }
        }
        double d3 = (d - this.Zz.getEndValue()) * (d2 *= (double)this.Zv) + 5.0 + this.Zz.getEndValue() / 2.0;
        double d4 = this.ZT.getInterpolatedValue();
        double d5 = 0.05;
        if (this.Zh == ColorChannelType.BLOCK_CHILD || this.Zh == ColorChannelType.RAINBOW) {
            if (this.v.g() && d2 == 0.0) {
                d5 = 0.0;
            }
        } else if (this.v.g() && this.v.I()) {
            d5 = 1.0;
        }
        this.ZT = new DoubleAnimation(d5, d4, d3);
        this.Zm = d2;
        this.ZT.c();
    }

    public void H(GuiComponent guiComponent) {
        this.Zb = guiComponent;
    }

    public double Q$src$D$2vzgp1() {
        return this.R;
    }


    public ColorChannelSliderComponent(ColorPaletteSliderComponent colorPaletteSliderComponent) {
        this(ColorChannelType.BLOCK_CHILD, colorPaletteSliderComponent.Z$src$Lgg_vape_value_ColorValue_$1er4i1l());
        this.Zw = new TextLabel(cb);
        this.Zw.r(new ColorPaletteRefreshClickListener(this, colorPaletteSliderComponent));
        this.H(new GuiComponent[]{this.Zw});
    }

    public RectData g$src$Lfunc_skidline_RectData_$3hcqur() {
        return this.Z3;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        RectData rectData;
        String string = ColorValueEditorComponent.P();
        if ((this.Zh.equals((Object)ColorChannelType.RAINBOW) || this.Zh.equals((Object)ColorChannelType.BLOCK_CHILD)) && this.a + 300L > System.currentTimeMillis()) {
            this.v.Y(!this.v.g());
        }
        /* Timebomb here (disabled): forces a color to transparent black on interaction after 2027-05-03 (epoch ms 1809594154878L)
        if (System.currentTimeMillis() > 1809594154878L) {
            this.v.Z(new Color(0, 0, 0, 0));
        }
        */
        if ((rectData = new RectData(this.G$src$D$1b2f02a(), this.Z3.W(), this.A(), this.Z3.R())).J(guiMouseEvent.getX(), guiMouseEvent.getY())) {
            this.o = RenderUtils.h();
            this.I = true;
        }
        this.a = System.currentTimeMillis();
    }
}
