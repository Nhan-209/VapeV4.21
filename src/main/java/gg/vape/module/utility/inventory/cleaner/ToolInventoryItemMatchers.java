package gg.vape.module.utility.inventory.cleaner;

import gg.vape.config.ClientSettings;
import gg.vape.module.utility.inventory.cleaner.CompositeInventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatchContext;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherBuilderFoundation;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherGroup;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherRegistry;
import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import java.lang.invoke.MethodHandles;
import java.util.Comparator;

public class ToolInventoryItemMatchers {
    public static final CompositeInventoryItemMatcher N;
    public static final CompositeInventoryItemMatcher r;
    public static final CompositeInventoryItemMatcher q;
    public static final CompositeInventoryItemMatcher a;
    public static final CompositeInventoryItemMatcher U;

    private static boolean lambda$static$4(ItemStack itemStack, Item item) {
        return ItemStackScoreUtil.g(item);
    }

    private static boolean lambda$static$3(ItemStack itemStack, Item item) {
        return ItemStackScoreUtil.T(item);
    }

    static void r() {
        InventoryItemMatcherRegistry.R(U);
        InventoryItemMatcherRegistry.R(q);
        InventoryItemMatcherRegistry.R(a);
        InventoryItemMatcherRegistry.R(N);
        InventoryItemMatcherRegistry.R(r);
    }

    static {
        long l = ZkmLongKeyState.a(8086798803493895557L, 9034623933788629277L, MethodHandles.lookup().lookupClass()).a(145471350503643L) ^ 0x581FC6F84D5DL;
        String[] stringArray = new String[]{"Any type of pickaxe", "Any type of axe", "hoe-hover@2x", "Any tool", "axe-tool", "Pickaxe", "Any type of hoe", "Axe", "Shovel", "Any type of shovel", "axe-hover@2x", "any-tool", "Any type of tool", "pickaxe-hover@2x", "hoe", "shovel", "Hoe", "tools", "shovel-hover@2x", "pickaxe"};
        U = ((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)InventoryItemMatcher.c().t().n(stringArray[11])).m(stringArray[3])).M(stringArray[12])).H(stringArray[17])).A(InventoryItemMatcherGroup.HIDDEN)).t(ToolInventoryItemMatchers::lambda$static$0).q();
        a = ((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)InventoryItemMatcher.c().t().n(stringArray[19])).m(stringArray[5])).M(stringArray[0])).H(stringArray[13])).A(InventoryItemMatcherGroup.TOOLS)).t(ToolInventoryItemMatchers::lambda$static$1).N(Comparator.comparingDouble(ToolInventoryItemMatchers::lambda$static$2))).q();
        q = ((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)InventoryItemMatcher.c().t().n(stringArray[4])).m(stringArray[7])).M(stringArray[1])).H(stringArray[10])).A(InventoryItemMatcherGroup.TOOLS)).t(ToolInventoryItemMatchers::lambda$static$3).N(a.v())).q();
        N = ((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)InventoryItemMatcher.c().t().n(stringArray[15])).m(stringArray[8])).M(stringArray[9])).H(stringArray[18])).A(InventoryItemMatcherGroup.TOOLS)).t(ToolInventoryItemMatchers::lambda$static$4).N(a.v())).q();
        r = ((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)InventoryItemMatcher.c().t().n(stringArray[14])).m(stringArray[16])).M(stringArray[6])).H(stringArray[2])).A(InventoryItemMatcherGroup.TOOLS)).t(ToolInventoryItemMatchers::lambda$static$5).q();
    }

    private static boolean lambda$static$0(ItemStack itemStack, Item item) {
        return ItemStackScoreUtil.I(item);
    }

    private static double lambda$static$2(InventoryItemMatchContext inventoryItemMatchContext) {
        return ClientSettings.X(inventoryItemMatchContext.v());
    }

    private static boolean lambda$static$1(ItemStack itemStack, Item item) {
        return ItemStackScoreUtil.m(item);
    }

    private static boolean lambda$static$5(ItemStack itemStack, Item item) {
        return ItemStackScoreUtil.k(item);
    }
}

