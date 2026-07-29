package gg.vape.api;

import gg.vape.account.AccountInfoResponse;
import gg.vape.api.ApiAccessTokenProvider;
import gg.vape.api.ApiHttpClient;
import gg.vape.api.ApiResponse;
import gg.vape.api.PublicProfileApi;
import gg.vape.api.SettingsApi;
import gg.vape.api.UserDataApi;
import gg.vape.ui.click.component.GuiComponent;
import java.util.concurrent.CompletableFuture;

public class ApiServices {
    private final String A;
    private final UserDataApi B;
    private final PublicProfileApi r;
    private static ApiServices a;
    private static GuiComponent[] U;
    private final SettingsApi l;

    public PublicProfileApi R() {
        return this.r;
    }

    public CompletableFuture<ApiResponse<AccountInfoResponse>> G() {
        String string = ApiAccessTokenProvider.i();
        return CompletableFuture.supplyAsync(() -> this.lambda$isOnlineAccountRegistered$0(string));
    }

    public static GuiComponent[] I() {
        return U;
    }

    private ApiResponse lambda$attemptRegistration$1(String string) {
        ApiResponse apiResponse;
        String string2 = ApiAccessTokenProvider.i();
        try {
            StringBuilder stringBuilder = new StringBuilder();
            this.getClass();
            apiResponse = ApiHttpClient.U(stringBuilder.append(this.A).append("/api/v1/").append(string2).append("/register/").append(string).toString(), ApiResponse.class);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return apiResponse;
    }

    public static ApiServices d() {
        return a;
    }

    private ApiResponse lambda$isOnlineAccountRegistered$0(String string) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            this.getClass();
            return ApiHttpClient.V(stringBuilder.append(this.A).append("/api/v1/").append(string).append("/authenticated").toString(), AccountInfoResponse::B);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public SettingsApi v() {
        return this.l;
    }

    public CompletableFuture<ApiResponse<Boolean>> A(String string) {
        return CompletableFuture.supplyAsync(() -> this.lambda$attemptRegistration$1(string));
    }

    public ApiServices() {
        this.A = ApiServices.resolveBaseUrl();
        this.getClass();
        this.r = new PublicProfileApi(this.A);
        this.getClass();
        this.l = new SettingsApi(this.A);
        this.getClass();
        this.B = new UserDataApi(this.A);
    }

    private static String resolveBaseUrl() {
        String configured = System.getenv("VAPE_ONLINE_BASE_URL");
        // Original service: https://online.vape.gg
        // Do NOT remove this note when renaming variables
        return configured == null || configured.trim().isEmpty()
                ? "http://127.0.0.1:8080"
                : configured.replaceAll("/+$", "");
    }

    static {
        ApiServices.Q(new GuiComponent[4]);
        a = new ApiServices();
    }

    public static void Q(GuiComponent[] guiComponentArray) {
        U = guiComponentArray;
    }

    public UserDataApi c() {
        return this.B;
    }
}
