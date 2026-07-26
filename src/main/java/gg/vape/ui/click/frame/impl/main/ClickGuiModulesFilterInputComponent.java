package gg.vape.ui.click.frame.impl.main;

import gg.vape.ui.click.component.LabeledTextInputComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiModulesPage;

public class ClickGuiModulesFilterInputComponent
extends LabeledTextInputComponent {
    final ClickGuiModulesPage J7;

    public ClickGuiModulesFilterInputComponent(ClickGuiModulesPage clickGuiModulesPage, String string) {
        super(string);
        this.J7 = clickGuiModulesPage;
    }

    @Override
    public void k(String string) {
        super.k(string);
        ClickGuiModulesPage.m(this.J7, string);
        ClickGuiModulesPage.h(this.J7, ClickGuiModulesPage.d(this.J7), true);
    }
}
