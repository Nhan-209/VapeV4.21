package gg.vape.event.impl;

import gg.vape.Vape;
import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.module.none.XRay;
import gg.vape.wrapper.impl.WorldRenderer;
import java.nio.ByteOrder;

public class EventBlockRenderColorOpacity
extends Event {
    private int F;
    private static final long b;
    private final WorldRenderer K;
    private final int G;
    private static final EventListeners W;
    private final float i;
    private final float Z;
    private final float T;

    static {
        b = 22123904822673663L;
        W = new EventListeners();
    }


    public static EventListeners getEventListeners() {
        return W;
    }

    public void setOpacity(int n) {
        this.F = n;
    }

    @Override
    public EventListeners getListeners() {
        return W;
    }

    @Override
    public boolean fire() {
        XRay xRay = Vape.INSTANCE.getModManager().G();
        if (xRay != null && xRay.r$src$Z$14eylz9()) {
            int n;
            int n2;
            int n3;
            xRay.onBlockRenderColorOpacity(this);
            int n4 = this.K.o(this.G);
            int n5 = this.K.O().get(n4);
            if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
                n3 = (int)((float)(n5 & 0xFF) * this.i);
                n2 = (int)((float)(n5 >> 8 & 0xFF) * this.Z);
                n = (int)((float)(n5 >> 16 & 0xFF) * this.T);
            } else {
                n3 = (int)((float)(n4 >> 24 & 0xFF) * this.i);
                n2 = (int)((float)(n4 >> 16 & 0xFF) * this.Z);
                n = (int)((float)(n4 >> 8 & 0xFF) * this.T);
            }
            int n6 = 0;
            n6 |= this.F << 24;
            n6 |= n3 << 16;
            n6 |= n2 << 8;
            this.K.O().put(n4, n6 |= n);
        }
        return this.isCanceled();
    }

    public EventBlockRenderColorOpacity(Object object, float f, float f2, float f3, int n) {
        this.K = new WorldRenderer(object);
        this.i = f;
        this.Z = f2;
        this.T = f3;
        this.G = n;
        this.F = (int)b;
    }
}

