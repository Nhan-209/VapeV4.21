package gg.vape.ui.click.frame.impl.target;

import gg.vape.Vape;
import gg.vape.ui.click.frame.impl.target.TargetInfoStatStripComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.ImageRenderer;
import java.awt.Color;

public class TargetInfoResettingCombatStatStripComponent
extends TargetInfoStatStripComponent {
    private static final String b = "combo_display";
    private int Q = 0;

    public TargetInfoResettingCombatStatStripComponent() {
        super(24, 10);
    }

    public void o$src$V$fgvspr() {
        this.Q = this.Q <= 0 ? --this.Q : 0;
    }


    @Override
    public void H() {
        super.H();
        Color color = this.Q > 0 ? TargetInfoResettingCombatStatStripComponent.J.B : (this.Q < 0 ? TargetInfoResettingCombatStatStripComponent.J.d : TargetInfoResettingCombatStatStripComponent.J.A);
        String string = String.valueOf(Math.abs(this.Q));
        Color color2 = this.O != null ? this.O.l(color) : color;
        ImageRenderer.E(color2, (float)this.G$src$D$1b2f02a() + 4.0f, (float)this.n() + 2.0f, b, 6.0f, 6.0f, false);
        SmoothFontRenderer smoothFontRenderer = Vape.INSTANCE.getFontManager().K(0.9f, true);
        smoothFontRenderer.d(string, (double)((float)this.G$src$D$1b2f02a()) + this.A() - smoothFontRenderer.N(string) - 5.0, (float)this.n() + 1.0f, color2);
    }

    public void R() {
        this.Q = this.Q >= 0 ? ++this.Q : 0;
    }

    public void c(int n) {
        this.Q = n;
    }
}

