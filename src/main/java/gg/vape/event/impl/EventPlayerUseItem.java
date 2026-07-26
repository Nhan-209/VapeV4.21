package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.wrapper.impl.ItemStack;

public class EventPlayerUseItem
extends Event {
    private final ItemStack b;
    private static final EventListeners L = new EventListeners();

    @Override
    public EventListeners getListeners() {
        return L;
    }

    public ItemStack getItemStack() {
        return this.b;
    }

    public EventPlayerUseItem(Object object) {
        this.b = new ItemStack(object);
    }

    public static EventListeners getEventListeners() {
        return L;
    }

    @Override
    public boolean fire() {
        return super.fire();
    }
}

