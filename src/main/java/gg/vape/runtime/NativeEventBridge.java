package gg.vape.runtime;

import gg.vape.Vape;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class NativeEventBridge {
    public static void reg(Class var0, int var1) {
    }

    public static void call(Object var0) {
        if (Vape.INSTANCE == null) {
            return;
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException var0) {
        return var0;
    }
}

