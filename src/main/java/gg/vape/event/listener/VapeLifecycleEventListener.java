package gg.vape.event.listener;

import gg.vape.event.EventHandler;
import gg.vape.event.EventListener;
import gg.vape.event.impl.EventPacketReceive;
import gg.vape.mapping.MappedClasses;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.PlayerEventNameFormat;

public class VapeLifecycleEventListener
implements EventListener {
    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @EventHandler
    public void onPacketReceive(EventPacketReceive eventPacketReceive) {
        PlayerEventNameFormat playerEventNameFormat;
        String string;
        if (eventPacketReceive.getPacket().isInstance(MappedClasses.l3) && (string = (playerEventNameFormat = new PlayerEventNameFormat(eventPacketReceive.getPacket())).h()).contains("vapeclient")) {
            playerEventNameFormat.J(string.replace("vapeclient", "-"));
        }
    }
}

