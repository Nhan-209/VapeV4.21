package gg.vape.module.utility.inventory.cleaner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.module.utility.inventory.cleaner.InventoryCleanerProfile;
import gg.vape.value.Value;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

public class InventoryCleanerProfileValue
extends Value<InventoryCleanerProfile, InventoryCleanerProfileValue> {
    private final List<InventoryCleanerProfile> M = new ArrayList<InventoryCleanerProfile>();

    @Nullable
    public InventoryCleanerProfile f(String string) {
        for (InventoryCleanerProfile inventoryCleanerProfile : this.M) {
            if (!inventoryCleanerProfile.Y().equalsIgnoreCase(string)) continue;
            return inventoryCleanerProfile;
        }
        return null;
    }

    public void i(InventoryCleanerProfile inventoryCleanerProfile) {
        this.M.remove(inventoryCleanerProfile);
        this.g$src$V$1akzyia();
    }

    public @UnmodifiableView List<InventoryCleanerProfile> w() {
        return this.M;
    }

    @Override
    public void S() {
        super.S();
        if (this.N$src$Z$1a793rp()) {
            this.M.clear();
        }
    }

    public static InventoryCleanerProfileValue Q(Object object, String string) {
        return new InventoryCleanerProfileValue(object, string);
    }

    public InventoryCleanerProfileValue P$src$Lgg_vape_module_utility_inventory_cleaner_Invent$t67h7j() {
        return new InventoryCleanerProfileValue(null, this.P$src$Ljava_lang_String_$1ijjhmj());
    }

    @Override
    public InventoryCleanerProfileValue getALimit() {
        return this.P$src$Lgg_vape_module_utility_inventory_cleaner_Invent$t67h7j();
    }

    @Override
    public JsonObject H(boolean bl) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", this.P$src$Ljava_lang_String_$1ijjhmj());
        InventoryCleanerProfile inventoryCleanerProfile = (InventoryCleanerProfile)this.K();
        if (inventoryCleanerProfile != null) {
            jsonObject.addProperty("selected", inventoryCleanerProfile.Y());
        }
        if (!this.M.isEmpty()) {
            JsonArray jsonArray = new JsonArray();
            for (InventoryCleanerProfile inventoryCleanerProfile2 : this.M) {
                jsonArray.add((JsonElement)inventoryCleanerProfile2.S(bl));
            }
            jsonObject.add("inventories", (JsonElement)jsonArray);
        }
        return jsonObject;
    }

    @Override
    public String c() {
        InventoryCleanerProfile inventoryCleanerProfile = (InventoryCleanerProfile)this.K();
        if (inventoryCleanerProfile == null) {
            return "";
        }
        return inventoryCleanerProfile.Y();
    }

    @Override
    public void parse(String string) {
    }

    @Override
    public boolean loadJson(JsonObject jsonObject) {
        if (jsonObject.get("id").getAsString().equalsIgnoreCase(this.P$src$Ljava_lang_String_$1ijjhmj())) {
            this.M.clear();
            if (jsonObject.has("inventories")) {
                JsonArray jsonArray = jsonObject.getAsJsonArray("inventories");
                for (JsonElement jsonElement : jsonArray) {
                    InventoryCleanerProfile inventoryCleanerProfile = new InventoryCleanerProfile(jsonElement.getAsJsonObject());
                    this.M.add(inventoryCleanerProfile);
                }
            }
            if (jsonObject.has("selected")) {
                this.o(this.f(jsonObject.get("selected").getAsString()));
            }
            return true;
        }
        return false;
    }

    public InventoryCleanerProfileValue(Object object, String string) {
        super(object, string, null);
    }


    public void I(InventoryCleanerProfile inventoryCleanerProfile) {
        this.M.add(inventoryCleanerProfile);
        this.g$src$V$1akzyia();
    }
}
