package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.AbstractInventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherBuilder;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherBuilderBase;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherGroup;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;

public class EmptySlotInventoryItemMatcher
extends AbstractInventoryItemMatcher {
    public static final EmptySlotInventoryItemMatcher a = new EmptySlotInventoryItemMatcher();


    EmptySlotInventoryItemMatcher() {
        super((InventoryItemMatcherBuilderBase<?>)((InventoryItemMatcherBuilder)((InventoryItemMatcherBuilder)InventoryItemMatcher.c().m("Hand")).M("No item")).A(InventoryItemMatcherGroup.HIDDEN));
    }

    @Override
    public boolean g(ItemStack itemStack, Item item) {
        return itemStack == null || itemStack.isNull() || item == null || item.isNull();
    }
}

