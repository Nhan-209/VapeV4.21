package gg.vape.inventory;

import gg.vape.inventory.InventoryClickBuildStage;
import gg.vape.inventory.InventoryClickSlotStage;

public interface InventoryClickOptionsStage
extends InventoryClickSlotStage {
    public InventoryClickBuildStage b();

    public InventoryClickBuildStage I(boolean var1);

    public InventoryClickBuildStage O(boolean var1);

    public InventoryClickBuildStage D(int var1);

    public InventoryClickBuildStage U();

    public InventoryClickBuildStage w();

    public InventoryClickBuildStage E(int var1);

    public InventoryClickBuildStage O();
}

