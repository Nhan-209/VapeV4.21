package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import java.util.ArrayList;
import java.util.Collection;

public class EventScoreboardScores
extends Event {
    private static final EventListeners f = new EventListeners();
    private static boolean G;

    @Override
    public boolean fire() {
        return G;
    }

    public static EventListeners getEventListeners() {
        return f;
    }

    public Collection getScores() {
        return new ArrayList();
    }

    @Override
    public EventListeners getListeners() {
        return f;
    }

    public static void setLocked(boolean bl) {
        G = bl;
    }
}

