package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.AttributeModifier;
import gg.vape.wrapper.impl.EquipmentSlotGroup;
import gg.vape.wrapper.impl.Holder;

public class ItemAttributeModifiersComponent$Entry
extends Wrapper {
    public EquipmentSlotGroup J() {
        Holder t4_02 = new Holder(ItemAttributeModifiersComponent$Entry.c.getMappingsMapperCompat().DN.P(this.I));
        return new EquipmentSlotGroup(t4_02.N());
    }

    public AttributeModifier e() {
        return new AttributeModifier(ItemAttributeModifiersComponent$Entry.c.getMappingsMapperCompat().DN.q(this.I));
    }

    public ItemAttributeModifiersComponent$Entry(Object object) {
        super(object);
    }
}

