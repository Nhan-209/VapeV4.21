package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MEntitySize
extends Mapping {
    private MappingField r;
    private MappingField c;

    private float C(Object object) {
        return this.r.getFloat(object);
    }

    public static float y(MEntitySize mEntitySize, Object object) {
        return mEntitySize.m(object);
    }

    public static float A(MEntitySize mEntitySize, Object object) {
        return mEntitySize.C(object);
    }

    public MEntitySize() {
        super(MappedClasses.Ve);
        Class<Float> clazz = Float.TYPE;
        boolean bl = true;
        String string = "width";
        MEntitySize mEntitySize = this;
        this.r = this.J(string, bl, clazz);
        Class<Float> clazz2 = Float.TYPE;
        boolean bl2 = true;
        String string2 = "height";
        MEntitySize mEntitySize2 = this;
        this.c = this.J(string2, bl2, clazz2);
    }

    private float m(Object object) {
        return this.c.getFloat(object);
    }
}

