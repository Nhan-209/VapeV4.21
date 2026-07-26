package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.Entity;

public class EventEntityJoinWorld
extends Event {
    private static final EventListeners f;
    private static final String b;
    private final Entity E;

    public EventEntityJoinWorld(Object object) {
        this.E = new Entity(object);
    }

    @Override
    public boolean fire() {
        if (Thread.currentThread().getName().contains(b)) {
            return false;
        }
        return super.fire();
    }

    static {
        b = "Server";
        f = new EventListeners();
    }

    public Entity getEntity() {
        return this.E;
    }

    public static EventListeners getEventListeners() {
        return f;
    }

    @Override
    public EventListeners getListeners() {
        return f;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

