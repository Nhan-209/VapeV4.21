package gg.vape.ui.click.frame.impl.main;

import gg.vape.ui.click.component.LabeledTextInputComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiMacrosController;

public class ClickGuiMacrosLabelInput
extends LabeledTextInputComponent {
    final ClickGuiMacrosController Z8;

    @Override
    public void k(String string) {
        super.k(string);
        ClickGuiMacrosController.b(this.Z8, string);
        this.Z8.S();
    }

    public ClickGuiMacrosLabelInput(ClickGuiMacrosController clickGuiMacrosController, String string) {
        super(string);
        this.Z8 = clickGuiMacrosController;
    }
}
