package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.InventoryItemCategory;
import gg.vape.module.utility.inventory.cleaner.InventoryItemCategoryBuilder;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcher;
import gg.vape.wrapper.impl.ItemStack;
import java.util.Comparator;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

public abstract class AbstractInventoryItemCategory
implements InventoryItemCategory {
    private final String k;
    @Nullable
    private final Comparator<ItemStack> d;
    private final String L;
    private final List<InventoryItemMatcher> v;
    private final String j;
    private static String Q;

    public static String X() {
        return Q;
    }

    @Override
    public @UnmodifiableView List<InventoryItemMatcher> i() {
        return this.v;
    }

    @Override
    public String r() {
        return this.L;
    }

    protected AbstractInventoryItemCategory(InventoryItemCategoryBuilder<?> oq_12) {
        this.j = oq_12.J();
        this.k = oq_12.F();
        this.L = oq_12.X();
        this.d = oq_12.e();
        this.v = oq_12.K();
    }

    @Override
    public String F() {
        return this.j;
    }

    @Override
    public Comparator<ItemStack> j() {
        return this.d;
    }

    @Override
    public String getName() {
        return this.k;
    }

    public static void K(String string) {
        Q = string;
    }

    static {
        if (AbstractInventoryItemCategory.X() == null) {
            AbstractInventoryItemCategory.K("FTzSR");
        }
    }
}

