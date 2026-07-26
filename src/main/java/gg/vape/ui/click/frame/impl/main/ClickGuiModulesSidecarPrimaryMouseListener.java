package gg.vape.ui.click.frame.impl.main;

import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseClickButton;
import gg.vape.ui.click.frame.impl.main.ClickGuiModulesSidecarPanel;
import java.awt.Point;

class ClickGuiModulesSidecarPrimaryMouseListener
implements GuiMouseListener {
    final Runnable c;
    final ClickGuiModulesSidecarPanel S;

    @Override
    public void g(Point point, MouseClickButton uA) {
        this.c.run();
    }

    ClickGuiModulesSidecarPrimaryMouseListener(ClickGuiModulesSidecarPanel yy_12, Runnable runnable) {
        this.S = yy_12;
        this.c = runnable;
    }
}

