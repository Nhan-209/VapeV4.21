package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherBuilderFoundation;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherGroup;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherRegistry;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;

public class ArmorInventoryItemMatchers {
    public static final InventoryItemMatcher g;

    static {
        String[] labels = new String[]{"any-armor", "Any armor", "armor_item", "Any type of armor"};
        g = ((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)InventoryItemMatcher.builder().composite().withId(labels[0])).withName(labels[1])).withIconName(labels[2])).withDescription(labels[3])).withGroup(InventoryItemMatcherGroup.ARMOR)).withPredicate(ArmorInventoryItemMatchers::isArmorItem).build();
    }

    static void v() {
        InventoryItemMatcherRegistry.register(g);
    }

    private static boolean isArmorItem(ItemStack itemStack, Item item) {
        return ItemStackScoreUtil.R(item);
    }
}

