package gg.vape.ui.click.component.gui;

import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.utils.MutableColor;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class ActionButtonBase
extends InteractiveComponent {
    protected Color I;
    protected double Q;
    protected ColorAnimation v;

    @Override
    public void F() {
        if (!this.w$src$Z$e457mb()) {
            this.v.J();
        }
        super.F();
    }

    @Override
    public double x() {
        return 0.0;
    }

    @Override
    public void I() {
    }

    public void Q(Color color) {
        this.I = color;
    }

    public Color P$src$Ljava_awt_Color_$va33hp() {
        return this.I;
    }

    @Override
    public void H() {
        GuiRenderPrimitives.P(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.v.getInterpolatedColor(), 2.0f, (float)this.Q, 1.0f);
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), (Color)new MutableColor(this.I).withAlpha((int)(255.0f * this.v.s())));
    }

    public ColorAnimation l$src$Lgg_vape_ui_click_animation_ColorAnimation_$1s4yq9u() {
        return this.v;
    }

    @Override
    public double C() {
        return 0.0;
    }

    public ActionButtonBase(double d, double d2, Color color, double d3) {
        this.o(d);
        this.Y(d2);
        this.I = color;
        this.v = new ColorAnimation(0.15, new Color(45, 45, 45), color);
        this.Q = d3;
    }

    @Override
    public void onEnable() {
        this.v.J();
        super.onEnable();
    }


    public void e(ColorAnimation colorAnimation) {
        this.v = colorAnimation;
    }

    public ActionButtonBase(double d, double d2, Color color) {
        this(d, d2, color, 1.0);
    }

    public /* synthetic */ void void_H() {
        this.H();
    }
}

