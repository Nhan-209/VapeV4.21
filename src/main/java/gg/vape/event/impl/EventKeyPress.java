package gg.vape.event.impl;

import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.config.Profile;
import gg.vape.event.EventBus;
import gg.vape.event.EventListeners;
import gg.vape.event.impl.EventKeyInputBase;
import gg.vape.input.KeyboardInput;
import gg.vape.manager.client.OnlineConnectionManager;
import gg.vape.module.Mod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;

public class EventKeyPress
extends EventKeyInputBase {
    private static final EventListeners w = new EventListeners();

    public EventKeyPress(int n, boolean bl) {
        super(n, bl);
    }

    @Override
    public EventListeners getListeners() {
        return w;
    }

    @Override
    public boolean fire() {
        if (!gg.vape.module.none.ClientSettings.fW.v() && KeyboardInput.isKeyDown(9) && KeyboardInput.isKeyDown(114)) {
            boolean bl = EventBus.y = !EventBus.y;
        }
        if (!gg.vape.module.none.ClientSettings.fW.v() && this.getKey() != 27) {
            boolean bl = gg.vape.module.none.ClientSettings.fW.H(this);
            return bl;
        }
        if (this.getKey() > 0 && this.isDown() && Minecraft.currentScreen().getObject() == null) {
            for (Profile object : Vape.INSTANCE.getProfilesManager().b()) {
                if (!object.f(this.getKey())) continue;
            }
        }
        for (Mod mod : Vape.INSTANCE.getModManager().collectMods()) {
            mod.u(this);
        }
        OnlineConnectionManager.T.S().y(this);
        return super.fire();
    }

    public static EventListeners getEventListeners() {
        return w;
    }

    public boolean isKeybinding(KeyBinding keyBinding) {
        return this.getKey() == ClientSettings.H(keyBinding);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

