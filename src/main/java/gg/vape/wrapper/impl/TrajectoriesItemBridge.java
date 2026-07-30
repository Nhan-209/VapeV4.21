package gg.vape.wrapper.impl;

public class TrajectoriesItemBridge
extends Item {
    public static boolean P(ItemStack itemStack) {
        return TrajectoriesItemBridge.vapeInstance.getMappingsMapperCompat().RX.g(itemStack.getObject());
    }

    public TrajectoriesItemBridge(Object object) {
        super(object);
    }
}

