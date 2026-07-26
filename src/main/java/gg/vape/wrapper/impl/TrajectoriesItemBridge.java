package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;

public class TrajectoriesItemBridge
extends Item {
    public static boolean P(ItemStack itemStack) {
        return TrajectoriesItemBridge.c.getMappingsMapperCompat().RX.g(itemStack.getObject());
    }

    public TrajectoriesItemBridge(Object object) {
        super(object);
    }
}

