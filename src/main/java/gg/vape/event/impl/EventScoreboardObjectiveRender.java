package gg.vape.event.impl;

import gg.vape.Vape;
import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.module.render.hud.ScoreboardHudModule;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.ScoreObjective;

public class EventScoreboardObjectiveRender
extends Event {
    private static final EventListeners U = new EventListeners();

    @Override
    public EventListeners getListeners() {
        return U;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public EventScoreboardObjectiveRender(Object object, Object object2) {
        ScoreboardHudModule scoreboardHudModule = Vape.INSTANCE.getModManager().getMod(ScoreboardHudModule.class);
        if (ForgeVersion.MC_1_16_5.d()) {
            scoreboardHudModule.Y(new ScoreObjective(object2));
        } else {
            scoreboardHudModule.Y(new ScoreObjective(object));
        }
    }

    public EventScoreboardObjectiveRender(Object object, int n, int n2, Object object2) {
        ScoreboardHudModule scoreboardHudModule = Vape.INSTANCE.getModManager().getMod(ScoreboardHudModule.class);
        scoreboardHudModule.Y(new ScoreObjective(object));
    }

    public static EventListeners getEventListeners() {
        return U;
    }

    @Override
    public boolean fire() {
        ScoreboardHudModule scoreboardHudModule = Vape.INSTANCE.getModManager().getMod(ScoreboardHudModule.class);
        this.setCancelled(scoreboardHudModule.boolean_r());
        return super.fire();
    }
}

