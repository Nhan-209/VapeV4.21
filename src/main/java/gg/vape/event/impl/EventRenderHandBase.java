package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;

public class EventRenderHandBase
extends Event {
    private static String c;
    private static final EventListeners h;
    private static String G;

    public static EventListeners getEventListeners() {
        return h;
    }

    @Override
    public EventListeners getListeners() {
        return h;
    }

    @Override
    public boolean fire() {
        return super.fire();
    }

    static {
        G = null;
        c = null;
        h = new EventListeners();
    }
}

