package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.InventoryItemMatchContext;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherGroup;
import gg.vape.runtime.ObfuscatedRuntimeException;
import java.util.Comparator;
import org.jetbrains.annotations.Nullable;

public class InventoryItemMatcherBuilderBase<T extends InventoryItemMatcherBuilderBase<T>> {
    @Nullable
    private Comparator<InventoryItemMatchContext> j;
    private String F;
    private InventoryItemMatcherGroup I;
    private static boolean T;
    private String L;
    private String a;
    @Nullable
    private String J;

    public T A(InventoryItemMatcherGroup wn_02) {
        this.I = wn_02;
        return (T)this;
    }

    public T m(String string) {
        this.F = string;
        return (T)this;
    }

    public static boolean y$src$Z$1hilr0k() {
        return T;
    }

    @Nullable
    public Comparator<InventoryItemMatchContext> L() {
        return this.j;
    }

    public InventoryItemMatcherGroup W() {
        return this.I;
    }

    public String S() {
        return this.F;
    }

    public String A() {
        return this.a;
    }

    public String w() {
        return this.J;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    protected InventoryItemMatcherBuilderBase(InventoryItemMatcherBuilderBase<?> qq_12) {
        this.L = qq_12.h();
        this.F = qq_12.S();
        this.a = qq_12.A();
        this.I = qq_12.W();
        this.j = qq_12.L();
    }

    public T n(String string) {
        this.L = string;
        return (T)this;
    }

    public T N(@Nullable Comparator<InventoryItemMatchContext> comparator) {
        this.j = comparator;
        return (T)this;
    }

    public T H(String string) {
        this.a = string;
        return (T)this;
    }

    public static boolean Q() {
        boolean bl = InventoryItemMatcherBuilderBase.y$src$Z$1hilr0k();
        return true;
    }

    protected InventoryItemMatcherBuilderBase() {
    }

    public String h() {
        return this.L;
    }

    public T M(String string) {
        this.J = string;
        return (T)this;
    }

    public static void S(boolean bl) {
        T = bl;
    }

    static {
        if (InventoryItemMatcherBuilderBase.y$src$Z$1hilr0k()) {
            InventoryItemMatcherBuilderBase.S(true);
        }
    }
}

