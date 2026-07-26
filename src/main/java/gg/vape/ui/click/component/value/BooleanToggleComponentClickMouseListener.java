package gg.vape.ui.click.component.value;

import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseClickButton;
import gg.vape.ui.click.component.value.BooleanToggleComponent;
import java.awt.Point;

class BooleanToggleComponentClickMouseListener
implements GuiMouseListener {
    final BooleanToggleComponent r;

    BooleanToggleComponentClickMouseListener(BooleanToggleComponent booleanToggleComponent) {
        this.r = booleanToggleComponent;
    }

    @Override
    public void g(Point point, MouseClickButton mouseClickButton) {
        this.r.N();
    }
}

