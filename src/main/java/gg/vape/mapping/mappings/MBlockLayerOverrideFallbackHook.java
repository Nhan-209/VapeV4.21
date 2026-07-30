package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MBlockLayerOverrideFallbackHook
extends Mapping {
    private static int n;
    private static final String b;
    public final MappingMethod Z;

    public static void K(int n) {
        MBlockLayerOverrideFallbackHook.n = n;
    }


    public static int m() {
        int n = MBlockLayerOverrideFallbackHook.A();
        return 41;
    }

    public MBlockLayerOverrideFallbackHook() {
        this(MBlockLayerOverrideFallbackHook.A());
    }

    private MBlockLayerOverrideFallbackHook(int n) {
        super(MappedClasses.lA);
        int n2 = n;
        Class[] classArray = new Class[]{};
        Class<Void> clazz = Void.TYPE;
        boolean bl = false;
        String string = b;
        MBlockLayerOverrideFallbackHook mBlockLayerOverrideFallbackHook = this;
        this.Z = this.registerStaticMethod(string, bl, clazz, classArray);
    }

    static {
        MBlockLayerOverrideFallbackHook.K(0);
        b = "gc";
    }

    public static int A() {
        return n;
    }
}

