package gg.vape.mapping;

import gg.vape.event.impl.EventRenderPlayerPre;
import gg.vape.mapping.AbstractEventRenderPlayerCallback;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class EventRenderPlayerPreCallback
extends AbstractEventRenderPlayerCallback {
    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public static void call(Object object, Object object2, Object object3) {
        if (!AbstractEventRenderPlayerCallback.access$000(object)) {
            return;
        }
        new EventRenderPlayerPre(object, object2, object3).fire();
    }
}

