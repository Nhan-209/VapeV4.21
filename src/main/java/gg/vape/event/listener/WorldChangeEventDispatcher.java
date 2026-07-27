package gg.vape.event.listener;

import gg.vape.event.EventHandler;
import gg.vape.event.EventListener;
import gg.vape.event.EventPriority;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventWorldChange;
import gg.vape.wrapper.impl.WorldClient;

public class WorldChangeEventDispatcher
implements EventListener {
    private WorldClient f;


    @EventHandler(A=EventPriority.LOW)
    public void onTick(EventPreTick eventTick) {
        WorldClient worldClient = eventTick.getWorld();
        boolean bl = worldClient.isNull();
        if (this.f == null && !bl) {
            new EventWorldChange(this.f, worldClient).fire();
            this.f = worldClient;
        } else if (this.f != null && !bl && this.f.getObject() != worldClient.getObject()) {
            new EventWorldChange(this.f, worldClient).fire();
            this.f = worldClient;
        } else if (this.f != null && bl) {
            new EventWorldChange(this.f, null).fire();
            this.f = null;
        }
    }
}

