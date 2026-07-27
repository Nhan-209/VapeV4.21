package gg.vape.ui.click.component.gui;

import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseClickButton;
import gg.vape.ui.click.component.PopupMenuButtonComponent;
import java.awt.Point;

public class InteractivePopupOutsideCloseMouseListener
implements GuiMouseListener {
    final PopupMenuButtonComponent e;

    @Override
    public void g(Point point, MouseClickButton mouseClickButton) {
        if (!this.e.t() && !PopupMenuButtonComponent.g(this.e).t()) {
            PopupMenuButtonComponent.l(this.e);
        }
    }


    public InteractivePopupOutsideCloseMouseListener(PopupMenuButtonComponent popupMenuButtonComponent) {
        this.e = popupMenuButtonComponent;
    }
}

