package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionGroup;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterPresetData;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ItemStack;
import java.util.List;

public abstract class AbstractInventoryFilterPreset
implements InventoryFilterPresetData {
    private static boolean F;
    protected String W;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public static void m(boolean bl) {
        F = bl;
    }

    public AbstractInventoryFilterPreset(String string) {
        this.W = string;
    }

    public static boolean i() {
        boolean bl = AbstractInventoryFilterPreset.O();
        return !bl;
    }

    @Override
    public boolean x(ItemStack itemStack) {
        List<InventoryFilterConditionGroup> list = this.z();
        if (list.isEmpty()) {
            return true;
        }
        for (InventoryFilterConditionGroup inventoryFilterConditionGroup : list) {
            if (!inventoryFilterConditionGroup.u(itemStack)) continue;
            return true;
        }
        return false;
    }

    public static boolean O() {
        return F;
    }

    @Override
    public String getName() {
        return this.W;
    }

    static {
        if (!AbstractInventoryFilterPreset.i()) {
            AbstractInventoryFilterPreset.m(true);
        }
    }
}

