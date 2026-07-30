package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MResourceKeyEnchantmentBridge;
import gg.vape.wrapper.Wrapper;

public class ResourceKeyEnchantmentBridge
extends Wrapper {
    public static ResourceKey h() {
        return new ResourceKey(MResourceKeyEnchantmentBridge.e(ResourceKeyEnchantmentBridge.vapeInstance.getMappingsMapperCompat().D1));
    }

    public ResourceKeyEnchantmentBridge(Object object) {
        super(object);
    }
}

