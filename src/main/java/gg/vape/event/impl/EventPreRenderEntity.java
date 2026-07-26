package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.Entity;

public class EventPreRenderEntity
extends Event {
    private Entity f = null;
    private static final EventListeners x = new EventListeners();
    private final Object E;

    @Override
    public EventListeners getListeners() {
        return x;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public boolean fire() {
        return super.fire();
    }

    public static EventListeners getEventListeners() {
        return x;
    }

    public Entity getEntity() {
        if (this.f == null) {
            this.f = new Entity(this.E);
        }
        return this.f;
    }

    public EventPreRenderEntity(Object object) {
        this.E = object;
    }
}

