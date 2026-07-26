package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.module.utility.inventory.cleaner.InventoryCleanerProfile;
import gg.vape.module.utility.inventory.cleaner.InventoryCleanerProfileValue;

public class InventoryCleanerProfileEditContext {
    public InventoryCleanerProfileValue I;
    public InventoryCleanerProfile s;
    public Runnable T;

    public InventoryCleanerProfileEditContext(InventoryCleanerProfileValue inventoryCleanerProfileValue, InventoryCleanerProfile inventoryCleanerProfile, Runnable runnable) {
        this.I = inventoryCleanerProfileValue;
        this.s = inventoryCleanerProfile;
        this.T = runnable;
    }
}

