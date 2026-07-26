package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MEntityEquipmentSlotHolder;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.EntityEquipmentSlot;

public class EntityEquipmentSlotHolder
extends Wrapper {
    public EntityEquipmentSlot a() {
        return new EntityEquipmentSlot(MEntityEquipmentSlotHolder.S(EntityEquipmentSlotHolder.c.getMappingsMapperCompat().hh, this.I));
    }

    public EntityEquipmentSlotHolder(Object object) {
        super(object);
    }
}

