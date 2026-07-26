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
import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import gg.vape.wrapper.impl.ItemSplashPotion;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.PotionEffect;
import java.lang.invoke.MethodHandles;
import java.util.Comparator;
import java.util.List;

public class HiddenInventoryItemMatchers {
    public static final InventoryItemMatcher d;
    public static final InventoryItemMatcher J;
    public static final InventoryItemMatcher R;

    private static double lambda$static$0(InventoryItemMatchContext inventoryItemMatchContext) {
        return ClientSettings.c(inventoryItemMatchContext.v());
    }

    public static void D() {
        InventoryItemMatcherRegistry.R(EmptySlotInventoryItemMatcher.a);
        InventoryItemMatcherRegistry.R(R);
        InventoryItemMatcherRegistry.R(J);
        InventoryItemMatcherRegistry.R(d);
    }

    static {
        long l = ZkmLongKeyState.a(690085326167056579L, 2964139095904894394L, MethodHandles.lookup().lookupClass()).a(261407506857418L) ^ 0x4D66EE48C924L;
        String[] stringArray = new String[]{"Any bow", "Any type of potion", "any-bow", "other@2x", "any-potion", "Any Item", "Any type of bow", "any-item", "Any type of item", "Any potion"};
        R = ((StringInventoryItemMatcherBuilder)((StringInventoryItemMatcherBuilder)((StringInventoryItemMatcherBuilder)((StringInventoryItemMatcherBuilder)((StringInventoryItemMatcherBuilder)InventoryItemMatcher.c().y().n(stringArray[7])).m(stringArray[5])).M(stringArray[8])).H(stringArray[3])).A(InventoryItemMatcherGroup.HIDDEN)).R("", StringMatchOperator.ANY).g();
        J = ((ClassInventoryItemMatcherBuilder)((ClassInventoryItemMatcherBuilder)((ClassInventoryItemMatcherBuilder)((ClassInventoryItemMatcherBuilder)((ClassInventoryItemMatcherBuilder)InventoryItemMatcher.c().Y().n(stringArray[2])).m(stringArray[0])).M(stringArray[6])).A(InventoryItemMatcherGroup.HIDDEN)).Q(MappedClasses.Vl).p(InventoryMatcherListMode.WHITELIST).N(Comparator.comparingDouble(HiddenInventoryItemMatchers::lambda$static$0))).o();
        d = ((ClassInventoryItemMatcherBuilder)((ClassInventoryItemMatcherBuilder)((ClassInventoryItemMatcherBuilder)((ClassInventoryItemMatcherBuilder)((ClassInventoryItemMatcherBuilder)InventoryItemMatcher.c().Y().n(stringArray[4])).m(stringArray[9])).M(stringArray[1])).A(InventoryItemMatcherGroup.HIDDEN)).Q(MappedClasses.Di).p(InventoryMatcherListMode.WHITELIST).N(HiddenInventoryItemMatchers::lambda$static$1)).o();
    }

    private static int lambda$static$1(InventoryItemMatchContext inventoryItemMatchContext, InventoryItemMatchContext inventoryItemMatchContext2) {
        ItemStack itemStack = inventoryItemMatchContext.v();
        ItemStack itemStack2 = inventoryItemMatchContext2.v();
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

