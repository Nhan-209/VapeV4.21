package gg.vape.wrapper.impl;

public class ItemCameraTransformLeaf
extends ItemCameraTransformIntermediate {
    public ItemCameraTransformSubtypeValue C() {
        return new ItemCameraTransformSubtypeValue(ItemCameraTransformLeaf.vapeInstance.getMappingsMapperCompat().C.R(this.I));
    }

    public ItemCameraTransformLeaf(Object object) {
        super(object);
    }
}

