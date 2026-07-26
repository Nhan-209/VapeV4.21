package gg.vape.module.utility.inventory;

import gg.vape.config.ClientSettings;
import gg.vape.module.utility.InventoryManager;
import gg.vape.wrapper.impl.Slot;
import java.util.Comparator;

public class InventoryManagerPrimaryItemScoreComparator
implements Comparator<Slot> {
    @Override
    public int compare(Slot first, Slot second) {
        return this.B(first, second);
    }
    final InventoryManager e;

    public int B(Slot slot, Slot slot2) {
        return Double.compare(ClientSettings.U(slot.I()), ClientSettings.U(slot2.I()));
    }

    public InventoryManagerPrimaryItemScoreComparator(InventoryManager inventoryManager) {
        this.e = inventoryManager;
    }
}
