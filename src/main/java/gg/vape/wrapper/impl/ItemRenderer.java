package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MItemRenderer;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.AbstractClientPlayer;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.ItemRendererBridge;
import gg.vape.wrapper.impl.ItemStack;

public class ItemRenderer
extends Wrapper {
    public void g(EntityLivingBase entityLivingBase, ItemStack itemStack, ItemRendererBridge itemRendererBridge) {
        ItemRenderer.c.getMappingsMapperCompat().qE.Z(this.I, entityLivingBase.getObject(), itemStack.getObject(), itemRendererBridge.getObject());
    }

    public ItemRenderer(Object object) {
        super(object);
    }

    public void X(AbstractClientPlayer abstractClientPlayer) {
        MItemRenderer.K(ItemRenderer.c.getMappingsMapperCompat().qE, this.I, abstractClientPlayer.getObject());
    }

    public float e() {
        return ItemRenderer.c.getMappingsMapperCompat().qE.S(this.I);
    }

    public ItemStack k() {
        return new ItemStack(ItemRenderer.c.getMappingsMapperCompat().qE.v(this.I));
    }

    public float R() {
        return ItemRenderer.c.getMappingsMapperCompat().qE.Y(this.I);
    }
}

