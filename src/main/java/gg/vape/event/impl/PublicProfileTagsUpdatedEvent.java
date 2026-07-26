package gg.vape.event.impl;

import gg.vape.event.EventListeners;
import gg.vape.event.IEvent;
import java.util.Collection;
import org.jetbrains.annotations.UnmodifiableView;

public class PublicProfileTagsUpdatedEvent
implements IEvent {
    private final Collection<String> s;
    private static final EventListeners B = new EventListeners();

    @Override
    public EventListeners getListeners() {
        return B;
    }

    public static EventListeners getEventListeners() {
        return B;
    }

    public PublicProfileTagsUpdatedEvent(Collection<String> collection) {
        this.s = collection;
    }

    public @UnmodifiableView Collection<String> getTags() {
        return this.s;
    }
}

