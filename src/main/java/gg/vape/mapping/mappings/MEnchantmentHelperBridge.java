package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MEnchantmentHelperBridge
extends Mapping {
    private final MappingMethod F;
    private static final String b;
    private static int D;

    public MEnchantmentHelperBridge() {
        this(MEnchantmentHelperBridge.T());
    }

    private MEnchantmentHelperBridge(int n) {
        super(MappedClasses.qV);
        int n2 = n;
        Class[] classArray = new Class[]{};
        Class clazz = MappedClasses.zi;
        boolean bl = true;
        String string = b;
        MEnchantmentHelperBridge mEnchantmentHelperBridge = this;
        this.F = this.registerStaticMethod(string, bl, clazz, classArray);
    }

    public static int Y() {
        int n = MEnchantmentHelperBridge.T();
        return 41;
    }

    public Object l() {
        return this.F.invokeObject(null, new Object[0]);
    }

    public static void F(int n) {
        D = n;
    }

    static {
        MEnchantmentHelperBridge.F(0);
        b = "createLookup";
    }

    public static int T() {
        return D;
    }

}

