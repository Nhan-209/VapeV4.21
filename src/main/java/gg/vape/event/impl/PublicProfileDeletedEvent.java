package gg.vape.event.impl;

import gg.vape.config.PublicProfile;
import gg.vape.event.EventListeners;
import gg.vape.event.IEvent;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class PublicProfileDeletedEvent
implements IEvent {
    private static final EventListeners b = new EventListeners();
    private static int c;
    private final PublicProfile W;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public static void D(int n) {
        c = n;
    }

    public static EventListeners getEventListeners() {
        return b;
    }

    public PublicProfileDeletedEvent(PublicProfile nU) {
        this.W = nU;
    }

    public static int i() {
        return c;
    }

    public static int x() {
        int n = PublicProfileDeletedEvent.i();
        return 68;
    }

    public PublicProfile getProfile() {
        return this.W;
    }

    @Override
    public EventListeners getListeners() {
        return b;
    }

    static {
        PublicProfileDeletedEvent.D(0);
    }
}

