package gg.vape.module.combat.silentaura;

import gg.vape.module.combat.silentaura.SilentAuraRotationMode;

public class SilentAuraAdaptiveRotationEntry {
    public static final int[] O = new int[SilentAuraRotationMode.values().length];

    SilentAuraAdaptiveRotationEntry() {
    }

    static {
        try {
            SilentAuraAdaptiveRotationEntry.O[SilentAuraRotationMode.FLICKING_AWAY.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            SilentAuraAdaptiveRotationEntry.O[SilentAuraRotationMode.ATTACKING.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            SilentAuraAdaptiveRotationEntry.O[SilentAuraRotationMode.IDLE.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}

