package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.InventoryFilterPreset;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterRule;

public class InventoryFilterRulePresetChange {
    private final InventoryFilterRule rule;
    private final boolean added;
    private final InventoryFilterPreset previousPreset;
    private final InventoryFilterPreset newPreset;

    public InventoryFilterPreset L() {
        return this.newPreset;
    }

    public boolean q() {
        return this.added;
    }

    public InventoryFilterPreset W() {
        return this.previousPreset;
    }

    public InventoryFilterRulePresetChange(InventoryFilterRule inventoryFilterRule, InventoryFilterPreset pJ, InventoryFilterPreset pJ2, boolean bl) {
        this.rule = inventoryFilterRule;
        this.newPreset = pJ;
        this.previousPreset = pJ2;
        this.added = bl;
    }

    public InventoryFilterRule R() {
        return this.rule;
    }
}

