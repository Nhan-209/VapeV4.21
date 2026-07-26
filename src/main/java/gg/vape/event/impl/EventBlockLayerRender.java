package gg.vape.event.impl;

import gg.vape.Vape;
import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.module.none.XRay;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.Block;
import gg.vape.wrapper.impl.EnumWorldBlockLayer;

public class EventBlockLayerRender
extends Event {
    private final EnumWorldBlockLayer s;
    private final Block Y;
    private static final EventListeners x = new EventListeners();
    private boolean l;

    public void setShouldRender(boolean bl) {
        this.l = bl;
    }

    @Override
    public boolean fire() {
        XRay xRay = Vape.INSTANCE.getModManager().G();
        if (xRay == null || !xRay.boolean_r()) {
            return false;
        }
        xRay.onBlockRenderLayer(this);
        return super.isCanceled();
    }

    @Override
    public EventListeners getListeners() {
        return x;
    }

    public static EventListeners getEventListeners() {
        return x;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public boolean shouldRender() {
        return this.l;
    }

    public EventBlockLayerRender(Object object, Object object2) {
        this.Y = new Block(object);
        this.s = new EnumWorldBlockLayer(object2);
    }

    public EnumWorldBlockLayer getEnumWorldBlockLayer() {
        return this.s;
    }

    public Block getBlock() {
        return this.Y;
    }
}

