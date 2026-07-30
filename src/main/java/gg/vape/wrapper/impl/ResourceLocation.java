package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class ResourceLocation
extends Wrapper {
    public static ResourceLocation create(String string) {
        return new ResourceLocation(ResourceLocation.vapeInstance.getMappingsMapperCompat().C9.o(string));
    }

    public String getResourcePath() {
        return ResourceLocation.vapeInstance.getMappingsMapperCompat().C9.s(this.I);
    }

    public ResourceLocation(Object object) {
        super(object);
    }
}

