package gg.vape.ui.click.frame.impl;

import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.impl.SettingsFrameHeaderComponent;

class SettingsFrameHeaderPrimaryButtonClickHandler
implements GuiClickListener {
    final SettingsFrameHeaderComponent D;
    final Frame p;

    @Override
    public void P() {
        if (SettingsFrameHeaderComponent.D(this.D) != null) {
            SettingsFrameHeaderComponent.D(this.D).P();
        }
        ClientSettings.f(this.p.getClass(), false);
    }

    SettingsFrameHeaderPrimaryButtonClickHandler(SettingsFrameHeaderComponent settingsFrameHeaderComponent, Frame frame) {
        this.D = settingsFrameHeaderComponent;
        this.p = frame;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

