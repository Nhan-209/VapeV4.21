package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.InventoryFilterPresetData;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherPreset;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherPresetBuilder;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import gg.vape.wrapper.impl.ItemStack;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public class InventoryItemMatcherPresetRegistry {
    private static final Map<String, InventoryItemMatcherPreset> j;
    public static InventoryItemMatcherPreset L;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Nullable
    public static InventoryItemMatcherPreset R(String string) {
        return j.get(string);
    }

    public static List<InventoryFilterPresetData> h(InventoryFilterRule inventoryFilterRule) {
        ArrayList<InventoryFilterPresetData> arrayList = new ArrayList<InventoryFilterPresetData>();
        InventoryItemMatcher inventoryItemMatcher = inventoryFilterRule.q().c();
        ItemStack itemStack = inventoryFilterRule.q().E();
        for (InventoryItemMatcherPreset inventoryItemMatcherPreset : j.values()) {
            if (!inventoryItemMatcherPreset.U().isEmpty() && (inventoryItemMatcher != null && !inventoryItemMatcherPreset.U().contains(inventoryItemMatcher) || itemStack != null && inventoryItemMatcherPreset.U().stream().noneMatch(arg_0 -> InventoryItemMatcherPresetRegistry.lambda$getRulesForFilterHolder$0(itemStack, arg_0)))) continue;
            arrayList.add(inventoryItemMatcherPreset);
        }
        return arrayList;
    }

    private static void d(InventoryItemMatcherPreset inventoryItemMatcherPreset) {
        j.put(inventoryItemMatcherPreset.getName(), inventoryItemMatcherPreset);
    }

    private static String a(byte[] byArray) {
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

    private static void y(InventoryItemMatcherPresetBuilder inventoryItemMatcherPresetBuilder) {
        InventoryItemMatcherPresetRegistry.d(inventoryItemMatcherPresetBuilder.z());
    }

    private static boolean lambda$getRulesForFilterHolder$0(ItemStack itemStack, InventoryItemMatcher inventoryItemMatcher) {
        return inventoryItemMatcher.g(itemStack, itemStack.getItem());
    }

    static {
        long l = ZkmLongKeyState.a(-5115948449218827586L, -1244080179424378416L, MethodHandles.lookup().lookupClass()).a(5476051244694L) ^ 0x2F062EE4B2AL;
        String string = "No rule";
        j = new LinkedHashMap<String, InventoryItemMatcherPreset>();
        L = InventoryItemMatcherPreset.J().B(string).z();
        InventoryItemMatcherPresetRegistry.d(L);
    }
}

