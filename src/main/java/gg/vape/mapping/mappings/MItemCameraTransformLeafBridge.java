package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MItemCameraTransformLeafBridge
extends Mapping {
    private static final String b = "key";
    private MappingField T;

    public Object R(Object object) {
        return this.T.getObject(object);
    }

    public MItemCameraTransformLeafBridge() {
        super(MappedClasses.zo);
        Class clazz = MappedClasses.qC;
        boolean bl = true;
        String string = b;
        MItemCameraTransformLeafBridge mItemCameraTransformLeafBridge = this;
        this.T = this.J(string, bl, clazz);
    }
}

