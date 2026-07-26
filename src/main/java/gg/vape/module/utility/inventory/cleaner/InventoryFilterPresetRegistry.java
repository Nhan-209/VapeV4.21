package gg.vape.module.utility.inventory.cleaner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.Vape;
import gg.vape.config.ConfigJsonUtils;
import gg.vape.module.utility.InvCleaner;
import gg.vape.module.utility.inventory.cleaner.InventoryCleanerProfile;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterPresetStore;
import gg.vape.module.utility.inventory.cleaner.ItemInventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.SharedInventoryFilterPreset;
import gg.vape.module.utility.inventory.cleaner.SlotInventoryFilterRule;
import gg.vape.runtime.ObfuscatedRuntimeException;
import java.util.Iterator;

public class InventoryFilterPresetRegistry {
    private static boolean x;
    private final InventoryFilterPresetStore W = new InventoryFilterPresetStore();
    private final InventoryFilterPresetStore E = new InventoryFilterPresetStore();

    public InventoryFilterPresetStore g() {
        return this.W;
    }

    static {
        InventoryFilterPresetRegistry.e(false);
    }

    public static boolean w() {
        return x;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void L(JsonObject jsonObject) {
        JsonArray jsonArray = ConfigJsonUtils.q(jsonObject, "slotRules");
        if (jsonArray != null) {
            for (JsonElement element : jsonArray) {
                if (!element.isJsonObject()) continue;
                this.W.n(new SharedInventoryFilterPreset(element.getAsJsonObject()));
            }
        }
        JsonArray inventoryFilterRules = ConfigJsonUtils.q(jsonObject, "inventoryFilterRules");
        if (inventoryFilterRules != null) {
            for (JsonElement jsonElement : inventoryFilterRules) {
                if (!jsonElement.isJsonObject()) continue;
                this.E.n(new SharedInventoryFilterPreset(jsonElement.getAsJsonObject()));
            }
        }
    }

    public InventoryFilterPresetStore r() {
        return this.E;
    }

    public static void e(boolean bl) {
        x = bl;
    }

    public static boolean u() {
        boolean bl = InventoryFilterPresetRegistry.w();
        return true;
    }

    public JsonObject U() {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (SharedInventoryFilterPreset object : this.W.M()) {
            jsonArray.add((JsonElement)object.K());
        }
        JsonArray jsonArray2 = new JsonArray();
        for (SharedInventoryFilterPreset sharedInventoryFilterPreset : this.E.M()) {
            jsonArray2.add((JsonElement)sharedInventoryFilterPreset.K());
        }
        jsonObject.add("slotRules", (JsonElement)jsonArray);
        jsonObject.add("inventoryFilterRules", (JsonElement)jsonArray2);
        return jsonObject;
    }

    public void Z(SharedInventoryFilterPreset sharedInventoryFilterPreset) {
        InvCleaner invCleaner = Vape.INSTANCE.getModManager().getMod(InvCleaner.class);
        for (InventoryCleanerProfile inventoryCleanerProfile : invCleaner.E$src$Lgg_vape_module_utility_inventory_cleaner_Invent$199cpgr().w()) {
            for (ItemInventoryFilterRule itemInventoryFilterRule : inventoryCleanerProfile.Q()) {
                if (!sharedInventoryFilterPreset.equals(itemInventoryFilterRule.W())) continue;
                itemInventoryFilterRule.U();
            }
            for (SlotInventoryFilterRule slotInventoryFilterRule : inventoryCleanerProfile.P()) {
                if (!sharedInventoryFilterPreset.equals(slotInventoryFilterRule.W())) continue;
                slotInventoryFilterRule.U();
            }
        }
    }
}
