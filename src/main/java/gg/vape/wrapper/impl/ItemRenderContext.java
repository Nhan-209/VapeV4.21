package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ItemStack;

public class ItemRenderContext
extends Wrapper {
    public static ItemRenderContext u(boolean bl, ItemStack itemStack) {
        return new ItemRenderContext(ItemRenderContext.c.getMappingsMapperCompat().J.G(bl, itemStack.getObject()));
    }

    public static ItemRenderContext M() {
        return new ItemRenderContext(ItemRenderContext.c.getMappingsMapperCompat().J.N());
    }

    public ItemRenderContext(Object object) {
        super(object);
    }

    public static ItemRenderContext X() {
        return new ItemRenderContext(ItemRenderContext.c.getMappingsMapperCompat().J.q());
    }
}

