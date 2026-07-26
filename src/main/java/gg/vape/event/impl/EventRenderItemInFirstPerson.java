package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.wrapper.impl.ItemRenderer;

public class EventRenderItemInFirstPerson
extends Event {
    private final Object b;
    public final float H;
    private static final EventListeners n = new EventListeners();

    public EventRenderItemInFirstPerson(Object object, float f) {
        this.b = object;
        this.H = f;
    }

    public ItemRenderer getItemRenderer() {
        return new ItemRenderer(this.b);
    }

    @Override
    public EventListeners getListeners() {
        return n;
    }

    public static EventListeners getEventListeners() {
        return n;
    }
}

