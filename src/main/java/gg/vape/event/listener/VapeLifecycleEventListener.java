package gg.vape.event.listener;

import gg.vape.event.EventHandler;
import gg.vape.event.EventListener;
import gg.vape.event.impl.EventPacketReceive;
import gg.vape.mapping.MappedClasses;
import gg.vape.wrapper.impl.PlayerEventNameFormat;

public class VapeLifecycleEventListener
implements EventListener {

    @EventHandler
    public void onPacketReceive(EventPacketReceive eventPacketReceive) {
        PlayerEventNameFormat playerEventNameFormat;
        String formattedName;
        if (eventPacketReceive.getPacket().isInstance(MappedClasses.l3) && (formattedName = (playerEventNameFormat = new PlayerEventNameFormat(eventPacketReceive.getPacket())).h()).contains("vapeclient")) {
            playerEventNameFormat.J(formattedName.replace("vapeclient", "-"));
        }
    }
}

