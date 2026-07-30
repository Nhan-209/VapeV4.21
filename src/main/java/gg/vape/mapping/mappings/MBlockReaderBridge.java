package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MBlockReaderBridge
extends Mapping {
    private final MappingMethod X;
    private static final String b = "getBlockState";

    public Object n(Object object, Object object2) {
        return this.X.invokeObject(object, object2);
    }

    public MBlockReaderBridge() {
        super(MappedClasses.zJ);
        Class[] classArray = new Class[]{MappedClasses.lf};
        Class clazz = MappedClasses.Zl;
        boolean bl = true;
        String string = b;
        MBlockReaderBridge mBlockReaderBridge = this;
        this.X = this.Y(string, bl, clazz, classArray);
    }
}

