package gg.vape.ui.click.component.value;

import func.skidline.RectData;
import gg.vape.Vape;
import gg.vape.input.MouseInput;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.MouseButton;
import gg.vape.ui.click.animation.DoubleAnimation;
import gg.vape.ui.click.component.value.NumberSliderInputHandle;
import gg.vape.ui.click.component.value.SliderComponentBase;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.RenderUtils;
import gg.vape.value.NumberValue;
import java.awt.Color;
import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class NumberSliderComponent
extends SliderComponentBase {
    private Double jI;
    private static final long cb;
    protected DoubleAnimation jP;
    private String a;
    private Color jA;
    private DoubleAnimation jj;
    private boolean jD;
    private double jf;
    private double O;
    private double jw;
    private NumberSliderInputHandle jp;
    private static final long ab;
    private double v;
    private RectData jm;
    private int jv;
    private NumberValue K;
    private double R;

    public NumberValue f$src$Lgg_vape_value_NumberValue_$1des1vc() {
        return this.K;
    }

    public void a(double d) {
        this.jw = d;
    }

    @Override
    public void F() {
        if (!this.jD) {
            this.jP.J();
        }
        this.jD = true;
    }

    static {
        ab = ZkmLongKeyState.a(-7416898314097674143L, -3845444827873714241L, MethodHandles.lookup().lookupClass()).a(93933529335533L);
        long l = ab ^ 0x51E6FEC68D2BL;
        cb = 2501393815093379082L;
    }

    public NumberSliderComponent(String string, double d, double d2, double d3) {
        super(string);
        long l = ab ^ 0x2915B385D40EL;
        this.a = "";
        this.jf = 0.75;
        this.jv = 1;
        this.jm = new RectData(0.0, 0.0, 0.0, 0.0);
        this.jj = new DoubleAnimation(0.0, 0.0, 0.0);
        this.jP = new DoubleAnimation(0.15, 7.0, 8.0);
        this.jA = NumberSliderComponent.J.Z;
        this.jI = -1.0;
        this.R = d;
        this.v = d2;
        this.jw = d3;
        this.O = (double)((int)(d2 * 100.0) - (int)(d * 100.0)) / 10000.0;
        this.jp = new NumberSliderInputHandle(this);
        this.H(this.jp);
        if (d3 <= 0.01) {
            this.jv = (int)cb;
        }
    }

    @Override
    public void u() {
        if (this.jD && !this.w$src$Z$e457mb()) {
            this.jP.J();
            this.jD = false;
        }
    }

    @Override
    public double C() {
        return 25.0;
    }

    public String n$src$Ljava_lang_String_$af0f9r() {
        return this.a;
    }

    public DecimalFormat O$src$Ljava_text_DecimalFormat_$mv9dca() {
        return this.K.Q$src$Ljava_text_DecimalFormat_$j98hth();
    }

    private double P(double d) {
        double d2;
        Double d4 = this.K != null ? (Double)this.K.K() : null;
        double d3 = d2 = d4 != null ? (d4 - this.R) / (this.v - this.R) : 0.0;
        if (d2 > 1.0) {
            d2 = 1.0;
        } else if (d2 < 0.0) {
            d2 = 0.0;
        }
        return (d - this.jP.getEndValue()) * d2 + this.Z$src$D$1wvori2() + this.jP.getEndValue() / 2.0;
    }

    public double o$src$D$3h2b8a() {
        return this.R;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        RectData rectData = new RectData(this.G$src$D$1b2f02a(), this.jm.W(), this.A(), this.jm.R());
        if (rectData.J(guiMouseEvent.getX(), guiMouseEvent.getY())) {
            this.o = RenderUtils.h();
            this.I = true;
        }
    }

    public void A(double d) {
        this.v = d;
    }

    @Override
    public void q(double d) {
        if (this.A() == d) {
            return;
        }
        super.q(d);
        this.q(true);
    }

    private void lambda$new$0(NumberValue numberValue) {
        this.q(false);
    }

    @Override
    public double x() {
        return 110.0;
    }

    private void A$src$V$2rrsd6() {
        if (this.I) {
            if (!MouseInput.I(MouseButton.LEFT_CLICK.ordinal())) {
                this.I = false;
                return;
            }
            double d = (double)this.o.O - this.G$src$D$1b2f02a() + this.P$src$D$34o7qt() - this.Z$src$D$1wvori2() - this.jm.e() / 2.0;
            double d2 = this.G$src$D$1b2f02a() + this.Z$src$D$1wvori2() + this.jm.e() / 2.0;
            double d3 = this.G$src$D$1b2f02a() + this.A() - 5.0 - this.jm.e() / 2.0;
            double d4 = this.h(this.R, this.v, d2, d3, this.jw, d);
            d4 = new BigDecimal("" + d4).setScale(this.jv, RoundingMode.HALF_UP).doubleValue();
            if (this.K != null) {
                if (((Double)this.K.K()).equals(this.jI)) {
                    return;
                }
                this.K.A(d4);
            }
        }
    }

    public double p() {
        return this.jw;
    }

    public void N(double d) {
        this.R = d;
    }

    @Override
    public void H() {
        this.A$src$V$2rrsd6();
        this.onDisable();
        SmoothFontRenderer smoothFontRenderer = this.O(this.jf);
        double d = smoothFontRenderer.d(this.W$src$Ljava_lang_String_$24bvf0());
        double d2 = this.jP.getInterpolatedValue();
        double d3 = this.n() + 12.5 + d;
        double d4 = this.G$src$D$1b2f02a() + this.jj.getInterpolatedValue();
        smoothFontRenderer.d(this.W$src$Ljava_lang_String_$24bvf0(), this.G$src$D$1b2f02a() + this.Z$src$D$1wvori2(), this.n() + 5.0, this.jA);
        this.jp.K(this.G$src$D$1b2f02a() + this.A() - 5.0 - this.jp.A());
        this.jp.S(this.n() + 5.0);
        this.jm = this.L(d4, d3 + 0.5, this.jP.getEndValue() / 2.0);
        double d5 = this.jm.o() - this.G$src$D$1b2f02a() - this.Z$src$D$1wvori2();
        double d6 = this.G$src$D$1b2f02a() + this.A() - this.jm.o() - 5.0;
        double d7 = d3 + 0.5 - 1.0;
        if (d5 - 0.5 >= 2.0) {
            GuiRenderPrimitives.j(this.G$src$D$1b2f02a() + this.Z$src$D$1wvori2(), d7, d5 - 0.5, 2.0, J.z());
        }
        if (d6 - 8.5 >= 2.0) {
            GuiRenderPrimitives.j(this.jm.o() + 8.5, d7, d6 - 8.5, 2.0, NumberSliderComponent.J.l);
        }
        GuiRenderPrimitives.Y((float)(d4 - d2 / 2.0), (float)(d3 + 0.5 - d2 / 2.0), (float)d2, (float)(0.8 / Vape.INSTANCE.getClientSettings().s()), J.z(), 0.0, this.d());
    }

    public NumberSliderComponent(NumberValue numberValue) {
        this(numberValue.getName(), numberValue.S$src$D$10pa1t3(), numberValue.Q$src$D$10o6gmd(), numberValue.K$src$D$10kvp27());
        this.a = numberValue.T();
        this.K = numberValue;
        if (numberValue.w$src$Ljava_lang_String_$ikqblg() != null) {
            this.w(numberValue.w$src$Ljava_lang_String_$ikqblg());
        }
        this.C(numberValue);
        numberValue.B(this::lambda$new$0);
        this.q(true);
    }

    private void q(boolean bl) {
        double d = this.A() - (this.Z$src$D$1wvori2() + 5.0);
        double d2 = this.P(d);
        double d3 = this.jj.getInterpolatedValue();
        this.jj = new DoubleAnimation(0.05, d3, d2);
        this.jj.c();
        if (bl) {
            this.jj.C();
        }
    }

    public double v() {
        return this.v;
    }
}
