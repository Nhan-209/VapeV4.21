package gg.vape.module.utility.inventory.cleaner;

import gg.vape.config.ClientSettings;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.utility.inventory.cleaner.ClassInventoryItemMatcherBuilder;
import gg.vape.module.utility.inventory.cleaner.EmptySlotInventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatchContext;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherGroup;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherRegistry;
import gg.vape.module.utility.inventory.cleaner.InventoryMatcherListMode;
import gg.vape.module.utility.inventory.cleaner.StringInventoryItemMatcherBuilder;
import gg.vape.module.utility.inventory.cleaner.StringMatchOperator;
import gg.vape.wrapper.impl.ItemSplashPotion;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.PotionEffect;
import java.util.Comparator;
import java.util.List;

public class HiddenInventoryItemMatchers {
    public static final InventoryItemMatcher d;
    public static final InventoryItemMatcher J;
    public static final InventoryItemMatcher R;

    private static double lambda$static$0(InventoryItemMatchContext inventoryItemMatchContext) {
        return ClientSettings.c(inventoryItemMatchContext.getItemStack());
    }

    public static void initialize() {
        InventoryItemMatcherRegistry.register(EmptySlotInventoryItemMatcher.a);
        InventoryItemMatcherRegistry.register(R);
        InventoryItemMatcherRegistry.register(J);
        InventoryItemMatcherRegistry.register(d);
    }

    static {
        String[] stringArray = new String[]{"Any bow", "Any type of potion", "any-bow", "other@2x", "any-potion", "Any Item", "Any type of bow", "any-item", "Any type of item", "Any potion"};
        R = ((StringInventoryItemMatcherBuilder)((StringInventoryItemMatcherBuilder)((StringInventoryItemMatcherBuilder)((StringInventoryItemMatcherBuilder)((StringInventoryItemMatcherBuilder)InventoryItemMatcher.builder().stringMatcher().withId(stringArray[7])).withName(stringArray[5])).withDescription(stringArray[8])).withIconName(stringArray[3])).withGroup(InventoryItemMatcherGroup.HIDDEN)).addPattern("", StringMatchOperator.ANY).build();
        J = ((ClassInventoryItemMatcherBuilder)((ClassInventoryItemMatcherBuilder)((ClassInventoryItemMatcherBuilder)((ClassInventoryItemMatcherBuilder)((ClassInventoryItemMatcherBuilder)InventoryItemMatcher.builder().classMatcher().withId(stringArray[2])).withName(stringArray[0])).withDescription(stringArray[6])).withGroup(InventoryItemMatcherGroup.HIDDEN)).addClass(MappedClasses.Vl).withListMode(InventoryMatcherListMode.WHITELIST).withComparator(Comparator.comparingDouble(HiddenInventoryItemMatchers::lambda$static$0))).build();
        d = ((ClassInventoryItemMatcherBuilder)((ClassInventoryItemMatcherBuilder)((ClassInventoryItemMatcherBuilder)((ClassInventoryItemMatcherBuilder)((ClassInventoryItemMatcherBuilder)InventoryItemMatcher.builder().classMatcher().withId(stringArray[4])).withName(stringArray[9])).withDescription(stringArray[1])).withGroup(InventoryItemMatcherGroup.HIDDEN)).addClass(MappedClasses.Di).withListMode(InventoryMatcherListMode.WHITELIST).withComparator(HiddenInventoryItemMatchers::lambda$static$1)).build();
    }

    private static int lambda$static$1(InventoryItemMatchContext inventoryItemMatchContext, InventoryItemMatchContext inventoryItemMatchContext2) {
        ItemStack itemStack = inventoryItemMatchContext.getItemStack();
        ItemStack itemStack2 = inventoryItemMatchContext2.getItemStack();
        List<PotionEffect> list = new ItemSplashPotion(itemStack.getItem()).getPotionEffects(itemStack);
        List<PotionEffect> list2 = new ItemSplashPotion(itemStack2.getItem()).getPotionEffects(itemStack2);
        int n = 0;
        for (PotionEffect potionEffect : list) {
            int n2 = potionEffect.C();
            for (PotionEffect potionEffect2 : list2) {
                int n3 = potionEffect2.C();
                if (n2 != n3) continue;
                n += Integer.compare(potionEffect.L(), potionEffect2.L());
            }
        }
        return n;
    }
}
