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
    private final List<InventoryCleanerProfile> profiles = new ArrayList<InventoryCleanerProfile>();

    @Nullable
    public InventoryCleanerProfile findByName(String name) {
        for (InventoryCleanerProfile profile : this.profiles) {
            if (!profile.getName().equalsIgnoreCase(name)) continue;
            return profile;
        }
        return null;
    }

    public void removeProfile(InventoryCleanerProfile profile) {
        this.profiles.remove(profile);
        this.notifyChanged();
    }

    public @UnmodifiableView List<InventoryCleanerProfile> getProfiles() {
        return this.profiles;
    }

    @Override
    public void reset() {
        super.reset();
        if (this.isResettable()) {
            this.profiles.clear();
        }
    }

    public static InventoryCleanerProfileValue create(Object owner, String id) {
        return new InventoryCleanerProfileValue(owner, id);
    }

    @Override
    public InventoryCleanerProfileValue copyValueDefinition() {
        return new InventoryCleanerProfileValue(null, this.getId());
    }

    @Override
    public JsonObject toJson(boolean bl) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", this.getId());
        InventoryCleanerProfile inventoryCleanerProfile = (InventoryCleanerProfile)this.getValue();
        if (inventoryCleanerProfile != null) {
            jsonObject.addProperty("selected", inventoryCleanerProfile.getName());
        }
        if (!this.profiles.isEmpty()) {
            JsonArray jsonArray = new JsonArray();
            for (InventoryCleanerProfile inventoryCleanerProfile2 : this.profiles) {
                jsonArray.add((JsonElement)inventoryCleanerProfile2.toJson(bl));
            }
            jsonObject.add("inventories", (JsonElement)jsonArray);
        }
        return jsonObject;
    }

    @Override
    public String getDisplayValue() {
        InventoryCleanerProfile inventoryCleanerProfile = (InventoryCleanerProfile)this.getValue();
        if (inventoryCleanerProfile == null) {
            return "";
        }
        return inventoryCleanerProfile.getName();
    }

    @Override
    public void parse(String string) {
    }

    @Override
    public boolean loadJson(JsonObject jsonObject) {
        if (jsonObject.get("id").getAsString().equalsIgnoreCase(this.getId())) {
            this.profiles.clear();
            if (jsonObject.has("inventories")) {
                JsonArray jsonArray = jsonObject.getAsJsonArray("inventories");
                for (JsonElement jsonElement : jsonArray) {
                    InventoryCleanerProfile inventoryCleanerProfile = new InventoryCleanerProfile(jsonElement.getAsJsonObject());
                    this.profiles.add(inventoryCleanerProfile);
                }
            }
            if (jsonObject.has("selected")) {
                this.setValue(this.findByName(jsonObject.get("selected").getAsString()));
            }
            return true;
        }
        return false;
    }

    public InventoryCleanerProfileValue(Object object, String string) {
        super(object, string, null);
    }


    public void addProfile(InventoryCleanerProfile profile) {
        this.profiles.add(profile);
        this.notifyChanged();
    }
}
