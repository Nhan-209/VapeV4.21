package gg.vape.ui.click.component;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.ToolTips;
import gg.vape.ui.font.SmoothFontRenderer;
import java.awt.Color;

class TooltipTextSegment {
    public boolean P;
    public String T;
    public double k;
    public Color A;
    final ToolTips E;

    public double Y() {
        SmoothFontRenderer smoothFontRenderer;
        SmoothFontRenderer smoothFontRenderer2 = smoothFontRenderer = this.P ? this.E.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(this.k) : this.E.O(this.k);
        return smoothFontRenderer2.d(this.T);
    }

    public TooltipTextSegment(ToolTips toolTips, String string, double d, Color color, boolean bl) {
        this.E = toolTips;
        this.T = string;
        this.k = d;
        this.A = color;
        this.P = bl;
    }

    public double B() {
        SmoothFontRenderer smoothFontRenderer;
        SmoothFontRenderer smoothFontRenderer2 = smoothFontRenderer = this.P ? this.E.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(this.k) : this.E.O(this.k);
        return smoothFontRenderer2.N(this.T);
    }

    public void o(double d, double d2) {
        SmoothFontRenderer smoothFontRenderer;
        SmoothFontRenderer smoothFontRenderer2 = smoothFontRenderer = this.P ? this.E.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(this.k) : this.E.O(this.k);
        smoothFontRenderer2.v(this.T, d, d2, this.A);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

