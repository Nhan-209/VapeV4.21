package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MMappedFieldSingletonWrapper
extends Mapping {
    private final MappingField i;
    private static final String b = "HUMANOID_ARMOR";

    public MMappedFieldSingletonWrapper() {
        super(MappedClasses.Vf);
        Class clazz = MappedClasses.Vf;
        boolean bl = true;
        String string = b;
        MMappedFieldSingletonWrapper mMappedFieldSingletonWrapper = this;
        this.i = this.u(string, bl, clazz);
    }

    public static MappingField W(MMappedFieldSingletonWrapper mMappedFieldSingletonWrapper) {
        return mMappedFieldSingletonWrapper.i;
    }
}

