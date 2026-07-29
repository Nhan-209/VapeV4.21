package gg.vape.module.utility.inventory.cleaner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.Vape;
import gg.vape.module.utility.InvCleaner;
import gg.vape.module.utility.inventory.cleaner.ItemInventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.SlotInventoryFilterRule;
import gg.vape.unmap.ModeOption;
import gg.vape.unmap.ModeSelection;
import gg.vape.value.ModeValue;
import gg.vape.wrapper.impl.ItemStack;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

public class InventoryCleanerProfile {
    private String name = "";
    public final ModeOption noArmorManagement;
    public ModeValue armorMode;
    private final Set<ItemInventoryFilterRule> itemRules;
    private final Map<Integer, SlotInventoryFilterRule> slotRules = new LinkedHashMap<Integer, SlotInventoryFilterRule>();
    public final ModeOption bestArmor;

    public void clearItemRules() {
        this.itemRules.clear();
    }

    public JsonObject toJson(boolean embedSharedPresets) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", this.name);
        JsonArray jsonArray = new JsonArray();
        for (SlotInventoryFilterRule rule : this.slotRules.values()) {
            JsonObject jsonObject2 = rule.toJson(embedSharedPresets);
            if (jsonObject2.entrySet().size() <= 1) continue;
            jsonArray.add((JsonElement)jsonObject2);
        }
        if (jsonArray.size() > 0) {
            jsonObject.add("slots", (JsonElement)jsonArray);
        }
        JsonArray jsonArray2 = new JsonArray();
        for (ItemInventoryFilterRule itemRule : this.itemRules) {
            jsonArray2.add((JsonElement)itemRule.toJson(embedSharedPresets));
        }
        if (jsonArray2.size() > 0) {
            jsonObject.add("inventoryFilters", (JsonElement)jsonArray2);
        }
        jsonObject.addProperty("armor_mode", ((ModeSelection)this.armorMode.getValue()).getName());
        return jsonObject;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addItemRule(ItemInventoryFilterRule itemRule) {
        this.itemRules.add(itemRule);
    }

    public void removeItemRule(ItemInventoryFilterRule itemRule) {
        this.itemRules.remove(itemRule);
    }


    public String getName() {
        return this.name;
    }

    public void assignDefaultName() {
        int suffix = 1;
        for (InventoryCleanerProfile profile : Vape.INSTANCE.getModManager().getMod(InvCleaner.class).getProfileValue().getProfiles()) {
            if (!profile.getName().equalsIgnoreCase("Inventory #" + suffix)) continue;
            ++suffix;
        }
        this.name = "Inventory #" + suffix;
    }

    public @UnmodifiableView Collection<SlotInventoryFilterRule> getSlotRules() {
        return this.slotRules.values();
    }

    public InventoryCleanerProfile(JsonObject jsonObject) {
        JsonElement jsonElement;
        SlotInventoryFilterRule slotInventoryFilterRule;
        this.itemRules = new LinkedHashSet<ItemInventoryFilterRule>();
        this.noArmorManagement = new ModeOption("No armor management");
        this.bestArmor = new ModeOption("Best armor");
        this.armorMode = ModeValue.create((Object)this, "armor_mode", "", "Armor Mode", (ModeSelection)this.noArmorManagement, this.noArmorManagement, this.bestArmor);
        this.name = jsonObject.get("name").getAsString();
        if (this.name.trim().isEmpty()) {
            this.assignDefaultName();
        }
        JsonArray jsonArray = jsonObject.getAsJsonArray("slots");
        for (int i = 0; i < jsonArray.size(); ++i) {
            JsonObject jsonObject2 = jsonArray.get(i).getAsJsonObject();
            slotInventoryFilterRule = new SlotInventoryFilterRule(jsonObject2);
            this.slotRules.put(slotInventoryFilterRule.getSlot(), slotInventoryFilterRule);
        }
        JsonArray jsonArray2 = jsonObject.getAsJsonArray("inventoryFilters");
        if (jsonArray2 != null) {
            for (int i = 0; i < jsonArray2.size(); ++i) {
                JsonObject filterJson = jsonArray2.get(i).getAsJsonObject();
                ItemInventoryFilterRule itemInventoryFilterRule = new ItemInventoryFilterRule(filterJson);
                this.itemRules.add(itemInventoryFilterRule);
            }
        }
        if ((jsonElement = jsonObject.get("armor_mode")) != null) {
            this.armorMode.parse(jsonElement.getAsString());
        }
    }

    public SlotInventoryFilterRule getOrCreateSlotRule(int slot) {
        return this.slotRules.computeIfAbsent(slot, SlotInventoryFilterRule::new);
    }

    public InventoryCleanerProfile() {
        this.itemRules = new LinkedHashSet<ItemInventoryFilterRule>();
        this.noArmorManagement = new ModeOption("No armor management");
        this.bestArmor = new ModeOption("Best armor");
        this.armorMode = ModeValue.create((Object)this, "armor_mode", "", "Armor Mode", (ModeSelection)this.noArmorManagement, this.noArmorManagement, this.bestArmor);
        this.assignDefaultName();
        for (int i = 0; i < 9; ++i) {
            this.slotRules.put(i, new SlotInventoryFilterRule(i));
        }
    }

    @Nullable
    public ItemInventoryFilterRule findMatchingItemRule(ItemStack itemStack) {
        for (ItemInventoryFilterRule itemRule : this.itemRules) {
            if (!itemRule.getItemSelection().matches(itemStack) || !itemRule.matches(itemStack)) continue;
            return itemRule;
        }
        return null;
    }

    public @UnmodifiableView Collection<ItemInventoryFilterRule> getItemRules() {
        return this.itemRules;
    }
}
