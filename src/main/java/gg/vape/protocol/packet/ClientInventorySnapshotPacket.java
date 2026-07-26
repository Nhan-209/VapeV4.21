package gg.vape.protocol.packet;

import gg.vape.friend.activity.ActivityItemStackPayload;
import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.ZeusSerializablePacket;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public class ClientInventorySnapshotPacket
implements ZeusSerializablePacket {
    private Map<Integer, @Nullable ActivityItemStackPayload> S;
    private static final String b = "Too many items in inventory";
    private int G;

    public ClientInventorySnapshotPacket() {
    }

    public Map<Integer, ActivityItemStackPayload> m() {
        return this.S;
    }

    @Override
    public void o(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.K(this.G);
        zeusPacketBuffer.i(this.S.size());
        for (Map.Entry<Integer, ActivityItemStackPayload> entry : this.S.entrySet()) {
            zeusPacketBuffer.i(entry.getKey());
            zeusPacketBuffer.Y(entry.getValue() != null);
            if (entry.getValue() == null) continue;
            entry.getValue().Q(zeusPacketBuffer);
        }
    }

    @Override
    public void S(ZeusPacketBuffer zeusPacketBuffer) {
        this.G = zeusPacketBuffer.k();
        int n = zeusPacketBuffer.Y();
        if (n > 40) {
            throw new RuntimeException(b);
        }
        this.S = new HashMap<Integer, ActivityItemStackPayload>();
        for (int i = 0; i < n; ++i) {
            int n2 = zeusPacketBuffer.Y();
            boolean bl = zeusPacketBuffer.a$src$Z$1c50x8d();
            this.S.put(n2, bl ? new ActivityItemStackPayload(zeusPacketBuffer) : null);
        }
    }

    public ClientInventorySnapshotPacket(int n, Map<Integer, @Nullable ActivityItemStackPayload> map) {
        this.G = n;
        this.S = map;
    }

    public int n() {
        return this.G;
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

