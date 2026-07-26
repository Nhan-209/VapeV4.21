package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.ItemCameraTransformIntermediate;
import gg.vape.wrapper.impl.ItemCameraTransformSubtypeValue;

public class ItemCameraTransformLeaf
extends ItemCameraTransformIntermediate {
    public ItemCameraTransformSubtypeValue C() {
        return new ItemCameraTransformSubtypeValue(ItemCameraTransformLeaf.c.getMappingsMapperCompat().C.R(this.I));
    }

    public ItemCameraTransformLeaf(Object object) {
        super(object);
    }
}

