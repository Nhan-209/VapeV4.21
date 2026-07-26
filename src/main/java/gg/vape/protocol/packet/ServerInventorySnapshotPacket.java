package gg.vape.protocol.packet;

import gg.vape.friend.UserModel;
import gg.vape.friend.activity.ActivityItemStackPayload;
import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.ZeusSerializablePacket;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public class ServerInventorySnapshotPacket
implements ZeusSerializablePacket {
    private static final String b = "Too many items in inventory";
    private Map<Integer, @Nullable ActivityItemStackPayload> X;
    private int Y;
    private long F;

    public ServerInventorySnapshotPacket(UserModel userModel, int n, Map<Integer, @Nullable ActivityItemStackPayload> map) {
        this.F = userModel.g();
        this.Y = n;
        this.X = map;
    }

    public int A() {
        return this.Y;
    }

    @Override
    public void o(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.v(this.F);
        zeusPacketBuffer.i(this.Y);
        zeusPacketBuffer.i(this.X.size());
        for (Map.Entry<Integer, ActivityItemStackPayload> entry : this.X.entrySet()) {
            zeusPacketBuffer.i(entry.getKey());
            zeusPacketBuffer.Y(entry.getValue() != null);
            if (entry.getValue() == null) continue;
            entry.getValue().Q(zeusPacketBuffer);
        }
    }

    @Override
    public void S(ZeusPacketBuffer zeusPacketBuffer) {
        this.F = zeusPacketBuffer.a();
        this.Y = zeusPacketBuffer.Y();
        int n = zeusPacketBuffer.Y();
        if (n > 40) {
            throw new RuntimeException(b);
        }
        this.X = new HashMap<Integer, ActivityItemStackPayload>();
        for (int i = 0; i < n; ++i) {
            int n2 = zeusPacketBuffer.Y();
            boolean bl = zeusPacketBuffer.a$src$Z$1c50x8d();
            this.X.put(n2, bl ? new ActivityItemStackPayload(zeusPacketBuffer) : null);
        }
    }

    public Map<Integer, @Nullable ActivityItemStackPayload> N() {
        return this.X;
    }

    public long f() {
        return this.F;
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }

    public ServerInventorySnapshotPacket() {
    }
}

