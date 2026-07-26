package gg.vape.value;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.value.ListValue;
import gg.vape.value.OptionalLimitEntry;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OptionalLimitValue
extends ListValue<OptionalLimitEntry, OptionalLimitValue> {
    public static final Color r = new Color(0, 170, 0);
    public static final Color G;
    private final Color g;
    public static final Color O;

    public List<String> D() {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (OptionalLimitEntry optionalLimitEntry : this.K()) {
            if (!optionalLimitEntry.h()) continue;
            arrayList.add(optionalLimitEntry.r());
        }
        return arrayList;
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    @Override
    public void parse(String string) {
    }

    public OptionalLimitValue Y() {
        return new OptionalLimitValue(null, this.P$src$Ljava_lang_String_$1ijjhmj(), this.getName(), this.O());
    }

    @Override
    public OptionalLimitEntry j(String string, int n) {
        return this.N(string, n);
    }

    private OptionalLimitValue(Object object, String string, String string2, Color color) {
        super(object, string, string2);
        this.g = color;
    }

    @Override
    public void o(List<OptionalLimitEntry> list) {
        this.Y(list);
    }

    @Override
    public OptionalLimitValue getALimit() {
        return this.Y();
    }

    @Override
    public JsonObject H(boolean bl) {
        JsonObject jsonObject = this.toJson();
        JsonArray jsonArray = new JsonArray();
        for (OptionalLimitEntry optionalLimitEntry : this.K()) {
            jsonArray.add(optionalLimitEntry.l());
        }
        jsonObject.add("value", (JsonElement)jsonArray);
        return jsonObject;
    }

    public OptionalLimitEntry k(String string) {
        OptionalLimitEntry optionalLimitEntry = new OptionalLimitEntry(string);
        this.K().add(optionalLimitEntry);
        return optionalLimitEntry;
    }

    public OptionalLimitEntry N(String string, int n) {
        return this.k(string);
    }

    @Override
    public boolean loadJson(JsonObject jsonObject) {
        if (jsonObject.get("id").getAsString().equalsIgnoreCase(this.P$src$Ljava_lang_String_$1ijjhmj())) {
            if (jsonObject.get("value").isJsonArray()) {
                JsonArray jsonArray = jsonObject.get("value").getAsJsonArray();
                ArrayList<OptionalLimitEntry> arrayList = new ArrayList<OptionalLimitEntry>(this.K());
                for (OptionalLimitEntry optionalLimitEntry : arrayList) {
                    this.b(optionalLimitEntry);
                }
                for (JsonElement jsonElement : jsonArray) {
                    try {
                        OptionalLimitEntry optionalLimitEntry2 = this.k("");
                        optionalLimitEntry2.s(jsonElement.getAsJsonObject());
                    }
                    catch (Exception exception) {}
                }
            }
            return true;
        }
        return super.loadJson(jsonObject);
    }

    public void Y(List<OptionalLimitEntry> list) {
        ArrayList<OptionalLimitEntry> arrayList = new ArrayList<OptionalLimitEntry>(list);
        ArrayList<OptionalLimitEntry> arrayList2 = new ArrayList<OptionalLimitEntry>(this.K());
        this.K().clear();
        for (OptionalLimitEntry optionalLimitEntry : arrayList2) {
            this.b(optionalLimitEntry);
        }
        for (OptionalLimitEntry optionalLimitEntry : arrayList) {
            this.k(optionalLimitEntry.r());
        }
    }

    public boolean w(String string, boolean bl) {
        if (this.K().isEmpty()) {
            return bl;
        }
        for (OptionalLimitEntry optionalLimitEntry : this.K()) {
            if (!optionalLimitEntry.h() || !optionalLimitEntry.r().equalsIgnoreCase(string)) continue;
            return true;
        }
        return false;
    }

    static {
        O = new Color(170, 170, 170);
        G = new Color(170, 0, 0);
    }

    public static OptionalLimitValue Q(Object object, String string, String string2, String string3, Color color, List<String> list) {
        OptionalLimitValue optionalLimitValue = new OptionalLimitValue(object, string, string2, color);
        for (String string4 : list) {
            optionalLimitValue.k(string4);
        }
        optionalLimitValue.A(new ArrayList<OptionalLimitEntry>(optionalLimitValue.K()));
        return (OptionalLimitValue)optionalLimitValue.Z$src$Lgg_vape_value_Value_$16i62fx(string3);
    }

    public static OptionalLimitValue l(Object object, String string, String string2, Color color, String ... stringArray) {
        return OptionalLimitValue.Q(object, string, string2, "List of Names/Strings", color, Arrays.asList(stringArray));
    }

    public void b(OptionalLimitEntry optionalLimitEntry) {
        try {
            this.K().remove(optionalLimitEntry);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Override
    public String c() {
        List<OptionalLimitEntry> list = this.K();
        if (list.isEmpty()) {
            return "None";
        }
        if (list.size() == 1) {
            return ((OptionalLimitEntry)list.get(0)).r();
        }
        return ((OptionalLimitEntry)list.get(0)).r() + " +" + (list.size() - 1);
    }

    public Color O() {
        return this.g;
    }
}
