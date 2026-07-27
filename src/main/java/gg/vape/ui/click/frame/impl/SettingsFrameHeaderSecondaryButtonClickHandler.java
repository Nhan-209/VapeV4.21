package gg.vape.ui.click.frame.impl;

import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.frame.impl.SettingsFrameHeaderComponent;

class SettingsFrameHeaderSecondaryButtonClickHandler
implements GuiClickListener {
    final SettingsFrameHeaderComponent Q;

    SettingsFrameHeaderSecondaryButtonClickHandler(SettingsFrameHeaderComponent settingsFrameHeaderComponent) {
        this.Q = settingsFrameHeaderComponent;
    }

    @Override
    public void P() {
        if (SettingsFrameHeaderComponent.O(this.Q) != null) {
            SettingsFrameHeaderComponent.O(this.Q).P();
        }
    }

}

