package gg.vape.event;

import gg.vape.event.EventBus;
import gg.vape.event.IEvent;
import gg.vape.runtime.ObfuscatedRuntimeException;

public interface ICancelableEvent
extends IEvent {
    public boolean isCanceled();

    @Override
    default public boolean fire() {
        ICancelableEvent iCancelableEvent = EventBus.getInstance().post(this);
        return !iCancelableEvent.isCanceled();
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void setCancelled(boolean var1);
}

