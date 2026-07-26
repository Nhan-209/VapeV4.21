package gg.vape.ui.click.frame.impl.main;

import gg.vape.ui.click.component.LabeledTextInputComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiModulesPage;

public class ClickGuiModulesSearchInputComponent
extends LabeledTextInputComponent {
    final ClickGuiModulesPage tO;

    @Override
    public void k(String string) {
        super.k(string);
        ClickGuiModulesPage.k(this.tO, string);
        ClickGuiModulesPage.h(this.tO, ClickGuiModulesPage.x(this.tO), false);
    }

    public ClickGuiModulesSearchInputComponent(ClickGuiModulesPage clickGuiModulesPage, String string) {
        super(string);
        this.tO = clickGuiModulesPage;
    }
}
