package gg.vape.event;

import gg.vape.event.IEvent;

public class EventTimingRecord {
    private final int K;
    private final long t;
    private final Class<? extends IEvent> b;
    private final long R;

    public EventTimingRecord(Class<? extends IEvent> clazz, long l, long l2, int n) {
        this.b = clazz;
        this.t = l;
        this.R = l2;
        this.K = n;
    }

    public int getCount() {
        return this.K;
    }

    public long W() {
        return this.t;
    }

    public Class<? extends IEvent> getEventType() {
        return this.b;
    }

    public long m() {
        return this.R;
    }
}

