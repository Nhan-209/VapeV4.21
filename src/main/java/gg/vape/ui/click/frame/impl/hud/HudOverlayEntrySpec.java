package gg.vape.ui.click.frame.impl.hud;

import gg.vape.ui.click.frame.Frame;
import java.util.Objects;
import java.util.function.BooleanSupplier;
import org.jetbrains.annotations.Nullable;

public final class HudOverlayEntrySpec {
    private final String m;
    @Nullable
    private final Runnable G;
    @Nullable
    private final BooleanSupplier X;
    private final String e;
    @Nullable
    private final Class<? extends Frame> K;

    public String E() {
        return this.m;
    }

    @Nullable
    public Runnable x() {
        return this.G;
    }

    public static HudOverlayEntrySpec O(String string, String string2, Class<? extends Frame> clazz) {
        return new HudOverlayEntrySpec(string, string2, clazz, null, null);
    }

    public String o() {
        return this.e;
    }

    public static HudOverlayEntrySpec s(String string, String string2, @Nullable Runnable runnable, @Nullable BooleanSupplier booleanSupplier) {
        return new HudOverlayEntrySpec(string, string2, null, booleanSupplier, runnable);
    }

    public HudOverlayEntrySpec R(BooleanSupplier booleanSupplier) {
        return new HudOverlayEntrySpec(this.e, this.m, this.K, booleanSupplier, this.G);
    }

    public HudOverlayEntrySpec s(Runnable runnable) {
        return new HudOverlayEntrySpec(this.e, this.m, this.K, this.X, runnable);
    }

    private HudOverlayEntrySpec(String string, String string2, @Nullable Class<? extends Frame> clazz, @Nullable BooleanSupplier booleanSupplier, @Nullable Runnable runnable) {
        this.e = Objects.requireNonNull(string, "label");
        this.m = Objects.requireNonNull(string2, "iconName");
        this.K = clazz;
        this.X = booleanSupplier;
        this.G = runnable;
    }

    @Nullable
    public BooleanSupplier q() {
        return this.X;
    }

    @Nullable
    public Class<? extends Frame> u() {
        return this.K;
    }
}

