package gg.vape.module.utility.inventory.cleaner;

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
    private final String description;
    private final String name;

    private InventoryFilterAction(String string2, String string3) {
        this.name = string2;
        this.description = string3;
    }

    @Nullable
    public static InventoryFilterAction findByName(String string) {
        for (InventoryFilterAction inventoryFilterAction : VALUES) {
            if (!inventoryFilterAction.getName().equalsIgnoreCase(string)) continue;
            return inventoryFilterAction;
        }
        return null;
    }

    static {
        VALUES = Arrays.asList(InventoryFilterAction.values());
    }

    @Override
    public String getName() {
        return this.name;
    }

    public static InventoryFilterAction fromName(String string) {
        InventoryFilterAction inventoryFilterAction = InventoryFilterAction.findByName(string);
        return inventoryFilterAction != null ? inventoryFilterAction : REMOVE;
    }


    @Override
    public String getDescription() {
        return this.description;
    }
}

