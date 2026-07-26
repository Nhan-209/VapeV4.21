package gg.vape.module.utility.invcleaner;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.datas.ItemStackData;
import java.util.Comparator;

public class ItemDataComparator
implements Comparator<ItemStackData> {
    int F;

    @Override
    public int compare(ItemStackData itemStackData, ItemStackData itemStackData2) {
        int n = this.F;
        int n2 = itemStackData.Y();
        int n3 = itemStackData2.Y();
        int n4 = n2 > n ? n2 - n : n - n2;
        int n5 = n3 > n ? n3 - n : n - n3;
        return n4 < n5 ? -1 : 0;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public ItemDataComparator(int n) {
        this.F = n;
    }
}

