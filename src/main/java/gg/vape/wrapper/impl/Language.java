package gg.vape.wrapper.impl;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.mappings.MLanguage;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class Language
extends Wrapper {
    public Language(Object object) {
        super(object);
    }


    public boolean N() {
        if (ForgeVersion.MC_1_16_5.d() && !MappedClasses.Vi.isInstance(this.getObject())) {
            return false;
        }
        return MLanguage.n(Language.c.getMappingsMapperCompat().D0, this.I);
    }
}

