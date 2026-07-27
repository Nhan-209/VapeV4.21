package gg.vape.ui.click.component.gui;

import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.gui.ActionButtonBase;
import gg.vape.ui.font.SmoothFontRenderer;
import java.awt.Color;

public class TextActionButton
extends ActionButtonBase {
    protected String zG;
    protected boolean K;
    protected ColorAnimation b;
    protected double z_;

    public TextActionButton(String string, double d, boolean bl, double d2, double d3, Color color, double d4) {
        super(d2, d3, color, d4);
        this.b = new ColorAnimation(0.15, TextActionButton.J.W, TextActionButton.J.f);
        this.zG = string;
        this.z_ = d;
        this.K = bl;
    }

    @Override
    public void H() {
        super.H();
        SmoothFontRenderer smoothFontRenderer = this.K ? this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(this.z_) : this.O(this.z_);
        smoothFontRenderer.d(this.zG, this.G$src$D$1b2f02a() + this.A() / 2.0 - smoothFontRenderer.N(this.zG) / 2.0, this.n() + this.L() / 2.0 - smoothFontRenderer.d(this.zG) / 2.0, this.b.getInterpolatedColor());
    }

    public void D(ColorAnimation colorAnimation) {
        this.b = colorAnimation;
    }


    public ColorAnimation n$src$Lgg_vape_ui_click_animation_ColorAnimation_$1ogfbog() {
        return this.b;
    }

    public TextActionButton(String string, double d, boolean bl, double d2, double d3, Color color) {
        super(d2, d3, color);
        this.b = new ColorAnimation(0.15, TextActionButton.J.W, TextActionButton.J.f);
        this.zG = string;
        this.z_ = d;
        this.K = bl;
    }

    @Override
    public void n(boolean bl) {
        if (this.q != bl) {
            this.b.J();
        }
        super.n(bl);
    }
}

