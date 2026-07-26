package gg.vape.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.config.ConfigJsonUtils;
import gg.vape.runtime.ObfuscatedRuntimeException;
import java.util.UUID;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public class PublicProfileShareInfo {
    private final boolean g;
    @Nullable
    private UUID F;
    private final boolean h;
    private final boolean d;
    private String V;
    private long I;

    public void O(long l) {
        this.I = l;
    }

    public String a() {
        return this.V.toUpperCase();
    }

    @Nullable
    public UUID v() {
        return this.F;
    }

    public boolean b() {
        return this.h;
    }

    @Contract(value="!null -> !null; null -> null")
    public static PublicProfileShareInfo T(@Nullable JsonElement jsonElement) {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String string = ConfigJsonUtils.P(jsonObject, "derivedFrom");
        return new PublicProfileShareInfo(string != null ? UUID.fromString(jsonObject.get("derivedFrom").getAsString()) : null, jsonObject.get("unreadNotifications").getAsLong(), jsonObject.get("shareCode").getAsString(), jsonObject.get("listedPublicly").getAsBoolean(), jsonObject.get("shareCodeFriendsOnly").getAsBoolean(), jsonObject.get("uploadAnonymously").getAsBoolean());
    }

    public boolean f() {
        return this.g;
    }

    public void m(@Nullable UUID uUID) {
        this.F = uUID;
    }

    public boolean q() {
        return this.d;
    }

    PublicProfileShareInfo(@Nullable UUID uUID, long l, String string, boolean bl, boolean bl2, boolean bl3) {
        this.F = uUID;
        this.I = l;
        this.V = string;
        this.d = bl;
        this.h = bl2;
        this.g = bl3;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public long o() {
        return this.I;
    }

    public void d(String string) {
        this.V = string;
    }
}

