package gg.vape.ui.click.component.value;

import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.value.ColorPaletteSliderComponent;
import gg.vape.ui.click.component.value.ColorValueDropdownHueSliderComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.unmap.ModeSelection;
import gg.vape.value.ColorValue;
import gg.vape.value.ModeValue;
import java.awt.Color;

public class ColorValueDropdownComponent
extends GuiComponent {
    private final ColorPaletteSliderComponent v;
    private final ModeValue I;
    private ModeSelection Q;
    private final ColorValue G;

    @Override
    public double C() {
        return 25.0;
    }

    @Override
    public void F() {
    }

    public ColorValueDropdownComponent(ModeValue modeValue) {
        this.I = modeValue;
        this.G = ColorValue.L(null, "Team Color", new Color(189, 0, 1));
        this.C(modeValue);
        Color[] colorArray = new Color[]{new Color(189, 0, 1), new Color(253, 63, 63), new Color(215, 162, 50), new Color(254, 254, 62), new Color(0, 191, 4), new Color(64, 253, 62), new Color(65, 255, 254), new Color(0, 190, 189), new Color(1, 1, 187), new Color(61, 64, 255), new Color(254, 63, 255), new Color(190, 0, 190), new Color(255, 255, 255), new Color(190, 190, 190), new Color(63, 63, 63), new Color(17, 17, 17)};
        this.v = new ColorValueDropdownHueSliderComponent(this, "Team color", this.G, colorArray);
        this.v.T(this.d());
        this.v.Y(true);
        this.v.M(null);
        this.H(this.v);
    }

    @Override
    public void I() {
    }

    @Override
    public void H() {
        this.onDisable();
        this.v.K(this.G$src$D$1b2f02a());
        this.v.S(this.n());
        SmoothFontRenderer smoothFontRenderer = this.O(0.7);
        String string = ((ModeSelection)this.I.K()).getName().substring(2);
        smoothFontRenderer.d(string, this.G$src$D$1b2f02a() + this.A() - 5.0 - smoothFontRenderer.N(string), this.n() + 5.0, ColorValueDropdownComponent.J.Z);
    }

    @Override
    public double x() {
        return 110.0;
    }

    private void e() {
        if (this.Q == null) {
            this.Q = (ModeSelection)this.I.K();
            return;
        }
        ModeSelection modeSelection = (ModeSelection)this.I.K();
        if (!this.Q.equals(modeSelection)) {
            this.v.Y(this.I.w$src$I$15qcf2k());
        }
    }

    @Override
    public void u() {
        this.I.f(true);
        this.v.Z$src$Lgg_vape_value_ColorValue_$1er4i1l().f(true);
        this.G.f(true);
        this.e();
        if (this.I.w$src$I$15qcf2k() != this.v.y$src$I$1f1aefk()) {
            this.I.M(this.v.y$src$I$1f1aefk());
        }
        this.Q = (ModeSelection)this.I.K();
        this.G.f(false);
        this.v.Z$src$Lgg_vape_value_ColorValue_$1er4i1l().f(false);
        this.I.f(false);
    }


    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }
}

