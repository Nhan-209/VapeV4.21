package gg.vape.module.minigame.bedplates;

import gg.vape.module.minigame.bedplates.BedPlateCountState;
import java.util.Comparator;
import java.util.Map;

public class BedPlateBlockCountComparator
implements Comparator {
    final BedPlateCountState D;

    public BedPlateBlockCountComparator(BedPlateCountState bedPlateCountState) {
        this.D = bedPlateCountState;
    }

    public int compare(Object object, Object object2) {
        return ((Integer)((Map.Entry)object).getValue()).compareTo((Integer)((Map.Entry)object2).getValue());
    }
}

