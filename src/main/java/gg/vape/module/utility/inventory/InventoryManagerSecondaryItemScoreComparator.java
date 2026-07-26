package gg.vape.module.utility.inventory;

import gg.vape.config.ClientSettings;
import gg.vape.module.utility.InventoryManager;
import gg.vape.wrapper.impl.Slot;
import java.util.Comparator;

public class InventoryManagerSecondaryItemScoreComparator
implements Comparator<Slot> {
    @Override
    public int compare(Slot first, Slot second) {
        return this.V(first, second);
    }
    final InventoryManager q;

    public InventoryManagerSecondaryItemScoreComparator(InventoryManager inventoryManager) {
        this.q = inventoryManager;
    }

    public int V(Slot slot, Slot slot2) {
        return Double.compare(ClientSettings.c(slot.I()), ClientSettings.c(slot2.I()));
    }
}
