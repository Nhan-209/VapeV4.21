package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.wrapper.impl.WorldInfo;

public class EventWorldTime
extends Event {
    private long b = 0L;
    private Object K;
    private static final EventListeners v = new EventListeners();

    public void setWorldTime(long l) {
        this.b = l;
        this.setCancelled(true);
    }

    public EventWorldTime(Object object) {
        this.K = object;
    }

    @Override
    public boolean fire() {
        return super.fire();
    }

    public WorldInfo getInstance() {
        return new WorldInfo(this.K);
    }

    @Override
    public EventListeners getListeners() {
        return v;
    }

    public long getWorldTime() {
        return this.b;
    }

    public static EventListeners getEventListeners() {
        return v;
    }
}

