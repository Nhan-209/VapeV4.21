package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.Vec3i;

public class InlineVec3i
extends Vec3i {
    int y;
    int a;
    int L;

    @Override
    public int o() {
        return this.L;
    }

    @Override
    public int P() {
        return this.y;
    }

    @Override
    public int d() {
        return this.a;
    }

    public InlineVec3i(int n, int n2, int n3) {
        super(null);
        this.y = n;
        this.L = n2;
        this.a = n3;
    }
}

