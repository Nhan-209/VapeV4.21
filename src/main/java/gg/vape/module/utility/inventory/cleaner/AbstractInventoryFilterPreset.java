package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionGroup;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterPresetData;
import gg.vape.wrapper.impl.ItemStack;
import java.util.List;

public abstract class AbstractInventoryFilterPreset
implements InventoryFilterPresetData {
    private static boolean initialized;
    protected String W;


    public static void setInitialized(boolean bl) {
        initialized = bl;
    }

    public AbstractInventoryFilterPreset(String string) {
        this.W = string;
    }

    public static boolean needsInitialization() {
        boolean bl = AbstractInventoryFilterPreset.isInitialized();
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

    public static boolean isInitialized() {
        return initialized;
    }

    @Override
    public String getName() {
        return this.W;
    }

    static {
        if (!AbstractInventoryFilterPreset.needsInitialization()) {
            AbstractInventoryFilterPreset.setInitialized(true);
        }
    }
}

