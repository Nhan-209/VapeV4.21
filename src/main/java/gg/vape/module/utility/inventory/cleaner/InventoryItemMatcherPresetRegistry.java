package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.InventoryFilterPresetData;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherPreset;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherPresetBuilder;
import gg.vape.wrapper.impl.ItemStack;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public class InventoryItemMatcherPresetRegistry {
    private static final Map<String, InventoryItemMatcherPreset> presetsByName;
    public static final InventoryItemMatcherPreset NO_RULE;


    @Nullable
    public static InventoryItemMatcherPreset getByName(String string) {
        return presetsByName.get(string);
    }

    public static List<InventoryFilterPresetData> findMatchingPresets(InventoryFilterRule inventoryFilterRule) {
        ArrayList<InventoryFilterPresetData> arrayList = new ArrayList<InventoryFilterPresetData>();
        InventoryItemMatcher inventoryItemMatcher = inventoryFilterRule.getItemSelection().getMatcher();
        ItemStack itemStack = inventoryFilterRule.getItemSelection().getItemStack();
        for (InventoryItemMatcherPreset inventoryItemMatcherPreset : presetsByName.values()) {
            if (!inventoryItemMatcherPreset.getMatchers().isEmpty() && (inventoryItemMatcher != null && !inventoryItemMatcherPreset.getMatchers().contains(inventoryItemMatcher) || itemStack != null && inventoryItemMatcherPreset.getMatchers().stream().noneMatch(arg_0 -> InventoryItemMatcherPresetRegistry.matcherAcceptsStack(itemStack, arg_0)))) continue;
            arrayList.add(inventoryItemMatcherPreset);
        }
        return arrayList;
    }

    private static void register(InventoryItemMatcherPreset inventoryItemMatcherPreset) {
        presetsByName.put(inventoryItemMatcherPreset.getName(), inventoryItemMatcherPreset);
    }

    private static boolean matcherAcceptsStack(ItemStack itemStack, InventoryItemMatcher inventoryItemMatcher) {
        return inventoryItemMatcher.matches(itemStack, itemStack.getItem());
    }

    static {
        String string = "No rule";
        presetsByName = new LinkedHashMap<String, InventoryItemMatcherPreset>();
        NO_RULE = InventoryItemMatcherPreset.builder().name(string).build();
        InventoryItemMatcherPresetRegistry.register(NO_RULE);
    }
}

