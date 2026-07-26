package gg.vape.protocol.packet;

import gg.vape.friend.UserModel;
import gg.vape.friend.activity.ActivityItemStackPayload;
import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.ZeusSerializablePacket;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public class ServerInventoryUpdatePacket
implements ZeusSerializablePacket {
    private Map<Integer, @Nullable ActivityItemStackPayload> y;
    private long W;
    private static int[] J;
    private static final String b;

    @Override
    public void S(ZeusPacketBuffer zeusPacketBuffer) {
        this.W = zeusPacketBuffer.a();
        int n = zeusPacketBuffer.Y();
        if (n > 40) {
            throw new RuntimeException(b);
        }
        this.y = new HashMap<Integer, ActivityItemStackPayload>();
        for (int i = 0; i < n; ++i) {
            int n2 = zeusPacketBuffer.Y();
            boolean bl = zeusPacketBuffer.a$src$Z$1c50x8d();
            this.y.put(n2, bl ? new ActivityItemStackPayload(zeusPacketBuffer) : null);
        }
    }

    public Map<Integer, @Nullable ActivityItemStackPayload> h() {
        return this.y;
    }

    public long S() {
        return this.W;
    }

    static {
        ServerInventoryUpdatePacket.N(new int[1]);
        b = "Too many items in inventory";
    }

    public ServerInventoryUpdatePacket(UserModel userModel, Map<Integer, @Nullable ActivityItemStackPayload> map) {
        this.W = userModel.g();
        this.y = map;
    }

    @Override
    public void o(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.v(this.W);
        zeusPacketBuffer.i(this.y.size());
        for (Map.Entry<Integer, ActivityItemStackPayload> entry : this.y.entrySet()) {
            zeusPacketBuffer.i(entry.getKey());
            zeusPacketBuffer.Y(entry.getValue() != null);
            if (entry.getValue() == null) continue;
            entry.getValue().Q(zeusPacketBuffer);
        }
    }

    public static int[] R() {
        return J;
    }

    public static void N(int[] nArray) {
        J = nArray;
    }

    public ServerInventoryUpdatePacket() {
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }
}

