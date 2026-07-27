package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.AbstractInventoryItemCategory;
import gg.vape.module.utility.inventory.cleaner.ComparisonOperator;
import gg.vape.module.utility.inventory.cleaner.ItemFilterSelection;
import gg.vape.module.utility.inventory.cleaner.StackSizeInventoryItemCategoryBuilder;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ItemStack;

public class StackSizeInventoryItemCategory
extends AbstractInventoryItemCategory {
    private final ComparisonOperator operator;
    private final int stackSize;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    @Override
    public boolean V(ItemFilterSelection cn_22) {
        ItemStack itemStack = cn_22.E();
        if (itemStack == null) {
            return false;
        }
        return this.operator.p(itemStack.P(), this.stackSize);
    }

    StackSizeInventoryItemCategory(StackSizeInventoryItemCategoryBuilder stackSizeInventoryItemCategoryBuilder) {
        super(stackSizeInventoryItemCategoryBuilder);
        this.stackSize = stackSizeInventoryItemCategoryBuilder.U();
        this.operator = stackSizeInventoryItemCategoryBuilder.f();
    }
}

