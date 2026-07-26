package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MItems;
import gg.vape.wrapper.Wrapper;

public class Items
extends Wrapper {
    public static Items I() {
        return new Items(MItems.T(Items.c.getMappingsMapperCompat().Rl));
    }

    public static Items e() {
        return new Items(MItems.d(Items.c.getMappingsMapperCompat().Rl));
    }

    public Items(Object object) {
        super(object);
    }
}

