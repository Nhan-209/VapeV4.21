package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MMinecraftBridge
extends Mapping {
    private static final String b = "blockEntity";
    private MappingField r;

    public MMinecraftBridge() {
        super(MappedClasses.up);
        Class clazz = MappedClasses.ZI;
        boolean bl = true;
        String string = b;
        MMinecraftBridge mMinecraftBridge = this;
        this.r = this.J(string, bl, clazz);
    }

    public Object J(Object object) {
        return this.r.getObject(object);
    }
}

