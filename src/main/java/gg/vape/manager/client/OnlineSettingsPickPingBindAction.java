package gg.vape.manager.client;

import gg.vape.friend.ping.PingManager;
import gg.vape.manager.client.OnlineSettings;
import gg.vape.unmap.Bendable;

class OnlineSettingsPickPingBindAction
extends Bendable {
    final OnlineSettings P;

    OnlineSettingsPickPingBindAction(OnlineSettings onlineSettings) {
        this.P = onlineSettings;
    }

    @Override
    public String getDisplayText() {
        return null;
    }

    @Override
    public void onBindActivated() {
        PingManager.B.onEnable();
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
