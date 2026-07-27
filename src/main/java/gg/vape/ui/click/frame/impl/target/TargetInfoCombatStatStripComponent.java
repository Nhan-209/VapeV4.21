package gg.vape.ui.click.frame.impl.target;

import gg.vape.Vape;
import gg.vape.ui.click.frame.impl.target.TargetInfoStatStripComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.ImageRenderer;
import java.awt.Color;

public class TargetInfoCombatStatStripComponent
extends TargetInfoStatStripComponent {
    private int a = 0;

    @Override
    public void H() {
        String string;
        super.H();
        Color color = this.a > 0 ? TargetInfoCombatStatStripComponent.J.B : (this.a < 0 ? TargetInfoCombatStatStripComponent.J.d : TargetInfoCombatStatStripComponent.J.A);
        String string2 = string = this.a >= 0 ? "+" + String.valueOf(this.a) : String.valueOf(this.a);
        if (this.a >= 9) {
            string = "9+";
        }
        Color color2 = this.O != null ? this.O.l(color) : color;
        ImageRenderer.E(color2, (float)this.G$src$D$1b2f02a() + 4.0f, (float)this.n() + 2.0f, "pot_normal", 6.0f, 6.0f, false);
        SmoothFontRenderer smoothFontRenderer = Vape.INSTANCE.getFontManager().K(0.9f, true);
        smoothFontRenderer.d(string, (double)((float)this.G$src$D$1b2f02a()) + this.A() - smoothFontRenderer.N(string) - 5.0, (float)this.n() + 1.0f, color2);
    }

    public void l$src$V$1wu6ffn() {
        ++this.a;
    }


    public void V$src$V$1wi2ydp() {
        --this.a;
    }

    public TargetInfoCombatStatStripComponent() {
        super(24, 10);
    }

    public void i(int n) {
        this.a = n;
    }
}

