package gg.vape.ui.click.frame.impl.profile;

import gg.vape.config.PublicProfileSummary;
import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.IconGlyphComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.SkeletonPlaceholderComponent;
import gg.vape.ui.click.component.gui.WrappedTextComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfilesFrame;
import gg.vape.utils.MutableColor;
import gg.vape.utils.render.GuiRenderPrimitives;
import org.jetbrains.annotations.Nullable;

public class PublicProfileListingResultCardComponent
extends GuiComponent {
    private SkeletonPlaceholderComponent G;
    private final ColorAnimation R;
    private SimpleTextLabelComponent o;
    private SkeletonPlaceholderComponent K;
    private IconGlyphComponent b;
    @Nullable
    private PublicProfileSummary I;
    private SkeletonPlaceholderComponent a;
    private final ColorAnimation v;
    private WrappedTextComponent O;
    private SimpleTextLabelComponent i;
    private boolean Q;

    @Override
    public void H() {
        this.v.u(this.w$src$Z$e457mb());
        this.R.u(this.w$src$Z$e457mb());
        GuiRenderPrimitives.B(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), PublicProfileListingResultCardComponent.J.m, 2.0f);
        GuiRenderPrimitives.B(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.v.getInterpolatedColor(), 2.0f);
        GuiRenderPrimitives.P(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.R.getInterpolatedColor(), 2.0f, 1.0f, 1.0f);
        if (this.I != null) {
            this.O.K(this.G$src$D$1b2f02a() + 8.0);
            this.O.S(this.n() + 8.0);
            this.i.K(this.G$src$D$1b2f02a() + 8.0);
            this.i.S(this.O.n() - 1.0 + this.O.C() + 4.0);
            double d = 10.0;
            double d2 = this.G$src$D$1b2f02a() + 8.0;
            double d3 = this.n() + this.L() - d - 8.0;
            GuiRenderPrimitives.B(d2, d3, this.b.A() + this.o.A() + 1.0, d, PublicProfileListingResultCardComponent.J.m.brighter(), (float)(d / 2.0) - 0.5f);
            if (this.Q) {
                double d4 = this.G$src$D$1b2f02a() + 40.0;
                GuiRenderPrimitives.B(d4, d3, 30.0, d, new MutableColor(PublicProfileListingResultCardComponent.J.q).withAlpha(80), (float)(d / 2.0) - 0.5f);
                this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(0.7).W(this.I.n(), (int)d4 + 15, (int)d3 + 3, PublicProfileListingResultCardComponent.J.q);
            }
            this.b.K(d2 + 5.0);
            this.b.S(d3 + 2.0);
            this.b.S(PublicProfileListingResultCardComponent.J.W);
            this.o.K(this.b.G$src$D$1b2f02a() + this.b.A() - 1.5);
            this.o.S(this.b.n() - 1.5);
            this.o.o(12.0 + this.o.h());
        } else {
            double d = this.G$src$D$1b2f02a();
            this.getClass();
            this.a.K(d + 5.0);
            double d5 = this.n();
            this.getClass();
            this.a.S(d5 + 5.0);
            double d6 = this.G$src$D$1b2f02a();
            this.getClass();
            this.G.K(d6 + 5.0);
            this.G.S(this.a.n() + this.a.L() + 2.0);
            double d7 = this.G$src$D$1b2f02a();
            this.getClass();
            this.K.K(d7 + 5.0 * 1.5);
            this.K.S(this.n() + this.L() - this.K.L() - 8.0);
        }
    }

    @Nullable
    public PublicProfileSummary r$src$Lgg_vape_config_PublicProfileSummary_$1fdzurr() {
        return this.I;
    }


    public PublicProfileListingResultCardComponent(@Nullable PublicProfileSummary publicProfileSummary) {
        this.getClass();
        this.R = new ColorAnimation(0.15, PublicProfileListingResultCardComponent.J.m, PublicProfileListingResultCardComponent.J.l);
        this.getClass();
        this.v = new ColorAnimation(0.15, PublicProfileListingResultCardComponent.J.t, PublicProfileListingResultCardComponent.J.E);
        this.o(78.0);
        this.Y(72.0);
        if (publicProfileSummary != null) {
            this.I = publicProfileSummary;
            this.O = new WrappedTextComponent(publicProfileSummary.h$src$Ljava_lang_String_$1lo47nn(), 0.85);
            this.O.l(true);
            this.O.P(true);
            this.O.o(50.0);
            this.O.c(50.0);
            this.O.Y(10.0);
            this.O.T$src$V$1orl066(PublicProfileListingResultCardComponent.J.A);
            this.O.l(true);
            this.i = new SimpleTextLabelComponent(publicProfileSummary.l() != null ? publicProfileSummary.l().o() : "Anonymous", 0.75);
            this.i.g(0.0f);
            this.i.z(0.0f);
            this.o = new SimpleTextLabelComponent(Long.toString(this.I.v()), 0.7);
            this.o.l(true);
            this.b = new IconGlyphComponent("like active@2x", 6.0f, 5.0f);
            this.o(true);
            if (publicProfileSummary.n() != null && publicProfileSummary.n().equalsIgnoreCase(ClientSettings.g(PublicProfilesFrame.class).o$src$Ljava_lang_String_$ububnq())) {
                this.Q = true;
            }
            this.H(this.O, this.i, this.b, this.o);
        } else {
            GuiComponent[] guiComponentArray = new GuiComponent[1];
            this.a = new SkeletonPlaceholderComponent(60.0, 10.0);
            guiComponentArray[0] = this.a;
            this.H(guiComponentArray);
            GuiComponent[] guiComponentArray2 = new GuiComponent[1];
            this.G = new SkeletonPlaceholderComponent(30.0, 10.0);
            guiComponentArray2[0] = this.G;
            this.H(guiComponentArray2);
            GuiComponent[] guiComponentArray3 = new GuiComponent[1];
            this.K = new SkeletonPlaceholderComponent(25.0, 10.0);
            guiComponentArray3[0] = this.K;
            this.H(guiComponentArray3);
        }
    }
}

