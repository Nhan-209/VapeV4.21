package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ItemCameraTransformSubtypeValue;

public class ChestTypeHolder
extends Wrapper {
    public static ItemCameraTransformSubtypeValue h() {
        return new ItemCameraTransformSubtypeValue(ChestTypeHolder.c.getMappingsMapperCompat().L.H());
    }

    public static ItemCameraTransformSubtypeValue d() {
        return new ItemCameraTransformSubtypeValue(ChestTypeHolder.c.getMappingsMapperCompat().L.g());
    }

    public ChestTypeHolder(Object object) {
        super(object);
    }
}

