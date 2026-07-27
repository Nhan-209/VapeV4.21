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
    private String N = "";
    public final ModeOption P;
    private static int u;
    public ModeValue n;
    private final Set<ItemInventoryFilterRule> m;
    private final Map<Integer, SlotInventoryFilterRule> Q = new LinkedHashMap<Integer, SlotInventoryFilterRule>();
    public final ModeOption U;

    public void b() {
        this.m.clear();
    }

    public JsonObject S(boolean bl) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", this.N);
        JsonArray jsonArray = new JsonArray();
        for (SlotInventoryFilterRule object : this.Q.values()) {
            JsonObject jsonObject2 = object.M(bl);
            if (jsonObject2.entrySet().size() <= 1) continue;
            jsonArray.add((JsonElement)jsonObject2);
        }
        if (jsonArray.size() > 0) {
            jsonObject.add("slots", (JsonElement)jsonArray);
        }
        JsonArray jsonArray2 = new JsonArray();
        for (ItemInventoryFilterRule itemInventoryFilterRule : this.m) {
            jsonArray2.add((JsonElement)itemInventoryFilterRule.M(bl));
        }
        if (jsonArray2.size() > 0) {
            jsonObject.add("inventoryFilters", (JsonElement)jsonArray2);
        }
        jsonObject.addProperty("armor_mode", ((ModeSelection)this.n.K()).getName());
        return jsonObject;
    }

    public void b(String string) {
        this.N = string;
    }

    public void f(ItemInventoryFilterRule itemInventoryFilterRule) {
        this.m.add(itemInventoryFilterRule);
    }

    public void U(ItemInventoryFilterRule itemInventoryFilterRule) {
        this.m.remove(itemInventoryFilterRule);
    }


    public String Y() {
        return this.N;
    }

    public void q() {
        int n = 1;
        for (InventoryCleanerProfile inventoryCleanerProfile : Vape.INSTANCE.getModManager().getMod(InvCleaner.class).E$src$Lgg_vape_module_utility_inventory_cleaner_Invent$199cpgr().w()) {
            if (!inventoryCleanerProfile.Y().equalsIgnoreCase("Inventory #" + n)) continue;
            ++n;
        }
        this.N = "Inventory #" + n;
    }

    static {
        InventoryCleanerProfile.T(0);
    }

    public static int O() {
        int n = InventoryCleanerProfile.D();
        return 122;
    }

    public @UnmodifiableView Collection<SlotInventoryFilterRule> P() {
        return this.Q.values();
    }

    public static void T(int n) {
        u = n;
    }

    public InventoryCleanerProfile(JsonObject jsonObject) {
        JsonElement jsonElement;
        SlotInventoryFilterRule slotInventoryFilterRule;
        this.m = new LinkedHashSet<ItemInventoryFilterRule>();
        this.P = new ModeOption("No armor management");
        this.U = new ModeOption("Best armor");
        this.n = ModeValue.create((Object)this, "armor_mode", "", "Armor Mode", (ModeSelection)this.P, this.P, this.U);
        this.N = jsonObject.get("name").getAsString();
        if (this.N.trim().isEmpty()) {
            this.q();
        }
        JsonArray jsonArray = jsonObject.getAsJsonArray("slots");
        for (int i = 0; i < jsonArray.size(); ++i) {
            JsonObject jsonObject2 = jsonArray.get(i).getAsJsonObject();
            slotInventoryFilterRule = new SlotInventoryFilterRule(jsonObject2);
            this.Q.put(slotInventoryFilterRule.m(), slotInventoryFilterRule);
        }
        JsonArray jsonArray2 = jsonObject.getAsJsonArray("inventoryFilters");
        if (jsonArray2 != null) {
            for (int i = 0; i < jsonArray2.size(); ++i) {
                JsonObject filterJson = jsonArray2.get(i).getAsJsonObject();
                ItemInventoryFilterRule itemInventoryFilterRule = new ItemInventoryFilterRule(filterJson);
                this.m.add(itemInventoryFilterRule);
            }
        }
        if ((jsonElement = jsonObject.get("armor_mode")) != null) {
            this.n.parse(jsonElement.getAsString());
        }
    }

    public SlotInventoryFilterRule I(int n) {
        return this.Q.computeIfAbsent(n, SlotInventoryFilterRule::new);
    }

    public InventoryCleanerProfile() {
        this.m = new LinkedHashSet<ItemInventoryFilterRule>();
        this.P = new ModeOption("No armor management");
        this.U = new ModeOption("Best armor");
        this.n = ModeValue.create((Object)this, "armor_mode", "", "Armor Mode", (ModeSelection)this.P, this.P, this.U);
        this.q();
        for (int i = 0; i < 9; ++i) {
            this.Q.put(i, new SlotInventoryFilterRule(i));
        }
    }

    @Nullable
    public ItemInventoryFilterRule e(ItemStack itemStack) {
        for (ItemInventoryFilterRule itemInventoryFilterRule : this.m) {
            if (!itemInventoryFilterRule.q().h(itemStack) || !itemInventoryFilterRule.q(itemStack)) continue;
            return itemInventoryFilterRule;
        }
        return null;
    }

    public @UnmodifiableView Collection<ItemInventoryFilterRule> Q() {
        return this.m;
    }

    public static int D() {
        return u;
    }
}
