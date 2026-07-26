package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class AtomicReferenceArrayBridge
extends Wrapper {
    public AtomicReferenceArray b() {
        return (AtomicReferenceArray)AtomicReferenceArrayBridge.c.getMappingsMapperCompat().H.G(this.I);
    }

    public AtomicReferenceArrayBridge(Object object) {
        super(object);
    }
}

