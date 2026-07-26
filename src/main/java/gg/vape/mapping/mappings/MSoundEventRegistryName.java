package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MSoundEventRegistryName
extends Mapping {
    private static final String b = "name";
    private MappingField M;

    public static Object P(MSoundEventRegistryName mSoundEventRegistryName, Object object) {
        return mSoundEventRegistryName.r(object);
    }

    private Object r(Object object) {
        return this.M.getObject(object);
    }

    public MSoundEventRegistryName() {
        super(MappedClasses.V4);
        Class clazz = MappedClasses.zC;
        boolean bl = true;
        String string = b;
        MSoundEventRegistryName mSoundEventRegistryName = this;
        this.M = this.J(string, bl, clazz);
    }
}

