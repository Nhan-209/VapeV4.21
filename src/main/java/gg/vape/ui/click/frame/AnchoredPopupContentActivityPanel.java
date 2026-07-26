package gg.vape.ui.click.frame;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;

class AnchoredPopupContentActivityPanel
extends PanelComponent {
    final GuiComponent jM;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public boolean t() {
        return super.t() || this.jM.t();
    }

    AnchoredPopupContentActivityPanel(double d, double d2, GuiComponent guiComponent) {
        super(d, d2);
        this.jM = guiComponent;
    }
}
