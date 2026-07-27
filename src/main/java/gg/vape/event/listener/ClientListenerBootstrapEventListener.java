package gg.vape.event.listener;

import gg.vape.event.EventHandler;
import gg.vape.event.EventListener;
import gg.vape.event.EventPriority;
import gg.vape.event.impl.EventLivingUpdate;
import gg.vape.event.impl.EventPacketReceive;
import gg.vape.mapping.MappedClasses;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.SPacketEntityStatus;
import gg.vape.wrapper.impl.WorldClient;

public class ClientListenerBootstrapEventListener
implements EventListener {
    private static GuiComponent[] E;


    @EventHandler(A=EventPriority.LOW)
    public void onPacketReceive(EventPacketReceive eventPacketReceive) {
        if (!eventPacketReceive.getPacket().isInstance(MappedClasses.lU)) {
            return;
        }
        SPacketEntityStatus sPacketEntityStatus = new SPacketEntityStatus(eventPacketReceive.getPacket().getObject());
        WorldClient worldClient = Minecraft.theWorld();
        if (worldClient.isNull()) {
            return;
        }
        Entity entity = worldClient.V(sPacketEntityStatus.X());
        if (entity.isNull()) {
            return;
        }
        new EventLivingUpdate(entity).fire();
    }

    static {
        if (ClientListenerBootstrapEventListener.g() != null) {
            ClientListenerBootstrapEventListener.U(new GuiComponent[1]);
        }
    }

    public static GuiComponent[] g() {
        return E;
    }

    public static void U(GuiComponent[] guiComponentArray) {
        E = guiComponentArray;
    }
}

