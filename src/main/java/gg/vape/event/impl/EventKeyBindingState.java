package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;

public class EventKeyBindingState
extends Event {
    private final Object r;
    private static final EventListeners P = new EventListeners();
    private final int n;
    private static String[] y;
    private final boolean p;

    public static void I(String[] stringArray) {
        y = stringArray;
    }

    public EventKeyBindingState(Object object, boolean bl) {
        this.r = object;
        this.n = 0;
        this.p = bl;
    }

    public Object getKeyBinding() {
        return this.r;
    }

    @Override
    public boolean fire() {
        return super.fire();
    }

    public EventKeyBindingState(int n, boolean bl) {
        this.r = null;
        this.n = n;
        this.p = bl;
    }

    public int getKeyCode() {
        return this.n;
    }

    @Override
    public EventListeners getListeners() {
        return P;
    }

    public boolean isPressed() {
        return this.p;
    }

    public static String[] J() {
        return y;
    }

    public static EventListeners getEventListeners() {
        return P;
    }

    static {
        EventKeyBindingState.I(null);
    }
}

