package gg.vape.event.impl;

import gg.vape.event.impl.EventMotion;
import gg.vape.wrapper.impl.Entity;

public class EventPostMotion
extends EventMotion {
    @Override
    public boolean fire() {
        if (V.isNull()) {
            return false;
        }
        boolean bl = super.fire();
        V.T(EventMotion.access$300());
        return bl;
    }


    public EventPostMotion(Object object) {
        super(new Entity(object));
    }
}

