package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.mapping.MappedClasses;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.Entity;
import org.jetbrains.annotations.Nullable;

public class EventEntityUpdateBase
extends Event {
    @Nullable
    private Entity h;
    private static final EventListeners E = new EventListeners();
    private final Object r;

    public static EventListeners getEventListeners() {
        return E;
    }

    @Override
    public boolean fire() {
        if (!MappedClasses.z5.isInstance(this.r)) {
            return false;
        }
        return super.fire();
    }

    @Override
    public EventListeners getListeners() {
        return E;
    }

    EventEntityUpdateBase(Object object) {
        this.r = object;
    }

    public Entity getEntity() {
        if (this.h == null) {
            this.h = new Entity(this.r);
        }
        return this.h;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }
}

