package gg.vape.module.render.hud;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPreRenderTick;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventWorldTime;
import gg.vape.module.render.hud.HudModule;
import gg.vape.module.render.hud.HudModuleGroup;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.WorldClient;

public class TimeChangerHudModule
extends HudModule {
    private long A = 0L;
    private NumberValue t = NumberValue.create((Object)this, "Time", "#", "hours", 0.0, 12.0, 24.0, 1.0);

    private void m(WorldClient worldClient, long l) {
        if (ForgeVersion.MC_1_16_5.d()) {
            worldClient.F().z(l);
            worldClient.F().R(l);
        } else {
            worldClient.i(l);
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @EventHandler
    public void g(EventPreRenderTick eventPreRenderTick) {
        if (ForgeVersion.MC_1_8_9.L()) {
            return;
        }
        if (eventPreRenderTick.getWorld().isNull()) {
            return;
        }
        double d = (Double)this.t.K();
        if ((d -= 6.0) < 0.0) {
            d = 24.0 + d;
        }
        this.m(eventPreRenderTick.getWorld(), Math.round(d * 1000.0));
    }

    public TimeChangerHudModule() {
        super("Time Changer", HudModuleGroup.T, "time_changer");
        this.setSuffix("Sets the in-game world time");
        this.addValue(this.t);
    }

    @EventHandler
    public void V(EventWorldTime eventWorldTime) {
        eventWorldTime.setWorldTime(this.A);
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        if (ForgeVersion.MC_1_8_9.A()) {
            return;
        }
        double d = (Double)this.t.K();
        if ((d -= 6.0) < 0.0) {
            d = 24.0 + d;
        }
        this.A = Math.round(d * 1000.0);
    }
}

