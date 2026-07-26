package gg.vape.module.utility.inventory;

import gg.vape.module.utility.InventoryManager;
import gg.vape.wrapper.impl.ItemSplashPotion;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.PotionEffect;
import gg.vape.wrapper.impl.Slot;
import java.util.Comparator;
import java.util.List;

public class ArmorSlotComparator
implements Comparator<Slot> {
    final InventoryManager F;

    public ArmorSlotComparator(InventoryManager inventoryManager) {
        this.F = inventoryManager;
    }

    @Override
    public int compare(Slot slot, Slot slot2) {
        ItemStack itemStack = slot.I();
        ItemStack itemStack2 = slot2.I();
        List<PotionEffect> list = new ItemSplashPotion(itemStack.getItem()).getPotionEffects(itemStack);
        List<PotionEffect> list2 = new ItemSplashPotion(itemStack2.getItem()).getPotionEffects(itemStack2);
        int n = 0;
        for (PotionEffect potionEffect : list) {
            int n2 = potionEffect.C();
            for (PotionEffect potionEffect2 : list2) {
                int n3 = potionEffect2.C();
                if (n2 != n3) continue;
                n += Integer.compare(potionEffect.L(), potionEffect2.L());
            }
        }
        return n;
    }
}

