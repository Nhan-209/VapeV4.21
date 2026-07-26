package gg.vape.config;

import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.unmap.BindChangeListener;

class ClientSettingsBindChangeListener
implements BindChangeListener {
    final ClientSettings C;

    ClientSettingsBindChangeListener(ClientSettings clientSettings) {
        this.C = clientSettings;
    }

    @Override
    public void S() {
        Vape.INSTANCE.getFriendManager().r();
    }
}

