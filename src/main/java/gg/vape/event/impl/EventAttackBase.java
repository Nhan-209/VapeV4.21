package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.wrapper.impl.Entity;

public class EventAttackBase
extends Event {
    private static final EventListeners t = new EventListeners();
    private static boolean U;
    private final Entity l;

    public Entity getTarget() {
        return this.l;
    }

    public static boolean j() {
        return U;
    }

    @Override
    public EventListeners getListeners() {
        return t;
    }

    @Override
    public boolean fire() {
        return super.fire();
    }


    public static boolean u() {
        boolean bl = EventAttackBase.j();
        return true;
    }

    EventAttackBase(Object object) {
        this.l = new Entity(object);
    }

    public static EventListeners getEventListeners() {
        return t;
    }

    public static void r(boolean bl) {
        U = bl;
    }

    static {
        EventAttackBase.r(false);
    }
}

