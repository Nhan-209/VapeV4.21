package gg.vape.module.utility.inventory;

import gg.vape.config.ClientSettings;
import gg.vape.module.utility.InventoryManager;
import gg.vape.wrapper.impl.Slot;
import java.util.Comparator;

public class InventoryManagerSecondaryItemScoreComparator
implements Comparator<Slot> {
    @Override
    public int compare(Slot first, Slot second) {
        return this.compareSecondaryScore(first, second);
    }
    final InventoryManager inventoryManager;

    public InventoryManagerSecondaryItemScoreComparator(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    public int compareSecondaryScore(Slot first, Slot second) {
        return Double.compare(ClientSettings.c(first.I()), ClientSettings.c(second.I()));
    }
}
