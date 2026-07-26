package gg.vape.input;

import gg.vape.input.InputEventDispatcher;
import gg.vape.input.MouseInputState;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.Minecraft;

public class MouseInput {
    private static MouseInputState R;

    public static int N() {
        return MouseInput.O().getMouseX();
    }

    public static boolean I(int n) {
        return MouseInput.O().isButtonDown(n);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static int u() {
        return Minecraft.h() - MouseInput.O().getMouseY();
    }

    public static MouseInputState O() {
        if (R == null) {
            R = InputEventDispatcher.getInstance().getMouseState();
        }
        return R;
    }

    public static int m() {
        return MouseInput.O().getScrollDelta();
    }

    public static boolean t() {
        return MouseInput.O().Q();
    }

    public static long long_l() {
        return MouseInput.O().getLastChangeTime();
    }

    public static boolean E() {
        return MouseInput.O().isLastButtonDown();
    }

    public static int int_l() {
        return MouseInput.O().getLastButton();
    }

    public static /* synthetic */ int l() {
        return MouseInput.int_l();
    }

    public static /* synthetic */ long l$src$J$dk87ei() {
        return MouseInput.long_l();
    }
}

