package gg.vape.module.utility;

import gg.vape.module.utility.AutoMLGState;

class AutoMLGStateSwitchMap {
    static final int[] u = new int[AutoMLGState.values().length];

    AutoMLGStateSwitchMap() {
    }

    static {
        try {
            AutoMLGStateSwitchMap.u[AutoMLGState.IDLE.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            AutoMLGStateSwitchMap.u[AutoMLGState.EQUIPPING_ITEM.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            AutoMLGStateSwitchMap.u[AutoMLGState.AIMING.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            AutoMLGStateSwitchMap.u[AutoMLGState.CONSERVING_WATER.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}

