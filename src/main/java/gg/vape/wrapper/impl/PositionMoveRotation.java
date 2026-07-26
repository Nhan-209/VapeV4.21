package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.Vec3;

public class PositionMoveRotation
extends Wrapper {
    public Vec3 a() {
        return new Vec3(PositionMoveRotation.c.getMappingsMapperCompat().qT.r(this.I));
    }

    public void V(float f) {
        PositionMoveRotation.c.getMappingsMapperCompat().qT.d(this.I, f);
    }

    public void y(float f) {
        PositionMoveRotation.c.getMappingsMapperCompat().qT.u(this.I, f);
    }

    public Vec3 u() {
        return new Vec3(PositionMoveRotation.c.getMappingsMapperCompat().qT.j(this.I));
    }

    public float b() {
        return PositionMoveRotation.c.getMappingsMapperCompat().qT.J(this.I);
    }

    public float t() {
        return PositionMoveRotation.c.getMappingsMapperCompat().qT.n(this.I);
    }

    public PositionMoveRotation(Object object) {
        super(object);
    }
}

