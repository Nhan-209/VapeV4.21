package gg.vape.ui.click.component.value;

import gg.vape.input.KeyboardInput;
import gg.vape.ui.click.component.TextInputComponentBase;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.RenderUtils;
import gg.vape.value.StringValue;
import gg.vape.value.Value;

public class StringValueTextInputComponent
extends TextInputComponentBase {
    private final StringValue ja;
    private static final String db = "Click to set";


    @Override
    public void p() {
        this.L$src$V$w6nnjd();
    }

    @Override
    public double C() {
        return 25.0;
    }

    @Override
    public double r() {
        return this.p$src$D$187zcry() - 20.0;
    }

    @Override
    public void C(Value value) {
        super.C(value);
        this.k(value.toString());
    }

    @Override
    public void H() {
        double d;
        double d2;
        double d3;
        double d4;
        this.onDisable();
        this.b(this.ja.getName());
        if (!this.n$src$Z$1rnxqrn()) {
            this.k((String)this.ja.K());
        }
        double d5 = this.G$src$D$1b2f02a() + 5.0;
        double d6 = d5 + 5.0;
        double d7 = this.n() + 10.0;
        double d8 = 14.0;
        GuiRenderPrimitives.d(d5, d7, this.p$src$D$187zcry() - 10.0, d8, this.xR.getInterpolatedColor());
        GuiRenderPrimitives.d(d5 + 0.5, d7 + 0.5, this.p$src$D$187zcry() - 10.0 - 1.0, d8 - 1.0, StringValueTextInputComponent.J.m);
        SmoothFontRenderer smoothFontRenderer = this.O(0.8);
        smoothFontRenderer.d(this.ja.getName(), d5, this.n() + 2.0, StringValueTextInputComponent.J.A);
        SmoothFontRenderer smoothFontRenderer2 = this.O(0.9);
        double d9 = smoothFontRenderer2.d(this.C$src$Ljava_lang_String_$1pcbyty());
        double d10 = d7 + d8 / 2.0 - d9 / 2.0;
        String string = this.i$src$Ljava_lang_String_$1n2xf3k();
        boolean bl = this.n$src$Z$1rnxqrn();
        if (!(this.i$src$Ljava_lang_String_$1n2xf3k() != null && this.i$src$Ljava_lang_String_$1n2xf3k().length() >= 1 || bl)) {
            string = db;
        }
        if (string == null) {
            string = "";
        }
        boolean bl2 = (d4 = smoothFontRenderer2.N(this.i$src$Ljava_lang_String_$1n2xf3k()) - this.r()) > 0.0;
        double d11 = 0.0;
        if (bl2) {
            RenderUtils.m(d5 + 3.0, this.n() + 2.5, this.p$src$D$187zcry() - 14.0, this.L() - 5.0);
            d11 = -d4;
        }
        if (this.x2 > string.length()) {
            this.x2 = string.length();
        }
        if (this.x2 < 0) {
            this.x2 = 0;
        }
        if ((d3 = d6 - (d2 = d6 + (d = smoothFontRenderer2.N(string.substring(0, this.x2))) + d11)) > 0.0) {
            d11 += d3;
            d2 += d3;
        }
        smoothFontRenderer2.d(string, d6 + d11, d10, StringValueTextInputComponent.J.Z);
        if (bl2) {
            RenderUtils.T();
        }
        if (bl) {
            if (this.x2 > string.length()) {
                this.x2 = string.length();
            }
            if (this.x2 < 0) {
                this.x2 = 0;
            }
            this.S(this.O(1.2), d2, d10 - 1.0);
        }
        if (bl && KeyboardInput.isKeyDown(8) && this.e$src$Lgg_vape_utils_TimerUtil_$1qrc1iy().hasTimeElapsed(100L)) {
            this.e$src$Lgg_vape_utils_TimerUtil_$1qrc1iy().reset();
        }
    }

    @Override
    public void k(String string) {
        super.k(string);
        this.ja.o(this.i$src$Ljava_lang_String_$1n2xf3k());
    }

    public StringValueTextInputComponent(StringValue stringValue) {
        super("");
        this.ja = stringValue;
        this.C(stringValue);
        this.t$src$Lgg_vape_ui_click_component_GlyphIconComponent_$s6bz9o().Z(false);
    }

    @Override
    public double x() {
        return 110.0;
    }
}

