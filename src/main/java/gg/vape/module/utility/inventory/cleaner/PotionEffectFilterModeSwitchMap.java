package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.PotionEffectFilterMode;

public class PotionEffectFilterModeSwitchMap {
    public static final int[] c = new int[PotionEffectFilterMode.values().length];

    PotionEffectFilterModeSwitchMap() {
    }

    static {
        try {
            PotionEffectFilterModeSwitchMap.c[PotionEffectFilterMode.HAS.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            PotionEffectFilterModeSwitchMap.c[PotionEffectFilterMode.LEVEL.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            PotionEffectFilterModeSwitchMap.c[PotionEffectFilterMode.DURATION.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}

