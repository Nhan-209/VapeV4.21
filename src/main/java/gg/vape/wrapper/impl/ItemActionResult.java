package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.EnumActionResult;
import gg.vape.wrapper.impl.GlStateManagerTexGenCoord;
import gg.vape.wrapper.impl.ItemRenderContext;
import gg.vape.wrapper.impl.ItemStack;

public class ItemActionResult
extends EnumActionResult {
    public static ItemActionResult q(GlStateManagerTexGenCoord t1_02, ItemRenderContext tx_12) {
        return new ItemActionResult(ItemActionResult.c.getMappingsMapperCompat().Di.d(t1_02.getObject(), tx_12.getObject()));
    }

    public boolean V() {
        return ItemActionResult.c.getMappingsMapperCompat().Di.Z(this.getObject());
    }

    public ItemActionResult u() {
        return new ItemActionResult(ItemActionResult.c.getMappingsMapperCompat().Di.z(this.getObject()));
    }

    public ItemStack a() {
        return new ItemStack(ItemActionResult.c.getMappingsMapperCompat().Di.T(this.getObject()));
    }

    public ItemActionResult u(ItemStack itemStack) {
        return new ItemActionResult(ItemActionResult.c.getMappingsMapperCompat().Di.Z(this.getObject(), itemStack.getObject()));
    }

    public boolean n() {
        return ItemActionResult.c.getMappingsMapperCompat().Di.P(this.getObject());
    }

    public ItemActionResult(Object object) {
        super(object);
    }
}

