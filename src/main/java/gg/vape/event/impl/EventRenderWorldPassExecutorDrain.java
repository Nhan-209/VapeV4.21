package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.util.ThreadBoundExecutor;
import gg.vape.wrapper.impl.DeltaTracker;

public class EventRenderWorldPassExecutorDrain
extends Event {
    private static final EventListeners s;
    private final float e;
    public static final ThreadBoundExecutor E;
    private static String[] W;

    public EventRenderWorldPassExecutorDrain(float f) {
        this.e = f;
    }

    public static String[] I() {
        return W;
    }

    @Override
    public boolean fire() {
        try {
            E.runPending();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return false;
    }

    public static void n(String[] stringArray) {
        W = stringArray;
    }

    @Override
    public EventListeners getListeners() {
        return s;
    }

    public EventRenderWorldPassExecutorDrain(Object object) {
        DeltaTracker p__02 = new DeltaTracker(object);
        this.e = p__02.r();
    }

    public static EventListeners getEventListeners() {
        return s;
    }

    static {
        E = new ThreadBoundExecutor();
        s = new EventListeners();
        EventRenderWorldPassExecutorDrain.n(null);
    }
}

