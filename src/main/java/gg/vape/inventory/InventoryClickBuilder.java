package gg.vape.inventory;

import gg.vape.inventory.InventoryClick;
import gg.vape.inventory.InventoryClickBuildStage;
import gg.vape.inventory.InventoryClickOptionsStage;
import gg.vape.inventory.InventoryClickSlotStage;
import gg.vape.inventory.InventoryClickWindowStage;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class InventoryClickBuilder
implements InventoryClickWindowStage,
InventoryClickSlotStage,
InventoryClickOptionsStage,
InventoryClickBuildStage {
    private int e;
    private int u;
    private int m;
    private int W;

    @Override
    public InventoryClickBuildStage D(int n) {
        this.e = 2;
        this.m = n;
        return this;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public InventoryClickBuildStage E(int n) {
        this.e = 5;
        this.m = n;
        return this;
    }

    @Override
    public InventoryClickSlotStage g(int n) {
        this.W = n;
        return this;
    }

    @Override
    public InventoryClickBuildStage w() {
        this.e = 0;
        return this;
    }


    @Override
    public InventoryClickBuildStage b() {
        return this.D(0);
    }

    @Override
    public InventoryClickBuildStage I(boolean bl) {
        this.e = 4;
        this.m = bl ? 1 : 0;
        return this;
    }

    @Override
    public InventoryClickOptionsStage j(int n) {
        this.u = n;
        return this;
    }

    @Override
    public InventoryClickBuildStage O() {
        this.e = 3;
        return this;
    }

    @Override
    public InventoryClick V() {
        return new InventoryClick(this.W, this.u, this.m, this.e);
    }

    @Override
    public InventoryClickBuildStage U() {
        this.e = 1;
        return this;
    }

    @Override
    public InventoryClickBuildStage O(boolean bl) {
        this.e = 6;
        this.m = bl ? 1 : 0;
        return this;
    }
}
