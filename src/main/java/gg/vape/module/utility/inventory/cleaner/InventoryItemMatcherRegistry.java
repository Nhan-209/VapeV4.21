package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.ArmorInventoryItemMatchers;
import gg.vape.module.utility.inventory.cleaner.BlockInventoryItemMatchers;
import gg.vape.module.utility.inventory.cleaner.EmptySlotInventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.FoodInventoryItemMatchers;
import gg.vape.module.utility.inventory.cleaner.HiddenInventoryItemMatchers;
import gg.vape.module.utility.inventory.cleaner.InventoryItemCategoryRegistry;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherGroup;
import gg.vape.module.utility.inventory.cleaner.ToolInventoryItemMatchers;
import gg.vape.module.utility.inventory.cleaner.WeaponInventoryItemMatchers;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

public class InventoryItemMatcherRegistry {
    private static final Map<String, InventoryItemMatcher> matchersByName = new LinkedHashMap<String, InventoryItemMatcher>();
    private static final Map<InventoryItemMatcherGroup, List<InventoryItemMatcher>> matchersByGroup = new LinkedHashMap<InventoryItemMatcherGroup, List<InventoryItemMatcher>>();

    public static @UnmodifiableView Collection<InventoryItemMatcher> Y() {
        return matchersByName.values();
    }

    private static List createGroupList(InventoryItemMatcherGroup inventoryItemMatcherGroup) {
        return new ArrayList();
    }

    @Nullable
    public static InventoryItemMatcher z(String string) {
        return matchersByName.get(string);
    }

    public static void R(InventoryItemMatcher inventoryItemMatcher) {
        matchersByName.put(inventoryItemMatcher.k(), inventoryItemMatcher);
        matchersByGroup.computeIfAbsent(inventoryItemMatcher.l(), InventoryItemMatcherRegistry::createGroupList).add(inventoryItemMatcher);
    }

    public static @UnmodifiableView List<InventoryItemMatcher> N(InventoryItemMatcherGroup inventoryItemMatcherGroup) {
        return matchersByGroup.get(inventoryItemMatcherGroup);
    }

    @Nullable
    public static InventoryItemMatcher S(ItemStack itemStack) {
        if (itemStack.isNull()) {
            return EmptySlotInventoryItemMatcher.a;
        }
        Item item = itemStack.getItem();
        if (item.isNull()) {
            return EmptySlotInventoryItemMatcher.a;
        }
        ArrayList<InventoryItemMatcher> arrayList = new ArrayList<InventoryItemMatcher>();
        for (InventoryItemMatcher inventoryItemMatcher : matchersByName.values()) {
            if (!inventoryItemMatcher.g(itemStack, itemStack.getItem())) continue;
            arrayList.add(inventoryItemMatcher);
        }
        arrayList.sort(InventoryItemMatcherRegistry::compareByPriority);
        Collections.reverse(arrayList);
        InventoryItemMatcher inventoryItemMatcher = arrayList.isEmpty() ? null : (InventoryItemMatcher)arrayList.get(0);
        return inventoryItemMatcher;
    }

    private static int compareByPriority(InventoryItemMatcher inventoryItemMatcher, InventoryItemMatcher inventoryItemMatcher2) {
        boolean hasPriorityFirst = inventoryItemMatcher.v() != null;
        boolean hasPrioritySecond = inventoryItemMatcher2.v() != null;
        return Boolean.compare(hasPriorityFirst, hasPrioritySecond);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    static {
        HiddenInventoryItemMatchers.D();
        WeaponInventoryItemMatchers.K();
        ToolInventoryItemMatchers.r();
        FoodInventoryItemMatchers.j();
        BlockInventoryItemMatchers.C();
        ArmorInventoryItemMatchers.v();
        InventoryItemCategoryRegistry.F();
    }
}

