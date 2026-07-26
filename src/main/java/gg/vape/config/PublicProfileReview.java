package gg.vape.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.Vape;
import gg.vape.api.ApiHttpClient;
import gg.vape.api.ApiResponse;
import gg.vape.api.ApiServices;
import gg.vape.config.ConfigJsonUtils;
import gg.vape.config.PublicProfile;
import gg.vape.config.PublicProfileReviewResponse;
import gg.vape.config.PublicProfileUser;
import gg.vape.manager.client.PublicProfileManager;
import gg.vape.module.none.ClientSettings;
import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.Executor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public class PublicProfileReview {
    private final PublicProfileUser C;
    @Nullable
    private Boolean w;
    static final boolean T = !PublicProfileReview.class.desiredAssertionStatus();
    private final String l;
    private final long R;
    private final Date t;
    private final boolean F;
    private final Date g;
    private final boolean n;
    private final long m;
    @Nullable
    private PublicProfileReviewResponse v;
    private final long e;

    public boolean g() {
        return this.F;
    }

    public String I() {
        return this.l;
    }

    public void o(boolean bl) {
        this.w = bl;
    }

    @Nullable
    @Contract(value="!null -> !null; null -> null")
    public static PublicProfileReview a(@Nullable JsonElement jsonElement) {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        try {
            return new PublicProfileReview(jsonObject.get("commentId").getAsLong(), jsonObject.get("profileId").getAsLong(), ApiHttpClient.U(jsonObject.get("createdDate").getAsString()), ApiHttpClient.U(jsonObject.get("updatedDate").getAsString()), PublicProfileUser.K(jsonObject.get("commenter")), jsonObject.get("message").getAsString(), jsonObject.get("liked").getAsBoolean(), jsonObject.get("version").getAsLong(), jsonObject.get("latest").getAsBoolean(), ConfigJsonUtils.t(jsonObject, "read"), PublicProfileReviewResponse.p(jsonObject.get("response")));
        }
        catch (ParseException parseException) {
            throw new RuntimeException(parseException);
        }
    }

    public boolean I$src$Z$148jdrc() {
        return this.w != null && this.w == false;
    }

    @Nullable
    public PublicProfileReviewResponse H() {
        return this.v;
    }

    public void U(@Nullable PublicProfileReviewResponse publicProfileReviewResponse) {
        this.v = publicProfileReviewResponse;
    }

    public boolean X() {
        return this.n;
    }

    public Date a() {
        return this.t;
    }

    public String toString() {
        return "PublicProfileReview{commentId=" + this.R + ", profileId=" + this.e + ", date=" + this.g + ", commenter=" + this.C + ", message='" + this.l + '\'' + ", liked=" + this.n + ", version=" + this.m + ", latest=" + this.F + ", response=" + this.v + '}';
    }

    public boolean L() {
        return this.w != null && this.w != false;
    }

    public long R() {
        return this.m;
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    public long M() {
        return this.R;
    }

    @Nullable
    public Boolean A() {
        return this.w;
    }

    PublicProfileReview(long l, long l2, Date date, Date date2, PublicProfileUser publicProfileUser, String string, boolean bl, long l3, boolean bl2, @Nullable Boolean bl3, @Nullable PublicProfileReviewResponse publicProfileReviewResponse) {
        this.R = l;
        this.e = l2;
        this.g = date;
        this.t = date2;
        this.C = publicProfileUser;
        this.l = string;
        this.n = bl;
        this.m = l3;
        this.F = bl2;
        this.w = bl3;
        this.v = publicProfileReviewResponse;
    }

    public long j() {
        return this.e;
    }

    public void B(PublicProfile publicProfile, Runnable runnable) {
        ApiServices.d().R().P(this).whenCompleteAsync((arg_0, arg_1) -> this.lambda$deleteReview$0(publicProfile, runnable, arg_0, arg_1), (Executor)ClientSettings.f6);
    }

    public PublicProfileUser F() {
        return this.C;
    }

    private void lambda$deleteReview$0(PublicProfile publicProfile, Runnable runnable, ApiResponse apiResponse, Throwable throwable) {
        if (throwable != null) {
            Vape.logThrowable(throwable);
            return;
        }
        if (!apiResponse.t()) {
            Vape.debugLog("Failed to delete review: " + apiResponse.N());
            PublicProfileManager.b("Failed to delete review: " + apiResponse.N());
            return;
        }
        if (!T && apiResponse.T() == null) {
            throw new AssertionError();
        }
        publicProfile.B(null);
        if (this.n) {
            publicProfile.E(publicProfile.J() - 1L);
        } else {
            publicProfile.b(publicProfile.W() - 1L);
        }
        if (runnable != null) {
            runnable.run();
        }
    }

    public Date P() {
        return this.g;
    }
}

