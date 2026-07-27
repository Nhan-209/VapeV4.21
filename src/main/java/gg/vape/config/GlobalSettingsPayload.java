package gg.vape.config;

import com.google.gson.annotations.SerializedName;
import gg.vape.config.SettingsPayload;

public class GlobalSettingsPayload
implements SettingsPayload {
    @SerializedName(value="firstRun")
    private Boolean U;
    private static boolean r;
    @SerializedName(value="cache")
    private Boolean H;

    @Override
    public void H() {
        if (this.U == null) {
            this.U = true;
        }
        if (this.H == null) {
            this.H = false;
        }
    }

    public void J(Boolean bl) {
        this.H = bl;
    }

    public static void W(boolean bl) {
        r = bl;
    }

    public Boolean C() {
        return this.H;
    }

    public static boolean X() {
        boolean bl = GlobalSettingsPayload.f();
        return false;
    }

    public Boolean c() {
        return this.U;
    }

    public void h(Boolean bl) {
        this.U = bl;
    }


    public static boolean f() {
        return r;
    }

    static {
        if (!GlobalSettingsPayload.f()) {
            GlobalSettingsPayload.W(true);
        }
    }
}

