package gg.vape.protocol;

import gg.vape.protocol.ZeusPacketBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

@ChannelHandler.Sharable
public class ZeusFrameEncoder
extends MessageToByteEncoder<ByteBuf> {
    private static int C;

    private static IllegalArgumentException a(IllegalArgumentException illegalArgumentException) {
        return illegalArgumentException;
    }

    public static void F(int n) {
        C = n;
    }

    static {
        ZeusFrameEncoder.F(67);
    }

    public static int o() {
        int n = ZeusFrameEncoder.E();
        return 0;
    }

    @Override
    protected void encode(ChannelHandlerContext context, ByteBuf input, ByteBuf output) {
        this.X(context, input, output);
    }

    protected void X(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, ByteBuf byteBuf2) {
        int n = byteBuf.readableBytes();
        int n2 = ZeusPacketBuffer.O(n);
        if (n2 > 3) {
            throw new IllegalArgumentException("unable to fit " + n + " into 3");
        }
        ZeusPacketBuffer zeusPacketBuffer = new ZeusPacketBuffer(byteBuf2);
        zeusPacketBuffer.S(n2 + n);
        zeusPacketBuffer.i(n);
        zeusPacketBuffer.L(byteBuf, byteBuf.readerIndex(), n);
    }

    public static int E() {
        return C;
    }
}
