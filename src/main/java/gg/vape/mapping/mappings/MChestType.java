package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MChestType
extends Mapping {
    private final MappingField V;
    private static final String b = "FALLDAMAGE_RESETTING";

    public MChestType() {
        super(MappedClasses.lN);
        Class clazz = MappedClasses.lN;
        boolean bl = true;
        String string = b;
        MChestType mChestType = this;
        this.V = this.registerStaticField(string, bl, clazz);
    }

    public Object u() {
        return this.V.getObject(null);
    }
}

