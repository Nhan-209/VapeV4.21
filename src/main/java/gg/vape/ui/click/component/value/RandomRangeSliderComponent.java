package gg.vape.ui.click.component.value;

import func.skidline.RectData;
import gg.vape.Vape;
import gg.vape.input.MouseInput;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.MouseButton;
import gg.vape.ui.click.MousePosition;
import gg.vape.ui.click.animation.DoubleAnimation;
import gg.vape.ui.click.component.value.RandomRangeSliderInputHandle;
import gg.vape.ui.click.component.value.RangeEndpoint;
import gg.vape.ui.click.component.value.SliderComponentBase;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.utils.render.RenderUtils;
import gg.vape.value.RandomValue;
import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class RandomRangeSliderComponent
extends SliderComponentBase {
    private int L7;
    private RandomValue LA;
    private final RandomRangeSliderInputHandle v;
    private double Lk;
    private double O;
    private final double LZ;
    private final RandomRangeSliderInputHandle K;
    private DoubleAnimation a = new DoubleAnimation(0.0, 0.0, 0.0);
    private double LI = -1.0;
    private RectData Lw;
    double LD;
    private DoubleAnimation L9 = new DoubleAnimation(0.0, 0.0, 0.0);
    private final Color LQ;
    private double LM;
    private double Lr;
    private RectData Le;
    private final DoubleAnimation L0 = new DoubleAnimation(0.15, 8.0, 9.0);
    private final double R = 0.75;
    private double LN = -1.0;
    private int Lz;
    private final double LG;
    private final DoubleAnimation LP = new DoubleAnimation(0.15, 8.0, 9.0);
    private double L2;

    public double g$src$D$bccmqt() {
        return this.O;
    }

    @Override
    public void q(double d) {
        if (this.A() == d) {
            return;
        }
        super.q(d);
        this.C(true);
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        double d = this.Le.o() - this.Lw.o();
        double d2 = this.Lw.o() - this.G$src$D$1b2f02a() + d / 2.0;
        RectData rectData = new RectData(this.G$src$D$1b2f02a(), this.Lw.W(), d2, this.Lw.R());
        RectData rectData2 = new RectData(this.Le.o() - d / 2.0, this.Le.W(), this.A() - d2, this.Le.R());
        if (rectData.J(guiMouseEvent.getX(), guiMouseEvent.getY())) {
            this.L7 = 1;
        } else if (rectData2.J(guiMouseEvent.getX(), guiMouseEvent.getY())) {
            this.L7 = 2;
        }
        if (this.L7 != 0) {
            this.o = RenderUtils.h();
        }
    }

    public void M(double d) {
        this.Lk = d;
    }

    @Override
    public void u() {
        switch (this.Lz) {
            case 1: {
                MousePosition mousePosition = RenderUtils.h();
                if (this.Lw.Z(mousePosition)) break;
                this.Lz = 0;
                this.L0.J();
                break;
            }
            case 2: {
                MousePosition mousePosition = RenderUtils.h();
                if (this.Le.Z(mousePosition)) break;
                this.Lz = 0;
                this.LP.J();
            }
        }
    }

    public RandomRangeSliderComponent(String string, double d, double d2, double d3) {
        this(string, d, d2, d3, 1.0, 1.0);
    }

    public double w$src$D$bl5c8l() {
        return this.L2;
    }

    private void C(boolean bl) {
        float f = (float)this.L0.getEndValue();
        double d = this.A() - (this.Z$src$D$1wvori2() + 5.0);
        double d2 = (this.LA.q$src$D$vgz097() - this.LM) / (this.Lr - this.LM);
        double d3 = 1.0 - (this.LA.M() - this.LM) / (this.Lr - this.LM);
        if (d2 > 1.0) {
            d2 = 1.0;
        } else if (d2 < 0.0) {
            d2 = 0.0;
        }
        if (d3 > 1.0) {
            d3 = 1.0;
        } else if (d3 < 0.0) {
            d3 = 0.0;
        }
        double d4 = 1.0 - (d2 + d3);
        double d5 = d2 * (d - (double)f - 5.0) + this.Z$src$D$1wvori2() + (double)(f / 2.0f);
        double d6 = d5 + 5.0 + d4 * (d - (double)f - 5.0);
        double d7 = this.a.getInterpolatedValue();
        double d8 = this.L9.getInterpolatedValue();
        this.a = new DoubleAnimation(0.05, d7, d5);
        this.a.c();
        this.L9 = new DoubleAnimation(0.05, d8, d6);
        this.L9.c();
        if (bl) {
            this.a.C();
            this.L9.C();
        }
    }

    private void Y$src$V$b4niv9() {
        if (this.L7 != 0) {
            if (!MouseInput.I(MouseButton.LEFT_CLICK.ordinal())) {
                this.L7 = 0;
                return;
            }
            double d = this.A() - (10.0 + this.Z$src$D$1wvori2()) - this.Lw.e() - this.Le.e();
            double d2 = (double)this.o.O - this.G$src$D$1b2f02a() + this.P$src$D$34o7qt() - this.Z$src$D$1wvori2();
            if (this.L7 == 2) {
                d2 -= 12.0;
            }
            if (this.L7 == 1) {
                d2 -= 2.0;
            }
            double d3 = this.Y(this.LM, this.Lr, d, this.L2, this.LZ, d2);
            double d4 = this.L2;
            int n = 0;
            while (d4 % 1.0 != 0.0) {
                ++n;
                d4 *= 10.0;
            }
            d3 = new BigDecimal("" + d3).setScale(n, RoundingMode.HALF_UP).doubleValue();
            if (d3 < this.LM) {
                d3 = this.LM;
            } else if (d3 > this.Lr) {
                d3 = this.Lr;
            }
            if (this.L7 == 1) {
                if (d3 == this.LN) {
                    return;
                }
                this.LA.u(d3);
            } else {
                if (d3 == this.LI) {
                    return;
                }
                this.LA.q(d3);
            }
            this.LN = this.LA.q$src$D$vgz097();
            this.LI = this.LA.M();
        }
    }

    public RandomRangeSliderComponent(RandomValue randomValue) {
        this(randomValue.getName(), randomValue.O$src$D$uya02x(), randomValue.g$src$D$vbh2bl(), randomValue.Q$src$D$uzdl9n(), randomValue.q$src$D$vgz097(), randomValue.M());
        this.LA = randomValue;
        this.C(randomValue);
        randomValue.B(this::lambda$new$0);
        this.C(true);
    }

    public void N(double d) {
        this.Lr = d;
    }

    private List<String> Y$src$Ljava_util_List_$1h7ga6i() {
        SmoothFontRenderer smoothFontRenderer = this.O(0.75);
        String string = Vape.INSTANCE.getFontSelector().W().s(this.W$src$Ljava_lang_String_$24bvf0());
        String[] stringArray = string.split(" ");
        double d = this.v.G$src$D$1b2f02a() - this.G$src$D$1b2f02a();
        ArrayList<String> arrayList = new ArrayList<String>();
        double d2 = 0.0;
        String string2 = "";
        for (String string3 : stringArray) {
            double d3 = d2 + smoothFontRenderer.N(string3 + " ");
            if (d3 > d) {
                d2 = 0.0;
                arrayList.add(string2);
                string2 = string3 + " ";
                continue;
            }
            d2 = d3;
            string2 = string2 + string3 + " ";
        }
        arrayList.add(string2);
        return arrayList;
    }

    public void E(double d) {
        this.L2 = d;
    }


    private void lambda$new$0(RandomValue randomValue) {
        this.C(false);
    }

    public RandomRangeSliderComponent(String string, double d, double d2, double d3, double d4, double d5) {
        super(string);
        this.Lw = new RectData(0.0, 0.0, 0.0, 0.0);
        this.Le = new RectData(0.0, 0.0, 0.0, 0.0);
        this.LQ = RandomRangeSliderComponent.J.Z;
        this.LM = d;
        this.Lr = d2;
        this.L2 = d3;
        this.LZ = (d2 - d) / 100.0;
        this.LG = (double)(string.split("\n").length - 1) * this.A$src$Lgg_vape_ui_font_SmoothFontRenderer_$jrhwp3().d(string) + 5.0;
        if (d4 == 1.0 && d5 == 1.0) {
            this.k();
        } else {
            this.Lk = d4;
            this.O = d5;
        }
        this.v = new RandomRangeSliderInputHandle(this, RangeEndpoint.MINIMUM);
        this.K = new RandomRangeSliderInputHandle(this, RangeEndpoint.MAXIMUM);
        this.H(this.v, this.K);
    }

    public RandomValue W() {
        return this.LA;
    }

    @Override
    public double x() {
        return 110.0;
    }

    public void i(double d) {
        this.LM = d;
    }

    @Override
    public double C() {
        return 20.0 + this.LG + this.LD;
    }

    @Override
    public void H() {
        double d;
        double d2;
        this.Y$src$V$b4niv9();
        this.onDisable();
        SmoothFontRenderer smoothFontRenderer = this.O(0.75);
        double d3 = smoothFontRenderer.d(this.W$src$Ljava_lang_String_$24bvf0());
        double d4 = this.n() + 12.5 + (double)((float)d3);
        double d5 = this.n() + 5.0;
        this.L$src$V$axi75k();
        double d6 = (double)(this.W$src$Ljava_lang_String_$24bvf0().split("\n").length - 1) * (smoothFontRenderer.d(this.W$src$Ljava_lang_String_$24bvf0()) + 3.0);
        d4 += d6;
        this.K.K(this.G$src$D$1b2f02a() + this.A() - 5.0 - this.K.A());
        this.K.S(d5 += d6);
        ImageRenderer.drawResWithShadow(RandomRangeSliderComponent.J.K, (int)(this.G$src$D$1b2f02a() + this.A() - 5.0 - this.K.A() - 8.0), (int)d5, "newrangeindicator", 0.1f, false);
        this.v.K(this.G$src$D$1b2f02a() + this.A() - 10.0 - this.v.A() - this.K.A() - 8.0);
        this.v.S(d5);
        this.Lw = this.L(this.G$src$D$1b2f02a() + this.a.getInterpolatedValue(), (d4 += this.LD / 2.0) + 0.5, this.L0.getInterpolatedValue() / 2.0);
        this.Le = this.L(this.G$src$D$1b2f02a() + this.L9.getInterpolatedValue(), d4 + 0.5, this.LP.getInterpolatedValue() / 2.0);
        this.Lw.A(this.Lw.e() / 2.0);
        this.Le.A(this.Le.e() / 2.0);
        this.Le.M(this.Le.o() + this.Le.e());
        double d7 = this.Lw.o() - this.G$src$D$1b2f02a() - this.Z$src$D$1wvori2();
        double d8 = this.Le.o() - this.Lw.o();
        double d9 = this.G$src$D$1b2f02a() + this.A() - this.Le.o() - 5.0;
        double d10 = d4 + 0.5 - 1.0;
        if (d7 - 0.5 >= 2.0) {
            GuiRenderPrimitives.j(this.G$src$D$1b2f02a() + this.Z$src$D$1wvori2(), d10, d7 - 0.5, 2.0, RandomRangeSliderComponent.J.l);
        }
        if (d8 > 0.0 && (d2 = d8 - 0.5 - 5.0 - (d = (this.L0.getInterpolatedValue() - this.L0.getStartValue()) / 2.0)) >= 2.0) {
            GuiRenderPrimitives.j(this.Lw.o() + 5.0 + d, d10, d2, 2.0, J.z());
        }
        if (d9 > 0.0 && (d2 = d9 - 5.0 - (d = (this.LP.getInterpolatedValue() - this.LP.getStartValue()) / 2.0)) >= 2.0) {
            GuiRenderPrimitives.j(this.Le.o() + 6.0 + d, d10, d2, 2.0, RandomRangeSliderComponent.J.l);
        }
        GuiRenderPrimitives.F("rangemin", this.Lw.o() + this.Lw.e(), d4 + 0.5, (double)this.L0.getInterpolatedValue(), this.L0.getInterpolatedValue(), J.z());
        GuiRenderPrimitives.F("rangemax", this.Le.o() + this.Le.e(), d4 + 0.5, (double)this.LP.getInterpolatedValue(), this.LP.getInterpolatedValue(), J.z());
    }

    public double r$src$D$bied9s() {
        return this.Lr;
    }

    @Override
    public void F() {
        MousePosition mousePosition = RenderUtils.h();
        if (this.Lw.Z(mousePosition) && this.Lz != 1) {
            if (this.Lz == 2) {
                this.LP.J();
            }
            this.Lz = 1;
            this.L0.J();
        } else if (this.Le.Z(mousePosition) && this.Lz != 2) {
            if (this.Lz == 1) {
                this.L0.J();
            }
            this.Lz = 2;
            this.LP.J();
        }
    }

    private void k() {
        double d;
        double d2 = (this.Lr + this.LM) / 2.0;
        this.Lk = d2 - this.LM;
        this.O = d2 + this.LM;
        double d3 = this.Lk % this.L2;
        if (d3 != 0.0) {
            this.Lk -= d3;
        }
        if ((d = this.O % this.L2) != 0.0) {
            this.O -= d;
        }
    }

    public double B$src$D$as08sg() {
        return this.Lk;
    }

    public double d$src$D$bap8yq() {
        return this.LM;
    }

    protected void L$src$V$axi75k() {
        double d = -6.0;
        SmoothFontRenderer smoothFontRenderer = this.O(0.75);
        List<String> list = this.Y$src$Ljava_util_List_$1h7ga6i();
        double d2 = this.n() + 5.0;
        for (String string : list) {
            double d3 = smoothFontRenderer.d(string);
            smoothFontRenderer.d(string, this.G$src$D$1b2f02a() + this.Z$src$D$1wvori2(), d2, this.LQ);
            d2 += d3;
            d += d3;
        }
        this.LD = d;
    }

    public void h(double d) {
        this.O = d;
    }

    @Override
    public void I() {
    }
}

