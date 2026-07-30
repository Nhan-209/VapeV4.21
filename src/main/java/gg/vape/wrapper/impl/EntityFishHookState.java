package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MEntityFishHook;
import gg.vape.wrapper.Wrapper;

public class EntityFishHookState
extends Wrapper {
    public boolean z() {
        return MEntityFishHook.isInGround(EntityFishHookState.vapeInstance.getMappingsMapperCompat().CX, this.I);
    }

    public CaughtEntity D() {
        return new CaughtEntity(MEntityFishHook.getCaughtEntity(EntityFishHookState.vapeInstance.getMappingsMapperCompat().CX, this.I));
    }

    public EntityFishHookState(Object object) {
        super(object);
    }
}

