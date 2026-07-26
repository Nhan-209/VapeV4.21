package gg.vape.ui.click.frame.impl;

import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.frame.impl.ClientSettingsFrame;

public class ClientSettingsFrameSortGuiClickHandler
implements GuiClickListener {
    final ClientSettingsFrame c;

    public ClientSettingsFrameSortGuiClickHandler(ClientSettingsFrame sG2) {
        this.c = sG2;
    }

    @Override
    public void P() {
        ClientSettingsFrame.a(this.c);
    }
}

