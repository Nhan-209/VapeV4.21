package gg.vape.ui.click.frame.impl.profile;

import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.SquareIconButtonComponent;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.FrameHeaderComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.ImageRenderer;
import java.awt.Color;
import org.jetbrains.annotations.Nullable;

public class PublicProfilesFrameHeaderActionComponent
extends FrameHeaderComponent {
    @Nullable
    private String G;
    private static final String Q = "newclose";
    @Nullable
    private GuiClickListener R;
    private float K;
    private String I;
    private SquareIconButtonComponent v = new SquareIconButtonComponent(Q, 1.5);

    public PublicProfilesFrameHeaderActionComponent(Frame frame, String string, String string2) {
        this(frame, string, string2, 1.0);
    }

    public SquareIconButtonComponent O$src$Lgg_vape_ui_click_component_SquareIconButtonComp$z3cp96() {
        return this.v;
    }

    public PublicProfilesFrameHeaderActionComponent Q(@Nullable GuiClickListener guiClickListener) {
        this.R = guiClickListener;
        return this;
    }


    public String K$src$Ljava_lang_String_$bvh3j6() {
        return this.I;
    }

    public void j(String string) {
        this.I = string;
    }

    @Override
    public void H() {
        SmoothFontRenderer smoothFontRenderer = this.O(0.9);
        Color color = PublicProfilesFrameHeaderActionComponent.J.A;
        double d = smoothFontRenderer.d(this.I);
        double d2 = this.n() + this.L() / 2.0 - d / 2.0;
        if (this.G != null) {
            double d3 = this.n() + this.L() / 2.0 - (double)(8.0f * this.K / 2.0f);
            smoothFontRenderer.d(this.I, this.G$src$D$1b2f02a() + 10.0 + 8.0, d2, color);
            ImageRenderer.E(color, (float)this.G$src$D$1b2f02a() + 5.0f, (float)d3, this.G, 8.0f * this.K, 8.0f * this.K, false);
        } else {
            smoothFontRenderer.d(this.I, this.G$src$D$1b2f02a() + 5.0, d2, color);
        }
        this.v.K(this.G$src$D$1b2f02a() + this.A() - 7.5 - 8.0);
        this.v.S(this.n());
        this.v.Y(this.L());
    }

    public PublicProfilesFrameHeaderActionComponent(Frame frame, @Nullable String string, String string2, double d) {
        super(frame);
        this.K = (float)d;
        this.G = string;
        this.I = string2;
        this.v.r(() -> {
            if (this.R != null) {
                this.R.P();
            }
            ClientSettings.f(frame.getClass(), false);
        });
        this.H(this.v);
    }
}
