package gg.vape.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.api.ApiAccessTokenProvider;
import gg.vape.api.ApiHttpClient;
import gg.vape.api.ApiResponse;
import gg.vape.api.UserDataResponse;
import gg.vape.sync.RemoteProfileDataMap;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class UserDataApi {
    private final String s;

    public CompletableFuture<ApiResponse<Boolean>> a(JsonObject jsonObject) {
        String string = ApiAccessTokenProvider.h();
        return CompletableFuture.supplyAsync(() -> this.lambda$savePrivateData$1(string, jsonObject));
    }

    private ApiResponse lambda$savePrivateData$1(String string, JsonObject jsonObject) {
        try {
            return ApiHttpClient.z(this.s + "/api/v1/" + string + "/profile/private/save/user/", jsonObject, JsonElement::getAsBoolean);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public CompletableFuture<ApiResponse<RemoteProfileDataMap>> F(JsonObject jsonObject) {
        String string = ApiAccessTokenProvider.h();
        return CompletableFuture.supplyAsync(() -> this.lambda$savePrivateProfileData$2(string, jsonObject));
    }

    private ApiResponse lambda$savePrivateProfileData$2(String string, JsonObject jsonObject) {
        try {
            return ApiHttpClient.z(this.s + "/api/v1/" + string + "/profile/private/save/profile/", jsonObject, RemoteProfileDataMap::U);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    private ApiResponse lambda$getUserData$0(String string) {
        try {
            return ApiHttpClient.V(this.s + "/api/v1/" + string + "/profile/private/all", UserDataResponse::S);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    private ApiResponse lambda$reserveUuid$4(String string) {
        try {
            return ApiHttpClient.z(this.s + "/api/v1/" + string + "/profile/private/reserve/", null, UserDataApi::lambda$null$3);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public CompletableFuture<ApiResponse<UserDataResponse>> R() {
        String string = ApiAccessTokenProvider.h();
        return CompletableFuture.supplyAsync(() -> this.lambda$getUserData$0(string));
    }

    private static UUID lambda$null$3(JsonElement jsonElement) {
        return UUID.fromString(jsonElement.getAsString());
    }

    public CompletableFuture<ApiResponse<UUID>> u() {
        String string = ApiAccessTokenProvider.h();
        return CompletableFuture.supplyAsync(() -> this.lambda$reserveUuid$4(string));
    }

    public UserDataApi(String string) {
        this.s = string;
    }
}

