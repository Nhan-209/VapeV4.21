package gg.vape.event.impl;

import gg.vape.event.impl.EventTickBase;

public class EventPostTick
extends EventTickBase {
    @Override
    public boolean fire() {
        p.runPending();
        return super.fire();
    }
}

