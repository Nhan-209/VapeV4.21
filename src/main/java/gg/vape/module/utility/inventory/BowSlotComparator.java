package gg.vape.module.utility.inventory;

import gg.vape.config.ClientSettings;
import gg.vape.module.utility.InventoryManager;
import gg.vape.wrapper.impl.Slot;
import java.util.Comparator;

public class BowSlotComparator
implements Comparator<Slot> {
    @Override
    public int compare(Slot first, Slot second) {
        return this.compareBowScore(first, second);
    }
    final InventoryManager inventoryManager;

    public int compareBowScore(Slot first, Slot second) {
        return Double.compare(ClientSettings.X(first.I()), ClientSettings.X(second.I()));
    }

    public BowSlotComparator(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }
}
