package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.AbstractInventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.ClassInventoryItemMatcherBuilder;
import gg.vape.module.utility.inventory.cleaner.InventoryItemCategory;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherBuilderBase;
import gg.vape.module.utility.inventory.cleaner.InventoryMatcherListMode;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import java.util.ArrayList;
import java.util.List;

public class ClassInventoryItemMatcher
extends AbstractInventoryItemMatcher {
    private final List<Class<?>> Y = new ArrayList();
    private InventoryItemCategory m;
    private final InventoryMatcherListMode E;

    @Override
    public InventoryItemCategory G() {
        return this.m;
    }

    @Override
    public boolean g(ItemStack itemStack, Item item) {
        if (this.E == InventoryMatcherListMode.WHITELIST) {
            return this.Y.stream().anyMatch(item::isInstance);
        }
        return this.Y.stream().noneMatch(item::isInstance);
    }

    public static ClassInventoryItemMatcherBuilder J(InventoryItemMatcherBuilderBase<?> inventoryItemMatcherBuilderBase) {
        return new ClassInventoryItemMatcherBuilder(inventoryItemMatcherBuilderBase, null);
    }

    public ClassInventoryItemMatcher(ClassInventoryItemMatcherBuilder classInventoryItemMatcherBuilder) {
        super(classInventoryItemMatcherBuilder);
        this.Y.addAll(classInventoryItemMatcherBuilder.V());
        this.E = classInventoryItemMatcherBuilder.C();
    }

    @Override
    public void S(InventoryItemCategory inventoryItemCategory) {
        this.m = inventoryItemCategory;
    }

}

