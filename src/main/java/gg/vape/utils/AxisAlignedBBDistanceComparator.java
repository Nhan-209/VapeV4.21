package gg.vape.utils;

import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.EntityPlayer;
import java.util.Comparator;

public final class AxisAlignedBBDistanceComparator
implements Comparator<AxisAlignedBB> {
    final EntityPlayer n;

    @Override
    public int compare(AxisAlignedBB axisAlignedBB, AxisAlignedBB axisAlignedBB2) {
        return this.k(axisAlignedBB, axisAlignedBB2);
    }

    public int k(AxisAlignedBB axisAlignedBB, AxisAlignedBB axisAlignedBB2) {
        double d = this.n.i(axisAlignedBB.getMinX() + 0.5, this.n.N(), axisAlignedBB.getMinZ() + 0.5);
        double d2 = this.n.i(axisAlignedBB2.getMinX() + 0.5, this.n.N(), axisAlignedBB2.getMinZ() + 0.5);
        return Double.compare(d, d2);
    }

    public AxisAlignedBBDistanceComparator(EntityPlayer entityPlayer) {
        this.n = entityPlayer;
    }
}
