package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class ItemAttributeModifiersComponent$Entry
extends Wrapper {
    public EquipmentSlotGroup J() {
        Holder t4_02 = new Holder(ItemAttributeModifiersComponent$Entry.vapeInstance.getMappingsMapperCompat().DN.P(this.I));
        return new EquipmentSlotGroup(t4_02.N());
    }

    public AttributeModifier e() {
        return new AttributeModifier(ItemAttributeModifiersComponent$Entry.vapeInstance.getMappingsMapperCompat().DN.q(this.I));
    }

    public ItemAttributeModifiersComponent$Entry(Object object) {
        super(object);
    }
}

