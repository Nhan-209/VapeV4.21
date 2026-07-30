package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MCaughtEntityActionBridge;
import gg.vape.wrapper.Wrapper;

public class CaughtEntity
extends Wrapper {
    public CaughtEntity(Object object) {
        super(object);
    }

    public boolean G() {
        return MCaughtEntityActionBridge.g(CaughtEntity.vapeInstance.getMappingsMapperCompat().hC, this.I);
    }

    public void F(Runnable runnable) {
        MCaughtEntityActionBridge.H(CaughtEntity.vapeInstance.getMappingsMapperCompat().hC, this.I, runnable);
    }
}

