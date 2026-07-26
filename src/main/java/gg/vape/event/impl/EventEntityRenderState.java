package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.RenderStateBridge;

public class EventEntityRenderState
extends Event {
    private final Object Z;
    private Entity V;
    private static final EventListeners N = new EventListeners();
    private RenderStateBridge x;
    private final Object F;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public EventEntityRenderState(Object object, Object object2) {
        this.F = object;
        this.Z = object2;
    }

    @Override
    public EventListeners getListeners() {
        return N;
    }

    public static EventListeners getEventListeners() {
        return N;
    }

    public Entity getEntity() {
        if (this.V == null) {
            this.V = new Entity(this.F);
        }
        return this.V;
    }

    public RenderStateBridge getEntityRenderState() {
        if (this.x == null) {
            this.x = new RenderStateBridge(this.Z);
        }
        return this.x;
    }
}

