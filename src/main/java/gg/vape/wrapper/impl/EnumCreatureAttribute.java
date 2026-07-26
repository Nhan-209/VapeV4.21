package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MEnumCreatureAttributeBridge;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class EnumCreatureAttribute
extends Wrapper {
    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static EnumCreatureAttribute R() {
        if (ForgeVersion.MC_1_21_0.d()) {
            return null;
        }
        return new EnumCreatureAttribute(MEnumCreatureAttributeBridge.V(EnumCreatureAttribute.c.getMappingsMapperCompat().R0));
    }

    public EnumCreatureAttribute(Object object) {
        super(object);
    }
}

