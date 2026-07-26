package gg.vape.friend.ui;

import gg.vape.protocol.packet.GroupInviteStatus;

public class PartyInviteFriendRowGroupInviteStatusSwitchMap {
    public static final int[] W = new int[GroupInviteStatus.values().length];

    PartyInviteFriendRowGroupInviteStatusSwitchMap() {
    }

    static {
        try {
            PartyInviteFriendRowGroupInviteStatusSwitchMap.W[GroupInviteStatus.SUCCESS.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            PartyInviteFriendRowGroupInviteStatusSwitchMap.W[GroupInviteStatus.TOO_MANY_INVITES.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            PartyInviteFriendRowGroupInviteStatusSwitchMap.W[GroupInviteStatus.NOT_ONLINE.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            PartyInviteFriendRowGroupInviteStatusSwitchMap.W[GroupInviteStatus.ALREADY_INVITED.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            PartyInviteFriendRowGroupInviteStatusSwitchMap.W[GroupInviteStatus.FAILED.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}

