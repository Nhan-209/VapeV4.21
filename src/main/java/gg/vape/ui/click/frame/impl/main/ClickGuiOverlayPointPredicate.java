package gg.vape.ui.click.frame.impl.main;

import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.frame.impl.main.ClickGuiMainFrame;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayController;
import java.awt.Point;

class ClickGuiOverlayPointPredicate
implements GuiMouseListener {
    final ClickGuiMainFrame P;
    final ClickGuiOverlayController m;

    ClickGuiOverlayPointPredicate(ClickGuiOverlayController clickGuiOverlayController, ClickGuiMainFrame clickGuiMainFrame) {
        this.m = clickGuiOverlayController;
        this.P = clickGuiMainFrame;
    }

    @Override
    public boolean Q(Point point) {
        return ClickGuiOverlayController.t(this.m).Q().R(point);
    }
}

