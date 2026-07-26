package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ResourceLocation;

public class SoundEvent
extends Wrapper {
    public SoundEvent(Object object) {
        super(object);
    }

    public ResourceLocation V() {
        return new ResourceLocation(SoundEvent.c.getMappingsMapperCompat().Cb.q(this.I));
    }
}

