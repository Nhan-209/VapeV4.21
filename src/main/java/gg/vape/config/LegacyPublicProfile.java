package gg.vape.config;

import com.google.gson.JsonObject;
import gg.vape.config.Profile;
import org.jetbrains.annotations.Nullable;

public class LegacyPublicProfile
extends Profile {
    private boolean Y = false;
    private int F;

    @Override
    public JsonObject C(boolean bl) {
        JsonObject jsonObject = super.C(bl);
        return jsonObject;
    }

    @Override
    public Profile e(JsonObject jsonObject) {
        super.e(jsonObject);
        if (jsonObject.get("uses") != null) {
            this.F = jsonObject.get("uses").getAsInt();
        }
        return this;
    }

    public LegacyPublicProfile(String string, String string2) {
        super(string, string2);
    }


    @Override
    public int D() {
        return this.F;
    }

    public boolean j() {
        return this.Y;
    }

    @Nullable
    public static String S(String string) {
        String string2 = string.trim();
        if (string2.isEmpty()) {
            return null;
        }
        return string2;
    }

    @Nullable
    public static String e(@Nullable String string) {
        if (string == null) {
            return "You must input a valid tag";
        }
        if (string.length() > 16) {
            return "Tags must be 16 characters or less";
        }
        return null;
    }

    public void E(boolean bl) {
        this.Y = bl;
    }
}

