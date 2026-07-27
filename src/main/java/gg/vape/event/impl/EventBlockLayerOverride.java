package gg.vape.event.impl;

import gg.vape.Vape;
import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.module.none.XRay;
import gg.vape.wrapper.impl.Block;
import gg.vape.wrapper.impl.EnumWorldBlockLayer;

public class EventBlockLayerOverride
extends Event {
    private boolean t;
    private final Block e;
    private static int G;
    private static final EventListeners g;

    public static EventListeners getEventListeners() {
        return g;
    }


    public Block getBlock() {
        return this.e;
    }

    @Override
    public boolean fire() {
        XRay xRay = Vape.INSTANCE.getModManager().G();
        if (xRay == null || !xRay.boolean_r()) {
            return false;
        }
        xRay.onBlockRenderDecision(this);
        return true;
    }

    @Override
    public EventListeners getListeners() {
        return g;
    }

    public EventBlockLayerOverride(Object object) {
        this.e = new Block(object);
    }

    public void setShouldRender(boolean bl) {
        this.t = bl;
    }

    public static int f() {
        return G;
    }

    public static void O(int n) {
        G = n;
    }

    public static int I() {
        int n = EventBlockLayerOverride.f();
        return 2;
    }

    public Object getBlockLayer() {
        Object object = this.t ? EnumWorldBlockLayer.W().getObject() : EnumWorldBlockLayer.v().getObject();
        return object;
    }

    static {
        g = new EventListeners();
        EventBlockLayerOverride.O(0);
    }
}

