package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.SlotInventoryFilterRule;

public class ArmorSlotInventoryFilterRule
extends SlotInventoryFilterRule {
    @Override
    public int b() {
        return 5 + super.m();
    }

    public ArmorSlotInventoryFilterRule(int n) {
        super(n);
    }
}

