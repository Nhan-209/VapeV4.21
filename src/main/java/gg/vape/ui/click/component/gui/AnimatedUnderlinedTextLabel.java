package gg.vape.ui.click.component.gui;

import gg.vape.ui.click.animation.DoubleAnimation;
import gg.vape.ui.click.component.gui.UnderlinedTextLabel;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.utils.render.OpenGlBackendHolder;
import java.awt.Color;

public class AnimatedUnderlinedTextLabel
extends UnderlinedTextLabel {
    private static final String cb = "expandarrow";
    private DoubleAnimation Z6 = new DoubleAnimation(0.15, 0.0, 1.0);
    private float Z5 = 4.0f;

    @Override
    public double C() {
        SmoothFontRenderer smoothFontRenderer = this.O(this.Yc);
        return smoothFontRenderer.d(this.Q);
    }

    public AnimatedUnderlinedTextLabel(String string, double d, Color color, Color color2) {
        super(string, d, color, color2);
    }


    @Override
    public void H() {
        Color color;
        SmoothFontRenderer smoothFontRenderer = this.O(this.Yc);
        double d = smoothFontRenderer.d(this.Q);
        double d2 = this.A();
        double d3 = this.G$src$D$1b2f02a();
        double d4 = this.n() + this.L() / 2.0 - d / 2.0;
        Color color2 = this.w$src$Z$e457mb() ? this.G().brighter() : this.G();
        Color color3 = color = this.w$src$Z$e457mb() ? this.R$src$Ljava_awt_Color_$bufo6d().brighter() : this.R$src$Ljava_awt_Color_$bufo6d();
        if (this.w$src$Z$e457mb()) {
            if (!this.Z6.I$src$Z$c48gtw()) {
                this.Z6.c();
            }
        } else if (this.Z6.I$src$Z$c48gtw()) {
            this.Z6.Z();
        }
        smoothFontRenderer.d(this.Q, d3, d4, color2);
        OpenGlBackendHolder.d.m();
        float f = 2.0f;
        OpenGlBackendHolder.d.H(0.5f, 0.5f, 0.5f);
        int n = 0;
        while ((double)n < d2) {
            GuiRenderPrimitives.V((int)((this.G$src$D$1b2f02a() + (double)n + 1.0 * this.Yc) * (double)f), (int)((d4 + d + 2.0 * this.Yc) * (double)f), 1.0 * this.Yc, 1.0 * this.Yc, color);
            ++n;
        }
        OpenGlBackendHolder.d.F();
        ImageRenderer.E(color2, (float)this.G$src$D$1b2f02a() + (float)this.A() - 5.0f + this.Z6.getInterpolatedValue().floatValue(), (float)d4 + 2.0f, cb, this.Z5, this.Z5, false);
    }

    @Override
    public double x() {
        return super.x();
    }
}

