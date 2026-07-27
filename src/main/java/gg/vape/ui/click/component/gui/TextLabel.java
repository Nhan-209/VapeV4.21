package gg.vape.ui.click.component.gui;

import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class TextLabel
extends InteractiveComponent {
    private boolean Y_;
    private static String YM;
    protected String Q;
    protected boolean I = true;
    protected double Yc;
    private boolean b;
    private boolean K;
    private Color v;
    private Color YI;

    @Override
    public double x() {
        return 0.0;
    }

    static {
        if (TextLabel.A$src$Ljava_lang_String_$3x6e5a() != null) {
            TextLabel.A("dpEwx");
        }
    }

    public double R() {
        SmoothFontRenderer smoothFontRenderer = this.K ? this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(this.Yc) : this.O(this.Yc);
        return smoothFontRenderer.d(this.Q.toUpperCase());
    }

    public boolean k$src$Z$8h3ev2() {
        return this.b;
    }

    public TextLabel(String string) {
        this(string, 0.9);
    }

    public double S$src$D$83wc3g() {
        SmoothFontRenderer smoothFontRenderer = this.K ? this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(this.Yc) : this.O(this.Yc);
        double d = smoothFontRenderer.d(this.Q);
        return this.n() + this.L() / 2.0 - d / 2.0;
    }

    public TextLabel d(String string) {
        this.Q = string;
        this.o(this.W());
        return this;
    }

    @Override
    public void I() {
    }

    public TextLabel c(boolean bl) {
        this.K = bl;
        return this;
    }

    public double W() {
        SmoothFontRenderer smoothFontRenderer = this.K ? this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(this.Yc) : this.O(this.Yc);
        return smoothFontRenderer.N(this.Q.toUpperCase());
    }

    public TextLabel(String string, double d, boolean bl) {
        this.YI = TextLabel.J.Z;
        this.v = null;
        this.Q = string;
        this.Yc = d;
        this.I = bl;
    }

    public TextLabel l(Color color) {
        this.YI = color;
        return this;
    }

    public Color G() {
        return this.YI;
    }

    @Override
    public void u() {
        if (this.A() < 0.0) {
            this.o(this.W());
        }
    }

    public TextLabel(String string, double d) {
        this.YI = TextLabel.J.Z;
        this.v = null;
        this.Q = string;
        this.Yc = d;
    }

    public String L$src$Ljava_lang_String_$1ncdwqb() {
        return this.Q;
    }

    public void s(boolean bl) {
        this.b = bl;
    }

    public static String A$src$Ljava_lang_String_$3x6e5a() {
        return YM;
    }

    public TextLabel B$src$Lgg_vape_ui_click_component_gui_TextLabel_$1bc29rb(boolean bl) {
        this.Y_ = bl;
        return this;
    }

    public boolean s$src$Z$8lhrly() {
        return this.K;
    }

    public TextLabel(String string, double d, boolean bl, Color color) {
        this(string, d, bl);
        this.v = color;
    }

    @Override
    public double C() {
        return 0.0;
    }

    public static void A(String string) {
        YM = string;
    }

    public boolean D$src$Z$7vnfpz() {
        return this.Y_;
    }

    public void B(Color color) {
        this.v = color;
    }

    public void a(boolean bl) {
        this.I = bl;
    }

    public TextLabel(String string, double d, boolean bl, double d2, double d3) {
        this.YI = TextLabel.J.Z;
        this.v = null;
        this.Q = string;
        this.Yc = d;
        this.I = bl;
        this.o(d2);
        this.Y(d3);
    }

    public void y(double d) {
        this.Yc = d;
    }


    @Override
    public void H() {
        Color color;
        String string = this.I ? this.Q.toUpperCase() : this.Q;
        SmoothFontRenderer smoothFontRenderer = this.K ? this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(this.Yc) : this.O(this.Yc);
        double d = this.S$src$D$83wc3g();
        color = this.YI != null
                ? (this.w$src$Z$e457mb() ? this.YI.brighter() : this.YI)
                : (this.w$src$Z$e457mb() ? TextLabel.J.A : TextLabel.J.Z);
        if (this.v != null) {
            smoothFontRenderer.W(string, this.G$src$D$1b2f02a() + this.A() / 2.0, d, color);
            GuiRenderPrimitives.P(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.w$src$Z$e457mb() ? this.v.brighter() : this.v, 1.5f, 0.75f, 1.0f);
            return;
        }
        if (this.Y_) {
            smoothFontRenderer.W(string, this.G$src$D$1b2f02a() + this.A() / 2.0, d, color);
        } else {
            smoothFontRenderer.d(string, this.G$src$D$1b2f02a(), d, color);
        }
        if (this.b) {
            GuiRenderPrimitives.L(this.G$src$D$1b2f02a(), d + smoothFontRenderer.d(string), this.A(), TextLabel.J.Z);
        }
    }

    public boolean N$src$Z$815dnl() {
        return this.I;
    }

    public Color T$src$Ljava_awt_Color_$1e8t7l7() {
        return this.v;
    }
}
