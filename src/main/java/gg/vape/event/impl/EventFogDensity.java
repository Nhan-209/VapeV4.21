package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.RenderSystem;

public class EventFogDensity
extends Event {
    private static final EventListeners S = new EventListeners();
    private float v;

    public void setDensity(float f) {
        this.v = f;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public boolean fire() {
        boolean bl = super.fire();
        if (bl) {
            if (ForgeVersion.MC_1_8_9.B()) {
                GlStateManager.g(this.v);
            } else if (ForgeVersion.MC_1_16_5.d()) {
                RenderSystem.o(this.v);
            }
        }
        return bl;
    }

    public EventFogDensity(float f) {
        this.v = f;
    }

    public float getDensity() {
        return this.v;
    }

    public static EventListeners getEventListeners() {
        return S;
    }

    @Override
    public EventListeners getListeners() {
        return S;
    }
}

