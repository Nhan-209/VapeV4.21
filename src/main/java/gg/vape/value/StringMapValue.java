package gg.vape.value;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.Base64Util;
import gg.vape.value.Value;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class StringMapValue
extends Value<Map<String, String>, StringMapValue> {
    private final String A;
    private final String l;
    private final String J;

    public StringMapValue K$src$Lgg_vape_value_StringMapValue_$8ovgzy() {
        return new StringMapValue(null, this.l, this.J, this.A);
    }

    @Override
    public StringMapValue getALimit() {
        return this.K$src$Lgg_vape_value_StringMapValue_$8ovgzy();
    }

    @Override
    public JsonObject H(boolean bl) {
        JsonObject jsonObject = super.H(bl);
        JsonObject jsonObject2 = new JsonObject();
        for (Map.Entry<String, String> entry : this.K().entrySet()) {
            String string = entry.getKey();
            String string2 = entry.getValue();
            String string3 = "b64:" + Base64Util.encodeUtf8Base64(string);
            String string4 = "b64:" + Base64Util.encodeUtf8Base64(string2);
            jsonObject2.addProperty(string3, string4);
        }
        jsonObject.add("data", (JsonElement)jsonObject2);
        jsonObject.remove("value");
        return jsonObject;
    }

    public static StringMapValue R(Object object, String string, String string2, String string3) {
        return new StringMapValue(object, string, string2, string3);
    }

    public String A() {
        return this.J;
    }

    @Override
    public boolean loadJson(JsonObject jsonObject) {
        if (!jsonObject.has("data")) {
            return false;
        }
        JsonObject jsonObject2 = (JsonObject)jsonObject.get("data");
        Set<Map.Entry<String, JsonElement>> set = jsonObject2.entrySet();
        this.K().clear();
        for (Map.Entry<String, JsonElement> entry : set) {
            String string = entry.getKey();
            String string2 = entry.getValue().getAsString();
            if (string.startsWith("b64:")) {
                string = Base64Util.decodeUtf8Base64(string.split(":")[1]);
                string2 = Base64Util.decodeUtf8Base64(string2.split(":")[1]);
            }
            this.E(string, string2);
        }
        return true;
    }

    public void E(String string, String string2) {
        this.K().put(string, string2);
    }

    @Override
    public void parse(String string) {
    }

    @Override
    public void S() {
        if (this.N$src$Z$1a793rp()) {
            this.o(new LinkedHashMap());
        }
    }

    public String x() {
        return this.A;
    }

    public void E(String string) {
        this.K().remove(string);
    }

    @Override
    public boolean k() {
        Map<String, String> map = this.K();
        Map<String, String> map2 = this.P$src$Ljava_lang_Object_$qcpui1();
        return map.size() == map2.size() && map.equals(map2);
    }

    public StringMapValue(Object object, String string, String string2, String string3) {
        super(object, string, new LinkedHashMap());
        this.o(new LinkedHashMap());
        this.l = string;
        this.J = string2;
        this.A = string3;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public String c() {
        Map<String, String> map = this.K();
        if (map == null || map.isEmpty()) {
            return "";
        }
        return map.size() + " entries";
    }
}
