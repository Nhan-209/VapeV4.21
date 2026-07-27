package gg.vape.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.config.ConfigJsonUtils;
import gg.vape.config.PublicProfileUser;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class PublicProfileSummary {
    private final long S;
    private final String B;
    @Nullable
    private final PublicProfileUser A;
    private final List<String> C;
    private final long w;
    private final long y;
    private final long Y;
    @Nullable
    private String L;

    public List<String> y() {
        return this.C;
    }

    public String toString() {
        return "SimplePublicProfile{profileId=" + this.Y + ", owner=" + this.A + ", name='" + this.B + '\'' + ", version=" + this.S + ", likes=" + this.w + ", dislikes=" + this.y + ", tags=" + this.C + '}';
    }

    @Nullable
    public PublicProfileUser l() {
        return this.A;
    }

    @Nullable
    public String n() {
        if (this.L == null) {
            return null;
        }
        return this.L.toUpperCase();
    }

    public static PublicProfileSummary g(JsonObject jsonObject) {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (JsonElement jsonElement : jsonObject.get("tags").getAsJsonArray()) {
            arrayList.add(jsonElement.getAsString());
        }
        return new PublicProfileSummary(jsonObject.get("profileId").getAsLong(), PublicProfileUser.K(jsonObject.get("owner")), jsonObject.get("name").getAsString(), jsonObject.get("version").getAsLong(), jsonObject.get("likes").getAsLong(), jsonObject.get("dislikes").getAsLong(), arrayList, ConfigJsonUtils.P(jsonObject, "shareCode"));
    }

    public long v() {
        return this.w;
    }

    PublicProfileSummary(long l, @Nullable PublicProfileUser publicProfileUser, String string, long l2, long l3, long l4, List<String> list, @Nullable String string2) {
        this.Y = l;
        this.A = publicProfileUser;
        this.B = string;
        this.S = l2;
        this.w = l3;
        this.y = l4;
        this.C = list;
        this.L = string2;
    }


    public String h$src$Ljava_lang_String_$1lo47nn() {
        return this.B;
    }

    public long s() {
        return this.y;
    }

    public long h() {
        return this.Y;
    }

    public long p() {
        return this.S;
    }
}

