package gg.vape.event.impl;

import gg.vape.Vape;
import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.blatant.BlockIn;
import gg.vape.module.blatant.Scaffold;
import gg.vape.module.none.MouseDelayFix;
import gg.vape.module.utility.Clutch;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class EventEntityRendererRayTrace
extends Event {
    private static Clutch M;
    private static final EventListeners Y;
    private float G;
    private static BlockIn a;
    private Object g;
    private static MouseDelayFix p;
    private static Scaffold q;

    public EventEntityRendererRayTrace(Object object, float f) {
        this.g = object;
        this.G = f;
    }

    @Override
    public EventListeners getListeners() {
        return Y;
    }

    public static EventListeners getEventListeners() {
        return Y;
    }

    @Override
    public boolean fire() {
        if (p == null) {
            p = Vape.INSTANCE.getModManager().getMod(MouseDelayFix.class);
            q = Vape.INSTANCE.getModManager().getMod(Scaffold.class);
            M = Vape.INSTANCE.getModManager().getMod(Clutch.class);
            a = Vape.INSTANCE.getModManager().getMod(BlockIn.class);
        }
        if (!p.boolean_r() && !q.boolean_r()) {
            if (!a.boolean_r()) {
                if (!M.boolean_r()) {
                    return false;
                }
            }
        }
        return MappedClasses.z5.isInstance(this.g);
    }

    public Object getVec() {
        Object object = Vape.INSTANCE.getMappingsMapperCompat().Rr.jL.Y(this.g, Float.valueOf(this.G));
        return object;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    static {
        Y = new EventListeners();
    }
}

