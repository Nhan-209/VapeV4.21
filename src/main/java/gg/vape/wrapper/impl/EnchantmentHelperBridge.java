package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.EnchantmentRegistryAccess;

public class EnchantmentHelperBridge
extends Wrapper {
    public EnchantmentHelperBridge(Object object) {
        super(object);
    }

    public static EnchantmentRegistryAccess i() {
        return new EnchantmentRegistryAccess(EnchantmentHelperBridge.c.getMappingsMapperCompat().q4.l());
    }
}

