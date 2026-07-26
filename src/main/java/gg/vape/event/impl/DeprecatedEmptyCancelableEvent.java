package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;

@Deprecated
public class DeprecatedEmptyCancelableEvent
extends Event {
    private static final EventListeners b = new EventListeners();

    public static EventListeners getEventListeners() {
        return b;
    }

    @Override
    public EventListeners getListeners() {
        return b;
    }

    @Override
    public boolean fire() {
        return super.fire();
    }
}

