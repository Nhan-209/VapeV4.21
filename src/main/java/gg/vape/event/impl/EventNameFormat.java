package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.ITextComponent;

public class EventNameFormat
extends Event {
    private static final EventListeners Y = new EventListeners();
    private ITextComponent c;
    private final EntityPlayer F;

    public EntityPlayer getPlayer() {
        return this.F;
    }

    public EventNameFormat(EntityPlayer entityPlayer, ITextComponent iTextComponent) {
        this.F = entityPlayer;
        this.c = iTextComponent;
    }

    public static EventListeners getEventListeners() {
        return Y;
    }

    public ITextComponent getDisplayName() {
        return this.c;
    }

    @Override
    public boolean fire() {
        return super.fire();
    }

    public void setDisplayName(ITextComponent iTextComponent) {
        this.c = iTextComponent;
    }

    @Override
    public EventListeners getListeners() {
        return Y;
    }
}

