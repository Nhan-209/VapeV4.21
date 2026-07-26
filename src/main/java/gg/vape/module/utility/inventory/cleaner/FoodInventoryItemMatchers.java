package gg.vape.module.utility.inventory.cleaner;

import gg.vape.mapping.MappedClasses;
import gg.vape.module.utility.inventory.cleaner.CompositeInventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatchContext;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherBuilderFoundation;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherGroup;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherRegistry;
import gg.vape.module.utility.inventory.cleaner.StringInventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.StringInventoryItemMatcherBuilder;
import gg.vape.module.utility.inventory.cleaner.StringMatchOperator;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import gg.vape.wrapper.impl.DataComponentMap;
import gg.vape.wrapper.impl.DataComponents;
import gg.vape.wrapper.impl.FoodProperties;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.TileEntityEnderChest;
import java.lang.invoke.MethodHandles;

public class FoodInventoryItemMatchers {
    public static final CompositeInventoryItemMatcher a;
    public static final StringInventoryItemMatcher O;

    private static boolean lambda$static$0(ItemStack itemStack, Item item) {
        if (ForgeVersion.MC_1_20_6.d()) {
            DataComponentMap dataComponentMap = item.g();
            return dataComponentMap.V(DataComponents.d());
        }
        return item.isInstance(MappedClasses.DL);
    }

    static {
        long l = ZkmLongKeyState.a(2786964799863358521L, 7483758666246314709L, MethodHandles.lookup().lookupClass()).a(247062353089996L) ^ 0x23BF6F382913L;
        String[] stringArray = new String[]{"baked_", "cooked_", "cooked-food-hover@2x", "Cooked food", "Any type of cooked food", "food-hover@2x", "Any food", "Any type of food", "cooked-food", "any-food"};
        a = ((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)InventoryItemMatcher.c().t().n(stringArray[9])).m(stringArray[6])).M(stringArray[7])).H(stringArray[5])).A(InventoryItemMatcherGroup.FOOD)).t(FoodInventoryItemMatchers::lambda$static$0).N(FoodInventoryItemMatchers::lambda$static$1)).q();
        O = ((StringInventoryItemMatcherBuilder)((StringInventoryItemMatcherBuilder)((StringInventoryItemMatcherBuilder)((StringInventoryItemMatcherBuilder)((StringInventoryItemMatcherBuilder)((StringInventoryItemMatcherBuilder)InventoryItemMatcher.c().y().n(stringArray[8])).m(stringArray[3])).M(stringArray[4])).H(stringArray[2])).A(InventoryItemMatcherGroup.FOOD)).R(stringArray[1], StringMatchOperator.STARTS).R(stringArray[0], StringMatchOperator.STARTS).N(a.v())).g();
    }

    public static void j() {
        InventoryItemMatcherRegistry.R(a);
        InventoryItemMatcherRegistry.R(O);
    }

    private static int lambda$static$1(InventoryItemMatchContext inventoryItemMatchContext, InventoryItemMatchContext inventoryItemMatchContext2) {
        if (ForgeVersion.MC_1_20_6.d()) {
            DataComponentMap dataComponentMap = inventoryItemMatchContext.M().g();
            DataComponentMap dataComponentMap2 = inventoryItemMatchContext2.M().g();
            Object object = dataComponentMap.E(DataComponents.d());
            Object object2 = dataComponentMap2.E(DataComponents.d());
            if (object == null || object2 == null) {
                return 0;
            }
            FoodProperties foodProperties = new FoodProperties(object);
            FoodProperties foodProperties2 = new FoodProperties(object2);
            float f = (float)foodProperties.n() * foodProperties.M();
            float f2 = (float)foodProperties2.n() * foodProperties2.M();
            return Float.compare(f, f2);
        }
        TileEntityEnderChest tileEntityEnderChest = new TileEntityEnderChest(inventoryItemMatchContext.M());
        TileEntityEnderChest tileEntityEnderChest2 = new TileEntityEnderChest(inventoryItemMatchContext2.M());
        float f = (float)tileEntityEnderChest.o$src$I$tnn4wh() * tileEntityEnderChest.o();
        float f3 = (float)tileEntityEnderChest2.o$src$I$tnn4wh() * tileEntityEnderChest2.o();
        return Float.compare(f, f3);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

