package gg.vape.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.Vape;
import gg.vape.api.ApiHttpClient;
import gg.vape.config.ClientSettings;
import gg.vape.config.ConfigJsonUtils;
import gg.vape.config.ProfileRemoteMetadata;
import gg.vape.config.ProfileSnapshot;
import gg.vape.config.PublicProfile;
import gg.vape.module.Mod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.Bendable;
import gg.vape.utils.Base64Util;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Profile
extends Bendable
implements Comparable<Profile> {
    private String l;
    private static boolean N;
    private JsonObject p;
    private JsonObject i;
    private JsonObject s;
    private int e;
    private UUID a = UUID.randomUUID();
    @Nullable
    private Integer O;
    @Nullable
    private UUID P;
    public static final int V;
    private boolean W;
    private JsonObject d;
    private boolean b;
    @Nullable
    private ProfileRemoteMetadata Z;
    private long m;
    private boolean D = true;
    private String M;
    private boolean K;
    @Nullable
    private PublicProfile n;
    private String h;
    @Deprecated
    private boolean z;

    public boolean Z() {
        return this.b;
    }

    public void a() {
        this.l = "4.21";
        this.N$src$V$1g4xz6q();
        this.p = this.k(false);
        this.s = this.k(true);
        this.R();
        this.p.add("enabled", (JsonElement)this.i);
        this.s.add("enabled", (JsonElement)this.i);
        this.y$src$V$1gsl4p9();
        this.p.add("legit_enabled", (JsonElement)this.d);
        this.s.add("legit_enabled", (JsonElement)this.d);
    }

    public void c(boolean bl) {
        this.K = bl;
    }

    public JsonObject k(boolean bl) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("modules", (JsonElement)Vape.INSTANCE.getModManager().toJson(bl));
        jsonObject.add("favorites", (JsonElement)Vape.INSTANCE.getModuleProfileMetadataCodec().J());
        jsonObject.add("values", (JsonElement)Vape.INSTANCE.getValueManager().toJson());
        jsonObject.add("macros", (JsonElement)Vape.INSTANCE.getMacrosManager().toJson());
        jsonObject.add("search", (JsonElement)Vape.INSTANCE.getSearch().toJson());
        jsonObject.add("frames", (JsonElement)gg.vape.module.none.ClientSettings.fW.J$src$Lcom_google_gson_JsonArray_$albj9k());
        return jsonObject;
    }

    public JsonObject I() {
        JsonObject jsonObject = (JsonObject)ApiHttpClient.Z.fromJson((JsonElement)this.s, JsonObject.class);
        if (jsonObject != null) {
            jsonObject.remove("sortOrder");
        }
        return jsonObject;
    }

    public Profile(String string, String string2, boolean bl) {
        this.i = new JsonObject();
        this.d = new JsonObject();
        this.M = string;
        this.l = string2;
        this.W = bl;
        this.N$src$V$1g4xz6q();
    }

    public void B(boolean bl) {
        this.b = bl;
    }

    public void K(@Nullable UUID uUID) {
        this.P = uUID;
    }

    public static void D(boolean bl) {
        N = bl;
    }

    public String P() {
        return this.l;
    }

    public JsonObject r() {
        return this.s;
    }

    public JsonObject C(boolean bl) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uuid", this.a.toString());
        if (this.P != null) {
            jsonObject.addProperty("profileId", this.P.toString());
        }
        if (bl && (this.l == null || this.l.isEmpty())) {
            this.l = "4.21";
        }
        jsonObject.addProperty("name", this.M.length() > 48 ? this.M.substring(0, 47) : this.M);
        jsonObject.addProperty(bl ? "vapeVersion" : "version", this.l);
        this.p.add("keybinds", (JsonElement)this.toJson$src$Lcom_google_gson_JsonArray_$13cfbto());
        this.p.addProperty("sortOrder", (Number)this.L$src$I$1g3udot());
        jsonObject.add("data", (JsonElement)this.p);
        jsonObject.addProperty("is_public", Boolean.valueOf(this.z));
        jsonObject.addProperty("updated", (Number)this.m);
        if (this.h != null) {
            jsonObject.addProperty("original_uuid", this.h);
        }
        return jsonObject;
    }

    public List<Mod> N$src$Ljava_util_List_$tynky5() {
        return Vape.INSTANCE.getModManager().F(this.i);
    }

    public void O(int n) {
        this.e = n;
    }

    @Nullable
    public UUID P$src$Ljava_util_UUID_$kdhg08() {
        return this.P;
    }

    @Override
    public boolean m() {
        return Vape.INSTANCE.getProfilesManager().M().equals(this);
    }

    private void N$src$V$1g4xz6q() {
        this.m = System.currentTimeMillis();
    }

    public void A(JsonObject jsonObject) {
        this.s = jsonObject;
    }

    @Override
    public String y() {
        return String.format(" %s7[%sr%s%s7]%sr %s", ClientSettings.F, ClientSettings.F, this.h(), ClientSettings.F, ClientSettings.F, this.n$src$Ljava_lang_String_$xqhelw());
    }

    public void r$src$V$1goqkjq() {
        Vape.INSTANCE.getModManager().S(this);
    }

    public long n() {
        return this.m;
    }

    public void f(PublicProfile publicProfile) {
        this.n = publicProfile;
    }

    public boolean W() {
        return this.K;
    }

    public String n$src$Ljava_lang_String_$xqhelw() {
        return this.M;
    }

    public void S(boolean bl) {
        JsonArray jsonArray;
        if (bl && Vape.INSTANCE.getPublicProfileSettings().u.L().booleanValue()) {
            Vape.INSTANCE.getModManager().y();
        }
        if (this.p.get("values") != null && !this.p.get("values").isJsonNull()) {
            Vape.INSTANCE.getValueManager().loadJson(this.p.get("values").getAsJsonArray());
        }
        if (this.p.get("modules") != null && !this.p.get("modules").isJsonNull()) {
            Vape.INSTANCE.getModManager().loadJson(this.p.get("modules").getAsJsonArray());
        }
        if (this.p.get("favorites") != null && !this.p.get("favorites").isJsonNull()) {
            Vape.INSTANCE.getModuleProfileMetadataCodec().Q(this.p.get("favorites").getAsJsonObject());
        }
        if (this.p.get("macros") != null && !this.p.get("macros").isJsonNull()) {
            Vape.INSTANCE.getMacrosManager().loadJson(this.p.get("macros").getAsJsonArray());
        }
        if (this.p.get("search") != null && !this.p.get("search").isJsonNull()) {
            jsonArray = this.p.get("search").getAsJsonArray();
            Vape.INSTANCE.getSearch().loadJson(jsonArray);
        }
        if (bl && Vape.INSTANCE.getPublicProfileSettings().u.L().booleanValue()) {
            this.r$src$V$1goqkjq();
        }
        for (Mod mod : Vape.INSTANCE.getModManager().collectMods()) {
            if (!mod.r$src$Z$14eylz9()) continue;
            mod.q(true, true);
        }
        Vape.INSTANCE.getModManager().i();
        gg.vape.module.none.ClientSettings.M$src$V$1giazqf();
        gg.vape.module.none.ClientSettings.d();
        if (this.p.get("frames") != null && !this.p.get("frames").isJsonNull() && Vape.INSTANCE.getPublicProfileSettings().Z.L().booleanValue()) {
            jsonArray = this.p.get("frames").getAsJsonArray();
            JsonArray frameGroups = new JsonArray();
            frameGroups.add((JsonElement)jsonArray);
            gg.vape.module.none.ClientSettings.fW.j(frameGroups);
        }
        if (this.p.get("original_uuid") != null && !this.p.get("original_uuid").isJsonNull()) {
            this.h = this.p.get("original_uuid").getAsString();
        }
    }

    public void B(JsonObject jsonObject) {
        this.N$src$V$1g4xz6q();
        this.F(jsonObject);
    }

    public String w() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(this.m), ZoneId.systemDefault());
        return dateTimeFormatter.format(localDateTime);
    }

    public void f(long l) {
        this.m = l;
    }

    @Nullable
    public Integer J$src$Ljava_lang_Integer_$vutkyf() {
        return this.O;
    }

    public void h(String string) {
        this.M = string;
        this.K = true;
    }

    @Override
    public void A() {
        Vape.INSTANCE.getProfilesManager().U(this);
    }

    public int z(@NotNull Profile profile) {
        return Integer.compare(this.L$src$I$1g3udot(), profile.L$src$I$1g3udot());
    }

    @Override
    public int compareTo(Profile profile) {
        return this.z(profile);
    }

    public void s(boolean bl) {
        this.z = bl;
    }

    public boolean U() {
        return this.D;
    }

    public static boolean b() {
        boolean bl = Profile.o();
        return true;
    }

    public void e() {
        this.S(true);
    }

    @Deprecated
    public boolean z() {
        return this.z;
    }

    public static boolean o() {
        return N;
    }

    public int L$src$I$1g3udot() {
        return Vape.INSTANCE.getProfilesManager().b().indexOf(this);
    }

    public JsonObject V() {
        return this.i;
    }

    public void l() {
        Vape.INSTANCE.getModManager().T(this.d);
    }

    private void F(JsonObject jsonObject) {
        Integer n;
        JsonObject jsonObject2;
        JsonObject jsonObject3;
        JsonArray jsonArray;
        this.p = jsonObject;
        JsonArray jsonArray2 = ConfigJsonUtils.q(jsonObject, "values");
        if (jsonArray2 != null) {
            this.p.add("values", (JsonElement)jsonArray2);
        }
        if ((jsonArray = ConfigJsonUtils.q(jsonObject, "keybinds")) != null) {
            this.O(jsonArray, false);
        }
        if ((jsonObject3 = ConfigJsonUtils.E(jsonObject, "enabled")) != null) {
            this.i = jsonObject3;
        }
        if ((jsonObject2 = ConfigJsonUtils.E(jsonObject, "legit_enabled")) != null) {
            this.d = jsonObject2;
        }
        if ((n = ConfigJsonUtils.r(jsonObject, "sortOrder")) != null) {
            this.O = n;
        }
    }

    public void T(boolean bl) {
        this.W = bl;
    }

    public void d(UUID uUID) {
        this.a = uUID;
    }

    private void y$src$V$1gsl4p9() {
        this.d = Vape.INSTANCE.getModManager().e();
    }

    public UUID u() {
        return this.a;
    }

    @Nullable
    public PublicProfile N() {
        return this.n;
    }

    public void R() {
        this.i = Vape.INSTANCE.getModManager().getJsonObj();
    }

    public Profile(String string, String string2) {
        this(string, string2, false);
    }

    static {
        Profile.D(false);
        long l = 2432912498588909616L;
        V = (int)l;
    }

    @Nullable
    public ProfileRemoteMetadata j$src$Lgg_vape_config_ProfileRemoteMetadata_$1dp9fd0() {
        return this.Z;
    }

    @Override
    public void c(List<Integer> list) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (Integer n : list) {
            int n2;
            if (n < 0 && (n2 = n + 100) <= 1) continue;
            arrayList.add(n);
        }
        super.c(arrayList);
    }

    public int D() {
        return this.e;
    }

    public void Y(boolean bl) {
        this.D = bl;
    }

    @Nullable
    public ProfileSnapshot n(boolean bl) {
        JsonObject jsonObject;
        JsonObject jsonObject2 = jsonObject = bl && this.s != null ? this.r() : this.J$src$Lcom_google_gson_JsonObject_$16ar19y();
        if (jsonObject.get("modules") != null && !jsonObject.get("modules").isJsonNull()) {
            return new ProfileSnapshot(this, jsonObject.get("modules").getAsJsonArray());
        }
        return null;
    }

    public boolean F() {
        return this.W;
    }

    public void U(@NotNull Integer n) {
        this.O = n;
    }

    public JsonObject J$src$Lcom_google_gson_JsonObject_$16ar19y() {
        return this.p;
    }

    public Profile e(JsonObject jsonObject) {
        String string;
        Long l;
        JsonObject jsonObject2;
        Boolean bl;
        String string2;
        String string3;
        String string4;
        String string5 = ConfigJsonUtils.P(jsonObject, "uuid");
        if (string5 != null) {
            this.a = UUID.fromString(string5);
        }
        if ((string4 = ConfigJsonUtils.P(jsonObject, "name")) != null) {
            this.M = string4;
            if (this.M.startsWith("b64:")) {
                this.M = Base64Util.decodeUtf8Base64(this.M.split(":")[1]);
            }
        } else {
            this.M = "Unknown";
        }
        if ((string3 = ConfigJsonUtils.P(jsonObject, "profileId")) != null) {
            this.P = UUID.fromString(string3);
        } else {
            this.K = true;
        }
        String string6 = ConfigJsonUtils.P(jsonObject, "version");
        if (string6 != null) {
            this.l = string6;
        }
        if ((string2 = ConfigJsonUtils.P(jsonObject, "vapeVersion")) != null) {
            this.l = string2;
        }
        if ((bl = ConfigJsonUtils.t(jsonObject, "version")) != null) {
            this.z = bl;
        }
        if ((jsonObject2 = ConfigJsonUtils.E(jsonObject, "data")) != null) {
            this.F(jsonObject2);
        }
        if ((l = ConfigJsonUtils.R(jsonObject, "updated")) != null) {
            this.f(l);
        }
        if ((string = ConfigJsonUtils.P(jsonObject, "original_uuid")) != null) {
            this.h = string;
        }
        this.Z = ProfileRemoteMetadata.s(jsonObject.get("metadata"));
        return this;
    }

    private static ObfuscatedRuntimeException c(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}
