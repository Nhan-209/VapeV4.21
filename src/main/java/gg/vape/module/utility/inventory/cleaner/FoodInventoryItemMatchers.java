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
import gg.vape.wrapper.impl.DataComponentMap;
import gg.vape.wrapper.impl.DataComponents;
import gg.vape.wrapper.impl.FoodProperties;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.TileEntityEnderChest;

public class FoodInventoryItemMatchers {
    public static final CompositeInventoryItemMatcher a;
    public static final StringInventoryItemMatcher O;

    private static boolean isFoodItem(ItemStack itemStack, Item item) {
        if (ForgeVersion.MC_1_20_6.d()) {
            DataComponentMap dataComponentMap = item.g();
            return dataComponentMap.V(DataComponents.d());
        }
        return item.isInstance(MappedClasses.DL);
    }

    static {
        String[] stringArray = new String[]{"baked_", "cooked_", "cooked-food-hover@2x", "Cooked food", "Any type of cooked food", "food-hover@2x", "Any food", "Any type of food", "cooked-food", "any-food"};
        a = ((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)((InventoryItemMatcherBuilderFoundation)InventoryItemMatcher.builder().composite().withId(stringArray[9])).withName(stringArray[6])).withDescription(stringArray[7])).withIconName(stringArray[5])).withGroup(InventoryItemMatcherGroup.FOOD)).withPredicate(FoodInventoryItemMatchers::isFoodItem).withComparator(FoodInventoryItemMatchers::compareByNutrition)).build();
        O = ((StringInventoryItemMatcherBuilder)((StringInventoryItemMatcherBuilder)((StringInventoryItemMatcherBuilder)((StringInventoryItemMatcherBuilder)((StringInventoryItemMatcherBuilder)((StringInventoryItemMatcherBuilder)InventoryItemMatcher.builder().stringMatcher().withId(stringArray[8])).withName(stringArray[3])).withDescription(stringArray[4])).withIconName(stringArray[2])).withGroup(InventoryItemMatcherGroup.FOOD)).addPattern(stringArray[1], StringMatchOperator.STARTS).addPattern(stringArray[0], StringMatchOperator.STARTS).withComparator(a.getComparator())).build();
    }

    public static void initialize() {
        InventoryItemMatcherRegistry.register(a);
        InventoryItemMatcherRegistry.register(O);
    }

    private static int compareByNutrition(InventoryItemMatchContext inventoryItemMatchContext, InventoryItemMatchContext inventoryItemMatchContext2) {
        if (ForgeVersion.MC_1_20_6.d()) {
            DataComponentMap dataComponentMap = inventoryItemMatchContext.getItem().g();
            DataComponentMap dataComponentMap2 = inventoryItemMatchContext2.getItem().g();
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
        TileEntityEnderChest tileEntityEnderChest = new TileEntityEnderChest(inventoryItemMatchContext.getItem());
        TileEntityEnderChest tileEntityEnderChest2 = new TileEntityEnderChest(inventoryItemMatchContext2.getItem());
        float f = (float)tileEntityEnderChest.o$src$I$tnn4wh() * tileEntityEnderChest.o();
        float f3 = (float)tileEntityEnderChest2.o$src$I$tnn4wh() * tileEntityEnderChest2.o();
        return Float.compare(f, f3);
    }

}

