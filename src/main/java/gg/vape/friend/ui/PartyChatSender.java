package gg.vape.friend.ui;

import gg.vape.friend.OnlineFriend;
import gg.vape.friend.ui.OnlineChatSender;
import gg.vape.protocol.ZeusConnectionManager;
import gg.vape.protocol.packet.GroupChatResponsePacket;
import java.util.function.BiConsumer;

public class PartyChatSender
implements OnlineChatSender {
    @Override
    public void W(String string, BiConsumer<OnlineFriend, String> biConsumer) {
        ZeusConnectionManager.T().u().L(string, PartyChatSender::lambda$sendChatMessage$0);
    }

    private static void lambda$sendChatMessage$0(GroupChatResponsePacket groupChatResponsePacket) {
    }
}

