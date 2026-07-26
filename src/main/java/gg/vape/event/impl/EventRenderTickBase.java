package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.wrapper.impl.DeltaTracker;
import gg.vape.wrapper.impl.Minecraft;

public class EventRenderTickBase
extends Event {
    private static final EventListeners G = new EventListeners();
    private final float L;
    private static String Y;

    public static EventListeners getEventListeners() {
        return G;
    }

    @Override
    public boolean fire() {
        return super.fire();
    }

    EventRenderTickBase(float f) {
        this.L = f == -1.0f ? Minecraft.getTimer().renderPartialTicks() : f;
    }

    public float getTicks() {
        return this.L;
    }

    public static String P() {
        return Y;
    }

    EventRenderTickBase(DeltaTracker deltaTracker) {
        this.L = deltaTracker.b(false);
    }

    public static void r(String string) {
        Y = string;
    }

    @Override
    public EventListeners getListeners() {
        return G;
    }

    static {
        EventRenderTickBase.r("qQGv3b");
    }
}

