package gg.vape.value;

import com.google.gson.JsonObject;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.utils.Base64Util;
import gg.vape.value.ToggleableListEntry;

public class OptionalLimitEntry
implements ToggleableListEntry {
    private boolean X = true;
    private String t;
    private static GuiComponent[] i;

    @Override
    public boolean q() {
        return this.X;
    }

    @Override
    public void z() {
        this.X = !this.X;
    }


    public static void O(GuiComponent[] guiComponentArray) {
        i = guiComponentArray;
    }

    public boolean h() {
        return this.X;
    }

    public OptionalLimitEntry(String string) {
        this.t = string;
    }

    public static GuiComponent[] L() {
        return i;
    }

    public void s(JsonObject jsonObject) {
        if (jsonObject.get("item-id") != null) {
            this.t = jsonObject.get("item-id").getAsString();
            if (this.t.startsWith("b64:")) {
                this.t = Base64Util.decodeUtf8Base64(this.t.split(":")[1]);
            }
        }
        if (jsonObject.get("enabled") != null) {
            this.X = jsonObject.get("enabled").getAsBoolean();
        }
    }

    public String r() {
        return this.t;
    }

    public JsonObject l() {
        JsonObject jsonObject = new JsonObject();
        String string = "b64:" + Base64Util.encodeUtf8Base64(this.t);
        jsonObject.addProperty("item-id", string);
        jsonObject.addProperty("enabled", Boolean.valueOf(this.X));
        return jsonObject;
    }

    public String toString() {
        return this.t;
    }

    static {
        OptionalLimitEntry.O(new GuiComponent[3]);
    }

    @Override
    public void x(boolean bl) {
        this.X = bl;
    }
}

