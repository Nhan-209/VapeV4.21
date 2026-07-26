package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MEmptyItemAttributeModifiers;
import gg.vape.wrapper.impl.ItemAttributeModifiers;

public class EmptyItemAttributeModifiers
extends ItemAttributeModifiers {
    public static EmptyItemAttributeModifiers l() {
        return new EmptyItemAttributeModifiers(MEmptyItemAttributeModifiers.I(EmptyItemAttributeModifiers.c.getMappingsMapperCompat().Rw));
    }

    public EmptyItemAttributeModifiers(Object object) {
        super(object);
    }
}

