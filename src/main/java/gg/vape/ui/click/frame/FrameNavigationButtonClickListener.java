package gg.vape.ui.click.frame;

import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.frame.FrameNavigationButtonComponent;

class FrameNavigationButtonClickListener
implements GuiClickListener {
    final String d;
    final FrameNavigationButtonComponent D;

    FrameNavigationButtonClickListener(FrameNavigationButtonComponent frameNavigationButtonComponent, String string) {
        this.D = frameNavigationButtonComponent;
        this.d = string;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    @Override
    public void P() {
        FrameNavigationButtonComponent frameNavigationButtonComponent = this.D;
        boolean bl = !FrameNavigationButtonComponent.K(this.D);
        FrameNavigationButtonComponent.u(frameNavigationButtonComponent, bl);
        FrameNavigationButtonComponent.d(this.D).J();
        ClientSettings.y(this.d);
    }
}

