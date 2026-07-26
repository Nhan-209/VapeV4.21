package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MDefaultVertexFormats
extends Mapping {
    private static final String b = "POSITION_COLOR";
    private final MappingField p;

    public static Object T(MDefaultVertexFormats mDefaultVertexFormats) {
        return mDefaultVertexFormats.p();
    }

    private Object p() {
        return this.p.getObject(null);
    }

    public MDefaultVertexFormats() {
        super(MappedClasses.Yo);
        Class clazz = MappedClasses.zG;
        boolean bl = true;
        String string = b;
        MDefaultVertexFormats mDefaultVertexFormats = this;
        this.p = this.u(string, bl, clazz);
    }
}

