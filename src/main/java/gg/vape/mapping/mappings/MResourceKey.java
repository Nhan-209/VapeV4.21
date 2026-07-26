package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingFieldBuilder;
import gg.vape.wrapper.impl.ForgeVersion;

public class MResourceKey
extends Mapping {
    private MappingField c;

    public MResourceKey() {
        super(MappedClasses.qB);
        Class clazz = MappedClasses.zC;
        String string = "location";
        MResourceKey mResourceKey = this;
        this.c = ((MappingFieldBuilder)this.T(string, clazz).A(ForgeVersion.MC_1_21_11.n(), "identifier")).z();
    }

    public static Object J(MResourceKey mResourceKey, Object object) {
        return mResourceKey.U(object);
    }

    private Object U(Object object) {
        return this.c.getObject(object);
    }
}

