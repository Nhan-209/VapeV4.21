package gg.vape.ui.click.frame.impl.target;

import gg.vape.Vape;
import gg.vape.ui.click.frame.impl.target.TargetInfoStatStripComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import java.awt.Color;

public class TargetInfoDistanceStatStripComponent
extends TargetInfoStatStripComponent {
    private int v = 0;

    public TargetInfoDistanceStatStripComponent() {
        super(16, 10);
    }

    public void c(int n) {
        this.v = n;
    }


    @Override
    public void H() {
        String string;
        super.H();
        Color color = new Color(255, 255, 255, 180);
        String string2 = string = this.v >= 0 ? "+" + String.valueOf(this.v) : String.valueOf(this.v);
        if (this.v >= 9) {
            string = "9+";
        } else if (this.v <= -9) {
            string = "-9";
        }
        if (this.v > 0) {
            this.T(new Color(31, 124, 85));
        } else if (this.v < 0) {
            this.T(TargetInfoDistanceStatStripComponent.J.d);
        } else {
            this.T(TargetInfoDistanceStatStripComponent.J.r);
        }
        Color color2 = this.O != null ? this.O.l(color) : color;
        SmoothFontRenderer smoothFontRenderer = Vape.INSTANCE.getFontManager().K(0.9f, true);
        smoothFontRenderer.d(string, (double)((float)this.G$src$D$1b2f02a()) + this.A() - smoothFontRenderer.N(string) - 5.0, (float)this.n() + 1.0f, color2);
    }
}

