package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionGroup;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionGroupBuilder;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherPreset;
import java.util.ArrayList;
import java.util.List;

public class InventoryItemMatcherPresetBuilder {
    private String name;
    private final List<InventoryItemMatcher> matchers;
    private final List<InventoryFilterConditionGroup> conditionGroups = new ArrayList<InventoryFilterConditionGroup>();

    public InventoryItemMatcherPreset build() {
        return new InventoryItemMatcherPreset(this.name, this.conditionGroups, this.matchers);
    }

    public InventoryItemMatcherPresetBuilder() {
        this.matchers = new ArrayList<InventoryItemMatcher>();
    }

    public InventoryItemMatcherPresetBuilder addMatcher(InventoryItemMatcher inventoryItemMatcher) {
        this.matchers.add(inventoryItemMatcher);
        return this;
    }

    public InventoryItemMatcherPresetBuilder addConditionGroup(InventoryFilterConditionGroup inventoryFilterConditionGroup) {
        this.conditionGroups.add(inventoryFilterConditionGroup);
        return this;
    }

    public InventoryItemMatcherPresetBuilder name(String string) {
        this.name = string;
        return this;
    }

    public InventoryItemMatcherPresetBuilder addConditionGroup(InventoryFilterConditionGroupBuilder inventoryFilterConditionGroupBuilder) {
        this.conditionGroups.add(inventoryFilterConditionGroupBuilder.w());
        return this;
    }
}

