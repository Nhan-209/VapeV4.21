package gg.vape.wrapper.impl;

import gg.vape.Vape;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import gg.vape.wrapper.Wrapper;
import java.lang.invoke.MethodHandles;

public class GlStateManagerTexGenState
extends Wrapper {
    public static final int F;

    public static boolean p() {
        return Vape.INSTANCE.getMappings().hs != null;
    }

    static {
        long l = ZkmLongKeyState.a(4606676221067559661L, 1825023284692129833L, MethodHandles.lookup().lookupClass()).a(11061185084063L) ^ 0x1CA4340C33CL;
        long l2 = -7022238366481794461L;
        F = (int)l2;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static void I(int n, int n2) {
        if (Vape.INSTANCE.getMappings().hs != null) {
            Vape.INSTANCE.getMappings().hs.K(n, n2);
        }
    }

    public GlStateManagerTexGenState(Object object) {
        super(object);
    }

    public static int J(int n) {
        if (Vape.INSTANCE.getMappings().hs != null) {
            return Vape.INSTANCE.getMappings().hs.F(n);
        }
        return 0;
    }
}

