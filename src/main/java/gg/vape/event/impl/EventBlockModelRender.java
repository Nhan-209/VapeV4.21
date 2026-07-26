package gg.vape.event.impl;

import gg.vape.Vape;
import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.module.none.XRay;
import gg.vape.utils.MathUtil;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.BlockState;
import gg.vape.wrapper.impl.ForgeVersion;

public class EventBlockModelRender
extends Event {
    private static final EventListeners a = new EventListeners();
    private Object B;
    private boolean v;
    private Object r;
    private Object T;
    private Object j;
    private boolean n;
    private Object w;
    private Object S;

    public EventBlockModelRender(Object object, Object object2, Object object3, Object object4, Object object5, Object object6, boolean bl) {
        this.B = object;
        this.T = object2;
        this.j = object3;
        this.S = object4;
        this.w = object5;
        this.r = object6;
        this.v = bl;
    }

    @Override
    public EventListeners getListeners() {
        return a;
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    public boolean getResult() {
        return this.n;
    }

    @Override
    public boolean fire() {
        XRay xRay = Vape.INSTANCE.getModManager().G();
        if (xRay == null || !xRay.r$src$Z$14eylz9()) {
            return false;
        }
        xRay.onBlockModelRender(this);
        try {
            long l = ForgeVersion.MC_1_12_2.d() ? MathUtil.S(new BlockPos(this.w)) : 0L;
            this.n = Vape.INSTANCE.getMappings().hE.a(this.B, this.T, this.j, new BlockState(this.S).getBlock().getObject(), this.S, this.w, this.r, this.v, l);
        }
        catch (Exception exception) {
            // empty catch block
        }
        return this.isCanceled();
    }

    public static EventListeners getEventListeners() {
        return a;
    }
}

