package gg.vape.module.utility.inventory.cleaner;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.INamed;
import java.util.Arrays;
import java.util.List;
import gg.vape.module.utility.inventory.cleaner.DescribedOption;
import org.jetbrains.annotations.Nullable;

public enum InventoryFilterAction
implements INamed,
DescribedOption {
    REMOVE("Drop item", "The item will be removed from the inventory."),
    MOVE("Move into inventory", "The item will be kept in the inventory. If the item is in a hotbar slot, and you don't have a configured hotbar slot, it will attempt to move the item into your upper inventory."),
    CONDENSE("Condense stacks", "Items will be condensed into as few item stacks as possible.");

    public static final List<InventoryFilterAction> VALUES;
    private final String k;
    private static final /* synthetic */ InventoryFilterAction[] L;
    private final String b;

    private InventoryFilterAction(String string2, String string3) {
        this.b = string2;
        this.k = string3;
    }

    @Nullable
    public static InventoryFilterAction R(String string) {
        for (InventoryFilterAction inventoryFilterAction : VALUES) {
            if (!inventoryFilterAction.getName().equalsIgnoreCase(string)) continue;
            return inventoryFilterAction;
        }
        return null;
    }

    static {
        String[] stringArray = new String[]{"Move into inventory", "Items will be condensed into as few item stacks as possible.", "REMOVE", "Condense stacks", "MOVE", "The item will be removed from the inventory.", "Drop item", "CONDENSE", "The item will be kept in the inventory. If the item is in a hotbar slot, and you don't have a configured hotbar slot, it will attempt to move the item into your upper inventory."};



        L = new InventoryFilterAction[]{REMOVE, MOVE, CONDENSE};
        VALUES = Arrays.asList(InventoryFilterAction.values());
    }

    @Override
    public String getName() {
        return this.b;
    }

    public static InventoryFilterAction c(String string) {
        InventoryFilterAction inventoryFilterAction = InventoryFilterAction.R(string);
        return inventoryFilterAction != null ? inventoryFilterAction : REMOVE;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public String E() {
        return this.k;
    }
}

