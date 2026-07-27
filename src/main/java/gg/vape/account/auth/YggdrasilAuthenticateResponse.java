package gg.vape.account.auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import gg.vape.account.auth.YggdrasilAuthResponse;
import gg.vape.account.auth.YggdrasilProfile;
import java.util.List;

public class YggdrasilAuthenticateResponse
implements YggdrasilAuthResponse {
    @Expose
    @SerializedName(value="errorMessage")
    private String c;
    @Expose
    @SerializedName(value="selectedProfile")
    private YggdrasilProfile P;
    @Expose
    @SerializedName(value="accessToken")
    private String t;
    @Expose
    @SerializedName(value="clientToken")
    private String x;
    private static int f;
    @Expose
    @SerializedName(value="availableProfiles")
    private List<YggdrasilProfile> b;

    public YggdrasilProfile j() {
        return this.P;
    }

    public String c() {
        return this.c;
    }

    public String R() {
        return this.t;
    }

    public static int T() {
        int n = YggdrasilAuthenticateResponse.Y();
        if (n == 0) {
            return 14;
        }
        return 0;
    }

    public static void Z(int n) {
        f = n;
    }

    public List<YggdrasilProfile> i() {
        return this.b;
    }


    public static int Y() {
        return f;
    }

    public void m() {
        this.c = null;
    }

    public String G() {
        return this.x;
    }

    static {
        if (YggdrasilAuthenticateResponse.T() == 0) {
            YggdrasilAuthenticateResponse.Z(1);
        }
    }
}

