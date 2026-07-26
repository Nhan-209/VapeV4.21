package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.InventoryItemMatchContext;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherBuilderBase;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherGroup;
import java.util.Comparator;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractInventoryItemMatcher
implements InventoryItemMatcher {
    @Nullable
    private final Comparator<InventoryItemMatchContext> T;
    private final String A;
    private final InventoryItemMatcherGroup j;
    @Nullable
    private final String d;
    private final String o;
    @Nullable
    private final String J;

    @Override
    public InventoryItemMatcherGroup l() {
        return this.j;
    }

    @Override
    public String getName() {
        return this.o;
    }

    @Override
    public String E() {
        return this.J;
    }

    public AbstractInventoryItemMatcher(InventoryItemMatcherBuilderBase<?> inventoryItemMatcherBuilderBase) {
        this.A = inventoryItemMatcherBuilderBase.h();
        this.o = inventoryItemMatcherBuilderBase.S();
        this.J = inventoryItemMatcherBuilderBase.w();
        this.d = inventoryItemMatcherBuilderBase.A();
        this.j = inventoryItemMatcherBuilderBase.W();
        this.T = inventoryItemMatcherBuilderBase.L();
    }

    @Override
    public String k() {
        return this.A;
    }

    @Override
    @Nullable
    public String Z() {
        return this.d;
    }

    @Override
    @Nullable
    public Comparator<InventoryItemMatchContext> v() {
        return this.T;
    }
}

