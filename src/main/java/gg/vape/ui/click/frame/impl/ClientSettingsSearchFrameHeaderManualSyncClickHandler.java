package gg.vape.ui.click.frame.impl;

import gg.vape.Vape;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.frame.impl.ClientSettingsSearchFrameHeader;

class ClientSettingsSearchFrameHeaderManualSyncClickHandler
implements GuiClickListener {
    final ClientSettingsSearchFrameHeader z;

    @Override
    public void P() {
        Vape.INSTANCE.getSyncThread().requestSave();
        ClientSettingsSearchFrameHeader.F(this.z).Z(false);
    }

    ClientSettingsSearchFrameHeaderManualSyncClickHandler(ClientSettingsSearchFrameHeader clientSettingsSearchFrameHeader) {
        this.z = clientSettingsSearchFrameHeader;
    }
}
