package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.wrapper.impl.Entity;

public class EventSetSprinting
extends Event {
    private final boolean V;
    private final Entity n;
    private static final EventListeners r = new EventListeners();

    public Entity getEntity() {
        return this.n;
    }

    @Override
    public EventListeners getListeners() {
        return r;
    }

    public EventSetSprinting(Object object, boolean bl) {
        this.n = new Entity(object);
        this.V = bl;
    }

    public boolean isNewStateSprinting() {
        return this.V;
    }

    @Override
    public boolean fire() {
        return super.fire();
    }

    public static EventListeners getEventListeners() {
        return r;
    }
}

