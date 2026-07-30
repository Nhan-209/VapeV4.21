package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class OpenedChestTypeSentinel
extends Wrapper {
    public OpenedChestTypeSentinel(Object object) {
        super(object);
    }

    public static OpenedChestTypeSentinel Y() {
        return new OpenedChestTypeSentinel(OpenedChestTypeSentinel.vapeInstance.getMappingsMapperCompat().CD.q());
    }
}

