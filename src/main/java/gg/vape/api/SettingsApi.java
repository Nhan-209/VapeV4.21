package gg.vape.api;

import gg.vape.api.ApiAccessTokenProvider;
import gg.vape.api.ApiHttpClient;
import gg.vape.api.ApiResponse;
import gg.vape.config.RefreshableSettingsPayload;
import gg.vape.config.SettingsDataType;
import gg.vape.config.SettingsPayload;

public class SettingsApi {
    private final String i;

    public <T> T u(SettingsDataType settingsDataType, SettingsPayload settingsPayload) throws Exception {
        String string = ApiAccessTokenProvider.i();
        if (settingsPayload instanceof RefreshableSettingsPayload) {
            ((RefreshableSettingsPayload)settingsPayload).M();
        }
        return (T)ApiHttpClient.l(this.i + "/api/v1/" + string + "/settings/save/" + settingsDataType.n().g(), settingsDataType.b(), settingsPayload);
    }

    public SettingsApi(String string) {
        this.i = string;
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    public <T> ApiResponse<T> h(SettingsDataType settingsDataType) throws Exception {
        String string = ApiAccessTokenProvider.i();
        ApiResponse apiResponse = ApiHttpClient.U(this.i + "/api/v1/" + string + "/settings/load/" + settingsDataType.n().g(), ApiResponse.class);
        if (apiResponse == null) {
            return null;
        }
        if (!apiResponse.t()) {
            return ApiResponse.w(apiResponse.N());
        }
        return ApiResponse.G((T)ApiHttpClient.Z.fromJson(ApiHttpClient.Z.toJson(apiResponse.T()), settingsDataType.b()));
    }
}
