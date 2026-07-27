package gg.vape.protocol.packet;

import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.GroupOption;
import gg.vape.protocol.packet.ZeusSerializablePacket;

public class ServerGroupOptionUpdatePacket
implements ZeusSerializablePacket {
    private GroupOption I;
    private static int m;
    private Object a;

    @Override
    public void S(ZeusPacketBuffer zeusPacketBuffer) {
        this.I = zeusPacketBuffer.Y(GroupOption.class);
        this.a = this.I.p().apply(zeusPacketBuffer);
    }

    public static int s() {
        int n = ServerGroupOptionUpdatePacket.T();
        if (n == 0) {
            return 32;
        }
        return 0;
    }

    @Override
    public void o(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.U(this.I);
        this.I.O().accept(zeusPacketBuffer, this.a);
    }

    public Object Z() {
        return this.a;
    }

    public GroupOption f() {
        return this.I;
    }

    public static int T() {
        return m;
    }

    public ServerGroupOptionUpdatePacket(GroupOption groupOption, Object object) {
        this.I = groupOption;
        this.a = object;
    }

    public static void j(int n) {
        m = n;
    }

    public ServerGroupOptionUpdatePacket() {
    }


    static {
        if (ServerGroupOptionUpdatePacket.s() != 0) {
            ServerGroupOptionUpdatePacket.j(13);
        }
    }
}

