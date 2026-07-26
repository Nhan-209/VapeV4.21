package gg.vape.input;

import gg.vape.input.InputEventDispatcher;
import gg.vape.input.InputEventHandler;

public class MouseButtonPressInputHandler
implements InputEventHandler {
    int x;

    public MouseButtonPressInputHandler(int n) {
        this.x = n;
    }

    @Override
    public boolean handle(long l, long l2) {
        return InputEventDispatcher.getInstance().getMouseState().setButtonState(this.x, true);
    }
}

