package gg.vape.ui.click.component;

import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseClickButton;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.IconTextActionRowComponent;
import java.awt.Point;

class IconTextActionRowForwardClickMouseListener
implements GuiMouseListener {
    final IconTextActionRowComponent q;
    final GuiClickListener R;

    @Override
    public void g(Point point, MouseClickButton mouseClickButton) {
        this.R.P();
    }

    IconTextActionRowForwardClickMouseListener(IconTextActionRowComponent iconTextActionRowComponent, GuiClickListener guiClickListener) {
        this.q = iconTextActionRowComponent;
        this.R = guiClickListener;
    }
}

