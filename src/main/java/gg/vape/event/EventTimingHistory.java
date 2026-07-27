package gg.vape.event;

import gg.vape.event.EventDispatchTrace;
import gg.vape.utils.TimerUtil;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventTimingHistory {
    private final ConcurrentHashMap<EventDispatchTrace, Long> O = new ConcurrentHashMap();
    private TimerUtil v = new TimerUtil();

    private static boolean lambda$cleanupOldEvents$0(long l, Map.Entry entry) {
        boolean bl = l - ((EventDispatchTrace)entry.getKey()).getStartNanos() > 10000000000L;
        return bl;
    }

    public void addTrace(EventDispatchTrace eventDispatchTrace) {
        this.O.put(eventDispatchTrace, eventDispatchTrace.getDurationNanos());
        if (this.v.hasTimeElapsed(1000L)) {
            this.v.reset();
            this.cleanupExpiredTraces();
        }
    }

    public ConcurrentHashMap<EventDispatchTrace, Long> getTraces() {
        return this.O;
    }

    private void cleanupExpiredTraces() {
        long l = System.nanoTime();
        this.O.entrySet().removeIf(arg_0 -> EventTimingHistory.lambda$cleanupOldEvents$0(l, arg_0));
    }

}

