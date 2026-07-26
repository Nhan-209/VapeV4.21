package gg.vape.input;

import gg.vape.input.InputEventDispatcher;
import gg.vape.input.InputEventHandler;

public class InputFocusGainedHandler
implements InputEventHandler {
    @Override
    public boolean handle(long l, long l2) {
        InputEventDispatcher.getInstance().getFocusState().markFocused();
        return false;
    }
}

