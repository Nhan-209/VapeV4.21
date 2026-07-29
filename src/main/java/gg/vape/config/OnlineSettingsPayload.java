package gg.vape.config;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;
import gg.vape.Vape;
import gg.vape.config.RefreshableSettingsPayload;
import gg.vape.friend.OnlineFriend;
import gg.vape.manager.client.OnlineConnectionManager;
import java.util.LinkedHashMap;
import java.util.Map;

public class OnlineSettingsPayload
implements RefreshableSettingsPayload {
    @SerializedName(value="inventorySwitchMode")
    private Integer k;
    @SerializedName(value="partyShowTarget")
    private Boolean X;
    @SerializedName(value="autoLogin")
    private Boolean x;
    @SerializedName(value="showSelf")
    private Boolean S;
    @SerializedName(value="showUsername")
    private Boolean y;
    @SerializedName(value="showInventoryKeybind")
    private JsonArray D;
    @SerializedName(value="friendStates")
    private Map<Long, Boolean> m;
    @SerializedName(value="shareInventory")
    private Boolean c;
    public static final OnlineSettingsPayload j = new OnlineSettingsPayload();
    @SerializedName(value="showServer")
    private Boolean R;
    @SerializedName(value="pingKeybind")
    private JsonArray F;

    public Boolean a() {
        return this.y;
    }

    public Boolean T() {
        if (this.S == null) {
            this.S = OnlineSettingsPayload.j.S;
        }
        return this.S;
    }

    public JsonArray b() {
        return this.D;
    }

    public Boolean k() {
        return this.x;
    }

    public Boolean R() {
        return this.X;
    }

    static {
        j.H();
    }

    public Boolean A() {
        if (this.c == null) {
            this.c = OnlineSettingsPayload.j.c;
        }
        return this.c;
    }

    @Override
    public void H() {
        if (this.x == null) {
            this.x = (Boolean)OnlineConnectionManager.T.S().X$src$Lgg_vape_value_BooleanValue_$7rygmo().getDefaultValue();
        }
        if (this.m == null) {
            this.m = new LinkedHashMap<Long, Boolean>();
        }
        if (this.R == null) {
            this.R = (Boolean)OnlineConnectionManager.T.S().z().getDefaultValue();
        }
        if (this.y == null) {
            this.y = (Boolean)OnlineConnectionManager.T.S().O().getDefaultValue();
        }
        if (this.X == null) {
            this.X = (Boolean)OnlineConnectionManager.T.S().k$src$Lgg_vape_value_BooleanValue_$ffgfgd().getDefaultValue();
        }
        if (this.c == null) {
            this.c = (Boolean)OnlineConnectionManager.T.S().l().getDefaultValue();
        }
        if (this.F == null) {
            this.F = OnlineConnectionManager.T.S().p().serializeBoundInputs();
        }
        if (this.D == null) {
            this.D = OnlineConnectionManager.T.S().k().serializeBoundInputs();
        }
        if (this.k == null) {
            this.k = OnlineConnectionManager.T.S().r$src$Lgg_vape_value_ModeValue_$lqfla9().getSelectedIndex();
        }
        if (this.S == null) {
            this.S = (Boolean)OnlineConnectionManager.T.S().j$src$Lgg_vape_value_BooleanValue_$1co7xi6().getDefaultValue();
        }
    }

    public Boolean m() {
        return this.R;
    }

    @Override
    public void M() {
        this.x = OnlineConnectionManager.T.S().X$src$Lgg_vape_value_BooleanValue_$7rygmo().getEffectiveValue();
        this.m.clear();
        for (OnlineFriend onlineFriend : Vape.INSTANCE.getOnlineFriendManager().g()) {
            if (!onlineFriend.y()) continue;
            this.m.put(onlineFriend.S().g(), true);
        }
        this.R = OnlineConnectionManager.T.S().z().getEffectiveValue();
        this.y = OnlineConnectionManager.T.S().O().getEffectiveValue();
        this.X = OnlineConnectionManager.T.S().k$src$Lgg_vape_value_BooleanValue_$ffgfgd().getEffectiveValue();
        this.c = OnlineConnectionManager.T.S().l().getEffectiveValue();
        this.F = OnlineConnectionManager.T.S().p().serializeBoundInputs();
        this.k = OnlineConnectionManager.T.S().r$src$Lgg_vape_value_ModeValue_$lqfla9().getSelectedIndex();
        this.S = OnlineConnectionManager.T.S().j$src$Lgg_vape_value_BooleanValue_$1co7xi6().getEffectiveValue();
        this.D = OnlineConnectionManager.T.S().k().serializeBoundInputs();
    }


    public JsonArray K() {
        return this.F;
    }

    public Integer x() {
        return this.k;
    }

    public Map<Long, Boolean> O() {
        return this.m;
    }
}

