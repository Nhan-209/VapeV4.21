package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.network.PacketDispatchGuard;
import gg.vape.utils.network.PacketDispatchMarkerRegistry;
import gg.vape.wrapper.impl.NetworkManager;
import gg.vape.wrapper.impl.Packet;

public class EventPacketSend
extends Event {
    private Packet q;
    private NetworkManager l;
    private boolean e = false;
    private static final EventListeners F = new EventListeners();

    public EventPacketSend(Object object, Object object2) {
        this.l = new NetworkManager(object);
        this.q = new Packet(object2);
    }

    public Object getPacketInstance() {
        return this.q.getObject();
    }

    public NetworkManager getNetworkManager() {
        return this.l;
    }

    public boolean wasModified() {
        return this.e;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public boolean fire() {
        if (PacketDispatchGuard.b.o(this.q)) {
            PacketDispatchGuard.b.onPacketSend(this);
            return this.isCanceled();
        }
        if (PacketDispatchMarkerRegistry.J(this.q)) {
            PacketDispatchMarkerRegistry.p(this.q);
            return this.isCanceled();
        }
        return super.fire();
    }

    public void forceCancel() {
        PacketDispatchMarkerRegistry.q(this.q);
        this.setCancelled(true);
    }

    @Override
    public EventListeners getListeners() {
        return F;
    }

    public void setPacket(Packet packet) {
        this.q = packet;
        this.e = true;
    }

    public static EventListeners getEventListeners() {
        return F;
    }

    public Packet getPacket() {
        return this.q;
    }

    @Override
    public void setCancelled(boolean bl) {
        super.setCancelled(bl);
    }
}

