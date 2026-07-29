package gg.vape.event.impl;

import gg.vape.Vape;
import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.module.none.XRay;

public class EventChunkRenderRebuild
extends Event {
    private static final EventListeners t = new EventListeners();

    @Override
    public boolean fire() {
        XRay xRay = Vape.INSTANCE.getModManager().G();
        if (xRay == null || !xRay.boolean_r()) {
            return false;
        }
        xRay.onChunkRenderRebuild(this);
        return this.isCanceled();
    }

    public static EventListeners getEventListeners() {
        return t;
    }


    @Override
    public EventListeners getListeners() {
        return t;
    }
}

