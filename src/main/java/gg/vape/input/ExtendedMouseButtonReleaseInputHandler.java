package gg.vape.input;

import gg.vape.input.InputEventDispatcher;
import gg.vape.input.InputEventHandler;
import gg.vape.input.KeyboardCodeUtil;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class ExtendedMouseButtonReleaseInputHandler
implements InputEventHandler {
    private static boolean L;

    public static void U(boolean bl) {
        L = bl;
    }

    @Override
    public boolean handle(long l, long l2) {
        return InputEventDispatcher.getInstance().getMouseState().setButtonState(KeyboardCodeUtil.s(l), false);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public static boolean I() {
        boolean bl = ExtendedMouseButtonReleaseInputHandler.L();
        return false;
    }

    public static boolean L() {
        return L;
    }

    static {
        if (!ExtendedMouseButtonReleaseInputHandler.L()) {
            ExtendedMouseButtonReleaseInputHandler.U(true);
        }
    }
}

