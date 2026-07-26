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
    private InventoryItemCategory L;
    private final BiPredicate<ItemStack, Item> h;
    private static String W;

    public static void t(String string) {
        W = string;
    }

    @Override
    public void S(InventoryItemCategory inventoryItemCategory) {
        this.L = inventoryItemCategory;
    }

    public static InventoryItemMatcherBuilderFoundation p(InventoryItemMatcherBuilderBase<?> inventoryItemMatcherBuilderBase) {
        return new InventoryItemMatcherBuilderFoundation(inventoryItemMatcherBuilderBase, null);
    }

    @Override
    public InventoryItemCategory G() {
        return this.L;
    }

    public static String t() {
        return W;
    }

    public CompositeInventoryItemMatcher(InventoryItemMatcherBuilderFoundation inventoryItemMatcherBuilderFoundation) {
        super(inventoryItemMatcherBuilderFoundation);
        this.h = inventoryItemMatcherBuilderFoundation.R();
    }

    @Override
    public boolean g(ItemStack itemStack, Item item) {
        return this.h.test(itemStack, item);
    }

    static {
        if (CompositeInventoryItemMatcher.t() != null) {
            CompositeInventoryItemMatcher.t("Pt1Dwb");
        }
    }
}

