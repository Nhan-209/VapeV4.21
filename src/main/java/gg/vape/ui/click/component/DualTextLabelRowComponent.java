package gg.vape.ui.click.component;

import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.WrappingTextLabelComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class DualTextLabelRowComponent
extends PanelComponent {
    private final SimpleTextLabelComponent wu;
    private final WrappingTextLabelComponent wr;

    @Override
    public void c() {
        GuiRenderPrimitives.d(this.wr.G$src$D$1b2f02a() - 2.0, this.wr.n() + 1.0, this.wr.A() + 4.0, this.wr.g$src$D$i3e26l() * 10.0, DualTextLabelRowComponent.J.z);
        super.c();
    }

    public DualTextLabelRowComponent(String string, String string2, double d, double d2) {
        super(40.0, d);
        this.d(false);
        this.T(Color.RED);
        this.wu = new SimpleTextLabelComponent(string, d2, DualTextLabelRowComponent.J.A);
        this.wu.o(this.wu.h() + 8.0);
        this.wu.l(true);
        this.wu.g(0.0f);
        this.wr = new WrappingTextLabelComponent(string2, d2 * 0.9, DualTextLabelRowComponent.J.Z);
        this.wr.o(this.wr.h() + 4.0);
        this.q(this.wu.A() + this.wr.A() + 2.0);
        this.H(this.wu, this.wr);
    }

    @Override
    public double x() {
        return this.wr.A();
    }

    @Override
    public void H() {
    }

    @Override
    public double C() {
        return 6.0;
    }

    public void J(double d) {
        this.wu.i(d);
        this.wr.i(d);
    }
}

