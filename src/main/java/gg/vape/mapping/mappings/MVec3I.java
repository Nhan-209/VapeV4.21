package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;

public class MVec3I
extends Mapping {
    private final MappingField S;
    private final MappingField U;
    private static final String b = "<init>";
    private final MappingField C;
    private final MappingMethod h;

    public static MappingMethod Y(MVec3I mVec3I) {
        return mVec3I.h;
    }

    public MVec3I() {
        super(MappedClasses.Vr);
        Class[] classArray = new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE};
        Class<Void> clazz = Void.TYPE;
        boolean bl = false;
        String string = b;
        MVec3I mVec3I = this;
        this.h = this.Y(string, bl, clazz, classArray);
        Class<Integer> clazz2 = Integer.TYPE;
        boolean bl2 = true;
        String string2 = "x";
        MVec3I mVec3I2 = this;
        this.S = this.J(string2, bl2, clazz2);
        Class<Integer> clazz3 = Integer.TYPE;
        boolean bl3 = true;
        String string3 = "y";
        MVec3I mVec3I3 = this;
        this.U = this.J(string3, bl3, clazz3);
        Class<Integer> clazz4 = Integer.TYPE;
        boolean bl4 = true;
        String string4 = "z";
        MVec3I mVec3I4 = this;
        this.C = this.J(string4, bl4, clazz4);
    }

    public int u(Object object) {
        return this.S.getInt(object);
    }

    public int O(Object object) {
        return this.C.getInt(object);
    }

    public int z(Object object) {
        return this.U.getInt(object);
    }
}

