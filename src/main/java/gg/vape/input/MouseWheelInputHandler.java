package gg.vape.input;

import gg.vape.input.InputEventDispatcher;
import gg.vape.input.InputEventHandler;
import gg.vape.input.Win32InputConstants;

public class MouseWheelInputHandler
implements InputEventHandler {
    @Override
    public boolean handle(long l, long l2) {
        short s = Win32InputConstants.c(l);
        return InputEventDispatcher.getInstance().getMouseState().setScrollDelta(s);
    }
}

