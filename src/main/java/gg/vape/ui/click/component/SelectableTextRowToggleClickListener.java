package gg.vape.ui.click.component;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseClickButton;
import gg.vape.ui.click.frame.impl.main.ClickGuiSidecarPanelBase;
import java.awt.Point;

public class SelectableTextRowToggleClickListener
implements GuiMouseListener {
    final ClickGuiSidecarPanelBase h;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    @Override
    public void g(Point point, MouseClickButton uA) {
        if (uA == MouseClickButton.LEFT_CLICK && ClickGuiSidecarPanelBase.m(this.h) != null) {
            ClickGuiSidecarPanelBase.m(this.h).run();
        }
    }

    public SelectableTextRowToggleClickListener(ClickGuiSidecarPanelBase ye_12) {
        this.h = ye_12;
    }
}

