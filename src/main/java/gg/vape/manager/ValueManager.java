package gg.vape.manager;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.Vape;
import gg.vape.module.Macro;
import gg.vape.module.Mod;
import gg.vape.module.UtilityMod;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.none.ConfigSettingsModule;
import gg.vape.value.Value;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValueManager {
    private static final List<Class<?>> NON_SERIALIZED_OWNER_TYPES = Arrays.asList(Mod.class, UtilityMod.class, Macro.class);
    private final List<Value<?, ?>> registeredValues = new ArrayList();
    private static final List<Class<?>> CONFIG_SETTINGS_OWNER_TYPES = Arrays.asList(ClientSettings.class);

    public List<Value<?, ?>> getValues() {
        return this.registeredValues;
    }

    public JsonArray toJson() {
        JsonArray jsonArray = new JsonArray();
        for (Value<?, ?> value : this.getValues()) {
            JsonObject jsonObject;
            if (NON_SERIALIZED_OWNER_TYPES.contains(value.getOwner().getClass()) || !value.isSerializable() || value.isDefault() || (jsonObject = value.toJson(false)).entrySet().size() <= 1) continue;
            jsonArray.add((JsonElement)jsonObject);
        }
        return jsonArray;
    }


    private void loadConfigSettings(JsonObject jsonObject) {
        for (Mod mod : Vape.INSTANCE.getModManager().s()) {
            if (!(mod instanceof ConfigSettingsModule)) continue;
            ((ConfigSettingsModule)mod).loadMatchingValues(jsonObject);
        }
    }

    public void registerValue(Value<?, ?> value) {
        this.registeredValues.add(value);
    }

    public void loadJson(JsonArray jsonArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jsonArray.size(); ++i) {
            JsonObject jsonObject;
            JsonElement jsonElement = jsonArray.get(i);
            if (!jsonElement.isJsonObject() || jsonElement.isJsonNull() || (jsonObject = jsonElement.getAsJsonObject()).get("id") == null || jsonObject.get("id").isJsonNull()) continue;
            for (Value<?, ?> value : this.getValues()) {
                if (arrayList.contains(value) || !CONFIG_SETTINGS_OWNER_TYPES.contains(value.getOwner().getClass().getSuperclass()) && !CONFIG_SETTINGS_OWNER_TYPES.contains(value.getOwner().getClass()) && NON_SERIALIZED_OWNER_TYPES.contains(value.getOwner().getClass().getSuperclass()) || NON_SERIALIZED_OWNER_TYPES.contains(value.getOwner().getClass()) || !value.matchesJsonId(jsonObject)) continue;
                arrayList.add(value);
                value.loadJson(jsonObject);
            }
        }
    }
}

