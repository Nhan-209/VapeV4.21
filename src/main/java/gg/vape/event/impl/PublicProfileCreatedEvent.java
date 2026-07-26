package gg.vape.event.impl;

import gg.vape.config.PublicProfile;
import gg.vape.event.EventListeners;
import gg.vape.event.IEvent;

public class PublicProfileCreatedEvent
implements IEvent {
    private final PublicProfile G;
    private static final EventListeners h = new EventListeners();

    public PublicProfile getProfile() {
        return this.G;
    }

    public PublicProfileCreatedEvent(PublicProfile nU) {
        this.G = nU;
    }

    public static EventListeners getEventListeners() {
        return h;
    }

    @Override
    public EventListeners getListeners() {
        return h;
    }
}

