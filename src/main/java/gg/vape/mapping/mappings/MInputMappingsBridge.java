package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MInputMappingsBridge
extends Mapping {
    private static final String b = "getInputByCode";
    private MappingMethod C;

    private Object p(int n, int n2) {
        return this.C.L(null, n, n2);
    }

    public static Object e(MInputMappingsBridge mInputMappingsBridge, int n, int n2) {
        return mInputMappingsBridge.p(n, n2);
    }

    public static Object a(MInputMappingsBridge mInputMappingsBridge, Object object) {
        return mInputMappingsBridge.e(object);
    }

    public MInputMappingsBridge() {
        super(MappedClasses.Q);
        Class[] classArray = new Class[]{Integer.TYPE, Integer.TYPE};
        Class clazz = MappedClasses.zp;
        boolean bl = true;
        String string = b;
        MInputMappingsBridge mInputMappingsBridge = this;
        this.C = this.x(string, bl, clazz, classArray);
    }

    private Object e(Object object) {
        return this.C.L(null, object);
    }
}

