package gg.vape.module.utility.autopearl;

import gg.vape.module.utility.autopearl.AutoPearlState;

public class AutoPearlStateSwitchMap {
    public static final int[] ordinalMap = new int[AutoPearlState.values().length];

    AutoPearlStateSwitchMap() {
    }

    static {
        try {
            AutoPearlStateSwitchMap.ordinalMap[AutoPearlState.ACQUIRING_PEARL.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            AutoPearlStateSwitchMap.ordinalMap[AutoPearlState.ACQUIRING_AIMLOCK.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            AutoPearlStateSwitchMap.ordinalMap[AutoPearlState.PENDING_AIMJOB.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            AutoPearlStateSwitchMap.ordinalMap[AutoPearlState.PENDING_THROW.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            AutoPearlStateSwitchMap.ordinalMap[AutoPearlState.PENDING_RESET.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}

