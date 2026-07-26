package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;

public class MBlockStateContainerBridge
extends Mapping {
    private final MappingMethod u;
    private final MappingField I;

    public Object V(Object object) {
        return this.I.getObject(object);
    }

    public Object N(Object object, Object object2) {
        return this.u.L(object, object2);
    }

    public MBlockStateContainerBridge() {
        super(MappedClasses.uF);
        Class[] classArray = new Class[]{MappedClasses.zE};
        Class clazz = MappedClasses.uC;
        boolean bl = true;
        String string = "getOrUpdate";
        MBlockStateContainerBridge mBlockStateContainerBridge = this;
        this.u = this.Y(string, bl, clazz, classArray);
        Class clazz2 = MappedClasses.lu;
        boolean bl2 = true;
        String string2 = "textureView";
        MBlockStateContainerBridge mBlockStateContainerBridge2 = this;
        this.I = this.J(string2, bl2, clazz2);
    }
}

