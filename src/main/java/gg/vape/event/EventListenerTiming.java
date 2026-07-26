package gg.vape.event;

import gg.vape.event.EventListenerRegistration;

public class EventListenerTiming {
    private final long S;
    private long W;
    private final EventListenerRegistration N;

    public EventListenerRegistration getRegistration() {
        return this.N;
    }

    public EventListenerTiming(EventListenerRegistration eventListenerRegistration) {
        this.N = eventListenerRegistration;
        this.S = System.nanoTime();
    }

    public void finish() {
        this.W = System.nanoTime() - this.S;
    }

    public long getDurationNanos() {
        return this.W;
    }
}

