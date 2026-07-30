package gg.vape.wrapper.impl;

public class EmptyDataComponentMap
extends DataComponentMap {
    public EmptyDataComponentMap(Object object) {
        super(object);
    }

    public static EmptyDataComponentMap J() {
        return new EmptyDataComponentMap(EmptyDataComponentMap.vapeInstance.getMappingsMapperCompat().RS.L(DataComponentMap.u().getObject()));
    }
}

