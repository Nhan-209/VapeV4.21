package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MEnumWorldBlockLayer
extends Mapping {
    public MappingField d;
    public MappingField H;

    public MEnumWorldBlockLayer() {
        super(MappedClasses.E);
        Class clazz = MappedClasses.E;
        boolean bl = true;
        String string = "TRANSLUCENT";
        MEnumWorldBlockLayer mEnumWorldBlockLayer = this;
        this.H = this.registerStaticField(string, bl, clazz);
        Class clazz2 = MappedClasses.E;
        boolean bl2 = true;
        String string2 = "SOLID";
        MEnumWorldBlockLayer mEnumWorldBlockLayer2 = this;
        this.d = this.registerStaticField(string2, bl2, clazz2);
    }

    private Object P() {
        return this.d.getObject(null);
    }

    private Object j() {
        return this.H.getObject(null);
    }

    public static Object g(MEnumWorldBlockLayer mEnumWorldBlockLayer) {
        return mEnumWorldBlockLayer.P();
    }

    public static Object U(MEnumWorldBlockLayer mEnumWorldBlockLayer) {
        return mEnumWorldBlockLayer.j();
    }
}

