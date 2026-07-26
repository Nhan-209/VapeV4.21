package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.DefaultInventoryItemCategoryBuilder;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.ItemFilterSelection;
import gg.vape.unmap.INamed;
import gg.vape.wrapper.impl.ItemStack;
import java.util.Comparator;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

public interface InventoryItemCategory
extends INamed {
    public String F();

    public @UnmodifiableView List<InventoryItemMatcher> i();

    @Override
    public String getName();

    public static DefaultInventoryItemCategoryBuilder L() {
        return new DefaultInventoryItemCategoryBuilder();
    }

    public boolean V(ItemFilterSelection var1);

    public String r();

    @Nullable
    public Comparator<ItemStack> j();
}

