package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;

public class EventBedBreakerUpdate
extends Event {
    private static final EventListeners P = new EventListeners();

    @Override
    public EventListeners getListeners() {
        return P;
    }

    public static EventListeners getEventListeners() {
        return P;
    }

    @Override
    public boolean fire() {
        return super.fire();
    }
}

