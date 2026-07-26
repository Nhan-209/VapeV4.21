package gg.vape.event.impl;

import gg.vape.event.impl.EventPacketSend;
import gg.vape.module.world.fastuse.FastUsePacketDelaySubModule;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class DelayedPacketSendEntry {
    private boolean d;
    private final EventPacketSend P;
    final FastUsePacketDelaySubModule q;
    private static int f;

    public static int G() {
        int n = DelayedPacketSendEntry.I();
        if (n == 0) {
            return 111;
        }
        return 0;
    }

    public DelayedPacketSendEntry(FastUsePacketDelaySubModule n2, EventPacketSend eventPacketSend) {
        this.q = n2;
        this.P = eventPacketSend;
    }

    public static int I() {
        return f;
    }

    public static void A(int n) {
        f = n;
    }

    public void d(boolean bl) {
        this.d = bl;
    }

    public EventPacketSend V() {
        return this.P;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public boolean E() {
        return this.d;
    }

    public static EventPacketSend g(DelayedPacketSendEntry rk_02) {
        return rk_02.P;
    }

    static {
        if (DelayedPacketSendEntry.G() == 0) {
            DelayedPacketSendEntry.A(123);
        }
    }
}

