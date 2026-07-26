package gg.vape.ui.click.component;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseClickButton;
import gg.vape.ui.click.component.DropdownSelectComponent;
import gg.vape.ui.click.frame.PopupFrame;
import java.awt.Point;

public class DropdownPopupCloseClickHandler
implements GuiMouseListener {
    final DropdownSelectComponent s;

    @Override
    public void g(Point point, MouseClickButton mouseClickButton) {
        PopupFrame popupFrame = DropdownSelectComponent.e(this.s);
        if (popupFrame != null && !this.s.t() && !popupFrame.t()) {
            DropdownSelectComponent.w(this.s);
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public DropdownPopupCloseClickHandler(DropdownSelectComponent dropdownSelectComponent) {
        this.s = dropdownSelectComponent;
    }
}

