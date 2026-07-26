package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.SoundEvent;

@Deprecated
public class EventPlaySoundAtEntity
extends Event {
    private final Entity C;
    private static final EventListeners n = new EventListeners();
    private final String g;

    @Override
    public EventListeners getListeners() {
        return n;
    }

    @Override
    public boolean fire() {
        return super.fire();
    }

    public Entity getEntity() {
        return this.C;
    }

    public String getName() {
        return this.g;
    }

    public EventPlaySoundAtEntity(Entity entity, Object object) {
        this.C = entity;
        if (ForgeVersion.MC_1_16_5.d()) {
            SoundEvent soundEvent = new SoundEvent(object);
            this.g = soundEvent.V().getResourcePath();
        } else {
            this.g = (String)object;
        }
    }

    public static EventListeners getEventListeners() {
        return n;
    }
}

