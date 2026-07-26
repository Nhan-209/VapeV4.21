package gg.vape.ui.click.component;

import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class FilledSpacerComponent
extends SpacerComponent {
    private Color O;
    private double i;
    private double K;

    public FilledSpacerComponent(double d, double d2, double d3, double d4, Color color) {
        super(d, d2);
        this.O = color;
        this.K = d3;
        this.i = d4;
    }

    public FilledSpacerComponent(double d, double d2, Color color) {
        super(d, d2);
        this.O = color;
        this.K = d;
        this.i = d2;
    }

    @Override
    public void c() {
        super.c();
        double d = this.G$src$D$1b2f02a() + (this.A() - this.K) / 2.0;
        double d2 = this.n() + (this.L() - this.i) / 2.0;
        GuiRenderPrimitives.C(d, d2, this.K, this.i, this.O);
    }
}

