package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class ItemRendererBridge
extends Wrapper {
    public static ItemRendererBridge G() {
        return new ItemRendererBridge(ItemRendererBridge.vapeInstance.getMappingsMapperCompat().o.V());
    }

    public ItemRendererBridge(Object object) {
        super(object);
    }
}

