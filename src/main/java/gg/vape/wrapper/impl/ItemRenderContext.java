package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class ItemRenderContext
extends Wrapper {
    public static ItemRenderContext u(boolean bl, ItemStack itemStack) {
        return new ItemRenderContext(ItemRenderContext.vapeInstance.getMappingsMapperCompat().J.G(bl, itemStack.getObject()));
    }

    public static ItemRenderContext M() {
        return new ItemRenderContext(ItemRenderContext.vapeInstance.getMappingsMapperCompat().J.N());
    }

    public ItemRenderContext(Object object) {
        super(object);
    }

    public static ItemRenderContext X() {
        return new ItemRenderContext(ItemRenderContext.vapeInstance.getMappingsMapperCompat().J.q());
    }
}

