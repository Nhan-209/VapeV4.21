package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionGroup;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionGroupBuilder;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherPreset;
import java.util.ArrayList;
import java.util.List;

public class InventoryItemMatcherPresetBuilder {
    private String D;
    private final List<InventoryItemMatcher> I;
    private final List<InventoryFilterConditionGroup> y = new ArrayList<InventoryFilterConditionGroup>();

    public InventoryItemMatcherPreset z() {
        return new InventoryItemMatcherPreset(this.D, this.y, this.I);
    }

    public InventoryItemMatcherPresetBuilder() {
        this.I = new ArrayList<InventoryItemMatcher>();
    }

    public InventoryItemMatcherPresetBuilder I(InventoryItemMatcher inventoryItemMatcher) {
        this.I.add(inventoryItemMatcher);
        return this;
    }

    public InventoryItemMatcherPresetBuilder b(InventoryFilterConditionGroup inventoryFilterConditionGroup) {
        this.y.add(inventoryFilterConditionGroup);
        return this;
    }

    public InventoryItemMatcherPresetBuilder B(String string) {
        this.D = string;
        return this;
    }

    public InventoryItemMatcherPresetBuilder P(InventoryFilterConditionGroupBuilder inventoryFilterConditionGroupBuilder) {
        this.y.add(inventoryFilterConditionGroupBuilder.w());
        return this;
    }
}

