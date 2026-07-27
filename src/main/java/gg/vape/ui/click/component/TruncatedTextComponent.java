package gg.vape.ui.click.component;

import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.text.SuffixTextTruncationIndexCache;
import gg.vape.ui.click.text.TruncatedTextSpec;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.StringUtils;
import java.awt.Color;

public class TruncatedTextComponent
extends GuiComponent {
    public boolean G;
    public Color o;
    private boolean K = true;
    protected String a = "";
    private static final String Q = "...";
    protected String v;
    public TruncatedTextSpec I;
    private boolean b = false;

    @Override
    public void I() {
    }

    @Override
    public void H() {
        this.V(this.G$src$D$1b2f02a(), this.n());
    }

    public void p(boolean bl) {
        this.I.B(bl);
    }

    @Override
    public void u() {
    }

    public void M(double d) {
        this.I.R(d);
    }

    public double f$src$D$ldt7xy() {
        double d = this.b$src$D$lbm1ki();
        return this.a$src$Z$lb29i3() ? this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(d).d(this.S$src$Ljava_lang_String_$1bp7ddx()) : this.O(d).d(this.S$src$Ljava_lang_String_$1bp7ddx());
    }

    public TruncatedTextComponent(String string, String string2, double d, double d2, Color color, boolean bl, boolean bl2) {
        this(string, string2, "", d, d2, color, bl, bl2);
    }

    public double b$src$D$lbm1ki() {
        return this.I.N();
    }

    public void G(String string) {
        this.a = string;
    }

    public TruncatedTextComponent(String string, String string2, double d, double d2, Color color, boolean bl) {
        this(string, string2, d, d2, color, bl, false);
    }

    public TruncatedTextComponent(String string, String string2, String string3, double d, double d2, Color color, boolean bl, boolean bl2) {
        this.I = new TruncatedTextSpec(string, string2, d, d2, bl);
        this.o = color;
        this.G = bl2;
        this.a = string3;
    }

    public TruncatedTextSpec i$src$Lgg_vape_ui_click_text_TruncatedTextSpec_$1pvuzoc() {
        return this.I;
    }

    public String S$src$Ljava_lang_String_$1bp7ddx() {
        return this.I.g();
    }

    public TruncatedTextComponent(String string, double d, double d2) {
        this(string, Q, d, d2, TruncatedTextComponent.J.A, false, false);
    }

    @Override
    public void F() {
    }

    public String w$src$Ljava_lang_String_$15ti09t() {
        return this.I.L();
    }

    public void V(double d, double d2) {
        String string;
        StringBuilder stringBuilder;
        boolean bl;
        SmoothFontRenderer smoothFontRenderer;
        if (this.S$src$Ljava_lang_String_$1bp7ddx().isEmpty()) {
            return;
        }
        int n = SuffixTextTruncationIndexCache.H.n(this.I);
        SmoothFontRenderer smoothFontRenderer2 = smoothFontRenderer = this.I.q() ? this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(this.I.N()) : this.O(this.I.N());
        String string2 = n >= 0 ? (n < this.I.g().length() - 1 ? this.I.g().substring(0, n) + this.I.L() : this.I.g()) : this.I.L();
        String string3 = StringUtils.l(string2);
        if (string3.isEmpty()) {
            string2 = string3;
        }
        double d3 = smoothFontRenderer.d("A");
        double d4 = d2 + this.L() / 2.0 - d3 / 2.0;
        double d5 = d;
        double d6 = d4;
        if (this.b) {
            d5 += this.A() / 2.0;
        }
        if (this.G) {
            if (this.b) {
                smoothFontRenderer.f(string2, d5, d6, this.o);
            } else {
                smoothFontRenderer.v(string2, d5, d6, this.o);
            }
        } else if (this.b) {
            smoothFontRenderer.W(string2, d5, d6, this.o);
        } else {
            smoothFontRenderer.d(string2, d5, d6, this.o);
        }
        boolean bl2 = bl = this.K && n < this.I.g().length() - 1;
        if (bl) {
            String string4;
            StringBuilder stringBuilder2;
            boolean bl3 = !this.a.isEmpty();
            StringBuilder stringBuilder3 = new StringBuilder();
            StringBuilder stringBuilder4 = stringBuilder3.append(this.I.g());
            if (bl3) {
                StringBuilder stringBuilder5;
                stringBuilder2 = stringBuilder5 = stringBuilder4;
                string4 = "\n" + this.a;
            } else {
                stringBuilder2 = stringBuilder4;
                string4 = "";
            }
            String string5 = stringBuilder2.append(string4).toString();
            this.w(string5);
            return;
        }
        boolean bl4 = !this.a.isEmpty();
        StringBuilder stringBuilder6 = new StringBuilder();
        StringBuilder stringBuilder7 = stringBuilder6.append("");
        if (bl4) {
            StringBuilder stringBuilder8;
            stringBuilder = stringBuilder8 = stringBuilder7;
            string = this.a;
        } else {
            stringBuilder = stringBuilder7;
            string = "";
        }
        String string6 = stringBuilder.append(string).toString();
        this.w(string6);
    }

    public double v() {
        return this.I.y();
    }

    public Color x$src$Ljava_awt_Color_$wl245c() {
        return this.o;
    }

    public String Q$src$Ljava_lang_String_$182wk07() {
        return this.a;
    }

    public boolean S$src$Z$l3d571() {
        return this.G;
    }

    public boolean p() {
        return this.b;
    }

    public boolean I$src$Z$kxv79f() {
        return this.K;
    }


    public void D(double d) {
        this.I.O(d);
    }

    public void K(boolean bl) {
        this.G = bl;
    }

    public boolean a$src$Z$lb29i3() {
        return this.I.q();
    }

    public void s(boolean bl) {
        this.K = bl;
    }

    public void R(Color color) {
        this.o = color;
    }

    public double u$src$D$ivbecn() {
        SmoothFontRenderer smoothFontRenderer;
        int n = SuffixTextTruncationIndexCache.H.n(this.I);
        SmoothFontRenderer smoothFontRenderer2 = smoothFontRenderer = this.I.q() ? this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(this.I.N()) : this.O(this.I.N());
        if (n == this.I.g().length() - 1) {
            return smoothFontRenderer.N(this.S$src$Ljava_lang_String_$1bp7ddx());
        }
        if (n == -1) {
            return smoothFontRenderer.N(this.w$src$Ljava_lang_String_$15ti09t());
        }
        if (n == -2) {
            return 0.0;
        }
        return smoothFontRenderer.N(this.S$src$Ljava_lang_String_$1bp7ddx().substring(0, n) + this.w$src$Ljava_lang_String_$15ti09t());
    }

    public void L(String string) {
        this.I.w(string);
    }

    public void N(boolean bl) {
        this.b = bl;
    }

    public void t(TruncatedTextSpec truncatedTextSpec) {
        this.I = truncatedTextSpec;
    }

    public TruncatedTextComponent(String string, String string2, double d, double d2) {
        this(string, string2, d, d2, TruncatedTextComponent.J.A, false, false);
    }

    @Override
    public double C() {
        return this.f$src$D$ldt7xy();
    }

    public void O(String string) {
        this.I.v(string);
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Override
    public double x() {
        return this.u$src$D$ivbecn();
    }
}

