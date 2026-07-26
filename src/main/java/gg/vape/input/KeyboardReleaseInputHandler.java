package gg.vape.input;

import gg.vape.input.InputEventDispatcher;
import gg.vape.input.InputEventHandler;
import gg.vape.input.KeyboardCodeUtil;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class KeyboardReleaseInputHandler
implements InputEventHandler {
    private static boolean t;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public static void w(boolean bl) {
        t = bl;
    }

    @Override
    public boolean handle(long l, long l2) {
        int n = KeyboardCodeUtil.M((int)l, (int)l2);
        InputEventDispatcher.getInstance().getKeyboardState().setKeyState(n, false);
        return false;
    }

    public static boolean U() {
        boolean bl = KeyboardReleaseInputHandler.H();
        return !bl;
    }

    public static boolean H() {
        return t;
    }

    static {
        if (!KeyboardReleaseInputHandler.U()) {
            KeyboardReleaseInputHandler.w(true);
        }
    }
}

