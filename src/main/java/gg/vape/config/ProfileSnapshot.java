package gg.vape.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.Vape;
import gg.vape.api.ApiHttpClient;
import gg.vape.config.ConfigJsonUtils;
import gg.vape.config.Profile;
import gg.vape.config.ProfileModuleSnapshot;
import gg.vape.config.ProfileModuleSnapshotOrderComparator;
import gg.vape.config.PublicProfile;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.SubModule;
import gg.vape.module.render.hud.HudModule;
import gg.vape.ui.click.frame.impl.profile.ProfileSnapshotGuiBuilder;
import gg.vape.utils.NameComparator;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ProfileSnapshot {
    private final List<ProfileModuleSnapshot> N;
    private static int I;
    static final boolean Z;
    private final JsonArray A;
    private final ProfileSnapshotGuiBuilder s;
    private Profile V;

    public Profile T() {
        return this.V;
    }

    public List<ProfileModuleSnapshot> Z(boolean bl) {
        List<ProfileModuleSnapshot> list = this.H(bl);
        list.sort(new ProfileModuleSnapshotOrderComparator(this));
        return list;
    }

    public JsonObject Q() {
        JsonObject jsonObject = new JsonObject();
        for (ProfileModuleSnapshot profileModuleSnapshot : this.L()) {
            if (profileModuleSnapshot.G() instanceof HudModule || !profileModuleSnapshot.Q()) continue;
            jsonObject.addProperty(profileModuleSnapshot.getName(), Boolean.valueOf(profileModuleSnapshot.Q()));
        }
        return jsonObject;
    }

    public ProfileSnapshotGuiBuilder C() {
        return this.s;
    }

    public void D() {
        if (this.V == null) {
            return;
        }
        JsonObject jsonObject = this.V.J$src$Lcom_google_gson_JsonObject_$16ar19y();
        jsonObject.add("modules", (JsonElement)this.V());
        jsonObject.add("enabled", (JsonElement)this.Q());
        this.V.B(jsonObject);
    }

    public static int O() {
        int n = ProfileSnapshot.I();
        return 0;
    }

    public void I(Profile profile) {
        this.V = profile;
    }

    public static void I(int n) {
        I = n;
    }

    public static int I() {
        return I;
    }

    public Profile d() {
        return this.V;
    }

    public JsonArray V() {
        JsonArray jsonArray = new JsonArray();
        for (ProfileModuleSnapshot profileModuleSnapshot : this.N) {
            JsonObject jsonObject = profileModuleSnapshot.g();
            if (jsonObject == null) continue;
            jsonArray.add((JsonElement)jsonObject);
        }
        return jsonArray;
    }

    public List<ProfileModuleSnapshot> H(boolean bl) {
        ArrayList<ProfileModuleSnapshot> arrayList = new ArrayList<ProfileModuleSnapshot>();
        for (ProfileModuleSnapshot profileModuleSnapshot : this.L()) {
            if (profileModuleSnapshot.G() instanceof SubModule || profileModuleSnapshot.G().getCategory() == Category.b || !profileModuleSnapshot.j() && !bl) continue;
            arrayList.add(profileModuleSnapshot);
        }
        return arrayList;
    }

    public ProfileSnapshot(Profile profile, JsonArray jsonArray) {
        JsonObject jsonObject;
        this.V = profile;
        this.A = jsonArray;
        this.N = new ArrayList<ProfileModuleSnapshot>();
        LinkedHashMap<String, JsonObject> linkedHashMap = new LinkedHashMap<String, JsonObject>();
        if (this.A != null) {
            for (JsonElement object : this.A) {
                String string;
                if (object.isJsonNull() || !object.isJsonObject() || (string = ConfigJsonUtils.P(jsonObject = object.getAsJsonObject(), "name")) == null) continue;
                linkedHashMap.put(string, jsonObject);
            }
        }
        for (Mod mod : Vape.INSTANCE.getModManager().f()) {
            jsonObject = (JsonObject)linkedHashMap.get(mod.getName());
            this.N.add(new ProfileModuleSnapshot(this, mod, jsonObject));
        }
        this.N.sort(new NameComparator());
        this.s = new ProfileSnapshotGuiBuilder(this);
    }

    public static ProfileSnapshot t(PublicProfile publicProfile, Profile profile) {
        Profile profile2 = new Profile(publicProfile.v(), profile.P());
        profile2.e(profile.C(true));
        profile2.A(profile.r());
        profile2.h(publicProfile.v());
        return new ProfileSnapshot(profile2, profile.J$src$Lcom_google_gson_JsonObject_$16ar19y().getAsJsonArray("modules"));
    }

    static {
        ProfileSnapshot.I(3);
        Z = !ProfileSnapshot.class.desiredAssertionStatus();
    }

    public List<ProfileModuleSnapshot> L() {
        return this.N;
    }


    public static ProfileSnapshot z(PublicProfile publicProfile) {
        Profile profile;
        Object var3_1;
        Object v0 = var3_1 = publicProfile.s$src$Ljava_util_Map_$1fhtcsp() != null ? publicProfile.s$src$Ljava_util_Map_$1fhtcsp().getOrDefault("modules", null) : null;
        if (!Z && publicProfile.c() == null) {
            throw new AssertionError();
        }
        Profile profile2 = profile = publicProfile.c().v() != null ? Vape.INSTANCE.getProfilesManager().H(publicProfile.c().v()) : null;
        if (profile != null) {
            if (profile.equals(Vape.INSTANCE.getProfilesManager().M())) {
                profile.a();
            }
            return profile.n(true);
        }
        if (publicProfile.c().v() != null) {
            // empty if block
        }
        Profile profile3 = new Profile(publicProfile.v(), "4.21");
        JsonArray jsonArray = (JsonArray)ApiHttpClient.Z.fromJson(var3_1 != null ? ApiHttpClient.Z.toJson(var3_1) : "[]", JsonArray.class);
        JsonObject jsonObject = new JsonObject();
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.add("modules", (JsonElement)jsonArray);
        jsonObject.add("data", (JsonElement)jsonObject2);
        profile3.e(jsonObject);
        profile3.h(publicProfile.v());
        return new ProfileSnapshot(profile3, jsonArray);
    }
}

