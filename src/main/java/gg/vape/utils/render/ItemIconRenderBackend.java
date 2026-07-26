package gg.vape.utils.render;

import gg.vape.wrapper.impl.ItemStack;

public interface ItemIconRenderBackend {
    public void e();

    default public void s(float f, float f2, int n, int n2, float f3, boolean bl) {
        this.H(f, f2, n, n2, f3);
    }

    public void N(ItemStack var1, float var2);

    public void H(float var1, float var2, int var3, int var4, float var5);
}

