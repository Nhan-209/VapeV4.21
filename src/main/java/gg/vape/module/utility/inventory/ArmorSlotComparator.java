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
    final InventoryManager inventoryManager;

    public ArmorSlotComparator(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    @Override
    public int compare(Slot slot, Slot slot2) {
        ItemStack itemStack = slot.I();
        ItemStack itemStack2 = slot2.I();
        List<PotionEffect> list = new ItemSplashPotion(itemStack.getItem()).getPotionEffects(itemStack);
        List<PotionEffect> list2 = new ItemSplashPotion(itemStack2.getItem()).getPotionEffects(itemStack2);
        int result = 0;
        for (PotionEffect potionEffect : list) {
            int effectId = potionEffect.C();
            for (PotionEffect potionEffect2 : list2) {
                int otherEffectId = potionEffect2.C();
                if (effectId != otherEffectId) continue;
                result += Integer.compare(potionEffect.L(), potionEffect2.L());
            }
        }
        return result;
    }
}

