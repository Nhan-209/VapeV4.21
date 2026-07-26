package gg.vape.ui.click.frame.impl.online;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.frame.impl.online.OnlineConnectionSettingsFrame;
import java.awt.Point;

public class OnlineConnectionBackdropMouseListener
implements GuiMouseListener {
    final OnlineConnectionSettingsFrame D;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public boolean Q(Point point) {
        return !this.D.Q().R(point);
    }

    public OnlineConnectionBackdropMouseListener(OnlineConnectionSettingsFrame onlineConnectionSettingsFrame) {
        this.D = onlineConnectionSettingsFrame;
    }
}

