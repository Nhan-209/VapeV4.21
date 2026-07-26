package gg.vape.event.impl;

import gg.vape.event.impl.EventMotion;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.Entity;

public class EventPostMotion
extends EventMotion {
    @Override
    public boolean fire() {
        if (V.isNull()) {
            return false;
        }
        boolean bl = super.fire();
        V.T(EventMotion.access$300());
        return bl;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public EventPostMotion(Object object) {
        super(new Entity(object));
    }
}

