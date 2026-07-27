package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.ComparisonOperator;
import gg.vape.module.utility.inventory.cleaner.InventoryItemCategoryBuilder;
import gg.vape.module.utility.inventory.cleaner.StackSizeInventoryItemCategory;

public class StackSizeInventoryItemCategoryBuilder
extends InventoryItemCategoryBuilder<StackSizeInventoryItemCategoryBuilder> {
    private ComparisonOperator comparisonOperator;
    private int stackSize;

    public StackSizeInventoryItemCategoryBuilder D(int size) {
        this.stackSize = size;
        return this;
    }

    public StackSizeInventoryItemCategory O() {
        return new StackSizeInventoryItemCategory(this);
    }

    public StackSizeInventoryItemCategoryBuilder h(ComparisonOperator operator) {
        this.comparisonOperator = operator;
        return this;
    }

    public ComparisonOperator f() {
        return this.comparisonOperator;
    }

    public int U() {
        return this.stackSize;
    }
}

