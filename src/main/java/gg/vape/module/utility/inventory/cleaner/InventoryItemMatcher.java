package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.InventoryItemCategory;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatchContext;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherBuilder;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherGroup;
import gg.vape.unmap.INamed;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import java.util.Comparator;
import gg.vape.module.utility.inventory.cleaner.DescribedOption;
import org.jetbrains.annotations.Nullable;

public interface InventoryItemMatcher
extends INamed,
DescribedOption {
    default public boolean R(ItemStack itemStack) {
        return this.g(itemStack, itemStack.getItem());
    }

    default public InventoryItemCategory G() {
        return null;
    }

    @Nullable
    public String Z();

    @Nullable
    public Comparator<InventoryItemMatchContext> v();

    public static InventoryItemMatcherBuilder c() {
        return new InventoryItemMatcherBuilder();
    }

    public String k();

    public InventoryItemMatcherGroup l();

    public boolean g(ItemStack var1, Item var2);

    default public void S(InventoryItemCategory inventoryItemCategory) {
    }
}

