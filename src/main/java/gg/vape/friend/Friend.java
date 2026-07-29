package gg.vape.friend;

import com.google.gson.JsonObject;
import gg.vape.Vape;
import gg.vape.config.ConfigJsonUtils;
import gg.vape.friend.FriendEntry;
import gg.vape.utils.Base64Util;

public class Friend
extends FriendEntry {
    private String e;
    private String a;

    @Override
    public String s() {
        return this.e;
    }

    public Friend(String string, String string2) {
        this.e = string;
        this.a = string2;
    }


    @Override
    public Friend loadJson(JsonObject jsonObject) {
        this.e = ConfigJsonUtils.B(jsonObject, "name_2");
        this.a = ConfigJsonUtils.B(jsonObject, "alias_2");
        this.k(ConfigJsonUtils.C(jsonObject, "target"));
        this.H(jsonObject);
        return this;
    }

    @Override
    public String E() {
        return this.a;
    }

    private void H(JsonObject jsonObject) {
        if (jsonObject.get("name") != null && !jsonObject.get("name").isJsonNull()) {
            this.e = jsonObject.get("name").getAsString();
        }
        if (jsonObject.get("alias") != null && !jsonObject.get("alias").isJsonNull()) {
            this.a = jsonObject.get("alias").getAsString();
        }
    }

    @Override
    public String o() {
        if (Vape.INSTANCE.getFriendManager().J.getEffectiveValue().booleanValue()) {
            return this.E();
        }
        return this.e;
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name_2", Base64Util.encodeUtf8Base64(this.e));
        jsonObject.addProperty("alias_2", Base64Util.encodeUtf8Base64(this.a));
        jsonObject.addProperty("target", Boolean.valueOf(this.c()));
        return jsonObject;
    }

    public void T(String string) {
        this.a = string;
    }
}

