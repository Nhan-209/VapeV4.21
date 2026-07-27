package gg.vape.ui.click.component;

import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.IconGlyphComponent;
import gg.vape.ui.click.component.IconTextActionRowForwardClickMouseListener;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class IconTextActionRowComponent
extends InteractiveComponent {
    private final IconGlyphComponent b = new IconGlyphComponent("create profile from", 6.0f, 6.0f, Color.WHITE);
    private final TruncatedTextComponent K;

    public void F(String string) {
        this.K.O(string);
        this.K.G(string);
    }

    public void R(double d) {
        this.K.M(d);
    }

    @Override
    public void u() {
        this.K.u();
    }

    @Override
    public void H() {
        if (this.w$src$Z$e457mb()) {
            GuiRenderPrimitives.C(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), IconTextActionRowComponent.J.z);
        }
        double d = 12.0;
        this.b.K(this.G$src$D$1b2f02a() + d);
        this.b.S(this.n() + this.L() / 2.0 - this.b.L() / 2.0);
        this.b.H();
        this.K.K(this.b.G$src$D$1b2f02a() + 10.0);
        this.K.S(this.n() + 0.5);
        this.K.o(this.A() - this.b.A() - 4.0);
        this.K.Y(this.L());
        this.K.D(this.A() - this.b.A() - 4.0);
        this.K.H();
        this.b.o(6.0);
        this.b.Y(6.0);
    }

    @Override
    public double x() {
        return 0.0;
    }

    @Override
    public void I() {
    }

    @Override
    public InteractiveComponent r(GuiClickListener guiClickListener) {
        this.K.j(new IconTextActionRowForwardClickMouseListener(this, guiClickListener));
        return super.r(guiClickListener);
    }


    public IconTextActionRowComponent(String string) {
        this.K = new TruncatedTextComponent(string, "...", string, 50.0, 0.8, Color.WHITE, false, false);
        this.K.G(string);
        this.Y(18.0);
    }

    @Override
    public double C() {
        return 0.0;
    }

    public String K$src$Ljava_lang_String_$16e2ilc() {
        return this.K.S$src$Ljava_lang_String_$1bp7ddx();
    }

    @Override
    public void F() {
        this.K.F();
    }

    public double O$src$D$1kfhmr0() {
        return this.K.b$src$D$lbm1ki();
    }
}

