package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;

public class LegacyAlwaysCanceledEvent
extends Event {
    private static final EventListeners x = new EventListeners();

    @Override
    public EventListeners getListeners() {
        return x;
    }

    public static EventListeners getEventListeners() {
        return x;
    }

    @Override
    public boolean fire() {
        this.setCancelled(true);
        return super.fire();
    }
}

