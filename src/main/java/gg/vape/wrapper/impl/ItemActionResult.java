package gg.vape.wrapper.impl;

public class ItemActionResult
extends EnumActionResult {
    public static ItemActionResult q(GlStateManagerTexGenCoord t1_02, ItemRenderContext tx_12) {
        return new ItemActionResult(ItemActionResult.vapeInstance.getMappingsMapperCompat().Di.d(t1_02.getObject(), tx_12.getObject()));
    }

    public boolean V() {
        return ItemActionResult.vapeInstance.getMappingsMapperCompat().Di.Z(this.getObject());
    }

    public ItemActionResult u() {
        return new ItemActionResult(ItemActionResult.vapeInstance.getMappingsMapperCompat().Di.z(this.getObject()));
    }

    public ItemStack a() {
        return new ItemStack(ItemActionResult.vapeInstance.getMappingsMapperCompat().Di.T(this.getObject()));
    }

    public ItemActionResult u(ItemStack itemStack) {
        return new ItemActionResult(ItemActionResult.vapeInstance.getMappingsMapperCompat().Di.Z(this.getObject(), itemStack.getObject()));
    }

    public boolean n() {
        return ItemActionResult.vapeInstance.getMappingsMapperCompat().Di.P(this.getObject());
    }

    public ItemActionResult(Object object) {
        super(object);
    }
}

