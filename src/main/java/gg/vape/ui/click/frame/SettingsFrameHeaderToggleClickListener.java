package gg.vape.ui.click.frame;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.frame.SettingsFrameHeaderComponent;

class SettingsFrameHeaderToggleClickListener
implements GuiClickListener {
    final SettingsFrameHeaderComponent i;

    SettingsFrameHeaderToggleClickListener(SettingsFrameHeaderComponent settingsFrameHeaderComponent) {
        this.i = settingsFrameHeaderComponent;
    }

    @Override
    public void P() {
        this.i.w$src$Lgg_vape_ui_click_frame_Frame_$y4htd0().c(!this.i.w$src$Lgg_vape_ui_click_frame_Frame_$y4htd0().y$src$Z$1f55jvh());
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

