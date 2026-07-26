package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MDirectionVector
extends Mapping {
    private static final String b = "POSITIVE";
    private final MappingField H;

    public static Object U(MDirectionVector mDirectionVector) {
        return mDirectionVector.W();
    }

    public MDirectionVector() {
        super(MappedClasses.Vy);
        Class clazz = MappedClasses.Vy;
        boolean bl = true;
        String string = b;
        MDirectionVector mDirectionVector = this;
        this.H = this.u(string, bl, clazz);
    }

    private Object W() {
        return this.H.getObject(null);
    }
}

