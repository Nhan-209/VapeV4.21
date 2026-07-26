package gg.vape.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class PagedResult<T> {
    private final boolean N;
    private final long f;
    private final long b;
    private final long y;
    private final List<T> X;
    private final long I;

    public long q() {
        return this.f;
    }

    public boolean F() {
        return this.N;
    }

    PagedResult(List<T> list, boolean bl, long l, long l2, long l3, long l4) {
        this.X = list;
        this.N = bl;
        this.y = l;
        this.b = l2;
        this.I = l3;
        this.f = l4;
    }

    public long A() {
        return this.y;
    }

    public List<T> E() {
        return this.X;
    }

    public static <T> PagedResult<T> u(JsonObject jsonObject, Function<JsonElement, T> function) {
        ArrayList<T> arrayList = new ArrayList<T>();
        for (JsonElement jsonElement : jsonObject.get("content").getAsJsonArray()) {
            arrayList.add(function.apply(jsonElement));
        }
        return new PagedResult(arrayList, jsonObject.get("last").getAsBoolean(), jsonObject.get("totalPages").getAsLong(), jsonObject.get("totalElements").getAsLong(), jsonObject.get("size").getAsLong(), jsonObject.get("numberOfElements").getAsLong());
    }

    public long L() {
        return this.b;
    }

    public long u() {
        return this.I;
    }

    public String toString() {
        return "Paged{content=" + this.X + ", last=" + this.N + ", totalPages=" + this.y + ", totalElements=" + this.b + ", size=" + this.I + ", numberOfElements=" + this.f + '}';
    }
}

