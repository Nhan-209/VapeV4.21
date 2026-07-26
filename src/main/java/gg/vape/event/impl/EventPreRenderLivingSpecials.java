package gg.vape.event.impl;

import gg.vape.Vape;
import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.ResourceLocationKey;
import gg.vape.wrapper.impl.ResourceLocationName;

public class EventPreRenderLivingSpecials
extends Event {
    private static final EventListeners O = new EventListeners();
    private final Object t;

    public static EventListeners getEventListeners() {
        return O;
    }

    public EventPreRenderLivingSpecials(Object object) {
        this.t = object;
    }

    @Override
    public EventListeners getListeners() {
        return O;
    }

    @Override
    public boolean fire() {
        try {
            String string = ForgeVersion.c() == ForgeVersion.MC_1_8_9.i() ? new ResourceLocationName(this.t).n() : new ResourceLocationName(this.t).b(ResourceLocationKey.L());
            Vape.logError(string);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return super.fire();
    }
}

