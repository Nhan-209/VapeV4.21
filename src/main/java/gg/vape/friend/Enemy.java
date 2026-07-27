package gg.vape.friend;

import com.google.gson.JsonObject;
import gg.vape.Vape;
import gg.vape.friend.TargetEntry;
import gg.vape.friend.TargetType;

public class Enemy
extends TargetEntry {
    private String V;
    private String n;
    private boolean W;

    public String x() {
        return this.V;
    }

    public Enemy(String string, String string2) {
        this(string, string2, false);
    }

    public static Enemy c(JsonObject jsonObject) {
        String string = "";
        String string2 = "";
        boolean bl = true;
        if (jsonObject.get("name") != null && !jsonObject.get("name").isJsonNull()) {
            string = jsonObject.get("name").getAsString();
        }
        if (jsonObject.get("alias") != null && !jsonObject.get("alias").isJsonNull()) {
            string2 = jsonObject.get("alias").getAsString();
        }
        if (jsonObject.get("exclusive") != null && !jsonObject.get("exclusive").isJsonNull()) {
            bl = jsonObject.get("exclusive").getAsBoolean();
        }
        return new Enemy(string, string2, bl);
    }


    public void x(String string) {
        this.V = string;
    }

    public void k(boolean bl) {
        this.W = bl;
        Vape.INSTANCE.getFriendManager().m();
    }

    public String R() {
        if (Vape.INSTANCE.getEnemyManager().z.L().booleanValue()) {
            return this.x();
        }
        return this.n;
    }

    public JsonObject s$src$Lcom_google_gson_JsonObject_$hkaqtu() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", this.n);
        jsonObject.addProperty("alias", this.V);
        jsonObject.addProperty("exclusive", Boolean.valueOf(this.W));
        return jsonObject;
    }

    public Enemy(String string, String string2, boolean bl) {
        super(TargetType.ENEMY);
        this.n = string;
        this.V = string2;
        this.W = bl;
    }

    public boolean t() {
        return this.W;
    }

    public String y() {
        return this.n;
    }
}

