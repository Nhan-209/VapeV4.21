package gg.vape.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.api.ApiHttpClient;
import gg.vape.api.PagedResult;
import gg.vape.config.ConfigJsonUtils;
import gg.vape.config.PublicProfileReview;
import gg.vape.config.PublicProfileShareInfo;
import gg.vape.config.PublicProfileUser;
import gg.vape.ui.click.component.GuiComponent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public class PublicProfile {
    private String d;
    @Nullable
    private final Date x;
    private final String N;
    private final Map<String, Object> W;
    private final String v;
    private final Date V;
    private final PagedResult<PublicProfileReview> G;
    private long s;
    @Nullable
    private final PublicProfileShareInfo u;
    private static GuiComponent[] n;
    private final long A;
    private final long O;
    private final long y;
    @Nullable
    private PublicProfileReview M;
    private final List<String> e;
    private long Y;
    @Nullable
    private final PublicProfileUser E;

    public long e() {
        return this.m().L() + (long)(this.z() != null ? 1 : 0);
    }

    public String h() {
        return this.v;
    }

    public int R() {
        long l = this.e();
        if (l == 0L) {
            return 0;
        }
        return (int)((double)this.J() / (double)l * 100.0);
    }

    public Map<String, Object> s$src$Ljava_util_Map_$1fhtcsp() {
        return this.W;
    }

    @Nullable
    public PublicProfileReview z() {
        return this.M;
    }

    public long K() {
        return this.O;
    }

    PublicProfile(long l, @Nullable PublicProfileUser publicProfileUser, String string, String string2, List<String> list, Map<String, Object> map, @Nullable String string3, long l2, long l3, long l4, long l5, Date date, @Nullable Date date2, @Nullable PublicProfileReview publicProfileReview, PagedResult<PublicProfileReview> pagedResult, @Nullable PublicProfileShareInfo publicProfileShareInfo) {
        this.A = l;
        this.E = publicProfileUser;
        this.N = string;
        this.v = string2;
        this.e = list;
        this.W = map;
        this.d = string3;
        this.y = l2;
        this.Y = l3;
        this.s = l4;
        this.O = l5;
        this.V = date;
        this.x = date2;
        this.M = publicProfileReview;
        this.G = pagedResult;
        this.u = publicProfileShareInfo;
    }

    public long W() {
        return this.s;
    }

    public void b(long l) {
        this.s = l;
    }

    public void M(String string) {
        this.d = string;
    }

    public String v() {
        return this.N;
    }

    @Nullable
    public Date M() {
        return this.x;
    }

    public static GuiComponent[] q() {
        return n;
    }

    public void B(@Nullable PublicProfileReview publicProfileReview) {
        this.M = publicProfileReview;
    }

    public String toString() {
        return "FullPublicProfile{profileId=" + this.A + ", owner=" + this.E + ", name='" + this.N + '\'' + ", description='" + this.v + '\'' + ", tags=" + this.e + ", data=" + this.W + ", version=" + this.y + ", likes=" + this.Y + ", dislikes=" + this.s + ", reviews=" + this.G + '}';
    }

    public long J() {
        return this.Y;
    }

    public long H() {
        return this.y;
    }

    public void E(long l) {
        this.Y = l;
    }

    public List<String> X() {
        return this.e;
    }

    public PagedResult<PublicProfileReview> m() {
        return this.G;
    }

    static {
        PublicProfile.P(null);
    }

    @Nullable
    @Contract(value="!null -> !null; null -> null")
    public static PublicProfile k(@Nullable JsonElement jsonElement) {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        ArrayList<String> arrayList = new ArrayList<String>();
        for (JsonElement jsonElement2 : jsonObject.get("tags").getAsJsonArray()) {
            arrayList.add(jsonElement2.getAsString());
        }
        try {
            return new PublicProfile(jsonObject.get("profileId").getAsLong(), PublicProfileUser.K(jsonObject.get("owner")), jsonObject.get("name").getAsString(), jsonObject.get("description").getAsString(), arrayList, (Map)ApiHttpClient.Z.fromJson(jsonObject.get("data"), Map.class), ConfigJsonUtils.P(jsonObject, "shareCode"), jsonObject.get("version").getAsLong(), jsonObject.get("likes").getAsLong(), jsonObject.get("dislikes").getAsLong(), jsonObject.get("downloads").getAsLong(), ApiHttpClient.U(jsonObject.get("creationDate").getAsString()), ApiHttpClient.U(ConfigJsonUtils.P(jsonObject, "updatedDate")), jsonObject.has("viewerReview") ? PublicProfileReview.a(jsonObject.get("viewerReview")) : null, PagedResult.u(jsonObject.get("reviews").getAsJsonObject(), PublicProfileReview::a), PublicProfileShareInfo.T(jsonObject.get("metadata")));
        }
        catch (ParseException parseException) {
            throw new RuntimeException(parseException);
        }
    }

    public long w() {
        return this.A;
    }

    public Date s$src$Ljava_util_Date_$tehmu9() {
        return this.V;
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    public String s() {
        return this.d.toUpperCase();
    }

    public Date C() {
        return this.x != null ? this.x : this.V;
    }

    @Nullable
    public PublicProfileShareInfo c() {
        return this.u;
    }

    @Nullable
    public PublicProfileUser S() {
        return this.E;
    }

    public static void P(GuiComponent[] guiComponentArray) {
        n = guiComponentArray;
    }
}

