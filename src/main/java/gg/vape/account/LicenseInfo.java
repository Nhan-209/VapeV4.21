package gg.vape.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LicenseInfo {
    @Expose
    @SerializedName(value="username")
    private String f;
    @Expose
    @SerializedName(value="expires")
    private String L;
    @Expose
    @SerializedName(value="licenseType")
    private String A;
    @Expose
    @SerializedName(value="hasLicense")
    private boolean K;

    public final String getLicenseType() {
        return this.A;
    }

    public final String getUsername() {
        return this.f;
    }

    public final boolean hasLicense() {
        return this.K;
    }

    public final String getExpires() {
        return this.L;
    }
}

