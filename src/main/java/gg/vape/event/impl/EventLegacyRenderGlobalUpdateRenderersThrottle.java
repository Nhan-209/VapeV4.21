package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.wrapper.impl.Minecraft;

public class EventLegacyRenderGlobalUpdateRenderersThrottle
extends Event {
    static float d = 0.0f;
    private static final EventListeners L;
    static float j;
    private static boolean m;

    @Override
    public boolean fire() {
        float f = Minecraft.getTimer().renderPartialTicks();
        j += 1.0f;
        if (f != d && j >= 10.0f) {
            j = 0.0f;
        }
        if (j > 0.0f) {
            this.setCancelled(true);
        }
        d = f;
        return super.fire();
    }

    @Override
    public EventListeners getListeners() {
        return L;
    }

    public static void V(boolean bl) {
        m = bl;
    }

    public static boolean c() {
        return m;
    }

    public static EventListeners getEventListeners() {
        return L;
    }

    static {
        j = 0.0f;
        L = new EventListeners();
        EventLegacyRenderGlobalUpdateRenderersThrottle.V(false);
    }


    public static boolean W() {
        boolean bl = EventLegacyRenderGlobalUpdateRenderersThrottle.c();
        return true;
    }
}

