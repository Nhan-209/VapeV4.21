package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.AbstractInventoryItemCategory;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.ItemFilterSelection;
import gg.vape.module.utility.inventory.cleaner.MatcherBackedInventoryItemCategoryBuilder;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;

public class MatcherBackedInventoryItemCategory
extends AbstractInventoryItemCategory {
    @Override
    public boolean V(ItemFilterSelection itemFilterSelection) {
        Object var5_8;
        InventoryItemMatcher inventoryItemMatcher2 = itemFilterSelection.c();
        if (inventoryItemMatcher2 != null) {
            Object var5_6;
            ItemStack itemStack = itemFilterSelection.E();
            if (itemStack != null) {
                Item item;
                Item item2 = item = itemStack.getItem();
                boolean bl = false;
                if (!this.i().isEmpty()) {
                    if (this.i().contains(inventoryItemMatcher2) && !this.i().stream().noneMatch(inventoryItemMatcher -> MatcherBackedInventoryItemCategory.lambda$isAvailableToItem$0(itemStack, item2, inventoryItemMatcher))) {
                        bl = true;
                    }
                } else {
                    bl = true;
                }
                return bl;
            }
            Object var3_10 = var5_6 = null;
            boolean bl = false;
            if (!this.i().isEmpty()) {
                if (this.i().contains(inventoryItemMatcher2)) {
                    bl = true;
                }
            } else {
                bl = true;
            }
            return bl;
        }
        ItemStack itemStack = itemFilterSelection.E();
        if (itemStack != null) {
            Item item;
            Item item3 = item = itemStack.getItem();
            boolean bl = false;
            if (!this.i().isEmpty()) {
                if (!this.i().stream().noneMatch(inventoryItemMatcher -> MatcherBackedInventoryItemCategory.lambda$isAvailableToItem$0(itemStack, item3, inventoryItemMatcher))) {
                    bl = true;
                }
            } else {
                bl = true;
            }
            return bl;
        }
        Object var3_12 = var5_8 = null;
        boolean bl = false;
        bl = !this.i().isEmpty() ? true : true;
        return bl;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private static boolean lambda$isAvailableToItem$0(ItemStack itemStack, Item item, InventoryItemMatcher inventoryItemMatcher) {
        return inventoryItemMatcher.g(itemStack, item);
    }

    public MatcherBackedInventoryItemCategory(MatcherBackedInventoryItemCategoryBuilder matcherBackedInventoryItemCategoryBuilder) {
        super(matcherBackedInventoryItemCategoryBuilder);
    }
}
