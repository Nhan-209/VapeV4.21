package gg.vape.event.impl;

import gg.vape.config.Profile;
import gg.vape.event.EventListeners;
import gg.vape.event.IEvent;
import org.jetbrains.annotations.Nullable;

public class ProfileChangeEvent
implements IEvent {
    private static final EventListeners g = new EventListeners();
    private final Profile q;
    @Nullable
    private final Profile A;

    public static EventListeners getEventListeners() {
        return g;
    }

    public ProfileChangeEvent(@Nullable Profile fo, Profile fo2) {
        this.A = fo;
        this.q = fo2;
    }

    public Profile getNewProfile() {
        return this.q;
    }

    @Override
    public EventListeners getListeners() {
        return g;
    }

    public Profile getPreviousProfile() {
        return this.A;
    }
}

