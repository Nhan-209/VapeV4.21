package gg.vape.account.auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import gg.vape.account.auth.YggdrasilAgent;
import gg.vape.account.auth.YggdrasilAuthRequest;

public class YggdrasilAuthenticateRequest
implements YggdrasilAuthRequest {
    @Expose
    @SerializedName(value="password")
    private final String Q;
    @Expose
    @SerializedName(value="agent")
    private final YggdrasilAgent c;
    @Expose
    @SerializedName(value="username")
    private final String r;
    @Expose(serialize=false, deserialize=false)
    @SerializedName(value="clientToken")
    private final String O;

    public YggdrasilAuthenticateRequest(String string, String string2, String string3, YggdrasilAgent cf_22) {
        this.c = cf_22;
        this.r = string;
        this.Q = string2;
        this.O = string3;
    }

    public YggdrasilAuthenticateRequest(String string, String string2, String string3) {
        this(string, string2, string3, new YggdrasilAgent());
    }

    public YggdrasilAuthenticateRequest(String string, String string2) {
        this(string, string2, "", new YggdrasilAgent());
    }

    public YggdrasilAuthenticateRequest(String string, String string2, YggdrasilAgent cf_22) {
        this(string, string2, "", cf_22);
    }
}

