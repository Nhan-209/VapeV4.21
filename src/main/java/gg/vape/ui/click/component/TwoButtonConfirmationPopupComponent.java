package gg.vape.ui.click.component;

import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class TwoButtonConfirmationPopupComponent
extends GuiComponent {
    private Color I;
    private static final String v = "CANCEL";
    private boolean b;
    private final TextButton a;
    private final TextButton i;
    private final SimpleTextLabelComponent K;

    public TwoButtonConfirmationPopupComponent(String string, String string2) {
        this.I = TwoButtonConfirmationPopupComponent.J.y;
        this.b = false;
        this.K = new SimpleTextLabelComponent(string, 0.8f, TwoButtonConfirmationPopupComponent.J.A, false);
        this.K.o(this.K.h() * (double)0.76f);
        this.i = new TextButton(string2, 0.65, TwoButtonConfirmationPopupComponent.J.d, TwoButtonConfirmationPopupComponent.J.c, 36.0, 14.0);
        this.a = new TextButton(v, 0.65, TwoButtonConfirmationPopupComponent.J.d, TwoButtonConfirmationPopupComponent.J.c, 36.0, 14.0);
        this.d(false);
        this.T(TwoButtonConfirmationPopupComponent.J.m.brighter());
        this.i.F(false);
        this.i.c(true);
        this.i.o(20.0);
        this.i.Y(9.0);
        this.i.m(1.0f);
        this.i.h(Color.WHITE);
        this.i.G(Color.WHITE);
        this.a.c(false);
        this.a.p(true);
        this.a.F(false);
        this.a.o(28.0);
        this.a.Y(9.0);
        this.a.u(0.8f);
        this.a.h(TwoButtonConfirmationPopupComponent.J.Z);
        this.a.G(TwoButtonConfirmationPopupComponent.J.A);
        this.a.R(new Color(255, 255, 255, 20));
        this.a.G(TwoButtonConfirmationPopupComponent.J.t, TwoButtonConfirmationPopupComponent.J.t);
        this.H(this.K, this.i, this.a);
    }

    public TextButton N() {
        return this.i;
    }


    @Override
    public double C() {
        return 25.0;
    }

    public boolean q$src$Z$19k2i8l() {
        return this.b;
    }

    public Color b$src$Ljava_awt_Color_$14aus4s() {
        return this.I;
    }

    public void z(Color color) {
        this.I = color;
    }

    public void O(boolean bl) {
        this.b = bl;
    }

    @Override
    public double x() {
        return this.K.A() + 20.0 + this.i.A() + this.a.A() + 8.0;
    }

    public TextButton O$src$Lgg_vape_ui_click_component_gui_TextButton_$1fvjbh() {
        return this.a;
    }

    @Override
    public void H() {
        double d = this.L();
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n(), this.A(), d, this.d());
        if (this.b) {
            GuiRenderPrimitives.P(this.G$src$D$1b2f02a(), this.n(), this.A(), d, this.I, 2.0f, 1.0f, 1.0f);
        }
        this.K.K(this.G$src$D$1b2f02a() + 2.0);
        this.K.S(this.n() + this.L() / 2.0 - this.K.y$src$D$idacv3() / 2.0 - 2.0);
        this.i.K(this.G$src$D$1b2f02a() + this.A() - (this.i.A() + 5.0));
        this.i.S(this.n() + this.L() / 2.0 - this.i.L() / 2.0);
        this.a.K(this.i.G$src$D$1b2f02a() - (this.a.A() + 4.0));
        this.a.S(this.i.n());
    }
}

