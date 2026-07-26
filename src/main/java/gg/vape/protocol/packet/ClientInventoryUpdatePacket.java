package gg.vape.protocol.packet;

import gg.vape.friend.activity.ActivityItemStackPayload;
import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.ZeusSerializablePacket;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public class ClientInventoryUpdatePacket
implements ZeusSerializablePacket {
    private static final String b;
    private Map<Integer, @Nullable ActivityItemStackPayload> h;
    private static String v;

    public ClientInventoryUpdatePacket() {
    }

    @Override
    public void S(ZeusPacketBuffer zeusPacketBuffer) {
        int n = zeusPacketBuffer.Y();
        if (n > 40) {
            throw new RuntimeException(b);
        }
        this.h = new HashMap<Integer, ActivityItemStackPayload>();
        for (int i = 0; i < n; ++i) {
            int n2 = zeusPacketBuffer.Y();
            boolean bl = zeusPacketBuffer.a$src$Z$1c50x8d();
            this.h.put(n2, bl ? new ActivityItemStackPayload(zeusPacketBuffer) : null);
        }
    }

    public Map<Integer, @Nullable ActivityItemStackPayload> P() {
        return this.h;
    }

    public static void d(String string) {
        v = string;
    }

    static {
        ClientInventoryUpdatePacket.d("omJQZ");
        b = "Too many items in inventory";
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }

    public ClientInventoryUpdatePacket(Map<Integer, @Nullable ActivityItemStackPayload> map) {
        this.h = map;
    }

    public static String A() {
        return v;
    }

    @Override
    public void o(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.i(this.h.size());
        for (Map.Entry<Integer, ActivityItemStackPayload> entry : this.h.entrySet()) {
            zeusPacketBuffer.i(entry.getKey());
            zeusPacketBuffer.Y(entry.getValue() != null);
            if (entry.getValue() == null) continue;
            entry.getValue().Q(zeusPacketBuffer);
        }
    }
}

