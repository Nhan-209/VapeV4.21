package gg.vape.mapping;

import gg.vape.event.impl.EventThreadBoundPostTick;
import gg.vape.event.impl.EventTickBase;
import gg.vape.mapping.ThreadBoundTickCallbackBase;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class ThreadBoundEventPostTickCallback
extends ThreadBoundTickCallbackBase {
    public static void call() {
        if (Thread.currentThread().equals(EventTickBase.S.getOwnerThread())) {
            new EventThreadBoundPostTick().fire();
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

