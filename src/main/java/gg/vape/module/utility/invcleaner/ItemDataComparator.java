package gg.vape.module.utility.invcleaner;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.datas.ItemStackData;
import java.util.Comparator;

public class ItemDataComparator
implements Comparator<ItemStackData> {
    int targetSlot;

    @Override
    public int compare(ItemStackData itemStackData, ItemStackData itemStackData2) {
        int target = this.targetSlot;
        int value1 = itemStackData.Y();
        int value2 = itemStackData2.Y();
        int distance1 = value1 > target ? value1 - target : target - value1;
        int distance2 = value2 > target ? value2 - target : target - value2;
        return distance1 < distance2 ? -1 : 0;
    }

    private static ObfuscatedRuntimeException rethrow(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public ItemDataComparator(int target) {
        this.targetSlot = target;
    }
}

