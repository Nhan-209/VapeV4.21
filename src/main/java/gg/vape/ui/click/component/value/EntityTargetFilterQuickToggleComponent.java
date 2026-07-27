package gg.vape.ui.click.component.value;

import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.AnimatedIconButtonComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.value.BooleanValue;
import gg.vape.value.EntityTargetFilterValue;

public class EntityTargetFilterQuickToggleComponent
extends GuiComponent {
    private AnimatedIconButtonComponent R = new AnimatedIconButtonComponent("newplayers");
    private EntityTargetFilterValue I;
    private AnimatedIconButtonComponent a;
    private AnimatedIconButtonComponent O = new AnimatedIconButtonComponent("newmobs");

    @Override
    public double x() {
        return 110.0;
    }

    @Override
    public void I() {
    }

    @Override
    public void F() {
    }

    @Override
    public double C() {
        return 20.0;
    }


    @Override
    public void H() {
        double d = (this.A() - 10.0 - 6.0) / 3.0;
        double d2 = this.G$src$D$1b2f02a() + 5.0;
        this.V(this.R, this.I.D(), d, d2);
        this.V(this.O, this.I.f(), d, d2 += d + 2.0);
        this.V(this.a, this.I.r$src$Lgg_vape_value_BooleanValue_$167auuf(), d, d2 += d + 2.0);
    }

    @Override
    public void u() {
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    private void V(AnimatedIconButtonComponent animatedIconButtonComponent, BooleanValue booleanValue, double d, double d2) {
        animatedIconButtonComponent.K(d2);
        animatedIconButtonComponent.S(this.n() + 2.5);
        animatedIconButtonComponent.o(d);
        animatedIconButtonComponent.Y(this.L() - 5.0);
        animatedIconButtonComponent.L(booleanValue.L());
        animatedIconButtonComponent.G(booleanValue.L() != false || animatedIconButtonComponent.w$src$Z$e457mb() ? J.B() : null);
        if (!animatedIconButtonComponent.d$src$Lgg_vape_ui_click_animation_ColorAnimation_$10kme50().N() && !animatedIconButtonComponent.d$src$Lgg_vape_ui_click_animation_ColorAnimation_$10kme50().l() && booleanValue.L().booleanValue()) {
            animatedIconButtonComponent.d$src$Lgg_vape_ui_click_animation_ColorAnimation_$10kme50().J();
        }
    }

    public EntityTargetFilterQuickToggleComponent(EntityTargetFilterValue entityTargetFilterValue) {
        this.a = new AnimatedIconButtonComponent("newpeaceful");
        this.I = entityTargetFilterValue;
        this.R.r(() -> entityTargetFilterValue.D().Z$src$Z$15e9hxx());
        this.R.h(true);
        this.R.w("Target players");
        this.O.r(() -> entityTargetFilterValue.f().Z$src$Z$15e9hxx());
        this.O.h(true);
        this.O.w("Target mobs");
        this.a.r(() -> entityTargetFilterValue.r$src$Lgg_vape_value_BooleanValue_$167auuf().Z$src$Z$15e9hxx());
        this.a.h(true);
        this.a.w("Target peaceful");
        this.H(this.R, this.O, this.a);
    }

}
