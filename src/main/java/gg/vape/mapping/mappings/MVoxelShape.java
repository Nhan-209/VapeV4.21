package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MVoxelShape
extends Mapping {
    private MappingMethod h;
    private MappingMethod s;

    public static boolean v(MVoxelShape mVoxelShape, Object object) {
        return mVoxelShape.r(object);
    }

    public MVoxelShape() {
        super(MappedClasses.la);
        Class[] classArray = new Class[]{};
        Class clazz = MappedClasses.uk;
        boolean bl = true;
        String string = "getBoundingBox";
        MVoxelShape mVoxelShape = this;
        this.s = this.Y(string, bl, clazz, classArray);
        Class[] classArray2 = new Class[]{};
        Class<Boolean> clazz2 = Boolean.TYPE;
        String string2 = "isEmpty";
        MVoxelShape mVoxelShape2 = this;
        this.h = this.u(string2, clazz2, classArray2).s();
    }

    private Object q(Object object) {
        return this.s.L(object, new Object[0]);
    }

    public static Object o(MVoxelShape mVoxelShape, Object object) {
        return mVoxelShape.q(object);
    }

    private boolean r(Object object) {
        return this.h.e(object, new Object[0]);
    }
}

