package gg.vape.wrapper.impl;

public class EnchantmentRegistryAccess
extends RegistryLookup {
    public EnchantmentRegistryAccess(Object object) {
        super(object);
    }

    public EnchantmentRegistry r(ResourceKey resourceKey) {
        return new EnchantmentRegistry(EnchantmentRegistryAccess.vapeInstance.getMappings().CT.m(this.getObject(), resourceKey.getObject()));
    }
}

