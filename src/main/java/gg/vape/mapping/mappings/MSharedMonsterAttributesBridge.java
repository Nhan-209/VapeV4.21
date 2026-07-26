package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MSharedMonsterAttributesBridge
extends Mapping {
    private MappingField v;
    private MappingField f;

    public MSharedMonsterAttributesBridge() {
        super(MappedClasses.Y8);
        Class clazz = MappedClasses.Y8;
        boolean bl = true;
        String string = "NORMAL";
        MSharedMonsterAttributesBridge mSharedMonsterAttributesBridge = this;
        this.v = this.u(string, bl, clazz);
        Class clazz2 = MappedClasses.Y8;
        boolean bl2 = true;
        String string2 = "SEE_THROUGH";
        MSharedMonsterAttributesBridge mSharedMonsterAttributesBridge2 = this;
        this.f = this.u(string2, bl2, clazz2);
    }

    public Object m() {
        return this.v.getObject(null);
    }

    public Object K() {
        return this.f.getObject(null);
    }
}

