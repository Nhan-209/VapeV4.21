package gg.vape.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gg.vape.config.Profile;
import gg.vape.runtime.ObfuscatedRuntimeException;
import java.util.List;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public class ProfilesSyncPayloadBuilder {
    private static int X;

    public static void e(int n) {
        X = n;
    }

    public static int G() {
        int n = ProfilesSyncPayloadBuilder.O();
        return 0;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    static {
        ProfilesSyncPayloadBuilder.e(99);
    }

    public static JsonObject T(@Nullable List<Profile> list, @Nullable List<UUID> list2) {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        if (list != null) {
            for (Profile object : list) {
                jsonArray.add((JsonElement)object.C(true));
            }
        }
        JsonArray jsonArray2 = new JsonArray();
        if (list2 != null) {
            for (UUID uUID : list2) {
                jsonArray2.add((JsonElement)new JsonPrimitive(uUID.toString()));
            }
        }
        jsonObject.add("updatedProfiles", (JsonElement)jsonArray);
        jsonObject.add("deletedProfiles", (JsonElement)jsonArray2);
        return jsonObject;
    }

    public static int O() {
        return X;
    }
}

