package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MFluidState
extends Mapping {
    private final MappingMethod s;
    private static final String b = "getHasNoSky";

    public MFluidState() {
        super(MappedClasses.DJ);
        Class[] classArray = new Class[]{};
        Class<Boolean> clazz = Boolean.TYPE;
        boolean bl = true;
        String string = b;
        MFluidState mFluidState = this;
        this.s = this.Y(string, bl, clazz, classArray);
    }

    public boolean t(Object object) {
        return this.s.e(object, new Object[0]);
    }
}

