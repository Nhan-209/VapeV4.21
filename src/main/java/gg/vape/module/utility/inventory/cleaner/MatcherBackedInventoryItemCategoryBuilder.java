package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.InventoryItemCategoryBuilder;
import gg.vape.module.utility.inventory.cleaner.MatcherBackedInventoryItemCategory;

public class MatcherBackedInventoryItemCategoryBuilder
extends InventoryItemCategoryBuilder<MatcherBackedInventoryItemCategoryBuilder> {
    private static String[] w;

    static {
        if (MatcherBackedInventoryItemCategoryBuilder.l$src$ALjava_lang_String_$r4e615() == null) {
            MatcherBackedInventoryItemCategoryBuilder.c(new String[3]);
        }
    }

    public static String[] l$src$ALjava_lang_String_$r4e615() {
        return w;
    }

    public static void c(String[] stringArray) {
        w = stringArray;
    }

    public MatcherBackedInventoryItemCategory G() {
        return new MatcherBackedInventoryItemCategory(this);
    }
}

