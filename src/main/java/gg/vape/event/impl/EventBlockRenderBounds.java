package gg.vape.event.impl;

import gg.vape.Vape;
import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.module.none.XRay;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.Block;
import gg.vape.wrapper.impl.RenderBlocks;

public class EventBlockRenderBounds
extends Event {
    private RenderBlocks j;
    private static final EventListeners Y = new EventListeners();
    private Block T;

    public RenderBlocks getRenderBlocks() {
        return this.j;
    }

    public EventBlockRenderBounds(Object object, Object object2) {
        this.j = new RenderBlocks(object);
        this.T = new Block(object2);
    }

    public Block getBlock() {
        return this.T;
    }

    @Override
    public boolean fire() {
        XRay xRay = Vape.INSTANCE.getModManager().G();
        if (xRay == null || !xRay.boolean_r()) {
            return false;
        }
        xRay.onAmbientOcclusion(this);
        return this.isCanceled();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public static EventListeners getEventListeners() {
        return Y;
    }

    @Override
    public EventListeners getListeners() {
        return Y;
    }
}

