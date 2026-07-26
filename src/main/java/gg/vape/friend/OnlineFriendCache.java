package gg.vape.friend;

import gg.vape.friend.OnlineFriend;
import gg.vape.friend.ui.OnlineFriendListEntry;
import gg.vape.runtime.ObfuscatedRuntimeException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.jetbrains.annotations.Nullable;

public class OnlineFriendCache {
    private final Map<Long, OnlineFriendListEntry> B;
    private final Map<Long, OnlineFriend> K = new LinkedHashMap<Long, OnlineFriend>();

    public OnlineFriendListEntry U(OnlineFriend yS, Supplier<OnlineFriendListEntry> supplier) {
        long l = yS.S().g();
        OnlineFriendListEntry mz_22 = this.P(l);
        if (mz_22 != null) {
            return mz_22;
        }
        mz_22 = supplier.get();
        this.B.put(l, mz_22);
        return mz_22;
    }

    public OnlineFriendCache() {
        this.B = new LinkedHashMap<Long, OnlineFriendListEntry>();
    }

    @Nullable
    public OnlineFriendListEntry P(long l) {
        return this.B.get(l);
    }

    public Collection<OnlineFriend> r() {
        return this.K.values();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public OnlineFriend Q(long l, Supplier<OnlineFriend> supplier) {
        OnlineFriend yS = this.m(l);
        if (yS != null) {
            return yS;
        }
        yS = supplier.get();
        this.K.put(yS.S().g(), yS);
        return yS;
    }

    @Nullable
    public OnlineFriend m(long l) {
        return this.K.get(l);
    }

    public void C() {
        this.K.clear();
        this.B.clear();
    }
}

