package gg.vape.module.utility.inventory;

import gg.vape.config.ClientSettings;
import gg.vape.module.utility.InventoryManager;
import gg.vape.wrapper.impl.Slot;
import java.util.Comparator;

public class BowSlotComparator
implements Comparator<Slot> {
    @Override
    public int compare(Slot first, Slot second) {
        return this.Q(first, second);
    }
    final InventoryManager z;

    public int Q(Slot slot, Slot slot2) {
        return Double.compare(ClientSettings.X(slot.I()), ClientSettings.X(slot2.I()));
    }

    public BowSlotComparator(InventoryManager inventoryManager) {
        this.z = inventoryManager;
    }
}
