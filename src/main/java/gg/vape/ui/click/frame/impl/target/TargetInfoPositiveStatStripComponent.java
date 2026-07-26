package gg.vape.ui.click.frame.impl.target;

import gg.vape.Vape;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.frame.impl.target.TargetInfoStatStripComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.ImageRenderer;
import java.awt.Color;

public class TargetInfoPositiveStatStripComponent
extends TargetInfoStatStripComponent {
    private int Q = 0;

    @Override
    public void H() {
        String string;
        super.H();
        Color color = this.Q > 0 ? TargetInfoPositiveStatStripComponent.J.B : (this.Q < 0 ? TargetInfoPositiveStatStripComponent.J.d : TargetInfoPositiveStatStripComponent.J.A);
        String string2 = string = this.Q >= 0 ? "+" + String.valueOf(this.Q) : String.valueOf(this.Q);
        if (this.Q >= 9) {
            string = "9+";
        }
        Color color2 = this.O != null ? this.O.l(color) : color;
        ImageRenderer.E(color2, (float)this.G$src$D$1b2f02a() + 5.0f, (float)this.n() + 2.0f, "sword_header", 6.0f, 6.0f, false);
        SmoothFontRenderer smoothFontRenderer = Vape.INSTANCE.getFontManager().K(0.9f, true);
        smoothFontRenderer.d(string, (double)((float)this.G$src$D$1b2f02a()) + this.A() - smoothFontRenderer.N(string) - 5.0, (float)this.n() + 1.0f, color2);
    }

    public void w$src$V$vtqvn7() {
        --this.Q;
    }

    public void p(int n) {
        this.Q = n;
    }

    public TargetInfoPositiveStatStripComponent() {
        super(24, 10);
    }

    public void Q$src$V$v8up3h() {
        ++this.Q;
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

