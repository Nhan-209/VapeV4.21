package gg.vape.friend.ui;

import gg.vape.protocol.packet.GroupInviteStatus;

public class OnlineFriendListEntryGroupInviteStatusSwitchMap {
    public static final int[] g = new int[GroupInviteStatus.values().length];

    OnlineFriendListEntryGroupInviteStatusSwitchMap() {
    }

    static {
        try {
            OnlineFriendListEntryGroupInviteStatusSwitchMap.g[GroupInviteStatus.SUCCESS.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            OnlineFriendListEntryGroupInviteStatusSwitchMap.g[GroupInviteStatus.TOO_MANY_INVITES.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            OnlineFriendListEntryGroupInviteStatusSwitchMap.g[GroupInviteStatus.NOT_ONLINE.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            OnlineFriendListEntryGroupInviteStatusSwitchMap.g[GroupInviteStatus.ALREADY_INVITED.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            OnlineFriendListEntryGroupInviteStatusSwitchMap.g[GroupInviteStatus.FAILED.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}

