package gg.vape.friend.ui;

import gg.vape.friend.ui.OnlineActivitySettingsFrame;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseClickButton;
import java.awt.Point;

public class OnlineActivityPanelRefreshClickHandler
implements GuiMouseListener {
    final OnlineActivitySettingsFrame Y;

    public OnlineActivityPanelRefreshClickHandler(OnlineActivitySettingsFrame onlineActivitySettingsFrame) {
        this.Y = onlineActivitySettingsFrame;
    }

    @Override
    public void g(Point point, MouseClickButton mouseClickButton) {
        if (mouseClickButton != MouseClickButton.LEFT_CLICK) {
            return;
        }
        OnlineActivitySettingsFrame.b(this.Y).Z$src$V$1pkcfcd();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

