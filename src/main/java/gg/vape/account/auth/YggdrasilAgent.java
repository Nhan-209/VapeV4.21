package gg.vape.account.auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class YggdrasilAgent {
    @Expose
    @SerializedName(value="name")
    private final String c;
    private static final String b = "Minecraft";
    @Expose
    @SerializedName(value="version")
    private final int A;

    public YggdrasilAgent(String string, int n) {
        this.c = string;
        this.A = n;
    }

    public int U() {
        return this.A;
    }

    public String p() {
        return this.c;
    }

    public YggdrasilAgent() {
        this(b, 1);
    }
}

