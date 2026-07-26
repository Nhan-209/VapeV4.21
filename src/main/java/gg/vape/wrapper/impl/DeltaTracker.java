package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MDeltaTracker;
import gg.vape.wrapper.Wrapper;

public class DeltaTracker
extends Wrapper {
    public float b(boolean bl) {
        return MDeltaTracker.d(DeltaTracker.c.getMappingsMapperCompat().RU, this.I, bl);
    }

    public DeltaTracker(Object object) {
        super(object);
    }

    public float r() {
        return MDeltaTracker.K(DeltaTracker.c.getMappingsMapperCompat().RU, this.I);
    }
}

