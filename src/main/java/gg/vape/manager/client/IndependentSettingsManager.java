package gg.vape.manager.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.Vape;
import gg.vape.module.none.ClientSettings;
import gg.vape.value.Value;
import java.util.ArrayList;
import java.util.List;

public class IndependentSettingsManager {
    private final List<Value<?, ?>> D = new ArrayList();


    public JsonArray toJson() {
        JsonArray jsonArray = new JsonArray();
        for (Value<?, ?> jsonObject2 : this.D) {
            if (jsonObject2.isDefault()) continue;
            jsonArray.add((JsonElement)jsonObject2.toJson(false));
        }
        JsonObject jsonObject3 = new JsonObject();
        jsonObject3.add("enemies", (JsonElement)Vape.INSTANCE.getEnemyManager().H());
        jsonArray.add((JsonElement)jsonObject3);
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("frames", (JsonElement)ClientSettings.INSTANCE.serializeFrameStates());
        jsonArray.add((JsonElement)jsonObject);
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.add("inventoryManager", (JsonElement)Vape.INSTANCE.getInventoryFilterPresetRegistry().toJson());
        jsonArray.add((JsonElement)jsonObject2);
        return jsonArray;
    }

    public List<Value<?, ?>> values() {
        return this.D;
    }

    public void loadIndependentSettings(JsonArray jsonArray) {
        if (jsonArray.size() == 0) {
            return;
        }
        for (int i = 0; i < jsonArray.size(); ++i) {
            JsonArray jsonArray2;
            JsonElement jsonElement = jsonArray.get(i);
            if (!jsonElement.isJsonObject() || jsonElement.isJsonNull()) continue;
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            for (Value<?, ?> value : this.D) {
                if (!value.matchesJsonId(jsonObject)) continue;
                value.loadJson(jsonObject);
            }
            if (jsonObject.get("enemies") != null && !jsonObject.get("enemies").isJsonNull()) {
                jsonArray2 = jsonObject.get("enemies").getAsJsonArray();
                Vape.INSTANCE.getEnemyManager().d(jsonArray2);
            }
            if (jsonObject.get("frames") != null && !jsonObject.get("frames").isJsonNull()) {
                jsonArray2 = jsonObject.get("frames").getAsJsonArray();
                if (!Vape.INSTANCE.getPublicProfileSettings().Z.getEffectiveValue().booleanValue()) {
                    JsonArray jsonArray3 = new JsonArray();
                    jsonArray3.add((JsonElement)jsonArray2);
                    ClientSettings.INSTANCE.loadFrameStates(jsonArray3);
                }
            }
            if (jsonObject.get("inventoryManager") == null || jsonObject.get("inventoryManager").isJsonNull()) continue;
            JsonObject inventoryManager = jsonObject.get("inventoryManager").getAsJsonObject();
            Vape.INSTANCE.getInventoryFilterPresetRegistry().loadJson(inventoryManager);
        }
    }

    public void registerValue(Value<?, ?> value) {
        this.D.add(value);
    }
}
