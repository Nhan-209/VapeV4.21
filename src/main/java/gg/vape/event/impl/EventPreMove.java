package gg.vape.event.impl;

import gg.vape.event.impl.EventMove;
import gg.vape.wrapper.impl.Vec3;

public class EventPreMove
extends EventMove {
    @Override
    public boolean fire() {
        return super.fire();
    }

    @Override
    public double getY() {
        return super.getY();
    }

    public Object getVector() {
        return Vec3.create(this.getX(), this.getY(), this.getZ()).getObject();
    }

    @Override
    public double getX() {
        return super.getX();
    }

    @Override
    public double getZ() {
        return super.getZ();
    }

    public EventPreMove(double d, double d2, double d3) {
        super(d, d2, d3);
    }

    public EventPreMove(Object object) {
        this(new Vec3(object).getX(), new Vec3(object).getY(), new Vec3(object).getZ());
    }
}

