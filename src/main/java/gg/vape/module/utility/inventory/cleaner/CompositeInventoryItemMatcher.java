package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.AbstractInventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemCategory;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherBuilderBase;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherBuilderFoundation;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import java.util.function.BiPredicate;

public class CompositeInventoryItemMatcher
extends AbstractInventoryItemMatcher {
    private InventoryItemCategory category;
    private final BiPredicate<ItemStack, Item> matchPredicate;
    private static String label;

    public static void t(String string) {
        label = string;
    }

    @Override
    public void S(InventoryItemCategory inventoryItemCategory) {
        this.category = inventoryItemCategory;
    }

    public static InventoryItemMatcherBuilderFoundation p(InventoryItemMatcherBuilderBase<?> inventoryItemMatcherBuilderBase) {
        return new InventoryItemMatcherBuilderFoundation(inventoryItemMatcherBuilderBase, null);
    }

    @Override
    public InventoryItemCategory G() {
        return this.category;
    }

    public static String t() {
        return label;
    }

    public CompositeInventoryItemMatcher(InventoryItemMatcherBuilderFoundation inventoryItemMatcherBuilderFoundation) {
        super(inventoryItemMatcherBuilderFoundation);
        this.matchPredicate = inventoryItemMatcherBuilderFoundation.R();
    }

    @Override
    public boolean g(ItemStack itemStack, Item item) {
        return this.matchPredicate.test(itemStack, item);
    }

    static {
        if (CompositeInventoryItemMatcher.t() != null) {
            CompositeInventoryItemMatcher.t("Pt1Dwb");
        }
    }
}

