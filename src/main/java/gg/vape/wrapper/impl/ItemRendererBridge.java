package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class ItemRendererBridge
extends Wrapper {
    public static ItemRendererBridge G() {
        return new ItemRendererBridge(ItemRendererBridge.c.getMappingsMapperCompat().o.V());
    }

    public ItemRendererBridge(Object object) {
        super(object);
    }
}

