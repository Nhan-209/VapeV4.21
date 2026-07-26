package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.InventoryItemCategoryBuilder;
import gg.vape.module.utility.inventory.cleaner.MatcherBackedInventoryItemCategoryBuilder;
import gg.vape.module.utility.inventory.cleaner.StackSizeInventoryItemCategoryBuilder;

public class DefaultInventoryItemCategoryBuilder
extends InventoryItemCategoryBuilder {
    public StackSizeInventoryItemCategoryBuilder u() {
        return new StackSizeInventoryItemCategoryBuilder();
    }

    public MatcherBackedInventoryItemCategoryBuilder m() {
        return new MatcherBackedInventoryItemCategoryBuilder();
    }
}

