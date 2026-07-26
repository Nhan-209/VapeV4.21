package gg.vape.manager.client;

import gg.vape.manager.client.OnlineSettings;
import gg.vape.unmap.Bendable;

class OnlineSettingsNoopBindAction
extends Bendable {
    final OnlineSettings v;

    OnlineSettingsNoopBindAction(OnlineSettings onlineSettings) {
        this.v = onlineSettings;
    }

    @Override
    public void A() {
    }

    @Override
    public boolean m() {
        return false;
    }

    @Override
    public String y() {
        return null;
    }
}

