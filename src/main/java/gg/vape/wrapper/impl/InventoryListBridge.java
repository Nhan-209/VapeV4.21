package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MInventoryListBridge;
import gg.vape.wrapper.Wrapper;
import java.util.List;

public class InventoryListBridge
extends Wrapper {
    public List p() {
        return MInventoryListBridge.Y(InventoryListBridge.vapeInstance.getMappingsMapperCompat().DL, this.I);
    }

    public InventoryListBridge(Object object) {
        super(object);
    }

    public static InventoryListBridge u() {
        return new InventoryListBridge(MInventoryListBridge.j(InventoryListBridge.vapeInstance.getMappingsMapperCompat().DL));
    }
}

