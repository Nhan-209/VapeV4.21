package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MEquipmentSlotSet;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.MappedFieldSingletonWrapper;

public class EquipmentSlotSet
extends Wrapper {
    public int d() {
        return MEquipmentSlotSet.e(EquipmentSlotSet.c.getMappingsMapperCompat().Ro, this.I);
    }

    public static EquipmentSlotSet n() {
        return new EquipmentSlotSet(MEquipmentSlotSet.e(EquipmentSlotSet.c.getMappingsMapperCompat().Ro));
    }

    public static EquipmentSlotSet T(int slot) {
        switch (slot) {
            case 0:
                return EquipmentSlotSet.k();
            case 1:
                return EquipmentSlotSet.q();
            case 2:
                return EquipmentSlotSet.o();
            case 3:
                return EquipmentSlotSet.n();
            case 4:
                return EquipmentSlotSet.j();
            case 5:
                return EquipmentSlotSet.U();
            default:
                return null;
        }
    }

    public static EquipmentSlotSet q() {
        return new EquipmentSlotSet(MEquipmentSlotSet.P(EquipmentSlotSet.c.getMappingsMapperCompat().Ro));
    }

    public static EquipmentSlotSet U() {
        return new EquipmentSlotSet(MEquipmentSlotSet.T(EquipmentSlotSet.c.getMappingsMapperCompat().Ro));
    }

    public static EquipmentSlotSet o() {
        return new EquipmentSlotSet(MEquipmentSlotSet.s(EquipmentSlotSet.c.getMappingsMapperCompat().Ro));
    }


    public static EquipmentSlotSet k() {
        return new EquipmentSlotSet(MEquipmentSlotSet.r(EquipmentSlotSet.c.getMappingsMapperCompat().Ro));
    }

    public MappedFieldSingletonWrapper y() {
        return new MappedFieldSingletonWrapper(MEquipmentSlotSet.a(EquipmentSlotSet.c.getMappingsMapperCompat().Ro, this.I));
    }

    public static EquipmentSlotSet j() {
        return new EquipmentSlotSet(MEquipmentSlotSet.t(EquipmentSlotSet.c.getMappingsMapperCompat().Ro));
    }

    public EquipmentSlotSet(Object object) {
        super(object);
    }
}
