package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MEnumCreatureAttributeBridge
extends Mapping {
    private final MappingField d;
    private static final String b = "UNDEFINED";

    public MEnumCreatureAttributeBridge() {
        super(MappedClasses.O);
        Class clazz = MappedClasses.O;
        boolean bl = true;
        String string = b;
        MEnumCreatureAttributeBridge mEnumCreatureAttributeBridge = this;
        this.d = this.registerStaticField(string, bl, clazz);
    }

    private Object e() {
        return this.d.getObject(null);
    }

    public static Object V(MEnumCreatureAttributeBridge mEnumCreatureAttributeBridge) {
        return mEnumCreatureAttributeBridge.e();
    }
}

