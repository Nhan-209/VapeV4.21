package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.utils.datas.BlockData;
import gg.vape.wrapper.impl.ForgeVersion;

public class MVector3f
extends Mapping {
    private MappingMethod q;
    private MappingField m;
    private MappingField v;
    private MappingField S;
    private MappingMethod T;


    public float e(Object object) {
        return this.m.getFloat(object);
    }

    public Object O(Object object, float f) {
        return this.T.invokeObject(object, Float.valueOf(f));
    }

    public static Object u(MVector3f mVector3f, float f, float f2, float f3) {
        return mVector3f.g(f, f2, f3);
    }

    public MVector3f() {
        super(MappedClasses.qb);
        Class[] classArray = new Class[]{Float.TYPE, Float.TYPE, Float.TYPE};
        Class<Void> clazz = Void.TYPE;
        boolean bl = false;
        String string = "<init>";
        MVector3f mVector3f = this;
        this.q = this.Y(string, bl, clazz, classArray);
        if (BlockData.W() != null) {
            if (ForgeVersion.MC_1_20_6.v()) {
                Class[] classArray2 = new Class[]{Float.TYPE};
                Class clazz2 = MappedClasses.qI;
                boolean bl2 = true;
                String string2 = "rotationDegrees";
                MVector3f mVector3f2 = this;
                this.T = this.Y(string2, bl2, clazz2, classArray2);
            }
            Class<Float> clazz3 = Float.TYPE;
            boolean bl3 = true;
            String string3 = "x";
            MVector3f mVector3f3 = this;
            this.v = this.J(string3, bl3, clazz3);
            Class<Float> clazz4 = Float.TYPE;
            boolean bl4 = true;
            String string4 = "y";
            MVector3f mVector3f4 = this;
            this.S = this.J(string4, bl4, clazz4);
            Class<Float> clazz5 = Float.TYPE;
            boolean bl5 = true;
            String string5 = "z";
            MVector3f mVector3f5 = this;
            this.m = this.J(string5, bl5, clazz5);
            return;
        }
    }

    public float L(Object object) {
        return this.S.getFloat(object);
    }

    public float X(Object object) {
        return this.v.getFloat(object);
    }

    private Object g(float f, float f2, float f3) {
        return this.q.newInstance(Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3));
    }
}

