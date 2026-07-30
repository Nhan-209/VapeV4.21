package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;

public class MDirectionAxis
extends Mapping {
    private final MappingMethod Q;
    private final MappingField v;
    private final MappingField i;
    private static final String b = "choose";

    public MDirectionAxis() {
        super(MappedClasses.u9);
        Class[] classArray = new Class[]{Double.TYPE, Double.TYPE, Double.TYPE};
        Class<Double> clazz = Double.TYPE;
        boolean bl = true;
        String string = b;
        MDirectionAxis mDirectionAxis = this;
        this.Q = this.Y(string, bl, clazz, classArray);
        Class clazz2 = MappedClasses.u9;
        boolean bl2 = true;
        String string2 = "X";
        MDirectionAxis mDirectionAxis2 = this;
        this.v = this.registerStaticField(string2, bl2, clazz2);
        Class clazz3 = MappedClasses.u9;
        boolean bl3 = true;
        String string3 = "Y";
        MDirectionAxis mDirectionAxis3 = this;
        this.i = this.registerStaticField(string3, bl3, clazz3);
    }

    private Object C() {
        return this.i.getObject(null);
    }

    private Object g() {
        return this.v.getObject(null);
    }

    public static Object G(MDirectionAxis mDirectionAxis) {
        return mDirectionAxis.g();
    }

    public static Object h(MDirectionAxis mDirectionAxis) {
        return mDirectionAxis.C();
    }

    public double T(Object object, double d, double d2, double d3) {
        return this.Q.invokeDouble(object, d, d2, d3);
    }
}

