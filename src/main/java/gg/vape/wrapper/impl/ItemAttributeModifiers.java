package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;
import java.util.Collection;

public class ItemAttributeModifiers
extends Wrapper {
    public int i() {
        return ItemAttributeModifiers.c.getMappingsMapperCompat().Cd.y(this.I);
    }

    public boolean v(Object object, Object object2) {
        return ItemAttributeModifiers.c.getMappingsMapperCompat().Cd.y(this.I, object, object2);
    }

    public Collection f() {
        return ItemAttributeModifiers.c.getMappingsMapperCompat().Cd.g(this.I);
    }

    public ItemAttributeModifiers(Object object) {
        super(object);
    }
}

