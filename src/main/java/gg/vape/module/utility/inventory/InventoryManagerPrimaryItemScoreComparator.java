package gg.vape.module.utility.inventory;

import gg.vape.config.ClientSettings;
import gg.vape.module.utility.InventoryManager;
import gg.vape.wrapper.impl.Slot;
import java.util.Comparator;

public class InventoryManagerPrimaryItemScoreComparator
implements Comparator<Slot> {
    @Override
    public int compare(Slot first, Slot second) {
        return this.comparePrimaryScore(first, second);
    }
    final InventoryManager inventoryManager;

    public int comparePrimaryScore(Slot first, Slot second) {
        return Double.compare(ClientSettings.getWeaponDamageScore(first.I()), ClientSettings.getWeaponDamageScore(second.I()));
    }

    public InventoryManagerPrimaryItemScoreComparator(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }
}
