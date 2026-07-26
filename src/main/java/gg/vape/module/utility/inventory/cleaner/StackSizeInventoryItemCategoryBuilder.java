package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.ComparisonOperator;
import gg.vape.module.utility.inventory.cleaner.InventoryItemCategoryBuilder;
import gg.vape.module.utility.inventory.cleaner.StackSizeInventoryItemCategory;

public class StackSizeInventoryItemCategoryBuilder
extends InventoryItemCategoryBuilder<StackSizeInventoryItemCategoryBuilder> {
    private ComparisonOperator I;
    private int X;

    public StackSizeInventoryItemCategoryBuilder D(int n) {
        this.X = n;
        return this;
    }

    public StackSizeInventoryItemCategory O() {
        return new StackSizeInventoryItemCategory(this);
    }

    public StackSizeInventoryItemCategoryBuilder h(ComparisonOperator cj_22) {
        this.I = cj_22;
        return this;
    }

    public ComparisonOperator f() {
        return this.I;
    }

    public int U() {
        return this.X;
    }
}

