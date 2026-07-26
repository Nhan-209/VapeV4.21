package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MInputMappingsInput
extends Mapping {
    private MappingField e;
    private static final String b = "keyCode";

    public MInputMappingsInput() {
        super(MappedClasses.zp);
        Class<Integer> clazz = Integer.TYPE;
        boolean bl = true;
        String string = b;
        MInputMappingsInput mInputMappingsInput = this;
        this.e = this.J(string, bl, clazz);
    }

    private int s(Object object) {
        return this.e.getInt(object);
    }

    public static int B(MInputMappingsInput mInputMappingsInput, Object object) {
        return mInputMappingsInput.s(object);
    }
}

