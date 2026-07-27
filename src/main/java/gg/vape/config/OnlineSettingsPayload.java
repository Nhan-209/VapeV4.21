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
            this.x = (Boolean)OnlineConnectionManager.T.S().X$src$Lgg_vape_value_BooleanValue_$7rygmo().P$src$Ljava_lang_Object_$qcpui1();
        }
        if (this.m == null) {
            this.m = new LinkedHashMap<Long, Boolean>();
        }
        if (this.R == null) {
            this.R = (Boolean)OnlineConnectionManager.T.S().z().P$src$Ljava_lang_Object_$qcpui1();
        }
        if (this.y == null) {
            this.y = (Boolean)OnlineConnectionManager.T.S().O().P$src$Ljava_lang_Object_$qcpui1();
        }
        if (this.X == null) {
            this.X = (Boolean)OnlineConnectionManager.T.S().k$src$Lgg_vape_value_BooleanValue_$ffgfgd().P$src$Ljava_lang_Object_$qcpui1();
        }
        if (this.c == null) {
            this.c = (Boolean)OnlineConnectionManager.T.S().l().P$src$Ljava_lang_Object_$qcpui1();
        }
        if (this.F == null) {
            this.F = OnlineConnectionManager.T.S().p().toJson$src$Lcom_google_gson_JsonArray_$13cfbto();
        }
        if (this.D == null) {
            this.D = OnlineConnectionManager.T.S().k().toJson$src$Lcom_google_gson_JsonArray_$13cfbto();
        }
        if (this.k == null) {
            this.k = OnlineConnectionManager.T.S().r$src$Lgg_vape_value_ModeValue_$lqfla9().w$src$I$15qcf2k();
        }
        if (this.S == null) {
            this.S = (Boolean)OnlineConnectionManager.T.S().j$src$Lgg_vape_value_BooleanValue_$1co7xi6().P$src$Ljava_lang_Object_$qcpui1();
        }
    }

    public Boolean m() {
        return this.R;
    }

    @Override
    public void M() {
        this.x = OnlineConnectionManager.T.S().X$src$Lgg_vape_value_BooleanValue_$7rygmo().L();
        this.m.clear();
        for (OnlineFriend onlineFriend : Vape.INSTANCE.getOnlineFriendManager().g()) {
            if (!onlineFriend.y()) continue;
            this.m.put(onlineFriend.S().g(), true);
        }
        this.R = OnlineConnectionManager.T.S().z().L();
        this.y = OnlineConnectionManager.T.S().O().L();
        this.X = OnlineConnectionManager.T.S().k$src$Lgg_vape_value_BooleanValue_$ffgfgd().L();
        this.c = OnlineConnectionManager.T.S().l().L();
        this.F = OnlineConnectionManager.T.S().p().toJson$src$Lcom_google_gson_JsonArray_$13cfbto();
        this.k = OnlineConnectionManager.T.S().r$src$Lgg_vape_value_ModeValue_$lqfla9().w$src$I$15qcf2k();
        this.S = OnlineConnectionManager.T.S().j$src$Lgg_vape_value_BooleanValue_$1co7xi6().L();
        this.D = OnlineConnectionManager.T.S().k().toJson$src$Lcom_google_gson_JsonArray_$13cfbto();
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

