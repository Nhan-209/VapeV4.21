package gg.vape.event.impl;

import gg.vape.event.impl.EventEntityRendererMouseUpdateBase;
import gg.vape.wrapper.impl.DeltaTracker;

public class EventEntityRendererMouseUpdate
extends EventEntityRendererMouseUpdateBase {
    private final float q;

    public EventEntityRendererMouseUpdate(Object object) {
        this.q = new DeltaTracker(object).b(true);
    }

    public float getPartialTicks() {
        return this.q;
    }
}

