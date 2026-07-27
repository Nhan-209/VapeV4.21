package gg.vape.event.impl;

import gg.vape.Vape;
import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.module.none.XRay;
import gg.vape.wrapper.impl.SetVisibility;

public class EventVisGraphComputeVisibility
extends Event {
    private static final EventListeners G = new EventListeners();

    public static EventListeners getEventListeners() {
        return G;
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


    @Override
    public EventListeners getListeners() {
        return G;
    }

    public EventVisGraphComputeVisibility() {
    }

    public EventVisGraphComputeVisibility(Object object) {
    }

    public static Object getVisibility() {
        SetVisibility setVisibility = new SetVisibility(Vape.INSTANCE.getMappingsMapperCompat().qy.z.O(new Object[0]));
        setVisibility.n(true);
        return setVisibility.getObject();
    }
}

