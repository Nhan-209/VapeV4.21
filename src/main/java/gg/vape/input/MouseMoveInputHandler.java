package gg.vape.input;

import gg.vape.input.InputEventDispatcher;
import gg.vape.input.InputEventHandler;
import gg.vape.input.Win32InputConstants;

public class MouseMoveInputHandler
implements InputEventHandler {
    @Override
    public boolean handle(long l, long l2) {
        short s = Win32InputConstants.d(l2);
        short s9 = Win32InputConstants.c(l2);
        return InputEventDispatcher.getInstance().getMouseState().updateCursorPosition(s, s9);
    }
}

