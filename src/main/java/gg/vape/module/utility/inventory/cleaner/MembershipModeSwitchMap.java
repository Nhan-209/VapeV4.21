package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.MembershipMode;

public class MembershipModeSwitchMap {
    public static final int[] w = new int[MembershipMode.values().length];

    MembershipModeSwitchMap() {
    }

    static {
        try {
            MembershipModeSwitchMap.w[MembershipMode.IS_IN.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            MembershipModeSwitchMap.w[MembershipMode.IS_NOT_IN.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}

