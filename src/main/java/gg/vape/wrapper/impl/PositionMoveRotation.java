package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class PositionMoveRotation
extends Wrapper {
    public Vec3 a() {
        return new Vec3(PositionMoveRotation.vapeInstance.getMappingsMapperCompat().qT.r(this.I));
    }

    public void V(float f) {
        PositionMoveRotation.vapeInstance.getMappingsMapperCompat().qT.d(this.I, f);
    }

    public void y(float f) {
        PositionMoveRotation.vapeInstance.getMappingsMapperCompat().qT.u(this.I, f);
    }

    public Vec3 u() {
        return new Vec3(PositionMoveRotation.vapeInstance.getMappingsMapperCompat().qT.j(this.I));
    }

    public float b() {
        return PositionMoveRotation.vapeInstance.getMappingsMapperCompat().qT.J(this.I);
    }

    public float t() {
        return PositionMoveRotation.vapeInstance.getMappingsMapperCompat().qT.n(this.I);
    }

    public PositionMoveRotation(Object object) {
        super(object);
    }
}

