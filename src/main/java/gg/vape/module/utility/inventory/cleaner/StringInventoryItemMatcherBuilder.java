package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherBuilderBase;
import gg.vape.module.utility.inventory.cleaner.StringInventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.StringInventoryItemMatcherBuilderConstructorMarker;
import gg.vape.module.utility.inventory.cleaner.StringMatchOperator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.jetbrains.annotations.UnmodifiableView;

public class StringInventoryItemMatcherBuilder
extends InventoryItemMatcherBuilderBase<StringInventoryItemMatcherBuilder> {
    private final Map<String, StringMatchOperator> K = new LinkedHashMap<String, StringMatchOperator>();

    private StringInventoryItemMatcherBuilder(InventoryItemMatcherBuilderBase<?> inventoryItemMatcherBuilderBase) {
        super(inventoryItemMatcherBuilderBase);
    }

    public @UnmodifiableView Map<String, StringMatchOperator> X() {
        return this.K;
    }

    public StringInventoryItemMatcherBuilder R(String string, StringMatchOperator stringMatchOperator) {
        this.K.put(string, stringMatchOperator);
        return this;
    }

    public StringInventoryItemMatcherBuilder L(StringMatchOperator stringMatchOperator, String ... stringArray) {
        for (String string : stringArray) {
            this.K.put(string, stringMatchOperator);
        }
        return this;
    }

    StringInventoryItemMatcherBuilder(InventoryItemMatcherBuilderBase inventoryItemMatcherBuilderBase, StringInventoryItemMatcherBuilderConstructorMarker stringInventoryItemMatcherBuilderConstructorMarker) {
        this(inventoryItemMatcherBuilderBase);
    }

    public StringInventoryItemMatcher g() {
        return new StringInventoryItemMatcher(this);
    }
}

