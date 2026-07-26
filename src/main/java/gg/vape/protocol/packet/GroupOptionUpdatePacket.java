package gg.vape.protocol.packet;

import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.GroupOption;
import gg.vape.protocol.packet.ZeusSerializablePacket;

public class GroupOptionUpdatePacket
implements ZeusSerializablePacket {
    private GroupOption c;
    private static String r;
    private Object u;

    public static void C(String string) {
        r = string;
    }

    public GroupOption o() {
        return this.c;
    }

    public Object V() {
        return this.u;
    }

    @Override
    public void S(ZeusPacketBuffer zeusPacketBuffer) {
        this.c = zeusPacketBuffer.Y(GroupOption.class);
        this.u = this.c.p().apply(zeusPacketBuffer);
    }

    public static String b() {
        return r;
    }

    @Override
    public void o(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.U(this.c);
        this.c.O().accept(zeusPacketBuffer, this.u);
    }

    public GroupOptionUpdatePacket() {
    }

    public GroupOptionUpdatePacket(GroupOption groupOption, Object object) {
        this.c = groupOption;
        this.u = object;
    }

    static {
        if (GroupOptionUpdatePacket.b() == null) {
            GroupOptionUpdatePacket.C("UDYYwc");
        }
    }
}

