package gg.vape.friend;

import gg.vape.friend.ui.OnlineFriendUiHelper;
import gg.vape.protocol.ZeusConnectionManager;
import gg.vape.protocol.packet.FriendRequestResponsePacket;
import gg.vape.protocol.packet.FriendRequestResponseStatus;
import gg.vape.ui.notification.NotificationMessage;
import gg.vape.ui.notification.NotificationType;

public class FriendRequestService {
    public static void Z(String string) {
        ZeusConnectionManager.T().u().Z(string, FriendRequestService::lambda$sendFriendRequest$0);
    }

    private static void lambda$sendFriendRequest$0(FriendRequestResponsePacket friendRequestResponsePacket) {
        String string;
        NotificationType notificationType;
        if (friendRequestResponsePacket.n() == FriendRequestResponseStatus.SENT) {
            notificationType = NotificationType.FRIENDINVITESENT;
            string = "Friend invite sent";
        } else if (friendRequestResponsePacket.n() == FriendRequestResponseStatus.ALREADY_SENT) {
            notificationType = NotificationType.WARNING;
            string = "You've already sent a friend request to this person";
        } else if (friendRequestResponsePacket.n() == FriendRequestResponseStatus.SELF_REQUEST) {
            notificationType = NotificationType.WARNING;
            string = "You cannot friend yourself";
        } else if (friendRequestResponsePacket.n() == FriendRequestResponseStatus.ALREADY_FRIENDS) {
            notificationType = NotificationType.WARNING;
            string = "You're already friends";
        } else if (friendRequestResponsePacket.n() == FriendRequestResponseStatus.INVALID_USER) {
            notificationType = NotificationType.WARNING;
            string = "Failed to find user";
        } else {
            notificationType = NotificationType.WARNING;
            string = "Unknown error";
        }
        OnlineFriendUiHelper.P(new NotificationMessage(notificationType, string));
    }
}

