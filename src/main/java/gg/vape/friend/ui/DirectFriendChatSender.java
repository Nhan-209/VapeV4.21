package gg.vape.friend.ui;

import gg.vape.friend.OnlineFriend;
import gg.vape.friend.ui.OnlineChatSender;
import gg.vape.protocol.ZeusConnectionManager;
import gg.vape.protocol.packet.ChatToFriendResponsePacket;
import gg.vape.ui.click.component.GuiComponent;
import java.util.function.BiConsumer;

public class DirectFriendChatSender
implements OnlineChatSender {
    private final OnlineFriend q;
    private static GuiComponent[] i;

    private void lambda$sendChatMessage$0(BiConsumer biConsumer, ChatToFriendResponsePacket chatToFriendResponsePacket) {
        biConsumer.accept(this.q, chatToFriendResponsePacket.X());
    }

    public static void c(GuiComponent[] guiComponentArray) {
        i = guiComponentArray;
    }

    @Override
    public void W(String string, BiConsumer<OnlineFriend, String> biConsumer) {
        ZeusConnectionManager.T().u().p(this.q.S(), string, chatToFriendResponsePacket -> this.lambda$sendChatMessage$0(biConsumer, (ChatToFriendResponsePacket)chatToFriendResponsePacket));
    }

    public static GuiComponent[] U() {
        return i;
    }

    public DirectFriendChatSender(OnlineFriend onlineFriend) {
        this.q = onlineFriend;
    }

    static {
        if (DirectFriendChatSender.U() != null) {
            DirectFriendChatSender.c(new GuiComponent[3]);
        }
    }
}

