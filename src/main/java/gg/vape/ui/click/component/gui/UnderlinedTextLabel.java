package gg.vape.ui.click.component.gui;

import gg.vape.ui.click.component.gui.TextLabel;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class UnderlinedTextLabel
extends TextLabel {
    private Color Ax;

    public UnderlinedTextLabel(String string, double d, Color color, Color color2) {
        super(string, d);
        this.Ax = UnderlinedTextLabel.J.Z;
        this.l(color);
        this.Ax = color2;
    }

    @Override
    public double C() {
        SmoothFontRenderer smoothFontRenderer = this.O(this.Yc);
        return smoothFontRenderer.d(this.Q);
    }


    @Override
    public void H() {
        SmoothFontRenderer smoothFontRenderer = this.O(this.Yc);
        double d = smoothFontRenderer.d(this.Q);
        double d2 = smoothFontRenderer.N(this.Q) + 1.0;
        double d3 = this.G$src$D$1b2f02a() + this.A() / 2.0;
        double d4 = this.n() + this.L() / 2.0 - d / 2.0;
        Color color = this.w$src$Z$e457mb() ? this.G().brighter() : this.G();
        smoothFontRenderer.W(this.Q, d3, d4, color);
        double d5 = d3 - d2 / 2.0 + 1.0;
        GuiRenderPrimitives.z(d5, d4 + d, d5 + d2, d4 + d, 1.0, 1.5, color);
    }

    @Override
    public double x() {
        SmoothFontRenderer smoothFontRenderer = this.O(this.Yc);
        return smoothFontRenderer.N(this.Q);
    }

    public UnderlinedTextLabel(String string, double d) {
        super(string, d);
        this.Ax = UnderlinedTextLabel.J.Z;
    }

    public Color R$src$Ljava_awt_Color_$bufo6d() {
        return this.Ax;
    }

    public UnderlinedTextLabel(String string, double d, Color color) {
        super(string, d);
        this.Ax = UnderlinedTextLabel.J.Z;
        this.l(color);
        this.Ax = color;
    }

    public UnderlinedTextLabel(String string) {
        super(string);
        this.Ax = UnderlinedTextLabel.J.Z;
    }
}

