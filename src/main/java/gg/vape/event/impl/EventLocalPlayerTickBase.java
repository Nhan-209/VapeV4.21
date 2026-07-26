package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.wrapper.impl.EntityPlayerSP;

public class EventLocalPlayerTickBase
extends Event {
    private final EntityPlayerSP e;
    private static final EventListeners r = new EventListeners();

    EventLocalPlayerTickBase(Object object) {
        this.e = new EntityPlayerSP(object);
    }

    public EntityPlayerSP getPlayer() {
        return this.e;
    }

    public static EventListeners getEventListeners() {
        return r;
    }

    @Override
    public boolean fire() {
        return super.fire();
    }

    @Override
    public EventListeners getListeners() {
        return r;
    }
}

