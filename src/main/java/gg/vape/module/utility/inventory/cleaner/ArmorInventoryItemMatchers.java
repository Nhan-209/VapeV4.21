package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherBuilderFoundation;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherGroup;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherRegistry;
import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import java.lang.invoke.MethodHandles;

public class ArmorInventoryItemMatchers {
    public static final InventoryItemMatcher g;

    static {
        long keyState = ZkmLongKeyState.a(-7527252553226812480L, 5145736676751609216L, MethodHandles.lookup().lookupClass()).a(96277157381052L) ^ 0x7F4235AD2FBBL;
        String[] labels = new String[]{"any-armor", "Any armor", "armor_item", "Any type of armor"};
        g = ((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)InventoryItemMatcher.c().t().n(labels[0])).m(labels[1])).H(labels[2])).M(labels[3])).A(InventoryItemMatcherGroup.ARMOR)).t(ArmorInventoryItemMatchers::isArmorItem).q();
    }

    static void v() {
        InventoryItemMatcherRegistry.R(g);
    }

    private static boolean isArmorItem(ItemStack itemStack, Item item) {
        return ItemStackScoreUtil.R(item);
    }
}

