package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import java.util.List;

public class MInventoryListBridge
extends Mapping {
    private MappingField c;
    private MappingField G;

    public static Object j(MInventoryListBridge mInventoryListBridge) {
        return mInventoryListBridge.O();
    }

    public static List Y(MInventoryListBridge mInventoryListBridge, Object object) {
        return mInventoryListBridge.H(object);
    }

    private List H(Object object) {
        return (List)this.G.getObject(object);
    }

    public MInventoryListBridge() {
        super(MappedClasses.Y_);
        Class clazz = MappedClasses.Y_;
        boolean bl = true;
        String string = "ARMOR";
        MInventoryListBridge mInventoryListBridge = this;
        this.c = this.u(string, bl, clazz);
        Class<List> clazz2 = List.class;
        boolean bl2 = true;
        String string2 = "slots";
        MInventoryListBridge mInventoryListBridge2 = this;
        this.G = this.J(string2, bl2, clazz2);
    }

    private Object O() {
        return this.c.getObject(null);
    }
}

