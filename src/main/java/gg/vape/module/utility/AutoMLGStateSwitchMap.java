package gg.vape.module.utility;

import gg.vape.module.utility.AutoMLGState;

class AutoMLGStateSwitchMap {
    static final int[] stateOrdinalMap = new int[AutoMLGState.values().length];

    AutoMLGStateSwitchMap() {
    }

    static {
        try {
            AutoMLGStateSwitchMap.stateOrdinalMap[AutoMLGState.IDLE.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            AutoMLGStateSwitchMap.stateOrdinalMap[AutoMLGState.EQUIPPING_ITEM.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            AutoMLGStateSwitchMap.stateOrdinalMap[AutoMLGState.AIMING.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            AutoMLGStateSwitchMap.stateOrdinalMap[AutoMLGState.CONSERVING_WATER.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}

