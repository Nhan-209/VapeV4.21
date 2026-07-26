package gg.vape.module;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.config.ClientSettings;
import gg.vape.config.ConfigJsonUtils;
import gg.vape.input.KeyboardCodeUtil;
import gg.vape.module.macro.CommandMacro;
import gg.vape.module.macro.ItemMacro;
import gg.vape.module.macro.ItemMacroActionState;
import gg.vape.module.macro.MacroAction;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.unmap.Bendable;
import gg.vape.utils.Base64Util;
import gg.vape.value.BooleanValue;
import gg.vape.value.RandomValue;
import java.util.Collections;

public abstract class Macro
extends Bendable {
    private RandomValue Z = RandomValue.C(this, "Delay", "#", "", 1.0, 100.0, 200.0, 1000.0, 1.0);
    private BooleanValue N = BooleanValue.create(this, "Double Click", false);
    private static GuiComponent[] e;
    private RandomValue G = RandomValue.create(this, "Double Click Delay", "#", "", 1.0, 100.0, 200.0, 1000.0);
    private String b;

    public BooleanValue getDoubleClick() {
        return this.N;
    }

    public Macro loadJson(JsonObject jsonObject) {
        if (jsonObject.has("name")) {
            this.b = ConfigJsonUtils.c(jsonObject, "name");
        }
        if (jsonObject.get("keybinds") != null && jsonObject.get("keybinds").isJsonArray()) {
            try {
                this.O(jsonObject.getAsJsonArray("keybinds"), false);
            }
            catch (Exception exception) {}
        } else if (jsonObject.get("key_2") != null && !jsonObject.get("key_2").isJsonNull()) {
            this.c(Collections.singletonList(jsonObject.get("key_2").getAsInt()));
        } else if (jsonObject.get("key") != null && !jsonObject.get("key").isJsonNull()) {
            int n = jsonObject.get("key").getAsInt();
            if (n > 0) {
                n = KeyboardCodeUtil.m(n);
            }
            this.c(Collections.singletonList(n));
        } else {
            this.L().clear();
        }
        if (jsonObject.get("double_click_enabled") != null && !jsonObject.get("double_click_enabled").isJsonNull()) {
            this.N.loadJson(jsonObject.get("double_click_enabled").getAsJsonObject());
        }
        if (jsonObject.get("double_click") != null && !jsonObject.get("delay").isJsonNull()) {
            this.Z.loadJson(jsonObject.get("delay").getAsJsonObject());
        }
        if (jsonObject.get("double_click") != null && !jsonObject.get("double_click").isJsonNull()) {
            this.G.loadJson(jsonObject.get("double_click").getAsJsonObject());
        }
        return this;
    }

    protected Macro(String string) {
        this.b = string;
        this.N.q$src$Ljava_util_List_$fyau59().add(this.G);
    }

    static {
        Macro.A(null);
    }

    public abstract MacroAction N();

    public static void A(GuiComponent[] guiComponentArray) {
        e = guiComponentArray;
    }

    @Override
    public String y() {
        return String.format(" %s7[%sr%s%s7]%sr %s", ClientSettings.F, ClientSettings.F, this.h(), ClientSettings.F, ClientSettings.F, this.getName());
    }

    public String getName() {
        return this.b;
    }

    public static GuiComponent[] r() {
        return e;
    }

    public static Macro create(String string) {
        if (string.startsWith("fishing rod")) {
            return new ItemMacroActionState();
        }
        if (string.startsWith("/")) {
            return new CommandMacro(string);
        }
        return new ItemMacro(string);
    }

    @Override
    public void A() {
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        String string = "b64:" + Base64Util.encodeUtf8Base64(this.b);
        jsonObject.addProperty("name", string);
        jsonObject.add("keybinds", (JsonElement)this.toJson$src$Lcom_google_gson_JsonArray_$13cfbto());
        jsonObject.add("delay", (JsonElement)this.Z.H(false));
        jsonObject.add("double_click_enabled", (JsonElement)this.N.H(false));
        jsonObject.add("double_click", (JsonElement)this.G.H(false));
        return jsonObject;
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    public RandomValue getDelay() {
        return this.Z;
    }

    public RandomValue getDoubleClickDelay() {
        return this.G;
    }

    @Override
    public boolean m() {
        return false;
    }
}

