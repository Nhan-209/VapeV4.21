package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

import java.util.Optional;

public class RegistryLookup
extends Wrapper {
    public Optional<Object> C(ResourceKey jy_12) {
        return RegistryLookup.vapeInstance.getMappingsMapperCompat().DO.W(this.getObject(), jy_12.getObject());
    }

    public Optional<Object> B(ResourceKey jy_12) {
        return RegistryLookup.vapeInstance.getMappingsMapperCompat().DO.f(this.getObject(), jy_12.getObject());
    }

    public Object w(ResourceKey jy_12) {
        return RegistryLookup.vapeInstance.getMappingsMapperCompat().DO.Y(this.getObject(), jy_12.getObject());
    }

    public RegistryLookup(Object object) {
        super(object);
    }
}

