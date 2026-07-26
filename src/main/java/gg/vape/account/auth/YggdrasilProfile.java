package gg.vape.account.auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class YggdrasilProfile {
    @Expose
    @SerializedName(value="legacy")
    private boolean T;
    @Expose
    @SerializedName(value="id")
    private String p;
    @Expose
    @SerializedName(value="name")
    private String n;

    public String v() {
        return this.n;
    }

    public String s() {
        return this.p;
    }

    public boolean u() {
        return this.T;
    }
}

