package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MEmptyItemAttributeModifiers;

public class EmptyItemAttributeModifiers
extends ItemAttributeModifiers {
    public static EmptyItemAttributeModifiers l() {
        return new EmptyItemAttributeModifiers(MEmptyItemAttributeModifiers.I(EmptyItemAttributeModifiers.vapeInstance.getMappingsMapperCompat().Rw));
    }

    public EmptyItemAttributeModifiers(Object object) {
        super(object);
    }
}

