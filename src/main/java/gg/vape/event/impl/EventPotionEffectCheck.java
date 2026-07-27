package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.Potion;

public class EventPotionEffectCheck
extends Event {
    private final Object f;
    private final Object S;
    private Potion v;
    private static final EventListeners g = new EventListeners();
    private boolean Y;
    private Entity j;

    public EventPotionEffectCheck(Object object, Object object2) {
        this.S = object;
        this.f = object2;
    }

    public static EventListeners getEventListeners() {
        return g;
    }


    @Override
    public EventListeners getListeners() {
        return g;
    }

    @Override
    public boolean fire() {
        return super.fire();
    }

    public boolean isActive() {
        return this.Y;
    }

    public Potion getPotion() {
        if (this.v == null) {
            this.v = new Potion(this.f);
        }
        return this.v;
    }

    public void setActive(boolean bl) {
        this.Y = bl;
    }

    public Entity getEntity() {
        if (this.j == null) {
            this.j = new Entity(this.S);
        }
        return this.j;
    }
}

