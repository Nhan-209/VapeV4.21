package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;

public class EventEntityRendererMouseUpdateBase
extends Event {
    private static final EventListeners W = new EventListeners();

    @Override
    public EventListeners getListeners() {
        return W;
    }

    public static EventListeners getEventListeners() {
        return W;
    }

    @Override
    public boolean fire() {
        return super.fire();
    }
}

