package gg.vape.input;

import gg.vape.input.InputEventDispatcher;
import gg.vape.input.KeyboardInputState;
import gg.vape.wrapper.impl.ForgeVersion;
import org.lwjgl.input.Keyboard;

public class KeyboardInput {
    private static KeyboardInputState t;

    public static boolean isKeyDown(int n) {
        return KeyboardInput.getState().isKeyDown(n);
    }

    public static String getKeyName(int n) {
        if (n < 0) {
            int n2 = n + 100;
            return "M" + n2;
        }
        if (ForgeVersion.MC_1_16_5.d()) {
            return String.valueOf((char)n);
        }
        return Keyboard.getKeyName((int)n);
    }


    public static KeyboardInputState getState() {
        if (t == null) {
            t = InputEventDispatcher.getInstance().getKeyboardState();
        }
        return t;
    }
}

