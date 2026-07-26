package gg.vape.event;

import gg.vape.event.EventListenerTiming;
import gg.vape.event.IEvent;
import java.util.ArrayList;
import java.util.List;

public class EventDispatchTrace {
    private final List<EventListenerTiming> t = new ArrayList<EventListenerTiming>();
    private final Class<? extends IEvent> o;
    private final long y;
    private long U;

    public EventDispatchTrace(Class<? extends IEvent> clazz) {
        this.o = clazz;
        this.y = System.nanoTime();
    }

    public long getDurationNanos() {
        return this.U - this.y;
    }

    public List<EventListenerTiming> getListenerTimings() {
        return this.t;
    }

    public long getStartNanos() {
        return this.y;
    }

    public void finish() {
        this.U = System.nanoTime();
    }

    public void addListenerTiming(EventListenerTiming eventListenerTiming) {
        this.t.add(eventListenerTiming);
    }

    public Class<? extends IEvent> getEventType() {
        return this.o;
    }
}

