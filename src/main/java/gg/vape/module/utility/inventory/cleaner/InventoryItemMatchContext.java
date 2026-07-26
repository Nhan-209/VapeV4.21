package gg.vape.module.utility.inventory.cleaner;

import gg.vape.Vape;
import gg.vape.mapping.ItemMappingEntry;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import org.jetbrains.annotations.Nullable;

public class InventoryItemMatchContext {
    private final ItemMappingEntry M;
    private final Item o;
    private final ItemStack z;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public Item M() {
        return this.o;
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
        return this.M;
    }

    public InventoryItemMatchContext(ItemMappingEntry itemMappingEntry, ItemStack itemStack, Item item) {
        this.M = itemMappingEntry;
        this.z = itemStack;
        this.o = item;
    }

    public ItemStack v() {
        return this.z;
    }
}

