package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MEmptyItemAttributeModifiers
extends Mapping {
    private final MappingMethod c;
    private static final String b = "create";

    public static Object I(MEmptyItemAttributeModifiers mEmptyItemAttributeModifiers) {
        return mEmptyItemAttributeModifiers.z();
    }

    private Object z() {
        return this.c.L(null, new Object[0]);
    }

    public MEmptyItemAttributeModifiers() {
        super(MappedClasses.V1);
        Class[] classArray = new Class[]{};
        Class clazz = MappedClasses.V1;
        boolean bl = false;
        String string = b;
        MEmptyItemAttributeModifiers mEmptyItemAttributeModifiers = this;
        this.c = this.x(string, bl, clazz, classArray);
    }
}

