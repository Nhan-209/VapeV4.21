package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class ChestTypeHolder
extends Wrapper {
    public static ItemCameraTransformSubtypeValue h() {
        return new ItemCameraTransformSubtypeValue(ChestTypeHolder.vapeInstance.getMappingsMapperCompat().L.H());
    }

    public static ItemCameraTransformSubtypeValue d() {
        return new ItemCameraTransformSubtypeValue(ChestTypeHolder.vapeInstance.getMappingsMapperCompat().L.g());
    }

    public ChestTypeHolder(Object object) {
        super(object);
    }
}

