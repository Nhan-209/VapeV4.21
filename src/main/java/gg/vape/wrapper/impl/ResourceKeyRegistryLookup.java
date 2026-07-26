package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.EnchantmentHolder;
import gg.vape.wrapper.impl.ResourceKey;
import java.util.Optional;

public class ResourceKeyRegistryLookup
extends Wrapper {
    public ResourceKeyRegistryLookup(Object object) {
        super(object);
    }

    public EnchantmentHolder x(ResourceKey jy_12) {
        return new EnchantmentHolder(ResourceKeyRegistryLookup.c.getMappingsMapperCompat().hl.N(this.getObject(), jy_12.getObject()));
    }

    public Optional<Object> U(ResourceKey jy_12) {
        return ResourceKeyRegistryLookup.c.getMappingsMapperCompat().hl.W(this.getObject(), jy_12.getObject());
    }
}

