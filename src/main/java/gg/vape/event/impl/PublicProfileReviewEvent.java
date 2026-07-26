package gg.vape.event.impl;

import gg.vape.config.PublicProfileReview;
import gg.vape.event.Event;
import gg.vape.event.EventListeners;

public class PublicProfileReviewEvent
extends Event {
    private static final EventListeners t = new EventListeners();
    private final PublicProfileReview h;

    public static EventListeners getEventListeners() {
        return t;
    }

    @Override
    public EventListeners getListeners() {
        return t;
    }

    public PublicProfileReviewEvent(PublicProfileReview publicProfileReview) {
        this.h = publicProfileReview;
    }

    public PublicProfileReview getReview() {
        return this.h;
    }
}

