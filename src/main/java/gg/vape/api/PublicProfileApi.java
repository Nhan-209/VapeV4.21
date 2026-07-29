package gg.vape.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gg.vape.api.ApiAccessTokenProvider;
import gg.vape.api.ApiHttpClient;
import gg.vape.api.ApiResponse;
import gg.vape.api.PagedResult;
import gg.vape.config.PublicProfile;
import gg.vape.config.PublicProfileReview;
import gg.vape.config.PublicProfileReviewResponse;
import gg.vape.config.PublicProfileShareInfo;
import gg.vape.config.PublicProfileSortMode;
import gg.vape.config.PublicProfileSummary;
import gg.vape.sync.RemoteProfileData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.Nullable;

public class PublicProfileApi {
    private final String q;
    private static boolean M;

    private ApiResponse lambda$listPublicProfileReviews$18(String string, long l, long l2) {
        try {
            Thread.sleep(1000L);
        }
        catch (InterruptedException interruptedException) {
            // empty catch block
        }
        try {
            return ApiHttpClient.V(this.q + "/api/v1/" + string + "/profile/public/review/view/" + l + "/" + l2, PublicProfileApi::lambda$null$17);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public CompletableFuture<ApiResponse<RemoteProfileData>> q(long l) {
        String string = ApiAccessTokenProvider.i();
        return CompletableFuture.supplyAsync(() -> this.lambda$downloadPublicProfile$14(string, l));
    }

    private ApiResponse lambda$regenerateShareCode$27(long l, String string) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("profileId", (Number)l);
            return ApiHttpClient.z(this.q + "/api/v1/" + string + "/profile/public/regenerate/sharecode", jsonObject, PublicProfileShareInfo::T);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public CompletableFuture<ApiResponse<Boolean>> b(PublicProfile publicProfile) {
        return this.z(publicProfile.w());
    }

    public CompletableFuture<ApiResponse<PublicProfile>> y(JsonObject jsonObject) {
        String string = ApiAccessTokenProvider.i();
        return CompletableFuture.supplyAsync(() -> this.lambda$createPublicProfile$10(string, jsonObject));
    }

    public CompletableFuture<ApiResponse<Boolean>> T(PublicProfileReviewResponse publicProfileReviewResponse) {
        return this.t(publicProfileReviewResponse.c());
    }

    public CompletableFuture<ApiResponse<Boolean>> i(long l) {
        String string = ApiAccessTokenProvider.i();
        return CompletableFuture.supplyAsync(() -> this.lambda$deletePublicProfile$11(string, l));
    }

    private ApiResponse lambda$markAllReviewsAsRead$25(long l, String string) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("profileId", (Number)l);
            return ApiHttpClient.z(this.q + "/api/v1/" + string + "/profile/public/review/mark/all", jsonObject, JsonElement::getAsBoolean);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    private static List lambda$null$0(JsonElement jsonElement) {
        ArrayList<String> arrayList = new ArrayList<String>();
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        for (JsonElement jsonElement2 : jsonArray) {
            arrayList.add(jsonElement2.getAsString());
        }
        return arrayList;
    }

    private static PublicProfile lambda$null$9(JsonElement jsonElement) {
        return PublicProfile.k((JsonElement)jsonElement.getAsJsonObject());
    }

    public CompletableFuture<ApiResponse<PagedResult<PublicProfileReview>>> U(long l, long l2) {
        String string = ApiAccessTokenProvider.i();
        return CompletableFuture.supplyAsync(() -> this.lambda$viewPublicProfileReviews$8(string, l, l2));
    }

    public CompletableFuture<ApiResponse<Boolean>> h(long l, String string) {
        String string2 = ApiAccessTokenProvider.i();
        return CompletableFuture.supplyAsync(() -> this.lambda$reportPublicProfileResponse$24(string, string2, l));
    }

    public CompletableFuture<ApiResponse<Boolean>> z(long l) {
        String string = ApiAccessTokenProvider.i();
        return CompletableFuture.supplyAsync(() -> this.lambda$markAllReviewsAsRead$25(l, string));
    }

    public CompletableFuture<ApiResponse<PublicProfileReview>> m(PublicProfile publicProfile, boolean bl, @Nullable String string) {
        return this.l(publicProfile.w(), bl, string);
    }

    public CompletableFuture<ApiResponse<Boolean>> H(long l, List<Long> list) {
        String string = ApiAccessTokenProvider.i();
        return CompletableFuture.supplyAsync(() -> this.lambda$markReviewsAsRead$26(l, list, string));
    }

    private ApiResponse lambda$deletePublicProfile$11(String string, long l) {
        try {
            return ApiHttpClient.U(this.q + "/api/v1/" + string + "/profile/public/" + l + "/delete", null, JsonElement::getAsBoolean);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public CompletableFuture<ApiResponse<PublicProfileReviewResponse>> h(PublicProfileReview publicProfileReview, String string) {
        return this.C(publicProfileReview.M(), string);
    }

    public CompletableFuture<ApiResponse<Boolean>> P(PublicProfileReview publicProfileReview) {
        return this.K(publicProfileReview.M());
    }

    private ApiResponse lambda$viewPublicProfileReviews$8(String string, long l, long l2) {
        try {
            return ApiHttpClient.V(this.q + "/api/v1/" + string + "/profile/public/review/view/" + l + "/" + l2, PublicProfileApi::lambda$null$7);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public CompletableFuture<ApiResponse<RemoteProfileData>> A(long l) {
        String string = ApiAccessTokenProvider.i();
        return CompletableFuture.supplyAsync(() -> this.lambda$updatePublicProfile$15(string, l));
    }

    private ApiResponse lambda$createPublicProfile$10(String string, JsonObject jsonObject) {
        try {
            return ApiHttpClient.z(this.q + "/api/v1/" + string + "/profile/public/create", jsonObject, PublicProfileApi::lambda$null$9);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public CompletableFuture<ApiResponse<PagedResult<PublicProfileReview>>> r(long l, long l2) {
        String string = ApiAccessTokenProvider.i();
        return CompletableFuture.supplyAsync(() -> this.lambda$listPublicProfileReviews$18(string, l, l2));
    }

    public CompletableFuture<ApiResponse<Boolean>> t(long l) {
        String string = ApiAccessTokenProvider.i();
        return CompletableFuture.supplyAsync(() -> this.lambda$deletePublicProfileReviewResponse$22(string, l));
    }

    private ApiResponse lambda$reportPublicProfileResponse$24(String string, String string2, long l) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("reason", string);
            return ApiHttpClient.z(this.q + "/api/v1/" + string2 + "/profile/public/reports/create/response/" + l, jsonObject, JsonElement::getAsBoolean);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public CompletableFuture<ApiResponse<PagedResult<PublicProfileReview>>> F(PublicProfile publicProfile, long l) {
        return this.r(publicProfile.w(), l);
    }

    public CompletableFuture<ApiResponse<PublicProfile>> x(long l) {
        String string = ApiAccessTokenProvider.i();
        return CompletableFuture.supplyAsync(() -> this.lambda$viewPublicProfile$6(string, l));
    }

    public PublicProfileApi(String string) {
        this.q = string;
    }

    private ApiResponse lambda$viewPublicProfile$6(String string, long l) {
        try {
            return ApiHttpClient.V(this.q + "/api/v1/" + string + "/profile/public/" + l + "/view", PublicProfileApi::lambda$null$5);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    private static PagedResult lambda$null$17(JsonElement jsonElement) {
        return PagedResult.u(jsonElement.getAsJsonObject(), PublicProfileApi::lambda$null$16);
    }

    private ApiResponse lambda$reportPublicProfileReview$23(String string, String string2, long l) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("reason", string);
            return ApiHttpClient.z(this.q + "/api/v1/" + string2 + "/profile/public/reports/create/review/" + l, jsonObject, JsonElement::getAsBoolean);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    private static PublicProfile lambda$null$12(JsonElement jsonElement) {
        return PublicProfile.k((JsonElement)jsonElement.getAsJsonObject());
    }

    private static PagedResult lambda$null$3(JsonElement jsonElement) {
        return PagedResult.u(jsonElement.getAsJsonObject(), PublicProfileApi::lambda$null$2);
    }

    public CompletableFuture<ApiResponse<PublicProfileReviewResponse>> C(long l, String string) {
        String string2 = ApiAccessTokenProvider.i();
        return CompletableFuture.supplyAsync(() -> this.lambda$createPublicProfileReviewResponse$21(string, string2, l));
    }

    public CompletableFuture<ApiResponse<Boolean>> K(long l) {
        String string = ApiAccessTokenProvider.i();
        return CompletableFuture.supplyAsync(() -> this.lambda$deletePublicProfileReview$20(string, l));
    }

    static {
        PublicProfileApi.Y(true);
    }

    public CompletableFuture<ApiResponse<PublicProfileReview>> l(long l, boolean bl, @Nullable String string) {
        String string2 = ApiAccessTokenProvider.i();
        return CompletableFuture.supplyAsync(() -> this.lambda$createPublicProfileReview$19(l, string, bl, string2));
    }

    private ApiResponse lambda$createPublicProfileReviewResponse$21(String string, String string2, long l) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("message", string);
            return ApiHttpClient.z(this.q + "/api/v1/" + string2 + "/profile/public/review/respond/" + l, jsonObject, PublicProfileReviewResponse::p);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public CompletableFuture<ApiResponse<Boolean>> j(PublicProfile publicProfile, List<Long> list) {
        return this.H(publicProfile.w(), list);
    }

    public CompletableFuture<ApiResponse<PublicProfileShareInfo>> H(long l) {
        String string = ApiAccessTokenProvider.i();
        return CompletableFuture.supplyAsync(() -> this.lambda$regenerateShareCode$27(l, string));
    }

    public static boolean G() {
        return M;
    }

    private ApiResponse lambda$downloadPublicProfile$14(String string, long l) {
        try {
            return ApiHttpClient.V(this.q + "/api/v1/" + string + "/profile/public/" + l + "/download", RemoteProfileData::fromJson);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    private ApiResponse lambda$editPublicProfile$13(String string, JsonObject jsonObject) {
        try {
            return ApiHttpClient.z(this.q + "/api/v1/" + string + "/profile/public/edit", jsonObject, PublicProfileApi::lambda$null$12);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    private ApiResponse lambda$deletePublicProfileReviewResponse$22(String string, long l) {
        try {
            return ApiHttpClient.U(this.q + "/api/v1/" + string + "/profile/public/review/delete/response/" + l, null, JsonElement::getAsBoolean);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    private ApiResponse lambda$markReviewsAsRead$26(long l, List list, String string) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("profileId", (Number)l);
            JsonArray jsonArray = new JsonArray();
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                long l2 = (Long)iterator.next();
                jsonArray.add((JsonElement)new JsonPrimitive((Number)l2));
            }
            jsonObject.add("reviewIds", (JsonElement)jsonArray);
            return ApiHttpClient.z(this.q + "/api/v1/" + string + "/profile/public/review/mark", jsonObject, JsonElement::getAsBoolean);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public CompletableFuture<ApiResponse<PagedResult<PublicProfileSummary>>> r(PublicProfileSortMode publicProfileSortMode, long l, @Nullable String string, @Nullable List<String> list) {
        String string2 = ApiAccessTokenProvider.i();
        return CompletableFuture.supplyAsync(() -> this.lambda$listPublicProfiles$4(list, publicProfileSortMode, l, string, string2));
    }

    public static void Y(boolean bl) {
        M = bl;
    }

    private static PublicProfileReview lambda$null$16(JsonElement jsonElement) {
        return PublicProfileReview.a((JsonElement)jsonElement.getAsJsonObject());
    }

    private ApiResponse lambda$createPublicProfileReview$19(long l, String string, boolean bl, String string2) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("profileId", (Number)l);
            jsonObject.addProperty("reason", string);
            jsonObject.addProperty("liked", Boolean.valueOf(bl));
            return ApiHttpClient.z(this.q + "/api/v1/" + string2 + "/profile/public/review/create", jsonObject, PublicProfileReview::a);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public CompletableFuture<ApiResponse<Boolean>> Y(long l, String string) {
        String string2 = ApiAccessTokenProvider.i();
        return CompletableFuture.supplyAsync(() -> this.lambda$reportPublicProfileReview$23(string, string2, l));
    }

    private static PublicProfile lambda$null$5(JsonElement jsonElement) {
        return PublicProfile.k((JsonElement)jsonElement.getAsJsonObject());
    }

    public static boolean C() {
        boolean bl = PublicProfileApi.G();
        return false;
    }

    private ApiResponse lambda$getMostPopularTags$1(String string) {
        try {
            return ApiHttpClient.V(this.q + "/api/v1/" + string + "/profile/public/tags", PublicProfileApi::lambda$null$0);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    private ApiResponse lambda$updatePublicProfile$15(String string, long l) {
        try {
            return ApiHttpClient.V(this.q + "/api/v1/" + string + "/profile/public/" + l + "/update", RemoteProfileData::fromJson);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public CompletableFuture<ApiResponse<List<String>>> F() {
        String string = ApiAccessTokenProvider.i();
        return CompletableFuture.supplyAsync(() -> this.lambda$getMostPopularTags$1(string));
    }

    private ApiResponse lambda$deletePublicProfileReview$20(String string, long l) {
        try {
            return ApiHttpClient.U(this.q + "/api/v1/" + string + "/profile/public/review/delete/" + l, null, JsonElement::getAsBoolean);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    private static PagedResult lambda$null$7(JsonElement jsonElement) {
        return PagedResult.u(jsonElement.getAsJsonObject(), PublicProfileReview::a);
    }

    private static PublicProfileSummary lambda$null$2(JsonElement jsonElement) {
        return PublicProfileSummary.g(jsonElement.getAsJsonObject());
    }

    public CompletableFuture<ApiResponse<PublicProfile>> O(JsonObject jsonObject) {
        String string = ApiAccessTokenProvider.i();
        return CompletableFuture.supplyAsync(() -> this.lambda$editPublicProfile$13(string, jsonObject));
    }

    private ApiResponse lambda$listPublicProfiles$4(List<String> list, PublicProfileSortMode publicProfileSortMode, long l, String string, String string2) {
        try {
            JsonArray jsonArray = new JsonArray();
            if (list != null) {
                for (String string3 : list) {
                    jsonArray.add((JsonElement)new JsonPrimitive(string3));
                }
            }
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("mode", publicProfileSortMode.C());
            jsonObject.addProperty("page", (Number)l);
            jsonObject.addProperty("search", string);
            jsonObject.add("tags", (JsonElement)jsonArray);
            return ApiHttpClient.z(this.q + "/api/v1/" + string2 + "/profile/public/list", jsonObject, PublicProfileApi::lambda$null$3);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
