package gg.vape.event.impl;

import gg.vape.Vape;
import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.module.none.XRay;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class EventBlockRenderLayerGate
extends Event {
    private static final EventListeners F = new EventListeners();

    public static EventListeners getEventListeners() {
        return F;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    @Override
    public EventListeners getListeners() {
        return F;
    }

    @Override
    public boolean fire() {
        XRay xRay = Vape.INSTANCE.getModManager().G();
        return xRay != null && xRay.boolean_r();
    }
}
