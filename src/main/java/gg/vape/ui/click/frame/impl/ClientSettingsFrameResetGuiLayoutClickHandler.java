package gg.vape.ui.click.frame.impl;

import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.frame.impl.ClientSettingsFrame;

public class ClientSettingsFrameResetGuiLayoutClickHandler
implements GuiClickListener {
    final ClientSettingsFrame j;

    @Override
    public void P() {
        ClientSettingsFrame.n(this.j);
    }

    public ClientSettingsFrameResetGuiLayoutClickHandler(ClientSettingsFrame clientSettingsFrame) {
        this.j = clientSettingsFrame;
    }
}

