package gg.vape.module.utility.inventory.cleaner;

import gg.vape.config.ClientSettings;
import gg.vape.module.utility.inventory.cleaner.CompositeInventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatchContext;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherBuilderFoundation;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherGroup;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherRegistry;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import java.util.Comparator;

public class WeaponInventoryItemMatchers {
    public static final CompositeInventoryItemMatcher j;
    public static final CompositeInventoryItemMatcher m;
    public static final CompositeInventoryItemMatcher z;

    public static void initialize() {
        InventoryItemMatcherRegistry.register(m);
        InventoryItemMatcherRegistry.register(z);
        InventoryItemMatcherRegistry.register(j);
    }

    private static boolean matchesAnyWeapon(ItemStack itemStack, Item item) {
        return ItemStackScoreUtil.h(item) || ItemStackScoreUtil.T(item);
    }

    private static double axeSortScore(InventoryItemMatchContext inventoryItemMatchContext) {
        return ClientSettings.U(inventoryItemMatchContext.getItemStack());
    }

    private static boolean matchesAxe(ItemStack itemStack, Item item) {
        return ItemStackScoreUtil.T(item);
    }

    private static double swordSortScore(InventoryItemMatchContext inventoryItemMatchContext) {
        return ClientSettings.U(inventoryItemMatchContext.getItemStack());
    }

    static {
        String[] stringArray = new String[]{"weapons", "axe-weapon", "swords", "weapons", "Axe", "axe-weapon", "sword-hover@2x", "Any Weapon", "Any type of axe", "Sword", "Any type of weapon (sword or axe)", "Any type of sword"};
        m = ((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)InventoryItemMatcher.builder().composite().withId(stringArray[3])).withName(stringArray[7])).withDescription(stringArray[10])).withIconName(stringArray[0])).withGroup(InventoryItemMatcherGroup.WEAPONS)).withPredicate(WeaponInventoryItemMatchers::matchesAnyWeapon).withComparator(Comparator.comparingDouble(WeaponInventoryItemMatchers::anyWeaponSortScore))).build();
        z = ((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)InventoryItemMatcher.builder().composite().withId(stringArray[2])).withName(stringArray[9])).withDescription(stringArray[11])).withIconName(stringArray[6])).withGroup(InventoryItemMatcherGroup.WEAPONS)).withPredicate(WeaponInventoryItemMatchers::matchesSword).withComparator(Comparator.comparingDouble(WeaponInventoryItemMatchers::swordSortScore))).build();
        j = ((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)InventoryItemMatcher.builder().composite().withId(stringArray[1])).withName(stringArray[4])).withDescription(stringArray[8])).withIconName(stringArray[5])).withGroup(InventoryItemMatcherGroup.WEAPONS)).withPredicate(WeaponInventoryItemMatchers::matchesAxe).withComparator(Comparator.comparingDouble(WeaponInventoryItemMatchers::axeSortScore))).build();
    }

    private static boolean matchesSword(ItemStack itemStack, Item item) {
        return ItemStackScoreUtil.h(item);
    }

    private static double anyWeaponSortScore(InventoryItemMatchContext inventoryItemMatchContext) {
        return ClientSettings.U(inventoryItemMatchContext.getItemStack());
    }

}

