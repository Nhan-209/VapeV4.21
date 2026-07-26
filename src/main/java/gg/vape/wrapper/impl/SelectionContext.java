package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.Entity;

public class SelectionContext
extends Wrapper {
    public SelectionContext(Object object) {
        super(object);
    }

    public static SelectionContext P(Entity entity) {
        return new SelectionContext(SelectionContext.c.getMappingsMapperCompat().RR.v(entity.getObject()));
    }
}

