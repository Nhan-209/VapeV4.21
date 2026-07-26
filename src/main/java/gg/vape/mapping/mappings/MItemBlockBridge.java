package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MItemBlockBridge
extends Mapping {
    private static final String b = "block";
    private MappingField M;

    public Object t(Object object) {
        return this.M.getObject(object);
    }

    public MItemBlockBridge() {
        super(MappedClasses.Vw);
        Class clazz = MappedClasses.Zk;
        boolean bl = true;
        String string = b;
        MItemBlockBridge mItemBlockBridge = this;
        this.M = this.J(string, bl, clazz);
    }
}

