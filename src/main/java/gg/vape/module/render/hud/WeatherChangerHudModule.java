package gg.vape.module.render.hud;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPreRenderTick;
import gg.vape.module.render.hud.HudModule;
import gg.vape.module.render.hud.HudModuleGroup;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ModeOption;
import gg.vape.unmap.ModeSelection;
import gg.vape.value.ModeValue;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.WorldClient;

public class WeatherChangerHudModule
extends HudModule {
    float c;
    public final ModeValue K;
    float J;
    private final ModeOption S;
    float F;
    float t;
    private final ModeOption s = new ModeOption("Clear");

    @EventHandler
    public void H(EventPreRenderTick eventPreRenderTick) {
        WorldClient worldClient = eventPreRenderTick.getWorld();
        if (worldClient.isNotNull()) {
            if (((ModeSelection)this.K.K()).equals(this.s)) {
                worldClient.T(0.0f);
                worldClient.o(0.0f);
                worldClient.f(0.0f);
                worldClient.g(0.0f);
            } else {
                worldClient.T(1.0f);
                worldClient.o(1.0f);
            }
        }
    }

    @Override
    public void onEnable() {
        if (Minecraft.theWorld().isNotNull()) {
            WorldClient worldClient = Minecraft.theWorld();
            this.t = worldClient.n();
            this.c = worldClient.N();
            this.J = worldClient.y();
            this.F = worldClient.V();
        }
    }

    public WeatherChangerHudModule() {
        super("Weather", HudModuleGroup.T, "weather");
        this.S = new ModeOption("Raining");
        this.K = ModeValue.create((Object)this, "Weather", this.s, this.s, this.S);
        this.addValue(this.K);
        this.setSuffix("Change the weather");
    }

    @Override
    public void onDisable() {
        if (Minecraft.theWorld().isNotNull()) {
            WorldClient worldClient = Minecraft.theWorld();
            worldClient.T(this.t);
            worldClient.o(this.c);
            worldClient.f(this.J);
            worldClient.g(this.F);
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

