package gg.vape.module.utility.inventory;

import com.google.gson.JsonObject;
import gg.vape.module.utility.inventory.HotbarSlotRule;
import gg.vape.module.utility.inventory.HotbarSlotRuleEditorComponent;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.value.Value;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class HotbarSlotRuleValue
extends Value<List<HotbarSlotRule>, HotbarSlotRuleValue> {
    @Nullable
    private HotbarSlotRuleEditorComponent f;

    public List<HotbarSlotRule> f$src$Ljava_util_List_$5if89l() {
        if (this.f == null || this.f.q$src$Lgg_vape_module_utility_inventory_HotbarSlotRule$1uq9d6u() == null) {
            return new ArrayList<HotbarSlotRule>();
        }
        return this.f.q$src$Lgg_vape_module_utility_inventory_HotbarSlotRule$1uq9d6u().u$src$Ljava_util_List_$1u5n2i3();
    }

    public HotbarSlotRuleValue f() {
        return new HotbarSlotRuleValue(null, this.P$src$Ljava_lang_String_$1ijjhmj());
    }

    @Override
    public HotbarSlotRuleValue getALimit() {
        return this.f();
    }

    @Override
    public void parse(String string) {
    }

    public void u(HotbarSlotRuleEditorComponent hotbarSlotRuleEditorComponent) {
        this.f = hotbarSlotRuleEditorComponent;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public HotbarSlotRuleValue(Object object, String string) {
        super(object, string, new ArrayList());
    }

    @Override
    public boolean loadJson(JsonObject jsonObject) {
        if (this.f != null) {
            this.f.f(jsonObject);
        }
        return true;
    }

    @Override
    public String c() {
        List<HotbarSlotRule> list = this.f$src$Ljava_util_List_$5if89l();
        if (list.isEmpty()) {
            return "None";
        }
        if (list.size() == 1) {
            return String.valueOf(list.get(0).x());
        }
        return list.get(0).x() + " +" + (list.size() - 1);
    }

    @Override
    public JsonObject H(boolean bl) {
        JsonObject jsonObject = this.f != null ? this.f.A$src$Lcom_google_gson_JsonObject_$167pnb8() : new JsonObject();
        jsonObject.addProperty("id", this.P$src$Ljava_lang_String_$1ijjhmj());
        return jsonObject;
    }

    @Nullable
    public HotbarSlotRuleEditorComponent Y() {
        return this.f;
    }

    public void W(List<HotbarSlotRule> list) {
    }

    public static HotbarSlotRuleValue m(Object object, String string) {
        return new HotbarSlotRuleValue(object, string);
    }
}
