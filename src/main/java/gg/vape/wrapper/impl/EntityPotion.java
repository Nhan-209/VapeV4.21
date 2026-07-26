package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MEntityPotion;
import gg.vape.wrapper.impl.EntityEnderPearl;
import gg.vape.wrapper.impl.ItemStack;

public class EntityPotion
extends EntityEnderPearl {
    public EntityPotion(Object object) {
        super(object);
    }

    public ItemStack getPotion() {
        return new ItemStack(MEntityPotion.s(EntityPotion.c.getMappingsMapperCompat().hf, this.getObject()));
    }
}

