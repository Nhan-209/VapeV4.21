package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.mapping.MappedClasses;
import gg.vape.wrapper.impl.EntityPlayer;

public class EventPlayerTickBase
extends Event {
    private final Object T;
    private static final EventListeners a = new EventListeners();
    private EntityPlayer M;

    EventPlayerTickBase(Object object) {
        this.T = object;
    }


    @Override
    public EventListeners getListeners() {
        return a;
    }

    public static EventListeners getEventListeners() {
        return a;
    }

    public EntityPlayer getPlayer() {
        if (this.M == null) {
            this.M = new EntityPlayer(this.T);
        }
        return this.M;
    }

    @Override
    public boolean fire() {
        if (!MappedClasses.z5.isInstance(this.T)) {
            return false;
        }
        return super.fire();
    }
}

