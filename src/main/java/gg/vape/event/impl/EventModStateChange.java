package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.module.Mod;

public class EventModStateChange
extends Event {
    private static final EventListeners K = new EventListeners();
    private final boolean O;
    private final Mod H;

    public EventModStateChange(Mod mod, boolean bl) {
        this.H = mod;
        this.O = bl;
    }

    @Override
    public EventListeners getListeners() {
        return K;
    }

    public boolean isEnabled() {
        return this.O;
    }

    public static EventListeners getEventListeners() {
        return K;
    }

    public Mod getModule() {
        return this.H;
    }
}

