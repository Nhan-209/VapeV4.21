package gg.vape.module.utility.autohotbar;

import gg.vape.Vape;
import gg.vape.module.utility.AutoHotbar;
import gg.vape.module.utility.autohotbar.AutoHotbarSlotGroup;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Slot;
import java.util.Comparator;
import java.util.List;

public class AutoHotbarSlotScoreComparator
implements Comparator<Integer> {
    final List u;
    final AutoHotbarSlotGroup i;

    @Override
    public int compare(Integer n, Integer n2) {
        try {
            ItemStack itemStack = ((Slot)this.u.get(n)).I();
            ItemStack itemStack2 = ((Slot)this.u.get(n2)).I();
            double d = AutoHotbar.I(itemStack);
            double d2 = AutoHotbar.I(itemStack2);
            return Double.compare(d, d2);
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
            return 0;
        }
    }

    public AutoHotbarSlotScoreComparator(AutoHotbarSlotGroup autoHotbarSlotGroup, List list) {
        this.i = autoHotbarSlotGroup;
        this.u = list;
    }
}

