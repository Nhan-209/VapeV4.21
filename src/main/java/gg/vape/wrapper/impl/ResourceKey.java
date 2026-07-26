package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MResourceKey;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ResourceLocation;

public class ResourceKey
extends Wrapper {
    public ResourceKey(Object object) {
        super(object);
    }

    public ResourceLocation X() {
        return new ResourceLocation(MResourceKey.J(ResourceKey.c.getMappingsMapperCompat().P, this.I));
    }
}

