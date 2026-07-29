package gg.vape.manager.client;

import gg.vape.api.ApiResponse;
import gg.vape.api.ApiServices;
import gg.vape.config.GlobalSettingsPayload;
import gg.vape.config.SettingsDataType;
import gg.vape.value.BooleanValue;

public class GlobalSettingsController {
    private boolean n = false;
    private GlobalSettingsPayload W;
    private boolean j = false;
    private final BooleanValue k = BooleanValue.create(null, "Cache data", false, "Caches data locally to improve load time (%appdata%/.vapeclient)");

    public boolean A() {
        return this.n;
    }

    public BooleanValue F() {
        return this.k;
    }

    public void i() {
        this.W.J(this.k.getEffectiveValue());
        this.W.h(false);
        try {
            ApiServices.d().v().u(SettingsDataType.GLOBAL, this.W);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public void K() {
        try {
            ApiResponse apiResponse = ApiServices.d().v().h(SettingsDataType.GLOBAL);
            this.j = false;
            if (apiResponse == null || !apiResponse.t()) {
                this.W = new GlobalSettingsPayload();
                this.W.H();
            } else {
                this.W = (GlobalSettingsPayload)apiResponse.T();
            }
        }
        catch (Exception exception) {
            this.W.H();
            this.j = true;
        }
        this.n = this.W.c();
        this.k.setValue(this.W.C());
    }

    private static Exception a(Exception exception) {
        return exception;
    }
}
