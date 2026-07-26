package gg.vape.module.utility.autohotbar;

import gg.vape.module.utility.autohotbar.AutoHotbarSlotScoreComparator;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Slot;
import java.util.ArrayList;
import java.util.List;

public class AutoHotbarSlotGroup {
    private final List<Integer> k = new ArrayList<Integer>();
    private final int l;
    private final int C;

    public void N(int n) {
        this.k.add(n);
    }

    public AutoHotbarSlotGroup(int n, int n2) {
        this.l = n;
        this.C = n2;
    }

    public void W() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        List<Slot> list = entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getInventorySlots();
        AutoHotbarSlotScoreComparator autoHotbarSlotScoreComparator = new AutoHotbarSlotScoreComparator(this, list);
        this.k.sort(autoHotbarSlotScoreComparator);
    }

    public int Z() {
        return this.C;
    }

    public List<Integer> r() {
        return this.k;
    }

    public static int l(AutoHotbarSlotGroup autoHotbarSlotGroup) {
        return autoHotbarSlotGroup.C;
    }
}

