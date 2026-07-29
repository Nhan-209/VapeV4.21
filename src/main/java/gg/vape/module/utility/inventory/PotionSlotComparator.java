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

    public int comparePotionScore(Slot first, Slot second) {
        ItemStack firstStack = first.I();
        ItemStack secondStack = second.I();
        TileEntityEnderChest firstPotion = new TileEntityEnderChest(firstStack.getItem());
        TileEntityEnderChest secondPotion = new TileEntityEnderChest(secondStack.getItem());
        float firstScore = (float)firstPotion.o$src$I$tnn4wh() * firstPotion.o();
        float secondScore = (float)secondPotion.o$src$I$tnn4wh() * secondPotion.o();
        return Float.compare(firstScore, secondScore);
    }

    public PotionSlotComparator(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }
}
