package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MEntitySize;
import gg.vape.wrapper.Wrapper;

public class EntitySize
extends Wrapper {
    public float c() {
        return MEntitySize.A(EntitySize.c.getMappingsMapperCompat().h2, this.I);
    }

    public float u() {
        return MEntitySize.y(EntitySize.c.getMappingsMapperCompat().h2, this.I);
    }

    public EntitySize(Object object) {
        super(object);
    }
}

