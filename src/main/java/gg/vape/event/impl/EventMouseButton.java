package gg.vape.event.impl;

import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.config.Profile;
import gg.vape.event.EventListeners;
import gg.vape.event.impl.EventKeyInputBase;
import gg.vape.manager.client.OnlineConnectionManager;
import gg.vape.module.Mod;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.KeyBinding;

public class EventMouseButton
extends EventKeyInputBase {
    public static int W;
    private static final EventListeners a;
    public static int E;


    public static EventListeners getEventListeners() {
        return a;
    }

    public EventMouseButton(int n, boolean bl) {
        super(n, bl);
    }

    public boolean isKeybinding(KeyBinding keyBinding) {
        int n = ClientSettings.H(keyBinding);
        int n2 = ForgeVersion.MC_1_16_5.d() ? n : 100 + n;
        return this.getKey() == n2;
    }

    static {
        E = 0;
        W = 1;
        a = new EventListeners();
    }

    @Override
    public EventListeners getListeners() {
        return a;
    }

    public int getButton() {
        return super.getKey();
    }

    @Override
    public boolean fire() {
        int n = -100 + this.getButton();
        if (this.getButtonState()) {
            for (Profile object : Vape.INSTANCE.getProfilesManager().b()) {
                if (!object.f(n)) continue;
                return this.isCanceled();
            }
        }
        for (Mod mod : Vape.INSTANCE.getModManager().collectMods()) {
            if (mod.a().L().isEmpty()) continue;
            mod.a().U(n, this.getButtonState());
        }
        OnlineConnectionManager.T.S().I(this);
        return super.fire();
    }

    public boolean getButtonState() {
        return super.isDown();
    }
}

