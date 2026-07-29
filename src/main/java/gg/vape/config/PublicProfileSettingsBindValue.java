package gg.vape.config;

import com.google.gson.JsonObject;
import gg.vape.config.PublicProfileSettings;
import gg.vape.input.BindSet;
import gg.vape.value.BindValue;

public class PublicProfileSettingsBindValue
extends BindValue {
    final PublicProfileSettings p;

    @Override
    public boolean loadJson(JsonObject jsonObject) {
        boolean bl = super.loadJson(jsonObject);
        return bl;
    }

    @Override
    public boolean isDefault() {
        return false;
    }

    public PublicProfileSettingsBindValue(PublicProfileSettings publicProfileSettings, Object object, String string, BindSet bindSet) {
        super(object, string, bindSet);
        this.p = publicProfileSettings;
    }
}
