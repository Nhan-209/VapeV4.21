package gg.vape.protocol.packet;

import gg.vape.friend.activity.ActivitySnapshotPayload;
import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.ZeusSerializablePacket;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class ActivitySnapshotsPacket
implements ZeusSerializablePacket {
    private ActivitySnapshotPayload[] K;
    private long[] L;

    public ActivitySnapshotsPacket(long[] lArray, ActivitySnapshotPayload[] activitySnapshotPayloadArray) {
        this.L = lArray;
        this.K = activitySnapshotPayloadArray;
    }

    public long[] u() {
        return this.L;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public ActivitySnapshotsPacket() {
    }

    @Override
    public void S(ZeusPacketBuffer zeusPacketBuffer) {
        int n = zeusPacketBuffer.Y();
        this.L = new long[n];
        this.K = new ActivitySnapshotPayload[n];
        for (int i = 0; i < n; ++i) {
            this.L[i] = zeusPacketBuffer.long_a();
            this.K[i] = new ActivitySnapshotPayload(zeusPacketBuffer);
        }
    }

    @Override
    public void o(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.i(this.L.length);
        for (int i = 0; i < this.L.length; ++i) {
            zeusPacketBuffer.v(this.L[i]);
            this.K[i].i(zeusPacketBuffer);
        }
    }

    public ActivitySnapshotPayload[] Q() {
        return this.K;
    }
}

