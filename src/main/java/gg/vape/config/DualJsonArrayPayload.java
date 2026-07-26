package gg.vape.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.runtime.ObfuscatedRuntimeException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public class DualJsonArrayPayload {
    private final JsonArray P;
    private final JsonArray M;

    public JsonArray D() {
        return this.M;
    }

    public JsonArray X() {
        return this.P;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    DualJsonArrayPayload(JsonArray jsonArray, JsonArray jsonArray2) {
        this.P = jsonArray;
        this.M = jsonArray2;
    }

    public String toString() {
        return "PrivateUserDataResponse{friends=" + this.P + ", otherData=" + this.M + '}';
    }

    @Nullable
    @Contract(value="!null -> !null; null -> null")
    public static DualJsonArrayPayload i(@Nullable JsonElement jsonElement) {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        return new DualJsonArrayPayload(jsonObject.get("friends").getAsJsonArray(), jsonObject.get("otherData").getAsJsonArray());
    }
}

