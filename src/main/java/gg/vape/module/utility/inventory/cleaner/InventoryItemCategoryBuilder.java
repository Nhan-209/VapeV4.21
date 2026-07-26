package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcher;
import gg.vape.wrapper.impl.ItemStack;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

public class InventoryItemCategoryBuilder<T extends InventoryItemCategoryBuilder<T>> {
    private Comparator<ItemStack> v;
    private static String y;
    private final List<InventoryItemMatcher> M = new ArrayList<InventoryItemMatcher>();
    private String A;
    private String p;
    private String d;

    static {
        InventoryItemCategoryBuilder.H("DS7Jxc");
    }

    public static String v() {
        return y;
    }

    public T M(@NotNull String string) {
        this.d = string;
        return (T)this;
    }

    public String X() {
        return this.A;
    }

    public T f(@NotNull String string) {
        this.A = string;
        return (T)this;
    }

    public T Z(InventoryItemMatcher inventoryItemMatcher) {
        this.M.add(inventoryItemMatcher);
        return (T)this;
    }

    public Comparator<ItemStack> e() {
        return this.v;
    }

    public String F() {
        return this.d;
    }

    public static void H(String string) {
        y = string;
    }

    public T M(@Nullable Comparator<ItemStack> comparator) {
        this.v = comparator;
        return (T)this;
    }

    public T X(@NotNull String string) {
        this.p = string;
        return (T)this;
    }

    public String J() {
        return this.p;
    }

    public @UnmodifiableView List<InventoryItemMatcher> K() {
        return this.M;
    }

    protected void l() {
        Objects.requireNonNull(this.p, "id");
        Objects.requireNonNull(this.F(), "name");
    }
}

