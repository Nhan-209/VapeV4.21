package gg.vape.event.impl;

import gg.vape.event.impl.EventTickBase;

public class EventPreTick
extends EventTickBase {
    @Override
    public boolean fire() {
        S.runPending();
        return super.fire();
    }
}

