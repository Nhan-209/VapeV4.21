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
    private final Mod module;
    private final ValueSnapshot<BindValue, BindSet> bindSnapshot;
    private final List<ValueSnapshot<?, ?>> valueSnapshots;
    private final ProfileSnapshot profileSnapshot;
    private boolean enabled;
    private boolean visible;

    public int getSortPriority() {
        int priority = 0;
        if (this.hasBind()) {
            priority += 2;
        }
        if (this.isEnabled()) {
            ++priority;
        }
        if (this.hasBind() && this.isEnabled()) {
            ++priority;
        }
        return priority;
    }

    public void resetBind() {
        this.bindSnapshot.getValue().setBoundInputs(new ArrayList<Integer>());
        this.bindSnapshot.getValue().setActivationMode(BindActivationMode.TOGGLE);
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public List<ValueSnapshot<?, ?>> getValueSnapshots() {
        return this.valueSnapshots;
    }

    public Mod getModule() {
        return this.module;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public ProfileModuleSnapshot(ProfileSnapshot profileSnapshot, Mod mod, JsonObject jsonObject) {
        JsonArray jsonArray;
        JsonObject jsonObject2;
        Object object;
        JsonArray jsonArray2;
        this.profileSnapshot = profileSnapshot;
        this.module = mod;
        this.valueSnapshots = new ArrayList<>();
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
            this.visible = visible != null ? visible.booleanValue() : mod.b();
        } else {
            jsonArray = new JsonArray();
            this.visible = mod.b();
        }
        this.bindSnapshot = new ValueSnapshot<>(new BindValue((Object)null, "", new BindSet(ConfigJsonUtils.o(jsonArray, false), false, mod.a().supportsActivationMode())));
        String bindMode = jsonObject == null ? null : ConfigJsonUtils.P(jsonObject, "bind_mode");
        if (bindMode != null && this.bindSnapshot.getValue().supportsActivationMode()) {
            try {
                this.bindSnapshot.getValue().setActivationMode(BindActivationMode.valueOf(bindMode));
            }
            catch (IllegalArgumentException illegalArgumentException) {
                this.bindSnapshot.getValue().setActivationMode(BindActivationMode.TOGGLE);
            }
        }
        for (Value<?, ?> value : mod.V()) {
            jsonObject2 = (JsonObject)linkedHashMap.get(value.getId());
            if (jsonObject2 == null) {
                this.valueSnapshots.add(new ValueSnapshot<>(value));
                continue;
            }
            ValueSnapshot<?, ?> valueSnapshot = new ValueSnapshot<>(value);
            valueSnapshot.loadJson(jsonObject2);
            this.valueSnapshots.add(valueSnapshot);
        }
        if (this.profileSnapshot.getProfile() != null) {
            this.enabled = this.profileSnapshot.getProfile().N$src$Ljava_util_List_$tynky5().contains(this.module);
        }
    }

    @Override
    public String getName() {
        return this.module.getName();
    }

    public boolean hasChanges() {
        if (this.module.getCategory() == Category.b) {
            return false;
        }
        for (ValueSnapshot<?, ?> valueSnapshot : this.getValueSnapshots()) {
            if (valueSnapshot.isDefault()) continue;
            return true;
        }
        if (this.hasBind()) {
            return true;
        }
        return this.isEnabled();
    }

    public List<ValueSnapshot<?, ?>> getValues(boolean includeDefaults) {
        ArrayList<ValueSnapshot<?, ?>> values = new ArrayList<>();
        for (ValueSnapshot<?, ?> valueSnapshot : this.getValueSnapshots()) {
            if (valueSnapshot.isDefault() && !includeDefaults) continue;
            values.add(valueSnapshot);
        }
        return values;
    }

    public boolean hasBind() {
        return this.bindSnapshot.getValue().hasValidBinding();
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", this.module.getName());
        if (this.module.a().usesOwnKeybindStorage()) {
            if (this.hasBind()) {
                jsonObject.add("keybinds_2", this.bindSnapshot.getValue().serializeBoundInputs());
            }
            if (this.bindSnapshot.getValue().supportsActivationMode() && this.bindSnapshot.getValue().getActivationMode() != BindActivationMode.TOGGLE) {
                jsonObject.addProperty("bind_mode", this.bindSnapshot.getValue().getActivationMode().name());
            }
        }
        JsonArray jsonArray = new JsonArray();
        for (ValueSnapshot<?, ?> valueSnapshot : this.valueSnapshots) {
            JsonObject jsonObject2;
            if (!((Value)valueSnapshot.getSourceValue()).isSerializable() || valueSnapshot.isDefault() || (jsonObject2 = valueSnapshot.toJson()).entrySet().size() <= 1) continue;
            jsonArray.add(valueSnapshot.toJson());
        }
        if (jsonArray.size() != 0) {
            jsonObject.add("values", (JsonElement)jsonArray);
        }
        if (this.visible != this.module.b()) {
            jsonObject.addProperty("visible", Boolean.valueOf(this.visible));
        }
        if (jsonObject.entrySet().size() == 1) {
            return null;
        }
        return jsonObject;
    }

    public String getBindDisplayText() {
        return this.bindSnapshot.getValue().getBindText();
    }

    public ValueSnapshot<BindValue, BindSet> getBindSnapshot() {
        return this.bindSnapshot;
    }
}
