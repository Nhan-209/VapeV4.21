package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;

public class EventMove
extends Event {
    private double q;
    private static final EventListeners f = new EventListeners();
    private double T;
    private double j;

    public EventMove setY(double d) {
        this.T = d;
        return this;
    }

    public double getZ() {
        return this.q;
    }

    public EventMove setZ(double d) {
        this.q = d;
        return this;
    }

    public static EventListeners getEventListeners() {
        return f;
    }

    @Override
    public EventListeners getListeners() {
        return f;
    }

    public double getX() {
        return this.j;
    }

    public EventMove(double d, double d2, double d3) {
        this.j = d;
        this.T = d2;
        this.q = d3;
    }

    public EventMove setX(double d) {
        this.j = d;
        return this;
    }

    public double getY() {
        return this.T;
    }

    @Override
    public boolean fire() {
        return super.fire();
    }
}

