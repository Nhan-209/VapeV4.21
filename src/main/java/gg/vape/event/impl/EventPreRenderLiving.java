package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.MatrixStack;

public class EventPreRenderLiving
extends Event {
    private final Object i;
    private MatrixStack p;
    private Entity A;
    private static final EventListeners d = new EventListeners();
    private final double W;
    private final double Q;
    private final double x;
    private final Object H;

    public Entity getEntity() {
        if (this.A == null) {
            this.A = new Entity(this.H);
        }
        return this.A;
    }

    public double getZ() {
        return this.x;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    @Override
    public boolean fire() {
        return super.fire();
    }

    public double getY() {
        return this.Q;
    }

    @Override
    public EventListeners getListeners() {
        return d;
    }

    public static EventListeners getEventListeners() {
        return d;
    }

    public MatrixStack getMatrixStack() {
        if (this.p == null) {
            this.p = new MatrixStack(this.i);
        }
        return this.p;
    }

    public double getX() {
        return this.W;
    }

    public EventPreRenderLiving(Object object, double d, double d2, double d3, Object object2) {
        this.H = object;
        this.i = object2;
        this.W = d;
        this.Q = d2;
        this.x = d3;
    }
}

