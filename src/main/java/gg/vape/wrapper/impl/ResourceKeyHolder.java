package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class ResourceKeyHolder
extends Wrapper {
    public static ResourceKey v() {
        return new ResourceKey(ResourceKeyHolder.vapeInstance.getMappingsMapperCompat().qV.d());
    }

    public ResourceKeyHolder(Object object) {
        super(object);
    }
}

