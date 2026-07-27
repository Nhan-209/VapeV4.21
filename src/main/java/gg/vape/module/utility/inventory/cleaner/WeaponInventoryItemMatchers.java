package gg.vape.module.utility.inventory.cleaner;

import gg.vape.config.ClientSettings;
import gg.vape.module.utility.inventory.cleaner.CompositeInventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatchContext;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherBuilderFoundation;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherGroup;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherRegistry;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import java.lang.invoke.MethodHandles;
import java.util.Comparator;

public class WeaponInventoryItemMatchers {
    public static final CompositeInventoryItemMatcher j;
    public static final CompositeInventoryItemMatcher m;
    public static final CompositeInventoryItemMatcher z;

    public static void K() {
        InventoryItemMatcherRegistry.R(m);
        InventoryItemMatcherRegistry.R(z);
        InventoryItemMatcherRegistry.R(j);
    }

    private static boolean matchesAnyWeapon(ItemStack itemStack, Item item) {
        return ItemStackScoreUtil.h(item) || ItemStackScoreUtil.T(item);
    }

    private static double axeSortScore(InventoryItemMatchContext inventoryItemMatchContext) {
        return ClientSettings.U(inventoryItemMatchContext.v());
    }

    private static boolean matchesAxe(ItemStack itemStack, Item item) {
        return ItemStackScoreUtil.T(item);
    }

    private static double swordSortScore(InventoryItemMatchContext inventoryItemMatchContext) {
        return ClientSettings.U(inventoryItemMatchContext.v());
    }

    static {
        long l = ZkmLongKeyState.a(296928274367331282L, 3060412332488315907L, MethodHandles.lookup().lookupClass()).a(51501192726028L) ^ 0x4264D45BEA17L;
        String[] stringArray = new String[]{"weapons", "axe-weapon", "swords", "weapons", "Axe", "axe-weapon", "sword-hover@2x", "Any Weapon", "Any type of axe", "Sword", "Any type of weapon (sword or axe)", "Any type of sword"};
        m = ((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)InventoryItemMatcher.c().t().n(stringArray[3])).m(stringArray[7])).M(stringArray[10])).H(stringArray[0])).A(InventoryItemMatcherGroup.WEAPONS)).t(WeaponInventoryItemMatchers::matchesAnyWeapon).N(Comparator.comparingDouble(WeaponInventoryItemMatchers::anyWeaponSortScore))).q();
        z = ((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)InventoryItemMatcher.c().t().n(stringArray[2])).m(stringArray[9])).M(stringArray[11])).H(stringArray[6])).A(InventoryItemMatcherGroup.WEAPONS)).t(WeaponInventoryItemMatchers::matchesSword).N(Comparator.comparingDouble(WeaponInventoryItemMatchers::swordSortScore))).q();
        j = ((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)InventoryItemMatcher.c().t().n(stringArray[1])).m(stringArray[4])).M(stringArray[8])).H(stringArray[5])).A(InventoryItemMatcherGroup.WEAPONS)).t(WeaponInventoryItemMatchers::matchesAxe).N(Comparator.comparingDouble(WeaponInventoryItemMatchers::axeSortScore))).q();
    }

    private static boolean matchesSword(ItemStack itemStack, Item item) {
        return ItemStackScoreUtil.h(item);
    }

    private static double anyWeaponSortScore(InventoryItemMatchContext inventoryItemMatchContext) {
        return ClientSettings.U(inventoryItemMatchContext.v());
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

