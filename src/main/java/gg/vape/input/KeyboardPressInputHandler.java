package gg.vape.input;

import gg.vape.input.InputEventDispatcher;
import gg.vape.input.InputEventHandler;
import gg.vape.input.KeyboardCodeUtil;
import gg.vape.input.KeyboardInputState;
import gg.vape.runtime.NativeBridge;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiKeyTypedDispatcher;
import java.util.HashSet;
import java.util.Set;

public class KeyboardPressInputHandler
implements InputEventHandler {
    private final Set<Integer> s = new HashSet<Integer>();

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public KeyboardPressInputHandler() {
        this.s.add(37);
        this.s.add(39);
        this.s.add(36);
        this.s.add(35);
    }

    @Override
    public boolean handle(long l, long l2) {
        int n = NativeBridge.mvk((int)l, 2);
        if (this.s.contains((int)l)) {
            GuiKeyTypedDispatcher.p((char)n, (int)l);
        }
        int n2 = KeyboardCodeUtil.M((int)l, (int)l2);
        KeyboardInputState keyboardInputState = InputEventDispatcher.getInstance().getKeyboardState();
        keyboardInputState.setKeyState(n2, true);
        return keyboardInputState.isCanceled();
    }
}

