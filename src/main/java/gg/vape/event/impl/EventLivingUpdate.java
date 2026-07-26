package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.wrapper.impl.Entity;

public class EventLivingUpdate
extends Event {
    private final Entity v;
    private static final EventListeners S = new EventListeners();

    public EventLivingUpdate(Entity entity) {
        this.v = entity;
    }

    @Override
    public EventListeners getListeners() {
        return S;
    }

    public Entity getEntity() {
        return this.v;
    }

    public static EventListeners getEventListeners() {
        return S;
    }
}

