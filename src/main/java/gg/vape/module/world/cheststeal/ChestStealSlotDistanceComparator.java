package gg.vape.module.world.cheststeal;

import gg.vape.utils.RotationUtil;
import java.util.Comparator;

public class ChestStealSlotDistanceComparator
implements Comparator {
    private final int sourceSlot;

    public ChestStealSlotDistanceComparator(int sourceSlot) {
        this.sourceSlot = sourceSlot;
    }

    public int compare(Object first, Object second) {
        int firstSlot = (Integer)first;
        int secondSlot = (Integer)second;
        int sourceColumn = this.sourceSlot % 9;
        int sourceRow = (this.sourceSlot - sourceColumn) / 9;
        int firstColumn = firstSlot % 9;
        int firstRow = (firstSlot - firstColumn) / 9;
        double firstDistance = RotationUtil.r(firstColumn, firstRow, sourceColumn, sourceRow);
        int secondColumn = secondSlot % 9;
        int secondRow = (secondSlot - secondColumn) / 9;
        double secondDistance = RotationUtil.r(secondColumn, secondRow, sourceColumn, sourceRow) + 1.0;
        return Double.compare(firstDistance, secondDistance);
    }
}

