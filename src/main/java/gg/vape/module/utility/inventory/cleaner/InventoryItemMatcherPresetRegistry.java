package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.InventoryFilterPresetData;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherPreset;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherPresetBuilder;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ItemStack;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public class InventoryItemMatcherPresetRegistry {
    private static final Map<String, InventoryItemMatcherPreset> presetsByName;
    public static InventoryItemMatcherPreset L;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Nullable
    public static InventoryItemMatcherPreset getByName(String string) {
        return presetsByName.get(string);
    }

    public static List<InventoryFilterPresetData> findMatchingPresets(InventoryFilterRule inventoryFilterRule) {
        ArrayList<InventoryFilterPresetData> arrayList = new ArrayList<InventoryFilterPresetData>();
        InventoryItemMatcher inventoryItemMatcher = inventoryFilterRule.q().c();
        ItemStack itemStack = inventoryFilterRule.q().E();
        for (InventoryItemMatcherPreset inventoryItemMatcherPreset : presetsByName.values()) {
            if (!inventoryItemMatcherPreset.U().isEmpty() && (inventoryItemMatcher != null && !inventoryItemMatcherPreset.U().contains(inventoryItemMatcher) || itemStack != null && inventoryItemMatcherPreset.U().stream().noneMatch(arg_0 -> InventoryItemMatcherPresetRegistry.matcherAcceptsStack(itemStack, arg_0)))) continue;
            arrayList.add(inventoryItemMatcherPreset);
        }
        return arrayList;
    }

    private static void register(InventoryItemMatcherPreset inventoryItemMatcherPreset) {
        presetsByName.put(inventoryItemMatcherPreset.getName(), inventoryItemMatcherPreset);
    }

    private static String decodeUtf8(byte[] byArray) {
        int n = 0;
        int n2 = byArray.length;
        char[] cArray = new char[n2];
        for (int i = 0; i < n2; ++i) {
            char c;
            int n3 = 0xFF & byArray[i];
            if (n3 < 192) {
                cArray[n++] = (char)n3;
                continue;
            }
            if (n3 < 224) {
                c = (char)((char)(n3 & 0x1F) << 6);
                n3 = byArray[++i];
                c = (char)(c | (char)(n3 & 0x3F));
                cArray[n++] = c;
                continue;
            }
            if (i >= n2 - 2) continue;
            c = (char)((char)(n3 & 0xF) << 12);
            n3 = byArray[++i];
            c = (char)(c | (char)(n3 & 0x3F) << 6);
            n3 = byArray[++i];
            c = (char)(c | (char)(n3 & 0x3F));
            cArray[n++] = c;
        }
        return new String(cArray, 0, n);
    }

    private static void registerFromBuilder(InventoryItemMatcherPresetBuilder inventoryItemMatcherPresetBuilder) {
        InventoryItemMatcherPresetRegistry.register(inventoryItemMatcherPresetBuilder.build());
    }

    private static boolean matcherAcceptsStack(ItemStack itemStack, InventoryItemMatcher inventoryItemMatcher) {
        return inventoryItemMatcher.g(itemStack, itemStack.getItem());
    }

    static {
        String string = "No rule";
        presetsByName = new LinkedHashMap<String, InventoryItemMatcherPreset>();
        L = InventoryItemMatcherPreset.J().name(string).build();
        InventoryItemMatcherPresetRegistry.register(L);
    }
}

