package gg.vape.ui.click.frame;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.frame.CollapsibleFrame;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.SettingsFrameHeaderComponent;

class SettingsFrameHeaderCollapseClickListener
implements GuiClickListener {
    final SettingsFrameHeaderComponent C;
    final Frame V;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void P() {
        if (this.V instanceof CollapsibleFrame) {
            ((CollapsibleFrame)((Object)this.V)).w();
        }
    }

    SettingsFrameHeaderCollapseClickListener(SettingsFrameHeaderComponent settingsFrameHeaderComponent, Frame frame) {
        this.C = settingsFrameHeaderComponent;
        this.V = frame;
    }
}

