package gg.vape.ui.click.frame.impl.profile;

import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseClickButton;
import gg.vape.ui.click.frame.impl.profile.PublicProfileOverlayPopupFrame;
import java.awt.Point;

class PublicProfileOverlayOutsideClickCloseHandler
implements GuiMouseListener {
    final PublicProfileOverlayPopupFrame e;

    PublicProfileOverlayOutsideClickCloseHandler(PublicProfileOverlayPopupFrame publicProfileOverlayPopupFrame) {
        this.e = publicProfileOverlayPopupFrame;
    }

    @Override
    public void g(Point point, MouseClickButton mouseClickButton) {
        if (!PublicProfileOverlayPopupFrame.C(this.e)) {
            return;
        }
        if (!this.e.w$src$Z$e457mb()) {
            ClientSettings.K(this.e);
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

