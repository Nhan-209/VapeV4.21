package gg.vape.protocol;

import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.ZeusPacketDirection;
import gg.vape.protocol.ZeusProtocolConstants;
import gg.vape.protocol.ZeusProtocolState;
import gg.vape.protocol.packet.ZeusSerializablePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ZeusPacketEncoder
extends MessageToByteEncoder<ZeusSerializablePacket> {
    private static final String b;
    private final ZeusPacketDirection G;
    private final boolean r;
    private static boolean p;

    public static void T(boolean bl) {
        p = bl;
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    static {
        ZeusPacketEncoder.T(false);
        b = "Failed to recognize packet ";
    }

    public ZeusPacketEncoder(ZeusPacketDirection zeusPacketDirection, boolean bl) {
        this.G = zeusPacketDirection;
        this.r = bl;
    }

    public static boolean X() {
        boolean bl = ZeusPacketEncoder.J();
        return true;
    }

    public static boolean J() {
        return p;
    }

    @Override
    protected void encode(ChannelHandlerContext context, ZeusSerializablePacket packet, ByteBuf output) throws Exception {
        this.x(context, packet, output);
    }

    protected void x(ChannelHandlerContext channelHandlerContext, ZeusSerializablePacket zeusSerializablePacket, ByteBuf byteBuf) throws Exception {
        ZeusProtocolState zeusProtocolState = (ZeusProtocolState)((Object)channelHandlerContext.channel().attr(ZeusProtocolConstants.Q).get());
        int n = zeusProtocolState.Z(this.G, zeusSerializablePacket);
        if (n == -1) {
            if (this.r) {
                throw new RuntimeException(b + zeusSerializablePacket.getClass().getName());
            }
            return;
        }
        ZeusPacketBuffer zeusPacketBuffer = new ZeusPacketBuffer(byteBuf);
        zeusPacketBuffer.i(n);
        try {
            zeusSerializablePacket.o(zeusPacketBuffer);
        }
        catch (Exception exception) {
            if (this.r) {
                exception.printStackTrace();
            }
            throw exception;
        }
    }
}
