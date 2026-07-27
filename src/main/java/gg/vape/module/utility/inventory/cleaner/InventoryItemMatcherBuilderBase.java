package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.InventoryItemMatchContext;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherGroup;
import gg.vape.runtime.ObfuscatedRuntimeException;
import java.util.Comparator;
import org.jetbrains.annotations.Nullable;

public class InventoryItemMatcherBuilderBase<T extends InventoryItemMatcherBuilderBase<T>> {
    @Nullable
    private Comparator<InventoryItemMatchContext> comparator;
    private String labelF;
    private InventoryItemMatcherGroup group;
    private static boolean flagT;
    private String labelL;
    private String labelA;
    @Nullable
    private String labelJ;

    public T A(InventoryItemMatcherGroup wn_02) {
        this.group = wn_02;
        return (T)this;
    }

    public T m(String string) {
        this.labelF = string;
        return (T)this;
    }

    public static boolean y$src$Z$1hilr0k() {
        return flagT;
    }

    @Nullable
    public Comparator<InventoryItemMatchContext> L() {
        return this.comparator;
    }

    public InventoryItemMatcherGroup W() {
        return this.group;
    }

    public String S() {
        return this.labelF;
    }

    public String A() {
        return this.labelA;
    }

    public String w() {
        return this.labelJ;
    }

    private static ObfuscatedRuntimeException passThrough(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    protected InventoryItemMatcherBuilderBase(InventoryItemMatcherBuilderBase<?> qq_12) {
        this.labelL = qq_12.h();
        this.labelF = qq_12.S();
        this.labelA = qq_12.A();
        this.group = qq_12.W();
        this.comparator = qq_12.L();
    }

    public T n(String string) {
        this.labelL = string;
        return (T)this;
    }

    public T N(@Nullable Comparator<InventoryItemMatchContext> comparatorArg) {
        this.comparator = comparatorArg;
        return (T)this;
    }

    public T H(String string) {
        this.labelA = string;
        return (T)this;
    }

    public static boolean Q() {
        boolean bl = InventoryItemMatcherBuilderBase.y$src$Z$1hilr0k();
        return true;
    }

    protected InventoryItemMatcherBuilderBase() {
    }

    public String h() {
        return this.labelL;
    }

    public T M(String string) {
        this.labelJ = string;
        return (T)this;
    }

    public static void S(boolean bl) {
        flagT = bl;
    }

    static {
        if (InventoryItemMatcherBuilderBase.y$src$Z$1hilr0k()) {
            InventoryItemMatcherBuilderBase.S(true);
        }
    }
}

