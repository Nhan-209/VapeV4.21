package gg.vape.event.impl;

import gg.vape.Vape;
import gg.vape.event.Event;
import gg.vape.event.EventListeners;

public class EventBlockLayerOverrideFallback
extends Event {
    private static final EventListeners F = new EventListeners();

    @Override
    public EventListeners getListeners() {
        return F;
    }


    @Override
    public boolean fire() {
        if (!Vape.INSTANCE.isEnabled()) {
            this.setCancelled(true);
        }
        return super.fire();
    }

    public static EventListeners getEventListeners() {
        return F;
    }
}

