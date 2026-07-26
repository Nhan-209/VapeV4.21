package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.EnchantmentRegistry;
import gg.vape.wrapper.impl.RegistryLookup;
import gg.vape.wrapper.impl.ResourceKey;

public class EnchantmentRegistryAccess
extends RegistryLookup {
    public EnchantmentRegistryAccess(Object object) {
        super(object);
    }

    public EnchantmentRegistry r(ResourceKey resourceKey) {
        return new EnchantmentRegistry(EnchantmentRegistryAccess.c.getMappings().CT.m(this.getObject(), resourceKey.getObject()));
    }
}

