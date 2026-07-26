package gg.vape.ui.click.component.value;

import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.component.value.RandomRangeSliderComponent;
import gg.vape.ui.click.component.value.RangeEndpoint;
import gg.vape.ui.click.component.value.SliderInputHandle;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;

public class RandomRangeSliderInputHandle
extends SliderInputHandle {
    private RangeEndpoint no;
    private RandomRangeSliderComponent n0;

    private static Exception a(Exception exception) {
        return exception;
    }

    public RandomRangeSliderInputHandle(RandomRangeSliderComponent randomRangeSliderComponent, RangeEndpoint rangeEndpoint) {
        this.n0 = randomRangeSliderComponent;
        this.no = rangeEndpoint;
        this.b.Z(false);
    }

    @Override
    public void H() {
        SmoothFontRenderer smoothFontRenderer = this.O(0.75);
        String string = this.W() ? this.i$src$Ljava_lang_String_$1n2xf3k() : this.M();
        smoothFontRenderer.d(string, this.G$src$D$1b2f02a() + (this.r() - smoothFontRenderer.N(string)), this.n(), RandomRangeSliderInputHandle.J.Z);
        if (this.n$src$Z$1rnxqrn()) {
            this.x2 = string.length();
            this.S(smoothFontRenderer, this.G$src$D$1b2f02a() + this.r(), this.n());
        }
        GuiRenderPrimitives.C(this.G$src$D$1b2f02a(), this.n() + 5.0 + 2.0, this.A(), 1.0, this.C$src$Ljava_awt_Color_$13eqlq4());
    }

    @Override
    public void p() {
        try {
            String string = this.i$src$Ljava_lang_String_$1n2xf3k().replace(this.n0.W().y$src$Ljava_text_DecimalFormat_$bdq2sj().getDecimalFormatSymbols().getDecimalSeparator(), '.');
            double d = Double.parseDouble(string);
            switch (this.no) {
                case MINIMUM: {
                    if (d > this.n0.W().M()) {
                        this.n0.W().a(this.n0.W().M());
                        this.n0.W().Q(d);
                        break;
                    }
                    this.n0.W().a(d);
                    break;
                }
                case MAXIMUM: {
                    if (d < this.n0.W().q$src$D$vgz097()) {
                        this.n0.W().Q(this.n0.W().q$src$D$vgz097());
                        this.n0.W().a(d);
                        break;
                    }
                    this.n0.W().Q(d);
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.n0.W().j(new double[]{this.n0.W().q$src$D$vgz097(), this.n0.W().M()});
        ClientSettings.fT = null;
    }

    @Override
    public void Y$src$V$npqhoj() {
        this.k(this.M());
    }

    public String M() {
        switch (this.no) {
            case MINIMUM: {
                return this.n0.W().y$src$Ljava_lang_String_$1nuhg7p();
            }
            case MAXIMUM: {
                return this.n0.W().E();
            }
        }
        return null;
    }
}

