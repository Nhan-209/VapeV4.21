package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.mapping.MappedClasses;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.NetHandlerPlayClientImpl;
import gg.vape.wrapper.impl.NetworkManager;
import gg.vape.wrapper.impl.Packet;
import org.jetbrains.annotations.Nullable;

public class EventPacketReceive
extends Event {
    @Nullable
    private NetworkManager c;
    private final Object e;
    @Nullable
    private Packet w;
    private final Object v;
    private static final EventListeners x = new EventListeners();

    @Override
    public EventListeners getListeners() {
        return x;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public Packet getPacket() {
        if (this.w == null) {
            this.w = new Packet(this.e);
        }
        return this.w;
    }

    public static EventListeners getEventListeners() {
        return x;
    }

    @Override
    public boolean fire() {
        if (!this.getNetworkManager().c().isInstance(MappedClasses.F1)) {
            return false;
        }
        return super.fire();
    }

    public Object getPacketInstance() {
        return this.e;
    }

    public NetworkManager getNetworkManager() {
        if (this.c == null) {
            this.c = new NetworkManager(this.v);
        }
        return this.c;
    }

    public NetHandlerPlayClientImpl getNetHandler() {
        return new NetHandlerPlayClientImpl(this.getNetworkManager().c());
    }

    public void setPacket(Packet packet) {
        this.w = packet;
    }

    public EventPacketReceive(Object object, Object object2) {
        this.v = object;
        this.e = object2;
    }
}

