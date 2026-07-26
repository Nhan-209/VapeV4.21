package gg.vape.protocol.packet;

import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import java.lang.invoke.MethodHandles;
import java.util.function.BiConsumer;
import java.util.function.Function;

public enum GroupOption {
    OPEN_INVITES(Boolean.class, false, GroupOption::lambda$static$0, ZeusPacketBuffer::a$src$Z$1c50x8d);

    private static final GroupOption[] h;
    private final Class<?> y;
    private final BiConsumer<ZeusPacketBuffer, Object> T;
    private final Function<ZeusPacketBuffer, Object> S;
    private final Object r;

    static {
        long l = ZkmLongKeyState.a(4717177968474791541L, -8388064241494297822L, MethodHandles.lookup().lookupClass()).a(155041192211443L) ^ 0x43BFD16D2459L;
        String string = "OPEN_INVITES";

        h = new GroupOption[]{OPEN_INVITES};
    }

    public BiConsumer<ZeusPacketBuffer, Object> O() {
        return this.T;
    }

    public Class<?> u() {
        return this.y;
    }

    private static String a(byte[] byArray) {
        int n = 0;
        int n2 = byArray.length;
        char[] cArray = new char[n2];
        for (int i = 0; i < n2; ++i) {
            char c;
            int n3 = 0xFF & byArray[i];
            if (n3 < 192) {
                cArray[n++] = (char)n3;
                continue;
            }
            if (n3 < 224) {
                c = (char)((char)(n3 & 0x1F) << 6);
                n3 = byArray[++i];
                c = (char)(c | (char)(n3 & 0x3F));
                cArray[n++] = c;
                continue;
            }
            if (i >= n2 - 2) continue;
            c = (char)((char)(n3 & 0xF) << 12);
            n3 = byArray[++i];
            c = (char)(c | (char)(n3 & 0x3F) << 6);
            n3 = byArray[++i];
            c = (char)(c | (char)(n3 & 0x3F));
            cArray[n++] = c;
        }
        return new String(cArray, 0, n);
    }

    public Object z() {
        return this.r;
    }

    private GroupOption(Class<?> clazz, Object object, BiConsumer<ZeusPacketBuffer, Object> biConsumer, Function<ZeusPacketBuffer, Object> function) {
        this.y = clazz;
        this.r = object;
        this.T = biConsumer;
        this.S = function;
    }

    public Function<ZeusPacketBuffer, Object> p() {
        return this.S;
    }

    private static void lambda$static$0(ZeusPacketBuffer zeusPacketBuffer, Object object) {
        zeusPacketBuffer.Y((Boolean)object);
    }
}

