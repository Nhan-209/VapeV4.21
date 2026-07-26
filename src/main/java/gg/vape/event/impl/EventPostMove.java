package gg.vape.event.impl;

import gg.vape.event.impl.EventMove;
import gg.vape.wrapper.impl.Vec3;

public class EventPostMove
extends EventMove {
    @Override
    public boolean fire() {
        return super.fire();
    }

    public EventPostMove(Object object) {
        this(new Vec3(object).getX(), new Vec3(object).getY(), new Vec3(object).getZ());
    }

    public EventPostMove(double d, double d2, double d3) {
        super(d, d2, d3);
    }
}

