package gg.vape.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public class ProfileRemoteMetadata {
    private final long K;
    private final long Y;
    private final long c;

    public JsonObject i() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("publicProfileId", (Number)this.c);
        jsonObject.addProperty("version", (Number)this.K);
        jsonObject.addProperty("publishedVersion", (Number)this.Y);
        return jsonObject;
    }

    @Nullable
    @Contract(value="!null -> !null; null -> null")
    public static ProfileRemoteMetadata s(@Nullable JsonElement jsonElement) {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        return new ProfileRemoteMetadata(jsonObject.get("publicProfileId").getAsLong(), jsonObject.get("version").getAsLong(), jsonObject.get("publishedVersion").getAsLong());
    }

    public long u() {
        return this.c;
    }

    public long w() {
        return this.Y;
    }

    public String toString() {
        return "PrivateProfileMetadata{publicProfileId=" + this.c + ", version=" + this.K + ", publishedVersion=" + this.Y + '}';
    }

    public long V() {
        return this.K;
    }

    ProfileRemoteMetadata(long l, long l2, long l3) {
        this.c = l;
        this.K = l2;
        this.Y = l3;
    }

    public boolean O() {
        return this.K < this.Y;
    }

}

