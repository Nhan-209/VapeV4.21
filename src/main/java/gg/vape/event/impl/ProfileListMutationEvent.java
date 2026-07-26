package gg.vape.event.impl;

import gg.vape.config.Profile;
import gg.vape.event.EventListeners;
import gg.vape.event.IEvent;
import gg.vape.event.impl.ProfileListMutationAction;

public class ProfileListMutationEvent
implements IEvent {
    private static final EventListeners Y = new EventListeners();
    private final ProfileListMutationAction B;
    private final Profile y;

    public ProfileListMutationEvent(Profile fo, ProfileListMutationAction _q_02) {
        this.y = fo;
        this.B = _q_02;
    }

    @Override
    public EventListeners getListeners() {
        return Y;
    }

    public static EventListeners getEventListeners() {
        return Y;
    }

    public Profile getProfile() {
        return this.y;
    }

    public ProfileListMutationAction getAction() {
        return this.B;
    }
}

