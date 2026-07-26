package gg.vape.event.listener;

import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.EventListener;
import gg.vape.event.EventPriority;
import gg.vape.event.impl.EventPacketSend;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.Packet;
import gg.vape.wrapper.impl.UseEntityPacketBridge;

public class VapeShutdownEventListener
implements EventListener {
    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @EventHandler(A=EventPriority.LOWEST)
    public void onPacketSend(EventPacketSend eventPacketSend) {
        UseEntityPacketBridge useEntityPacketBridge;
        int n;
        Packet packet = eventPacketSend.getPacket();
        if (UseEntityPacketBridge.h(packet) && ClientSettings.B(n = (useEntityPacketBridge = new UseEntityPacketBridge(packet.getObject())).w())) {
            eventPacketSend.setCancelled(true);
        }
    }
}

