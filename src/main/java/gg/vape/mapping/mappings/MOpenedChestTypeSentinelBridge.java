package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MOpenedChestTypeSentinelBridge
extends Mapping {
    private final MappingField i;
    private static final String b = "BASIC";

    public MOpenedChestTypeSentinelBridge() {
        super(MappedClasses.q1);
        Class clazz = MappedClasses.q1;
        boolean bl = true;
        String string = b;
        MOpenedChestTypeSentinelBridge mOpenedChestTypeSentinelBridge = this;
        this.i = this.u(string, bl, clazz);
    }

    public Object q() {
        return this.i.getObject(null);
    }
}

