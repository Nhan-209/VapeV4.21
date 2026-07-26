package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MRenderItemContext
extends Mapping {
    private static final String b = "GUI";
    private MappingField F;

    public static Object r(MRenderItemContext mRenderItemContext) {
        return mRenderItemContext.r();
    }

    public MRenderItemContext() {
        super(MappedClasses.YK);
        Class clazz = MappedClasses.YK;
        boolean bl = true;
        String string = b;
        MRenderItemContext mRenderItemContext = this;
        this.F = this.u(string, bl, clazz);
    }

    private Object r() {
        return this.F.getObject(null);
    }
}

