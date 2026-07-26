package gg.vape.ui.click.component.value;

import gg.vape.ui.click.component.value.ColorPaletteSliderComponent;
import gg.vape.ui.click.component.value.ColorValueDropdownComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.value.ColorValue;
import java.awt.Color;

public class ColorValueDropdownHueSliderComponent
extends ColorPaletteSliderComponent {
    final ColorValueDropdownComponent kO;
    private static final String gb = "teamdot";

    @Override
    protected void C$src$V$1okgo1v() {
        GuiRenderPrimitives.F(gb, this.G$src$D$1b2f02a() + (double)this.I$src$Lgg_vape_ui_click_animation_DoubleAnimation_$f0r39g().getInterpolatedValue().floatValue(), this.v.W() + this.v.R() / 2.0, this.v.e(), this.v.R(), this.l$src$Lgg_vape_ui_click_animation_ColorAnimation_$lcq3bn().getInterpolatedColor());
    }

    @Override
    public boolean D() {
        return false;
    }

    public ColorValueDropdownHueSliderComponent(ColorValueDropdownComponent colorValueDropdownComponent, String string, ColorValue colorValue, Color[] colorArray) {
        super(string, colorValue, colorArray);
        this.kO = colorValueDropdownComponent;
    }
}
