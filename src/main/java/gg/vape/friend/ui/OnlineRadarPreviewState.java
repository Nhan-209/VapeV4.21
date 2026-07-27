package gg.vape.friend.ui;

import org.jetbrains.annotations.Nullable;

public class OnlineRadarPreviewState<K, V> {
    private final K F;
    private static int O;
    @Nullable
    private final V Z;

    private OnlineRadarPreviewState(K k, @Nullable V v) {
        this.F = k;
        this.Z = v;
    }

    public static <K, V> OnlineRadarPreviewState<K, V> l(K k, V v) {
        return new OnlineRadarPreviewState<K, V>(k, v);
    }

    public static int T() {
        int n = OnlineRadarPreviewState.g();
        return 0;
    }

    public String toString() {
        return "Pair(key=" + this.P() + ", value=" + this.R() + ")";
    }


    public static void V(int n) {
        O = n;
    }

    public K P() {
        return this.F;
    }

    static {
        OnlineRadarPreviewState.V(10);
    }

    public static int g() {
        return O;
    }

    public V h() {
        return this.Z;
    }

    public K n() {
        return this.F;
    }

    @Nullable
    public V R() {
        return this.Z;
    }
}

