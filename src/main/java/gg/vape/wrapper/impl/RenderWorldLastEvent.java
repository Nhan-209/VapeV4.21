package gg.vape.wrapper.impl;

import gg.vape.Vape;
import gg.vape.mapping.mappings.MRenderWorldLastEvent;
import gg.vape.wrapper.Wrapper;

public class RenderWorldLastEvent
extends Wrapper {

    public static float getPartialTicks() {
        if (!Vape.renderReady) {
            return 1.0f;
        }
        return MRenderWorldLastEvent.o(RenderWorldLastEvent.vapeInstance.getMappingsMapperCompat().Cf);
    }

    public RenderWorldLastEvent(Object object) {
        super(object);
    }
}

