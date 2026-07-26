package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.module.Mod;

public class SyntheticAttackRequestEvent
extends Event {
    private static final EventListeners m = new EventListeners();
    private final Mod H;

    public Mod getSource() {
        return this.H;
    }

    @Override
    public EventListeners getListeners() {
        return m;
    }

    public SyntheticAttackRequestEvent(Mod mod) {
        this.H = mod;
    }

    public static EventListeners getEventListeners() {
        return m;
    }
}

