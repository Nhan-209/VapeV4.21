package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ResourceKey;

public class ResourceKeyHolder
extends Wrapper {
    public static ResourceKey v() {
        return new ResourceKey(ResourceKeyHolder.c.getMappingsMapperCompat().qV.d());
    }

    public ResourceKeyHolder(Object object) {
        super(object);
    }
}

