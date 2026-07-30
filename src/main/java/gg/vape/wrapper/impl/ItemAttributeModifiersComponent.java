package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

import java.util.ArrayList;
import java.util.List;

public class ItemAttributeModifiersComponent
extends Wrapper {
    public List<Object> z() {
        return ItemAttributeModifiersComponent.vapeInstance.getMappingsMapperCompat().r.p(this.I);
    }

    public List<ItemAttributeModifiersComponent$Entry> P() {
        ArrayList<ItemAttributeModifiersComponent$Entry> arrayList = new ArrayList<ItemAttributeModifiersComponent$Entry>();
        for (Object object : this.z()) {
            arrayList.add(new ItemAttributeModifiersComponent$Entry(object));
        }
        return arrayList;
    }

    public ItemAttributeModifiersComponent(Object object) {
        super(object);
    }
}

