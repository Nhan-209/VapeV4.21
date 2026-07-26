package gg.vape.module.utility.inventory.cleaner;

import gg.vape.Vape;
import gg.vape.mapping.ItemMappingEntry;
import gg.vape.module.utility.inventory.cleaner.AbstractInventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherBuilderBase;
import gg.vape.module.utility.inventory.cleaner.StringInventoryItemMatcherBuilder;
import gg.vape.module.utility.inventory.cleaner.StringMatchOperator;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import java.util.Map;

public class StringInventoryItemMatcher
extends AbstractInventoryItemMatcher {
    private final Map<String, StringMatchOperator> t;

    @Override
    public boolean g(ItemStack itemStack, Item item) {
        if (this.t.isEmpty()) {
            return false;
        }
        ItemMappingEntry itemMappingEntry = Vape.INSTANCE.getItemStackResolver().j(itemStack);
        if (itemMappingEntry == null) {
            return false;
        }
        for (Map.Entry<String, StringMatchOperator> entry : this.t.entrySet()) {
            String string = entry.getKey();
            StringMatchOperator stringMatchOperator = entry.getValue();
            if (!stringMatchOperator.z().test(itemMappingEntry.q(), string)) continue;
            return true;
        }
        return false;
    }

    public static StringInventoryItemMatcherBuilder n(InventoryItemMatcherBuilderBase<?> inventoryItemMatcherBuilderBase) {
        return new StringInventoryItemMatcherBuilder(inventoryItemMatcherBuilderBase, null);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public StringInventoryItemMatcher(StringInventoryItemMatcherBuilder stringInventoryItemMatcherBuilder) {
        super(stringInventoryItemMatcherBuilder);
        this.t = stringInventoryItemMatcherBuilder.X();
    }
}

