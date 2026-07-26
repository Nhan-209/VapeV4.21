package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.event.impl.EventStep;
import gg.vape.wrapper.impl.Entity;

public class EventStepHeightRestore
extends Event {
    private static final EventListeners C = new EventListeners();
    private final Entity L;

    public EventStepHeightRestore(Object object) {
        this.L = new Entity(object);
    }

    @Override
    public EventListeners getListeners() {
        return C;
    }

    @Override
    public boolean fire() {
        this.L.K(EventStep.access$000());
        return super.fire();
    }

    public static EventListeners getEventListeners() {
        return C;
    }
}

