package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;

public class EventRenderGlobalUpdateChunksThrottle
extends Event {
    private static final EventListeners T;
    static float E;
    private static String N;

    @Override
    public EventListeners getListeners() {
        return T;
    }

    @Override
    public boolean fire() {
        if ((E += 1.0f) >= 100.0f) {
            E = 0.0f;
        }
        if (E > 0.0f) {
            this.setCancelled(true);
        }
        return super.fire();
    }


    public static void h(String string) {
        N = string;
    }

    public static String k() {
        return N;
    }

    public static EventListeners getEventListeners() {
        return T;
    }

    static {
        E = 0.0f;
        EventRenderGlobalUpdateChunksThrottle.h("p3vFd");
        T = new EventListeners();
    }
}

