package gg.vape.module.combat.crystalaura;

import gg.vape.module.combat.crystalaura.CrystalAuraActionState;

public class CrystalAuraActionStateSwitchMap {
    public static final int[] a = new int[CrystalAuraActionState.values().length];

    CrystalAuraActionStateSwitchMap() {
    }

    static {
        try {
            CrystalAuraActionStateSwitchMap.a[CrystalAuraActionState.BREAKING_CRYSTAL.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            CrystalAuraActionStateSwitchMap.a[CrystalAuraActionState.PLACING_CRYSTAL.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            CrystalAuraActionStateSwitchMap.a[CrystalAuraActionState.PLACING_OBSIDIAN.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}

