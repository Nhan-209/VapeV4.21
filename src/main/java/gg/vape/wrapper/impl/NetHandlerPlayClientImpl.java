package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MNetHandlerPlayClientImpl;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.NetHandlerPlayClient;
import gg.vape.wrapper.impl.NetworkManager;
import gg.vape.wrapper.impl.Packet;
import java.util.Collection;

public class NetHandlerPlayClientImpl
extends NetHandlerPlayClient {
    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public Collection getPlayerInfoMap() {
        return MNetHandlerPlayClientImpl.H(NetHandlerPlayClientImpl.c.getMappings().hB, this.I);
    }

    public boolean M() {
        return MNetHandlerPlayClientImpl.d(NetHandlerPlayClientImpl.c.getMappings().hB, this.I);
    }

    public void addToSendQueue(Packet packet) {
        MNetHandlerPlayClientImpl.I(NetHandlerPlayClientImpl.c.getMappings().hB, this.I, packet.getObject());
    }

    public boolean d() {
        if (ForgeVersion.MC_1_20_6.d()) {
            return true;
        }
        return MNetHandlerPlayClientImpl.X(NetHandlerPlayClientImpl.c.getMappings().hB, this.I);
    }

    public NetHandlerPlayClientImpl(Object object) {
        super(object);
    }

    public NetworkManager a() {
        return new NetworkManager(MNetHandlerPlayClientImpl.z(NetHandlerPlayClientImpl.c.getMappings().hB, this.I));
    }
}

