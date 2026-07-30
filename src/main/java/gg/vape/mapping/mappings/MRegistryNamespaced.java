package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.MappingMethodBuilder;
import gg.vape.wrapper.impl.ForgeVersion;

public class MRegistryNamespaced
extends Mapping {
    private MappingMethod m;

    public MRegistryNamespaced() {
        super(MappedClasses.lz);
        Class[] classArray = new Class[]{Integer.TYPE};
        Class<Object> clazz = Object.class;
        String string = "getByValue";
        MRegistryNamespaced mRegistryNamespaced = this;
        this.m = ((MappingMethodBuilder)((MappingMethodBuilder)this.methodBuilder(string, clazz, classArray).setNameForVersion(ForgeVersion.MC_1_16_5.n(), "byId")).setOwnerClassForVersion(ForgeVersion.MC_1_21_4.n(), MappedClasses.ua)).buildMethod();
    }

    public Object e(Object object, int n) {
        return this.m.invokeObject(object, n);
    }
}

