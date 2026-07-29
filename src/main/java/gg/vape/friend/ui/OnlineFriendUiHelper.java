package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.OnlineFriend;
import gg.vape.friend.ui.ChatMessagePartyMemberRow;
import gg.vape.friend.ui.OnlineFriendListEntry;
import gg.vape.friend.ui.OnlineFriendsFrame;
import gg.vape.friend.ui.PartyDetailsAndChatPanel;
import gg.vape.friend.ui.PartyMemberRow;
import gg.vape.friend.ui.PartyMemberTextStatusComponent;
import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.notification.NotificationMessage;
import gg.vape.ui.notification.NotificationType;

public final class OnlineFriendUiHelper {
    private static final String b;
    private static int K;

    public static int n() {
        return K;
    }

    public static void w(NotificationType notificationType, String string) {
        OnlineFriendUiHelper.P(new NotificationMessage(notificationType, string));
    }

    public static void q(int n) {
        K = n;
    }

    public static void R(NotificationType notificationType, String string) {
        OnlineFriendUiHelper.P(new NotificationMessage(notificationType, string));
    }

    public static void n$src$V$uh9sir() {
        ClientSettings.getFrame(OnlineFriendsFrame.class).d$src$Lgg_vape_friend_ui_OnlineFriendEntriesPanel_$86qf3j().b$src$V$1a27gcp();
        OnlineFriendUiHelper.U();
    }

    public static OnlineFriendsFrame N(GuiComponent guiComponent) {
        while (!guiComponent.getClass().equals(OnlineFriendsFrame.class)) {
            try {
                guiComponent = guiComponent.getParentFrameComponent();
            }
            catch (Exception exception) {
                // empty catch block
                break;
            }
        }
        return (OnlineFriendsFrame)guiComponent;
    }


    static {
        OnlineFriendUiHelper.q(0);
        b = "Message from \u00a7f";
    }

    public static int T() {
        int n = OnlineFriendUiHelper.n();
        return 21;
    }

    public static void y(PartyMemberRow partyMemberRow) {
        OnlineFriendsFrame onlineFriendsFrame = ClientSettings.getFrame(OnlineFriendsFrame.class);
        PartyDetailsAndChatPanel partyDetailsAndChatPanel = onlineFriendsFrame.x$src$Lgg_vape_friend_ui_OnlineFriendsListPanel_$lt2vne().U$src$Lgg_vape_friend_ui_CurrentPartyPanel_$nthhyv().v$src$Lgg_vape_friend_ui_PartyDetailsAndChatPanel_$1pxu2wh();
        if (partyDetailsAndChatPanel == null) {
            return;
        }
        partyDetailsAndChatPanel.e$src$Lgg_vape_friend_ui_OnlineChatPanel_$1fym7va().z().b(partyMemberRow);
    }

    public static void P(NotificationMessage notificationMessage) {
        ClientSettings.getFrame(OnlineFriendsFrame.class).V$src$Lgg_vape_ui_notification_NotificationToastOverla$1025be3().C(notificationMessage);
    }

    public static void U() {
        ClientSettings.getFrame(OnlineFriendsFrame.class).R$src$Lgg_vape_friend_ui_FriendEntriesPanel_$19ux45q().k$src$V$gzyo7z();
    }

    public static void l(OnlineFriend onlineFriend, OnlineFriend onlineFriend2, String string) {
        OnlineFriendListEntry onlineFriendListEntry = Vape.INSTANCE.getOnlineManager().u().P(onlineFriend.S().g());
        if (onlineFriendListEntry == null) {
            return;
        }
        onlineFriend.F(true);
        onlineFriend.J(true);
        if (onlineFriend2 != null) {
            Vape.INSTANCE.getNotificationManager().show(b + onlineFriend2.C(), string, gg.vape.notification.NotificationType.FRIENDS_NEW_CHAT, 4000L);
        }
        OnlineFriend onlineFriend3 = onlineFriend2 == null ? Vape.INSTANCE.getOnlineManager().r() : onlineFriend2;
        ChatMessagePartyMemberRow chatMessagePartyMemberRow = new ChatMessagePartyMemberRow(onlineFriend3, new PartyMemberTextStatusComponent(string));
        onlineFriendListEntry.s$src$Lgg_vape_friend_ui_OnlineFriendCard_$urytiw().d$src$Lgg_vape_friend_ui_OnlineFriendDetailsPanel_$1i561zb().o$src$Lgg_vape_friend_ui_OnlineChatPanel_$15yewwy().z().b(chatMessagePartyMemberRow);
        onlineFriendListEntry.s$src$Lgg_vape_friend_ui_OnlineFriendCard_$urytiw().d$src$Lgg_vape_friend_ui_OnlineFriendDetailsPanel_$1i561zb().o$src$Lgg_vape_friend_ui_OnlineChatPanel_$15yewwy().z().l$src$V$1mibm4x();
        onlineFriendListEntry.s$src$Lgg_vape_friend_ui_OnlineFriendCard_$urytiw().d$src$V$ttzgw7();
    }
}

