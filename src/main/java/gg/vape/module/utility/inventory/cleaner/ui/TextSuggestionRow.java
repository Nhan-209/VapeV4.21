package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.WrappingTextLabelComponent;
import gg.vape.utils.render.GuiRenderPrimitives;

public class TextSuggestionRow
extends GuiComponent {
    private final WrappingTextLabelComponent O;

    public TextSuggestionRow(String string) {
        this.o(true);
        this.O = new WrappingTextLabelComponent(string, 0.7);
        this.O.T$src$V$1orl066(TextSuggestionRow.J.Z);
        this.H(this.O);
    }

    @Override
    public void I() {
    }

    public void M(String string) {
        this.O.G(string);
    }

    @Override
    public void H() {
        this.O.K(this.G$src$D$1b2f02a());
        this.O.S(this.n() + this.L() / 2.0 - this.O.L() / 2.0);
        this.O.o(this.A());
        this.O.Y(this.L());
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), TextSuggestionRow.J.z);
    }

    @Override
    public void F() {
    }

    @Override
    public double x() {
        double d = this.O.h();
        this.getClass();
        return d + 5.0 + 4.0;
    }

    public String x$src$Ljava_lang_String_$1m64ofa() {
        return this.O.c$src$Ljava_lang_String_$1q00otb();
    }

    @Override
    public double C() {
        return 12.0;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Override
    public void u() {
    }
}

