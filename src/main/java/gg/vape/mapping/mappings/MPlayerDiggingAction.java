package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MPlayerDiggingAction
extends Mapping {
    private MappingField Y;
    private static final String b = "RELEASE_USE_ITEM";

    public MPlayerDiggingAction() {
        super(MappedClasses.FL);
        Class clazz = MappedClasses.FL;
        boolean bl = true;
        String string = b;
        MPlayerDiggingAction mPlayerDiggingAction = this;
        this.Y = this.registerStaticField(string, bl, clazz);
    }

    public Object l() {
        return this.Y.getObject(null);
    }
}

