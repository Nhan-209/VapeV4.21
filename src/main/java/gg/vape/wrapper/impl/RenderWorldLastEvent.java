package gg.vape.wrapper.impl;

import gg.vape.Vape;
import gg.vape.mapping.mappings.MRenderWorldLastEvent;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;

public class RenderWorldLastEvent
extends Wrapper {
    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static float getPartialTicks() {
        if (!Vape.renderReady) {
            return 1.0f;
        }
        return MRenderWorldLastEvent.o(RenderWorldLastEvent.c.getMappingsMapperCompat().Cf);
    }

    public RenderWorldLastEvent(Object object) {
        super(object);
    }
}

