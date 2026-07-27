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

    private static boolean matchesShovel(ItemStack itemStack, Item item) {
        return ItemStackScoreUtil.g(item);
    }

    private static boolean matchesPickaxe(ItemStack itemStack, Item item) {
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
        long keyState = ZkmLongKeyState.a(8086798803493895557L, 9034623933788629277L, MethodHandles.lookup().lookupClass()).a(145471350503643L) ^ 0x581FC6F84D5DL;
        String[] labels = new String[]{"Any type of pickaxe", "Any type of axe", "hoe-hover@2x", "Any tool", "axe-tool", "Pickaxe", "Any type of hoe", "Axe", "Shovel", "Any type of shovel", "axe-hover@2x", "any-tool", "Any type of tool", "pickaxe-hover@2x", "hoe", "shovel", "Hoe", "tools", "shovel-hover@2x", "pickaxe"};
        U = ((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)InventoryItemMatcher.c().t().n(labels[11])).m(labels[3])).M(labels[12])).H(labels[17])).A(InventoryItemMatcherGroup.HIDDEN)).t(ToolInventoryItemMatchers::matchesAnyTool).q();
        a = ((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)InventoryItemMatcher.c().t().n(labels[19])).m(labels[5])).M(labels[0])).H(labels[13])).A(InventoryItemMatcherGroup.TOOLS)).t(ToolInventoryItemMatchers::matchesAxe).N(Comparator.comparingDouble(ToolInventoryItemMatchers::axeSortScore))).q();
        q = ((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)InventoryItemMatcher.c().t().n(labels[4])).m(labels[7])).M(labels[1])).H(labels[10])).A(InventoryItemMatcherGroup.TOOLS)).t(ToolInventoryItemMatchers::matchesPickaxe).N(a.v())).q();
        N = ((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)InventoryItemMatcher.c().t().n(labels[15])).m(labels[8])).M(labels[9])).H(labels[18])).A(InventoryItemMatcherGroup.TOOLS)).t(ToolInventoryItemMatchers::matchesShovel).N(a.v())).q();
        r = ((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)InventoryItemMatcher.c().t().n(labels[14])).m(labels[16])).M(labels[6])).H(labels[2])).A(InventoryItemMatcherGroup.TOOLS)).t(ToolInventoryItemMatchers::matchesHoe).q();
    }

    private static boolean matchesAnyTool(ItemStack itemStack, Item item) {
        return ItemStackScoreUtil.I(item);
    }

    private static double axeSortScore(InventoryItemMatchContext inventoryItemMatchContext) {
        return ClientSettings.X(inventoryItemMatchContext.v());
    }

    private static boolean matchesAxe(ItemStack itemStack, Item item) {
        return ItemStackScoreUtil.m(item);
    }

    private static boolean matchesHoe(ItemStack itemStack, Item item) {
        return ItemStackScoreUtil.k(item);
    }
}

