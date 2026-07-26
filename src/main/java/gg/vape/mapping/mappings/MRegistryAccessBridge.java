package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MRegistryAccessBridge
extends Mapping {
    private final MappingMethod i;
    private static final String b = "lookupOrThrow";

    public Object m(Object object, Object object2) {
        return this.i.L(object, object2);
    }

    public MRegistryAccessBridge() {
        super(MappedClasses.zi);
        Class[] classArray = new Class[]{MappedClasses.qB};
        Class clazz = MappedClasses.l1;
        boolean bl = true;
        String string = b;
        MRegistryAccessBridge mRegistryAccessBridge = this;
        this.i = this.Y(string, bl, clazz, classArray);
    }
}

