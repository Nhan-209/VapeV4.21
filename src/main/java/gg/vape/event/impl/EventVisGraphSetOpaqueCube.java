package gg.vape.event.impl;

import gg.vape.Vape;
import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.module.none.XRay;

public class EventVisGraphSetOpaqueCube
extends Event {
    private static final EventListeners x = new EventListeners();

    @Override
    public EventListeners getListeners() {
        return x;
    }

    @Override
    public boolean fire() {
        XRay xRay = Vape.INSTANCE.getModManager().G();
        if (xRay == null || !xRay.boolean_r()) {
            return false;
        }
        this.setCancelled(true);
        return true;
    }

    public static EventListeners getEventListeners() {
        return x;
    }

}

