package gg.vape.protocol;

import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.ZeusPacketBufferUtil;
import gg.vape.protocol.ZeusPacketDirection;
import gg.vape.protocol.ZeusProtocolConstants;
import gg.vape.protocol.ZeusProtocolState;
import gg.vape.protocol.packet.ZeusSerializablePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

public class ZeusPacketDecoder
extends ByteToMessageDecoder {
    private final ZeusPacketDirection z;
    private final boolean d;
    private static final String b = "Read packet with no bytes";

    private static Exception a(Exception exception) {
        return exception;
    }

    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() == 0) {
            if (this.d) {
                throw new RuntimeException(b);
            }
            return;
        }
        ZeusProtocolState zeusProtocolState = (ZeusProtocolState)((Object)channelHandlerContext.channel().attr(ZeusProtocolConstants.Q).get());
        int n = ZeusPacketBufferUtil.P(byteBuf);
        ZeusSerializablePacket zeusSerializablePacket = zeusProtocolState.V(this.z, n);
        try {
            zeusSerializablePacket.S(new ZeusPacketBuffer(byteBuf));
            list.add(zeusSerializablePacket);
        }
        catch (Exception exception) {
            if (this.d) {
                exception.printStackTrace();
            }
            throw exception;
        }
    }

    public ZeusPacketDecoder(ZeusPacketDirection zeusPacketDirection, boolean bl) {
        this.z = zeusPacketDirection;
        this.d = bl;
    }
}

