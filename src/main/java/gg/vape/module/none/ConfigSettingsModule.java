package gg.vape.module.none;

import com.google.gson.JsonObject;
import gg.vape.module.Mod;
import gg.vape.value.Value;

public class ConfigSettingsModule
extends Mod {
    public void G(JsonObject jsonObject) {
        for (Value<?, ?> value : this.V()) {
            if (!value.W(jsonObject)) continue;
            value.loadJson(jsonObject);
        }
    }

    public ConfigSettingsModule(String string) {
        super(string);
    }

}

