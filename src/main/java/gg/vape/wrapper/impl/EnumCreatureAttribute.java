package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MEnumCreatureAttributeBridge;
import gg.vape.wrapper.Wrapper;

public class EnumCreatureAttribute
extends Wrapper {

    public static EnumCreatureAttribute R() {
        if (ForgeVersion.MC_1_21_0.d()) {
            return null;
        }
        return new EnumCreatureAttribute(MEnumCreatureAttributeBridge.V(EnumCreatureAttribute.vapeInstance.getMappingsMapperCompat().R0));
    }

    public EnumCreatureAttribute(Object object) {
        super(object);
    }
}

