package gg.vape.inventory;

import gg.vape.inventory.InventoryClick;
import gg.vape.inventory.InventoryClickBuildStage;
import gg.vape.inventory.InventoryClickOptionsStage;
import gg.vape.inventory.InventoryClickSlotStage;
import gg.vape.inventory.InventoryClickWindowStage;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import java.lang.invoke.MethodHandles;

public class InventoryClickBuilder
implements InventoryClickWindowStage,
InventoryClickSlotStage,
InventoryClickOptionsStage,
InventoryClickBuildStage {
    private int e;
    private int u;
    private int m;
    private static final long a;
    private int W;

    @Override
    public InventoryClickBuildStage D(int n) {
        long l = a ^ 0x613895C2792AL;
        this.e = 2;
        this.m = n;
        return this;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public InventoryClickBuildStage E(int n) {
        long l = a ^ 0x47625B486066L;
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

    static {
        long l = a = ZkmLongKeyState.a(-7166944455035180926L, 8509409652536648132L, MethodHandles.lookup().lookupClass()).a(145861546482329L);
    }

    @Override
    public InventoryClickBuildStage b() {
        return this.D(0);
    }

    @Override
    public InventoryClickBuildStage I(boolean bl) {
        long l = a ^ 0x67385A91E181L;
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
        long l = a ^ 0x6EE44CFC6D2BL;
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
        long l = a ^ 0x2D1C364D868CL;
        this.e = 6;
        this.m = bl ? 1 : 0;
        return this;
    }
}
