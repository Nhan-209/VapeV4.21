package gg.vape.module.render.hud;

import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPreTick;
import gg.vape.module.render.hud.HudModule;
import gg.vape.module.render.hud.HudModuleGroup;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;

public class NoClickDelayHudModule
extends HudModule {
    public NoClickDelayHudModule() {
        super("NoClickDelay", HudModuleGroup.T, "no_click_delay2");
        this.setSuffix("Removes the click delay that normally occurs after missing an attack");
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        if (ForgeVersion.MC_1_8_9.d() && ClientSettings.M() && Minecraft.currentScreen().isNull()) {
            Minecraft.r(0);
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

