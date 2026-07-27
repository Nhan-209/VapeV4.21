package gg.vape.ui.click.frame.impl.target;

import gg.vape.Vape;
import gg.vape.ui.click.frame.impl.target.TargetInfoStatStripComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import java.awt.Color;

public class TargetInfoCompactDistanceStatStripComponent
extends TargetInfoStatStripComponent {
    private int G = 0;

    public void X(int n) {
        this.G = n;
    }

    public TargetInfoCompactDistanceStatStripComponent() {
        super(20, 10);
    }


    @Override
    public void H() {
        String string;
        super.H();
        Color color = new Color(255, 255, 255, 180);
        String string2 = string = this.G >= 0 ? "+" + String.valueOf(this.G) : String.valueOf(this.G);
        if (this.G >= 9) {
            string = "9+";
        } else if (this.G <= -9) {
            string = "-9";
        }
        if (this.G > 0) {
            this.T(new Color(31, 124, 85));
        } else if (this.G < 0) {
            this.T(TargetInfoCompactDistanceStatStripComponent.J.d);
        } else {
            this.T(TargetInfoCompactDistanceStatStripComponent.J.r);
        }
        this.q(16.0);
        SmoothFontRenderer smoothFontRenderer = Vape.INSTANCE.getFontManager().K(0.9f, true);
        smoothFontRenderer.d(string, (double)((float)this.G$src$D$1b2f02a()) + this.A() - smoothFontRenderer.N(string) - 5.0, (float)this.n() + 1.0f, color);
    }
}

