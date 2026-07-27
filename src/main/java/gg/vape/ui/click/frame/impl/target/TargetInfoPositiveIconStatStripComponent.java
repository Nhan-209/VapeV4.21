package gg.vape.ui.click.frame.impl.target;

import gg.vape.Vape;
import gg.vape.ui.click.frame.impl.target.TargetInfoStatStripComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.ImageRenderer;
import java.awt.Color;

public class TargetInfoPositiveIconStatStripComponent
extends TargetInfoStatStripComponent {
    private int v = 0;


    public TargetInfoPositiveIconStatStripComponent() {
        super(24, 10);
    }

    public void n(int n) {
        this.v = n;
    }

    @Override
    public void H() {
        super.H();
        Color color = this.v > 0 ? TargetInfoPositiveIconStatStripComponent.J.B : (this.v < 0 ? TargetInfoPositiveIconStatStripComponent.J.d : TargetInfoPositiveIconStatStripComponent.J.A);
        String string = this.v >= 0 ? "+" + String.valueOf(this.v) : String.valueOf(this.v);
        boolean bl = false;
        if (this.v >= 9) {
            string = "9+";
            bl = true;
        }
        ImageRenderer.E(color, (float)this.G$src$D$1b2f02a() + 4.0f, (float)this.n() + 2.0f, "armor_header", 6.0f, 6.0f, false);
        SmoothFontRenderer smoothFontRenderer = Vape.INSTANCE.getFontManager().K(0.9f, true);
        smoothFontRenderer.d(string, (double)((float)this.G$src$D$1b2f02a()) + this.A() - smoothFontRenderer.N(string) - 5.0 + (bl ? 1.0 : 0.0), (float)this.n() + 1.0f, color);
    }
}
