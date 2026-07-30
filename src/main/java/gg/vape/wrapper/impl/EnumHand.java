package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class EnumHand
extends Wrapper {
    public static EnumHand M() {
        return new EnumHand(EnumHand.vapeInstance.getMappingsMapperCompat().DX.L());
    }

    public static EnumHand p() {
        return new EnumHand(EnumHand.vapeInstance.getMappingsMapperCompat().DX.c());
    }

    public EnumHand(Object object) {
        super(object);
    }
}

