package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MSoundEvent
extends Mapping {
    private static final String b = "name";
    private MappingField B;

    public MSoundEvent() {
        super(MappedClasses.Y6);
        Class clazz = MappedClasses.zC;
        boolean bl = true;
        String string = b;
        MSoundEvent mSoundEvent = this;
        this.B = this.J(string, bl, clazz);
    }

    public Object q(Object object) {
        return this.B.getObject(object);
    }
}

