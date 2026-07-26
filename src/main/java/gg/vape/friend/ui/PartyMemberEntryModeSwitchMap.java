package gg.vape.friend.ui;

import gg.vape.friend.ui.PartyMemberEntryMode;

class PartyMemberEntryModeSwitchMap {
    static final int[] B = new int[PartyMemberEntryMode.values().length];

    PartyMemberEntryModeSwitchMap() {
    }

    static {
        try {
            PartyMemberEntryModeSwitchMap.B[PartyMemberEntryMode.CURRENT_PARTY.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            PartyMemberEntryModeSwitchMap.B[PartyMemberEntryMode.INVITE.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}

