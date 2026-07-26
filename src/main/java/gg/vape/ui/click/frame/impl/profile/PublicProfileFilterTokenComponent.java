package gg.vape.ui.click.frame.impl.profile;

import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.WrappingTextLabelComponent;
import gg.vape.utils.render.GuiRenderPrimitives;

public class PublicProfileFilterTokenComponent
extends GuiComponent {
    private final WrappingTextLabelComponent b;

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Override
    public void F() {
    }

    public PublicProfileFilterTokenComponent(String string) {
        this.o(true);
        this.b = new WrappingTextLabelComponent(string, 0.7);
        this.b.T$src$V$1orl066(PublicProfileFilterTokenComponent.J.Z);
        this.H(this.b);
    }

    @Override
    public double x() {
        double d = this.b.h();
        this.getClass();
        return d + 5.0 + 4.0;
    }

    public String N() {
        return this.b.c$src$Ljava_lang_String_$1q00otb();
    }

    public void S(String string) {
        this.b.G(string);
    }

    @Override
    public void I() {
    }

    @Override
    public double C() {
        return 12.0;
    }

    @Override
    public void u() {
    }

    @Override
    public void H() {
        this.b.K(this.G$src$D$1b2f02a());
        this.b.S(this.n() + this.L() / 2.0 - this.b.L() / 2.0);
        this.b.o(this.A());
        this.b.Y(this.L());
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), PublicProfileFilterTokenComponent.J.z);
    }
}

