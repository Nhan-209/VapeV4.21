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
        long l = ZkmLongKeyState.a(-7527252553226812480L, 5145736676751609216L, MethodHandles.lookup().lookupClass()).a(96277157381052L) ^ 0x7F4235AD2FBBL;
        String[] stringArray = new String[]{"any-armor", "Any armor", "armor_item", "Any type of armor"};
        g = ((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)InventoryItemMatcher.c().t().n(stringArray[0])).m(stringArray[1])).H(stringArray[2])).M(stringArray[3])).A(InventoryItemMatcherGroup.ARMOR)).t(ArmorInventoryItemMatchers::lambda$static$0).q();
    }

    static void v() {
        InventoryItemMatcherRegistry.R(g);
    }

    private static boolean lambda$static$0(ItemStack itemStack, Item item) {
        return ItemStackScoreUtil.R(item);
    }
}

