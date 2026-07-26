package gg.vape.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class LicenseStatusResponse {
    @Expose
    @SerializedName(value="skin")
    private String v;
    @Expose
    @SerializedName(value="limit")
    private boolean Y;
    @Expose
    @SerializedName(value="info")
    private List<String> Q;
    @Expose
    @SerializedName(value="token")
    private String e;
    @Expose
    @SerializedName(value="username")
    private String i;
    @Expose
    @SerializedName(value="password")
    private String f;

    public String e() {
        return this.e;
    }

    public String u() {
        return this.v;
    }

    public String f() {
        return this.i;
    }

    public String Y() {
        return this.f;
    }

    public boolean P() {
        return this.Y;
    }

    public List<String> W() {
        return this.Q;
    }
}

