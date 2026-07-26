package gg.vape.event.impl;

import gg.vape.event.impl.EventStep;

public class EventStepHeightOverride
extends EventStep {
    private final float j;

    public EventStepHeightOverride(Object object, double d) {
        super(object);
        this.j = (float)(1.0 + d);
    }

    @Override
    public double getRealHeight() {
        return this.j;
    }
}

