package gg.vape.ui.click.component;

import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class InsetFilledSpacerComponent
extends SpacerComponent {
    private final double O;
    private final double o;
    private final Color K;

    @Override
    public void void_H() {
        super.void_H();
        GuiRenderPrimitives.C(this.double_G() + this.o, this.double_n() + this.double_L() / 2.0 - this.O / 2.0, this.double_A() - this.o * 2.0, this.O, this.K);
    }

    public InsetFilledSpacerComponent(double d, double d2, double d3, double d4, Color color) {
        super(d, d2);
        this.O = d3;
        this.o = d4;
        this.K = color;
    }
}

