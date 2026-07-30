package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class EnchantmentHelperBridge
extends Wrapper {
    public EnchantmentHelperBridge(Object object) {
        super(object);
    }

    public static EnchantmentRegistryAccess i() {
        return new EnchantmentRegistryAccess(EnchantmentHelperBridge.vapeInstance.getMappingsMapperCompat().q4.l());
    }
}

