package gg.vape.ui.click.frame.impl.profile;

import gg.vape.ui.click.animation.Animation;
import gg.vape.ui.click.animation.ThemeColorAnimation;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class ProfileCreateActionButtonComponent
extends InteractiveComponent {
    private double d0;
    private boolean dO;
    private Color d4;
    private boolean d3;
    private Color dn;
    private boolean d9;
    private double dC;
    private String I;
    private boolean b;
    private String v;
    private boolean dF = true;
    private double d8;
    private Animation<Color> K = new ThemeColorAnimation(0.15, new Color(0, 0, 0, 0));
    private Color Q;

    @Override
    public void H() {
        Color color;
        SmoothFontRenderer smoothFontRenderer = this.b ? this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(this.d8) : this.O(this.d8);
        float f = 8.0f * (float)this.dC;
        double d = this.G$src$D$1b2f02a() + (double)(f / 2.0f) + 5.0 + this.d0 + this.Z$src$D$1wvori2() - 5.0;
        if (this.w$src$Z$e457mb() && this.dF) {
            GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), J.z());
        }
        Color color2 = color = this.w$src$Z$e457mb() ? J.B() : (this.d9 ? J.z() : this.dn);
        if (!this.dF) {
            color = this.w$src$Z$e457mb() ? this.dn.brighter() : this.dn;
        }
        GuiRenderPrimitives.F(this.I, d, this.n() + this.L() / 2.0, (double)f, f, color);
        d += 3.3333333333333335 + (double)(f / 2.0f);
        if (this.Q != null) {
            Color color3;
            Color color4 = color3 = this.w$src$Z$e457mb() ? J.B() : (this.dO ? J.z() : this.Q);
            if (!this.dF) {
                color3 = this.w$src$Z$e457mb() ? this.Q.brighter() : this.Q;
            }
            this.Z(smoothFontRenderer, d, color3);
        }
        if (this.d4 != null) {
            GuiRenderPrimitives.P(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.w$src$Z$e457mb() ? this.d4.brighter() : this.d4, 1.5f, 0.75f, 1.0f);
        }
    }

    public void J(double d) {
        this.dC = d;
    }


    @Override
    public double x() {
        return 0.0;
    }

    @Override
    public double C() {
        return 0.0;
    }

    public void n(double d) {
        this.d0 = d;
    }

    @Override
    public void F() {
        if (!this.w$src$Z$e457mb()) {
            this.K.J();
        }
        super.F();
    }

    @Override
    public void onEnable() {
        this.K.J();
        super.onEnable();
    }

    @Override
    public void u() {
        if (this.A() <= 0.0) {
            this.o(8.0 * this.dC + (this.b ? this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(this.d8) : this.O(this.d8)).N(this.d3 ? this.v.toUpperCase() : this.v) + 13.75);
        }
    }

    private void Z(SmoothFontRenderer smoothFontRenderer, double d, Color color) {
        String string = this.d3 ? this.v.toUpperCase() : this.v;
        double d2 = smoothFontRenderer.d(string);
        double d3 = this.n() + this.L() / 2.0 - d2 / 2.0;
        smoothFontRenderer.d(string, d, d3, color);
    }

    public ProfileCreateActionButtonComponent(String string, boolean bl, boolean bl2, double d, Color color, String string2, double d2, Color color2, Color color3) {
        this.v = string;
        this.d3 = bl;
        this.b = bl2;
        this.d8 = d;
        this.Q = color;
        if (color == null) {
            this.Q = ProfileCreateActionButtonComponent.J.Z;
        }
        if (this.Q.equals(J.z())) {
            this.dO = true;
        }
        this.I = string2;
        this.dC = d2;
        this.dn = color2;
        if (color2 == null) {
            this.dn = ProfileCreateActionButtonComponent.J.W;
        }
        if (this.dn.equals(J.z())) {
            this.d9 = true;
        }
        this.d4 = color3;
        this.Y(20.0);
    }

    public void J(boolean bl) {
        this.dF = bl;
    }

    public void p(double d) {
        this.d8 = d;
    }
}
