package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.wrapper.impl.WorldClient;
import org.jetbrains.annotations.Nullable;

public class EventWorldChange
extends Event {
    private static final EventListeners O = new EventListeners();
    @Nullable
    private final WorldClient B;
    @Nullable
    private final WorldClient y;

    @Nullable
    public WorldClient getNewWorld() {
        return this.B;
    }

    @Nullable
    public WorldClient getPreviousWorld() {
        return this.y;
    }

    public static EventListeners getEventListeners() {
        return O;
    }

    @Override
    public EventListeners getListeners() {
        return O;
    }

    public EventWorldChange(@Nullable WorldClient worldClient, @Nullable WorldClient worldClient2) {
        this.y = worldClient;
        this.B = worldClient2;
    }
}

