package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.DurabilityValueMode;

public class DurabilityValueModeSwitchMap {
    public static final int[] o = new int[DurabilityValueMode.values().length];

    DurabilityValueModeSwitchMap() {
    }

    static {
        try {
            DurabilityValueModeSwitchMap.o[DurabilityValueMode.PERCENTAGE.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            DurabilityValueModeSwitchMap.o[DurabilityValueMode.VALUE.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}

