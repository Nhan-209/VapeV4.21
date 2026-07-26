package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.Registry;
import gg.vape.wrapper.impl.ResourceKey;

public class RegistryAccess
extends Wrapper {
    public RegistryAccess(Object object) {
        super(object);
    }

    public Registry getFluidState(ResourceKey jy_12) {
        return new Registry(RegistryAccess.c.getMappingsMapperCompat().qs.H(this.I, jy_12.getObject()));
    }
}

