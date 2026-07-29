package gg.vape.module.utility.inventory.cleaner;

import gg.vape.unmap.INamed;
import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public enum InventoryFilterLogicalOperator
implements INamed {
    AND("And"),
    OR("Or");

    private final String displayName;
    public static final List<InventoryFilterLogicalOperator> VALUES;

    static {
        VALUES = Arrays.asList(InventoryFilterLogicalOperator.values());
    }

    private InventoryFilterLogicalOperator(String string2) {
        this.displayName = string2;
    }

    @Override
    public String getName() {
        return this.displayName;
    }

    @Nullable
    public static InventoryFilterLogicalOperator findByName(String string) {
        for (InventoryFilterLogicalOperator inventoryFilterLogicalOperator : VALUES) {
            if (!inventoryFilterLogicalOperator.getName().equalsIgnoreCase(string)) continue;
            return inventoryFilterLogicalOperator;
        }
        return null;
    }


}

