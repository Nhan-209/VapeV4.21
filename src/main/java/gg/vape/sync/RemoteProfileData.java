package gg.vape.sync;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.api.ApiHttpClient;
import gg.vape.config.ProfileRemoteMetadata;
import java.util.Map;
import java.util.UUID;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public class RemoteProfileData {
    @Nullable
    private final ProfileRemoteMetadata L;
    private final UUID g;
    private final String K;
    private final Map<String, Object> c;
    private final UUID v;
    private final String h;

    RemoteProfileData(UUID uUID, UUID uUID2, String string, String string2, Map<String, Object> map, @Nullable ProfileRemoteMetadata profileRemoteMetadata) {
        this.g = uUID;
        this.v = uUID2;
        this.h = string;
        this.K = string2;
        this.c = map;
        this.L = profileRemoteMetadata;
    }

    public Map<String, Object> U() {
        return this.c;
    }

    public String i() {
        return this.h;
    }

    public String v() {
        return this.K;
    }

    @Nullable
    public ProfileRemoteMetadata r() {
        return this.L;
    }

    public String toString() {
        return "PrivateProfile{uuid=" + this.g + "profileId=" + this.v + ", name='" + this.h + '\'' + ", data=" + this.c + ", metadata=" + this.L + '}';
    }

    public JsonObject G() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uuid", this.g != null ? this.g.toString() : null);
        jsonObject.addProperty("profileId", this.v != null ? this.v.toString() : null);
        jsonObject.addProperty("name", this.h);
        jsonObject.addProperty("vapeVersion", this.K);
        jsonObject.add("data", this.c != null ? ApiHttpClient.Z.toJsonTree(this.c) : null);
        jsonObject.add("metadata", this.L != null ? this.L.i() : null);
        return jsonObject;
    }

    public UUID d() {
        return this.v;
    }


    @Nullable
    @Contract(value="!null -> !null; null -> null")
    public static RemoteProfileData q(@Nullable JsonElement jsonElement) {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonElement jsonElement2 = jsonObject.get("uuid");
        return new RemoteProfileData(jsonElement2 == null || jsonElement2.isJsonNull() ? null : UUID.fromString(jsonElement2.getAsString()), UUID.fromString(jsonObject.get("profileId").getAsString()), jsonObject.get("name").getAsString(), jsonObject.get("vapeVersion").getAsString(), (Map)ApiHttpClient.Z.fromJson(jsonObject.get("data"), Map.class), ProfileRemoteMetadata.s(jsonObject.get("metadata")));
    }
}

