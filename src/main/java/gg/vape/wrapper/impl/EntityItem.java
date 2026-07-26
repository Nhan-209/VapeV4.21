package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MEntityItem;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.ItemStack;

public class EntityItem
extends Entity {
    public ItemStack J$src$Lgg_vape_wrapper_impl_ItemStack_$5gv0ko() {
        return new ItemStack(MEntityItem.z(EntityItem.c.getMappings().Rn, this.I));
    }

    public EntityItem(Object object) {
        super(object);
    }
}

