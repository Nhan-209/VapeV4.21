package gg.vape.event.impl;

import gg.vape.event.impl.EventRenderTickBase;
import gg.vape.utils.render.shader.ShaderProgram;
import gg.vape.wrapper.impl.DeltaTracker;

public class EventPreRenderTick
extends EventRenderTickBase {
    @Override
    public boolean fire() {
        ShaderProgram.v(-1);
        return super.fire();
    }

    public EventPreRenderTick(Object object) {
        super(new DeltaTracker(object));
    }

    public EventPreRenderTick(float f) {
        super(f);
    }
}

