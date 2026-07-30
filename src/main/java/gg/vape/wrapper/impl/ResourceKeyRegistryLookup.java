package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

import java.util.Optional;

public class ResourceKeyRegistryLookup
extends Wrapper {
    public ResourceKeyRegistryLookup(Object object) {
        super(object);
    }

    public EnchantmentHolder x(ResourceKey jy_12) {
        return new EnchantmentHolder(ResourceKeyRegistryLookup.vapeInstance.getMappingsMapperCompat().hl.N(this.getObject(), jy_12.getObject()));
    }

    public Optional<Object> U(ResourceKey jy_12) {
        return ResourceKeyRegistryLookup.vapeInstance.getMappingsMapperCompat().hl.W(this.getObject(), jy_12.getObject());
    }
}

