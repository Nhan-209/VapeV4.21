package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MNetworkManager;
import gg.vape.wrapper.Wrapper;

public class NetworkManager
extends Wrapper {

    public void G(Packet packet) {
        if (ForgeVersion.MC_1_20_6.d()) {
            MNetworkManager.m(NetworkManager.vapeInstance.getMappingsMapperCompat().Do, this.I, packet.getObject(), null, true);
            return;
        }
        MNetworkManager.B(NetworkManager.vapeInstance.getMappingsMapperCompat().Do, this.I, packet.getObject());
    }

    public EntityFishHookState w() {
        return new EntityFishHookState(MNetworkManager.T(NetworkManager.vapeInstance.getMappingsMapperCompat().Do, this.I));
    }

    public EntityFishHookState B() {
        return this.w();
    }

    public NetworkPacketHandle c() {
        return new NetworkPacketHandle(MNetworkManager.U(NetworkManager.vapeInstance.getMappingsMapperCompat().Do, this.I));
    }

    public NetworkManager(Object object) {
        super(object);
    }
}

