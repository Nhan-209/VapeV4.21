package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class ForgeEvent
extends Wrapper {
    public void setCancelled(boolean bl) {
        ForgeEvent.c.getMappingsMapperCompat().qv.setCanceled(this.getObject(), bl);
    }

    public ForgeEvent(Object object) {
        super(object);
    }
}

