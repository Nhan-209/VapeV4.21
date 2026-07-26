package gg.vape.event;

import gg.vape.event.EventHandlerInvoker;
import gg.vape.event.EventListenerRegistration;
import gg.vape.event.EventPriority;
import gg.vape.event.IEvent;

public class EventHandlerEntry {
    private final EventPriority D;
    private final EventListenerRegistration S;
    private final boolean f;
    private final EventHandlerInvoker n;

    public EventHandlerInvoker K() {
        return this.n;
    }

    public EventListenerRegistration p() {
        return this.S;
    }

    public boolean y() {
        return this.f;
    }

    public EventPriority F() {
        return this.D;
    }

    public EventHandlerEntry(EventListenerRegistration eventListenerRegistration, EventPriority eventPriority, boolean bl, EventHandlerInvoker eventHandlerInvoker) {
        this.S = eventListenerRegistration;
        this.D = eventPriority;
        this.f = bl;
        this.n = eventHandlerInvoker;
    }

    public <T extends IEvent> T X(T t) {
        this.n.B(t);
        return t;
    }
}

