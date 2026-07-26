package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class MISelectionContext
extends Mapping {
    private static final String b;
    private static boolean i;
    private final MappingMethod z;

    public static void Y(boolean bl) {
        i = bl;
    }

    public static boolean P() {
        return i;
    }

    public static boolean a() {
        boolean bl = MISelectionContext.P();
        return false;
    }

    public Object v(Object object) {
        return this.z.O(object);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public MISelectionContext() {
        this(MISelectionContext.P());
    }

    private MISelectionContext(boolean bl) {
        super(MappedClasses.qg);
        Class[] classArray = new Class[]{MappedClasses.zc};
        Class clazz = MappedClasses.qg;
        boolean bl2 = true;
        String string = b;
        MISelectionContext mISelectionContext = this;
        this.z = this.x(string, bl2, clazz, classArray);
        boolean bl3 = bl;
    }

    static {
        MISelectionContext.Y(true);
        b = "forEntity";
    }
}

