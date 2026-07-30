package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class RegistryAccess
extends Wrapper {
    public RegistryAccess(Object object) {
        super(object);
    }

    public Registry getFluidState(ResourceKey jy_12) {
        return new Registry(RegistryAccess.vapeInstance.getMappingsMapperCompat().qs.H(this.I, jy_12.getObject()));
    }
}

