package gg.vape.ui.click.component;

import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.WrappingTextLabelComponent;
import gg.vape.utils.render.GuiRenderPrimitives;

public class TwoLineTextDisplayComponent
extends GuiComponent {
    private final WrappingTextLabelComponent G;
    private final WrappingTextLabelComponent a;

    public TwoLineTextDisplayComponent(String string, String string2) {
        this.T(TwoLineTextDisplayComponent.J.E);
        this.a = new WrappingTextLabelComponent(string2, 0.9);
        this.a.l(true);
        this.a.T$src$V$1orl066(TwoLineTextDisplayComponent.J.A);
        this.a.Y(4.0);
        this.a.W(true);
        this.H(this.a);
        this.G = new WrappingTextLabelComponent(string, 0.65);
        this.G.l(true);
        this.G.T$src$V$1orl066(TwoLineTextDisplayComponent.J.C);
        this.G.Y(4.0);
        this.G.W(true);
        this.H(this.G);
    }

    public void E(double d) {
        this.G.i(d);
    }

    @Override
    public void u() {
    }

    @Override
    public void I() {
    }

    @Override
    public double x() {
        return 0.0;
    }

    @Override
    public double C() {
        return 0.0;
    }

    @Override
    public void H() {
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.d());
        double d = this.L() / 2.0;
        this.G.S(this.n() + d + this.G.y$src$D$idacv3() / 2.0);
        this.a.S(this.n() + d - 7.0);
        for (GuiComponent guiComponent : this.f()) {
            guiComponent.K(this.G$src$D$1b2f02a());
            guiComponent.o(this.A());
            guiComponent.H();
        }
        double d2 = this.G.A();
        this.getClass();
        this.G.A(d2 - 5.0);
        double d3 = this.a.A();
        this.getClass();
        this.a.A(d3 - 5.0);
    }

    public void H(double d) {
        this.a.i(d);
    }

    @Override
    public void F() {
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }
}

