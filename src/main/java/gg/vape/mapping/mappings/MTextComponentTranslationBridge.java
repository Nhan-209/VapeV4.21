package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MTextComponentTranslationBridge
extends Mapping {
    private MappingMethod K;
    private static final String b;
    private static boolean n;


    static {
        MTextComponentTranslationBridge.n(true);
        b = "getModelIdentity";
    }

    public MTextComponentTranslationBridge() {
        super(MappedClasses.zE);
        Class[] classArray = new Class[]{};
        Class<Object> clazz = Object.class;
        boolean bl = true;
        String string = b;
        MTextComponentTranslationBridge mTextComponentTranslationBridge = this;
        this.K = this.Y(string, bl, clazz, classArray);
    }

    public Object J(Object object) {
        return this.K.invokeObject(object, new Object[0]);
    }

    public static boolean C() {
        boolean bl = MTextComponentTranslationBridge.I();
        return !bl;
    }

    public static boolean I() {
        return n;
    }

    public static void n(boolean bl) {
        n = bl;
    }
}

