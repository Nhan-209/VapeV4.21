package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;

public class EventRenderFirstPersonItemBase
extends Event {
    public final float Z;
    public final float p;
    private static final EventListeners n = new EventListeners();

    public EventRenderFirstPersonItemBase(float f, float f2) {
        this.p = f;
        this.Z = f2;
    }

    public static EventListeners getEventListeners() {
        return n;
    }

    @Override
    public EventListeners getListeners() {
        return n;
    }
}

