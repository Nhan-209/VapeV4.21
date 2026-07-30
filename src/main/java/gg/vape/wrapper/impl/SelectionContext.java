package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class SelectionContext
extends Wrapper {
    public SelectionContext(Object object) {
        super(object);
    }

    public static SelectionContext P(Entity entity) {
        return new SelectionContext(SelectionContext.vapeInstance.getMappingsMapperCompat().RR.v(entity.getObject()));
    }
}

