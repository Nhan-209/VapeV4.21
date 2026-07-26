package gg.vape.protocol;

import gg.vape.protocol.ZeusPacketBufferUtil;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import io.netty.buffer.ByteBuf;
import java.nio.ByteBuffer;
import java.util.UUID;

public class ZeusPacketBuffer {
    private static GuiComponent[] x;
    private final ByteBuf u;

    public void x(double d) {
        this.u.writeDouble(d);
    }

    public short x() {
        return this.u.readShort();
    }

    public void Y(boolean bl) {
        this.u.writeBoolean(bl);
    }

    public ByteBuf B(byte[] byArray, int n, int n2) {
        return this.u.writeBytes(byArray, n, n2);
    }

    public void r(UUID uUID) {
        ZeusPacketBufferUtil.z(this.u, uUID);
    }

    public static int O(int n) {
        for (int i = 1; i < 5; ++i) {
            if ((n & -1 << i * 7) != 0) continue;
            return i;
        }
        return 5;
    }

    public void l(float f) {
        this.u.writeFloat(f);
    }

    public boolean boolean_a() {
        return this.u.readBoolean();
    }

    public void i(int n) {
        ZeusPacketBufferUtil.L(this.u, n);
    }

    public void t(short s) {
        this.u.writeShort((int)s);
    }

    public ByteBuf P(ByteBuf byteBuf, int n) {
        return this.u.writeBytes(byteBuf, n);
    }

    public ByteBuf j(ByteBuffer byteBuffer) {
        return this.u.writeBytes(byteBuffer);
    }

    public static void V(GuiComponent[] guiComponentArray) {
        x = guiComponentArray;
    }

    public String v(int n) {
        return ZeusPacketBufferUtil.Y(this.u, n);
    }

    public void v(long l) {
        this.u.writeLong(l);
    }

    public boolean h(int n) {
        return this.u.release(n);
    }

    public int Y() {
        return ZeusPacketBufferUtil.P(this.u);
    }

    public UUID N() {
        return ZeusPacketBufferUtil.l(this.u);
    }

    public ByteBuf M(ByteBuf byteBuf) {
        return this.u.writeBytes(byteBuf);
    }

    public int k() {
        return this.u.readInt();
    }

    public ByteBuf p(byte[] byArray) {
        return this.u.writeBytes(byArray);
    }

    public ByteBuf io_netty_buffer_ByteBuf_A() {
        return this.u;
    }

    public ByteBuf S(int n) {
        return this.u.ensureWritable(n);
    }

    public <E extends Enum<E>> E Y(Class<E> clazz) {
        return ZeusPacketBufferUtil.z(this.u, clazz);
    }

    public void y(String string) {
        ZeusPacketBufferUtil.T(this.u, string);
    }

    public void K(int n) {
        this.u.writeInt(n);
    }

    public long long_a() {
        return this.u.readLong();
    }

    public float e() {
        return this.u.readFloat();
    }

    public double S() {
        return this.u.readDouble();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void U(Enum<?> enum_) {
        ZeusPacketBufferUtil.R(this.u, enum_);
    }

    public ByteBuf L(ByteBuf byteBuf, int n, int n2) {
        return this.u.writeBytes(byteBuf, n, n2);
    }

    public static GuiComponent[] q() {
        return x;
    }

    public ZeusPacketBuffer(ByteBuf byteBuf) {
        this.u = byteBuf;
    }

    public boolean boolean_A() {
        return this.u.release();
    }

    static {
        if (ZeusPacketBuffer.q() != null) {
            ZeusPacketBuffer.V(new GuiComponent[3]);
        }
    }

    public /* synthetic */ boolean A() {
        return this.boolean_A();
    }

    public /* synthetic */ long a() {
        return this.long_a();
    }

    public /* synthetic */ boolean a$src$Z$1c50x8d() {
        return this.boolean_a();
    }
}

