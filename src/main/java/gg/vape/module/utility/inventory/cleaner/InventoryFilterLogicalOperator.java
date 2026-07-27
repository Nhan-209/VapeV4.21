package gg.vape.module.utility.inventory.cleaner;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.INamed;
import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public enum InventoryFilterLogicalOperator
implements INamed {
    AND("And"),
    OR("Or");

    private static final /* synthetic */ InventoryFilterLogicalOperator[] valuesArray;
    private final String displayName;
    public static final List<InventoryFilterLogicalOperator> VALUES;

    static {
        String[] stringArray = new String[]{"AND", "OR", "And", "Or"};


        valuesArray = new InventoryFilterLogicalOperator[]{AND, OR};
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
    public static InventoryFilterLogicalOperator J(String string) {
        for (InventoryFilterLogicalOperator inventoryFilterLogicalOperator : VALUES) {
            if (!inventoryFilterLogicalOperator.getName().equalsIgnoreCase(string)) continue;
            return inventoryFilterLogicalOperator;
        }
        return null;
    }

    private static ObfuscatedRuntimeException passThrough(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

}

