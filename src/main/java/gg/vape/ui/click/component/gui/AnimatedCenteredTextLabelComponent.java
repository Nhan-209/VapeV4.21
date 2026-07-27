package gg.vape.ui.click.component.gui;

import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.gui.TextLabel;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class AnimatedCenteredTextLabelComponent
extends TextLabel {
    private float lD;
    private ColorAnimation lI;
    private Color l4;

    @Override
    public double C() {
        return 0.0;
    }

    public AnimatedCenteredTextLabelComponent p(Color color) {
        this.l4 = color;
        return this;
    }

    public AnimatedCenteredTextLabelComponent(String string, Color color) {
        super(string);
        this.l4 = AnimatedCenteredTextLabelComponent.J.Z;
        this.lD = 1.0f;
        this.lI = new ColorAnimation(0.15, color, color.brighter());
    }

    @Override
    public void H() {
        GuiRenderPrimitives.P(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.lI.getInterpolatedColor(), 2.0f, this.lD, 1.0f);
        SmoothFontRenderer smoothFontRenderer = this.s$src$Z$8lhrly() ? this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(this.Yc) : this.O(this.Yc);
        double d = smoothFontRenderer.d(this.Q);
        double d2 = this.n() + this.L() / 2.0 - d / 2.0;
        smoothFontRenderer.W(this.Q, this.G$src$D$1b2f02a() + this.A() / 2.0, d2, this.G());
    }

    public GuiComponent R(Color color, Color color2) {
        this.lI = new ColorAnimation(0.15, color, color2);
        return super.T(color);
    }


    @Override
    public double x() {
        return 0.0;
    }

    public void y(float f) {
        this.lD = f;
    }

    @Override
    public Color G() {
        return this.l4;
    }

    @Override
    public void n(boolean bl) {
        if (this.w$src$Z$e457mb() != bl) {
            this.lI.J();
        }
        super.n(bl);
    }
}

