package gg.vape.event.impl;

import gg.vape.Vape;
import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.module.none.XRay;
import gg.vape.wrapper.impl.Block;

public class EventBlockShouldRender
extends Event {
    private final Block a;
    private static final EventListeners p = new EventListeners();

    public EventBlockShouldRender(Object object) {
        this.a = new Block(object);
    }

    @Override
    public boolean fire() {
        XRay xRay = Vape.INSTANCE.getModManager().G();
        if (xRay == null || !xRay.boolean_r()) {
            return false;
        }
        xRay.onBlockSideRender(this);
        return this.isCanceled();
    }

    @Override
    public EventListeners getListeners() {
        return p;
    }

    public Block getBlock() {
        return this.a;
    }


    public static EventListeners getEventListeners() {
        return p;
    }
}

