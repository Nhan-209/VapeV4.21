package gg.vape.ui.click.component.value;

import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.value.ColorChannelSliderComponent;
import gg.vape.ui.click.component.value.ColorPaletteSliderComponent;

public class ColorPaletteRefreshClickListener
implements GuiClickListener {
    final ColorChannelSliderComponent m;
    final ColorPaletteSliderComponent C;

    @Override
    public void P() {
        this.C.k();
    }

    public ColorPaletteRefreshClickListener(ColorChannelSliderComponent r7_02, ColorPaletteSliderComponent r3_02) {
        this.m = r7_02;
        this.C = r3_02;
    }
}

