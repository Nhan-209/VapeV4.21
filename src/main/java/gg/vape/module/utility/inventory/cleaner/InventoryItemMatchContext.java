package gg.vape.module.utility.inventory.cleaner;

import gg.vape.Vape;
import gg.vape.mapping.ItemMappingEntry;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import org.jetbrains.annotations.Nullable;

public class InventoryItemMatchContext {
    private final ItemMappingEntry mappingEntry;
    private final Item item;
    private final ItemStack itemStack;


    public Item M() {
        return this.item;
    }

    @Nullable
    public static InventoryItemMatchContext i(ItemStack itemStack) {
        if (itemStack == null || itemStack.isNull()) {
            return null;
        }
        ItemMappingEntry itemMappingEntry = Vape.INSTANCE.getItemStackResolver().j(itemStack);
        return new InventoryItemMatchContext(itemMappingEntry, itemStack);
    }

    public InventoryItemMatchContext(ItemMappingEntry itemMappingEntry, ItemStack itemStack) {
        this(itemMappingEntry, itemStack, itemStack.getItem());
    }

    public ItemMappingEntry f() {
        return this.mappingEntry;
    }

    public InventoryItemMatchContext(ItemMappingEntry itemMappingEntry, ItemStack stack, Item resolvedItem) {
        this.mappingEntry = itemMappingEntry;
        this.itemStack = stack;
        this.item = resolvedItem;
    }

    public ItemStack v() {
        return this.itemStack;
    }
}

