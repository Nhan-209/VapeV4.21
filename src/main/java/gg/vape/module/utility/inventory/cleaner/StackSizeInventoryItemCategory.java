package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.AbstractInventoryItemCategory;
import gg.vape.module.utility.inventory.cleaner.ComparisonOperator;
import gg.vape.module.utility.inventory.cleaner.ItemFilterSelection;
import gg.vape.module.utility.inventory.cleaner.StackSizeInventoryItemCategoryBuilder;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ItemStack;

public class StackSizeInventoryItemCategory
extends AbstractInventoryItemCategory {
    private final ComparisonOperator H;
    private final int M;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    @Override
    public boolean V(ItemFilterSelection cn_22) {
        ItemStack itemStack = cn_22.E();
        if (itemStack == null) {
            return false;
        }
        return this.H.p(itemStack.P(), this.M);
    }

    StackSizeInventoryItemCategory(StackSizeInventoryItemCategoryBuilder stackSizeInventoryItemCategoryBuilder) {
        super(stackSizeInventoryItemCategoryBuilder);
        this.M = stackSizeInventoryItemCategoryBuilder.U();
        this.H = stackSizeInventoryItemCategoryBuilder.f();
    }
}

