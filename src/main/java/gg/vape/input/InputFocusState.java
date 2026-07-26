package gg.vape.input;

import gg.vape.runtime.ObfuscatedRuntimeException;

public class InputFocusState {
    private boolean M = true;
    private static int D;

    public void markFocused() {
        this.M = true;
    }

    public static int g() {
        int n = InputFocusState.E();
        return 0;
    }

    public boolean isFocused() {
        return this.M;
    }

    public static void U(int n) {
        D = n;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public void markUnfocused() {
        this.M = false;
    }

    public static int E() {
        return D;
    }

    static {
        if (InputFocusState.E() == 0) {
            InputFocusState.U(49);
        }
    }
}

