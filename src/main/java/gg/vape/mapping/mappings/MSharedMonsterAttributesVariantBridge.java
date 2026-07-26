package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MSharedMonsterAttributesVariantBridge
extends Mapping {
    private MappingField c;
    private MappingField U;

    public MSharedMonsterAttributesVariantBridge() {
        super(MappedClasses.Vx);
        Class clazz = MappedClasses.Vx;
        boolean bl = true;
        String string = "NONE";
        MSharedMonsterAttributesVariantBridge mSharedMonsterAttributesVariantBridge = this;
        this.c = this.u(string, bl, clazz);
        Class clazz2 = MappedClasses.Vx;
        boolean bl2 = true;
        String string2 = "ALL";
        MSharedMonsterAttributesVariantBridge mSharedMonsterAttributesVariantBridge2 = this;
        this.U = this.u(string2, bl2, clazz2);
    }

    public Object D() {
        return this.c.getObject(null);
    }

    public Object h() {
        return this.U.getObject(null);
    }
}

