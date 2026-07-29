package gg.vape.module.utility.inventory.cleaner;

import gg.vape.Vape;
import gg.vape.mapping.ItemMappingEntry;
import gg.vape.module.utility.inventory.cleaner.AbstractInventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherBuilderBase;
import gg.vape.module.utility.inventory.cleaner.StringInventoryItemMatcherBuilder;
import gg.vape.module.utility.inventory.cleaner.StringMatchOperator;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import java.util.Map;

public class StringInventoryItemMatcher
extends AbstractInventoryItemMatcher {
    private final Map<String, StringMatchOperator> matchOperators;

    @Override
    public boolean matches(ItemStack itemStack, Item item) {
        if (this.matchOperators.isEmpty()) {
            return false;
        }
        ItemMappingEntry itemMappingEntry = Vape.INSTANCE.getItemStackResolver().resolve(itemStack);
        if (itemMappingEntry == null) {
            return false;
        }
        for (Map.Entry<String, StringMatchOperator> entry : this.matchOperators.entrySet()) {
            String string = entry.getKey();
            StringMatchOperator stringMatchOperator = entry.getValue();
            if (!stringMatchOperator.getPredicate().test(itemMappingEntry.q(), string)) continue;
            return true;
        }
        return false;
    }

    public static StringInventoryItemMatcherBuilder builderFrom(InventoryItemMatcherBuilderBase<?> inventoryItemMatcherBuilderBase) {
        return new StringInventoryItemMatcherBuilder(inventoryItemMatcherBuilderBase, null);
    }


    public StringInventoryItemMatcher(StringInventoryItemMatcherBuilder stringInventoryItemMatcherBuilder) {
        super(stringInventoryItemMatcherBuilder);
        this.matchOperators = stringInventoryItemMatcherBuilder.getOperatorsByPattern();
    }
}

