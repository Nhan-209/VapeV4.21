package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MItemBlock
extends Mapping {
    private MappingField G;
    private static final String b = "storage";

    public Object v(Object object) {
        return this.G.getObject(object);
    }

    public MItemBlock() {
        super(MappedClasses.I);
        Class clazz = MappedClasses.zd;
        boolean bl = true;
        String string = b;
        MItemBlock mItemBlock = this;
        this.G = this.J(string, bl, clazz);
    }
}

