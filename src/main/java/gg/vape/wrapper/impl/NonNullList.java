package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MNonNullList;
import gg.vape.wrapper.Wrapper;

public class NonNullList
extends Wrapper {
    public NonNullList(Object object) {
        super(object);
    }

    public static NonNullList F() {
        return new NonNullList(MNonNullList.f(NonNullList.vapeInstance.getMappingsMapperCompat().DR));
    }
}

