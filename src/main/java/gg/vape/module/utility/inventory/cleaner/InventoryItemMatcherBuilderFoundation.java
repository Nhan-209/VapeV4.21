package gg.vape.module.utility.inventory.cleaner;

import gg.vape.inventory.cleaner.InventoryMatcherMarker;
import gg.vape.module.utility.inventory.cleaner.CompositeInventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherBuilderBase;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import java.util.function.BiPredicate;

public class InventoryItemMatcherBuilderFoundation
extends InventoryItemMatcherBuilderBase<InventoryItemMatcherBuilderFoundation> {
    private BiPredicate<ItemStack, Item> O;

    InventoryItemMatcherBuilderFoundation(InventoryItemMatcherBuilderBase qq_12, InventoryMatcherMarker oK) {
        this(qq_12);
    }

    public CompositeInventoryItemMatcher q() {
        return new CompositeInventoryItemMatcher(this);
    }

    public BiPredicate<ItemStack, Item> R() {
        return this.O;
    }

    public InventoryItemMatcherBuilderFoundation t(BiPredicate<ItemStack, Item> biPredicate) {
        this.O = biPredicate;
        return this;
    }

    private InventoryItemMatcherBuilderFoundation(InventoryItemMatcherBuilderBase<?> qq_12) {
        super(qq_12);
    }
}

