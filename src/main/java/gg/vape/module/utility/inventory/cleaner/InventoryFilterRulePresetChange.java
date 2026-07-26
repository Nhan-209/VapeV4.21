package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.InventoryFilterPreset;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterRule;

public class InventoryFilterRulePresetChange {
    private final InventoryFilterRule u;
    private final boolean a;
    private final InventoryFilterPreset j;
    private final InventoryFilterPreset C;

    public InventoryFilterPreset L() {
        return this.C;
    }

    public boolean q() {
        return this.a;
    }

    public InventoryFilterPreset W() {
        return this.j;
    }

    public InventoryFilterRulePresetChange(InventoryFilterRule inventoryFilterRule, InventoryFilterPreset pJ, InventoryFilterPreset pJ2, boolean bl) {
        this.u = inventoryFilterRule;
        this.C = pJ;
        this.j = pJ2;
        this.a = bl;
    }

    public InventoryFilterRule R() {
        return this.u;
    }
}

