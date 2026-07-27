package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.mapping.MappedClasses;
import gg.vape.wrapper.impl.EntityLivingBase;
import org.jetbrains.annotations.Nullable;

public class EventLivingTravelBase
extends Event {
    private final Object K;
    @Nullable
    private EntityLivingBase Q;
    private static final EventListeners r = new EventListeners();

    @Override
    public boolean fire() {
        if (!MappedClasses.z5.isInstance(this.K)) {
            return false;
        }
        return super.fire();
    }

    public EntityLivingBase getEntity() {
        if (this.Q == null) {
            this.Q = new EntityLivingBase(this.K);
        }
        return this.Q;
    }


    public static EventListeners getEventListeners() {
        return r;
    }

    @Override
    public EventListeners getListeners() {
        return r;
    }

    public EventLivingTravelBase(Object object) {
        this.K = object;
    }
}

