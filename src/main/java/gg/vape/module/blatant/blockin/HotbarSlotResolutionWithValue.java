package gg.vape.module.blatant.blockin;

import gg.vape.module.blatant.blockin.HotbarSlotResolution;
import gg.vape.module.blatant.blockin.HotbarSlotResolutionStatus;
import org.jetbrains.annotations.Nullable;

public class HotbarSlotResolutionWithValue<T>
extends HotbarSlotResolution<HotbarSlotResolutionWithValue<T>> {
    @Nullable
    private T J = null;

    HotbarSlotResolutionWithValue(HotbarSlotResolutionStatus zD, @Nullable String string, @Nullable T t) {
        super(zD, string);
        this.q(t);
    }

    public HotbarSlotResolutionWithValue() {
        this(HotbarSlotResolutionStatus.PENDING, null, null);
    }

    @Nullable
    public T w() {
        return this.J;
    }

    private HotbarSlotResolutionWithValue<T> s() {
        return this;
    }

    public HotbarSlotResolutionWithValue<T> q(@Nullable T t) {
        this.J = t;
        return this.s();
    }
}

