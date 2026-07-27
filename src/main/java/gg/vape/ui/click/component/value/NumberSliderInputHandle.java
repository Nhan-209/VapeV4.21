package gg.vape.ui.click.component.value;

import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.component.value.NumberSliderComponent;
import gg.vape.ui.click.component.value.SliderInputHandle;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;

public class NumberSliderInputHandle
extends SliderInputHandle {
    private NumberSliderComponent XV;


    @Override
    public void Y$src$V$npqhoj() {
        this.k(((Double)this.XV.f$src$Lgg_vape_value_NumberValue_$1des1vc().K()).toString());
    }

    public NumberSliderInputHandle(NumberSliderComponent numberSliderComponent) {
        this.XV = numberSliderComponent;
        this.b.Z(false);
    }

    @Override
    public void H() {
        SmoothFontRenderer smoothFontRenderer = this.O(0.75);
        String string = "";
        string = this.W() ? this.i$src$Ljava_lang_String_$1n2xf3k() : (this.kd ? this.XV.O$src$Ljava_text_DecimalFormat_$mv9dca().format(this.XV.f$src$Lgg_vape_value_NumberValue_$1des1vc().K()) : this.XV.O$src$Ljava_text_DecimalFormat_$mv9dca().format(this.XV.f$src$Lgg_vape_value_NumberValue_$1des1vc().K()) + " " + this.XV.n$src$Ljava_lang_String_$af0f9r());
        if (!this.W() && !this.kd && this.XV.n$src$Ljava_lang_String_$af0f9r().length() <= 1) {
            string = this.XV.O$src$Ljava_text_DecimalFormat_$mv9dca().format(this.XV.f$src$Lgg_vape_value_NumberValue_$1des1vc().K());
        }
        smoothFontRenderer.d(string, this.G$src$D$1b2f02a() + (this.r() - smoothFontRenderer.N(string)), this.n(), NumberSliderInputHandle.J.Z);
        if (this.n$src$Z$1rnxqrn()) {
            this.x2 = string.length();
            this.S(smoothFontRenderer, this.G$src$D$1b2f02a() + this.r(), this.n());
        }
        GuiRenderPrimitives.C(this.G$src$D$1b2f02a(), this.n() + 5.0 + 2.0, this.A(), 1.0, this.C$src$Ljava_awt_Color_$13eqlq4());
    }

    @Override
    public void p() {
        try {
            String string = this.i$src$Ljava_lang_String_$1n2xf3k().replace(this.XV.f$src$Lgg_vape_value_NumberValue_$1des1vc().Q$src$Ljava_text_DecimalFormat_$j98hth().getDecimalFormatSymbols().getDecimalSeparator(), '.');
            double d = Double.parseDouble(string);
            this.XV.f$src$Lgg_vape_value_NumberValue_$1des1vc().e(d);
        }
        catch (Exception exception) {
            // empty catch block
        }
        ClientSettings.fT = null;
    }
}

