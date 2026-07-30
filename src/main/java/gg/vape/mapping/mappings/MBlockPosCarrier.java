package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MBlockPosCarrier
extends Mapping {
    private static int[] j;
    private MappingMethod w;
    private static final String b;

    public MBlockPosCarrier() {
        super(MappedClasses.uD);
        Class[] classArray = new Class[]{};
        Class clazz = MappedClasses.lf;
        boolean bl = true;
        String string = b;
        MBlockPosCarrier mBlockPosCarrier = this;
        this.w = this.Y(string, bl, clazz, classArray);
    }

    static {
        MBlockPosCarrier.p(new int[5]);
        b = "getPos";
    }

    private Object A(Object object) {
        return this.w.invokeObject(object, new Object[0]);
    }

    public static Object Y(MBlockPosCarrier mBlockPosCarrier, Object object) {
        return mBlockPosCarrier.A(object);
    }

    public static int[] S() {
        return j;
    }

    public static void p(int[] nArray) {
        j = nArray;
    }
}

