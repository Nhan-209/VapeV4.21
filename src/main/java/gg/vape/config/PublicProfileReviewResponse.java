package gg.vape.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.api.ApiHttpClient;
import java.text.ParseException;
import java.util.Date;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public class PublicProfileReviewResponse {
    private final long f;
    private final Date u;
    private final String k;
    private final Date U;

    @Nullable
    @Contract(value="!null -> !null; null -> null")
    public static PublicProfileReviewResponse p(@Nullable JsonElement jsonElement) {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        try {
            return new PublicProfileReviewResponse(jsonObject.get("id").getAsLong(), ApiHttpClient.U(jsonObject.get("createdDate").getAsString()), ApiHttpClient.U(jsonObject.get("updatedDate").getAsString()), jsonObject.get("response").getAsString());
        }
        catch (ParseException parseException) {
            throw new RuntimeException(parseException);
        }
    }

    PublicProfileReviewResponse(long l, Date date, Date date2, String string) {
        this.f = l;
        this.U = date;
        this.u = date2;
        this.k = string;
    }

    public String m() {
        return this.k;
    }

    public long c() {
        return this.f;
    }

    public String toString() {
        return "PublicProfileReviewResponse{id=" + this.f + ", date=" + this.U + ", response='" + this.k + '\'' + '}';
    }

    private static ParseException a(ParseException parseException) {
        return parseException;
    }

    public Date j() {
        return this.u;
    }

    public Date Y() {
        return this.U;
    }
}

