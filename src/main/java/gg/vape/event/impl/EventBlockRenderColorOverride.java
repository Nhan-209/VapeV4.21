package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.wrapper.impl.ForgeVersion;
import java.nio.FloatBuffer;

public class EventBlockRenderColorOverride
extends Event {
    private static boolean e;
    private static final EventListeners T;
    private static float f;
    private static float w;
    private static float m;
    private static float L;

    public static void flip(FloatBuffer floatBuffer) {
        if (!e) {
            return;
        }
        floatBuffer.position(0);
        floatBuffer.put(w);
        floatBuffer.put(m);
        floatBuffer.put(L);
        floatBuffer.put(f);
        e = false;
    }

    public static EventListeners getEventListeners() {
        return T;
    }

    public EventBlockRenderColorOverride() {
        w = 1.0f;
        m = 0.0f;
        L = 0.0f;
        f = ForgeVersion.MC_1_7_10.L() ? 0.4f : 0.3f;
    }

    @Override
    public boolean fire() {
        return super.fire();
    }

    @Override
    public EventListeners getListeners() {
        return T;
    }


    public static void setColor(float f, float f2, float f3, float f4) {
        w = f;
        m = f2;
        L = f3;
        EventBlockRenderColorOverride.f = f4;
        e = true;
    }

    static {
        T = new EventListeners();
    }
}

