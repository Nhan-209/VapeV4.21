package gg.vape.module.blatant.anchormacro;

import gg.vape.module.blatant.anchormacro.AnchorMacroState;

public class AnchorMacroStateSwitchMap {
    public static final int[] g = new int[AnchorMacroState.values().length];

    AnchorMacroStateSwitchMap() {
    }

    static {
        try {
            AnchorMacroStateSwitchMap.g[AnchorMacroState.FINDING_ITEMS.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            AnchorMacroStateSwitchMap.g[AnchorMacroState.PLACING_ANCHOR.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            AnchorMacroStateSwitchMap.g[AnchorMacroState.WAITING_FOR_ANCHOR.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            AnchorMacroStateSwitchMap.g[AnchorMacroState.PLACING_SHIELD.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            AnchorMacroStateSwitchMap.g[AnchorMacroState.CHARGING_ANCHOR.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            AnchorMacroStateSwitchMap.g[AnchorMacroState.SWAPPING_TO_EXPLOSION_ITEM.ordinal()] = 6;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            AnchorMacroStateSwitchMap.g[AnchorMacroState.DETONATING_ANCHOR.ordinal()] = 7;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            AnchorMacroStateSwitchMap.g[AnchorMacroState.FINISH.ordinal()] = 8;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}

