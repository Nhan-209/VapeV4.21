package gg.vape.module.utility.inventory.cleaner;

import gg.vape.inventory.cleaner.InventoryMatcherMarker;
import gg.vape.module.utility.inventory.cleaner.CompositeInventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherBuilderBase;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import java.util.function.BiPredicate;

public class InventoryItemMatcherBuilderFoundation
extends InventoryItemMatcherBuilderBase<InventoryItemMatcherBuilderFoundation> {
    private BiPredicate<ItemStack, Item> predicate;

    InventoryItemMatcherBuilderFoundation(InventoryItemMatcherBuilderBase base, InventoryMatcherMarker marker) {
        this(base);
    }

    public CompositeInventoryItemMatcher q() {
        return new CompositeInventoryItemMatcher(this);
    }

    public BiPredicate<ItemStack, Item> R() {
        return this.predicate;
    }

    public InventoryItemMatcherBuilderFoundation t(BiPredicate<ItemStack, Item> biPredicate) {
        this.predicate = biPredicate;
        return this;
    }

    private InventoryItemMatcherBuilderFoundation(InventoryItemMatcherBuilderBase<?> base) {
        super(base);
    }
}

