package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.wrapper.impl.Entity;

public class EventMotion
extends Event {
    private static Entity l;
    private static boolean B;
    public static Entity V;
    private static final EventListeners F;
    static boolean C;
    private static float N;
    private static float Z;

    public static float getRotationPitch() {
        return Z;
    }

    public static void setRotationYaw(float f) {
        N = f;
    }

    static boolean access$202(boolean bl) {
        B = bl;
        return B;
    }

    public static void setRotationPitch(float f) {
        Z = f;
    }

    static float access$002(float f) {
        N = f;
        return N;
    }

    @Override
    public EventListeners getListeners() {
        return F;
    }

    public static float getRotationYaw() {
        return N;
    }

    static float access$102(float f) {
        Z = f;
        return Z;
    }

    public static boolean shouldAlwaysSend() {
        return C;
    }

    public static boolean isOnGround() {
        return B;
    }

    static Entity access$302(Entity entity) {
        l = entity;
        return l;
    }

    public static void setOnGround(boolean bl) {
        B = bl;
    }

    EventMotion(Entity entity) {
        V = entity;
    }

    public static void setShouldAlwaysSend(boolean bl) {
        C = bl;
    }

    @Override
    public boolean fire() {
        return super.fire();
    }

    public static EventListeners getEventListeners() {
        return F;
    }

    static Entity access$300() {
        return l;
    }

    static {
        F = new EventListeners();
    }
}

