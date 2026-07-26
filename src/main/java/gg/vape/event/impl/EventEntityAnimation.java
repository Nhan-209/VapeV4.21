package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;

public class EventEntityAnimation
extends Event {
    private boolean f;
    private Object P;
    private int Q;
    private static final EventListeners W = new EventListeners();

    public EventEntityAnimation(Object object, int n, boolean bl) {
        this.P = object;
        this.Q = n;
        this.f = bl;
    }

    @Override
    public EventListeners getListeners() {
        return W;
    }

    public static EventListeners getEventListeners() {
        return W;
    }
}

