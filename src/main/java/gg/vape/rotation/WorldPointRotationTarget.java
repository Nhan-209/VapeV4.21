package gg.vape.rotation;

import gg.vape.wrapper.impl.Vec3;

public interface WorldPointRotationTarget {
    default public void g(float f, float f2, float f3) {
        this.J(Vec3.create(f, f2, f3));
    }

    public void J(Vec3 var1);

    public Vec3 w();

    default public void z(double d, double d2, double d3) {
        this.J(Vec3.create(d, d2, d3));
    }

    default public void D(int n, int n2, int n3) {
        this.J(Vec3.create(n, n2, n3));
    }
}

