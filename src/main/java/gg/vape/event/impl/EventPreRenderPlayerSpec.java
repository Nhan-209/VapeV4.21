package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.wrapper.impl.EntityPlayer;

public class EventPreRenderPlayerSpec
extends Event {
    private final float e;
    private static final EventListeners Y = new EventListeners();
    private final EntityPlayer h;

    public static EventListeners getEventListeners() {
        return Y;
    }

    @Override
    public boolean fire() {
        return super.fire();
    }

    public float getPartial() {
        return this.e;
    }

    @Override
    public EventListeners getListeners() {
        return Y;
    }

    public EntityPlayer getClientPlayer() {
        return this.h;
    }

    public EventPreRenderPlayerSpec(Object object, float f) {
        this.h = new EntityPlayer(object);
        this.e = f;
    }
}

