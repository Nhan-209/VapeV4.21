package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MEntityEquipmentSlotHolder
extends Mapping {
    private static final String b = "slot";
    private MappingField D;

    public static Object S(MEntityEquipmentSlotHolder mEntityEquipmentSlotHolder, Object object) {
        return mEntityEquipmentSlotHolder.c(object);
    }

    public MEntityEquipmentSlotHolder() {
        super(MappedClasses.YW);
        Class clazz = MappedClasses.FY;
        boolean bl = true;
        String string = b;
        MEntityEquipmentSlotHolder mEntityEquipmentSlotHolder = this;
        this.D = this.J(string, bl, clazz);
    }

    private Object c(Object object) {
        return this.D.getObject(object);
    }
}

