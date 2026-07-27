package gg.vape.module.render.hud;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPreRenderTick;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventWorldTime;
import gg.vape.module.render.hud.HudModule;
import gg.vape.module.render.hud.HudModuleGroup;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.WorldClient;

public class TimeChangerHudModule
extends HudModule {
    private long worldTime = 0L;
    private NumberValue timeValue = NumberValue.create((Object)this, "Time", "#", "hours", 0.0, 12.0, 24.0, 1.0);

    private void applyWorldTime(WorldClient worldClient, long time) {
        if (ForgeVersion.MC_1_16_5.d()) {
            worldClient.F().z(time);
            worldClient.F().R(time);
        } else {
            worldClient.i(time);
        }
    }


    @EventHandler
    public void g(EventPreRenderTick eventPreRenderTick) {
        if (ForgeVersion.MC_1_8_9.L()) {
            return;
        }
        if (eventPreRenderTick.getWorld().isNull()) {
            return;
        }
        double hours = (Double)this.timeValue.K();
        if ((hours -= 6.0) < 0.0) {
            hours = 24.0 + hours;
        }
        this.applyWorldTime(eventPreRenderTick.getWorld(), Math.round(hours * 1000.0));
    }

    public TimeChangerHudModule() {
        super("Time Changer", HudModuleGroup.T, "time_changer");
        this.setSuffix("Sets the in-game world time");
        this.addValue(this.timeValue);
    }

    @EventHandler
    public void V(EventWorldTime eventWorldTime) {
        eventWorldTime.setWorldTime(this.worldTime);
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        if (ForgeVersion.MC_1_8_9.A()) {
            return;
        }
        double hours = (Double)this.timeValue.K();
        if ((hours -= 6.0) < 0.0) {
            hours = 24.0 + hours;
        }
        this.worldTime = Math.round(hours * 1000.0);
    }
}

