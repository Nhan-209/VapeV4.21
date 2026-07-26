package gg.vape.protocol.packet;

import gg.vape.friend.activity.ActivitySnapshotPayload;
import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.ZeusSerializablePacket;

public class ClientActivitySnapshotPacket
implements ZeusSerializablePacket {
    private ActivitySnapshotPayload B;

    public ClientActivitySnapshotPacket() {
    }

    public ActivitySnapshotPayload A() {
        return this.B;
    }

    @Override
    public void S(ZeusPacketBuffer zeusPacketBuffer) {
        this.B = new ActivitySnapshotPayload(zeusPacketBuffer);
    }

    public ClientActivitySnapshotPacket(ActivitySnapshotPayload activitySnapshotPayload) {
        this.B = activitySnapshotPayload;
    }

    @Override
    public void o(ZeusPacketBuffer zeusPacketBuffer) {
        this.B.i(zeusPacketBuffer);
    }
}

