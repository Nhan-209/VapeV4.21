package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.mapping.MappedClasses;
import gg.vape.wrapper.impl.Entity;

@Deprecated
public class DeprecatedEntityPlayerUpdateEventBase
extends Event {
    private static final EventListeners A = new EventListeners();
    private final Entity N;

    public DeprecatedEntityPlayerUpdateEventBase(Object object) {
        this.N = new Entity(object);
    }

    @Override
    public EventListeners getListeners() {
        return A;
    }

    public Entity getEntity() {
        return this.N;
    }

    public static EventListeners getEventListeners() {
        return A;
    }


    @Override
    public boolean fire() {
        if (!this.N.isInstance(MappedClasses.z5)) {
            return false;
        }
        return super.fire();
    }
}

