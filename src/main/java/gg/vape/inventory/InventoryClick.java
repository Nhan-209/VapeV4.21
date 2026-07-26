package gg.vape.inventory;

import gg.vape.inventory.InventoryClickBuilder;
import gg.vape.wrapper.impl.Minecraft;

public class InventoryClick {
    private final int E;
    private final int C;
    private final int B;
    private final int i;

    public int t() {
        return this.E;
    }

    public int W() {
        return this.i;
    }

    public int M() {
        return this.B;
    }

    public static InventoryClickBuilder P() {
        return new InventoryClickBuilder();
    }

    public int G() {
        return this.C;
    }

    public void k() {
        Minecraft.playerController().O(this.E, this.C, this.B, this.i, Minecraft.thePlayer());
    }

    public InventoryClick(int n, int n2, int n3, int n4) {
        this.E = n;
        this.C = n2;
        this.B = n3;
        this.i = n4;
    }
}

