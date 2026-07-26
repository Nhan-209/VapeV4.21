package gg.vape.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.config.ConfigJsonUtils;
import gg.vape.config.ProfileSnapshot;
import gg.vape.input.BindActivationMode;
import gg.vape.input.BindSet;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.unmap.INamed;
import gg.vape.value.BindValue;
import gg.vape.value.Value;
import gg.vape.value.ValueSnapshot;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class ProfileModuleSnapshot
implements INamed {
    private final Mod P;
    private final JsonObject n;
    private final ValueSnapshot<BindValue, BindSet> X;
    private final List<ValueSnapshot<?, ?>> h;
    private final ProfileSnapshot H;
    private boolean j;
    private boolean g;

    public int v() {
        int n = 0;
        if (this.n()) {
            n += 2;
        }
        if (this.Q()) {
            ++n;
        }
        if (this.n() && this.Q()) {
            ++n;
        }
        return n;
    }

    public void T() {
        this.X.J().c(new ArrayList<Integer>());
        this.X.J().Y(BindActivationMode.TOGGLE);
    }

    public boolean Q() {
        return this.j;
    }

    public List<ValueSnapshot<?, ?>> z() {
        return this.h;
    }

    public Mod G() {
        return this.P;
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }

    public void L(boolean bl) {
        this.j = bl;
    }

    public ProfileModuleSnapshot(ProfileSnapshot profileSnapshot, Mod mod, JsonObject jsonObject) {
        JsonArray jsonArray;
        JsonObject jsonObject2;
        Object object;
        JsonArray jsonArray2;
        this.H = profileSnapshot;
        this.P = mod;
        this.h = new ArrayList();
        this.n = jsonObject;
        LinkedHashMap<String, JsonObject> linkedHashMap = new LinkedHashMap<String, JsonObject>();
        if (jsonObject != null) {
            jsonArray2 = jsonObject.getAsJsonArray("values");
            if (jsonArray2 != null) {
                for (JsonElement object2 : jsonArray2) {
                    if (object2.isJsonNull() || !object2.isJsonObject() || (object = ConfigJsonUtils.P(jsonObject2 = object2.getAsJsonObject(), "id")) == null) continue;
                    linkedHashMap.put((String)object, jsonObject2);
                }
            }
            if ((jsonArray = ConfigJsonUtils.q(jsonObject, "keybinds_2")) == null) {
                jsonArray = new JsonArray();
            }
            Boolean visible = ConfigJsonUtils.t(jsonObject, "visible");
            this.g = visible != null ? visible.booleanValue() : mod.b();
        } else {
            jsonArray = new JsonArray();
            this.g = mod.b();
        }
        this.X = new ValueSnapshot(profileSnapshot, new BindValue((Object)null, "", new BindSet(ConfigJsonUtils.o(jsonArray, false), false, mod.a().A$src$Z$jg36ch())));
        String bindMode = jsonObject == null ? null : ConfigJsonUtils.P(jsonObject, "bind_mode");
        if (bindMode != null && this.X.J().A$src$Z$jg36ch()) {
            try {
                this.X.J().Y(BindActivationMode.valueOf(bindMode));
            }
            catch (IllegalArgumentException illegalArgumentException) {
                this.X.J().Y(BindActivationMode.TOGGLE);
            }
        }
        for (Value<?, ?> value : mod.V()) {
            jsonObject2 = (JsonObject)linkedHashMap.get(value.P$src$Ljava_lang_String_$1ijjhmj());
            if (jsonObject2 == null) {
                this.h.add(new ValueSnapshot(profileSnapshot, value));
                continue;
            }
            object = new ValueSnapshot(profileSnapshot, value);
            ((ValueSnapshot)object).j(jsonObject2);
            this.h.add((ValueSnapshot<?, ?>)object);
        }
        if (this.H.d() != null) {
            this.j = this.H.d().N$src$Ljava_util_List_$tynky5().contains(this.P);
        }
    }

    @Override
    public String getName() {
        return this.P.getName();
    }

    public boolean j() {
        if (this.P.getCategory() == Category.b) {
            return false;
        }
        for (ValueSnapshot<?, ?> valueSnapshot : this.z()) {
            if (valueSnapshot.x()) continue;
            return true;
        }
        if (this.n()) {
            return true;
        }
        return this.Q();
    }

    public List<ValueSnapshot<?, ?>> h(boolean bl) {
        ArrayList arrayList = new ArrayList();
        for (ValueSnapshot<?, ?> valueSnapshot : this.z()) {
            if (valueSnapshot.h() && !bl) continue;
            arrayList.add(valueSnapshot);
        }
        return arrayList;
    }

    public boolean n() {
        return this.X.J().y$src$Z$r0tfl8();
    }

    public JsonObject g() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", this.P.getName());
        if (this.P.a().Y()) {
            if (this.n()) {
                jsonObject.add("keybinds_2", (JsonElement)this.X.J().toJson$src$Lcom_google_gson_JsonArray_$13cfbto());
            }
            if (this.X.J().A$src$Z$jg36ch() && this.X.J().G() != BindActivationMode.TOGGLE) {
                jsonObject.addProperty("bind_mode", this.X.J().G().name());
            }
        }
        JsonArray jsonArray = new JsonArray();
        for (ValueSnapshot<?, ?> valueSnapshot : this.h) {
            JsonObject jsonObject2;
            if (!((Value)valueSnapshot.W()).s$src$Z$1arlhq2() || valueSnapshot.x() || (jsonObject2 = valueSnapshot.I()).entrySet().size() <= 1) continue;
            jsonArray.add((JsonElement)valueSnapshot.I());
        }
        if (jsonArray.size() != 0) {
            jsonObject.add("values", (JsonElement)jsonArray);
        }
        if (this.g != this.P.b()) {
            jsonObject.addProperty("visible", Boolean.valueOf(this.g));
        }
        if (jsonObject.entrySet().size() == 1) {
            return null;
        }
        return jsonObject;
    }

    public String I() {
        return this.X.J().h();
    }

    public ValueSnapshot<BindValue, BindSet> O() {
        return this.X;
    }
}
