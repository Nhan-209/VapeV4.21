package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;

public class LegacyRecursiveCancelGuardEvent
extends Event {
    private static int[] K;
    static int v;
    private static final EventListeners L;

    @Override
    public EventListeners getListeners() {
        return L;
    }


    public static int[] q() {
        return K;
    }

    @Override
    public boolean fire() {
        if (v > 0) {
            this.setCancelled(true);
        }
        if (++v > 100) {
            v = 0;
        }
        return super.fire();
    }

    public static void q(int[] nArray) {
        K = nArray;
    }

    public static EventListeners getEventListeners() {
        return L;
    }

    static {
        v = 0;
        L = new EventListeners();
        LegacyRecursiveCancelGuardEvent.q(new int[5]);
    }
}

