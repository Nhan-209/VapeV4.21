package gg.vape.module.utility.inventory.cleaner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.Vape;
import gg.vape.config.ConfigJsonUtils;
import gg.vape.module.utility.inventory.cleaner.AbstractInventoryFilterPreset;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionGroup;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.SharedInventoryFilterPreset;
import gg.vape.module.utility.inventory.cleaner.SlotInventoryFilterRule;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public class InventoryFilterPreset
extends AbstractInventoryFilterPreset
implements Cloneable {
    protected UUID h;
    private static boolean u;
    private final List<InventoryFilterConditionGroup> M = new ArrayList<InventoryFilterConditionGroup>();

    public static void d(boolean bl) {
        u = bl;
    }

    public InventoryFilterPreset(boolean bl) {
        this(null, "");
        this.I(bl);
    }

    public static boolean r() {
        return u;
    }

    static {
        InventoryFilterPreset.d(true);
    }

    public UUID j() {
        return this.h;
    }


    public InventoryFilterPreset(@Nullable UUID uUID, String string) {
        super(string);
        this.h = uUID != null ? uUID : UUID.randomUUID();
    }

    @Override
    public List<InventoryFilterConditionGroup> z() {
        return this.M;
    }

    public void J(String string) {
        this.W = string;
    }

    public JsonObject K() {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (InventoryFilterConditionGroup inventoryFilterConditionGroup : this.M) {
            jsonArray.add((JsonElement)inventoryFilterConditionGroup.g());
        }
        jsonObject.addProperty("uuid", this.h.toString());
        jsonObject.addProperty("name", this.getName());
        jsonObject.add("conditions", (JsonElement)jsonArray);
        return jsonObject;
    }

    public InventoryFilterPreset(JsonObject jsonObject) {
        this(ConfigJsonUtils.u(jsonObject, "uuid"), jsonObject.get("name").getAsString());
        JsonArray jsonArray = jsonObject.getAsJsonArray("conditions");
        for (JsonElement jsonElement : jsonArray) {
            this.x(new InventoryFilterConditionGroup(jsonElement.getAsJsonObject()));
        }
    }

    public SharedInventoryFilterPreset o(InventoryFilterRule inventoryFilterRule) {
        SharedInventoryFilterPreset sharedInventoryFilterPreset = new SharedInventoryFilterPreset(this);
        boolean bl = inventoryFilterRule instanceof SlotInventoryFilterRule;
        inventoryFilterRule.p(sharedInventoryFilterPreset);
        if (bl) {
            Vape.INSTANCE.getInventoryFilterPresetRegistry().g().u(null, sharedInventoryFilterPreset);
        } else {
            Vape.INSTANCE.getInventoryFilterPresetRegistry().r().u(null, sharedInventoryFilterPreset);
        }
        return sharedInventoryFilterPreset;
    }

    public static boolean v() {
        boolean bl = InventoryFilterPreset.r();
        return false;
    }

    public void x(InventoryFilterConditionGroup inventoryFilterConditionGroup) {
        this.M.add(inventoryFilterConditionGroup);
    }

    public InventoryFilterPreset s() {
        InventoryFilterPreset inventoryFilterPreset = new InventoryFilterPreset(this.j(), this.getName());
        for (InventoryFilterConditionGroup inventoryFilterConditionGroup : this.M) {
            inventoryFilterPreset.x(inventoryFilterConditionGroup.A());
        }
        return inventoryFilterPreset;
    }

    public InventoryFilterPreset(SharedInventoryFilterPreset sharedInventoryFilterPreset) {
        this(sharedInventoryFilterPreset.K());
        this.h = UUID.randomUUID();
    }

    public void I(boolean bl) {
        String string = (bl ? "Inventory Filter " : "Custom ") + "Rule #";
        int n = 1;
        for (InventoryFilterPreset inventoryFilterPreset : (!bl ? Vape.INSTANCE.getInventoryFilterPresetRegistry().g() : Vape.INSTANCE.getInventoryFilterPresetRegistry().r()).M()) {
            if (!inventoryFilterPreset.getName().equalsIgnoreCase(string + n)) continue;
            ++n;
        }
        this.W = string + n;
    }

    public void F(InventoryFilterConditionGroup inventoryFilterConditionGroup) {
        this.M.remove(inventoryFilterConditionGroup);
    }
}

