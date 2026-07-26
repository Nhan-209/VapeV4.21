package gg.vape.mapping.mappings;

import gg.vape.asm.helper.DescUtils;
import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MPointOfView
extends Mapping {
    private MappingMethod n;
    private static final String b = "values";

    public Object[] M() {
        return this.n.invokeObjectArray(null, new Object[0]);
    }

    public MPointOfView() {
        super(MappedClasses.ZR);
        Class[] classArray = new Class[]{};
        Class<?> clazz = DescUtils.getArrayType(MappedClasses.ZR);
        boolean bl = true;
        String string = b;
        MPointOfView mPointOfView = this;
        this.n = this.x(string, bl, clazz, classArray);
    }
}

