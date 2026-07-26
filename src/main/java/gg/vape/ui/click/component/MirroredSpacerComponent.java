package gg.vape.ui.click.component;

import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.SpacerComponent;

public class MirroredSpacerComponent
extends SpacerComponent {
    private final GuiComponent K;
    private final SpacerComponent O;

    @Override
    public void H() {
        super.H();
        this.O.K(this.G$src$D$1b2f02a());
        this.O.S(this.n());
        this.O.u(-1.0);
        this.O.Y(this.L());
        this.O.c();
    }

    @Override
    public double C() {
        return this.K.L();
    }

    public MirroredSpacerComponent(GuiComponent guiComponent, double d, SpacerComponent spacerComponent) {
        super(d, 0.0);
        this.K = guiComponent;
        this.O = spacerComponent;
    }

    @Override
    public double L() {
        return this.K.L();
    }

    public MirroredSpacerComponent(GuiComponent guiComponent, SpacerComponent spacerComponent) {
        this(guiComponent, 0.0, spacerComponent);
    }
}

