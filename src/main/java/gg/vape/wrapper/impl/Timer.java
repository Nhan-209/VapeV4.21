package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MTimer;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class Timer
extends Wrapper {
    public float getTimerSpeed() {
        if (ForgeVersion.MC_1_12_2.d()) {
            float f = 50.0f;
            return f / Timer.c.getMappings().hr.Z(this.I);
        }
        return MTimer.S(Timer.c.getMappings().hr, this.I);
    }

    public void setTimerSpeed(float f) {
        if (ForgeVersion.MC_1_12_2.d()) {
            float f2 = 50.0f;
            MTimer.k(Timer.c.getMappings().hr, this.I, f2 / f);
            return;
        }
        MTimer.y(Timer.c.getMappings().hr, this.I, f);
    }

    public Timer(Object object) {
        super(object);
    }

    public float renderPartialTicks() {
        return MTimer.l(Timer.c.getMappings().hr, this.I);
    }

    public float getElapsedPartialTicks() {
        return MTimer.H(Timer.c.getMappings().hr, this.I);
    }
}

