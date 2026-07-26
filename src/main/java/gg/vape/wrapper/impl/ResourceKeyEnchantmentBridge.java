package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MResourceKeyEnchantmentBridge;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ResourceKey;

public class ResourceKeyEnchantmentBridge
extends Wrapper {
    public static ResourceKey h() {
        return new ResourceKey(MResourceKeyEnchantmentBridge.e(ResourceKeyEnchantmentBridge.c.getMappingsMapperCompat().D1));
    }

    public ResourceKeyEnchantmentBridge(Object object) {
        super(object);
    }
}

