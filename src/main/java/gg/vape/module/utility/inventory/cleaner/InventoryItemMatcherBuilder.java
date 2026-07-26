package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.ClassInventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.ClassInventoryItemMatcherBuilder;
import gg.vape.module.utility.inventory.cleaner.CompositeInventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherBuilderBase;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherBuilderFoundation;
import gg.vape.module.utility.inventory.cleaner.StringInventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.StringInventoryItemMatcherBuilder;

public class InventoryItemMatcherBuilder
extends InventoryItemMatcherBuilderBase<InventoryItemMatcherBuilder> {
    public InventoryItemMatcherBuilderFoundation t() {
        return CompositeInventoryItemMatcher.p(this);
    }

    public StringInventoryItemMatcherBuilder y() {
        return StringInventoryItemMatcher.n(this);
    }

    public ClassInventoryItemMatcherBuilder Y() {
        return ClassInventoryItemMatcher.J(this);
    }
}

