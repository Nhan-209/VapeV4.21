package gg.vape.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.config.ConfigJsonUtils;
import gg.vape.runtime.ObfuscatedRuntimeException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public class PublicProfileUser {
    private final String u;
    private final long r;
    private static int[] Z;

    public static int[] I() {
        return Z;
    }

    public static void Q(int[] nArray) {
        Z = nArray;
    }

    public String o() {
        return this.u;
    }

    static {
        PublicProfileUser.Q(null);
    }

    @Nullable
    @Contract(value="null -> null")
    public static PublicProfileUser K(@Nullable JsonElement jsonElement) {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        PublicProfileUser publicProfileUser = new PublicProfileUser(jsonObject.get("userId").getAsLong(), ConfigJsonUtils.P(jsonObject, "username"));
        return publicProfileUser.o() != null ? publicProfileUser : null;
    }

    PublicProfileUser(long l, String string) {
        this.r = l;
        this.u = string;
    }

    public String toString() {
        return "SimpleOnlineUser{userId=" + this.r + ", username='" + this.u + '\'' + '}';
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public long j() {
        return this.r;
    }
}

