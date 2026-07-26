package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;

public class EventWindowClick
extends Event {
    private static final EventListeners N = new EventListeners();

    public EventWindowClick(Object object) {
    }

    @Override
    public boolean fire() {
        return super.fire();
    }

    @Override
    public EventListeners getListeners() {
        return N;
    }

    public static EventListeners getEventListeners() {
        return N;
    }
}

