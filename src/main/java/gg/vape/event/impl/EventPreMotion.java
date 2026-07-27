package gg.vape.event.impl;

import gg.vape.event.impl.EventMotion;
import gg.vape.wrapper.impl.Entity;

public class EventPreMotion
extends EventMotion {
    @Override
    public boolean fire() {
        if (V.isNull()) {
            return false;
        }
        boolean bl = super.fire();
        EventMotion.access$302(V.S$src$Lgg_vape_wrapper_impl_Entity_$dgzs12());
        if (EventPreMotion.shouldAlwaysSend()) {
            V.T(new Entity(null));
        }
        return bl;
    }


    public EventPreMotion(Object object) {
        super(new Entity(object));
        C = false;
        EventMotion.access$002(EventMotion.V.J());
        EventMotion.access$102(EventMotion.V.V());
        EventMotion.access$202(EventMotion.V.b$src$Z$fqlxe4());
    }
}

