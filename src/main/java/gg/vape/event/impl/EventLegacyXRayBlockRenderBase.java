package gg.vape.event.impl;

import gg.vape.Vape;
import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.module.none.XRay;
import gg.vape.wrapper.impl.Block;
import gg.vape.wrapper.impl.Tessellator;

public class EventLegacyXRayBlockRenderBase
extends Event {
    private static boolean r;
    private final Tessellator M;
    private static final EventListeners v;
    private final Block x;

    public Tessellator getTessellator() {
        return this.M;
    }

    @Override
    public boolean fire() {
        XRay xRay = Vape.INSTANCE.getModManager().G();
        if (xRay == null || !xRay.boolean_r()) {
            return false;
        }
        if (!xRay.isTargetBlock(this.x) && this.M.w()) {
            this.M.u(255, 255, 255, xRay.getOpacity());
        }
        return this.isCanceled();
    }

    @Override
    public EventListeners getListeners() {
        return v;
    }

    public Block getBlock() {
        return this.x;
    }

    public static void h(boolean bl) {
        r = bl;
    }

    public static EventListeners getEventListeners() {
        return v;
    }


    public EventLegacyXRayBlockRenderBase(Object object) {
        this.x = new Block(object);
        this.M = Tessellator.getInstance();
    }

    public static boolean a() {
        return r;
    }

    public static boolean C() {
        boolean bl = EventLegacyXRayBlockRenderBase.a();
        return true;
    }

    static {
        v = new EventListeners();
        EventLegacyXRayBlockRenderBase.h(false);
    }
}

