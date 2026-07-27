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
    private final String name;
    @Nullable
    private final Comparator<ItemStack> comparator;
    private final String displayName;
    private final List<InventoryItemMatcher> matchers;
    private final String id;
    private static String secretKey;

    public static String X() {
        return secretKey;
    }

    @Override
    public @UnmodifiableView List<InventoryItemMatcher> i() {
        return this.matchers;
    }

    @Override
    public String r() {
        return this.displayName;
    }

    protected AbstractInventoryItemCategory(InventoryItemCategoryBuilder<?> builder) {
        this.id = builder.J();
        this.name = builder.F();
        this.displayName = builder.X();
        this.comparator = builder.e();
        this.matchers = builder.K();
    }

    @Override
    public String F() {
        return this.id;
    }

    @Override
    public Comparator<ItemStack> j() {
        return this.comparator;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public static void K(String string) {
        secretKey = string;
    }

    static {
        if (AbstractInventoryItemCategory.X() == null) {
            AbstractInventoryItemCategory.K("FTzSR");
        }
    }
}

