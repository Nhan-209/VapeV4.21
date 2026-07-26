package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.DataComponentMap;

public class EmptyDataComponentMap
extends DataComponentMap {
    public EmptyDataComponentMap(Object object) {
        super(object);
    }

    public static EmptyDataComponentMap J() {
        return new EmptyDataComponentMap(EmptyDataComponentMap.c.getMappingsMapperCompat().RS.L(DataComponentMap.u().getObject()));
    }
}

