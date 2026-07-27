package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MNetworkManager;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.EntityFishHookState;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.NetworkPacketHandle;
import gg.vape.wrapper.impl.Packet;

public class NetworkManager
extends Wrapper {

    public void G(Packet packet) {
        if (ForgeVersion.MC_1_20_6.d()) {
            MNetworkManager.m(NetworkManager.c.getMappingsMapperCompat().Do, this.I, packet.getObject(), null, true);
            return;
        }
        MNetworkManager.B(NetworkManager.c.getMappingsMapperCompat().Do, this.I, packet.getObject());
    }

    public EntityFishHookState w() {
        return new EntityFishHookState(MNetworkManager.T(NetworkManager.c.getMappingsMapperCompat().Do, this.I));
    }

    public EntityFishHookState B() {
        return this.w();
    }

    public NetworkPacketHandle c() {
        return new NetworkPacketHandle(MNetworkManager.U(NetworkManager.c.getMappingsMapperCompat().Do, this.I));
    }

    public NetworkManager(Object object) {
        super(object);
    }
}

