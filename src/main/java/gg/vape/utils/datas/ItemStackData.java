package gg.vape.utils.datas;

import gg.vape.wrapper.impl.ItemStack;

public class ItemStackData {
    private boolean V;
    private final ItemStack F;
    private final int J;

    public ItemStack w() {
        return this.F;
    }

    public ItemStackData(int n, ItemStack itemStack) {
        this.J = n;
        this.F = itemStack;
    }

    public void w(boolean bl) {
        this.V = bl;
    }

    public boolean H() {
        return this.V;
    }

    public int Y() {
        return this.J;
    }
}

