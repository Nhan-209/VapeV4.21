package gg.vape.event.impl;

import gg.vape.Vape;
import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.module.none.XRay;

public class EventBlockFluidRender
extends Event {
    private int n;
    private int T;
    private Object A;
    private int d;
    private Object h;
    private boolean v;
    private static final EventListeners G = new EventListeners();

    public boolean isResult() {
        return this.v;
    }

    @Override
    public EventListeners getListeners() {
        return G;
    }

    public EventBlockFluidRender(Object object, Object object2, int n, int n2, int n3) {
        this.h = object;
        this.A = object2;
        this.T = n;
        this.d = n2;
        this.n = n3;
    }

    @Override
    public boolean fire() {
        XRay xRay = Vape.INSTANCE.getModManager().G();
        if (xRay == null || !xRay.boolean_r()) {
            return false;
        }
        xRay.onBlockFluidRender(this);
        if (this.isCanceled()) {
            this.v = Vape.INSTANCE.getMappingsMapperCompat().i.E.e(this.h, this.A, this.T, this.d, this.n, Float.valueOf(1.0f), Float.valueOf(1.0f), Float.valueOf(1.0f));
        }
        return this.isCanceled();
    }


    public static EventListeners getEventListeners() {
        return G;
    }
}

