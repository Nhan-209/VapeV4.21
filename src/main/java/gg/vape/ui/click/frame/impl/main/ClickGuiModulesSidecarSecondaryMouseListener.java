package gg.vape.ui.click.frame.impl.main;

import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseClickButton;
import gg.vape.ui.click.frame.impl.main.ClickGuiModulesSidecarPanel;
import java.awt.Point;

class ClickGuiModulesSidecarSecondaryMouseListener
implements GuiMouseListener {
    final ClickGuiModulesSidecarPanel Y;
    final Runnable w;

    @Override
    public void g(Point point, MouseClickButton uA) {
        this.w.run();
    }

    ClickGuiModulesSidecarSecondaryMouseListener(ClickGuiModulesSidecarPanel yy_12, Runnable runnable) {
        this.Y = yy_12;
        this.w = runnable;
    }
}

