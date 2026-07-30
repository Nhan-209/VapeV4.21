package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MEntityPotion;

public class EntityPotion
extends EntityEnderPearl {
    public EntityPotion(Object object) {
        super(object);
    }

    public ItemStack getPotion() {
        return new ItemStack(MEntityPotion.s(EntityPotion.vapeInstance.getMappingsMapperCompat().hf, this.getObject()));
    }
}

