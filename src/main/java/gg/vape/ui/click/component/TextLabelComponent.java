package gg.vape.ui.click.component;

import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.FadingTruncatedTextComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.click.text.TextLabelFitScaleCache;
import gg.vape.ui.click.text.TextLabelFitSpec;
import gg.vape.ui.click.text.TruncatedTextSpec;
import gg.vape.ui.font.SmoothFontRenderer;
import java.awt.Color;

public class TextLabelComponent
extends GuiComponent {
    private boolean b;
    private Color a;
    private FadingTruncatedTextComponent R;
    private GuiComponent i;
    private TruncatedTextComponent I;
    private boolean O;
    private TextLabelFitSpec o;

    public boolean u$src$Z$18fsepf() {
        return this.b;
    }

    public boolean z() {
        return this.O;
    }

    public void a(TextLabelFitSpec textLabelFitSpec) {
        this.o = textLabelFitSpec;
    }

    public void F(boolean bl) {
        this.b = bl;
    }

    public boolean r$src$Z$18e50xc() {
        return this.o.x();
    }

    @Override
    public double x() {
        return this.k();
    }

    public TextLabelComponent(String string, double d, double d2, double d3, double d4, boolean bl, boolean bl2, Color color) {
        this(string, d, d2, d3, d4, bl, bl2, color, null);
    }

    public void N(double d) {
        this.o.Y(d);
    }

    @Override
    public void H() {
        this.R(this.G$src$D$1b2f02a(), this.n() + this.C() / 2.0);
    }

    @Override
    public void u() {
    }

    public void f(double d) {
        this.o.k(d);
    }

    public double v() {
        if (this.I != null) {
            return this.I.f$src$D$ldt7xy();
        }
        if (this.R != null) {
            return this.R.f$src$D$ldt7xy();
        }
        double d = TextLabelFitScaleCache.m.T(this.o);
        return this.o.x() ? this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(d).d(this.o.o()) : this.O(d).d(this.o.o());
    }

    public GuiComponent q$src$Lgg_vape_ui_click_component_GuiComponent_$1sqrlko() {
        return this.i;
    }

    @Override
    public double C() {
        return this.v();
    }

    public double k() {
        if (this.I != null) {
            return this.I.u$src$D$ivbecn();
        }
        if (this.R != null) {
            return this.R.u$src$D$ivbecn();
        }
        double d = TextLabelFitScaleCache.m.T(this.o);
        return this.o.x() ? this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(d).N(this.o.o()) : this.O(d).N(this.o.o());
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    public TextLabelComponent(String string, double d, double d2, double d3, double d4, boolean bl, boolean bl2, Color color, GuiComponent guiComponent) {
        this(string, d, d2, d3, d4, bl, bl2, color, guiComponent, false);
    }

    public void J(boolean bl) {
        this.O = bl;
    }

    @Override
    public void I() {
    }

    public TextLabelComponent(String string, double d, double d2, double d3, double d4, boolean bl, boolean bl2, Color color, GuiComponent guiComponent, boolean bl3) {
        this.o = new TextLabelFitSpec(string, d, d2, d3, d4, bl);
        this.a = color;
        this.b = bl2;
        this.i = guiComponent;
        this.O = bl3;
    }

    public void R(double d, double d2) {
        double d3 = TextLabelFitScaleCache.m.T(this.o);
        if (d3 >= this.o.g()) {
            SmoothFontRenderer smoothFontRenderer;
            SmoothFontRenderer smoothFontRenderer2 = smoothFontRenderer = this.o.x() ? this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(d3) : this.O(d3);
            if (this.b) {
                smoothFontRenderer.v(this.o.o(), d, d2 - smoothFontRenderer.d(this.o.o()) / 2.0, this.a);
            } else {
                smoothFontRenderer.d(this.o.o(), d, d2 - smoothFontRenderer.d(this.o.o()) / 2.0, this.a);
            }
            this.M(null);
            this.I = null;
        } else if (this.O) {
            if (this.R == null) {
                this.R = new FadingTruncatedTextComponent(this.o.o(), this.o.T(), this.o.g(), this.a, null, this.r$src$Z$18e50xc(), this.b);
            }
            this.e();
            this.R.V(d, d2 - this.v() / 2.0);
            this.M(this.R.J$src$Lgg_vape_ui_click_component_ToolTips_$bb9snf());
        } else {
            if (this.I == null) {
                this.I = new TruncatedTextComponent(this.o.o(), "...", this.o.T(), this.o.g(), this.a, this.r$src$Z$18e50xc(), this.b);
            }
            this.W();
            this.I.V(d, d2 - this.v() / 2.0);
            this.M(this.I.J$src$Lgg_vape_ui_click_component_ToolTips_$bb9snf());
        }
    }

    public double s() {
        return this.o.g();
    }

    public Color a$src$Ljava_awt_Color_$9fqh83() {
        return this.a;
    }

    public void U(String string) {
        this.o.N(string);
    }

    public TextLabelFitSpec d$src$Lgg_vape_ui_click_text_TextLabelFitSpec_$1sd3ps() {
        return this.o;
    }

    public String H$src$Ljava_lang_String_$1kzmics() {
        return this.o.o();
    }

    private void e() {
        this.R.t(new TruncatedTextSpec(this.o.o(), "", this.o.T(), this.o.g(), this.o.x()));
        this.R.R(this.a$src$Ljava_awt_Color_$9fqh83());
        this.R.K(this.u$src$Z$18fsepf());
    }

    public void T(boolean bl) {
        this.o.Q(bl);
    }

    public void b(Color color) {
        if (this.R != null) {
            this.R.C(color);
        }
    }

    @Override
    public void S(double d) {
        super.S(d);
    }

    public double o$src$D$18chmmb() {
        return this.o.h();
    }

    public void d(double d) {
        this.o.u(d);
    }

    public void Q(double d) {
        this.o.W(d);
    }

    public double D$src$D$17ouh3s() {
        return this.o.u();
    }


    public void o(GuiComponent guiComponent) {
        this.i = guiComponent;
    }

    @Override
    public void F() {
    }

    private void W() {
        this.I.t(new TruncatedTextSpec(this.o.o(), "...", this.o.T(), this.o.g(), this.o.x()));
        this.I.R(this.a$src$Ljava_awt_Color_$9fqh83());
        this.I.K(this.u$src$Z$18fsepf());
    }

    public void a(Color color) {
        this.a = color;
    }

    public double B$src$D$17nqvx2() {
        return this.o.T();
    }
}

