package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.wrapper.impl.ITextComponent;

public class EventChat
extends Event {
    private ITextComponent P;
    private static final EventListeners V = new EventListeners();

    @Override
    public boolean fire() {
        return super.fire();
    }

    public void setMessage(ITextComponent iTextComponent) {
        this.P = iTextComponent;
    }

    public static EventListeners getEventListeners() {
        return V;
    }

    public ITextComponent getMessage() {
        return this.P;
    }

    public EventChat(ITextComponent iTextComponent) {
        this.P = iTextComponent;
    }

    @Override
    public EventListeners getListeners() {
        return V;
    }
}

