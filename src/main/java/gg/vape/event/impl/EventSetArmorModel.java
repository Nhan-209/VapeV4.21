package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.wrapper.impl.Entity;

public class EventSetArmorModel
extends Event {
    private static final EventListeners O = new EventListeners();
    private final float i;
    private int F;
    private final Entity h;

    public float getPartialTick() {
        return this.i;
    }

    public int getResult() {
        return this.F;
    }

    public Entity getEntity() {
        return this.h;
    }

    @Override
    public EventListeners getListeners() {
        return O;
    }

    public void setResult(int n) {
        this.F = n;
    }

    public static EventListeners getEventListeners() {
        return O;
    }

    @Override
    public boolean fire() {
        return super.fire();
    }

    public EventSetArmorModel(Object object, int n, float f) {
        this.h = new Entity(object);
        this.F = n;
        this.i = f;
    }
}

