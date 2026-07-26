package gg.vape.account;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.api.ApiHttpClient;
import gg.vape.config.ConfigJsonUtils;
import java.text.ParseException;
import java.util.Date;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public class AccountInfoResponse {
    @Nullable
    private final String p;
    private final boolean r;
    private final long J;
    private final Date x;
    private final boolean C;
    private final boolean O;
    private final boolean I;

    public boolean e() {
        return this.O;
    }

    public boolean W() {
        return this.I;
    }

    AccountInfoResponse(long l, @Nullable String string, @Nullable Date date, boolean bl, boolean bl2, boolean bl3, boolean bl4) {
        this.J = l;
        this.p = string;
        this.x = date;
        this.O = bl;
        this.C = bl2;
        this.I = bl3;
        this.r = bl4;
    }

    @Nullable
    public String S() {
        return this.p;
    }

    private static ParseException a(ParseException parseException) {
        return parseException;
    }

    public boolean Y() {
        return this.C;
    }

    @Nullable
    @Contract(value="!null -> !null; null -> null")
    public static AccountInfoResponse B(@Nullable JsonElement jsonElement) {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        try {
            return new AccountInfoResponse(jsonObject.get("userId").getAsLong(), ConfigJsonUtils.P(jsonObject, "username"), ApiHttpClient.U(ConfigJsonUtils.P(jsonObject, "accountCreation")), jsonObject.get("licensed").getAsBoolean(), jsonObject.get("registered").getAsBoolean(), jsonObject.get("profiles").getAsBoolean(), jsonObject.get("banned").getAsBoolean());
        }
        catch (ParseException parseException) {
            throw new RuntimeException(parseException);
        }
    }

    public Date M() {
        return this.x;
    }

    public boolean f() {
        return this.r;
    }

    public long z() {
        return this.J;
    }
}

