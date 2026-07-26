package gg.vape.value;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.input.BindSet;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.value.Value;

public class BindValue
extends Value<BindSet, BindValue> {
    public BindValue(Object object, String string, BindSet bindSet) {
        super(object, string, bindSet);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public BindValue n() {
        return new BindValue((Object)null, this.P$src$Ljava_lang_String_$1ijjhmj(), (BindSet)this.K());
    }

    @Override
    public BindValue getALimit() {
        return this.n();
    }

    @Override
    public void parse(String string) {
    }

    @Override
    public JsonObject H(boolean bl) {
        JsonObject jsonObject = super.H(bl);
        jsonObject.add("binds", (JsonElement)((BindSet)this.K()).toJson$src$Lcom_google_gson_JsonArray_$13cfbto());
        jsonObject.add("value", null);
        return jsonObject;
    }

    @Override
    public boolean k() {
        for (Integer n : ((BindSet)this.K()).o()) {
            if (((BindSet)this.K()).L().contains(n)) continue;
            return false;
        }
        return ((BindSet)this.K()).L().size() == ((BindSet)this.K()).o().size();
    }

    @Override
    public boolean loadJson(JsonObject jsonObject) {
        JsonArray jsonArray = jsonObject.getAsJsonArray("binds");
        ((BindSet)this.K()).O(jsonArray, false);
        return super.loadJson(jsonObject);
    }

    public static BindValue O(Object object, String string, int n) {
        return new BindValue(object, string, new BindSet(n));
    }

    public static BindValue i(Object object, String string) {
        return new BindValue(object, string, new BindSet());
    }
}
