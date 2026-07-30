package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MBiomeProviderBridge
extends Mapping {
    private static final String b = "getAllEffects";
    private MappingMethod U;

    public static Iterable D(MBiomeProviderBridge mBiomeProviderBridge, Object object) {
        return mBiomeProviderBridge.D(object);
    }

    public MBiomeProviderBridge() {
        super(MappedClasses.uV);
        Class[] classArray = new Class[]{};
        Class<Iterable> clazz = Iterable.class;
        boolean bl = true;
        String string = b;
        MBiomeProviderBridge mBiomeProviderBridge = this;
        this.U = this.Y(string, bl, clazz, classArray);
    }

    private Iterable D(Object object) {
        return (Iterable)this.U.invokeObject(object, new Object[0]);
    }
}

