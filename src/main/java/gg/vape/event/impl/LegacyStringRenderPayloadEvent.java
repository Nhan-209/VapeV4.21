package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;

public class LegacyStringRenderPayloadEvent
extends Event {
    private Object p;
    private String S;
    private static final EventListeners s = new EventListeners();
    private int w;
    private boolean q;
    private float Z;
    private float v;

    public static EventListeners getEventListeners() {
        return s;
    }

    public int getX() {
        return (int)this.Z;
    }

    public LegacyStringRenderPayloadEvent(Object object, String string, float f, float f2, int n, boolean bl) {
        this.p = object;
        this.S = string;
        this.Z = f;
        this.v = f2;
        this.w = n;
        this.q = bl;
    }

    @Override
    public EventListeners getListeners() {
        return s;
    }
}

