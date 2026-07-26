package gg.vape.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.EncoderException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class ZeusPacketBufferUtil {
    public static int P(ByteBuf byteBuf) {
        byte by;
        int n = 0;
        int n2 = 0;
        do {
            by = byteBuf.readByte();
            n |= (by & 0x7F) << n2++ * 7;
            if (n2 <= 5) continue;
            throw new RuntimeException("VarInt too big");
        } while ((by & 0x80) == 128);
        return n;
    }

    public static void L(ByteBuf byteBuf, int n) {
        while ((n & 0xFFFFFF80) != 0) {
            byteBuf.writeByte(n & 0x7F | 0x80);
            n >>>= 7;
        }
        byteBuf.writeByte(n);
    }

    public static <E extends Enum<E>> E z(ByteBuf byteBuf, Class<E> clazz) {
        return (E)((Enum[])clazz.getEnumConstants())[ZeusPacketBufferUtil.P(byteBuf)];
    }

    public static void T(ByteBuf byteBuf, String string) {
        byte[] byArray = string.getBytes(StandardCharsets.UTF_8);
        if (byArray.length > Short.MAX_VALUE) {
            throw new EncoderException("String too big (was " + string.length() + " bytes encoded, max " + Short.MAX_VALUE + ")");
        }
        ZeusPacketBufferUtil.L(byteBuf, byArray.length);
        byteBuf.writeBytes(byArray);
    }

    public static UUID l(ByteBuf byteBuf) {
        return new UUID(byteBuf.readLong(), byteBuf.readLong());
    }

    public static void z(ByteBuf byteBuf, UUID uUID) {
        byteBuf.writeLong(uUID.getMostSignificantBits());
        byteBuf.writeLong(uUID.getLeastSignificantBits());
    }

    public static String Y(ByteBuf byteBuf, int n) {
        int n2 = ZeusPacketBufferUtil.P(byteBuf);
        if (n2 > n * 4) {
            throw new DecoderException("The received encoded string buffer length is longer than maximum allowed (" + n2 + " > " + n * 4 + ")");
        }
        if (n2 < 0) {
            throw new DecoderException("The received encoded string buffer length is less than zero! Weird string!");
        }
        byte[] byArray = new byte[n2];
        byteBuf.readBytes(byArray);
        String string = new String(byArray, StandardCharsets.UTF_8);
        if (string.length() > n) {
            throw new DecoderException("The received string length is longer than maximum allowed (" + n2 + " > " + n + ")");
        }
        return string;
    }

    public static void R(ByteBuf byteBuf, Enum<?> enum_) {
        ZeusPacketBufferUtil.L(byteBuf, enum_.ordinal());
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

