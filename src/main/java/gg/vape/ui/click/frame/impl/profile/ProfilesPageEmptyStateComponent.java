package gg.vape.ui.click.frame.impl.profile;

import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.input.BindableInputComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiProfilesPage;

public class ProfilesPageEmptyStateComponent
extends GuiComponent {
    final BindableInputComponent v;
    final ClickGuiProfilesPage R;

    @Override
    public void H() {
        double d = this.G$src$D$1b2f02a();
        double d2 = this.n();
        double d3 = this.A();
        double d4 = this.v.A();
        double d5 = d + d3 - d4;
        double d6 = d2 + (this.L() - this.v.L()) / 2.0;
        this.v.K(d5);
        this.v.S(d6);
        double d7 = ClickGuiProfilesPage.V(this.R).L();
        double d8 = d2 + (this.L() - d7) / 2.0;
        double d9 = d3 - d4 - 6.0;
        ClickGuiProfilesPage.V(this.R).o(d9);
        ClickGuiProfilesPage.V(this.R).K(d);
        ClickGuiProfilesPage.V(this.R).S(d8);
        super.H();
    }

    @Override
    public void I() {
    }

    public ProfilesPageEmptyStateComponent(ClickGuiProfilesPage clickGuiProfilesPage, BindableInputComponent bindableInputComponent) {
        this.R = clickGuiProfilesPage;
        this.v = bindableInputComponent;
    }

    @Override
    public double C() {
        return 0.0;
    }

    @Override
    public double x() {
        return 0.0;
    }

    @Override
    public void u() {
    }
}

