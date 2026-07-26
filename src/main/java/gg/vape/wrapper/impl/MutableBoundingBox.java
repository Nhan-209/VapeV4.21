package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class MutableBoundingBox
extends Wrapper {
    public static MutableBoundingBox Q(int n, int n2, int n3, int n4, int n5, int n6) {
        return new MutableBoundingBox(MutableBoundingBox.c.getMappingsMapperCompat().hM.P(n, n2, n3, n4, n5, n6));
    }

    public MutableBoundingBox(Object object) {
        super(object);
    }

    public boolean O(MutableBoundingBox jz_12) {
        return MutableBoundingBox.c.getMappingsMapperCompat().hM.D(this.I, jz_12.getObject());
    }
}

