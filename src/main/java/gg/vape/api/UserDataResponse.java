package gg.vape.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.config.ConfigJsonUtils;
import gg.vape.config.PublicProfile;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.sync.RemoteProfileData;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public class UserDataResponse {
    private final Map<UUID, RemoteProfileData> m;
    @Nullable
    private final JsonArray R;
    private final Map<Long, PublicProfile> O;
    @Nullable
    private final JsonArray J;

    @Nullable
    public JsonArray D() {
        return this.R;
    }

    @Nullable
    @Contract(value="!null -> !null; null -> null")
    public static UserDataResponse S(@Nullable JsonElement jsonElement) {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }
        JsonObject jsonObject2 = jsonElement.getAsJsonObject();
        LinkedHashMap<UUID, RemoteProfileData> linkedHashMap = new LinkedHashMap<UUID, RemoteProfileData>();
        LinkedHashMap<Long, PublicProfile> linkedHashMap2 = new LinkedHashMap<Long, PublicProfile>();
        JsonObject jsonObject3 = ConfigJsonUtils.E(jsonObject2, "profiles");
        if (jsonObject3 != null) {
            for (Map.Entry<String, JsonElement> entry : jsonObject3.entrySet()) {
                RemoteProfileData remoteProfileData = RemoteProfileData.q(entry.getValue());
                if (remoteProfileData == null) continue;
                linkedHashMap.put(UUID.fromString(entry.getKey()), remoteProfileData);
            }
        }
        JsonObject publicProfiles = ConfigJsonUtils.E(jsonObject2, "publicProfiles");
        if (publicProfiles != null) {
            for (Map.Entry<String, JsonElement> entry : publicProfiles.entrySet()) {
                PublicProfile publicProfile = PublicProfile.k(entry.getValue());
                if (publicProfile == null) continue;
                linkedHashMap2.put(publicProfile.w(), publicProfile);
            }
        }
        return new UserDataResponse(ConfigJsonUtils.q(jsonObject2, "friends"), linkedHashMap, linkedHashMap2, ConfigJsonUtils.q(jsonObject2, "otherData"));
    }

    public String toString() {
        return "FullPrivateDataResponse{friends=" + this.R + ", profiles=" + this.m + ", publicProfiles=" + this.O + ", otherData=" + this.J + '}';
    }

    @Nullable
    public JsonArray y() {
        return this.J;
    }

    public Map<UUID, RemoteProfileData> F() {
        return this.m;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public Map<Long, PublicProfile> s() {
        return this.O;
    }

    UserDataResponse(@Nullable JsonArray jsonArray, Map<UUID, RemoteProfileData> map, Map<Long, PublicProfile> map2, @Nullable JsonArray jsonArray2) {
        this.R = jsonArray;
        this.m = map;
        this.O = map2;
        this.J = jsonArray2;
    }
}
