package gg.vape.sync;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.sync.RemoteProfileData;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public class RemoteProfileDataMap {
    private final Map<UUID, RemoteProfileData> Q;
    private static final String b = "PrivateProfilesResponse{profiles=";

    RemoteProfileDataMap(Map<UUID, RemoteProfileData> map) {
        this.Q = map;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Nullable
    @Contract(value="!null -> !null; null -> null")
    public static RemoteProfileDataMap U(@Nullable JsonElement jsonElement) {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        LinkedHashMap<UUID, RemoteProfileData> linkedHashMap = new LinkedHashMap<UUID, RemoteProfileData>();
        for (Map.Entry entry : jsonObject.entrySet()) {
            RemoteProfileData remoteProfileData = RemoteProfileData.q((JsonElement)entry.getValue());
            if (remoteProfileData == null) continue;
            linkedHashMap.put(UUID.fromString((String)entry.getKey()), remoteProfileData);
        }
        return new RemoteProfileDataMap(linkedHashMap);
    }

    public String toString() {
        return b + this.Q + '}';
    }

    public Map<UUID, RemoteProfileData> S() {
        return this.Q;
    }
}

