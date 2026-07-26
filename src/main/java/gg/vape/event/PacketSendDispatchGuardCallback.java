package gg.vape.event;

import gg.vape.event.impl.EventPacketSend;
import gg.vape.utils.network.PacketDispatchGuard;

public class PacketSendDispatchGuardCallback {
    EventPacketSend I;

    public PacketSendDispatchGuardCallback(EventPacketSend eventPacketSend) {
        this.I = eventPacketSend;
    }

    public void O(PacketDispatchGuard eu_02) {
        eu_02.o(this.I);
    }
}

