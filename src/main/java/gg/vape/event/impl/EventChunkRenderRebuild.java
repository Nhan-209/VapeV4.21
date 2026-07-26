package gg.vape.event.impl;

import gg.vape.Vape;
import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.module.none.XRay;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class EventChunkRenderRebuild
extends Event {
    private static final EventListeners t = new EventListeners();

    @Override
    public boolean fire() {
        XRay xRay = Vape.INSTANCE.getModManager().G();
        if (xRay == null || !xRay.boolean_r()) {
            return false;
        }
        xRay.i(this);
        return this.isCanceled();
    }

    public static EventListeners getEventListeners() {
        return t;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    @Override
    public EventListeners getListeners() {
        return t;
    }
}

