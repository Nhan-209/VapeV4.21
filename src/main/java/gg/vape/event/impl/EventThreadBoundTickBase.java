package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;

public class EventThreadBoundTickBase
extends Event {
    private static final EventListeners p = new EventListeners();

    @Override
    public EventListeners getListeners() {
        return p;
    }

    @Override
    public boolean fire() {
        return super.fire();
    }

    public static EventListeners getEventListeners() {
        return p;
    }
}

