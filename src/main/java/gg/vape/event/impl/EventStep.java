package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.wrapper.impl.Entity;

public class EventStep
extends Event {
    private static final EventListeners c = new EventListeners();
    private final Entity b;
    private static float a;

    static float access$002(float f) {
        a = f;
        return a;
    }

    public double getStepHeight() {
        return this.b.u();
    }

    public void setStepHeight(double d) {
        this.b.K((float)d);
    }

    static float access$000() {
        return a;
    }

    @Override
    public boolean fire() {
        return super.fire();
    }

    public static EventListeners getEventListeners() {
        return c;
    }

    public Entity getEntity() {
        return this.b;
    }

    @Override
    public EventListeners getListeners() {
        return c;
    }

    public double getRealHeight() {
        return a;
    }

    EventStep(Object object) {
        this.b = new Entity(object);
    }
}

