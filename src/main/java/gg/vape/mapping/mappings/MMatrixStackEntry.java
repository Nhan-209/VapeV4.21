package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MMatrixStackEntry
extends Mapping {
    private static final String b = "matrix";
    private MappingField k;

    private Object A(Object object) {
        return this.k.getObject(object);
    }

    public static Object s(MMatrixStackEntry mMatrixStackEntry, Object object) {
        return mMatrixStackEntry.A(object);
    }

    public MMatrixStackEntry() {
        super(MappedClasses.G);
        Class clazz = MappedClasses.qr;
        boolean bl = true;
        String string = b;
        MMatrixStackEntry mMatrixStackEntry = this;
        this.k = this.J(string, bl, clazz);
    }
}

