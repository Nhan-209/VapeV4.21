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
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.value.Value;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValueManager {
    private static final List<Class<?>> z = Arrays.asList(Mod.class, UtilityMod.class, Macro.class);
    private final List<Value<?, ?>> c = new ArrayList();
    private static final List<Class<?>> u = Arrays.asList(ClientSettings.class);

    public List<Value<?, ?>> getValues() {
        return this.c;
    }

    public JsonArray toJson() {
        JsonArray jsonArray = new JsonArray();
        for (Value<?, ?> value : this.getValues()) {
            JsonObject jsonObject;
            if (z.contains(value.k$src$Ljava_lang_Object_$13p7u5q().getClass()) || !value.s$src$Z$1arlhq2() || value.k() || (jsonObject = value.H(false)).entrySet().size() <= 1) continue;
            jsonArray.add((JsonElement)jsonObject);
        }
        return jsonArray;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private void F(JsonObject jsonObject) {
        for (Mod mod : Vape.INSTANCE.getModManager().s()) {
            if (!(mod instanceof ConfigSettingsModule)) continue;
            ((ConfigSettingsModule)mod).G(jsonObject);
        }
    }

    public void registerValue(Value<?, ?> value) {
        this.c.add(value);
    }

    public void loadJson(JsonArray jsonArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jsonArray.size(); ++i) {
            JsonObject jsonObject;
            JsonElement jsonElement = jsonArray.get(i);
            if (!jsonElement.isJsonObject() || jsonElement.isJsonNull() || (jsonObject = jsonElement.getAsJsonObject()).get("id") == null || jsonObject.get("id").isJsonNull()) continue;
            for (Value<?, ?> value : this.getValues()) {
                if (arrayList.contains(value) || !u.contains(value.k$src$Ljava_lang_Object_$13p7u5q().getClass().getSuperclass()) && !u.contains(value.k$src$Ljava_lang_Object_$13p7u5q().getClass()) && z.contains(value.k$src$Ljava_lang_Object_$13p7u5q().getClass().getSuperclass()) || z.contains(value.k$src$Ljava_lang_Object_$13p7u5q().getClass()) || !value.W(jsonObject)) continue;
                arrayList.add(value);
                value.loadJson(jsonObject);
            }
        }
    }
}

