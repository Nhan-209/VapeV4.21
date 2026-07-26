package gg.vape.module.utility.inventory.cleaner.ui;

import org.jetbrains.annotations.Nullable;

public class ItemPickerSelection<L, R> {
    @Nullable
    private final L z;
    @Nullable
    private final R K;

    public static <L, R> ItemPickerSelection<L, R> B() {
        return new ItemPickerSelection<L, R>(null, null);
    }

    public static <L, R> ItemPickerSelection<L, R> D(@Nullable R r) {
        return new ItemPickerSelection<L, R>(null, r);
    }

    public static <L, R> ItemPickerSelection<L, R> k(@Nullable L l) {
        return new ItemPickerSelection<L, R>(l, null);
    }

    public String toString() {
        return "Either{left=" + this.z + ", right=" + this.K + '}';
    }

    ItemPickerSelection(@Nullable L l, @Nullable R r) {
        this.z = l;
        this.K = r;
    }

    @Nullable
    public L N() {
        return this.z;
    }

    @Nullable
    public R X() {
        return this.K;
    }
}
