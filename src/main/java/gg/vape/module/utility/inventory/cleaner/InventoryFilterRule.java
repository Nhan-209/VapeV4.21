package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.InventoryFilterPreset;
import gg.vape.module.utility.inventory.cleaner.InventoryItemCategory;
import gg.vape.module.utility.inventory.cleaner.ItemFilterSelection;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ItemStack;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface InventoryFilterRule {
    public void p(@Nullable InventoryFilterPreset var1);

    public void U();

    @Nullable
    public UUID t();

    default public boolean q(ItemStack itemStack) {
        InventoryFilterPreset inventoryFilterPreset = this.W();
        return inventoryFilterPreset == null || inventoryFilterPreset.x(itemStack);
    }

    public ItemFilterSelection q();

    public void i(@Nullable InventoryItemCategory var1);

    @NotNull
    public InventoryItemCategory o();

    static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void y();

    public InventoryItemCategory L();

    @Nullable
    public InventoryFilterPreset W();
}

