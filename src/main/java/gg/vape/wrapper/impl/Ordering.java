package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;
import java.util.List;

public class Ordering
extends Wrapper {
    public Ordering(Object object) {
        super(object);
    }

    public List E(Iterable iterable) {
        return Ordering.c.getMappingsMapperCompat().qm.n(this.I, iterable);
    }
}

