package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;

public class EventClickMouse
extends Event {
    private static final EventListeners g = new EventListeners();

    @Override
    public boolean fire() {
        return super.fire();
    }

    @Override
    public EventListeners getListeners() {
        return g;
    }

    public static EventListeners getEventListeners() {
        return g;
    }
}

