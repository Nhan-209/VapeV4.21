package gg.vape.module.utility.inventory;

import gg.vape.module.utility.InventoryManager;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Slot;
import gg.vape.wrapper.impl.TileEntityEnderChest;
import java.util.Comparator;

public class PotionSlotComparator
implements Comparator<Slot> {
    @Override
    public int compare(Slot first, Slot second) {
        return this.comparePotionScore(first, second);
    }
    final InventoryManager inventoryManager;

    public int comparePotionScore(Slot slot, Slot slot2) {
        ItemStack itemStack = slot.I();
        ItemStack itemStack2 = slot2.I();
        TileEntityEnderChest tileEntityEnderChest = new TileEntityEnderChest(itemStack.getItem());
        TileEntityEnderChest tileEntityEnderChest2 = new TileEntityEnderChest(itemStack2.getItem());
        float score = (float)tileEntityEnderChest.o$src$I$tnn4wh() * tileEntityEnderChest.o();
        float score2 = (float)tileEntityEnderChest2.o$src$I$tnn4wh() * tileEntityEnderChest2.o();
        return Float.compare(score, score2);
    }

    public PotionSlotComparator(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }
}
