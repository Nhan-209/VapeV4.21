package gg.vape.utils;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.TimerUtil;

public class NanoTimerUtil
extends TimerUtil {
    public double getElapsedMilliseconds() {
        return (double)this.getLastMS() / 1000000.0;
    }

    @Override
    public void reset() {
        this.Q = System.nanoTime();
    }

    @Override
    public long getLastMS() {
        return System.nanoTime() - this.Q;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    @Override
    public boolean hasTimeElapsed(long l) {
        boolean bl = this.getLastMS() >= l;
        return bl;
    }
}

