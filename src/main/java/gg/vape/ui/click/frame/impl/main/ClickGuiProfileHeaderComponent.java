package gg.vape.ui.click.frame.impl.main;

import gg.vape.config.Profile;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.click.component.input.BindableInputComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileGlyphIconPanel;
import java.awt.Color;

public class ClickGuiProfileHeaderComponent
extends GuiComponent {
    private Profile I;
    private final ProfileGlyphIconPanel b;
    private final TruncatedTextComponent i;
    private final BindableInputComponent K;
    private static final double R = 4.0;
    private static final String o = "...";

    public ClickGuiProfileHeaderComponent(Profile profile, double d) {
        this.I = profile;
        this.o(d);
        this.Y(22.0);
        this.d(false);
        String string = profile != null ? profile.n$src$Ljava_lang_String_$xqhelw() : "";
        this.i = new TruncatedTextComponent(string, o, d, 0.875, Color.WHITE, true);
        this.K = new BindableInputComponent(profile, ClickGuiProfileHeaderComponent.J.Z);
        this.K.f(false);
        this.K.Y(10.0);
        this.b = new ProfileGlyphIconPanel(profile);
        this.b.o(12.0);
        this.b.Y(12.0);
        this.H(this.i, this.K, this.b);
    }


    @Override
    public void H() {
        double d;
        super.onDisable();
        double d2 = this.G$src$D$1b2f02a();
        double d3 = this.n();
        double d4 = this.A();
        double d5 = d2 + d4;
        double d6 = this.K.A();
        double d7 = d5 - d6;
        double d8 = d3 + (this.L() - this.K.L()) / 2.0;
        this.K.K(d7);
        this.K.S(d8);
        d5 = d7;
        if (this.b.V$src$Z$1xhop3l()) {
            d = d5 - this.b.A();
            double d9 = d3 + (this.L() - this.b.L()) / 2.0;
            this.b.K(d);
            this.b.S(d9 + 3.0);
            d5 = d - 4.0;
        }
        d = Math.max(0.0, d5 - d2 - 4.0);
        this.i.K(d2);
        this.i.S(d3);
        this.i.o(d);
        this.i.Y(this.L());
        this.i.D(d);
        super.H();
    }

    public void R(Profile profile) {
        this.I = profile;
        this.i.O(profile != null ? profile.n$src$Ljava_lang_String_$xqhelw() : "");
        this.K.r(profile);
        this.b.N(profile);
        this.b.L$src$V$14lppcr();
    }

    public ProfileGlyphIconPanel Y$src$Lgg_vape_ui_click_frame_impl_profile_ProfileGlyp$eswjq4() {
        return this.b;
    }

    public BindableInputComponent w$src$Lgg_vape_ui_click_component_input_BindableInputC$1d1r2au() {
        return this.K;
    }
}

